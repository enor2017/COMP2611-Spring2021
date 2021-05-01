/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.Coprocessor1;
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
/*    */ public class SyscallReadFloat
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallReadFloat() {
/* 46 */     super(6, "ReadFloat");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 53 */     float f = 0.0F;
/*    */     
/*    */     try {
/* 56 */       f = SystemIO.readFloat(getNumber());
/*    */     }
/* 58 */     catch (NumberFormatException numberFormatException) {
/*    */       
/* 60 */       throw new ProcessingException(paramProgramStatement, "invalid float input (syscall " + 
/* 61 */           getNumber() + ")", 8);
/*    */     } 
/*    */     
/* 64 */     Coprocessor1.updateRegister(0, Float.floatToRawIntBits(f));
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallReadFloat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */