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
/*    */ public class SyscallPrintDouble
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallPrintDouble() {
/* 46 */     super(3, "PrintDouble");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 54 */     SystemIO.printString((new Double(Double.longBitsToDouble(
/* 55 */             Binary.twoIntsToLong(Coprocessor1.getValue(13), Coprocessor1.getValue(12)))))
/* 56 */         .toString());
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallPrintDouble.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */