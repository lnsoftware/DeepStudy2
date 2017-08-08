package aop;

import org.aspectj.lang.JoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAspect {
//前置通知  
public void before() throws Throwable {  
        System.out.println("业务方法开始执行……");  
}

/**  
* 前置通知 在目标方法执行之前执行日志记录  
*/  
    public void before(JoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();// 目标方法所有参数  
        String methodName = jp.getSignature().getName();//获得目标方法名称  
  
        if ("buyPhone".equals(methodName)) {  
            System.out.println(currentTime() +   
"即将执行进货操作,数量为" + args[0]);  
        }  
        if ("salePhone".equals(methodName)) {  
            System.out.println(currentTime() +   
"即将执行销售操作,数量为" + args[0]);  
        }  
    }  
    /** 
     * 输出当前时间的工具方法 
     */  
    public String currentTime() {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(new Date());
    }  
}  
