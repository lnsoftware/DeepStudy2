CFLAGS=-O -fomit-frame-pointer #-fno-reorder-blocks #fails on older gccs
CC=gcc
TIME=time
#TIME=perfex -e 4100c9 -e 0100ca
#TIME=time perfex -e 0x0003b000/0x0c001804@0x8000000D -e 0x0003b000/0x0c001004@0x8000000F -e 0x00035000/0x08002004@0x80000005 -e 0x00035000/0x0a002004@0x80000006

BENCHMARKS=subroutine direct indirect switch call repl-switch

all: $(BENCHMARKS)
	for i in $(BENCHMARKS); do echo -n $$i; $(TIME) ./$$i; done

table: $(BENCHMARKS)
	@echo 'subrout direct indirect switch    call  repl-sw'
	@for i in $(BENCHMARKS); do echo -n '  '`\time -f "%U" ./$$i 2>&1`'  '; done
	@echo

clean:
	rm -f $(BENCHMARKS) *~

dist:
	mkdir threading
	cp -p $(addsuffix .c,$(BENCHMARKS)) Makefile threading
	tar cvfz threading.tar.gz threading
	rm -rf threading

%.s: %.c
	$(CC) $(CFLAGS) -S $<