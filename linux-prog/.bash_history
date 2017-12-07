cd 05-static/
ll
ls
gcc -c test.c -o test.o
ls
gcc -c test.c -o test.o
gcc -c test.c -o test.o
ar rc libtest.a test.o
ar rc libtest.a test.o
gcc hello.c -o hello ./libtest.a 
ls
gcc -c add.c -o add.o
ar rc libadd.a add.o
gcc test.c -o test ./libadd.a
./test 
ll
ls
cd ..
ll
cd 06-dyna/
ll
root@683724f1d8a9:~/06-dyna# ll
root@683724f1d8a9:~/06-dyna# ll
gcc -shared -fPIC -o libadd.so add.c
gcc test.c -o test ./libadd.so
./test 
rm -rf libadd.so 
./test 
cd ..
cd 07-timer/
ll
ls
gcc test.c o test
 gcc test.c -o hello
./hello 
uname -r
apt-get install build-essential linux-headers-`uname -r`
ll
ls /usr/src/
ls /lib64/ld-linux-x86-64.so.2 
ls /lib64/ld-linux-
dpkg -l | grep linux-headers
dpkg -l 
apt-get install linux-headers-generic
uname -r
apt-get install linux-headers-generic
ll
cd ..
cd kernel/
ll
l
ls
make
make
make
  make -C /lib/modules/4.4.0-101-generic/build M=$(PWD) modules
  make -C /lib/modules/4.4.0-101-generic/build M=`PWD` modules
  make -C /lib/modules/4.4.0-101-generic/build M=`PWD` modules
make
ls
vi makefile 
make
make
vi makefile 
make
ls
ls -a
make
make
ls
vi m
ll
vi makefile 
make test
make test
sudo
sudo
make test
instl
ls /usr/src/linux-headers-4.4.0-101
ll -l
ls -l
ls -l /usr/src/
uname -a
uname -a
uname -r
insmod
ls
insmod
 apt-get install kmod
insmod 
 insmod lkm_example.ko
ls
make
make test
ls
ls
ls
insmod lkm_example.ko 
gcc -v
dmesg |tail
aptitude install linux-headers-$(uname -r)
aptitude install linux-headers-$(uname -r)
aptitude install linux-headers-$(uname -r)
apt-get install linux-generic
dmesg |tail
 apt-get install linux-headers-$(uname -r)
ls /lib/modules/
apt-get install build-essential 
 apt-get install linux-headers-$(uname -r)
 apt-get install linux-headers-$(uname -r)
u
uname -r
sudo apt-get install linux-generic-lts-vivid
sudo apt-get install linux-generic-lts-vivid
apt-get install linux-generic-lts-vivid
ls /lib/modules/
ls /usr/src/linux-headers-4.4.0-101
ls /usr/src/linux-headers-4.4.0-101/
sudo lsb_release -a
 lsb_release -a
uname 
uname  -a
ls /usr/src/linux-headers-4.4.0-101/include/
ls /usr/src/linux-headers-4.4.0-101/include/
ls /usr/src/linux-headers-4.4.0-101/include/
uname -r
