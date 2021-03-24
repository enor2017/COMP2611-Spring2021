# Tutorial 5: add_sub
.data

.text
.globl _main
_main:
sub $t0, $t0, $t0  # t0 = 0
addi $t1, $zero, 1  # t1 = 1
addi $t2, $0, -2  # t2 = -2
add $t3, $t0, $t1  
add $t3, $t3, $t2  # t3 = t0 + t1 + t2 = -1