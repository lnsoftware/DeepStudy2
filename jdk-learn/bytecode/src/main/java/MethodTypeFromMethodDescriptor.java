import java.lang.invoke.MethodType;

public class MethodTypeFromMethodDescriptor {

    public void generateMethodTypesFromDescriptor() {
        ClassLoader cl = this.getClass().getClassLoader();
        String descriptor = "(Ljava/lang/String;)Ljava/lang/String;";
        MethodType mt1 = MethodType.fromMethodDescriptorString(descriptor, cl);
        System.out.println(mt1);
    }


    public static void main(String[] args) {
        new MethodTypeFromMethodDescriptor().generateMethodTypesFromDescriptor();
    }
}
