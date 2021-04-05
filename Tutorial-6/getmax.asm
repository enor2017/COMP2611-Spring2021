# $s0: array starting address
# $s1: array size
# $s2: max, init as array[0]

# $t0: loop iterator i
# $t1: addr of array [i]
# $t2: content of array [i]

# max = array[0]
# for (int i = 1 ; i < size; i++)
#	if(array[i] > max)
#		max = array[i]

.text
.globl _main
_main:
	lw $s2, 0($s0)
	addi $t0, $0, 1
judge:	slt $t