/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.Globals;
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.RegisterFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SyscallExit2
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallExit2() {
/* 46 */     super(17, "Exit2");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 55 */     if (Globals.getGui() == null) {
/* 56 */       Globals.exitCode = RegisterFile.getValue(4);
/*    */     }
/* 58 */     throw new ProcessingException();
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallExit2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */