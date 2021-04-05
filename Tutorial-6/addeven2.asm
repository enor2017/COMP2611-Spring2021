.data
.text
.globl _main
_main:
	li $v0, 5
	syscall
	add $t0, $0, $v0  # $t0 store n
	add $t1, $0, $0,  # $t1 is iterator
	add $t4, $0, $0,  # $t4 stores answer
	
judge:	slt $t2, $t0, $t1  # if $t0 <= $t1
	beq $t2, $0, loop
	j exit
loop:	andi $t3, $t1, 1   # $t3 is LSB
	bne $t3, $0, odd
	add $t4, $t4, $t1
odd:	addi $t1, $t1, 1   
	j judge
exit:
	add $a0, $0, $t4
	li $v0, 1
	syscall
	li $v0, 10
	syscall
	