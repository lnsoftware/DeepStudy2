cmd_/root/kernel/lkm_example.ko := ld -r -m elf_x86_64 -T ./scripts/module-common.lds --build-id  -o /root/kernel/lkm_example.ko /root/kernel/lkm_example.o /root/kernel/lkm_example.mod.o
