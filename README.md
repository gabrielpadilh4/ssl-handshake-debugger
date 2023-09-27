# SSL Handshake Debugger

![build](https://github.com/gabrielpadilh4/ssl-handshake-debugger/actions/workflows/maven.yml/badge.svg)

Built with JDK 11 using Maven.

A very simple java command line application to show ssl and handshake logs from jvm.

```sh
 $ java -jar ssl-handshake-debugger-1.0-SNAPSHOT.jar
********** SSL HANDSHAKE - DEBUGGER **********
For default, the program uses ssl:handshake:verbose value for javax.net.debug
Options:
  -s, --server   (Required)Server or Ip Address with the port(default 443) to be tested for ssl handshake (e.g example:8443 / 127.0.0.1:2100 / google.com)
  -f, --filename Redirects debug log output to a file
  -a, --all      Enable all debugging log javax.net.debug=all
  -h, --help     Display help information
```

Command output example:

[See here](https://gist.github.com/gabrielpadilh4/bb53c94cdf578190a77b73cc6c323875#file-ssl-handshake-example-output-log)

Feel free to contribute.
