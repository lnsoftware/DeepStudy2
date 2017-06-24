package com.zhiyin.classloader.loaders;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.commons.io.FileUtils;

/** 
 * 加载网络class的ClassLoader 
 */  
public class LocalPathClassLoader extends ClassLoader {

    private String rootPath;

    public LocalPathClassLoader(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;//this.findLoadedClass(name); // 父类已加载
        //if (clazz == null) {  //检查该类是否已被加载过
            byte[] classData = getClassData(name);  //根据类的二进制名称,获得该class文件的字节码数组
            if (classData == null) {
                throw new ClassNotFoundException();
            }
            clazz = defineClass(name, classData, 0, classData.length);  //将class的字节码数组转换成Class类的实例
        //}
        return clazz;
    }

    private byte[] getClassData(String name) {
        try {
            String path = classNameToPath(name);
            File file = new File(path);
            if(file.exists()){
                return FileUtils.readFileToByteArray(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }  
        return null;  
    }
  
    private String classNameToPath(String name) {  
        return rootPath + "/" + name.replace(".", "/") + ".class";
    }  
  
}  