#define NEXT break
#define guard(n) asm("#" #n)

main()
{
  static int prog[] = {0,1,0,2,0,3,0,4,0,5};
  int *ip=prog;
  int    count = 10000000;

  for (;;) {
    switch (*ip++) {
    case 0:
      guard(0);
      NEXT;
    case 1:
      guard(1);
      NEXT;
    case 2:
      guard(2);
      NEXT;
    case 3:
      guard(3);
      NEXT;
    case 4:
      guard(4);
      NEXT;
    case 5:
      if (count>0) {
	count--;
	ip=prog;
	NEXT;
	/* the rest is to get gcc to make a realistic switch statement */
      }
      else
	exit(0);
    case 12:
    case 8:
      count=25;
      NEXT;
    case 10:
      count--;
      NEXT;
    }
  }
}
