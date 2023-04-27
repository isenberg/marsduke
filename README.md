# Marsduke
This example creates with the script makeitso.sh the macOS application bundle AppExample.app from the source file AppExample.java and includes an image file marsduke.png for display in the app window.
A default JRE is created internally by jpackage which is also included into the bundle.
The image is re-used to create an application icon.
To build it: `chmod a+rx makeitso.sh` and run `./makeitso.sh` in Terminal.app. The AppExample.app is created in the current directory and can be started from there with a double click or can be moved to the folder /Applications. The additional required steps for distributions to other systems, codesigning and Apple notarization, are not part of this example.

## Install4j Project File
The file appexample.install4j is optional and an example project file for https://ej-technologies.com/products/install4j/overview.html to produce the installation packages listed below. All packages except for the appexample_unix.tar.gz include a JRE. The macOS disk image contains an universal binary AppExample.app inside appexample.dmg for drag & drop into the Applications folder. 
* appexample_macos.dmg
* appexample_linux.deb
* appexample_linux.rpm
* appexample_unix.tar.gz
* appexample_windows.exe
