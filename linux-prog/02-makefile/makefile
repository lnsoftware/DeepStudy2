test: add.o sub.o
	gcc -o test add.o sub.o
add.o: add.c test.h
	gcc -c add.c
sub.o: sub.c test.h
	gcc -c sub.c
clean:
	rm -rf test
	rm -rf *.o