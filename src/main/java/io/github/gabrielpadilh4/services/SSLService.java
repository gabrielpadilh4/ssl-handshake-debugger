package io.github.gabrielpadilh4.services;

import io.github.gabrielpadilh4.exceptions.InvalidUrlException;
import io.github.gabrielpadilh4.models.CommandLineSSL;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SSLService {

    private final static String URL_PATTERN = "^(https):\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";

    public static void validateHttpsUrl(String url) throws InvalidUrlException {
        boolean isMatch = Pattern.compile(URL_PATTERN)
                .matcher(url)
                .find();

        if (!isMatch) {
            throw new InvalidUrlException(String.format("Value %s is invalid for URL. It must have to start with https://", url));
        }
    }

    public static void logSSLHandshake(CommandLineSSL commandLineSSL) {
        try {

            System.setProperty("javax.net.debug", "ssl:handshake");

            if (!commandLineSSL.getFileName().isBlank()) {
                File file = new File(commandLineSSL.getFileName());
                PrintStream stream = new PrintStream(file);
                System.setErr(stream);
                System.err.println("********** SSL HANDSHAKE - DEBUGGER **********\n");
            }

            URL url = new URL(commandLineSSL.getUrl());
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(url.getHost(), url.getDefaultPort());
            SSLSession session = socket.getSession();
            String cipherSuite = session.getCipherSuite();
            X509Certificate[] peerCertificates = (X509Certificate[]) session.getPeerCertificates();
            String protocolVersion = session.getProtocol();

            if (!commandLineSSL.getFileName().isBlank()) {
                System.err.printf("Cipher suite: %s\n", cipherSuite);
                System.err.printf("Protocol version: %s\n", protocolVersion);
                System.err.print("Peer certificates:\n");
                for (X509Certificate cert : peerCertificates) {
                    System.err.printf("- Subject: %s\n", cert.getSubjectDN().getName());
                    System.err.printf("  Issuer: %s\n", cert.getIssuerDN().getName());
                    System.err.printf("  Valid from: %s\n", cert.getNotBefore().toString());
                    System.err.printf("  Valid until: %s\n", cert.getNotAfter().toString());
                }
            }

            System.out.printf("Cipher suite: %s\n", cipherSuite);
            System.out.printf("Protocol version: %s\n", protocolVersion);
            System.out.print("Peer certificates:\n");
            for (X509Certificate cert : peerCertificates) {
                System.out.printf("- Subject: %s\n", cert.getSubjectDN().getName());
                System.out.printf("  Issuer: %s\n", cert.getIssuerDN().getName());
                System.out.printf("  Valid from: %s\n", cert.getNotBefore().toString());
                System.out.printf("  Valid until: %s\n", cert.getNotAfter().toString());
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
