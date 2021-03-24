# Tutorial 5: logical operations
.data
.text
.globl _main
_main:
addi $s0, $0, 0xffff0000
addi $s1, $0, 0xaaaa1111
not $t0, $s0
and $t1, $s0, $s1
or $t2, $s0, $s1
nor $t3, $s0, $s1
xor $t4, $s0, $s1

# Expected Result:
# $t0 = 0x0000ffff
# $t1 = 0xaaaa0000
# $t2 = 0xffff1111
# $t3 = 0x0000eeee
# $t4 = 0x55551111