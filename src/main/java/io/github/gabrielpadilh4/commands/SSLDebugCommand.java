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
@Command(name = "handshake-debug", mixinStandardHelpOptions = true, 
        description = "Command line application that tests SSL/TLS handshake as client or server and prints the javax.net.debug output.", 
        version = { "SSL Handshake Debugger 1.3", "JVM: ${java.version} (${java.vendor} ${java.vm.name} ${java.vm.version})", "OS: ${os.name} ${os.version} ${os.arch}" },
        usageHelpAutoWidth = true)
public class SSLDebugCommand implements Callable<Integer> {

    @Parameters(description = "Mode to run, client or server", defaultValue = "client")
    private String mode;

    @Option(names = { "-s", "-server" }, description = "IP or Host to bind or call", required = true)
    private String server;

    @Option(names = { "-p", "--port" }, description = "Port to listen or be hit", defaultValue = "443", required = true)
    private int port;

    @Option(names = { "-f", "--file" }, description = "Filename to write the handshake output", defaultValue = "", required = false)
    private String fileName;

    @Option(names = { "-pr", "--protocols" }, description = "TLS/SSL JVM enabled protocols list(e.g. TLSv1.2, TLSv1.3)", defaultValue = "", required = false)
    private String enabledProtocols;

    @Option(names = { "-a", "--all" }, description = "Use javax.net.debug=all instead of javax.net.debug=ssl:handshake:verbose", required = false)
    private boolean allJavaxNetDebug;

    @Override
    public Integer call() throws Exception {

        SslCliParams sslCliParams = new SslCliParams();

        sslCliParams.setMode(mode);
        sslCliParams.setServer(server);
        sslCliParams.setPort(port);
        sslCliParams.setFileName(fileName);
        sslCliParams.setEnabledProtocols(enabledProtocols);
        sslCliParams.setAllDebug(allJavaxNetDebug);

        SSLService.logSSLHandshake(sslCliParams);

        return 0;
    }
}
