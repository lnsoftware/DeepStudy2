#!/bin/bash
#
# Build a minimal static BusyBox with *only* sh ("HUSH").
#
# .config:
#   Basically "make allnoconfig" (disable all built-in utility programs, i.e. "applets"),
#   except that "STATIC" and "HUSH" are enabled.
#   @see http://git.busybox.net/busybox/tree/INSTALL
#

#set -e
#set -x


echo "=== Fetching BusyBox source..."
rm -rf busybox
git clone git://busybox.net/busybox.git
cp .config busybox/
cd busybox


echo
echo "=== Building BusyBox binary..."
LC_ALL=en_US.UTF-8  make
LC_ALL=en_US.UTF-8  make install

ldd busybox
cp busybox ../busybox-sh


echo
echo "=== Building rootfs.tar.gz ..."

cp LICENSE _install/LICENSE-OF-BUSYBOX
cd _install/bin
ln -s busybox sh
ln -s busybox bash
cd ..
tar zcvf ../../busybox-sh-rootfs.tar.gz *


echo
echo "=== Done!"
