Docker image for BusyBox's sh
============

Repository name in Docker Hub: **[williamyeh/busybox-sh](https://registry.hub.docker.com/u/williamyeh/busybox-sh/)**

This Docker image contains only the "SH" part of [BusyBox](http://www.busybox.net/).


## Purpose

To build a minimal Docker image, you may begin with `scratch`, and then add something (usually *static binary files* without any runtime dependency) into it.

Sometimes we'd also like to put *simple wrapper scripts* into the image, too.  Most people will begin with base images such as [`busybox`](https://registry.hub.docker.com/_/busybox/), [`progrium/busybox`](https://registry.hub.docker.com/u/progrium/busybox/), or [`alpine`](https://registry.hub.docker.com/_/alpine/).  However, even the BusyBox itself can be slimed down further, since the only thing needed here is a sh-compatible shell to interpret and execute our `*.sh` files.

For this case, I build a minimal Docker base image that contains only a statically linked program: the "SH" part ("HUSH") of BusyBox.


## Features

- Highly compatible with sh (Bourne Shell).

- Small (about 989 kB).


## Usage

Mostly used as a base image.  For example:


```dockerfile
# First, inherit from this image...
FROM williamyeh/busybox-sh

# Then, put your static binaries and scripts below...
COPY ...
ADD  ...

```


## About the rootfs

To build the rootfs on your own, see [instructions](build-rootfs/README.md) for more details.



## Dependencies

- `scratch`.


## History

- 1.0 - Initial release.


## Author

William Yeh, william.pjyeh@gmail.com


## License

This image contains two components; each has its own license statement, respectively.

1. The first part is [BusyBox](http://www.busybox.net/). This part is licensed under GPLv2.  See the [license statement of BusyBox](http://busybox.net/license.html) for more details.

2. The remain part, written by me, is released to public domain.  See [LICENSE](LICENSE) file for details.
