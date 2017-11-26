#include <netinet/in.h>
#include <sys/socket.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/epoll.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <arpa/inet.h>

#define MAXSIZE     1024
#define IPADDRESS   "127.0.0.1"
#define SERV_PORT   8787
#define FDSIZE        1024
#define EPOLLEVENTS 20

static void handle_connection(int sockfd);
static void
handle_events(int epollfd,struct epoll_event *events,int num,int sockfd,char *buf);
static void do_read(int epollfd,int fd,int sockfd,char *buf);
static void do_read(int epollfd,int fd,int sockfd,char *buf);
static void do_write(int epollfd,int fd,int sockfd,char *buf);
static void add_event(int epollfd,int fd,int state);
static void delete_event(int epollfd,int fd,int state);
static void modify_event(int epollfd,int fd,int state);

int main(int argc,char *argv[])
{
	int                 sockfd;
	struct sockaddr_in  servaddr;
	sockfd = socket(AF_INET,SOCK_STREAM,0);
	bzero(&servaddr,sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(SERV_PORT);
	inet_pton(AF_INET,IPADDRESS,&servaddr.sin_addr);
	connect(sockfd,(struct sockaddr*)&servaddr,sizeof(servaddr));

    printf("STDIN_FILENO fd: %d\n",STDIN_FILENO);
    printf("sockfd fd: %d",sockfd);

	//处理连接
	handle_connection(sockfd);
	close(sockfd);
	return 0;
}


static void handle_connection(int sockfd)
{
	int epollfd;
	struct epoll_event events[EPOLLEVENTS];
	char buf[MAXSIZE];
	int ret;
	epollfd = epoll_create(FDSIZE);
	// 注册描述符，监听控制台输入
	add_event(epollfd,STDIN_FILENO,EPOLLIN);
	for ( ; ; )
	{
		ret = epoll_wait(epollfd,events,EPOLLEVENTS,-1);
		handle_events(epollfd,events,ret,sockfd,buf);
	}
	close(epollfd);
}

static void
handle_events(int epollfd,struct epoll_event *events,int num,int sockfd,char *buf)
{
	int fd;
	int i;
	for (i = 0;i < num;i++)
	{
		fd = events[i].data.fd;
		if (events[i].events & EPOLLIN)
			do_read(epollfd,fd,sockfd,buf);
		else if (events[i].events & EPOLLOUT)
			do_write(epollfd,fd,sockfd,buf);
	}
}

static void do_read(int epollfd,int fd,int sockfd,char *buf)
{
	int nread;
	// 有可能是系统输入，有可能是socket连接
	nread = read(fd,buf,MAXSIZE);
	printf("client fd %d,read buf: %s",fd,buf);
	if (nread == -1)
	{
		perror("read error:");
		close(fd);
	}
	else if (nread == 0)
	{
		fprintf(stderr,"server close.\n");
		close(fd);
	}
	else
	{
	    // 用户输入，转到socket中
		if (fd == STDIN_FILENO){
			add_event(epollfd,sockfd,EPOLLOUT);
		}
		else
		{
		// 服务器返回后，删除socket监听
			delete_event(epollfd,sockfd,EPOLLIN);
			// 注册控制台输出监听
			add_event(epollfd,STDOUT_FILENO,EPOLLOUT);
		}
	}
}

static void do_write(int epollfd,int fd,int sockfd,char *buf){
	int nwrite;
	printf("client fd %d",fd);
	// 有可能是写入控制台输出，也有可能是写入socket
	nwrite = write(fd,buf,strlen(buf));
	if (nwrite == -1){
		perror("write error:");
		close(fd);
	}
	else
	{
		if (fd == STDOUT_FILENO)
			delete_event(epollfd,fd,EPOLLOUT);
		else
			modify_event(epollfd,fd,EPOLLIN);
	}
	// 清空用户输入
	memset(buf,0,MAXSIZE);
}

static void add_event(int epollfd,int fd,int state)
{
	struct epoll_event ev;
	ev.events = state;
	ev.data.fd = fd;
	epoll_ctl(epollfd,EPOLL_CTL_ADD,fd,&ev);
}

static void delete_event(int epollfd,int fd,int state)
{
	struct epoll_event ev;
	ev.events = state;
	ev.data.fd = fd;
	epoll_ctl(epollfd,EPOLL_CTL_DEL,fd,&ev);
}

static void modify_event(int epollfd,int fd,int state)
{
	struct epoll_event ev;
	ev.events = state;
	ev.data.fd = fd;
	epoll_ctl(epollfd,EPOLL_CTL_MOD,fd,&ev);
}
