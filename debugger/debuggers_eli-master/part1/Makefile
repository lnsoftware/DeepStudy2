CC = gcc
CFLAGS = -Wall -Wextra -Werror -Wshadow -pedantic
SOURCE = hello

all:
	#$(CC) $(SOURCE).c -o $(SOURCE)
	nasm -f elf64 -o demo2.o demo2.asm
	ld -o demo2 demo2.o
	gcc -Wall -Wextra tracer.c -o tracer.out

	./tracer.out demo2

clean:
	rm -f *.out
	rm -f demo2.o demo2
