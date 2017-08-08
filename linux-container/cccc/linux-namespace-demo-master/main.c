#define _GNU_SOURCE
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <sched.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>  // system()

#include <sys/types.h>
#include <sys/wait.h>

#include <stdio.h>
#include <sched.h>

#include <signal.h>
#include <unistd.h>

#define STACK_SIZE (1024*1024)
static char container_stack[STACK_SIZE];

char* const container_args[] = {
    "/bin/bash",
    NULL
};

int container_main(void* arg){
    printf("Container-inside the container!\n");
    sethostname("container", 10);
    system("mount -t proc proc /proc");
    execv(container_args[0], container_args);
    printf("Sometthind is wrong!\n");
    return 1;
}

int main(){
    printf("Parent-start a container!\n");
    /* not use user namespace, because the 'mount' cmd must be run as root */
    int flags = SIGCHLD | CLONE_NEWUTS | CLONE_NEWIPC | CLONE_NEWPID | CLONE_NEWNS | CLONE_NEWNET;
    int container_pid = clone(container_main, container_stack + STACK_SIZE, flags, NULL);
    waitpid(container_pid, NULL, 0);
    printf("Parent - container stopped!\n");
    return 0;
}
