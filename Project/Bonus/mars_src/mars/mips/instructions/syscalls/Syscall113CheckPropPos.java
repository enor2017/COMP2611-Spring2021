package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.ext.game.GameObject;
import mars.ext.game.GameScreen;
import mars.ext.game.MazeGameObject;
import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

import java.util.Hashtable;
import java.util.Map;

public class Syscall113CheckPropPos extends AbstractSyscall{
    public Syscall113CheckPropPos(){
        super(113, "CheckPropPos");
    }
    public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
        // (x, y) is the coordinate of cell(picture), not pixel.
        int x = RegisterFile.getValue(4); // x, stored in $a0
        int y = RegisterFile.getValue(5); // y, stored in $a1

        // get actually-painted map from gameScreen
        // -1 : grass, 0 : empty, 1 : brick, 2 : iron wall, 4 : water, 9 : home
        GameScreen gameScreen = GameScreen.getInstance();
        if (gameScreen == null) {
            SystemIO.printString("When checking prop positions, no gameScreen is found!");
            throw new ProcessingException();
        }
        int[][] mazeBitmap = gameScreen.getMazeBitmap();
        // (For debug) Print mazeBitmap
//        for(int i = 0 ; i < mazeBitmap.length; ++i){
//            for(int j = 0 ; j < mazeBitmap[i].length; ++j){
//                System.out.print(mazeBitmap[i][j]);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }

        // If position seems good, set $v0 = 1
        // Note here x and y is reversed.
        if(mazeBitmap[y][x] == 0){
//            System.out.println("x = " + x + ", y = " + y + ", array[y][x] = " + mazeBitmap[y][x]);
            RegisterFile.updateRegister(2, 1);
        } else RegisterFile.updateRegister(2, 0);
    }
}
