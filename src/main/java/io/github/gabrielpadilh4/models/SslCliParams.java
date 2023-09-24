package io.github.gabrielpadilh4.models;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SslCliParams {
    private String url;
    private String fileName;
    private boolean allDebug;
    private boolean enable;

    public SslCliParams() {
        this.url = "";
        this.fileName = "";
        this.enable = false;
        this.allDebug = false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
