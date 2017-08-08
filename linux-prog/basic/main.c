#include <stdio.h>

int sum(int c, int d)
{
    int e = c + d;
    return e;
}

int func(int a, int b)
{
    return sum(a, b);
}

int main(void)
{
    func(2,3);
    return 0;
}
