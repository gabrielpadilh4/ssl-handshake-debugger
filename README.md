# SSL Handshake Debugger

![build](https://github.com/gabrielpadilh4/ssl-handshake-debugger/actions/workflows/maven.yml/badge.svg)

## About

Command line application that tests SSL/TLS handshake as client or server and prints the `javax.net.debug` output.

Usage:
```sh
 $ java -jar ssl-handshake-debugger-1.0-SNAPSHOT.jar
********** SSL HANDSHAKE - DEBUGGER **********
For default, the program uses ssl:handshake:verbose value for javax.net.debug
Options:
  -m, --mode     (Required) Aplication mode, client or server
  -s, --server   (Required) Server or Ip Address with the port(default 443) to listen or call for ssl handshake (e.g example:8443 / 127.0.0.1:2100 / google.com)
  -f, --filename Redirects debug log output to a file
  -a, --all      Enable all debugging log javax.net.debug=all
  -h, --help     Display help information
```

Command output example as client:

[See here](https://gist.github.com/gabrielpadilh4/bb53c94cdf578190a77b73cc6c323875)

Command output example as server:

[See here](https://gist.github.com/gabrielpadilh4/5468e38a2cbd11cc7d45d19c8d4b2589)

Feel free to contribute.