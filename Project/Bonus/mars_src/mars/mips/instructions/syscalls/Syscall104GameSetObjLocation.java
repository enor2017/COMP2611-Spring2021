/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.ext.game.*;
/*    */
/*    */ import mars.mips.hardware.RegisterFile;
/*    */ import mars.util.SystemIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Syscall104GameSetObjLocation
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall104GameSetObjLocation() {
/* 16 */     super(104, "Set Object Location");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 22 */     int i = RegisterFile.getValue(4);
/* 23 */     int j = RegisterFile.getValue(5)+32;
/* 24 */     int k = RegisterFile.getValue(6)+16;
/* 25 */     int m = RegisterFile.getValue(7);
/*    */     
/* 27 */     GameScreen gameScreen = GameScreen.getInstance();
/* 28 */     if (gameScreen == null) {
/*    */       
/* 30 */       SystemIO.printString("GameScreen has not been created!");
/* 31 */       throw new ProcessingException();
/*    */     }

            /*****  GameTextObject cannot be cast to MazeGameObject  *****/
            if(m == 3){
                GameObject textobj = gameScreen.getGameObject(i, m);
                if(textobj == null){
                    SystemIO.printString("TextObject: id=" + i + " of type=" + m + " does not exist!");
                    throw new ProcessingException();
                }
                textobj.setLocations(j, k);
                return;
            }

/* 33 */     MazeGameObject mazeGameObject = (MazeGameObject)gameScreen.getGameObject(i, m);
/* 34 */     if (mazeGameObject == null) {
/*    */       
/* 36 */       SystemIO.printString("GameObject: id=" + i + " of type=" + m + " does not exist!");
/*    */       
/* 38 */       throw new ProcessingException();
/*    */     } 
/*    */     
///* 41 */     if (m < 0 || m > 3) {
///* 42 */       SystemIO.printString("Object type=" + m + " is invalid!");
///* 43 */       throw new ProcessingException();
///*    */     }
/*    */     mazeGameObject.setDirection(j,k);
/* 46 */     mazeGameObject.setLocations(j, k);

/* 47 */     if (m == 1) {
                mazeGameObject.incImageIndex();
                GameConfigFile.playerPosiontions= mazeGameObject.tankPositions2();
             }
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall206GameSetObjLocation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */