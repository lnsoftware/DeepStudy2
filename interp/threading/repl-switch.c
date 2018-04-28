#include <stdlib.h>

#define NEXT switch(*ip++) {case 0: goto inst0; case 1: goto inst1; \
                            case 2: goto inst2; case 3: goto inst3; \
                            case 4: goto inst4; case 5: goto inst5; \
                           }
#define guard(n) asm("#" #n)

main()
{
  static int prog[] = {0,1,0,2,0,3,0,4,0,5};
  int *ip=prog;
  int    count = 10000000;

  NEXT;

 inst0:
  guard(0);
  NEXT;
 inst1:
  guard(1);
  NEXT;
 inst2:
  guard(2);
  NEXT;
 inst3:
  guard(3);
  NEXT;
 inst4:
  guard(4);
  NEXT;
 inst5:
  if (count>0) {
    count--;
    ip=prog;
    NEXT;
  }
  else
    exit(0);
}
