/*    */ package mars.mips.instructions.syscalls;

/*    */ 
/*    */

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.ext.game.GameConfigFile;
import mars.ext.game.GameScreen;
import mars.ext.game.MazeGameObject;
import mars.mips.hardware.RegisterFile;

/*    */ public class Syscall110HitBrick
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall110HitBrick() {
/* 17 */     super(110, "Create enemies");
/*    */   }
/*    */
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
				int i=RegisterFile.getValue(4); //id1
//				int j=RegisterFile.getValue(6); //id2
				GameScreen sc=GameScreen.getInstance();
				sc.editBitmapCell(i,0);
				sc.loadMaze(GameConfigFile.properties,1);
//				if(Integer.parseInt(GameConfigFile.properties.getProperty("numEnemy"))==2){
//					MazeGameObject enemy2=(MazeGameObject) sc.getGameObject(j,2);
//					enemy2.enemyMove();
//				}
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall205CreateGameObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */