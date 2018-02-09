
sudo apt-get -y update
sudo apt-get install -y software-properties-common
sudo apt-get install -y dkms
# basic tools
sudo apt-get -y install vim git wget curl

# c
sudo apt-get -y install gdb
sudo apt-get -y install build-essential

# java8
sudo echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo debconf-set-selections && \
	sudo add-apt-repository -y ppa:webupd8team/java && \
	sudo apt-get -y update && \
	sudo apt-get install -y oracle-java8-installer
sudo apt-get -y install maven

# kernel
sudo apt-get -y install build-essential linux-headers-`uname -r`



sudo apt-get -y install openssh-server
