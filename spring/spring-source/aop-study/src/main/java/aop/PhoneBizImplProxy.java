package aop;

public class PhoneBizImplProxy implements PhoneBiz {
    private PhoneBiz phoneBiz=new PhoneBizImpl();// 目标对象  
    private LogUtil logUtil=new LogUtil();// 日志操作对象  
    public void buyPhone(int num) {  
        phoneBiz.buyPhone(num);// 调用目标对象的手机进货方法  
        logUtil.log("购买", num);//日志操作  
    }  
    public void salePhone(int num) {  
        phoneBiz.salePhone(num);// 调用目标对象的手机销售方法  
        logUtil.log("销售", num);//日志操作  
    }  
    //setter & getter  
}  