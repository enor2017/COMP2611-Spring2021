# COMP2611 Midterm Quick Reference

## 1. Base Conversion

|       Bin        | Dec  | Hex  |
| :--------------: | :--: | :--: |
|       0000       |  0   |  0   |
|       0001       |  1   |  1   |
|       0010       |  2   |  2   |
|       0011       |  3   |  3   |
|       0100       |  4   |  4   |
|       0101       |  5   |  5   |
|       0110       |  6   |  6   |
|       0111       |  7   |  7   |
|       1000       |  8   |  8   |
|       1001       |  9   |  9   |
|       1010       |  10  |  A   |
|       1011       |  11  |  B   |
|       1100       |  12  |  C   |
|       1101       |  13  |  D   |
|       1110       |  14  |  E   |
|       1111       |  15  |  F   |
|     11  0000     |  48  |  30  |
|    0100  0001    |  65  |  41  |
|    0110  0001    |  97  |  61  |
|    0111  1111    | 127  |  7F  |
|    1000  0000    | 128  |  80  |
|    1111  1111    | 255  |  FF  |
| 0001  0000  0000 | 256  | 100  |
| 0011  1111  1111 | 1023 | 3FF  |
| 0100  0000  0000 | 1024 | 400  |

## 2. MIPS Code for Inequal Equation

- ```c++
  if($i < $j) stat_A;
  else stat_B;
  ```

  ```assembly
          slt $t0, $i, $j
          bne $t1, $0, stat_A
          j stat_B
  stat_A: 						# do sth
  				j exit
  stat_B:							# do sth
  exit:
  
  ```
  
  
  
- ```c++
  if($i > $j) stat_A;
  else stat_B;
  ```

  ```assembly
          slt $t0, $j, $i
          bne $t1, $0, stat_A
          j stat_B
  stat_A: 						# do sth
  				j exit
  stat_B:							# do sth
  exit:
  
  ```
  
  
  
- ```c++
  if($i <= $j) stat_A;
  else stat_B;
  ```

  ```assembly
          slt $t0, $j, $i
          beq $t1, $0, stat_A
          j stat_B
  stat_A: 						# do sth
  				j exit
  stat_B:							# do sth
  exit:
  
  ```
  
  
  
- ```c++
  if($i >= $j) stat_A;
  else stat_B;
  ```

  ```assembly
          slt $t0, $i, $j
          beq $t1, $0, stat_A
          j stat_B
  stat_A: 						# do sth
          j exit
  stat_B:							# do sth
  exit:
  ```



