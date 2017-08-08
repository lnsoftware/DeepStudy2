wget http://www.nasm.us/pub/nasm/releasebuilds/2.12.01/nasm-2.12.01.tar.bz2
tar xfj nasm-2.12.01.tar.bz2
cd nasm-2.12.01/
./autogen.sh
./configure --prefix=/usr/local/ 
make 
sudo make install
hash -d nasm