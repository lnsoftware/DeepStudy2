
### set mb->native_invoker

### class.c 
line 1122
breakpoint strcmp(slash2DotsDup(CLASS_CB(mb->class)->name),"JniAdder")==0

mb->native_invoker = &resolveNativeWrapper;

make native_invoker point to method resolveNativeWrapper in dll.c

### dll.c

line 224

breakpoint
strcmp(slash2DotsDup(CLASS_CB(mb->class)->name),"JniAdder")==0



dll.c resolveDll
br strcmp(name,"/home/vagrant/Desktop/DeepStudy2/jvm-study/jamvm/java/libbridge.so")==0
1) line 341 load so
handle = nativeLibOpen(name);
2) line 388 add entry to hash_table




### os.c
nativeLibSym
strcmp(symbol,"JniAdder") == 0



jni exec, invoke
resolveNativeWrapper dll.c 224
->
	resolveNativeMethod()

	-> lookupLoadedDlls load JniAdder

	->setJNIMethod make call callJNIWrapper
	dll.c 615
	br strcmp(slash2DotsDup(CLASS_CB(mb->class)->name),"JniAdder")==0


callJNIWrapper really exec native method