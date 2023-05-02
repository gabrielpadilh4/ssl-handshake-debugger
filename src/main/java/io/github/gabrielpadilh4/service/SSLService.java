package io.github.gabrielpadilh4.service;

import io.github.gabrielpadilh4.exception.InvalidUrlException;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.regex.Pattern;

public class SSLService {

    private final static String URL_PATTERN = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
    private final static int DEFAULT_TIMEOUT = 5000;
    private final static String DEFAULT_HTTP_METHOD = "GET";
    private String url;
    private String fileName;
    private boolean enable;

    public SSLService() {
        this.url = "";
        this.fileName = "";
        this.enable = false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws InvalidUrlException {
        boolean isMatch = Pattern.compile(URL_PATTERN)
                .matcher(url)
                .find();

        if (!isMatch) {
            throw new InvalidUrlException(String.format("Value %s is invalid for URL", url));
        }

        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        System.out.printf("\nLog file: %s", this.getFileName());
    }

    public boolean isEnabled() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void logSSL() {
        try {
            System.setProperty("javax.net.debug", "ssl:handshake");

            if (!this.getFileName().isBlank()) {
                File file = new File(this.getFileName());
                PrintStream stream = new PrintStream(file);
                System.setErr(stream);
                System.err.println("********** SSL HANDSHAKE - DEBUGGER **********\n");
            }

            URL url = new URL(this.getUrl());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(DEFAULT_HTTP_METHOD);
            connection.setReadTimeout(DEFAULT_TIMEOUT);
            connection.setReadTimeout(DEFAULT_TIMEOUT);

            int responseCode = connection.getResponseCode();

            System.err.printf("\nResponse code %s for %s%n%n", responseCode, this.getUrl());
            System.out.printf("\nResponse code %s for %s%n%n", responseCode, this.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
