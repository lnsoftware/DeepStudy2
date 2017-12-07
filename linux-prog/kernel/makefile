obj-m += lkm_example.o

all:
	make -C /lib/modules/4.4.0-101-generic/build M=$(PWD) modules
clean:
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) clean

test:
	dmesg -C
	insmod lkm_example.ko
	rmmod lkm_example.ko
	dmesg
