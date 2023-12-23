package io.github.gabrielpadilh4.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

import io.github.gabrielpadilh4.models.SslCliParams;
import io.github.gabrielpadilh4.services.SSLService;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
@Command(name = "ssl-handshake-debugger", 
        mixinStandardHelpOptions = true, 
        description = "Command line application that tests SSL/TLS handshake as client or server and prints the javax.net.debug output.", 
        version = { "SSL Handshake Debugger 1.5", 
                    "JVM: ${java.version} (${java.vendor} ${java.vm.name} ${java.vm.version})", 
                    "OS: ${os.name} ${os.version} ${os.arch}" 
                },
        sortOptions = false, 
        usageHelpAutoWidth = true,
        descriptionHeading = "%nDescription:%n%n",
        optionListHeading = "%nParameters:%n",
        requiredOptionMarker = '*')
public class SSLDebugCommand implements Callable<Integer> {

    @Parameters(description = "mode to run, client or server", defaultValue = "client")
    private String mode;

    @Option(names = { "-server", "-s" }, required = true, description = "ip or host to bind or call")
    private String server;

    @Option(names = { "--port", "-p" }, required = true, description = "port to listen or be hit")
    private int port;

    @Option(names = { "--ciphers", "-c" }, description = "enabled cipher suites(e.g TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384)", defaultValue = "")
    private String ciphers;

    @Option(names = { "--file", "-f" }, description = "filename to write the handshake output", defaultValue = "")
    private String fileName;

    @Option(names = { "--protocols", "-pr" }, description = "jvm ssl/tls enabled protocols list(e.g. TLSv1.2, TLSv1.3)", defaultValue = "")
    private String protocols;

    @Option(names = { "--truststore", "-ts" }, description = "truststore path", defaultValue = "")
    private String truststore;

    @Option(names = { "--truststorePassword", "-tsp" }, description = "truststore password", defaultValue = "")
    private String truststorePassword;

    @Option(names = { "--keystore", "-ks" }, description = "keystore path", defaultValue = "")
    private String keystore;

    @Option(names = { "--keystorePassword", "-ksp" }, description = "keystorePassword", defaultValue = "")
    private String keystorePassword;

    @Option(names = { "--all", "-a" }, description = "use javax.net.debug=all instead of javax.net.debug=ssl:handshake:verbose")
    private boolean allJavaxNetDebug;

    @Option(names = { "--version", "-v" }, versionHelp = true, description = "display version info")
    boolean versionInfoRequested;

    @Option(names = { "--help", "-h" }, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;

    @Override
    public Integer call() throws Exception {

        SslCliParams sslCliParams = new SslCliParams();

        sslCliParams.setMode(mode);
        sslCliParams.setServer(server);
        sslCliParams.setPort(port);
        sslCliParams.setCiphers(ciphers);
        sslCliParams.setFileName(fileName);
        sslCliParams.setEnabledProtocols(protocols);
        sslCliParams.setTruststore(truststore);
        sslCliParams.setTruststorePassword(truststorePassword);
        sslCliParams.setKeystore(keystore);
        sslCliParams.setKeystorePassword(keystorePassword);
        sslCliParams.setAllDebug(allJavaxNetDebug);

        SSLService.logSSLHandshake(sslCliParams);

        return 0;
    }
}
