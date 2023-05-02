package io.github.gabrielpadilh4;

import io.github.gabrielpadilh4.exceptions.InvalidUrlException;
import io.github.gabrielpadilh4.exceptions.MissingArgumentException;
import io.github.gabrielpadilh4.services.SSLService;

public class Main {

    public static void main(String[] args) throws InvalidUrlException, MissingArgumentException {

        System.out.println("********** SSL HANDSHAKE - DEBUGGER **********");

        if (args.length == 0) {
            printHelp();
        }

        SSLService sslService = new SSLService();

        for (int i = 0; i < args.length; i++) {
            final String argument = args[i];

            if (argument.equals("-h") || argument.equals("--help")) {
                printHelp();
                break;
            }

            if (argument.equals("-u") || argument.equals("--url")) {
                try {
                    String url = args[++i];
                    sslService.setUrl(url);
                    sslService.setEnable(true);
                } catch (IndexOutOfBoundsException e) {
                    throw new MissingArgumentException("Missing argument for option -u, --url");
                }
            }

            if (argument.equals("-f") || argument.equals("--filename")) {
                try {
                    String fileName = args[++i];
                    sslService.setFileName(fileName);
                } catch (IndexOutOfBoundsException e) {
                    throw new MissingArgumentException("Missing argument for option -f, --file");
                }
            }
        }

        if (sslService.isEnabled()) {
            sslService.logSSL();
        }
    }

    public static void printHelp() {
        System.out.println("Options:");
        System.out.println("  -u, --url      Url to be called for https handshake ssl debug");
        System.out.println("  -f, --filename Filename to write the output of ssl handshake logs");
        System.out.println("  -h, --help     Display help information");
    }
}