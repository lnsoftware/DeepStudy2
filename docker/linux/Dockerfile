FROM ubuntu:14.04

RUN \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get install -y build-essential && \
  apt-get install -y software-properties-common && \
  apt-get install -y byobu curl git htop man unzip vim wget

RUN apt-get install -y nasm make build-essential grub qemu

RUN rm -rf /var/lib/apt/lists/*
## 安装qemu
RUN ln -s /usr/bin/qemu-system-i386 /usr/bin/qemu

# Set environment variables.
ENV HOME /root

# Define working directory.
WORKDIR /root

# Define default command.
CMD ["bash"]
