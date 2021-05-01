/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.RegisterFile;
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
/*    */ public class SyscallPrintIntHex
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallPrintIntHex() {
/* 46 */     super(34, "PrintIntHex");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 53 */     SystemIO.printString(Binary.intToHexString(RegisterFile.getValue(4)));
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallPrintIntHex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */