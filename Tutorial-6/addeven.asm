.data

.text
.globl _main
_main:
# get the sum of even from 1 to $s0
	li $v0, 5
	syscall
	add $s0, $0, $v0
	
	addi $t0, $0, 2  # $t0 as iterator
	addi $t1, $0, 0  # $t1 to store result
for:	slt $t2, $s0, $t0  # $t0 > $s0, exit
	bne $t2, $0, exit
	add $t1, $t1, $t0
	addi $t0, $t0, 2
	j for
exit:	
	li $v0, 1
	add $a0, $0, $t1
	syscall
	
	li $v0, 10
	syscall
