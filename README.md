# Marsduke
This example creates with the script makeitso.sh the macOS application bundle AppExample.app from the source file AppExample.java and includes an image file marsduke.png for display in the app window.
A default JRE is created internally by jpackage which is also included into the bundle.
The image is re-used to create an application icon.

## How to build
In Terminal.app on macOS run `chmod a+rx makeitso.sh` and then `./makeitso.sh` to build the application package. The AppExample.app is created in the current directory and can be started from there with a double click or can be moved to the folder /Applications. The additional required steps for distributions to other systems, codesigning and Apple notarization, are not part of this example.

## Install4j Project File
The file appexample.install4j is optional and an example project file for https://ej-technologies.com/products/install4j/overview.html to produce the installation packages listed below. All packages except for the appexample_unix.tar.gz include a JRE. The macOS disk image contains an universal binary AppExample.app inside appexample.dmg for drag & drop into the Applications folder. 
* appexample_macos.dmg
* appexample_linux.deb
* appexample_linux.rpm
* appexample_unix.tar.gz
* appexample_windows.exe

To build all above packages, run the following on either macOS, Linux or Windows: `install4jc appexample.install4j`

## Code Signing
Just for running the created application bundle locally on the same Mac where it was created, no code signing or Apple Notarization is needed.

For distributing the bundle to others refer to the following:
* https://developer.apple.com/library/archive/documentation/Security/Conceptual/CodeSigningGuide
* https://developer.apple.com/documentation/security/notarizing_macos_software_before_distribution

## License
This is an application example, no copyright claimed for any of the included files, license: https://www.apache.org/licenses/LICENSE-2.0
