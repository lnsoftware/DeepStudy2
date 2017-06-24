//这是因为在类初始化的时候，就规定了，静态语句块中只能访问到定义在静态语句块之前的变量，定义在它之后的，只能赋值，不能访问。
public class StaticFieldSetVal {
                                                            
    static {                                                
        i = 0;  //  给变量复制可以正常编译通过                           
//        System.out.print(i);  // 这句编译器会提示“非法向前引用”         
    }                                                       
    static int i = 1;                                       
                                                            
    static int j = 1;                                       
                                                            
    static{
        j = 2;                                              
    }                                                       
                                                            
    public static void main(String[] args){                 
        System.out.println(i);  //1
        System.out.println(j); //2
    }                                                       
}                                                           