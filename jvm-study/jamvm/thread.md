
### dll path
"/usr/local/classpath/lib/classpath"



classpath/java/lang/Thread.c

  public synchronized void start()
  {
    if (vmThread != null || group == null)
      throw new IllegalThreadStateException();

    VMThread.create(this, stacksize);
  }



  classpath/vm/reference/java/lang/Thread.c


 static void create(Thread thread, long stacksize)
    {
        VMThread vmThread = new VMThread(thread);
        vmThread.start(stacksize);
        thread.vmThread = vmThread;
    }


    native void start(long stacksize);



    /home/vagrant/Desktop/jamvm/src/classlib/gnuclasspath/natives.c  992

    
