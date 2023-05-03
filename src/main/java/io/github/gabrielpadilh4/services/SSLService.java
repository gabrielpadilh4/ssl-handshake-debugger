package io.github.gabrielpadilh4.services;

import io.github.gabrielpadilh4.exceptions.InvalidUrlException;
import io.github.gabrielpadilh4.models.SslCliParams;
import io.github.gabrielpadilh4.utils.LoggerUtil;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.security.cert.X509Certificate;
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

    public static void logSSLHandshake(SslCliParams sslCliParams) {
        try {

            System.setProperty("javax.net.debug", "ssl:handshake:verbose");

            if (!sslCliParams.getFileName().isBlank()) {
                File file = new File(sslCliParams.getFileName());
                PrintStream stream = new PrintStream(file);
                System.setErr(stream);
            }

            URL url = new URL(sslCliParams.getUrl());
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(url.getHost(), url.getDefaultPort());
            SSLSession session = socket.getSession();
            String cipherSuite = session.getCipherSuite();
            X509Certificate[] peerCertificates = (X509Certificate[]) session.getPeerCertificates();
            String protocolVersion = session.getProtocol();

            socket.close();

            LoggerUtil.LOG("--------------------");
            LoggerUtil.LOG(String.format("Cipher suite: %s", cipherSuite));
            LoggerUtil.LOG(String.format("Protocol version: %s", protocolVersion));
            LoggerUtil.LOG("Peer certificates:");
            for (X509Certificate cert : peerCertificates) {
                LoggerUtil.LOG(String.format("- Subject: %s", cert.getSubjectDN().getName()));
                LoggerUtil.LOG(String.format("  Issuer: %s", cert.getIssuerDN().getName()));
                LoggerUtil.LOG(String.format("  Valid from: %s", cert.getNotBefore().toString()));
                LoggerUtil.LOG(String.format("  Valid until: %s", cert.getNotAfter().toString()));
            }
            LoggerUtil.LOG("--------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
