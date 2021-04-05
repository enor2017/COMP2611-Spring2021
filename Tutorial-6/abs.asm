.data

.text
.globl _main
_main:
# get the abs value of $s0
addi $s0, $0, -2

slt $t1, $s0, $zero
bne $t1, $0, rev
j exit
rev: sub $s0, $0, $s0
exit:

li $v0, 1
add $a0, $0, $s0
syscall

li $v0, 10
syscall