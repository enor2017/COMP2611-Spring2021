/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ public class SyscallRandIntRange
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallRandIntRange() {
/* 47 */     super(42, "RandIntRange");
/*    */   }
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
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 61 */     Integer integer = new Integer(RegisterFile.getValue(4));
/* 62 */     Random random = (Random)RandomStreams.randomStreams.get(integer);
/* 63 */     if (random == null) {
/* 64 */       random = new Random();
/* 65 */       RandomStreams.randomStreams.put(integer, random);
/*    */     } 
/*    */     try {
/* 68 */       RegisterFile.updateRegister(4, random.nextInt(RegisterFile.getValue(5)));
/*    */     }
/* 70 */     catch (IllegalArgumentException illegalArgumentException) {
/* 71 */       throw new ProcessingException(paramProgramStatement, "Upper bound of range cannot be negative (syscall " + 
/* 72 */           getNumber() + ")", 8);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallRandIntRange.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */