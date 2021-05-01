/*    */ package mars.mips.instructions.syscalls;

/*    */ 
/*    */

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.ext.game.GameConfigFile;
import mars.ext.game.GameObject;
import mars.ext.game.GameScreen;
import mars.ext.game.MazeGameObject;
import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

/*    */
/*    */ 
/*    */ public class Syscall108CreateEnemies
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall108CreateEnemies() {
/* 17 */     super(108, "Create enemies");
/*    */   }
/*    */
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
				int n=RegisterFile.getValue(4);
				int i=RegisterFile.getValue(5); //id1
				int j=RegisterFile.getValue(6); //id2
				GameConfigFile.properties.setProperty("numEnemy",""+n);
				if(n<1||n>2){
/* 41 */       SystemIO.printString("n=" + n + " is invalid!");
/* 42 */       throw new ProcessingException();
/*    */     }
				int x1=Integer.parseInt(GameConfigFile.properties.getProperty("x1"));
				int x2=Integer.parseInt(GameConfigFile.properties.getProperty("x2"));
				int y1=Integer.parseInt(GameConfigFile.properties.getProperty("y1"));
				int y2=Integer.parseInt(GameConfigFile.properties.getProperty("y2"));

/* 33 */     	GameScreen gameScreen = GameScreen.getInstance();
				MazeGameObject enemy1 = new MazeGameObject(i, x1+32, y1+16, 2);
				enemy1.direction_=2;
				enemy1.incImageIndex();
				enemy1.pair=i;
				enemy1.enemyBulletNum=1;
				MazeGameObject bullet1=new MazeGameObject(i,1000,1000,4);
				gameScreen.addGameObject(i, (GameObject)enemy1, 2);
				gameScreen.addGameObject(i, (GameObject)bullet1, 4);
				if (n==2){
					MazeGameObject enemy2 = new MazeGameObject(j, x2+32, y2+16, 2);
					enemy2.direction_=2;
					enemy2.incImageIndex();
					enemy2.pair=j;
					enemy2.enemyBulletNum=1;
					MazeGameObject bullet2=new MazeGameObject(j,1000,1000,4);
					gameScreen.addGameObject(j, (GameObject)enemy2, 2);
					gameScreen.addGameObject(j, (GameObject)bullet2, 4);

				}


/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall205CreateGameObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */