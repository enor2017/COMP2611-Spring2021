/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ import java.util.Map;
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
import mars.ext.game.GameObject;
/*    */ import mars.ext.game.GameScreen;
/*    */ import mars.ext.game.MazeGameObject;
/*    */ import mars.util.SystemIO;
/*    */ 
/*    */ 
/*    */ public class Syscall101GameRefreshScreen
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall101GameRefreshScreen() {
/* 16 */     super(101, "Refresh Screen");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 23 */     GameScreen gameScreen = GameScreen.getInstance();
/* 24 */     if (gameScreen == null) {
/*    */       
/* 26 */       SystemIO.printString("In Refreshing Screen, but GameScreen has not been created first!");
/* 27 */       throw new ProcessingException();
/*    */     } 
/*    */     
/* 30 */     Hashtable<Integer, GameObject> hashtable = gameScreen.getAllObjects(5);
///* 31 */     for (Map.Entry entry : hashtable.entrySet()) {
			for (Map.Entry<Integer, GameObject> entry : hashtable.entrySet()) {
/*    */       
/* 33 */       MazeGameObject mazeGameObject = (MazeGameObject)entry.getValue();
/* 34 */       mazeGameObject.incImageIndex();
/*    */     } 
			
			
			
/* 36 */     gameScreen.refreshScreen();
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall201GameRefreshScreen.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */