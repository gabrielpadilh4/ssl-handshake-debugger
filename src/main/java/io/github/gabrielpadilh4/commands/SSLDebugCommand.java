package io.github.gabrielpadilh4.commands;

import io.github.gabrielpadilh4.exceptions.MissingArgumentException;
import io.github.gabrielpadilh4.models.SslCliParams;
import io.github.gabrielpadilh4.services.SSLService;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SSLDebugCommand {

    public static void execute(String[] args) {

        try {

            System.out.println("********** SSL HANDSHAKE - DEBUGGER **********");

            SslCliParams sslCliParams = new SslCliParams();

            if (args.length == 0) {
                printHelp();
            }

            for (int i = 0; i < args.length; i++) {
                final String argument = args[i];

                if (argument.equals("-h") || argument.equals("--help")) {
                    printHelp();
                    break;
                }

                if (argument.equals("-s") || argument.equals("--server")) {
                    try {
                        String server = args[++i];
                        sslCliParams.setServer(server);
                        sslCliParams.setEnable(true);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingArgumentException("Missing argument for option -s, --server");
                    }
                }

                if (argument.equals("-f") || argument.equals("--filename")) {
                    try {
                        String fileName = args[++i];
                        sslCliParams.setFileName(fileName);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingArgumentException("Missing argument for option -f, --file");
                    }
                }

                if (argument.equals("-a") || argument.equals("--all")) {
                    sslCliParams.setAllDebug(true);
                }
            }

            if (sslCliParams.isEnabled()) {
                SSLService.logSSLHandshake(sslCliParams);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printHelp() {
        System.out.println("For default, the program uses ssl:handshake:verbose value for javax.net.debug");
        System.out.println("Options:");
        System.out.println("  -s, --server   (Required)Server or Ip Address with the port(default 443) to be tested for ssl handshake (e.g example:8443 / 127.0.0.1:2100 / google.com)");
        System.out.println("  -f, --filename Redirects debug log output to a file");
        System.out.println("  -a, --all      Enable all debugging log javax.net.debug=all");
        System.out.println("  -h, --help     Display help information");
    }

}
