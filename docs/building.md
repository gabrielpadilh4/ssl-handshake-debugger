## Building from source

Ensure you have JDK 11 (or newer), Git and Maven 3.5+ installed

    java -version
    git --version
    mvn --version

---    
First clone the ssl-handshake-debugger repository:
    
    git clone https://github.com/gabrielpadilh4/ssl-handshake-debugger
    cd ssl-handshake-debugger
    
To build ssl-handshake-debugger run:

    mvn clean package
    
This will build the project.

You can then find the `bin` and `repo` directories in `target/ssl-handshake-debugger` folder.

---
