# Streams 流
  Java 的传统 I/O 里提供了流来处理 I/O 操作。流即是任意长度的有序的字节数组。
  字节从源目标应用以输入流的形式读取并以输出流的形式输出到目标应用。
  
  Java 提供的 java.io 包里包含了各种不同输出流：byte arrs,files,thread pipes。同样提供了各种不同的输入流：byte arrays,files,thread pipes。
  
  
## Stream Classes Overview 流的总览  

### 输出流   
- [ ] OutputStream(abstract)
    - [ ] ByteArrayOutputStream
    - [ ] FileOutputStream
    - [ ] FilterOutputStream
       - [ ] BufferedOutputStream
       - [ ] DataOutputStream
       - [ ] PrintStream
    - [ ] ObjectOutputStream
    - [ ] PipedOutputStream  
    

### 输入流
- [ ] InputStream(abstract)
    - [ ] ByteArrayInputStream
    - [ ] FileInputStream
    - [ ] FilterInputStream
        - [ ] BufferedInputStream
        - [ ] DataInputStream
        - [ ] LineNumberInputStream
        - [ ] PushbackInputStream
    - [ ] ObjectInputStream
    - [ ] PipedInputStream
    - [ ] SequenceInputStream
    - [ ] StringBufferInputStream

    
    LineNumberInputStream 和 StringBufferInputStream 已经不推荐使用，因为它们不支持不同的字符编码。用来替换的是 java.io.LineNumberReader 和 java.io.StringReader 。
    
    注意：PrintStream 是另一个不推荐再使用的类，也是因为它不支持不同的字符编码。用来替换的是 java.io.PrintWriter 类。
    
    
    其他 java 包提供了额外的输入输出流。如：
- [ ] CheckedOutputStream
- [ ] CheckedInputStream
- [ ] DeflaterOutputStream
- [ ] GZIPOutputStream
- [ ] GZIPInputStream
- [ ] InflaterInputStream
- [ ] ZipOutputStream
- [ ] ZipInputStream


   java.uti.jar 包提供了一对对于 JAR 包的处理：
- [ ] JarOutputStream
- [ ] JarInputStream


### OutputStream and InputStream 输入输出流

    输出流提供方法
    
方法|描述
----|----
void close() | 关闭流并释放操作系统里关于流的资源。当发生 I/O 错误时，抛出 java.io.IOException 。
void flush() | 将输出缓冲里的数据刷到目的地。如果低层系统对于目标是抽象的，低层系统只保证将输出到低层系统的数据输出，但并不保证一定写到物理设置如磁盘上。当发生 I/O 错误时，抛出 java.io.IOException 。
void write(byte[] b) | 向输出流里从字节数组b里输出长度为 b.length 的字节。当 b 为空时报 java.lang.NullPointerException 。当发生 I/O 错误时报 java.io.IOException 。
void write(byte[] b,int off,int len) | 向输出流里写出从字节数组下标为 off 开始，长度为 len 的字节数据。当字节数组 b 为空时报 java.lang.NullPointerException 。当 off 是负数，len 为负数时，或者 off + len 大于 b.length 时会报 java.lang.IndexOutOfBoundsException 。当发生 I/O 错误时报 java.io.IOException 。
void write(int b) | 向输出流里输出字节 b 。只有低8位才会输出；高24位会被忽略掉。当发生 I/O 错误时报 java.io.IOException 。
    
    
    flush() 方法对于那些长时间运行频繁保存的应用很有用，例如文本编辑器，每隔几分钟都会将改变的内容保存到临时文件。
    
    **记录：flush() 只是将字节刷入到操作系统;但并不保证操作系统一定会将内容写入到磁盘。**
    
    
#### 输入流方法   

方法|描述
----|----
int available() | 没有线程调用阻塞时通过调用 read() 或 skip() 方法从输入流中返回的可读的字节数。当发生I/O错误时，返回IOException。该方法不能返回输入缓存流里实际所包含的准确字节数，因为子类不能返回流的总共的大小。
void close() | 关闭流并释放操作系统里与流相关的其他资源。当发生I/O错误时，返回IOException。
void mark(int readlimit) | 标识在输入流中当前的位置。后续调用 reset() 方法重新定位到流最后标志的位置；因此后续再调用 read() 方法重新读会读出相同的字节。参数 readlimt 明确了在无效标记之前允许从输入流中读取的字节。因此流不能重新 reset 到标志的位置。
boolean markSupported() | 当输入流支持 mark() 和 reset() 方法时返回 true 否则返回 false 。
int read() | 从输入流里返回下一个字节（作为 int ，范围是 0 到 255），当到达文件末尾时返回-1。
int read(byte[] b) | 从输入流里读取 b.length 的字节并存放到 b 里。返回实际存放到 b 里的字节数，当到达文件末尾或是没有可读时返回 -1 。直到输入流可用，到达流末尾或是异常抛出才不会阻塞。当 b 是 null 时抛出 NullPointerException 或者是当发生 I/O 错误时抛出 IOException 。
int read(byte[] b, int off,int len) |从输入流里读取不超过 len 的字节数据并存放到 b 里，起始位置由 off 确定。当到达文件末尾或是没有可读时返回 -1 。直到输入流可用，到达流末尾或是异常抛出才不会阻塞。当 b 是 null 时抛出 NullPointerException 或者是当发生 I/O 错误时抛出 IOException。当 off 是负数，len 是负数，或者 len 大于  b.length - off 抛出 IndexOutOfBoundsException 。
void reset() | 将指针重新定义到流最后一次使用 mark() 作标记的地方。当输入流并没有标识过或标识无效时抛出 IOException 。
long skip(long n) | 从输入流当们位置跳过并忽略掉 n 个字节。实际跳过的字节数可能为0，例如当到达文件末尾时。返回的为实际跳过的字节数。当 n 是负数时，不会跳过。当输入流并不支持 skipping 或发生 I/O 错误时抛出 IOException 。


   InputStream 的子类例如 ByteArrayInputStream 支持标识获取调用 mark() 标识的位置，并返回调用 reset() 标识的位置。
   
   注意在使用 mark() 和 reset() 方法时，需要先调用 markSupport() 方法来确定是否支持。
   
### ByteArrayOutputStream and ByteArrayInputStream page79
    字节数组经常被用作流的目的地和数据源。ByteArrayOutputStream 允许将流的数据输出到字节数组中；ByteArrayInputStream 允许将输入流的数据读入到字节数组中。  
    
    
    ByteArrayOutputStream 构造方法
方法|描述
----|----
ByteArrayOutputStream | 创建一个输出流内部的字节数组，其初始大小为32，当有需要时会增长。
ByteArrayOutputStream(int size) | 创建一个输出流内部的字节数组，其初始大小为 size 指定的大小，当有需要时会增长。
当 size 小于 0 时抛出 java.lang.IllegalArgumentException 。
    
    
    ByteArrayInputStream 构造方法
方法|描述
----|----
ByteArrayInputStream(byte[] ba)  | 创建输入流使用的字节数组，字节数组是直接使用并不会复制一份。起始位置为0，长度设置为 ba.length 。
ByteArrayInputStream(byte[] ba ,int offset,int count) | 创建输入流使用的字节数组（不会有副本）。起始位置根据 offset 设定，实际读取的字节数由 count 设定。

    当你需要将图片转化为字节数组时，ByteArrayOutputStream 和 ByteArrayInputStream 非常有用，按某种规则处理这些字节数组，并可将其还原为图片。
    
### FileOutputSteam and FileInputStream page82  
    文件是流常用的输入源，输出目的地。FileOutputSteam 可以将流的字节数组输出到文件；FileInputStream 可以将文件里的字节数据加载到输入流里。   
    
    FileOutputSteam 作为 OutputStream 的子类声明了5个构造方法来创建输出流。例如，FileOutputStream(String name) 创建由 name 指定的已存在文件的输出流。当文件不存在或者不能被创建时抛出 java.io.FileNotFoundException 。
    
    示例：
```
public class Copy {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("usage: java copy srcfile dstfile");
            return;
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try{
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            // I chose b instead of byte because byte is a reserved word
            int b ;

            while((b = fis.read()) != -1)
                fos.write(b);
        }catch (FileNotFoundException fnfe){
            System.err.println(args[0] + " could not be opened for input, or "
                               + args[1] + " could not be created for output");
        }catch(IOException e){
            System.err.println("I/O error: " + e.getMessage());
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException ioe){
                    //shouldn't happen in this context
                    assert false;
                }
            }

            if(fos != null){
                try{
                    fos.close();
                }catch(IOException ioe){
                    assert false;
                }
            }
        }

    }
}
```   
   版本2
```
public class Copy2 {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("usage : java Copy srcfile dstfile");
            return;
        }

        try(FileInputStream fis = new FileInputStream(args[0]);
            FileOutputStream fos = new FileOutputStream(args[1])){

            int b;
            while((b = fis.read()) != -1)
                fos.write(b);

        }catch(FileNotFoundException fnfe){
            System.err.println(args[0] + " could not be open for input"
                               + args[1] + " could not be open for wirte");
            return;
        }catch(IOException ioe){
            System.err.println("I/O error: " + ioe.getMessage());
        }
    }
}
```

### PipedOutputStream and PipedInputStream page86  
    线程之间经常需要通信。一种途径是使用共享变量；另一种途径是通过 PipedOutputStream 和 PipedInputStream 使用管道流来实现。线程A可以将数据输出到一个与 PipedInpuStream 相关的 PipedOutputStream 管道输出流里；线程B可以从 PipedInpuStream 里读取之前输出的数据。
    
    注意：单个线程时不推荐使用管道输入输出流，因为这样容易导致死锁。
    
    管道输出流提供了如下两个构造方法
方法|描述
----|----
PipedOutputStream() | 创建一个并未连接管道输入流的管道输出流。在使用之前，它与管道输出流进行连接或者使用 receiver 或 sender 。
PipedOutputStream(PipedInputStream dest) | 创建一个连接到管道输入流 dest 的管道输出流。通过向管道输出流写入数据就可以将数据输出到 dest 。当发生 I/O 错误时，抛出 IOException 。

    PipedOutputStream 声明了一个 void connect(PipedInputStream dest) 方法，用于将管道输出流连接到 dest 的管道输入流。当管道输出流已经连接到另一个管道输入流时会报出 IOException 。
    
    管道输入流提供了如下四个构造方法：  
方法|描述
----|----
PipedInputStream() | 创建一个并未连接到管道输出流的输入流。在使用之前必须建立与管道输出流之间的连接。
PipedInputStream(int pipeSize) | 创建一个并未连接到管道输出流使用 pipeSize 定义缓冲流大小的管道入流。在使用之前必须建立与管道输出流之间的连接。当 pipeSize 小于或等于0时，抛出 IllegalArgumentException 。  
PipedInputStream(PipedOutputStream src) | 创建一个连接到管道输出流 src 的管道输入流。写入 src 的数据可以从管道输入流里读取到。当发生 I/O 错误时，抛出 IOException 。
PipedInputStream(PipedOutputStream src,int pipeSize) |  创建一个连接到管道输出流 src 并指定缓冲大小为 pipeSize 的 管道输入流。写入 src 的数据可以从管道输入流里读取到。当发生 I/O 错误时，抛出 IOException 。当 pipeSIze 小于或等于0时，抛出 IllegalArgumentException 。  
  
  PipedInputStream 声明了 void connect(PipedOutputStream src) 方法用于建立与管道输出流 src 之间的连接。当该管道输入流已经与别的管道输出流建立连接后，报出 IOException 。
  
  ```
      PipedOutputStream pos = new PipedOutputStream();
      PipedInputStream pis = new PipedInputStream(pos);
  ```
  
  或者
  ```
      PipedInputStream pis = new PipedInputSteram();
      PipedOutputStream pos = new PipedOutputStream(pis);
  ```
  
  或者
  ```
      PipedOutputStream pos = new PipedOutputStream();
      PipedInputStream pis = new PipedInputStream();
      
      //...
      pos.connect(pis);
  ```
  
  示例：
  ```
  public class PipedStreamDemo {
        final static int LIMIT = 10;
    
        public static void main(String[] args) throws Exception {
            final PipedOutputStream pos = new PipedOutputStream();
            final PipedInputStream pis = new PipedInputStream(pos);
    
            Runnable senderTask = () -> {
                try{
                    for(int i = 0; i < LIMIT; i++)
                        pos.write((byte)(Math.random() * 256));
    
    
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }finally {
                    try{
                        pos.close();
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            };
    
            Runnable receiverTask = () -> {
                try{
                    int b;
                    while((b = pis.read()) != -1)
                        System.out.println(b);
    
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }finally {
                    try{
                        pis.close();
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            };
    
            Thread sender = new Thread(senderTask);
            Thread receiver = new Thread(receiverTask);
    
            sender.start();
            receiver.start();
    
        }
    }
  ```
### FilterOutputStream and FileInputStream page90
    字节数组输入输出流、文件输入输入流、管道输入输出流对于输出到目的的流是没有改变的。Java 同样支持在输出到目的地时对缓冲流过滤、压缩/解压、加密/解密，以及直接操作字节流。
    
    过滤器输出流将数据传送到 write() 方法，过滤，然后将过滤后的数据传递给低层的输出流，这个流或许是另一个过滤器输出流或者是输出流目的的如文件输出流。
    
    过滤器输出流是从 OutputStream 子类 FilterOutputStream 创建而来的。
    

```
    public class ScrambledOutputStream extends FilterOutputStream {

        private int[] map;
    
        /**
         * Creates an output stream filter built on top of the specified
         * underlying output stream.
         *
         * @param out the underlying output stream to be assigned to
         *            the field <tt>this.out</tt> for later use, or
         *            <code>null</code> if this instance is to be
         *            created without an underlying stream.
         */
        public ScrambledOutputStream(OutputStream out,int[] map) {
            super(out);
    
            if(map == null)
                throw new NullPointerException("map is null");
    
            if(map.length != 256)
                throw new IllegalArgumentException("map.length != 256");
    
            this.map = map;
        }
    
        @Override
        public void write(int b) throws IOException {
            out.write(map[b]);
        }
    }

  public class Scramble {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
           System.err.println("usage: java Scramble srcpath destpath");
           return;
        }

        FileInputStream fis = null;
        ScrambledOutputStream sos = null;

        try{
            fis = new FileInputStream(args[0]);
            FileOutputStream fos = new FileOutputStream(args[1]);
            sos = new ScrambledOutputStream(fos,makeMap());
            int b ;
            while ((b = fis.read()) != -1)
                sos.write(b);


        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }

            if (sos != null) {
                try{
                    sos.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }

    }

    static int[] makeMap(){
        int[] map = new int[256];
        for(int i=0;i<map.length;i++){
            map[i] = i;
        }

        Random r = new Random(0);
        for(int i=0;i<map.length;i++){
            int n = r.nextInt(map.length);
            int temp = map[i];
            map[i] = map[n];
            map[n] = temp;
        }

        return map;
    }
}



```
  过滤器输入流示例：
```
public class ScrambleInputStream extends FilterInputStream {

    private int[] map;

    public ScrambleInputStream(InputStream in,int[] map){
        super(in);

        if(map == null)
            throw new NullPointerException("map is null");

        if(map.length != 256)
            throw new IllegalArgumentException("map.length != 256");

        this.map = map;
    }

    @Override
    public int read() throws IOException {
        int value = in.read();
        return (value == -1) ? -1 : map[value];
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int nBytes = in.read(b,off,len);
        if(nBytes <= 0)
            return nBytes;

        for(int i= 0; i < nBytes; i++)
            b[off + i] = (byte)map[off + i];

        return nBytes;
    }


public class Unscramble {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("usage : java Unscramle srcpath destpath");
            return;
        }

        ScrambleInputStream sis = null;
        FileOutputStream fos = null;

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            sis = new ScrambleInputStream(fis,makeMap());
            fos = new FileOutputStream(args[1]);

            int b;
            while((b = sis.read()) != -1)
                fos.write(b);


        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            if(sis != null)
                try{
                   sis.close();
                }catch(IOException ioe){
                   ioe.printStackTrace();
                }

            if(fos != null)
                try{
                    fos.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
        }
    }

    static int[] makeMap(){
        int[] map = new int[256];
        for(int i=0;i<map.length;i++)
            map[i] = i;

        Random r = new Random(0);
        for(int i=0;i<map.length;i++){
            int n = r.nextInt(map.length);
            int temp = map[i];
            map[i] = map[n];
            map[n] = temp;
        }

        int[] temp = new int[256];
        for(int i=0;i<temp.length;i++)
            temp[map[i]] = i;

        return temp;
    }
}


```
  
####  BufferedOutputStream and BufferedInputStream 缓冲输入输出流
      FileInputStream 和 FileOutputStream 存在性能问题。文件输出流调用 write() 方法以及文件输入流调用 read() 方法都是调用底层操作系统的本地方法，而这些本地方法降低了I/O调用。
      
      作为过滤器输入输出流系列类的 BufferedOutputStream 和 BufferedInputStream 通过最小化对低层操作系统 write() read() 方法的调用以提供性能。相反，BufferedOutputStream write() 和 BufferedInputStream read() 方法使用到了 Java 缓存。
      
- [ ] 当“写缓存”满时，write() 调用低层系统的 write() 方法将数据输出并清空“写缓存”。后续调用 BufferedOputStream 的 write() 方法时将数据首先存放到“写缓存”直到它被再次填充满。
- [ ] 当“读缓存”为空时，read() 调用低层系统的 read() 方法将数据读出并填充到“读缓存”。后续调用 BufferedInputStream 的 read() 方法时直接从“读缓存”读出直到“读缓存”再次为空。   

     BufferedOutputStream 声明了下面两个构造方法：
方法名|描述
----|----
BufferedOutputStream(OutputStream out) | 创建一个缓冲输出流，数据流通过 out 输出。内部缓存创建并用来存储需要输出的数据。内部缓冲默认为 byte[8192]
BufferedOutputStream(OutputStream out,int size) | 创建一个缓冲输出流，数据流通过 out 输出。内部缓存大小由 size 指定。  

    示例：
```
    FileOutputStream fos = new FileOutputStream("employee.dat");
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    
    //通过缓存将数据输出到 employee.dat
    bos.write(o);
    
    //该方法内部会调用  fos.close() 方法
    bos.close();

```  
    
    
    BufferedInputStream 声明了下面两个构造方法：
方法名|描述
----|----
BufferedInputStream(InputStream in) | 创建一个缓冲输入流，输入流的数据通过 in 输入。内部缓存用于存储输入的数据。默认缓存大小为 8192 。
BufferedInputStream(InputStream in, int size) | 创建一个缓冲输入流，输入流的数据通过 in 输入。缓存的大小由 size 指定。  

    
    示例：
```
    FileInputStream fis = new FileInputStream("employee.dat");
    BufferedInputStream bis = new BufferedInputStream(fis);
    
    //通过缓存读取 employee.dat 的内容
    int ch = bis.read();
    
    //...
    
    //该方法内部自动调用 fis 的 close() 方法
    bis.close();
```
    
    
####  DataOutputStream and DataInputStream 数据输入输出流  
      文件输入输出流对于读写字节数据和字节数组非常有效。然而，并不支持读写基本类型（primitive-type）（如整型）和字符串。
      
      鉴于这个原因，Java 创建根据过滤器输入输出流创建了 DataOutputStream 和 DataInputStream 。每个类通过独立于操作系统的方式克服了对基本类型和字符串的读写。
- [ ] 整型数据的读写采用 big-endian 格式
- [ ] float 和 double 根据 IEEE 754 标准来读写。标明了每个 float 为4个字节，每个 double 类型为8个字节。
- [ ] String 的读写是根据 UTF-8 标准来操作。每个字符使用2个字节存储。   


    示例：
```
public class DataStreamsDemo {
    final static String FILENAME = "/home/yifan/work/tmp/values.txt";

    public static void main(String[] args) throws Exception {
        try(FileOutputStream fos = new FileOutputStream(FILENAME);
            DataOutputStream dos = new DataOutputStream(fos)){

            dos.writeInt(1995);
            dos.writeUTF("Saving this string in modified UTF-8 format!");
            dos.writeFloat(1.0F);

        }catch (IOException ioe){
            System.err.println("I/O error:" + ioe.getMessage());
        }

        try(FileInputStream fis = new FileInputStream(FILENAME);
            DataInputStream dis = new DataInputStream(fis)){
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
            System.out.println(dis.readFloat());
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
}
```


#### Object Serialization and Deserialization 对象序列化和反序列化   page104
     Java 提供的 DataOutputStream 和 DataInputStream 支持对基本类型和字符串对象的操作。然后，并不能使用该类进行非字符串对象的操作。相反，必须实现对象的序列和反序列化。   
     
     对象序列化是JVM提供的将对象转化为字节流的机制。其反序化是JVM提供将字节流转化为对象。   
     
     Java 支持默认的序列化、反序列化；自定义序列化、反序列化及其他序列化。   
     
##### Default Serialization and Deserializaton 默认序列化、反序列化   
     默认序列化易于使用但不易于控制。
     
     首先，需要被序列化的类必须 implement java.io.Serializable 接口；或者直接或间接地其超类为该类。
     
```
public class Employee implements Serializable{
    private String name;
    private int age;

    public Employee(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {return this.name;}
    public int getAGe(){return this.age;}
}
```

    因为 Employee 类实现了 Serializiable 接口，因此序列化是不会抛出 NotSerializableException 异常。不仅 Employee 实现了 Serializable ，java.langString 也同样实现了该接口。
    
    第二步使用 ObjectOutputStream 类的 writeObject() 方法来将对象序列化。调用 ObjectInputStream 的 readObject() 来将其反序列化为对象。   
    
    writeObject() 并不序列化静态字段（static fields）。相反，没带 transient 的所有字段都能被序列化，如下声明：
    
  ```
      public transient char[] password;
  ```
    
    通过声明为 transient 类型避免将 password 序列化。JVM 的序列化机制会忽略掉所有标记为 transient 的字段。
    
    writeObject() 当发生错误时报出 IOException 或 IOException 异常的子类异常。例如，将遇到该类并未实现 Serializable 接口时，报 NotSerializableException 。   
    
    Java 使用 ObjectInputStream 类用于从对象输入流里反序列化出对象。该类声明了 ObjectInputStream(InputStream in) 构造方法，由 in 指定具体的输入流。
    
    将具体的输入流传递给 in 时，构造方法尝试从 in 输入流里读取出序列化的头部信息。当 in 为空时抛出 NullPointerException ；当流头部不正确时抛出  java.io.StreamCorruptedException  。当发生I/O错误或阻止读取流头部时，抛出 IOException 。
    
    示例：
```
public class SerializatioinDemo {
    final static String FILENAME = "/home/yifan/work/tmp/employee_serial.dat";

    public static void main(String[] args) throws Exception {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);

            Employee emp = new Employee("John Doe",36);
            oos.writeObject(emp);
            oos.close();

            oos = null;

            FileInputStream fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);

            // cast
            emp = (Employee) ois.readObject();
            ois.close();

            System.out.println(emp.getName());
            System.out.println(emp.getAGe());

        } catch (ClassNotFoundException cnfe){
            System.err.println(cnfe.getMessage());
        } catch (IOException ioe){
            System.err.println(ioe.getMessage());
        } finally {
            if( oos != null)
                try{
                    oos.close();
                }catch(IOException ioe){
                    assert false;
                }

            if( ois != null)
                try{
                    ois.close();
                }catch(IOException e){
                    assert false;
                }
        }

    }
}
```

通过为类加上 static final long serialVersionUID = long integer ；可以避免 InvalidClassException 。这个 serialVersionUID 值必须是唯一的。
    
    
  JDK 提供了 serialver 工具用于计算生成 SUID 。如下： （page 109）
  ```
      serialver Employee

      Employee: static final long serialVersionUID = 1517331364702470316L;


  ```
