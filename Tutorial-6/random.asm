.data

.text
.globl _main
_main:
li $v0, 30  # get system time
syscall

add $a1, $0, $a0  # put time to random seed
li $a0, 0  # use #0 generator
li $v0, 40
syscall

li $a0, 0
li $a1, 20  # generate [1,20], set upper bound = 20
li $v0, 42
syscall

addi $a0, $a0, 1  # [0,19] -> [1,20]

li $v0, 1  # print integer
syscall