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
/*    */ 
/*    */ public class SyscallSbrk
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallSbrk() {
/* 47 */     super(9, "Sbrk");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 54 */     int i = 0;
/*    */     try {
/* 56 */       i = Globals.memory.allocateBytesFromHeap(RegisterFile.getValue(4));
/*    */     }
/* 58 */     catch (IllegalArgumentException illegalArgumentException) {
/* 59 */       throw new ProcessingException(paramProgramStatement, illegalArgumentException
/* 60 */           .getMessage() + " (syscall " + getNumber() + ")", 8);
/*    */     } 
/*    */     
/* 63 */     RegisterFile.updateRegister(2, i);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallSbrk.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */