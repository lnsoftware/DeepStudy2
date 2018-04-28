public class JniAdder{
        static{
                System.loadLibrary("bridge");
        }

        public native int nativeAdd(int x,int y);

        public static void main(String[] args){
                JniAdder obj = new JniAdder();
                System.out.printf("%d\n",obj.nativeAdd(2012,3));
        }
}