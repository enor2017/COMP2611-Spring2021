.data
inputMsg: .asciiz "Type the string (with length at most 50):\n"
trueMsg: .asciiz "True!\n"
falseMsg: .asciiz "False!\n"
string: .byte 0:50

.text
.globl main
main:
	la $s0, string
	
	# print input message
	li $v0, 4
	la $a0, inputMsg
	syscall
	
	#read the string
	li $v0, 8
	add $a0, $s0, $zero #string
	li $a1, 50 #length
	syscall
	add $a0, $s0, $zero
	jal strlen
	add $s1, $v0, $zero
	
	add $a0, $s0, $zero
	add $a1, $s1, $zero
	jal isPalindrome
	add $s2, $v0, $zero
	
	bgtz $s2, printTrue 
	j printFalse
	
 ### TODO BELOW
 #input a0: the address of the string
 #output v0: the length
 #hints: 1. assume the last character of the string is a '\n' (with ascii value of 10)
 #       2. you just need to count the number of characters before encountering '\n'
 #       3. you can use lbu/lb to load the characters from the "string" array, 
 #          the base address is already in $a0. 
 #You can add labels as you wish
#remember to preserve the save registers and the $ra according to slide 76 of https://course.cse.ust.hk/comp2611/note/COMP2611_ISA_Spring21.pdf       
strlen:
	addi $t0, $0, 10	# $t0 store 10 (ascii '\n')
	add $t1, $0, $0		# $t1 is iterator
loop:	
	add $t2, $a0, $t1	# $t2 is addr(str[i])
	lbu $t3, 0($t2)		# $t3 is str[i]
	beq $t3, $t0, loop_exit
	addi $t1, $t1, 1	# $t1 ++
	j loop
	
loop_exit:
	add $v0, $0, $t1	# $t1 is just the length
	
        jr $ra
### TODO ABOVE
	

### TODO BELOW
# input: a0: address of string
#	 a1: length of string
# output:v0: 1 if true, 0 if false
#hints: refer to the C++ program for the logic
#You can add labels as you wish
#remember to preserve the save registers and the $ra according to slide 76 of https://course.cse.ust.hk/comp2611/note/COMP2611_ISA_Spring21.pdf       
isPalindrome:
	add $t0, $0, $0		# $t0 is iterator
	srl $t1, $a1, 1		# $t1 is n/2
	
for_start:	
	beq $t0, $t1, for_exit
	
	add $t2, $a0, $t0	# $t2 is addr(str[i])
	lbu $t3, 0($t2)		# $t3 is str[i]
	
	add $t2, $a0, $a1
	addi $t2, $t2, -1
	sub $t2, $t2, $t0	# $t2 is addr(str[n - 1 - i])
	lbu $t4, 0($t2)		# $t4 is str[n - 1 - i]
	
	bne $t3, $t4, return_f	# if not equal, 'return_false'
	
	addi $t0, $t0, 1	# else, i++, goto 'for_start'
	j for_start

return_f:		
	add $v0, $0, $0		
	jr $ra
for_exit:
	addi $v0, $0, 1		# if reaches here, must be true
	jr $ra
### TODO ABOVE
	
	
printFalse:
	li $v0,4
	la $a0,falseMsg
	syscall
	j exit
printTrue:
	li $v0,4
	la $a0,trueMsg
	syscall
	j exit
exit:
	li $v0,10
	syscall
	
	


