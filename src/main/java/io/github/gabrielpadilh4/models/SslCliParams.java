package io.github.gabrielpadilh4.models;

import io.github.gabrielpadilh4.exceptions.InvalidModeException;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SslCliParams {

    private String mode;
    private String server;
    private int port;
    private String ciphers;
    private String fileName;
    private String enabledProtocols;
    private boolean allDebug;

    public SslCliParams() {
        this.mode = "";
        this.server = "";
        this.port = 0;
        this.ciphers = "";
        this.fileName = "";
        this.enabledProtocols = "";
        this.allDebug = false;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) throws InvalidModeException {
        if (mode.equals("client") || mode.equals("server")) {
            this.mode = mode;
            return;
        }

        throw new InvalidModeException(
                "Invalid value for application mode! Accepted values client / server. Current value: " + mode);
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getCiphers() {
        return ciphers;
    }

    public void setCiphers(String ciphers) {
        this.ciphers = ciphers;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setEnabledProtocols(String enabledProtocols) {
        this.enabledProtocols = enabledProtocols;
    }

    public String getEnabledProtocols() {
        return this.enabledProtocols;
    }

    public boolean isAllDebug() {
        return this.allDebug;
    }

    public void setAllDebug(boolean allDebug) {
        this.allDebug = allDebug;
    }
}
