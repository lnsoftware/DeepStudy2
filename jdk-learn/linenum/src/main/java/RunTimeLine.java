
public class RunTimeLine{
    public static void main(String[] args) {
        hello();
    }

    public static void hello(){

        printLogV1("hello");
        printLogV1("helloV2");

    }

    public static void printLogV1(String msg){
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        // 注意!这里是下标为2的,而不是为1的
        StackTraceElement tmp = trace[2];
        System.out.println( tmp.getClassName() + "." + tmp.getMethodName()
                + "(" + tmp.getFileName() + ":" + tmp.getLineNumber() + "):"+msg );
    }

    public static void printLogV2(String mess){
        StackTraceElement[] stes = new Throwable().getStackTrace();
        StackTraceElement tmp = stes[stes.length-2];
        System.out.println( tmp.getClassName() + "." + tmp.getMethodName()
                + "(" + tmp.getFileName() + ":" + tmp.getLineNumber() + ")" +":"+mess );
    }
}