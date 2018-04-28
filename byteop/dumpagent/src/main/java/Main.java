import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.*;
import java.lang.management.ManagementFactory;

/**
 * 
 * 
 * @author zhangwei_david
 * @version $Id: Main.java, v 0.1 2015年6月19日 下午1:10:03 zhangwei_david Exp $
 */
public class Main {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //获取Mean的平台服务
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        // 对即将被注册的MBean 构造一个ObjectName
        ObjectName objectName = new ObjectName("com.cathy.demo.jmx:type=Hi");
        // 创建一个Mbean
        RequiredModelMBean mbean = new RequiredModelMBean();
        HiMbeanImpl hiMbean = new HiMbeanImpl();
        mbean.setManagedResource(hiMbean, "objectReference");

        ModelMBeanAttributeInfo name = new ModelMBeanAttributeInfo("name", "java.lang.String",
            "userName", true, true, false, new DescriptorSupport(new String[] { "name=name",
                    "descriptorType=attribute", "getMethod=getName", "setMethod=setName" }));

        ModelMBeanOperationInfo sayHello = new ModelMBeanOperationInfo("say Hello", hiMbean
            .getClass().getMethod("sayHello"));
        // 创建一个ModelMBeanOperationInfo
        ModelMBeanOperationInfo getName = new ModelMBeanOperationInfo("get userName", hiMbean
            .getClass().getMethod("getName"));

        // 使用ModelMbeanAttributeInfo和ModelMbeanOperationInfo构建一个ModelMBeanInfo对象
        ModelMBeanInfo mbeanInfo = new ModelMBeanInfoSupport("HiMbean", "Test",
            new ModelMBeanAttributeInfo[] { name }, null, new ModelMBeanOperationInfo[] { sayHello,
                    getName }, null);
        // 向ModelMBean 设置ModelMBeanInfo
        mbean.setModelMBeanInfo(mbeanInfo);

        // 将Mbean 注册到MBeanServer
        mbs.registerMBean(mbean, objectName);
        // 一直等待
        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);

    }
}
