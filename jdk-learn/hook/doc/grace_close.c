#include <signal.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdbool.h>

static void gracefulClose(int sig)
{
    printf("C Demo Server Close\n");
    exit(0);    //正常关闭
}

int main(int argc,char *argv[])
{
    if(signal(SIGTERM,gracefulClose) == SIG_ERR)
        exit(-1);

    printf("C Demo Server Started\n");

    while(true)
    {
        // 执行工作
        sleep(1);
    }
}