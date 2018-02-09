/**
 * Created by hg on 2017/12/27.
 */

public class PrintStackInfo{
    public static void main(String[] args) {
        printStack();
    }

    public static void printStack(){
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for(StackTraceElement s : stackTrace){
                System.out.println("类名：" + s.getClassName() + "  ,  java文件名：" + s.getFileName() + ",  当前方法名字：" + s.getMethodName() + ""
                        + " , 当前代码是第几行：" + s.getLineNumber() + ", " );
            }
    }
}