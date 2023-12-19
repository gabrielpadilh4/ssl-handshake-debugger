package io.github.gabrielpadilh4;

import io.github.gabrielpadilh4.commands.SSLDebugCommand;
import picocli.CommandLine;

/**
 * @author gabrielpadilhasantos@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new SSLDebugCommand()).execute(args);
        System.exit(exitCode);
    }
}