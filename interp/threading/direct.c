#define NEXT goto **ip++
#define guard(n) asm("#" #n)

main()
{
  /* this has 50% mispredictions (50-60% is typical in large benchmarks) */
  static void  *prog[] = {&&next1,&&next2,&&next1,&&next3,&&next1,&&next4,&&next1,&&next5,&&next1,&&loop};
  void **ip=prog;
  int    count = 10000000;
  NEXT;
 next1:
  guard(1);
  NEXT;
 next2:
  guard(2);
  NEXT;
 next3:
  guard(3);
  NEXT;
 next4:
  guard(4);
  NEXT;
 next5:
  guard(5);
  NEXT;
 loop:
  if (count>0) {
    count--;
    ip=prog;
    NEXT;
  }
  exit(0);
}
