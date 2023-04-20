# Marsduke
This example creates with the script makeitso.sh the macOS application bundle AppExample.app from the source file AppExample.java and includes an image file marsduke.png for display in the app window.
A default JRE is created internally by jpackage which is also included into the bundle.
The image is re-used to create an application icon.
To build it: `chmod a+rx makeitso.sh` and run `./makeitso.sh` in Terminal.app. The AppExample.app is created in the current directory and can be started from there with a double click or can be moved to the folder /Applications. The additional required steps for distributions to other systems, codesigning and Apple notarization, are not part of this example.
