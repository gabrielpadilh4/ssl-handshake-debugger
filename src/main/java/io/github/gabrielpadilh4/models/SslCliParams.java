package io.github.gabrielpadilh4.models;

import io.github.gabrielpadilh4.exceptions.InvalidModeException;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SslCliParams {

    private String mode;
    private String server;
    private int port;
    private String fileName;
    private boolean allDebug;

    public SslCliParams() {
        this.mode = "";
        this.server = "";
        this.fileName = "";
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isAllDebug() {
        return this.allDebug;
    }

    public void setAllDebug(boolean allDebug) {
        this.allDebug = allDebug;
    }
}
