/*    */ package mars.mips.instructions.syscalls;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SyscallMidiOutSync
/*    */   extends AbstractSyscall
/*    */ {
/*    */   static final int rangeLowEnd = 0;
/*    */   static final int rangeHighEnd = 127;
/*    */   
/*    */   public SyscallMidiOutSync() {
/* 65 */     super(33, "MidiOutSync");
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 83 */     int i = RegisterFile.getValue(4);
/* 84 */     int j = RegisterFile.getValue(5);
/* 85 */     int k = RegisterFile.getValue(6);
/* 86 */     int m = RegisterFile.getValue(7);
/* 87 */     if (i < 0 || i > 127) i = 60; 
/* 88 */     if (j < 0) j = 1000; 
/* 89 */     if (k < 0 || k > 127) k = 0; 
/* 90 */     if (m < 0 || m > 127) m = 100; 
/* 91 */     (new ToneGenerator()).generateToneSynchronously((byte)i, j, (byte)k, (byte)m);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallMidiOutSync.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */