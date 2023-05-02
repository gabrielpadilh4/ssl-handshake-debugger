package io.github.gabrielpadilh4.commands;

import io.github.gabrielpadilh4.exceptions.InvalidUrlException;
import io.github.gabrielpadilh4.exceptions.MissingArgumentException;
import io.github.gabrielpadilh4.models.CommandLineSSL;
import io.github.gabrielpadilh4.services.SSLService;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class SSLDebugCommand {

    public static void execute(String[] args) {

        try {

            System.out.println("********** SSL HANDSHAKE - DEBUGGER **********");

            CommandLineSSL commandLineSSL = new CommandLineSSL();

            if (args.length == 0) {
                printHelp();
            }

            for (int i = 0; i < args.length; i++) {
                final String argument = args[i];

                if (argument.equals("-h") || argument.equals("--help")) {
                    printHelp();
                    break;
                }

                if (argument.equals("-u") || argument.equals("--url")) {
                    try {
                        String url = args[++i];
                        SSLService.validateHttpsUrl(url);
                        commandLineSSL.setUrl(url);
                        commandLineSSL.setEnable(true);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingArgumentException("Missing argument for option -u, --url");
                    } catch (InvalidUrlException e) {
                        e.printStackTrace();
                    }
                }

                if (argument.equals("-f") || argument.equals("--filename")) {
                    try {
                        String fileName = args[++i];
                        commandLineSSL.setFileName(fileName);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingArgumentException("Missing argument for option -f, --file");
                    }
                }
            }

            if (commandLineSSL.isEnabled()) {
                SSLService.logSSLHandshake(commandLineSSL);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printHelp() {
        System.out.println("Options:");
        System.out.println("  -u, --url      Url to be called for https ssl handshake (it has to start with https://)");
        System.out.println("  -f, --filename Filename to write the output of ssl handshake log");
        System.out.println("  -h, --help     Display help information");
    }

}
