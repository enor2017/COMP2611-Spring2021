/*    */ package mars.mips.instructions.syscalls;

/*    */ 
/*    */

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.ext.game.GameConfigFile;
import mars.ext.game.GameObject;
import mars.ext.game.GameScreen;
import mars.ext.game.MazeGameObject;
import mars.mips.hardware.Register;
import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

/*    */ public class Syscall109EnemyMove
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall109EnemyMove() {
/* 17 */     super(109, "Create enemies");
/*    */   }
/*    */
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
				int i=RegisterFile.getValue(4); //id1
//				int j=RegisterFile.getValue(5); //id2
				GameScreen sc=GameScreen.getInstance();
				MazeGameObject enemy=(MazeGameObject) sc.getGameObject(i,2);
				if(i==0||i==2)
					enemy.enemyChasePlayer();
				else if(i==1)
					enemy.enemyChaseHome();

				int[] pos=enemy.tankPositions1();
				RegisterFile.updateRegister(2,enemy.getXLoc()-32);
				RegisterFile.updateRegister(3,enemy.getYLoc()-16);

/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall205CreateGameObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */