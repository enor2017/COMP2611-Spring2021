# Tutorial 5: data transfer
.data
fourbytes: .ascii "12AB"
fourwords: .word 1 -1 1024 -65536

.text
.globl _main
_main:
la $s0, fourbytes
lb $t0, 0($s0)  # load_byte, $t0 = '1' = 49
lb $t1, 2($s0)  # $t1 = 'A' = 65
lb $t2, 1($s0)  # $t2 = '2' = 50
lb $t3, 4($s0)  # out of range, $t3 = '1' = 1

la $s1, fourwords
lw $t0, 0($s1)  # $t0 = 1 = 0x00000001
lw $t1, 4($s1)  # $t1 = -1 = 0xffffffff
lw $t2, 8($s1)  # $t0 = 1024 = 0x00000400
lw $t3, 12($s1)  # $t1 = -65536 = 0xffff0000