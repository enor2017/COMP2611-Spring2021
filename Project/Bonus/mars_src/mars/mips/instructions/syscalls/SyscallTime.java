/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import java.util.Date;
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.RegisterFile;
/*    */ import mars.util.Binary;
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
/*    */ public class SyscallTime
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallTime() {
/* 46 */     super(30, "Time");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 54 */     long l = (new Date()).getTime();
/* 55 */     RegisterFile.updateRegister(4, Binary.lowOrderLongToInt(l));
/* 56 */     RegisterFile.updateRegister(5, Binary.highOrderLongToInt(l));
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallTime.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */