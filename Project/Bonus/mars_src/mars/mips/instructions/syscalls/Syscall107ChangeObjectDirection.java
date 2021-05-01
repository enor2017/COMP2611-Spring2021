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
/*    */ public class Syscall107ChangeObjectDirection
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall107ChangeObjectDirection() {
/* 17 */     super(107, "Create Game Object");
/*    */   }
/*    */
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 28 */     int i = RegisterFile.getValue(4); // id
/* 31 */     int m = RegisterFile.getValue(5); // type
			 int dir=RegisterFile.getValue(6);


/* 33 */     GameScreen gameScreen = GameScreen.getInstance();
/* 34 */     if (gameScreen == null) {
/*    */       
/* 36 */       SystemIO.printString("In Creating Object, but GameScreen has not been created first!");
/* 37 */       throw new ProcessingException();
/*    */     }
/*    */     
/* 40 */     if (m < 0 || m > 3) {
/* 41 */       SystemIO.printString("Object type=" + m + " is invalid!");
/* 42 */       throw new ProcessingException();
/*    */     } 
/*    */
/*    */     


			MazeGameObject ob=(MazeGameObject)gameScreen.getGameObject(i,m);

            ob.direction_=dir;
			ob.incImageIndex();
	/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall205CreateGameObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */