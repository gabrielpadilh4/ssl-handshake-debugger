package io.github.gabrielpadilh4.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import io.github.gabrielpadilh4.commands.SSLDebugCommand;
import io.github.gabrielpadilh4.models.Server;
import io.github.gabrielpadilh4.models.SslCliParams;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SSLService {

    private static int DEFAULT_SOCKET_TIMEOUT_MILLIS = 3000;

    private static Server parseSslCliParams(SslCliParams sslCliParams) throws Exception {

        if (sslCliParams.isAllDebug()) {
            System.setProperty("javax.net.debug", "all");
        } else {
            System.setProperty("javax.net.debug", "ssl:handshake:verbose");
        }

        if (!sslCliParams.getEnabledProtocols().isBlank()) {
            System.setProperty("jdk.tls.server.protocols", sslCliParams.getEnabledProtocols());
            System.setProperty("jdk.tls.client.protocols", sslCliParams.getEnabledProtocols());
        }

        if (!sslCliParams.getCiphers().isBlank()) {
            System.setProperty("jdk.tls.client.cipherSuites", sslCliParams.getCiphers());
            System.setProperty("jdk.tls.server.cipherSuites", sslCliParams.getCiphers());
        }

        if (!sslCliParams.getKeystore().isBlank()) {
            System.setProperty("javax.net.ssl.keyStore", sslCliParams.getKeystore());
            System.setProperty("javax.net.ssl.keyStorePassword", sslCliParams.getKeystorePassword());
        } else {

            Path temp = Files.createTempDirectory("app");
            Files.copy(SSLDebugCommand.class.getResourceAsStream("/server.keystore"), temp, StandardCopyOption.REPLACE_EXISTING);

            String keyStorePath = temp.toFile().getAbsolutePath();
            System.setProperty("javax.net.ssl.keyStore", keyStorePath);
            System.setProperty("javax.net.ssl.keyStorePassword", "password");
        }

        if (!sslCliParams.getTruststore().isBlank()) {
            System.setProperty("javax.net.ssl.trustStore", sslCliParams.getTruststore());
            System.setProperty("javax.net.ssl.trustStorePassword", sslCliParams.getTruststorePassword());
        }

        if (!sslCliParams.getFileName().isBlank()) {
            File file = new File(sslCliParams.getFileName());
            System.out.println("Writing output to file: " + sslCliParams.getFileName());
            PrintStream stream = new PrintStream(file);
            System.setErr(stream);
        }

        String serverName = sslCliParams.getServer();
        int serverPort = sslCliParams.getPort();

        return new Server(serverName, serverPort);
    }

    private static void openUrlSocket(String url) throws Exception {
        URL urlTest = new URL(url);
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(urlTest.getHost(), urlTest.getDefaultPort());
        socket.startHandshake();
        socket.close();
    }

    private static void openClientSocket(Server serverToBeCalled) throws IOException {
        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
        socket.setSoTimeout(DEFAULT_SOCKET_TIMEOUT_MILLIS);
        socket.connect(
                new InetSocketAddress(serverToBeCalled.getServerName(), serverToBeCalled.getServerPort()),
                DEFAULT_SOCKET_TIMEOUT_MILLIS);
        socket.startHandshake();
        socket.close();
    }

    private static void openServerSocket(Server serverListener) throws IOException {

        ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
        InetAddress bindAddress = InetAddress.getByName(serverListener.getServerName());

        try (SSLServerSocket listener = (SSLServerSocket) factory.createServerSocket(serverListener.getServerPort(), 5,
                bindAddress)) {

            try (Socket socket = listener.accept()) {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String request = null;

                while ((request = bufferedReader.readLine()) != null) {
                    System.out.println(request);
                    System.out.flush();
                }
            }
        }

    }

    public static void logSSLHandshake(SslCliParams sslCliParams) {
        try {

            Server server = parseSslCliParams(sslCliParams);

            if (sslCliParams.getMode().equals("client")) {
                if (!sslCliParams.getUrl().isBlank()) {
                    openUrlSocket(sslCliParams.getUrl());
                    return;
                }
                openClientSocket(server);
            }

            if (sslCliParams.getMode().equals("server")) {
                openServerSocket(server);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
