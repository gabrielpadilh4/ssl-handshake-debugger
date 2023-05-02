package io.github.gabrielpadilh4.models;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class CommandLineSSL {
    private String url;
    private String fileName;
    private boolean enable;

    public CommandLineSSL() {
        this.url = "";
        this.fileName = "";
        this.enable = false;
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

    public boolean isEnabled() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
