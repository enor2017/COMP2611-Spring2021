# Bonus Project for COMP 2611

Name: LIU, Jianmeng

Student ID: 20760163

Email: [jliudq@connect.ust.hk](mailto:jliudq@connect.ust.hk)

## 1. Introduction

In addition to the original project "Battle City Game", I implemented three kinds of props in order to make the game more interesting. They are:

- *Heart*: After player gain(i.e., player's tank collides with) this prop, player has an extra life. One thing worths mentioning is that after the player has been hit for the first time, a 2-second invincible time will be given, in case being attacked immediately after rebirth.
- *Invincible*: After player gain this prop, player will be invincible for 10 seconds. Notice that when enemy's bullet collides with player's tank, there will still be explosion, but the player won't die, making the game more vivid.
- *SpeedUP*: After player gain this prop, the move speed of player will be doubled, i.e., from $16$ px/s to $32$ px/s, for 10 seconds. You can move faster to avoid from your enemy easier, but notice that it will be harder for you to operate your tank, interesting ha?



## 2. Implement Logic

For each prop, we need to consider when to create it, when to display it on the screen, when will the player gain it, when will it in effect, and when will it be expired.

- *Heart*:
  - While initializing the game, get a random coordinate for it(done in MIPS), check whether this coordinate is proper, such as not in the river, not in stone wall.(done in Mars) Then pass the coordinate to Mars, and show the image on screen.
  - When player's tank collides with it, set `heart_status = 1` (done in MIPS), and display text on the screen to show player has gained the prop. (done in MIPS with the help of modified syscall)
  - When player was hit, set `heart_status = 0`, and enable *heart_invincible_protection* (i.e., make player invincible for 2 seconds in case immediate death, done in MIPS)
  - When player was hit, remove text from screen and remove image from screen. (done in MIPS with the help of modified syscall)
- *Invincible*
  - Initialize: same as *Heart*
  - When player gains it, basically the same as *Heart*.
  - One thing different is that we need to record the time that player gain it, since we will countdown 10 seconds.(done in MIPS by syscall 30: get current system time)
  - When player was hit, check whether the player is invincible, if yes, do nothing, just continue game loop.
  - After 10 seconds(this is checked in MIPS inside procedure `game_refresh`, by comparing current time and `prop_begin_time`) disable the prop and remove the text on the screen.
- *SpeedUP*
  - Initialize: same as *Invincible*.
  - When player gains it: basically same as *Invincible*.
  - One thing different is that we need to modify the four functions that control player's movement(i.e., `move_player_up/down/left/right`). Before each movement, check whether `speedup_status` is on, if so, double the speed.(done in MIPS).
  - After 10 seconds: same as *Invincible*.



## 3. Code Comments

For all codes related to props, I've added comments beside them using  `###`.