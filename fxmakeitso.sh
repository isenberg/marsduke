#!/bin/bash

# application packaging example, no copyright claimed, license: https://www.apache.org/licenses/LICENSE-2.0
# this example packages a JavaFX application with an JRE-FX as native application bundle on macOS or Linux

BUILDDIR="tmpbuild"
ICONDIR="app.iconset"

# JDK 17 is minimum for jpackage, older versions or JRE won't work
# they keyworkd "newest" doesn't really exist, any non-matching version number will return the newest installed
if [ $(uname -s) == "Darwin" ]; then
  MACOS=1
  APPNAME=FXMarsDuke
  echo "Creating $APPNAME.app macOS application bundle..."
else
  unset MACOS
  APPNAME=fxmarsduke
  echo "Creating Linux application bundle $APPNAME..."
fi
if jpackage --version 2>/dev/null | grep -E '^(17|18|19|2)' >/dev/null; then
  echo JDK version found: $(jpackage --version)
elif [ "$MACOS" ] && /usr/libexec/java_home -v newest --exec jpackage --version 2>/dev/null | grep -E '^(17|18|19|2)' >/dev/null; then
  FOUNDJDK=$(/usr/libexec/java_home -v newest)
  PATH="$FOUNDJDK/bin:$PATH"
  echo JDK found via /usr/libexec/java_home: $FOUNDJDK
else
  echo "Canceled. No JDK 17 or newer found."
  exit 1
fi

if ! java --list-modules | grep javafx >/dev/null; then
  echo "Canceled. No JavaFX found in JDK." 
  exit 1
fi

if [ $MACOS ]; then
# re-using the image file also for the app icon
mkdir -p "$ICONDIR"
rprev=""
for r in 1024 512 256 128 64 32 16; do
  sips -z $r $r marsduke.png --out "$ICONDIR/icon_${r}x${r}.png"
  if [ "$rprev" ]; then
    cp "$ICONDIR/icon_${rprev}x${rprev}.png" "$ICONDIR/icon_${r}x${r}@2x.png"
  fi
  rprev=$r
done
rm "$ICONDIR/icon_1024x1024.png"
iconutil -c icns "$ICONDIR" 
fi

mkdir -p "$BUILDDIR"
cp marsduke.png "$BUILDDIR/"

javac FXMarsDuke.java
jar -cvfe "$BUILDDIR/app.jar" $APPNAME FXMarsDuke*.class

# create application bundle with embedded JRE
if [ $MACOS ]; then
  # alternatively, use --type dmg instead of --type app-image to create .app bundle inside a .dmg image
  jpackage --type app-image \
    --dest . --input "$BUILDDIR" --icon app.icns --name $APPNAME \
    --main-class FXMarsDuke --main-jar app.jar --verbose
  rm app.icns
  rm -rf "$ICONDIR"
  echo "$APPNAME bundle created."
  echo "To start it in Terminal.app: $APPNAME.app/Contents/MacOS/$APPNAME"
  echo "Or start it from the desktop with a double click on $APPNAME."
else
  # Linux
  # alternatively, use --type rpm or deb instead of --type app-image
  jpackage --type app-image \
    --dest . --input "$BUILDDIR" --name $APPNAME \
    --main-class FXMarsDuke --main-jar app.jar --verbose
  echo "$APPNAME bundle created. To start it: $APPNAME/bin/$APPNAME"
fi

rm -rf "$BUILDDIR"
rm FXMarsDuke*.class

