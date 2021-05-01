
mkdir game_temp
move mars\mips\instructions\syscalls\Syscall1*.* game_temp\
move mars\mips\instructions\syscalls\*.java game_temp\
jar cmf mainclass.txt NewMars.jar PseudoOps.txt Config.properties Syscall.properties Settings.properties MARSlicense.txt mainclass.txt MipsXRayOpcode.xml registerDatapath.xml controlDatapath.xml ALUcontrolDatapath.xml Mars.java Mars.class docs help images game mars/*.* mars/venus mars/util mars/tools/ mars/simulator mars/ext/game/*.class mars/assembler mars/mips/dump mars/mips/hardware mars/mips/instructions/*.* mars/mips/instructions/syscalls
move game_temp\* mars\mips\instructions\syscalls
rmdir game_temp
