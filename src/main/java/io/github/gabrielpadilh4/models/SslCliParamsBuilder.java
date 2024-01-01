package io.github.gabrielpadilh4.models;

import java.util.Optional;

/**
 * @author pedro-hos@outlook.com
 */
public class SslCliParamsBuilder {

	protected String mode;
	protected String server;
	protected String url;
	protected int port;
	protected String ciphers;
	protected String fileName;
	protected String enabledProtocols;
	protected String keystore;
	protected String keystorePassword;
	protected String truststore;
	protected String truststorePassword;
	protected boolean allDebug;
	protected String openAiApiKey;

	public SslCliParamsBuilder openAiApiKey(String openAiApiKey) {
		this.openAiApiKey = Optional.ofNullable(openAiApiKey).orElse("");
		return this;
	}

	public SslCliParamsBuilder mode(String mode) {
		this.mode = Optional.ofNullable(mode).orElse("");
		return this;
	}

	public SslCliParamsBuilder server(String server) {
		this.server = Optional.ofNullable(server).orElse("");
		return this;
	}

	public SslCliParamsBuilder url(String url) {
		this.url = Optional.ofNullable(url).orElse("");
		return this;
	}

	public SslCliParamsBuilder port(int port) {
		this.port = port;
		return this;
	}

	public SslCliParamsBuilder ciphers(String ciphers) {
		this.ciphers = Optional.ofNullable(ciphers).orElse("");
		return this;
	}

	public SslCliParamsBuilder fileName(String fileName) {
		this.fileName = Optional.ofNullable(fileName).orElse("");
		return this;
	}

	public SslCliParamsBuilder enabledProtocols(String enabledProtocols) {
		this.enabledProtocols = Optional.ofNullable(enabledProtocols).orElse("");
		return this;
	}

	public SslCliParamsBuilder keystore(String keystore) {
		this.keystore = Optional.ofNullable(keystore).orElse("");
		return this;
	}

	public SslCliParamsBuilder keystorePassword(String keystorePassword) {
		this.keystorePassword = Optional.ofNullable(keystorePassword).orElse("");
		return this;
	}

	public SslCliParamsBuilder truststore(String truststore) {
		this.truststore = Optional.ofNullable(truststore).orElse("");
		return this;
	}

	public SslCliParamsBuilder truststorePassword(String truststorePassword) {
		this.truststorePassword = Optional.ofNullable(truststorePassword).orElse("");
		return this;
	}

	public SslCliParamsBuilder allDebug(boolean allDebug) {
		this.allDebug = Optional.ofNullable(allDebug).orElse(false);
		return this;
	}

    public SslCliParams build() {
    	return new SslCliParams(this);
    }

}
