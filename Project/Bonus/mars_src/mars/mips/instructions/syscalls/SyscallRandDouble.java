/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import java.util.Random;
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.Coprocessor1;
/*    */ import mars.mips.hardware.InvalidRegisterAccessException;
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
/*    */ public class SyscallRandDouble
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallRandDouble() {
/* 47 */     super(44, "RandDouble");
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
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 59 */     Integer integer = new Integer(RegisterFile.getValue(4));
/* 60 */     Random random = (Random)RandomStreams.randomStreams.get(integer);
/* 61 */     if (random == null) {
/* 62 */       random = new Random();
/* 63 */       RandomStreams.randomStreams.put(integer, random);
/*    */     } 
/*    */     try {
/* 66 */       Coprocessor1.setRegisterPairToDouble(0, random.nextDouble());
/*    */     }
/* 68 */     catch (InvalidRegisterAccessException invalidRegisterAccessException) {
/* 69 */       throw new ProcessingException(paramProgramStatement, "Internal error storing double to register (syscall " + 
/* 70 */           getNumber() + ")", 8);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallRandDouble.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */