# 2021 Spring COMP2611 Project: Battle City
# Name: LIU, Jianmeng
# SID: 20760163
# Email: jliudq@connect.ust.hk
##################################################################
###             This is bonus version with props.              ###
###  All codes related with props is commented with three '#'  ###
##################################################################

.data
# game setting
enemy_num: 		.word 	0	# the number of enemys
enemy_alive_num: 		.word 	0	# the number of alive enemys
input_enemy_num:	.asciiz	"Enter the number of enemys (in the range [1,2]): "
game_win_text: .asciiz	"You Win!"

enemy1_pos_num: .word 4 
enemy1_locs: .word 0 0
enemy1_alive: .word 1

enemy2_pos_num: .word 4
enemy2_locs: .word 384 0

enemy2_alive: .word 1

enemy_ids: .word 0 1


# movement
input_key:	.word 0 # input key from the user
move_iteration: .word 0 # remaining number of game iterations for last player movement    
initial_move_iteration: .word 8 # default number of game iterations for a player movement
move_key:	.word 0 # last processed key for a player movement
buffered_move_key: .word 0 # latest buffered movement input during an in-progress player movement

# player properties
player_id: 	.word 0 # id of player object is set to 0
player_locs:	.word -1:2 # initialized location of player object
player_speed:	.word 2
player_dir:   		.word 0 #player direction
remaining_bullet:   .word 1



# bullet
bullet_id:		.word 0
bullet_locs:		.word -1:2 
bullet_type:      .word 3
bullet_half_size:      .word 3 3 #1/2 of bullet's weight and height
bullet_dir:       .word 0 
bullet_speed:     .word 10
bullet_collision: .word 0 # set to 1 when enemy's bullet collides with players bullet


tank_explosion_id: .word 0


# size properties
player_size:	.word 32 32 # width and height of player object

maze_size:		.word 416 416 # width and height of the maze
grid_cell_size:	.word 16 16 # width and height of a grid cell
grid_row_num:	.word 26 # the number of rows in the grid of the maze
grid_col_num:	.word 26 # the number of columns in the grid of the maze

home_locs:      .word 192 384
broken_home_id: .word 0
game_over:  .word 0
maze_destroy:	.word -1:8			##### What's this?

game_over_locs: .word 0xb0 0xc0		##### And this?
game_win_locs: .word 0xb0 0xc0

### props locations
heart_locs: .word -1:2
invincible_locs: .word -1:2
speedup_locs: .word -1:2
extrabullet_locs: .word -1:2

# maze bit map
# 1:brick wall 2:stone -1:river 0:open path or grass (since both tanks and bullets can go through grass, we denote grass as 0)
maze_bitmap: .byte
0 0 0 0 1 1 0 0 0 0 1 1 0 0 0 0 0 0 1 1 0 0 0 0 0 0
0 0 0 0 1 1 0 0 0 0 1 1 0 0 1 1 0 0 1 1 0 0 0 0 0 0 
0 0 1 1 1 1 1 1 0 0 1 1 0 0 0 0 0 0 1 1 1 0 0 0 0 0 
0 0 1 1 1 1 1 1 0 0 1 1 0 0 2 2 0 0 1 1 1 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 1 1 0 0 1 1 0 0 1 1 0 0 0 1 1 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 1 1 0 
0 0 -1 -1 -1 -1 1 1 1 1 1 1 1 1 -1 -1 -1 -1 1 1 1 1 0 0 -1 -1
0 0 -1 -1 -1 -1 1 1 1 1 1 1 1 1 -1 -1 -1 -1 1 1 1 1 0 0 -1 -1
0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 1 1 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 1 1 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 2 2 2 2 
0 0 0 0 1 1 0 0 0 0 0 1 1 1 1 1 0 0 1 1 0 0 0 0 0 0 
1 1 1 1 0 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 0 0 0 0 1 1 
1 1 1 1 0 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 2 2 2 2 1 1 
0 0 0 0 0 0 2 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 2 2 0 0 2 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
-1 -1 -1 -1 0 0 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 0 0 1 1 1 1 -1 -1 -1 -1
-1 -1 -1 -1 0 0 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 0 0 1 1 1 1 -1 -1 -1 -1
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 1 0 0 0 0 1 1 1 1 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 1 1 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0 0 1 1 0 0 
0 0 0 0 1 1 0 0 1 0 0 0 0 0 0 1 0 0 2 2 1 1 1 1 0 0 
0 0 0 0 1 1 0 0 1 0 0 0 0 0 0 0 0 0 1 1 0 0 1 1 0 0 
0 0 2 2 1 1 0 0 1 0 0 1 1 1 1 0 0 0 0 0 0 0 1 1 0 0 
0 0 0 0 0 0 0 0 0 0 0 1 9 8 1 0 0 0 0 0 0 0 1 1 0 0 
0 0 0 0 0 0 0 0 0 0 0 1 8 8 1 0 0 0 1 1 0 0 0 0 0 0 



.text

main:	
	jal input_game_params
	la $t0, enemy_num
	sw $v0, 0($t0)
	la $t0, enemy_alive_num
	sw $v0, 0($t0)
	li $v0, 100 # create the screen
	syscall
	
	li $a0, 0
	li $a1, 0
	li $v0, 102
	
	# Initialize the game
	jal init_game

game_loop:	
	jal get_keyboard_input
	la $t0, game_over
	lw $t1, 0($t0)
	li $t0,1
	beq $t0,$t1,process_game_over

	la $t0,enemy_alive_num
	lw $t1,0($t0)
	beq $t1,$zero, process_game_win

game_tank_shoot:
	jal process_tank_shoot

game_move_user:
	jal process_move_input
	 
game_move_enemy:
	jal game_move_enemy1
	la $t0, enemy_num
	lw $t1, 0($t0)
	li $t2,1
	beq $t1, $t2,game_enemy_shoot
	jal game_move_enemy2

game_enemy_shoot:
	li $a0, 0
	jal enemy_shoot

	la $t0, enemy_num
	lw $t1, 0($t0)
	li $t2,1
	beq $t1, $t2,game_refresh
	li $a0, 1
	jal enemy_shoot



game_refresh: # refresh screen
	li $v0, 101
	syscall

	li $a0, 30 # iteration gap: 30 milliseconds
	jal have_a_nap
	j game_loop


process_game_over:
	li $v0, 103
	li $a0,0
	la $t0,game_over_locs
	lw $a1,0($t0)
	lw $a2,4($t0)
	li $a3,7
	syscall
	j game_refresh

process_game_win:
	li $v0, 105
	li $a0,0
	la $t0,game_win_locs
	lw $a1,0($t0)
	lw $a2,4($t0)
	la $a3,game_win_text
	syscall
	j game_refresh
#--------------------------------------------------------------------
# enemy_shoot
#--------------------------------------------------------------------

enemy_shoot:
	# call Enemyshot syscall
	li $a1,0 #$a0 and $a1 will be used for storing brick walls hit by enemy bullet
	li $v0, 112
	syscall
	li $t0,1
	beq $v0, $t0, es_gameover # $v0=1 means enemy bullet hits the player or the home and the player loses.
	li $t0,2
	beq $v0, $t0, es_bullet_crash # $v0=2 means enemy bullet hits the player's bullet and they both disappear.
	beq $v1, $zero, es_exit # $v1=1 or 2 denotes the number of brick walls the enemy bullet hits.
	la $t0, maze_bitmap # edit bitmap
	add $t0, $t0, $a0
	sb $zero, 0($t0)
	li $t0, 1
	beq $v1, $t0, es_exit 
	la $t0, maze_bitmap 
	add $t0, $t0, $a1
	sb $zero, 0($t0)
es_exit:
	jr $ra
es_gameover:
	la $t0, game_over
	li $t1,1
	sw $t1,0($t0)
	j es_exit
es_bullet_crash:
	la $t0, remaining_bullet
	lw $t1, 0($t0)
	addi $t1, $t1, 1
	sw $t1, 0($t0)
	j es_exit




#--------------------------------------------------------------------
# input_game_params
#--------------------------------------------------------------------
input_game_params:
	la $a0, input_enemy_num
	li $v0, 4
	syscall
	li $v0, 5
	syscall
	addi $t0, $v0, 0
	addi $v0, $t0, 0
	jr $ra

#--------------------------------------------------------------------
# procedure: init_game
# Initialize a new game:
# 1. end any last movement of the player object
# 2. create the player object: located at the point
# 3. create enemy_num enemy objects;
#    their locations are random on the paths of the game maze.
#--------------------------------------------------------------------
init_game:
	addi $sp, $sp, -12
	sw $ra, 8($sp)
	sw $s0, 4($sp)
	sw $s1, 0($sp)

	la $t0, move_iteration
	sw $zero, 0($t0) # reset any last movement of player
	la $t0, buffered_move_key
	sw $zero, 0($t0) # reset latest buffered movement input of player

ig_start:
	# create the player object
	li $v0, 103
	la $t0, player_id
	lw $a0, 0($t0) # the id of player object
	li $a1, 96 # initial place
	li $a2, 384
	li $a3, 1
	la $t0, player_locs
	sw $a1, 0($t0)
	sw $a2, 4($t0)
	syscall

	# create bullets
	li $v0, 103
	la $t0, bullet_id
	lw $a0, 0($t0) # the id of bullet object
	li $a1, 1000 # out of screen (hidden)
	li $a2, 1000
	li $a3, 3
	la $t0, player_id #id of the corresponding tank 
	lw $t1, 0($t0)
	la $t0, bullet_locs
	sw $a1, 0($t0)
	sw $a2, 4($t0)
	syscall

	# create the specified number of enemys
	la $t0, enemy_num
	lw $a0, 0($t0) # num of enemy objects

	la $t0, enemy_ids
	lw $a1, 0($t0)
	lw $a2, 4($t0)
	li $v0, 108
	syscall

	# create the broken home and hide
	la $t0, broken_home_id
	lw $a0, 0($t0)
	# la $t0, home_locs
	li $a1, 1000
	li $a2, 1000
	li $a3, 6
	li $v0, 103
	syscall

	### Randomly choose positions of props
	### Arrange each in a certain area:
	### - heart: left-top 1/4
	### - invincible: right-top 1/4
	### - speedup: left-bottom 1/4
	### - extrabullet: right-bottom 1/4
	### 26 x 26 cells, [0, 12], [13, 25]

	### Set random seed
	li $v0, 30  		### get system time
	syscall
	add $a1, $0, $a0  	### put time to random seed
	li $a0, 0  			### use #0 generator
	li $v0, 40
	syscall


	### randomly get a good position for prop: heart
generate_heart_pos:
	jal get_rand_coordinates
	la $t9, heart_locs
	sw $v0, 0($t9)
	sw $v1, 4($t9)		### store coordinates into array, though it maybe improper

	add $a0, $v0, $0
	add $a1, $v1, $0
	li $v0, 113			### call 113 to check if proper position
	syscall
	beq $v0, $0, generate_heart_pos

	### create prop: heart
	li $a0, 10
	la $t9, heart_locs
	lw $a1, 0($t9)
	### remember the random coordinates we get is cell coordinates
	### need to * 16
	sll $a1, $a1, 4
	lw $a2, 4($t9)
	sll $a2, $a2, 4
	li $a3, 10
	li $v0, 103
	syscall

	### invicible
generate_invicible_pos:
	jal get_rand_coordinates
	la $t9, invincible_locs
	addi $v0, $v0, 13	### put it in right-top 1/4
	sw $v0, 0($t9)
	sw $v1, 4($t9)		### store coordinates into array, though it maybe improper

	add $a0, $v0, $0
	add $a1, $v1, $0
	li $v0, 113			### call 113 to check if proper position
	syscall
	beq $v0, $0, generate_invicible_pos

	### create prop
	li $a0, 11
	la $t9, invincible_locs
	lw $a1, 0($t9)
	sll $a1, $a1, 4
	lw $a2, 4($t9)
	sll $a2, $a2, 4
	li $a3, 11
	li $v0, 103
	syscall

	### speedup
generate_speedup_pos:
	jal get_rand_coordinates
	la $t9, speedup_locs
	addi $v1, $v1, 13	### put it in left-bottom 1/4
	sw $v0, 0($t9)
	sw $v1, 4($t9)		### store coordinates into array, though it maybe improper

	add $a0, $v0, $0
	add $a1, $v1, $0
	li $v0, 113			### call 113 to check if proper position
	syscall
	beq $v0, $0, generate_speedup_pos

	### create prop
	li $a0, 12
	la $t9, speedup_locs
	lw $a1, 0($t9)
	sll $a1, $a1, 4
	lw $a2, 4($t9)
	sll $a2, $a2, 4
	li $a3, 12
	li $v0, 103
	syscall

generate_extrabullet_pos:
	jal get_rand_coordinates
	la $t9, extrabullet_locs
	addi $v0, $v0, 13
	addi $v1, $v1, 13
	sw $v0, 0($t9)
	sw $v1, 4($t9)		### store coordinates into array, though it maybe improper

	add $a0, $v0, $0
	add $a1, $v1, $0
	li $v0, 113			### call 113 to check if proper position
	syscall
	beq $v0, $0, generate_extrabullet_pos

	### create prop
	li $a0, 13
	la $t9, extrabullet_locs
	lw $a1, 0($t9)
	sll $a1, $a1, 4
	lw $a2, 4($t9)
	sll $a2, $a2, 4
	li $a3, 13
	li $v0, 103
	syscall

ig_exit:
	# li $a0, 30000 # iteration gap: 30 milliseconds
	# jal have_a_nap
	# j game_loop

	li $v0, 101 # refresh the screen
	syscall
	lw $ra, 8($sp)
	lw $s0, 4($sp)
	lw $s1, 0($sp)
	addi $sp, $sp, 12
	jr $ra

### procedure to get rand(x, y) both in [0, 12]
get_rand_coordinates:
	addi $sp, $sp, -4
	sw $ra, 0($sp)

	li $a0, 0
	li $a1, 13  		### generate [0,13), set upper bound = 13
	li $v0, 42
	syscall
	add $t0, $a0, $0	### store rand x in $t0
	li $a0, 0
	li $a1, 13  		
	li $v0, 42
	syscall
	add $t1, $a0, $0	### store rand y in $t1

	### store in $v0, $v1
	add $v0, $t0, $0
	add $v1, $t1, $0

	### return
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra

#--------------------------------------------------------------------
# procedure: have_a_nap(nap_time)
#--------------------------------------------------------------------
have_a_nap:
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	li $v0, 32 # syscall: let mars java thread sleep $a0 milliseconds
	syscall

	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra

#--------------------------------------------------------------------
# procedure: get_keyboard_input
# If an input is available, save its ASCII value in the array input_key,
# otherwise save the value 0 in input_key.
#--------------------------------------------------------------------
get_keyboard_input:
	add $t2, $zero, $zero
	lui $t0, 0xFFFF
	lw $t1, 0($t0)
	andi $t1, $t1, 1
	beq $t1, $zero, gki_exit
	lw $t2, 4($t0)

gki_exit:	
	la $t0, input_key 
	sw $t2, 0($t0) # save input key
	jr $ra

#--------------------------------------------------------------------
# procedure: game_move_enemy1
#--------------------------------------------------------------------
game_move_enemy1: 
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	la $t0, enemy1_alive
	lw $t1, 0($t0)
	beq $t1, $zero,gme1_exit

gme1_move:
	la $t0,enemy_ids
	lw $a0, 0($t0)
	li $v0, 109 
	syscall
	la $t0,enemy1_locs # ($v0,$v1) is the up-to-date enemy location.
	sw $v0, 0($t0)
	sw $v1, 4($t0)

gme1_exit:
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra

#--------------------------------------------------------------------
# procedure: game_move_enemy2
#--------------------------------------------------------------------
game_move_enemy2: 
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	la $t0, enemy2_alive
	lw $t1, 0($t0)
	beq $t1, $zero,gme2_exit

gme2_move:
	la $t0,enemy_ids
	lw $a0, 4($t0)
	li $v0, 109 
	syscall
	la $t0,enemy2_locs
	sw $v0, 0($t0)
	sw $v1, 4($t0)

gme2_exit:
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra



#--------------------------------------------------------------------
# procedure: process_tank_shoot
#--------------------------------------------------------------------

process_tank_shoot:
# The player tank have 1 bullet only, so it can only shoot bullet after the previous shot bullet 
# hit something (brick, stone, enemy tanks or borders of the map).
	addi $sp, $sp, -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	la $t0, remaining_bullet 
	lw $t1, 0($t0)
	beq $t1, $zero, pts_bullet_move# If there is no remaining bullet, move the current bullet forward
	j pts_new_bullet # else shoot a new bullet

pts_bullet_move:
	jal bullet_move
	j pts_check_bullet_collision

pts_new_bullet:
	la $t0, input_key
	lw $t1, 0($t0)
	addi, $t2, $zero, 32
	bne $t1, $t2, pts_exit # If the input_key is not space key, exit.
	jal new_bullet

pts_check_bullet_collision:
	addi $a0,$v0,0 # ($v0,$v1) is the updated bullet location.
	addi $a1,$v1,0
	la $t0,bullet_half_size
	lw $a2,0($t0)
	jal check_bullet_collision

pts_exit:
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	lw $s3, 16($sp)
	lw $s4, 20($sp)
	addi $sp, $sp, 24
	jr $ra


#--------------------------------------------------------------------
# procedure: new_bullet
#--------------------------------------------------------------------
new_bullet:	
	addi $sp, $sp, -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)

	

	#shoot sound
	li $v0,102
	li $a0,4
	li $a1,0
	syscall

	la $t0, remaining_bullet #set the remaining_bullet=0
	sw $zero, 0($t0)

	la $t0, bullet_id
	lw $s0, 0($t0)

	la $t0, player_locs
	lw $s1, 0($t0) # tank location
	lw $s2, 4($t0)
	addi $s1,$s1, 16
	addi $s2,$s2, 16 #tank midpoint

	la $t0, bullet_half_size
	lw $s3, 0($t0)


	la $t0, player_dir #let bullet_dir=player_dir
	lw $t4, 0($t0)

	la $t0, bullet_dir
	sw $t4, 0($t0)

	li $v0, 107  #set bullet direction
	add $a0, $s0, $zero
	li $a1, 3 #bullet type
	add $a2, $t4, $zero
	syscall



	addi $t5, $zero, 0
	beq $t4, $t5, nb_up_bullet

	addi $t5, $zero, 1
	beq $t4, $t5, nb_left_bullet

	addi $t5, $zero, 2
	beq $t4, $t5, nb_down_bullet

	addi $t5, $zero, 3
	beq $t4, $t5, nb_right_bullet
	j nb_exit

nb_up_bullet:
	sub $s1, $s1, $s3
	sub $s2, $s2, $s3
	sub $s2, $s2, $s3
	subi $s2, $s2, 16
	la $t0, bullet_locs
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1, 0
	addi $v1, $s2, 0
	j nb_exit


# Task4: complete nb_down_bullet and nb_left_bullet
# Hint: 1. Calculate the location of the top-left corner of the bullet and store into bullet_locs
# 2. Set ($v0,$v1) the location
# 3. You can refer to nb_up_bullet and nb_right_bullet
#*** Your code starts here

nb_down_bullet:
	sub $s1, $s1, $s3
	add $s2, $s2, $s3
	add $s2, $s2, $s3
	addi $s2, $s2, 16
	la $t0, bullet_locs
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1, 0
	addi $v1, $s2, 0
	j nb_exit
	

nb_left_bullet:
	add $s2, $s2, $s3
	addi $s2, $s2, -6	# since (x,y) is left-top coordinate, need to minus bullet size
	addi $s1, $s1, -16
	la $t0, bullet_locs
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1, 0
	addi $v1, $s2, 0
	j nb_exit

#*** Your code ends here	

nb_right_bullet:
	sub $s2, $s2, $s3
	addi $s1, $s1, 16
	la $t0, bullet_locs
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1, 0
	addi $v1, $s2, 0
	j nb_exit

nb_exit:
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	lw $s3, 16($sp)
	lw $s4, 20($sp)
	addi $sp, $sp, 24
	jr $ra



#--------------------------------------------------------------------
# procedure: bullet_move
# Since the current bullet is on the fly, move it foward
#--------------------------------------------------------------------
bullet_move: 
	addi $sp, $sp, -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)

	# get bullet_locs, bullet_speed and bullet_dir
	la $t0, bullet_locs
	lw $s1, 0($t0)
	lw $s2, 4($t0)
	la $t0, bullet_speed
	lw $a3, 0($t0)

	la $t0, bullet_dir
	lw $t4, 0($t0)

	addi $t5, $zero, 0

	beq $t4, $t5, bm_up_move

	addi $t5, $zero, 1
	beq $t4, $t5, bm_left_move

	addi $t5, $zero, 2
	beq $t4, $t5, bm_down_move

	addi $t5, $zero, 3
	beq $t4, $t5, bm_right_move
	j pts_exit

bm_up_move:
	sub $s2, $s2, $a3 # reduce y 
	la $t0, bullet_locs # update the coordinates
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1,0  # set ($v0,$v1) the updated coordinates
	addi $v1, $s2,0
	j bm_exit
bm_down_move:
	add $s2, $s2, $a3
	la $t0, bullet_locs
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1,0
	addi $v1, $s2,0
	j bm_exit
bm_left_move:
	sub $s1, $s1, $a3
	la $t0, bullet_locs
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1,0
	addi $v1, $s2,0
	j bm_exit
bm_right_move:
	add $s1, $s1, $a3
	la $t0, bullet_locs
	sw $s1, 0($t0)
	sw $s2, 4($t0)
	addi $v0, $s1,0
	addi $v1, $s2,0
	j bm_exit

bm_exit:
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	lw $s3, 16($sp)
	lw $s4, 20($sp)
	addi $sp, $sp, 24
	jr $ra

#--------------------------------------------------------------------
# procedure: check_bullet_collision(x,y,half_size)
# Check whether the bullet collides with border, enemys, brick wall, stone or home
# collision type: 0:no collision; 1:brick wall; 2:border&stone; 4:enemy1; 5:enemy2; 8 or 9: home
#--------------------------------------------------------------------

check_bullet_collision:
	addi $sp, $sp, -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	addi $s1,$a0,0
	addi $s2,$a1,0
	addi $s3,$a2,0
	add $a2,$a2,$a2 #pass the full size
	jal hit_border
	li $a0, 2 #collision type
	beq $v0, $zero, cbc_top_left
	jal process_collision

# check 4 corners of the bullet respectively
cbc_top_left:
	addi $a0, $s1, 0 
	addi $a1, $s2, 0
	jal check_hit_enemy
	add $a0,$v0,$zero
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_top_left_grid
	jal process_collision
cbc_top_left_grid:
	addi $a0, $s1, 0 
	addi $a1, $s2, 0
	jal get_bitmap_cell
	add $a0,$v0,$zero
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_top_right
	addi $a1, $v1, 0
	jal process_collision

# Task5: Complete cbc_top_right and cbc_bottom_left
# Hints: 1. calculate the coordinates of the corner as ($a0,$a1) and call check_hit_enemy
# 2. If it hits enemy, do $a0=$v0 and call process_collision
# 3. If it does not hit enemy, use get_bitmap_cell to see wether it hits brick wall, stone or home
# 4. get_bitmap_cell will return the location where the collision happens (the index of the bitmap cell)
#*** Your code starts here
cbc_top_right:
	add $a0, $s1, $s3
	add $a0, $a0, $s3	
	add $a1, $s2, $0
	# addi $a1, $a1, -1 This line is removed
	jal check_hit_enemy
	add $a0,$v0,$zero
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_top_right_grid
	jal process_collision
cbc_top_right_grid:
	add $a0, $s1, $s3
	add $a0, $a0, $s3	
	addi $a0, $a0, -1 
	add $a1, $s2, $0
	jal get_bitmap_cell
	add $a0,$v0,$zero
	
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_bottom_left

	addi $a1, $v1,0
	jal process_collision

#*** Your code ends here

cbc_bottom_left:
	add $a0, $s1, $zero 	
	add $a1, $s2, $s3
	add $a1, $a1, $s3
	# addi $a1, $a1, -1 This line is removed
	jal check_hit_enemy
	add $a0,$v0,$zero
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_bottom_left_grid
	jal process_collision
cbc_bottom_left_grid:
	add $a0, $s1, $zero 	
	add $a1, $s2, $s3
	add $a1, $a1, $s3
	addi $a1, $a1, -1 
	jal get_bitmap_cell
	add $a0,$v0,$zero
	
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_bottom_right

	addi $a1, $v1,0
	jal process_collision

cbc_bottom_right:
	add $a0, $s1, $s3 
	add $a0, $a0, $s3 
	# addi $a0, $a0, -1 This line is removed
	add $a1, $s2, $s3
	add $a1, $a1, $s3
	# addi $a1, $a1, -1 This line is removed
	jal check_hit_enemy
	add $a0,$v0,$zero
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_bottom_right_grid
	jal process_collision
cbc_bottom_right_grid:
	add $a0, $s1, $s3 
	add $a0, $a0, $s3 
	addi $a0, $a0, -1 	
	add $a1, $s2, $s3
	add $a1, $a1, $s3
	addi $a1, $a1, -1

	jal get_bitmap_cell
	addi $a0,$v0,0
	
	slt $v0, $zero, $v0 
	beq $v0, $zero, cbc_end
	addi $a1, $v1,0
	jal process_collision

cbc_end:
	la $t0,bullet_collision
	lw $t1,0($t0)
	sw $zero, 0($t0) # set bullet_collision to 0 after each check
	beq $t1, $zero, cbc_no_collision #process_collision will set bullet_collision to 1, so if bullet_collision is 0 means no collision happened


	la $t0, remaining_bullet # set remaining_bullet to 1 if collision happened
	li $t1,  1
	sw $t1, 0($t0)

	la $t0, bullet_id
	lw $a0, 0($t0)
	li $a3, 3
	li $a1, 1000  # hide the bullet
	li $a2, 1000
	li $v0, 104
	syscall  
	j cbc_exit

cbc_no_collision:
	la $t0, bullet_id
	lw $a0, 0($t0)
	add $a1, $s1, $zero
	add $a2, $s2, $zero
	li $a3, 3
	li $v0, 104 #set bullet location 
	syscall
	j cbc_exit

cbc_exit:
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	lw $s3, 16($sp)
	lw $s4, 20($sp)
	addi $sp, $sp, 24
	jr $ra


#--------------------------------------------------------------------
# procedure: check_hit_enemy(x,y)
# Check whether the bullet collides with enemys
# (x,y) is one corner of the bullet
# If the bullet collides with enemy1, set $v0=4 and return
# If the bullet collides with enemy2, set $v0=5 and return
#--------------------------------------------------------------------


check_hit_enemy:
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	li $v0,0
che_enemy1:
# Task6:  you need to complete this procedure check_hit_enemy to perform its operations as described in comments above. 
# Hints: 1. check wether the enemy is alive.
# 2. if it's alive get enemy1_locs (x0,y0)
# 3. calculate the y1=y0+32 and x1=x0+32
# 4. Only if x0<x<x1 and y0<y<y1, return $v0=4 or return $v0=0
#*** Your code starts here
	la $t0, enemy1_alive
	lw $t1, 0($t0)
	beq $t1, $zero, che_enemy2 	# if enemy_1 not alive, check 2
	la $t0, enemy1_locs
	lw $t1, 0($t0)	# $t1 : x0
	lw $t2, 4($t0)	# $t2 : y0

	addi $t3, $t1, 32	# $t3 : x_1
	addi $t4, $t2, 32	# $t4 : y_1

	slt $t5, $t1, $a0
	beq $t5, $0, che_enemy2		# if x0 >= x, not satisfied, check 2
	slt $t5, $a0, $t3
	beq $t5, $0, che_enemy2	
	slt $t5, $t2, $a1
	beq $t5, $0, che_enemy2	
	slt $t5, $a1, $t4
	beq $t5, $0, che_enemy2	
	
	li $v0, 4			# reaching here means collides, return 4.
	j che_exit

che_enemy2:
# similar to che_enemy1
	la $t0, enemy2_alive
	lw $t1, 0($t0)
	beq $t1, $zero, che_no_collide 	# if enemy_2 not alive, no_collide
	la $t0, enemy2_locs
	lw $t1, 0($t0)	# $t1 : x0
	lw $t2, 4($t0)	# $t2 : y0

	addi $t3, $t1, 32	# $t3 : x_1
	addi $t4, $t2, 32	# $t4 : y_1

	slt $t5, $t1, $a0
	beq $t5, $0, che_no_collide		# if x0 >= x, not satisfied, no_collide
	slt $t5, $a0, $t3
	beq $t5, $0, che_no_collide	
	slt $t5, $t2, $a1
	beq $t5, $0, che_no_collide	
	slt $t5, $a1, $t4
	beq $t5, $0, che_no_collide	
	
	li $v0, 5			# reaching here means collides, return 5.
	j che_exit

che_no_collide:
	li $v0, 0

#*** Your code ends here
che_exit:
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra

#--------------------------------------------------------------------
# procedure: process_collision(type,index)
# process bullet collision
# Only when type is 1, we need the index parameter which denotes the index of the bitmap cell where the collision happened
#--------------------------------------------------------------------
process_collision: 
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	la $t0, bullet_collision # set bullet_collision to 1
	li $t1,1
	sw $t1, 0($t0)
	# swtich case
	li $t1,1
	beq $a0,$t1,pc_hit_brick
	li $t1,4
	beq $a0,$t1,pc_hit_enemy1
	li $t1,5
	beq $a0,$t1,pc_hit_enemy2
	li $t1,8
	beq $a0,$t1,pc_hit_home
	li $t1,9
	beq $a0,$t1,pc_hit_home

	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra




pc_hit_brick:
	la $t0, maze_bitmap
	add $t0,$t0,$a1
	sb $zero, 0($t0) # edit bitmap cell to 0 (open path)
	addi $a0, $a1,0
	li $v0, 110 
	syscall
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra

pc_hit_enemy1:
	li $v0,102
	li $a0,2
	li $a1,0
	syscall

	li $a0,0  #get enemy1 locs
	li $v0,106
	syscall

	la $t0, tank_explosion_id
	lw $a0, 0($t0)
	addi $t1,$a0,1
	sw $t1,0($t0)

	addi $a1 ,$v0,0
	addi $a2,$v1,0
	li $a3, 5
	li $v0,103  #create tank explosion object
	syscall


	li $a0,0
	li $v0,111
	syscall
	la $t0, enemy1_alive # set enemy1_alive to 0
	sw $zero, 0($t0)
	la $t0, enemy_alive_num
	lw $t1,0($t0)
	subi $t1,$t1,1
	sw $t1,0($t0)

	lw $ra, 0($sp)
	addi $sp,$sp,4
	jr $ra


pc_hit_enemy2:
	li $v0,102
	li $a0,4
	li $a1,0
	syscall

	li $a0,1  #get enemy2 locs
	li $v0,106
	syscall

	la $t0, tank_explosion_id
	lw $a0, 0($t0)
	addi $t1,$a0,1
	sw $t1,0($t0)

	addi $a1 ,$v0,0
	addi $a2,$v1,0
	li $a3, 5
	li $v0,103  
	syscall

	li $a0,1
	li $v0,111
	syscall
	la $t0, enemy2_alive
	sw $zero, 0($t0)
	la $t0, enemy_alive_num
	lw $t1,0($t0)
	subi $t1,$t1,1
	sw $t1,0($t0)
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra

pc_hit_home:
	la $t0, broken_home_id
	lw $a0, 0($t0)
	la $t0, home_locs
	lw $a1, 0($t0)
	lw $a2, 4($t0)
	li $a3, 6
	li $v0, 104 # move broken home object to the location of home
	syscall
	la $t0,game_over # set game_over to 1
	li $t1,1
	sw $t1,0($t0)
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra





#--------------------------------------------------------------------
# procedure: process_move_input
# Continue any last in-progress movement repesented by move_key, and 
# save any latest movement input key during that process to the
# buffer buffered_move_key.
# If no in-progress movement, perform the action of the new keyboard
# input input_key if it is a valid movement input for the player object,
# otherwise perform the action of any buffered movement input key
# if it is a valid movement input.
# If an input is processed but it cannot actually move the player
# object (e.g. due to a wall), no more movements will be made in later
# iterations for this input. 
#--------------------------------------------------------------------
process_move_input:	
	addi $sp, $sp, -4
	sw $ra, 0($sp)

	la $t6, move_iteration
	lw $t5, 0($t6) # remaining number of game iterations for last movement
	bne $t5, $zero, pmi_last_move # last movement is not completed, so process it
	la $t0, input_key 
	lw $t1, 0($t0) # new input key
	la $t0, initial_move_iteration
	lw $t2, 0($t0)
	addi $t2, $t2, -1 # count this game iteration for any new movement
	sw $t2, 0($t6) # first assume new input key is valid
	la $t8, move_key 
	sw $t1, 0($t8) # save new input key in case it is a movement key
	j pmi_check_buffer

pmi_last_move:
	la $t0, input_key 
	lw $t7, 0($t0) # new input key
	li $t0, 119 # corresponds to key 'w'
	beq $t7, $t0, pmi_buffer  
	li $t0, 115 # corresponds to key 's'
	beq $t7, $t0, pmi_buffer
	li $t0, 97 # corresponds to key 'a'
	beq $t7, $t0, pmi_buffer
	li $t0, 100 # corresponds to key 'd'
	beq $t7, $t0, pmi_buffer
	j pmi_start_move

pmi_buffer:
	la $t0, buffered_move_key
	sw $t7, 0($t0) # buffer latest movement input of player during the in-progress movement

pmi_start_move:
	addi $t5, $t5, -1 # process last movement for one more game iteration
	sw $t5, 0($t6)
	la $t0, move_key 
	lw $t1, 0($t0) # last movement key
	li $a0, 0 # no needs to check again whether this movement can actually move the object 
	j pmi_check

pmi_check_buffer:
	li $a0, 1 # check whether this movement can actually move the player object
	la $t0, buffered_move_key
	lw $t9, 0($t0) # check whether buffered movement input is valid
	sw $zero, 0($t0) # reset buffer
	li $t0, 119 # corresponds to key 'w'
	beq $t1, $t0, pmi_move_up  
	li $t0, 115 # corresponds to key 's'
	beq $t1, $t0, pmi_move_down
	li $t0, 97 # corresponds to key 'a'
	beq $t1, $t0, pmi_move_left
	li $t0, 100 # corresponds to key 'd'
	beq $t1, $t0, pmi_move_right
	sw $t9, 0($t8) # save buffered input key in case it is a movement key
	addi $t1, $t9, 0

pmi_check:
	li $t0, 119 # corresponds to key 'w'
	beq $t1, $t0, pmi_move_up  
	li $t0, 115 # corresponds to key 's'
	beq $t1, $t0, pmi_move_down
	li $t0, 97 # corresponds to key 'a'
	beq $t1, $t0, pmi_move_left
	li $t0, 100 # corresponds to key 'd'
	beq $t1, $t0, pmi_move_right
	sw $zero, 0($t6) # above assumption of new input key or buffered key being valid is wrong
	j pmi_exit

pmi_move_up: 
	la $t0, player_dir
	addi $t1, $zero, 0
	sw $t1, 0($t0)
	jal pmi_change_dir
	jal move_player_up
	j pmi_after_move

pmi_move_down:
	la $t0, player_dir
	addi $t1, $zero, 2
	sw $t1, 0($t0)
	jal pmi_change_dir
	jal move_player_down
	j pmi_after_move

pmi_move_left:
	la $t0, player_dir
	addi $t1, $zero, 1
	sw $t1, 0($t0)
	jal pmi_change_dir
	jal move_player_left
	j pmi_after_move

pmi_move_right:
	la $t0, player_dir
	addi $t1, $zero, 3
	sw $t1, 0($t0)
	jal pmi_change_dir
	jal move_player_right		

pmi_after_move:
	bne $v0, $zero, pmi_exit # actual movement has been made
	la $t6, move_iteration
	sw $zero, 0($t6) # current movement is blocked by a wall, so no more movements in later iterations for the move_key
	j pmi_exit

pmi_exit: 
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra

pmi_change_dir:
	li $v0, 107
	la $t0, player_id
	lw $a0, 0($t0)
	li $a1, 1
	la $t0, player_dir
	lw $a2, 0($t0)
	syscall
	jr $ra

#--------------------------------------------------------------------
# procedure: hit_border(x,y,size)
# Check wether an object hits the border of the maze
# We assume the object is a square (width=height)
# (x,y) is the coordinates of the top left corner of the object
# $v0=1 if the object hit the border 
# $v0=0 otherwise (Notice that if the object is tangent to the border $v0 should also be set to 0)
#--------------------------------------------------------------------	
# *****Task3: you need to complete this procedure hit_border to perform its operations as described in comments above. 
# Hints: 1. check wether the upper border of the object has y<0 
# 2. check wether the lower border of the object has y> maze_size
# 3. check wether the left border of the object has x<0
# 4. check wether the right border of the object has x>maze_size
# 5. If either of obove conditions is satisfied, set $v0=1. Otherwise, set $v0=0.

hit_border:
#*** Your code starts here
		la $t0, maze_size
		lw $t1, 0($t0) 	# maze width
		lw $t2, 4($t0) 	# maze height

		slt $t3, $a1, $0  	# if y < 0, $t3 = 1
		bne $t3, $0, hb_hit

		add $t4, $a1, $a2	# y-cor of right-bottom
		slt $t3, $t2, $t4 	# if height < y , $t3 = 1
		bne $t3, $0, hb_hit

		slt $t3, $a0, $0	# if x < 0, $t3 = 1
		bne $t3, $0, hb_hit

		add $t4, $a0, $a2	# x-cor of right-bottom
		slt $t3, $t1, $t4 	# if width < x , $t3 = 1
		bne $t3, $0, hb_hit

		li $v0, 0
		j hb_exit

hb_hit:
		li $v0, 1

hb_exit:

#*** Your code ends here
	
	jr $ra
#--------------------------------------------------------------------
# procedure: move_player_up()
# Move the player object upward by one step which is its speed.
# Move the object only when the object will not overlap with a wall cell afterward. 
# $v0=1 if a movement has been made, otherwise $v0=0.
#--------------------------------------------------------------------	

move_player_up:
		addi $sp, $sp, -24
		sw $ra, 0($sp)
		sw $s0, 4($sp)
		sw $s1, 8($sp)
		sw $s2, 12($sp)
		sw $s3, 16($sp)
		sw $s4, 20($sp)

		la $t0, player_size
		lw $s3, 0($t0) # player width	
		lw $s4, 4($t0) # player height	
		la $t0, maze_size
		lw $t2, 4($t0) # maze height	

		la $t0, player_speed
		lw $t3, 0($t0) # player speed
		la $s2, player_locs
		lw $s0, 0($s2) # x_loc
		lw $s1, 4($s2) # y_loc
		add $t9, $s1, $zero
		addi $t9, $t9, -1 # y-coordinate of player's bottom corners		
		slt $t4, $t9, $zero # y-coordinate of upper-border is 0
		beq $t4, $zero, mpu_check_path1 
		
		j mpu_no_move

mpu_check_path1:	
		# check whether player's top-left corner is in a wall 
		sub $s1, $s1, $t3 # new y_loc
		addi $a0, $s0, 0
		addi $a1, $s1, 0
		jal get_bitmap_cell
		# slt $v0, $zero, $v0 
		bne $v0, $zero, mpu_no_move 

mpu_check_path2:
		# check whether player's top-left corner is in a wall 
		# Many student ask about why $a0=$s0+16 not $a0=$s0+32
		# Since the tank coordinates are always multiple of 16, add 16 is to check whther the right half of the player collides with 16-wide any object.
		addi $a0, $s0, 16
		addi $a1, $s1, 0
		jal get_bitmap_cell
		# slt $v0, $zero, $v0 
		bne $v0, $zero, mpu_no_move  

mpu_save_yloc:	sw $s1, 4($s2) # save new y_loc
		la $t0, player_id
		lw $a0, 0($t0)
		addi $a1, $s0, 0
		addi $a2, $s1, 0
		li $a3, 1 # object type
		li $v0, 104
		syscall # set new object location
		li $v0, 1
		j mpu_exit
	
mpu_no_move:	li $v0, 0		 
mpu_exit:	lw $ra, 0($sp)
		lw $s0, 4($sp)
		lw $s1, 8($sp)
		lw $s2, 12($sp)
		lw $s3, 16($sp)
		lw $s4, 20($sp)
		addi $sp, $sp, 24
		jr $ra
#--------------------------------------------------------------------
# procedure: move_player_down()
# Move the player object downward by one step which is its speed.
# Move the object only when the object will not overlap with a wall cell afterward. 
# $v0=1 if a movement has been made, otherwise $v0=0.
#--------------------------------------------------------------------	
move_player_down:
		addi $sp, $sp, -24
		sw $ra, 0($sp)
		sw $s0, 4($sp)
		sw $s1, 8($sp)
		sw $s2, 12($sp)
		sw $s3, 16($sp)
		sw $s4, 20($sp)

		la $t0, player_size
		lw $s3, 0($t0) # player width	
		lw $s4, 4($t0) # player height	
		la $t0, maze_size
		lw $t2, 4($t0) # maze height	

		la $t0, player_speed
		lw $t3, 0($t0) # player speed
		la $s2, player_locs
		lw $s0, 0($s2) # x_loc
		lw $s1, 4($s2) # y_loc
		

		addi $t2, $t2, -1 # y-coordinate of lower-border is (height - 1)
		add $t9, $s1, $s4
		slt $t4, $t2, $t9
		beq $t4, $zero, mpd_check_path1 
		# li $s1, 0
		# j mbd_save_yloc
		j mpd_no_move

mpd_check_path1:	
		# check whether player's bottom-left corner is in a wall
		add $s1, $s1, $t3 # new y_loc
		addi $a0, $s0, 0
		add $a1, $s1, $s4
		addi $a1, $a1, -1 # y-coordinate of player's bottom corners	
		jal get_bitmap_cell
		# slt $v0, $zero, $v0 
		bne $v0, $zero, mpd_no_move  

mpd_check_path2:	
		# check whether player's bottom-left corner is in a wall
		addi $a0, $s0, 16
		add $a1, $s1, $s4
		addi $a1, $a1, -1 # y-coordinate of player's bottom corners	
		jal get_bitmap_cell
		# slt $v0, $zero, $v0 
		bne $v0, $zero, mpd_no_move  

mpd_save_yloc:	sw $s1, 4($s2) # save new y_loc
		la $t0, player_id
		lw $a0, 0($t0)
		addi $a1, $s0, 0
		addi $a2, $s1, 0
		li $a3, 1 # object type
		li $v0, 104
		syscall # set new object location
		li $v0, 1
		j mpd_exit
	
mpd_no_move:	li $v0, 0				 
mpd_exit:	lw $ra, 0($sp)
		lw $s0, 4($sp)
		lw $s1, 8($sp)
		lw $s2, 12($sp)
		lw $s3, 16($sp)
		lw $s4, 20($sp)
		addi $sp, $sp, 24
		jr $ra


#--------------------------------------------------------------------
# procedure: move_player_left()
# Move the player object leftward by one step which is its speed.
# Move the object only when the object will not overlap with a wall cell afterward. 
# $v0=1 if a movement has been made, otherwise $v0=0.
#--------------------------------------------------------------------	
move_player_left:

# *****Task1: you need to complete this procedure move_player_left to perform its operations as described in comments above. 
# Hints:
# Firstly, preserve values $ra, $s0, $s1, $s2, $s3, $s4 with stack
# Then, use the registers as described below:
# 		The x_loc of the player object is in $s0
# 		The y_loc of the player object is in $s1
# 		The address of the player obejct is in $s2
# 		The height of the player object is in $s3
# 		The width of the player object is in $s4 
# Calculate new x_loc of the player object.
# Check whether player's top-left or bottom-left corner is in a wall: 
#		If it is in a wall, then the player can't move
# 		If it is not, then save and set the new x_loc for the player object
# Set $v0=1 if a movement has been made, 0 otherwise
# Lastly, pop and restore values in $ra, $s0, $s1, $s2, $s3, $s4  and return
# Hint: you can refer to move_player_up and move_player_down to get some clues
# *****Your codes start here

		addi $sp, $sp, -24
		sw $ra, 0($sp)
		sw $s0, 4($sp)
		sw $s1, 8($sp)
		sw $s2, 12($sp)
		sw $s3, 16($sp)
		sw $s4, 20($sp)

		la $t0, player_size
		lw $s4, 0($t0) # player width	
		lw $s3, 4($t0) # player height	
		la $t0, maze_size
		lw $t2, 4($t0) # maze height	

		la $t0, player_speed
		lw $t3, 0($t0) # player speed
		la $s2, player_locs
		lw $s0, 0($s2) # x_loc
		lw $s1, 4($s2) # y_loc
		add $t9, $s0, $zero
		addi $t9, $t9, -1 # check if (x-coordinate - 1) out of bound		
		slt $t4, $t9, $zero 
		beq $t4, $zero, mpl_check_path1 
		
		j mpl_no_move

mpl_check_path1:	
		# check whether player's top-left corner is in a wall 
		sub $s0, $s0, $t3 # new x_loc
		addi $a0, $s0, 0
		addi $a1, $s1, 0
		jal get_bitmap_cell
		bne $v0, $zero, mpl_no_move 

mpl_check_path2:
		# check whether player's left-center coordinate overlap with others
		add $a0, $s0, $0
		addi $a1, $s1, 16
		jal get_bitmap_cell
		bne $v0, $zero, mpl_no_move  

mpl_save_xloc:	sw $s0, 0($s2) # save new x_loc
				la $t0, player_id
				lw $a0, 0($t0)
				addi $a1, $s0, 0
				addi $a2, $s1, 0
				li $a3, 1 # object type
				li $v0, 104
				syscall # set new object location
				li $v0, 1
				j mpl_exit
	
mpl_no_move:	li $v0, 0		 
mpl_exit:	lw $ra, 0($sp)
			lw $s0, 4($sp)
			lw $s1, 8($sp)
			lw $s2, 12($sp)
			lw $s3, 16($sp)
			lw $s4, 20($sp)
			addi $sp, $sp, 24
			jr $ra

# *****Your codes end here

#--------------------------------------------------------------------
# procedure: move_player_right()
# Move the player object rightward by one step which is its speed.
# Move the object only when the object will not overlap with a wall cell afterward. 
# $v0=1 if a movement has been made, otherwise $v0=0.
#--------------------------------------------------------------------	
move_player_right:

# *****Task2: you need to complete this procedure move_player_right to perform its operations as described in comments above. 
# Hints:
# Firstly, preserve values $ra, $s0, $s1, $s2, $s3, $s4 with stack
# Then, use the registers as described below:
# 		The x_loc of the player object is in $s0
# 		The y_loc of the player object is in $s1
# 		The address of the player obejct is in $s2
# 		The height of the player object is in $s3
# 		The width of the player object is in $s4 
# Calculate new x_loc of the player object.
# Check whether player's top-right or bottom-right corner is in a wall: 
#		If it is in a wall, then the player can't move
# 		If it is not, then save and set the new x_loc for the player object
# Set $v0=1 if a movement has been made, 0 otherwise
# Lastly, pop and restore values in $ra, $s0, $s1, $s2, $s3, $s4  and return
# Hint: you can refer to move_player_up and move_player_down to get some clues
# *****Your codes start here

		addi $sp, $sp, -24
		sw $ra, 0($sp)
		sw $s0, 4($sp)
		sw $s1, 8($sp)
		sw $s2, 12($sp)
		sw $s3, 16($sp)
		sw $s4, 20($sp)

		la $t0, player_size
		lw $s4, 0($t0) # player width	
		lw $s3, 4($t0) # player height	
		la $t0, maze_size
		lw $t2, 0($t0) # maze width	

		la $t0, player_speed
		lw $t3, 0($t0) # player speed
		la $s2, player_locs
		lw $s0, 0($s2) # x_loc
		lw $s1, 4($s2) # y_loc
		

		addi $t2, $t2, -1 # x-coordinate of right-border is (width - 1)
		add $t9, $s0, $s4
		slt $t4, $t2, $t9
		beq $t4, $zero, mpr_check_path1 
		j mpr_no_move

mpr_check_path1:	
		# check whether player's top-right corner is in a wall
		add $s0, $s0, $t3 # new x_loc
		add $a0, $s0, $s4
		addi $a0, $a0, -1 # x-coordinate of player's right corner
		add $a1, $s1, $0
		jal get_bitmap_cell
		bne $v0, $zero, mpr_no_move  

mpr_check_path2:	
		# check whether player's right-center overlap with others
		add $a0, $s0, $s4	# (new x-cor + player_width)
		addi $a0, $a0, -1  	# (new x-cor + player_width) - 1
		addi $a1, $s1, 16  	# y-cor + 16 
		jal get_bitmap_cell
		bne $v0, $zero, mpr_no_move  

mpr_save_xloc:	sw $s0, 0($s2) # save new x_loc
				la $t0, player_id
				lw $a0, 0($t0)
				addi $a1, $s0, 0
				addi $a2, $s1, 0
				li $a3, 1 # object type
				li $v0, 104
				syscall # set new object location
				li $v0, 1
				j mpr_exit
	
mpr_no_move:	li $v0, 0				 
mpr_exit:	lw $ra, 0($sp)
			lw $s0, 4($sp)
			lw $s1, 8($sp)
			lw $s2, 12($sp)
			lw $s3, 16($sp)
			lw $s4, 20($sp)
			addi $sp, $sp, 24
			jr $ra

# *****Your codes end here

#--------------------------------------------------------------------
# procedure: get_bitmap_cell(x, y)
# Get the bitmap value for the grid cell containing the given pixel coordinate (x, y). 
# The value will be returned in $v0, or -1 will be returned in $v0 if (x, y) is outside the maze.   
# The index of the value in the bitmap array is returned in $v1
#--------------------------------------------------------------------
get_bitmap_cell:
	la $t0, grid_cell_size
	lw $t1, 0($t0) # cell width	
	lw $t2, 4($t0) # cell height	
	la $t0, grid_col_num
	lw $t3, 0($t0) 
	la $t0, maze_size
	lw $t7, 0($t0) # maze width	
	lw $t8, 4($t0) # maze height	
	li $v0, -1 # initialize the return value to -1

	slti $t5, $a0, 0 # check whether x is outside the maze
	bne $t5, $zero, gbc_exit
	slt $t5, $a0, $t7 
	beq $t5, $zero, gbc_exit
	slti $t5, $a1, 0 # check whether y is outside the maze
	bne $t5, $zero, gbc_exit
	slt $t5, $a1, $t8 
	beq $t5, $zero, gbc_exit

	div $a0, $t1
	mflo $t1 # column no. for given x-coordinate
	div $a1, $t2
	mflo $t2 # row no. for given y-coordinate

	# get the cell from the array
	mult $t3, $t2  
	mflo $t3
	add $t3, $t3, $t1 # index of the cell in 1D-array of bitmap
						
	la $t0, maze_bitmap
	add $t0, $t0, $t3
	lb $v0, 0($t0)
	addi $v1,$t3,0
	jr $ra 
	
gbc_exit: 
	jr $ra  