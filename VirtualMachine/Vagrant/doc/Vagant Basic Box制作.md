

### 

1) 进入box

2) 安装软件

3) 

vagrant package --output mynew.box

vagrant package --output ubuntu16.box


4)
vagrant box add huiwq1990/ubuntu16 ubuntu16.box



### 用户配置

ubuntu16默认用户为ubuntu，添加vagrant用户
```
 sudo mkdir -pm 700 /home/vagrant/.ssh
 sudo wget --no-check-certificate 'https://raw.github.com/mitchellh/vagrant/master/keys/vagrant.pub' -O /home/vagrant/.ssh/authorized_keys
 sudo chmod 0600 /home/vagrant/.ssh/authorized_keys
 sudo chown -R vagrant /home/vagrant/.ssh
```



https://www.vagrantup.com/docs/virtualbox/boxes.html

https://scotch.io/tutorials/how-to-create-a-vagrant-base-box-from-an-existing-one

https://www.sitepoint.com/create-share-vagrant-base-box/

https://www.zzxworld.com/blogs/create-vagrant-box-base-on-centos.html