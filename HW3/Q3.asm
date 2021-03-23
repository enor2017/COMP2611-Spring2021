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

	jr $ra
### TODO ABOVE
	
#do not remove this label!	
end:
