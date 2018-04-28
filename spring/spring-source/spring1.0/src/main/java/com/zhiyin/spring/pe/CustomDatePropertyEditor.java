package com.zhiyin.spring.pe;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
/**
 * http://gundumw100.iteye.com/blog/574440
 * java.util.Date属性编辑器  
 *
 */
@Slf4j
public class CustomDatePropertyEditor extends PropertyEditorSupport {
  
    private String format="yyyy-MM-dd";  

    // breakpoint here, bean初始化设置属性值时，会触发设置值的解析
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        log.info("UtilDatePropertyEditor.saveAsText() -- text=" + text);
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        try {  
            Date d = sdf.parse(text);  
            this.setValue(d);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void setFormat(String format) {  
        this.format = format;  
    }  
  
}  