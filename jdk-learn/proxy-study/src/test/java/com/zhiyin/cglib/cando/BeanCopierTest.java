package com.zhiyin.cglib.cando;

import java.math.BigInteger;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.junit.Test;

public class BeanCopierTest extends BaseTest {

    @Test
    public void test() {
        BeanCopier copier = BeanCopier.create(SourceSampleBean.class, TargetSampleBean.class, true);
        SourceSampleBean from = new SourceSampleBean();
        from.setValue("hello");

        TargetSampleBean to = new TargetSampleBean();
        Converter converter = new BigIntConverter();
        copier.copy(from, to, converter); //使用converter类

        System.out.println(to.getValue());
    }

    @Test
    public void test2() {
        BeanCopier copier = BeanCopier.create(SourceSampleBean.class, TargetSampleBean.class, false);
        SourceSampleBean from = new SourceSampleBean();
        from.setValue("hello");

        TargetSampleBean to = new TargetSampleBean();
        Converter converter = new BigIntConverter();
        copier.copy(from, to, null); //使用converter类

        System.out.println(to.getValue());
    }
}

class BigIntConverter implements net.sf.cglib.core.Converter {

    @Override
    public Object convert(Object value, Class target, Object context) {
        System.out.println(value.getClass() + " " + value); // from类中的value对象  
        System.out.println(target); // to类中的定义的参数对象  
        System.out.println(context.getClass() + " " + context); // String对象,具体的方法名  
        if (target.isAssignableFrom(BigInteger.class)) {
            return new BigInteger(value.toString());
        } else {
            return value;
        }
    }

}

class SourceSampleBean {
    private int count;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class TargetSampleBean {
    private int count;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
