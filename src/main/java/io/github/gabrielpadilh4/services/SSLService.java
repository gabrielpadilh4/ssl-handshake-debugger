package io.github.gabrielpadilh4.services;

import io.github.gabrielpadilh4.exceptions.InvalidUrlException;
import io.github.gabrielpadilh4.models.CommandLineSSL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SSLService {

    private final static String URL_PATTERN = "^(https):\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
    private final static int DEFAULT_TIMEOUT = 5000;
    private final static String DEFAULT_HTTP_METHOD = "GET";

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
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            connection.setSSLSocketFactory(sslSocketFactory);
            connection.setRequestMethod(DEFAULT_HTTP_METHOD);
            connection.setReadTimeout(DEFAULT_TIMEOUT);
            connection.setReadTimeout(DEFAULT_TIMEOUT);

            int responseCode = connection.getResponseCode();

            if (!commandLineSSL.getFileName().isBlank()) {
                System.err.printf("\nResponse code %s for %s", responseCode, commandLineSSL.getUrl());
            }

            System.out.printf("\nResponse code %s for %s", responseCode, commandLineSSL.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
