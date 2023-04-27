#!/bin/bash

if ! [ -x /usr/libexec/java_home ]; then
  echo "Canceled. This script is intendend to be run on the macOS Terminal."
  exit 1
fi

JAVA_HOME=$(/usr/libexec/java_home -v 17)
BUILDDIR=tmpbuild
ICONDIR=app.iconset

# re-using the image file also for the app icon
mkdir -p $ICONDIR
rprev=""
for r in 1024 512 256 128 64 32 16; do
  sips -z $r $r marsduke.png --out $ICONDIR/icon_${r}x${r}.png
  if [ "$rprev" ]; then
    cp $ICONDIR/icon_${rprev}x${rprev}.png $ICONDIR/icon_${r}x${r}@2x.png
  fi
  rprev=$r
done
rm $ICONDIR/icon_1024x1024.png
iconutil -c icns $ICONDIR 

mkdir -p $BUILDDIR
cp marsduke.png $BUILDDIR/

$JAVA_HOME/bin/javac AppExample.java
$JAVA_HOME/bin/jar -cvfe $BUILDDIR/app.jar AppExample AppExample*.class

# create .app bundle
# or use --type dmg instead of --type app-image
# to create .app bundle inside a .dmg image
$JAVA_HOME/bin/jpackage --type app-image \
 --dest . --input $BUILDDIR --icon app.icns --name AppExample \
 --main-class AppExample --main-jar app.jar --verbose

rm -rf $BUILDDIR $ICONDIR
rm app.icns AppExample*.class
