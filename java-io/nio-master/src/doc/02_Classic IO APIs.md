## File
   应用经常同文件系统交互，而文件系统就是从根目录开始下面子目录或文件的层级关系。运行在操作系统上的JVM至少支持一种文件系统。例如，在Unix/Linux上将所有加载的磁盘加载到一个虚拟的文件系统。相反，在 windows 上会将每个活动的磁盘单独作为文件系统。Java 通过 java.io.File 类实现对这些文件系统的访问。
   
### Constructing File Instances
   ```
   //unix linux
   File file1 = new File("/x/y");
   
   //windows
   File file2 = new File("C:\\temp\\x.dat");
   ```
   
   为了获取系统的属性可以调用 java.lang.System 类的 getProperty() 方法。
   
   默认的分隔符为 File.separator 
   
   File 提供了在父文件基础上创建子文件：
   ```
    File(String parent,String child)
    File(File parent,String child)
   ```
   
   ```
     File file3 = new File("prj/books","io");
   ```
   
###  Abstract Paths 抽象路径   
```
public class PathInfo {

    public static void main(final String[] args) throws IOException{

        if(args.length != 1){
            System.err.println("usage:java PathInfo path");
            return;
        }

        File file = new File(args[0]);
        System.out.println("Absolute path = " + file.getAbsolutePath());
        System.out.println("Canonical path = " + file.getCanonicalPath());
        System.out.println("Name = " + file.getName());
        System.out.println("Parent = " + file.getParent());
        System.out.println("Path = " + file.getPath());
        System.out.println("Is absolute = " + file.isAbsolute());

    }
}

    //输出
    Absolute path = /home/yifan/work/tmp/a.txt
    Canonical path = /home/yifan/work/tmp/a.txt
    Name = a.txt
    Parent = /home/yifan/work/tmp
    Path = /home/yifan/work/tmp/a.txt
    Is absolute = true
```

#### File Methods for A File or Dirctory
```
public class FileDirectoryInfo {

    public static void main(final String[] args) throws IOException {
        if(args.length != 1){
            System.err.println("usage : java FileDirectoryInfo path");
            return;
        }

        File file = new File(args[0]);
        System.out.println("Abount " + file + ":");
        System.out.println("Exists = " + file.exists());
        System.out.println("Is directory = " + file.isDirectory());
        System.out.println("Is hidden = " + file.isHidden());
        System.out.println("Last modified = " + new Date(file.lastModified()));
        System.out.println("Length = " + file.length());
    }
}

//输出
Abount /home/yifan/work/tmp/b.txt:
Exists = true
Is directory = false
Is hidden = false
Last modified = Tue Mar 28 20:34:08 PDT 2017
Length = 64
```

#### Listing File System Root Dirctories
```
public class DumpRoot {
    
    public static void main(String[] args){
        File[] roots = File.listRoots();
        for(File root : roots){
            System.out.println(root);
        }
    }
}
```

#### Obtaining Disk Space Information 获取磁盘信息
     Java6 向 File 类添加了 getFreeSpace() 、getTotalSpace()、getUsableSpace() 方法用于返回关于当前磁盘的信息。
     
```
public class PartitionSpace {
    public static void main(String[] args){
        File[] roots = File.listRoots();
        for(File root : roots){
            System.out.println("Partition: " + root);
            System.out.println("Free space on this partition = "
                    + root.getFreeSpace() );
            System.out.println("Usable space on this partition = "
                    + root.getUsableSpace());
            System.out.println("Total space on this partition = "
                    + root.getTotalSpace());
            System.out.println("***");
        }
    }
}

```

//输出
```
Partition: /
Free space on this partition = 8539783168
Usable space on this partition = 7664066560
Total space on this partition = 16775028736
***
```


#### Listing Directories 目录遍历
```
public class Dir {
    public static void main(final String[] args){
        if(args.length != 2){
            System.err.println("usage:java Dir dirpath ext");
            return;
        }
        File file = new File(args[0]);
        FilenameFilter fnf = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(args[1]);
            }
        };
        
        String[] names = file.list(fnf);
        for(String name : names){
            System.out.println(name);
        }
    }
}

```

#### Creating/Modifying Files and Directoires 文件和目录的创建与修改

```
public class TempFileDemo {
    public static void main(String[] args) throws IOException{
        System.out.println(System.getProperty("java.io.tmpdir"));
        File temp = File.createTempFile("text",".txt");
        System.out.println(temp);
        temp.deleteOnExit();
    }
}
```

//输出
```
/tmp
/tmp/text1718727053833638871.txt
```

####  Setting and Getting Permission 设置、获取权限
      Java 1.2 添加了 boolean setReadOnly() 方法用于设置文件或目录只读。
      Java 6 添加了如下方法：
      - [ ] boolean setExecutable(boolean executable,boolean ownerOnly)
      - [ ] boolean setExecutable(boolean executable)
      - [ ] boolean setReadable(boolean readable,boolean ownerOnly)
      - [ ] boolean setReadable(boolean readable)
      - [ ] boolean setWritable(boolean readable,boolean ownerOnly)
      - [ ] boolean setWritable(boolean readable)
      
```
public class Permissions {
    public static void main(String[] args){
        if(args.length != 1){
            System.err.println("usage:java Permissions filespec");
            return;
        }
        File file = new File(args[0]);
        System.out.println("Checking permissons for " + args[0]);
        System.out.println("   Execute = " + file.canExecute());
        System.out.println("   Read = " + file.canRead());
        System.out.println("   Write = " + file.canWrite());
    }
}
```

//输出
```
Checking permissons for /home/yifan/work/tmp/b.txt
   Execute = false
   Read = true
   Write = true
```

#### Exploring Miscellaneous Capabilities 探索各种各样的功能
```
public class Compare {

    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            System.err.println("usage:java Compare filespec1 filespec2");
            return;
        }

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        System.out.println(file1.compareTo(file2));
        System.out.println(file1.getCanonicalFile().compareTo(file2.getCanonicalFile()));
    }
}
```



   
   
   
   
   