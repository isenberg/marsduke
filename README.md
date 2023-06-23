# Marsduke
This example creates the macOS application bundle AppExample.app from the source file AppExample.java and includes an image file marsduke.png for display in the app window. A default JRE is created internally by jpackage which is also included into the bundle. That means for later running the example you don't need a separate Java installation as all is included in one package. The included image is used to create an application icon.

## How to build the plain Java example
In Terminal.app on macOS run `chmod a+rx makeitso.sh` and then `./makeitso.sh` to build the application package. The AppExample.app bundle is created in the current directory and can be started from there with a double click or can be moved to the folder /Applications.

## How to build the JavaFX example
You need at least Java FX 17 JDK installed to build it.

In Terminal.app on macOS run `chmod a+rx fxmakeitso.sh` and then `./fxmakeitso.sh` to build the FX application package. The FXMarsDuke.app bundle is created in the current directory and can be started from there with a double click or can be moved to the folder /Applications.

## Install4j Project File
The file appexample.install4j is optional and an example project file for https://ej-technologies.com/products/install4j/overview.html to produce the installation packages listed below. All packages except for the appexample_unix.tar.gz include a JRE. The macOS disk image contains a universal binary AppExample.app inside appexample.dmg for drag & drop into the Applications folder. 
* appexample_macos.dmg
* appexample_linux.deb
* appexample_linux.rpm
* appexample_unix.tar.gz
* appexample_windows.exe

To build all above packages, run the following on either macOS, Linux or Windows: `install4jc appexample.install4j`

## Code Signing
Just for running the created application bundle locally on the same Mac where it was created, no code signing or Apple Notarization is needed.

For distributing the bundle to others, refer to the following:
* https://developer.apple.com/library/archive/documentation/Security/Conceptual/CodeSigningGuide
* https://developer.apple.com/documentation/security/notarizing_macos_software_before_distribution

## Image Source
Mars Exploration Rover Spirit, Pancam, sol 120, May 5, 2004, ID 2P137011382EFF4000P2563L5M1

Processed color image: https://areo.info/mer/spirit/120

Raw data: https://mars.nasa.gov/mer/gallery/all/spirit_p120.html

## License
This is an application example, no copyright is claimed.

License: https://www.apache.org/licenses/LICENSE-2.0
