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
/*    */ public class SyscallRandSeed
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallRandSeed() {
/* 47 */     super(40, "RandSeed");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 58 */     Integer integer = new Integer(RegisterFile.getValue(4));
/* 59 */     Random random = (Random)RandomStreams.randomStreams.get(integer);
/* 60 */     if (random == null) {
/* 61 */       RandomStreams.randomStreams.put(integer, new Random(RegisterFile.getValue(5)));
/*    */     } else {
/* 63 */       random.setSeed(RegisterFile.getValue(5));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallRandSeed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */