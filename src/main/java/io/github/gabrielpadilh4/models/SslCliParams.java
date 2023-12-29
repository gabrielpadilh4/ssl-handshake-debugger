package io.github.gabrielpadilh4.models;

import io.github.gabrielpadilh4.exceptions.InvalidModeException;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SslCliParams {

    private String mode;
    private String server;
    private String url;
    private int port;
    private String ciphers;
    private String fileName;
    private String enabledProtocols;
    private String keystore;
    private String keystorePassword;
    private String truststore;
    private String truststorePassword;
    private String openAiApiKey;
    private boolean allDebug;

    public SslCliParams() {
        this.mode = "";
        this.server = "";
        this.url = "";
        this.port = 0;
        this.ciphers = "";
        this.fileName = "";
        this.enabledProtocols = "";
        this.keystore = "";
        this.keystorePassword = "";
        this.truststore = "";
        this.truststorePassword = "";
        this.openAiApiKey = "";
        this.allDebug = false;
    }
    
    public SslCliParams(SslCliParamsBuilder builder) {
        this.mode = builder.mode;
        this.server = builder.server;
        this.url = builder.url;
        this.port = builder.port;
        this.ciphers = builder.ciphers;
        this.fileName = builder.fileName;
        this.enabledProtocols = builder.enabledProtocols;
        this.keystore = builder.keystore;
        this.keystorePassword = builder.keystorePassword;
        this.truststore = builder.truststore;
        this.truststorePassword = builder.truststorePassword;
        this.allDebug = builder.allDebug;
        this.openAiApiKey = builder.openAiApiKey;
    }
    
    public static SslCliParamsBuilder create() {
    	return new SslCliParamsBuilder();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getKeystore() {
        return this.keystore;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getKeystorePassword() {
        return this.keystorePassword;
    }

    public void setTruststore(String truststore) {
        this.truststore = truststore;
    }

    public String getTruststore() {
        return this.truststore;
    }

    public void setTruststorePassword(String truststorePassword) {
        this.truststorePassword = truststorePassword;
    }

    public String getTruststorePassword() {
        return this.truststorePassword;
    }

    public boolean isAllDebug() {
        return this.allDebug;
    }

    public void setAllDebug(boolean allDebug) {
        this.allDebug = allDebug;
    }

	public String getOpenAiApiKey() {
		return openAiApiKey;
	}

	public void setOpenAiApiKey(String openAiApiKey) {
		this.openAiApiKey = openAiApiKey;
	}
}
