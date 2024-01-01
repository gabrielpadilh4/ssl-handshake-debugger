package io.github.gabrielpadilh4.commands;

import java.util.concurrent.Callable;

import io.github.gabrielpadilh4.models.SslCliParams;
import io.github.gabrielpadilh4.services.SSLService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
@Command(name = "ssl-handshake-debugger",
        mixinStandardHelpOptions = true,
        description = "Command line application that tests SSL/TLS handshake as client or server and prints the javax.net.debug output.",
        version = { "SSL Handshake Debugger 1.7",
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

    @Option(names = { "-server", "-s" }, description = "ip or host to bind or call")
    private String server;

    @Option(names = { "-url", "-u" }, description = "url to be called")
    private String url;

    @Option(names = { "--port", "-p" }, description = "port to listen or be hit, default value is 443", defaultValue = "443")
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

    @Option(names = { "--openAIApiKey" }, description = "OpenAI API Key (ChatGPT)", defaultValue = "")
    private String openAIApiKey;

    @Option(names = { "--all", "-a" }, description = "use javax.net.debug=all instead of javax.net.debug=ssl:handshake:verbose")
    private boolean allJavaxNetDebug;

    @Option(names = { "--version", "-v" }, versionHelp = true, description = "display version info")
    boolean versionInfoRequested;

    @Option(names = { "--help", "-h" }, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;

    @Override
    public Integer call() throws Exception {

        SslCliParams sslCliParams = SslCliParams.create()
        		.mode(mode)
        		.server(server)
        		.url(url)
        		.port(port)
        		.ciphers(ciphers)
        		.fileName(fileName)
        		.enabledProtocols(protocols)
        		.truststore(truststore)
        		.truststorePassword(truststorePassword)
        		.keystore(keystore)
        		.keystorePassword(keystorePassword)
        		.allDebug(allJavaxNetDebug)
        		.openAiApiKey(openAIApiKey)
        		.build();

        SSLService.logSSLHandshake(sslCliParams);

        return 0;
    }
}
