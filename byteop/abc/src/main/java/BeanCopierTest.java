import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.AbstractClassGenerator;
import net.sf.cglib.core.Converter;
import net.sf.cglib.core.DebuggingClassWriter;

import java.math.BigInteger;

public class BeanCopierTest {

    public static void main(String args[]) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "genclass");
        BeanCopier copier = BeanCopier.create(Source.class, Target.class, true);
        Source from = new Source();
        from.setValue(1);

        Target to = new Target();
        Converter converter = new BigIntConverter();
        copier.copy(from, to, converter); //使用converter类

        System.out.println(to.getValue());



         copier = BeanCopier.create(Source.class, Target.class, false);
         from = new Source();
        from.setValue(1);

         to = new Target();
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
