/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.RegisterFile;
/*    */ import mars.util.SystemIO;
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
/*    */ public class SyscallReadInt
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallReadInt() {
/* 47 */     super(5, "ReadInt");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 54 */     int i = 0;
/*    */     
/*    */     try {
/* 57 */       i = SystemIO.readInteger(getNumber());
/*    */     }
/* 59 */     catch (NumberFormatException numberFormatException) {
/*    */       
/* 61 */       throw new ProcessingException(paramProgramStatement, "invalid integer input (syscall " + 
/* 62 */           getNumber() + ")", 8);
/*    */     } 
/*    */     
/* 65 */     RegisterFile.updateRegister(2, i);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallReadInt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */