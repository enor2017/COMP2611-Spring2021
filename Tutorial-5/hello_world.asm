# Tutorial 5: Hello World Program
.data 
message: .asciiz "\nHello World!\n"  # .asciiz type data: String with terminator

.text 
.globl _main
_main:  # Codes start here
li $v0, 4  # load syscall code 4 to $v0 for print string
la $a0, message  # get the address of message, store into $a0 (argument of syscall)
syscall  # invoke syscall
li $v0, 10  # load syscall code 10 to exit program
syscall
 