/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.ext.game.GameScreen;
/*    */ import mars.mips.hardware.RegisterFile;
/*    */ import mars.util.SystemIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Syscall102GamePlaySound
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall102GamePlaySound() {
/* 15 */     super(102, "Play Sound");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 22 */     int i = RegisterFile.getValue(4);
/* 23 */     boolean bool = (RegisterFile.getValue(5) == 1) ? true : false;
/* 24 */     GameScreen gameScreen = GameScreen.getInstance();
/* 25 */     if (gameScreen == null) {
/*    */       
/* 27 */       SystemIO.printString("GameScreen has not been created!");
/* 28 */       throw new ProcessingException();
/*    */     } 
/*    */     
/* 31 */     if (RegisterFile.getValue(5) == 2)
/* 32 */     { gameScreen.stopSound(i); }
/* 33 */     else { gameScreen.playSound(i, bool); }
/*    */   
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall202GamePlaySound.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */