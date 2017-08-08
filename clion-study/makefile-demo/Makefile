#all: makefile-demo

#makefile-demo: main.c
#    gcc -c main.c -o makefile-demo

OBJS = main.o

main: main.o
	gcc -o main main.o
main.o: main.c
	gcc -c main.c
clean:
	rm -f *.o main

print:
	@echo "开始生成最终执行文件，请稍候..."
    #@echo $(OBJS)
    $(info $(OBJS)"hello")