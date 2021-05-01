/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.ext.game.GameObject;
/*    */ import mars.ext.game.GameScreen;
/*    */ import mars.mips.hardware.RegisterFile;
/*    */ import mars.util.SystemIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Syscall106GameGetObjLocation
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall106GameGetObjLocation() {
/* 16 */     super(106, "Get Object Location");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 23 */     int i = RegisterFile.getValue(4);
/*    */     
/* 25 */     byte b = 2;
/* 26 */     GameScreen gameScreen = GameScreen.getInstance();
/* 27 */     int j = -1;
/* 28 */     int k = -1;
/* 29 */     if (gameScreen == null) {
/*    */       
/* 31 */       SystemIO.printString("GameScreen has not been created!");
/* 32 */       throw new ProcessingException();
/*    */     } 
/*    */     
/* 35 */     GameObject gameObject = gameScreen.getGameObject(i, b);
/* 36 */     if (gameObject == null) {
/*    */       
/* 38 */       SystemIO.printString("GameObject: id=" + i + " of type=" + b + " does not exist!");
/*    */       
/* 40 */       throw new ProcessingException();
/*    */     } 
/*    */     
/* 43 */     j = gameObject.getXLoc()-32;
/* 44 */     k = gameObject.getYLoc()-16;
/* 45 */     RegisterFile.updateRegister(2, j);
/* 46 */     RegisterFile.updateRegister(3, k);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall208GameGetObjLocation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */