# Add two numbers
# $t0: 1st number
# $t1: 2nd number
# $v0: syscall id and return value
# $a0: syscall argument

.text
.globl main

main:
li $v0, 5  # syscall code 5: read integer
syscall
add $t0, $zero, $v0

li $v0, 5  
syscall
add $t1, $zero, $v0

add $a0, $t0, $t1  # put sum into $a0, which is syscall's parameter

li $v0, 1  # syscall code 1: print integer
syscall

# don't forget to exit
li $v0, 10
syscall