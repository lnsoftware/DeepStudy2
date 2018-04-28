


#define A1(name, type)  type name_##type##_type  /* 等价于: int name_int_type; */
#define A2(name, type)  type name##_##type##_type /* 等价于: int a1_int_type;   */


#include <stdio.h>

#define paster( n ) printf( "token" #n " = %d", token##n )

int token9 = 9;

int main(){

   paster(9);

}