
.PHONY:all
all:epollServer epollClient echoServer

epollServer:epollServer.c
	gcc -o $@ $^ -g

epollClient:epollClient.c
	gcc -o $@ $^ -g

echoServer:epollEchoserver.c
	gcc -o $@ $^ -g

.PHONY:clean
clean:
	rm -rf epollServer
	rm -rf epollClient
	rm -rf core.*
