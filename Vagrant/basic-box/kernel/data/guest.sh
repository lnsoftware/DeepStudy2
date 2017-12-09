
VBOX_VERSION=5.1.24
wget http://download.virtualbox.org/virtualbox/${VBOX_VERSION}/VBoxGuestAdditions_${VBOX_VERSION}.iso
sudo mount -o loop VBoxGuestAdditions_${VBOX_VERSION}.iso /mnt
sudo /mnt/VBoxLinuxAdditions.run
sudo umount /mnt
