package io.github.gabrielpadilh4.models;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SslCliParams {

    private String server;
    private String fileName;
    private boolean allDebug;
    private boolean enable;

    public SslCliParams() {
        this.server = "";
        this.fileName = "";
        this.enable = false;
        this.allDebug = false;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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

    public boolean isEnabled() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
