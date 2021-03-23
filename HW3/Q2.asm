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
	
	


