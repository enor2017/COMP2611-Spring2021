# I don't know whether this is correct >_<
##########################################
.data
.text
.globl _main
_main:
	add $s0, $0, $0	  # $s0 stores c
	j loop
judge:	slti $t0, $s0, 10
	bne $t0, $0 loop
	j exit
loop:	addi $s0, $s0, 2
	sll $s1, $s0, 2  # $s1 is addr of A[c]
	lw $t1, 0($s1)   # $t1 = A[c]
	sw $t1, -4($s1)  # A[c-1] = $t1
	j judge
exit:
 