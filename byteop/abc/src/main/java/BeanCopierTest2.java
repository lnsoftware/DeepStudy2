import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import net.sf.cglib.core.DebuggingClassWriter;

public class BeanCopierTest2 {

    public static void main(String args[]) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "genclass");
        BeanCopier copier = BeanCopier.create(Source.class, Target.class, false);
        Source from = new Source();
        from.setValue(1);

        Target to = new Target();
        copier.copy(from, to, null); //使用converter类

        System.out.println(to.getValue());



    }
}

