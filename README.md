# SSL Handshake Debugger

![build](https://github.com/gabrielpadilh4/ssl-handshake-debugger/actions/workflows/maven.yml/badge.svg)

## Usage

```sh
$ ssl-handshake-debugger -h
Usage: ssl-handshake-debugger [-ahv] [-c=<ciphers>] [-f=<fileName>] [-ks=<keystore>] [-ksp=<keystorePassword>] [-p=<port>] [-pr=<protocols>] [-s=<server>] [-ts=<truststore>] [-tsp=<truststorePassword>] [-u=<url>] <mode>

Description:

Command line application that tests SSL/TLS handshake as client or server and prints the javax.net.debug output.
*     <mode>                mode to run, client or server

Parameters:
  -s, -server=<server>                            ip or host to bind or call
  -u, -url=<url>                                  url to be called
  -p, --port=<port>                               port to listen or be hit
  -c, --ciphers=<ciphers>                         enabled cipher suites(e.g TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384)
  -f, --file=<fileName>                           filename to write the handshake output
  -pr, --protocols=<protocols>                    jvm ssl/tls enabled protocols list(e.g. TLSv1.2, TLSv1.3)
  -ts, --truststore=<truststore>                  truststore path
  -tsp, --truststorePassword=<truststorePassword> truststore password
  -ks, --keystore=<keystore>                      keystore path
  -ksp, --keystorePassword=<keystorePassword>     keystorePassword
  -a, --all                                       use javax.net.debug=all instead of javax.net.debug=ssl:handshake:verbose
  -v, --version                                   display version info
  -h, --help                                      display this help message
```

## Installation

Use the following command to get the `ssl-handshake-debugger` installed on your machine:
```sh
sh <(curl -s https://raw.githubusercontent.com/gabrielpadilh4/ssl-handshake-debugger/main/install.sh)
```

It will download and extract the binaries of `ssl-handshake-debugger` command on `$HOME/ssl-handshake-debugger` and add it to the `$PATH` variable by changing the `.bashrc` file.

## Usage Examples

These examples showcase various scenarios for using the SSL handshake debugger CLI, it demonstrates how to set server or client modes, define server details, enable debugging, specify protocols, cipher suites, keystore, and truststore paths, and direct output to a file. Adjust these commands according to your specific needs and configurations.

### Running as Server
```
ssl-handshake-debugger -s localhost -p 443 -pr TLSv1.2 -c TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 server
```

`-s localhost`: Specifies the server as 'localhost'.

`-p 443`: Sets the port to '443'.

`-pr TLSv1.2`: Defines the enabled SSL/TLS protocols to 'TLSv1.2'.

`-c TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384`: Specifies the enabled cipher suite

### Running as Client
Calling a server and port:
```
ssl-handshake-debugger -s example.com -p 443 client
```

`-s localhost`: Specifies the server as 'localhost'.

`-p 443`: Sets the port to '443'.

Calling an URL:
```
ssl-handshake-debugger -u https://example.com
```

`-u https://example.com`: Specifies the url to be called.

### Enabling Debugging for All SSL Operations
```
ssl-handshake-debugger -s example.com -p 443 -a client
```

`-a`: Sets the debugging mode to 'all' for all SSL operations.

### Specifying Keystore and Truststore
```
ssl-handshake-debugger -s example.com -p 443 -ks /path/to/keystore -ksp password -ts /path/to/truststore -tsp password client
```

`-ks /path/to/keystore`: Sets the path to the keystore.

`-ksp password`: Specifies the keystore password.

`-ts /path/to/truststore`: Sets the path to the truststore.

`-tsp password`: Specifies the truststore password.

### Writing Output to a File
```
ssl-handshake-debugger -s example.com -p 443 -f output.txt client
```

`-f output.txt`: Writes the handshake output to a file named 'output.txt'.

## About

Command line application built in Java that tests SSL/TLS handshake as client or server and prints the [javax.net.debug](https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/ReadDebug.html) output.

Output as client:

[See here](https://gist.github.com/gabrielpadilh4/bb53c94cdf578190a77b73cc6c323875)

Output as server:

[See here](https://gist.github.com/gabrielpadilh4/5468e38a2cbd11cc7d45d19c8d4b2589)

## Building from Source

To build from source, refer to the [building and working with the code base](docs/building.md) guide.

## Contributing

Before contributing to ssl-handshake-debugger, please read our [contributing guidelines](CONTRIBUTING.md).

## License

* [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
