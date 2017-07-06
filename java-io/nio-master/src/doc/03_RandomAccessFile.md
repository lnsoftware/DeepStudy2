### Exploring RandomAccessFile
    RandomAccessFile 声明了下面两个构造方法
    
方法|描述
----|----
RandomAccessFile(File file,String mode)  | 当 file 不存在时创建并打开，存在时直接打开。创建或打开的方式根据后面model 指定来指定置。
RandomAccessFile(String path,String mode) | 当指定path对应文件不存在时则创建并打开，存在时直接打开；而打开的方式根据后面 mode 指定。

    mode 包含的参数
参数名|描述
----|----
"r" | 以只读的方式打开已存在的文件。任何尝试写的操作都会抛出 java.io.IOException 。当文件不存在时，抛出 java.io.FileNotFoundException。
"rw" | 当文件不存在时便创建，并以读写的方式进行操作
"rwd" | 以读写的方式打开文件（文件不存在时，先创建）。并且，对于文件的每次修改必须同步的更新到存储设备
"rws" | 以读写的方式打开文件（文件不存在时，先创建）。并且，对于文件内容或元数据的更新必须同步地更新到存储设备


   **当使用了上面指定参数以外的参数时，会抛出 java.lang.IllegalArgumentException**
   
   一个随机访问文件带有文件指针，一个游标标识着当下用于写或读的字节位置。当已存在的文件打开时，文件指针被设置到起始位置0。当创建时同样将文件指针设置到0的位置。
   
   写或读操作从文件指针开始,根据读或写的字节数移动指针。

#### RadomAccessFile 方法

方法|描述
----|----
void close() | 关闭并释放指定的文件资源。后续的读或写都为抛 出 IOException 。同样不能再以 RandomAccessFile 的方式重新打开。当发生 I/O 错误时抛出 IOException 。
FileDescriptor getFD() | 返回文件描述对象。当发生 I/O 错误时抛出 IOException 。
long getFilePointer() | 返回当前指针的位置（起始位置为0）。当发生 I/O 错误时抛出 IOException 。
long length() | 返回该文件以字节为单位的大小。当发生 I/O 错误时抛出 IOException 。
int read() | 从文件的下一字节开始返回一个 int(0到255)，当到达文件末尾时返回 -1。当发生 I/O 错误时抛出 IOException 。
int read(byte[] b) | 从文件当前指针位置开始读取大小为 b.length 的字节数据装载到字节数组 b 中。该方法阻塞直到至少有一个字节是可用的。该方法返回装载到字节数组b的实际数据大小；当到达文件末尾时返回-1。
char readChar() | 从文件里读取并返回字符。该方法从文件指针当前所在位置开始读取2个字节并返回。如果按顺序读取了2个字节 b1 和 b2 且 0<=b1 b2<=255 。该方法会阻塞直到读取了两个字节或者到达文件末尾或者抛出异常。当发生 I/O 错误时抛出 IOException 。
int readInt() | 从文件读取并返回一个 32字节大小的整型数字。该方法从文件指针当前位置开始读取4个字节返回。如果按顺序读取4个，分别是 b1 b2 b3 b4 且 0<=b1,b2,b3,b3<=255，相当于(b1<<24)|(b2<<16)|(b3<<8)|b4。该方法会阻塞直到读取了两个字节或者到达文件末尾或者抛出异常。当发生 I/O 错误时抛出 IOException 。
void seek(long pos)|将文件指针移到到 pos 所指定的位置（pos 会按字节计算从文件头部开始）。如果 pos 超过了文件，文件长度大小不会改变。只有在文件末尾继续进行了写操作才会导致文件长度大小改变。当 pos 为负数或发生 I/O 错误时抛出 IOExcepton 。
void setLength(long newLength) | 设置文件的长度大小。如果当前文件 length() 大于 newLength ；那么该文件会被截取成只有 newLength 大。当文件 getFilePointer() 返回的位移位置大于 newLength 时，位移位置将会被设置为 newLength 并返回。如果当前文件 length() 小于 newLength ，那么该文件为被扩展。这种情况下，文件被扩展的部分并未定义。发生 I/O 错误时抛出 IOExcepton 。
int skipBytes(int n) | 尝试跳过 n 个字节。当还未完成跳过 n 个字节就到达文件末尾时，实际跳过的字节数要小于 n 也有可能为 0。而且在这种情况下并不会抛出 EOFException 。如果 n 是负数，不会跳过。返回的为实际中跳过的字节数。发生 I/O 错误时抛出 IOExcepton 。
void write(byte[] b) | 从文件指针当前文件开始从字节数组b里写出 b.length() 个字节。发生 I/O 错误时抛出 IOExcepton 。
void write(int b) | 在文件指针当前位置将 b 的低8位作为 32字节的整数输出。发生 I/O 错误时抛出 IOExcepton 。
void writeChars(String s) | 从文件指针当前位置开始写入 s 所指定的有序字符序列。发生 I/O 错误时抛出 IOExcepton 。
void writeInt(int i) | 从文件指针当前位置开始写入一个32位字节的整数。这4个字节里高字节最开始写入。发生 I/O 错误时抛出 IOExcepton 。


   当文件已经被打开时，低层的操作系统会创建一个操作系统所依赖的数据结构来代表该文件。对于该数据结构的存储使用了 java.io.FileDescriptor 类，调用 getFD() 方法可获得。
   
   
   FileDescriptor 是一个比较小的类。内部声明了 FileDescriptor 类型的三个常量 in 、out、err。借助这三个常量可以使 System.in、System.out、System.err 访问标准的输入、标准出出以及标准错误流。
   
   FileDescriptor 同样声明了下面两对方法：
   方法名|描述
   ----|----
   void sync() | 告诉低层操作系统将当前打开文件缓存中的内容立即写入到磁盘文件上。sync() 返回写进相关设备上文件被修改的所有内容以及属性。当缓存中的内容不能被写入或低层操作系统不能保证缓存里所有内空都已写入到媒体设备上时报：java.io.SyncFailedException 。
   boolean valid() | 标识文件描述是否有效。当文件描述符指向了当前打开文件或其他活动I/O连接时返回 true ；否则返回 false 。
   
   写入开个文件的数据是存储在低层系统的输出缓存中。当缓存被填满时，操作系统为将所有所数据写入到磁盘并清空缓存。通过缓存提高了性能，因为磁盘访问远低于访问计算机的内存。
   
   然而，当你以“rwd”或“rws”向 RandomAccessFile 写入数据时，每个操作数据都将直接写到磁盘。因此，当以“rw”模式打开RandomAccessFile时，写操作要慢些。
   
   将借助输出缓存写数据与直接将数据写到缓存结合起来。
   
### 使用 RandomAccessFile API
    数据库里的文件内容典型的组织为有序列的固定长度大小的记录。每条记录又进一步的组织为一个或多个固定大小的字段。如下图：

![image](E:\learn\java\io\4.png)

    下面的示例展示了如何借助于实现典型的数据库文件。
    
    //自定义数据库
    
```
package classic;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by yifan on 3/29/17.
 */
public class PartsDB {
    public final static int PNUMLEN = 20;
    public final static int DESCLEN = 30;
    public final static int QUANLEN = 4;
    public final static int COSTLEN = 4;


    public final static int RECLEN = 2 * PNUMLEN + 2 * DESCLEN + 2 * QUANLEN + 2 * COSTLEN;

    public RandomAccessFile raf;

    public PartsDB(String path) throws IOException{
        raf = new RandomAccessFile(path,"rw");
    }

    public void append(String partnum,String partdesc,int qty,int ucost) throws IOException{
        raf.seek(raf.length());
        write(partnum,partdesc,qty,ucost);
    }

    public void close(){
        try{
            raf.close();
        }catch (IOException ioe){
            System.err.println(ioe);
        }
    }


    public int numResc() throws IOException{
        return (int)raf.length() / RECLEN;
    }

    public Part select(int recno) throws IOException{
        if(recno < 0 || recno >= numResc())
            throw new IllegalArgumentException(recno + " out of range");
        raf.seek(recno * RECLEN);
        return read();
    }

    public void update(int recno,
                       String partnum,
                       String partdesc,
                       int qty,
                       int ucost) throws IOException{
        if(recno < 0 || recno >= numResc())
            throw new IllegalArgumentException(recno + " out of range");

        raf.seek(recno * RECLEN);
        write(partnum,partdesc,qty,ucost);
    }

    private Part read() throws IOException{
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < PNUMLEN; i++)
            sb.append(raf.readChar());

        String partnum = sb.toString().trim();
        sb.setLength(0);

        for(int i = 0; i < DESCLEN; i++)
            sb.append(raf.readChar());

        String partdesc = sb.toString().trim();
        int qty = raf.readInt();
        int ucost = raf.readInt();

        return new Part(partnum,partdesc,qty,ucost);
    }

    private void write(String partnum,
                       String partdesc,
                       int qty,
                       int ucost)
            throws IOException{
        StringBuffer sb = new StringBuffer(partnum);
        if(sb.length() > PNUMLEN)
            sb.setLength(PNUMLEN);
        else if(sb.length() < PNUMLEN){
                int len = PNUMLEN - sb.length();
                for (int i = 0; i < len; i++)
                    sb.append(" ");
        }

        raf.writeChars(sb.toString());

        sb = new StringBuffer(partdesc);
        if(sb.length() > DESCLEN)
            sb.setLength(DESCLEN);
        else if(sb.length() < DESCLEN){
            int len = DESCLEN - sb.length();
            for (int i = 0; i < len; i++)
                sb.append(" ");
        }

        raf.writeChars(sb.toString());
        raf.writeInt(qty);
        raf.writeInt(ucost);
    }


    public static class Part{

        private String partnum;
        private String desc;
        private int qty;
        private int ucost;

        public Part(String partnum,String desc,int qty,int ucost){
            this.partnum = partnum;
            this.desc = desc;
            this.qty = qty;
            this.ucost = ucost;
        }

        public String getPartnum() {
            return partnum;
        }


        public String getDesc() {
            return desc;
        }

        public int getQty() {
            return qty;
        }

        public int getUnitCost() {
            return ucost;
        }

    }


}

```
    自定义数据库的使用
```
package classic;

import java.io.IOException;

/**
 * Created by yifan on 3/29/17.
 */
public class UsePartsDB {

    public static void main(String[] args){
        PartsDB pdb = null;
        try{
            pdb = new PartsDB("/home/yifan/work/tmp/parts.db");
            if(pdb.numResc() == 0){
                pdb.append("1-9009-3323-4x", "Wiper Blade Micro Edge", 30,2468);
                pdb.append("1-3233-44923-7j", "Parking Brake Cable", 5, 1439);
                pdb.append("2-3399-6693-2m", "Halogen Bulb H4 55/60W", 22, 813);
                pdb.append("2-599-2029-6k", "Turbo Oil Line O-Ring ", 26, 155);
                pdb.append("3-1299-3299-9u", "Air Pump Electric", 9, 20200);
            }

            dumpRecords(pdb);
            pdb.update(1, "1-3233-44923-7j", "Parking Brake Cable", 5, 1995);
            dumpRecords(pdb);


        }catch (IOException ioe){
            System.err.println(ioe);
        }
    }


    static void dumpRecords(PartsDB pdb) throws IOException{

        for (int i = 0; i < pdb.numResc(); i++){
            PartsDB.Part part = pdb.select(i);
            System.out.print(format(part.getPartnum(), PartsDB.PNUMLEN, true));
            System.out.print(" | ");
            System.out.print(format(part.getDesc(), PartsDB.DESCLEN, true));
            System.out.print(" | ");
            System.out.print(format("" + part.getQty(), 10, false));
            System.out.print(" | ");
            String s = part.getUnitCost() / 100 + "." + part.getUnitCost() %  100;

            if (s.charAt(s.length() - 2) == '.') s += "0";
            System.out.println(format(s, 10, false));
        }

        System.out.println("Number of records = " + pdb.numResc());
        System.out.println();
    }

    static String format(String value, int maxWidth, boolean leftAlign)
    {
        StringBuffer sb = new StringBuffer();
        int len = value.length();
        if (len > maxWidth)
        {
            len = maxWidth;
            value = value.substring(0, len);
        }
        if (leftAlign)
        {
            sb.append(value);
            for (int i = 0; i < maxWidth - len; i++)
                sb.append(" ");
        }
        else
        {
            for (int i = 0; i < maxWidth - len; i++)
                sb.append(" ");
            sb.append(value);
        }
        return sb.toString();
    }
}

```
   
   
