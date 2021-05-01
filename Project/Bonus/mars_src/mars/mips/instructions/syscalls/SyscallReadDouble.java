/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.Coprocessor1;
/*    */ import mars.util.Binary;
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
/*    */ public class SyscallReadDouble
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallReadDouble() {
/* 47 */     super(7, "ReadDouble");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 55 */     double d = 0.0D;
/*    */     
/*    */     try {
/* 58 */       d = SystemIO.readDouble(getNumber());
/*    */     }
/* 60 */     catch (NumberFormatException numberFormatException) {
/*    */       
/* 62 */       throw new ProcessingException(paramProgramStatement, "invalid double input (syscall " + 
/* 63 */           getNumber() + ")", 8);
/*    */     } 
/*    */     
/* 66 */     long l = Double.doubleToRawLongBits(d);
/* 67 */     Coprocessor1.updateRegister(1, Binary.highOrderLongToInt(l));
/* 68 */     Coprocessor1.updateRegister(0, Binary.lowOrderLongToInt(l));
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallReadDouble.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */