# SSL Handshake Debugger

![build](https://github.com/gabrielpadilh4/ssl-handshake-debugger/actions/workflows/maven.yml/badge.svg)

## About

Command line application that tests SSL/TLS handshake as client or server and prints the `javax.net.debug` output.

Usage:
```sh
$ ssl-handshake-debugger -h
Usage: handshake-debug [-ahV] [-f=<fileName>] [-p=<port>] [-pr=<enabledProtocols>] -s=<server> <mode>
Command line application that tests SSL/TLS handshake as client or server and prints the javax.net.debug output.
      <mode>              Mode to run, client or server
  -a, --all               Use javax.net.debug=all instead of javax.net.debug=ssl:handshake:verbose
  -f, --file=<fileName>   Filename to write the handshake output
  -h, --help              Show this help message and exit.
  -p, --port=<port>       Port to listen or be hit
      -pr, --protocols=<enabledProtocols>
                          TLS/SSL JVM enabled protocols list(e.g. TLSv1.2, TLSv1.3)
  -s, -server=<server>    IP or Host to bind or call
  -V, --version           Print version information and exit.
```

Command output example as client:

[See here](https://gist.github.com/gabrielpadilh4/bb53c94cdf578190a77b73cc6c323875)

Command output example as server:

[See here](https://gist.github.com/gabrielpadilh4/5468e38a2cbd11cc7d45d19c8d4b2589)

## Building from Source

To build from source, refer to the [building and working with the code base](docs/building.md) guide.

## Contributing

Before contributing to Keycloak, please read our [contributing guidelines](CONTRIBUTING.md).

## License

* [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
