package aop;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    public void log(String type,int num) {  
        System.out.println("日志:"+currentTime()+type+"手机"+num+"部...");  
    }  
    public String currentTime() {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(new Date());
    }  
}  