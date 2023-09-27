package io.github.gabrielpadilh4.services;

import io.github.gabrielpadilh4.models.Server;
import io.github.gabrielpadilh4.models.SslCliParams;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.PrintStream;
import java.net.InetSocketAddress;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SSLService {

    private static int DEFAULT_SSL_PORT = 443;
    private static int DEFAULT_SOCKET_TIMEOUT_MILLIS = 1000;

    private static Server parseSslCliParams(SslCliParams sslCliParams) throws Exception {

        if (sslCliParams.isAllDebug()) {
            System.setProperty("javax.net.debug", "all");
        } else {
            System.setProperty("javax.net.debug", "ssl:handshake:verbose");
        }

        if (!sslCliParams.getFileName().isBlank()) {
            File file = new File(sslCliParams.getFileName());
            System.out.println("Writing output to file: " + sslCliParams.getFileName());
            PrintStream stream = new PrintStream(file);
            System.setErr(stream);
        }

        String[] serverParams = sslCliParams.getServer().split(":");

        String serverName = serverParams[0];
        int serverPort = DEFAULT_SSL_PORT;

        try {
            serverPort = Integer.parseInt(serverParams[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            System.out.println(String.format("Invalid port specified for %s using default port 443", serverName));
            serverPort = DEFAULT_SSL_PORT;
        }

        return new Server(serverName, serverPort);
    }

    public static void logSSLHandshake(SslCliParams sslCliParams) {
        try {

            Server serverToBeCalled = parseSslCliParams(sslCliParams);

            SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
            socket.setSoTimeout(DEFAULT_SOCKET_TIMEOUT_MILLIS);
            socket.connect(new InetSocketAddress(serverToBeCalled.getServerName(), serverToBeCalled.getServerPort()), DEFAULT_SOCKET_TIMEOUT_MILLIS);
            socket.startHandshake();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
