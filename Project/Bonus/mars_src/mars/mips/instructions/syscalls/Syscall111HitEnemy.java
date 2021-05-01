/*    */ package mars.mips.instructions.syscalls;

/*    */ 
/*    */

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.ext.game.GameScreen;
import mars.ext.game.MazeGameObject;
import mars.mips.hardware.RegisterFile;

/*    */ public class Syscall111HitEnemy
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall111HitEnemy() {
/* 17 */     super(111, "Create enemies");
/*    */   }
/*    */
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
				int i=RegisterFile.getValue(4); //id1

				GameScreen sc=GameScreen.getInstance();
				MazeGameObject enemy=(MazeGameObject) sc.getGameObject(i,2);

				enemy.setLocations(1000,1000);

				enemy.destroyed=true;
				MazeGameObject enemyBullet=(MazeGameObject)sc.getGameObject(enemy.pair,4);
				enemyBullet.setLocations(1000,1000);
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