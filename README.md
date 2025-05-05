# Marsduke
This example creates the macOS application bundle AppExample.app or Linux executable from the source file AppExample.java and includes an image file marsduke.png for display in the app window. A default JRE is created internally by jpackage which is also included into the bundle. That means for later running the example you don't need a separate Java installation as all is included in one package. The included image is used to create an application icon. On Linux it also works on Raspberry Pi as long as it is running a 64bit arm64 Linux.

## To build the plain Java example
In Terminal.app on macOS or in a terminal on Linux arm64 or x86_64 run `chmod a+rx makeitso.sh` and then `./makeitso.sh` to build the application package. The AppExample.app bundle or Linux the appexample directory is created in the current directory and can be started from there on macOS with a double click or can be moved to the folder /Applications or on Linux can be started with ./appexample/bin/appexample from the terminal.

## To build the JavaFX example
You need at least Java 17 FX JDK installed to build it. Java FX-JDK 17 is available for example from https://javaalmanac.io/jdk/download/#version=17 and https://foojay.io/download . Make sure to download a package with ***fx*** and ***jdk*** in the name.

In Terminal.app on macOS or in a terminal on Linux run `chmod a+rx fxmakeitso.sh` and then `./fxmakeitso.sh` to build the FX application package. On macOS FXMarsDuke.app bundle is created in the current directory and can be started from there with a double click or can be moved to the folder /Applications. On Linux the fxmarskduke directory is created in the current directory and can be started from there with ./fxmarsduke/bin/fxmarsduke from the terminal. On Raspberry Pi prepend the command line with ```_JAVA_OPTIONS="-Dprism.forceGPU=true"``` to enforce using the hardware-accelerated rendering.

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

Duke: https://web.archive.org/web/19991215163004/http://java.sun.com/features/1999/05/duke.html

## License
This is an application example, no copyright is claimed.

License: https://www.apache.org/licenses/LICENSE-2.0
