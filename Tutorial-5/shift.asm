# Tutorial 5: logical shift
.data
.text
.globl _main
_main:
addi $t0, $0, 0xf0  # t0 = 0x000000f0
sll $t1, $t0, 4  # logical shift
srl $t2, $t0, 4

addi $t0, $0, 0xffff0000
srl $t1, $t0, 4  # logical shift, use zero extension
sra $t2, $t0, 4  # arithmetic shift, use sign extension

addi $t0, $0, 1
sll $t0, $t0, 3

addi $t0, $0, 0x12345678
addi $t1, $0, 0xf
sll $t1, $t1, 4
and $t1, $t0, $t1  # pick the '7' in $t0

