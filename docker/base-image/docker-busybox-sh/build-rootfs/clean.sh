#!/bin/bash

rm -rf busybox
rm busybox-sh*

vagrant halt
vagrant destroy --force
