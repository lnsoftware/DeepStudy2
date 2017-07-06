### Classic I/O
  JDK1.0 引入了基础 I/O 用于访问文件系统
  
### File System Access and the File Class
  不同的操作系统其路径不一样。Unix ,Linux 以及类 Unix 操作系统使用 / 来分隔路径。而 windows 使用 \ 来分隔路径。
  ```
      /users/username/bin
      C:\users\username\bin
  ```
  
  ```
     new File("temp").mkdir();
  ```
  
### Accessing File Content via RandomAccessFile 
    文件内容可以有序访问也可以随机访问。随机访问可以提供搜索排序的性能。  
    
```
  RandomAccessFile raf = new RandomAcessFile("employees.dat","r");
  int empIndex = 10;
  raf.seek(empIndex * EMP_REC_LEN);
              
```   
  employees.dat 文件里包含的内容是按固定 EMP_REC_LEN 大小分开的 employee 记录。seek 到访问记录的第一个字节的位置。
    
### Streaming Data via Stream Classes
    传统I/O 包含了以流的方式来操作I/O .
    一个数据流就是一个任意长度的有序的字节数组。
    字节数组通过输出流从应用输出，通过输入流从目的应用接收。  
    
![image](E:\learn\java\io\1.png)

    在 java.io 包下提供了不同的流操作类用于文件的读写。
    例如，你可以使用 FileInputStream 类打开并建立已经存在的文件。通过使用 read() 从文件输入流中读取字节数组。最后调用 close() 方法来判断指向文件的流。大概如下：
    
```
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("image.jpg");
            
            //从文件里读取 bytes
            int _byte;
            while((_byte = fis.read()) != -1){  // -1 意味着 EOF 指到文件末尾
                //数据处理
            }
        }catch (IOException ioe){
            //异常处理
        }finally {
            if(null != fis){
                try{
                    fis.close();
                }catch (IOException ioe){
                    //异常处理
                }
            }
        }    
```
    
    上面的例子描述了用传统的方式去打开、创建指定文件的流进而读取数据。需要关心的是异常的处理 java.io.IOException 。
    
    无论是否有异常抛出，在不使用时一定要关闭输入流。这个操作放置在 try 语句的 finally 块。由于文件关闭的冗长处理，可以替换为使用 JDK7 里的带资源的try语句(try-with-resources) 这会自动文件文件，代码如下：
    
```
        try (FileInputStream fis = new FileInputStream("image.jpg"))
        {
            // Read bytes from file.
            int _byte;
            while ((_byte = fis.read()) != -1) // -1 signifies EOF
                ; // Process _byte in some way.
        }
        catch (IOException ioe)
        {
             // Handle exception.
        }
```
    
    一些流处理类用于封装其他一些流处理类。例如，为了提供性能，BufferedInputStream 从另一个流里按块的方式读取并返回到其缓存中，直到 nio.nio.buffer 为空才读取另一块。
    
```
    try(FileInputStream fis = new FileInputStream("image.jpg");
        BufferedInputStream bis = new BufferedInputStream(ifs)){
        
        //Read bytes form file
        
        int _byte;
        while((_byte = bis.read()) != -1) // -1 标示 EOF 文件末尾
        ; //数据处理
    } catch(IOException ioe){
        //异常处理
    }
```

### Stream Classes and Standard I/O
    许多操作系统支持标准I/O操作。这些提前建立连接的流有：标准输入流、标准输出流、标准错误流。
    
    标准输入流默认从键盘读取数据。
    标准输出流和标准错误流默认将数据输出到屏幕。
    然后这些流都可以重定向到其他目的地进行数据的读写，如文件。
    
```
    //从标准输入流里读取单个字符
    int ch = System.in.read();
    
    //向标准输出流里写字符串
    System.out.println("Hello");
    
    //向标准错误流写字符串
    System.err.println("I/O error:" + ioe.getMessage());
```    


### NIO

    现代操作系统提供了更复杂的 I/O 服务以提高I/O性能以及简化I/O。
    可扩展性I/O、更快的二进制缓存及字符流、正则表达式、编码转换。
    包含的成员有：  
            
- [x] Buffers
- [x] Channels
- [x] Selectors
- [x] Regular expression
- [x] Charsets
    
#### Buffers
     Buffers 是NIO操作的基础。本质上，NIO就是将数据装载到 buffers 以及从 buffers 中移除。

![image](E:\learn\java\io\2.png)

     将操作系统的缓存复制到处理器的缓存是并不高效。通过 DMA 控制器直接复制到处理器缓存会更高效，不过这种方式存在两个问题：
     - [ ] DMA 控制器不能直接与JVM进程的用户空间进行通信。但是它可以直接与操作系统内核进行通信。
     - [ ] 面向块操作的设备如 DMA 控制器是以固定大小数据块进行工作的。但是，JVM 处理时并不是按固定块大小进行数据处理。
     
     因为这些问题，操作系统就当作 JVM 和 DMA 控制器的中介，用于数据的分离以及重组。
     
#### Channels
     强迫 CPU 进行I/O任务处理以及等待I/O完成是一种资源的浪费。为了提高性能，将这些任务卸下来交给 DMA 控制器来处理进而处理器可以用来处理其他工作。
     
     Channel 被当作是 DMA 控制器与操作系统之间用于从磁盘读取和写入的管道。JDK1.4 里 java.nio.channels.Channel 接口及其子接口以及各种类都实现了 channel 这种架构。
     
     作为这些类之一的 java.nio.channels.FileChannel ，它抽象了对文件以管道的方式进行数据读、写、映射及其他操作。FileChannel 特性之一是支持文件锁，这是其他复杂应用诸如数据管理系统所依赖的。
     
     通过文件锁处理器可以用来阻止或限制对一个文件的访问进程。文件锁可以应用于整个文件，也可以应用于文件的一个部分。一个文件锁的范围是从文件起始的位置到指定大小的位置。
     
     FileChannel 另一个特性是通过 map() 方法 进行对I/O文件的内存映射(memory-mapped)，这会返回 java.nio.MappedByteBuffer ；它的内容就是内存里对于文件一部分的映射。文件内容的访问可以直接通过对内存的访问；nio.nio.buffer 复制读写都可以不用了。
     
     通过调用 java.nio.channels.Channels 类里的方法或者传递 I/O 类如 RanddomAccessFile 都可以获得 Channel。
     
     
#### Selectors  

     I/O 基于块或基于流。从文件中读取或写入文件是基于块的I/O。相反，从键盘中读取或输出到网络是基于流的I/O。
     
     基于流的I/O 通过比基于块的I/O要慢一点。因此输入往往存在间隙。例如输入时用户的暂停或因为网络连接短暂缓慢导致视频播放不流畅。
     
     许多操作系统支持当没有有用输入时线程持续检查是否有非块方式输入流。线程可以处理输入数据或处理其他任务走到数据流到达。
     
     对于输入流的轮询是一种浪费，特别是线程需要监控许多输入流时（如 web 服务）。现代操作系统通过内置的非阻塞式有条件选择可以高效的检查。操作系统监控流并在I/O流到达时为线程返回信号。 
     
     JDK1.4 通过提供 selectors 支持 readiness selection，对应的实现类为 java.nio.channels.Selector ，该类可用于检查一个或多个 channel 以判断哪一个 channel 可用于读或写。通过这种方式单个线程就可以高效地管理多个 channel 。  
     

![image](E:\learn\java\io\3.png)
     
     
#### Regular Expression
     正则表达式作为 NIO 的一部分。你可能会惊讶放在I/O里能做什么？正则表达式通常用于文本数据或其他资源的扫描。在这里需要尽可能快地扫描到所需要包含的内容。JDK1.4 提供了 java.util.regex 以及 Pattern 、Matcher 类来支持正则表达式。
     
     
#### Charsets
     在 JDK1.1 里的 writer/reader 类已经考虑到字符的编码。最初，类如java.io.InputStreamReader 都是基于 java.io.ByteToCharConverter 类来实现编码转换。从 JDK6 已经不推荐并删除 ByteToCharConverter 。代之的是更具有扩展性的 java.nio.charset 包及其 Charset，CharsetEncoder,CharsetDecoder。
     
#### Formatter
     JSR51里提到了更为灵巧的打印样式格式化。JDK1.4 并没有从语言特性里支持这一点。直到 JDK1.5 通过 java.util.Formatter 很好支持了自定义格式化。
     
     
#### NIO.2
    JSR 51指定NIO将引入一种改进的文件系统接口克服 File 各种遗留问题。
    
#### Improve File System Interface 提升文件系统接口
    File 类遗留了许多问题。例如，renameTo() 方法并不能跨平台使用。同样，File 类的许多方法并不具有扩展性，当从服务器请求比较大的目录列表时会造成假死。在 JSR203 提到的新的文件系统接口修复了这些及其他问题。例如，支持批量访问文件属性；改变通知更灵巧；提供了跨文件系统的 API ，并提供了对于可插入式文件系统访问的接口。
    
#### Asynchronous I/O 异步I/O
    
#### Completion of Socket Channel Functionality 实现套接字管道
     JDK1.4 在 java.nio.channel 包下添加了 DatagramChannel、ServerSocketChannel 以及 SocketChannel 类。然而，由于缺乏时间没有实现这些类的绑定和可配置。同样基于管道的数据广播也并未支持。JDK7 添加了对这些类的绑定和可配置，并引入了新的类 java.nio.channels.MulticastChannel 接口。
     
     
     
     
     