package aop;

//手机业务接口实现
public class PhoneBizImpl implements PhoneBiz {  
    public void buyPhone(int num) {  
        System.out.println("手机进货，进货数量 为" + num + "部");  
    }  
    public void salePhone(int num) {  
        System.out.println("销售手机，销售数量为" + num + "部");  
    }  
}  