#!/bin/sh

VERSION="v1.4"
INSTALL_DIR="$HOME"
BIN_DIR="$INSTALL_DIR/ssl-handshake-debugger/bin"
DOWNLOAD_URL="https://github.com/gabrielpadilh4/ssl-handshake-debugger/releases/download/$VERSION/ssl-handshake-debugger.zip"
ZIP_DOWNLOAD_FILE_NAME="ssl-handshake-debugger.zip"


function display_error() {
    echo "Error: $1"
    exit 1
}

# Download the application archive and extract it to the installation directory
echo "Downloading and extracting the application..."
wget -q -O /tmp/$ZIP_DOWNLOAD_FILE_NAME "$DOWNLOAD_URL" && unzip -o /tmp/$ZIP_DOWNLOAD_FILE_NAME -d "$INSTALL_DIR" || display_error "Failed to donwload and extract ssl-handshake-debugger"

# Add the application's bin directory to the PATH
echo "Updating PATH to include the application..."

echo "export PATH=\"$BIN_DIR:\$PATH\"" >> "$HOME/.bashrc"
source "$HOME/.bashrc"

echo "Installation successful! Use 'ssl-handshake-debugger -h' command to run the application."