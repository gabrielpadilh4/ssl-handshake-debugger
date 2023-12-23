#!/bin/sh

ARG="${1}"

VERSION="1.5"

# If calling './install.sh local', deploy it in the local working directory instead of $HOME

if [[ -n ${ARG} ]] && [[ "${ARG,,}" == "local" ]]; then
    INSTALL_DIR="$PWD"
    BIN_DIR="$INSTALL_DIR/bin"

else
    INSTALL_DIR="$HOME/ssl-handshake-debugger"
    BIN_DIR="$INSTALL_DIR/bin"
fi

DOWNLOAD_URL="https://github.com/gabrielpadilh4/ssl-handshake-debugger/releases/download/v$VERSION/ssl-handshake-debugger-$VERSION-SNAPSHOT.zip"

echo $DOWNLOAD_URL

ZIP_DOWNLOAD_FILE_NAME="ssl-handshake-debugger-$VERSION-SNAPSHOT.zip"

function display_error() {
    echo "Error: $1"
    exit 1
}

# Verify whether 'wget' and 'unzip' exists

if ! which curl 2> /dev/null; then
    display_error "'curl' has not been found in the system. Please install the package first to proceed."

elif ! which unzip 2> /dev/null; then
    display_error "'unzip' has not been found in the system. Please install the package first to proceed."

elif ! which java 2> /dev/null; then
    display_error "'java' has not been found in the system. Please install the package first to proceed."
fi

# Download the application archive and extract it to the installation directory
echo "Downloading and extracting the application..."
curl -s "$DOWNLOAD_URL" -L -o /tmp/$ZIP_DOWNLOAD_FILE_NAME && unzip -qo /tmp/$ZIP_DOWNLOAD_FILE_NAME -d "$INSTALL_DIR" || display_error "Failed to download and extract ssl-handshake-debugger"
rm -rf /tmp/$ZIP_DOWNLOAD_FILE_NAME

# Add the application's bin directory to the PATH
echo "Updating PATH to include the application..."

# Verify whether the $PATH already exists to avoid duplicate installations

VALIDATE_PATH=$(< $HOME/.bashrc grep "PATH=\"$BIN_DIR:\$PATH\"")

if [[ -n ${VALIDATE_PATH} ]]; then
    echo "PATH=\"$BIN_DIR:\$PATH\" already exists in: $HOME/.bashrc. Not adding to avoid duplicates."

else
    echo "export PATH=\"$BIN_DIR:\$PATH\"" >> "$HOME/.bashrc"
fi

source "$HOME/.bashrc"

echo "Installation successful! Use 'ssl-handshake-debugger -h' command to run the application."
echo "Obs: Some distributions need to restart the shell to work."
