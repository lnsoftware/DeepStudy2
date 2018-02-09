
## Packer打包Ubuntu系统

官网
https://www.packer.io
入门demo
https://github.com/geerlingguy/packer-ubuntu-1604
晋级例子
https://bitbucket.org/janihur/ubuntu-1604-lxde-desktop
https://github.com/boxcutter/ubuntu


packer build -only=virtualbox-iso -var-file=ubuntu1604-desktop.json ubuntu.json
vagrant box add --name ubuntu1604-desktop box/virtualbox/ubuntu1604-desktop-0.1.0.box


### preseed自动化部署
http://huxos.me/debian/2014/04/28/debian-autoinstall.html


