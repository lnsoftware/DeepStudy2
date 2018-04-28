#define guard(n) asm("#" #n)

void next1();
void next2();
void next3();
void next4();
void next5();
void loop();

main()
{
 start:
  next1(); next2(); next1(); next3(); next1();
  next4(); next1(); next5(); next1();
  loop(); goto start;
}

void next1()
{
  guard(1);
}

void next2()
{
  guard(2);
}

void next3()
{
  guard(3);
}

void next4()
{
  guard(4);
}

void next5()
{
  guard(5);
}

void loop()
{
  static int count=10000000;

  if (count<=0)
    exit(0);
  count--;
}
