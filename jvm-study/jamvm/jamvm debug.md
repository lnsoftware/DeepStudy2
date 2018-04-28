## interp model
in interp.h jamvm use interp-inlining.h

## INTERPRETER_DEFINITIONS

interp/engine/interp.c executeJava

INTERPRETER_DEFINITIONS (src/interp/engine/interp-inlining.h)
use
DEFINE_HANDLER_TABLES src/interp/engine/interp-threading.h 43 
use
src/interp/engine/interp-inlining.h 107

result:
build handlers_0_START handlers_0_ENTRY handlers_0_END

array content: &&opc##opcode##_##level##_##label
demo:&&opc_0_0_START
this is a opcode process label,the code is below

### PREPARE_MB 

src/interp/direct.c
as opcode 

make mb->code to new code





direct.c prepare 
lint 84 condition breakpoint 
code == 0x7bf2f0
```
(gdb) x/19xb mb.code

0x7bf2f0:	0xb2	0x00	0x02	0x12	0x03	0xb6	0x00	0x04
0x7bf2f8:	0xb1	0x00	0x00	0x00	0x00	0x00	0x00	0x00
0x7bf300:	0x00	0x00	0x00
```

interp.c 1324
java method exec: GETSTATIC
1349 opcode change to quick:OPC_GETSTATIC_QUICK_REF

