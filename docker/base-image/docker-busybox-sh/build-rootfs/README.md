Building rootfs for busybox-sh
===

## Build it on Linux

```bash
$ ./build.sh
```

## Build it on Vagrant

```bash
$ vagrant up
```

## Done!

If everything is ok, you'll obtain two files:

 - `busybox-sh`: the minimal BusyBox binary with *only* `sh` applet.
 - `busybox-sh-rootfs.tar.gz`: the rootfs tarball to be put into the Docker image.



使用Vagrant启动一个Ubuntu系统，在系统里执行build.sh脚本。
