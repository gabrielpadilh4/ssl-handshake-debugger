# SSL Handshake Debugger

![build](https://github.com/gabrielpadilh4/ssl-handshake-debugger/actions/workflows/maven.yml/badge.svg)

Built with JDK 11 using Maven.

A very simple java command line application to show ssl and handshake logs from jvm.

```sh
 $ java -jar ssl-handshake-debugger-1.0-SNAPSHOT.jar
********** SSL HANDSHAKE - DEBUGGER **********
For default, the program uses ssl:handshake:verbose value for javax.net.debug
Options:
  -u, --url      Url to be called for https ssl handshake debug log (it has to start with https://)
  -f, --filename Redirects debug log output to a file
  -a, --all      Enable all debugging, this will display a very verbose output
  -h, --help     Display help information
```

Command output example:

[See here](https://gist.github.com/gabrielpadilh4/bb53c94cdf578190a77b73cc6c323875#file-ssl-handshake-example-output-log)

Feel free to contribute.
