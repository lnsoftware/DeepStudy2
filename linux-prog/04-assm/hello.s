
.data
	message: .string "hello!\n"
	length = . - message

.text
.global _start

_start:
	movl $length, %edx
	movl $message, %ecx
	movl $1, %ebx
	movl $4, %eax
	int $0x80

	movl $0, %ebx
	movl $1, %eax
	int $0x80
