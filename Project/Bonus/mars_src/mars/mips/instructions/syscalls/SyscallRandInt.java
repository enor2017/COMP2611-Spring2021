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
/*    */ public class SyscallRandInt
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallRandInt() {
/* 47 */     super(41, "RandInt");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 57 */     Integer integer = new Integer(RegisterFile.getValue(4));
/* 58 */     Random random = (Random)RandomStreams.randomStreams.get(integer);
/* 59 */     if (random == null) {
/* 60 */       random = new Random();
/* 61 */       RandomStreams.randomStreams.put(integer, random);
/*    */     } 
/* 63 */     RegisterFile.updateRegister(4, random.nextInt());
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallRandInt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */