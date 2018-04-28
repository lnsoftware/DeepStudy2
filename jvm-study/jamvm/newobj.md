
### Java ByteCode

```
 0: new           #2                  // class NewObj
         3: dup
         4: invokespecial #3                  // Method "<init>":()V
         7: astore_1
         8: return
```

### BitCode

bb000259b700034cb1

interp.c 
in line:    PREPARE_MB(mb);
condition breakpoint: mb->code == 0x7bf160

### OPC_NEW

resolve class file
rewrite OPC_NEW code to OPC_NEW_QUICK

alloc.c allocObject