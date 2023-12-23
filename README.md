# SSL Handshake Debugger

![build](https://github.com/gabrielpadilh4/ssl-handshake-debugger/actions/workflows/maven.yml/badge.svg)

## About

Command line application built in Java that tests SSL/TLS handshake as client or server and prints the [javax.net.debug](https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/ReadDebug.html) output.

Usage:
```sh
$ ssl-handshake-debugger -h
Usage: handshake-debug [-ahv] [-c=<ciphers>] [-f=<fileName>] -p=<port> [-pr=<protocols>] -s=<server> <mode>

Description:

Command line application that tests SSL/TLS handshake as client or server and prints the javax.net.debug output.
*     <mode>                mode to run, client or server

Parameters:
* -s, -server=<server>      ip or host to bind or call
* -p, --port=<port>         port to listen or be hit
  -c, --ciphers=<ciphers>   enabled cipher suites(e.g TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384)
  -f, --file=<fileName>     filename to write the handshake output
      -pr, --protocols=<protocols>
                            jvm ssl/tls enabled protocols list(e.g. TLSv1.2, TLSv1.3)
  -a, --all                 use javax.net.debug=all instead of javax.net.debug=ssl:handshake:verbose
  -v, --version             display version info
  -h, --help                display this help message
```

Command output example as client:

[See here](https://gist.github.com/gabrielpadilh4/bb53c94cdf578190a77b73cc6c323875)

Command output example as server:

[See here](https://gist.github.com/gabrielpadilh4/5468e38a2cbd11cc7d45d19c8d4b2589)

## Installation

Use the following command to get the `ssl-handshake-debugger` installed on your machine:
```sh
sh <(curl -s https://raw.githubusercontent.com/gabrielpadilh4/ssl-handshake-debugger/main/install.sh)
```

It will download and extract the binaries of `ssl-handshake-debugger` command on `$HOME/ssl-handshake-debugger` and add it to the `$PATH` variable by changing the `.bashrc` file.

## Building from Source

To build from source, refer to the [building and working with the code base](docs/building.md) guide.

## Contributing

Before contributing to ssl-handshake-debugger, please read our [contributing guidelines](CONTRIBUTING.md).

## License

* [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
