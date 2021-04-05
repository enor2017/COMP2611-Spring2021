.data
msg: .asciiz "How many starts to output?\n"
star: .asciiz "*"

.text
.globl _main
_main:
	la $a0, msg
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	
	add $s0, $v0, $0  # $s0 is n
	addi $t0, $0, 1   # $t0 is iterator
	
judge:	slt $t1, $s0, $t0
	beq $t1, $0, loop
	j exit
loop:	la $a0, star
	li $v0, 4
	syscall
	addi $t0, $t0, 1
	j judge
exit:

	li $v0, 10
	syscall
	
