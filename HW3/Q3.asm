.data
str:.byte 0:50
substr1: .byte 0:50
substr2: .byte 0:50

inputStrMsg:.asciiz "\nInput the string (with length at most 50): \n"
inputSubstr1Msg:.asciiz "Input the substring you want to replace: \n"
inputSubstr2Msg:.asciiz "Input the substring you want to replace with (in the same length): \n"
failMsg:.asciiz  "\nsubstring not found!"
successMsg: .asciiz "\nThe processed string is :"

.text
main:	
	la $s0, str  # s0 = address of target string
	la $s1, substr1     # s1 = address of substr1
	la $s2, substr2     # s2 = address of substr2

	# print input message
	li $v0,4
	la $a0, inputStrMsg
	syscall
	
        # getting the original/target string from user 
	add $a0, $s0, $zero
	li $a1, 50
	li $v0, 8
	syscall              
	
	li $v0,4
	la $a0, inputSubstr1Msg
	syscall
	
        # getting the substr1 from user 
	add $a0, $s1, $zero
	li $a1, 50
	li $v0, 8
	syscall               

	li $v0,4
	la $a0, inputSubstr2Msg
	syscall
	
	# getting the substr2 from user 
	add $a0, $s2, $zero
	li $a1, 50
	li $v0, 8
	syscall               
	

	add $a0,$s0,$zero #$a0 address of the target/original string	
	add $a1,$s1,$zero #$a1 the address of substr1
	add $a2,$s2,$zero #$a2 the address of substr1
	
	#pass $a0,$a1,$a2 to the function call
	#$a0 is address of the target/original string
	#$a1 is the address of substr1 (the substring to search for)
	#$a2 is the address of substr2 (the substring to replace substr1 with)
	jal findAndReplaceSubstring
	j end
	

####TODO BELOW
#inputs $a0:  address of the target/original string
#       $a1:  the address of substr1 (the substring to search for)
#       $a2:  the address of substr2 (the substring to replace substr1 with)
# hint: 1. you can call the strlen() to find the lengths of str, substr1 and substr2 as needed
#       2. you can use "lbu" to load a character
#       3. you can call replace() to do the substr replacement directly in str[], at the end just need to output str if replacement has been done
#You can add labels as you wish
#remember to preserve the save registers and the $ra according to slide 76 of https://course.cse.ust.hk/comp2611/note/COMP2611_ISA_Spring21.pdf       
findAndReplaceSubstring:
	addi $sp, $sp, -4	# push $sp into stack
	sw $ra, 0($sp)
	
	# push $s value into stack
	addi $sp, $sp, -32
	sw $s0, 0($sp)		# 0($sp) : $s0
	sw $s1, 4($sp)		# 4($sp) : $s1
	sw $s2, 8($sp)		# 8($sp) : $s2
	sw $s3, 12($sp)		# 12($sp) : $s3 
	sw $s4, 16($sp)		# 16($sp) : $s4
	sw $s5, 20($sp)		# 20($sp) : $s5
	sw $s6, 24($sp)		# 24($sp) : $s6
	sw $s7, 28($sp)		# 28($sp) : $s7
	
	add $s0, $a0, $0	# $s0 : addr(str)
	add $s1, $a1, $0	# $s1 : addr(substr1)
	add $s2, $a2, $0	# $s2 : addr(substr2)
	
	add $a0, $s0, $0
	jal strlen
	add $s3, $v0, $0 	# $s3 : length

	add $a0, $s1, $0
	jal strlen
	add $s4, $v0, $0	# $s4 : substrlength
	
	add $s5, $0, $0 	# $s5 : processed
	add $s6, $0, $0 	# $s6 : i
for_loop_i:
	beq $s6, $s3, exit_for_i
	
	lbu $t2, 0($s1)		# $t2 = substr1[0]
	add $t3, $s0, $s6	
	lbu $t3, 0($t3)		# $t3 = str[i]
	bne $t2, $t3, next_iterator_i	# if not equal, next iterator i.
	
	add $s7, $0, $0 	# $s7 : j
for_loop_j:
	beq $s7, $s4, exit_for_j
	
	# step3: no match
	add $t5, $s1, $s7 	# $t5 = addr(substr1[j])
	lbu $t5, 0($t5)		# $t5 = substr1[j]
	add $t6, $s0, $s6
	add $t6, $t6, $s7	# $t6 = addr(str[i+j])
	lbu $t6, 0($t6)		# $t6 = str[i+j]
	bne $t5, $t6, exit_for_j
	
	# step3: match and replace
	addi $t5, $s4, -1	# $t5 = substrlength -1
	bne $s7, $t5, next_iterator_j
	
	# prepare parameters
	add $a0, $s0, $0
	add $a1, $s6, $0
	add $a2, $s2, $0
	add $a3, $s4, $0
	jal replace
	
	add $s6, $s6, $s4
	addi $s6, $s6, -1	# i = i + substrlength -1
	
	addi $s5, $0, 1		# processed = true
	

next_iterator_j:
	addi $s7, $s7, 1	# j++
	j for_loop_j
	
exit_for_j:
next_iterator_i:
	addi $s6, $s6, 1	# i++
	j for_loop_i
	
exit_for_i:

	bne $s5, $0, print_processed
	j print_fail
print_processed:
	li $v0,4
	la $a0, successMsg
	syscall
	li $v0, 4
	add $a0, $s0, $0
	syscall
	j exit_print
	
print_fail:
	li $v0,4
	la $a0, failMsg
	syscall

exit_print:
	

	# restore $s from stack
	lw $s0, 0($sp)		
	lw $s1, 4($sp)		
	lw $s2, 8($sp)		
	lw $s3, 12($sp)		
	lw $s4, 16($sp)	
	lw $s5, 20($sp)
	lw $s6, 24($sp)	
	lw $s7, 28($sp)
	addi $sp, $sp, 32
	
	# pop $ra from stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
 	jr $ra        
###TODO above 	


 #input $a0: the address of the string
 #output $v0: the length of the string
 #this is the same strlen() function as in Q2
 #*you can copy and paste the same function from Q2 to here*
 #TODO BELOW
 #add labels as you wish
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
###TODO ABOVE
        
         	
 ### TODO BELOW
#inputs $a0: address of target string
#	$a1: index of where substr1 is found in the target string
#	$a2: address of substr2
#	$a3: length of substr2
#IMPORT! This function write directly to str[] updaing str[] by replacing substr1 starting at str[i] with substr2
#add labels as you wish
#remember to preserve the save registers and the $ra according to slide 76 of https://course.cse.ust.hk/comp2611/note/COMP2611_ISA_Spring21.pdf       
replace:
	add $t0, $0, $0 	# $t0 is iterator
for_judge:
	beq $t0, $a3, for_exit
	
	add $t1, $a2, $t0	# $t1 is addr(substr2[i])
	lbu $t2, 0($t1)		# $t2 is substr2[i]
	
	add $t3, $a0, $a1
	add $t3, $t3, $t0	# $t3 is addr(str[index+i])
	sb $t2, 0($t3)		
	
	addi $t0, $t0, 1	# $t0 ++
	j for_judge
for_exit:

	jr $ra
### TODO ABOVE
	
#do not remove this label!	
end:
