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
/*    */ public class SyscallMidiOut
/*    */   extends AbstractSyscall
/*    */ {
/*    */   static final int rangeLowEnd = 0;
/*    */   static final int rangeHighEnd = 127;
/*    */   
/*    */   public SyscallMidiOut() {
/* 54 */     super(31, "MidiOut");
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
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 71 */     int i = RegisterFile.getValue(4);
/* 72 */     int j = RegisterFile.getValue(5);
/* 73 */     int k = RegisterFile.getValue(6);
/* 74 */     int m = RegisterFile.getValue(7);
/* 75 */     if (i < 0 || i > 127) i = 60; 
/* 76 */     if (j < 0) j = 1000; 
/* 77 */     if (k < 0 || k > 127) k = 0; 
/* 78 */     if (m < 0 || m > 127) m = 100; 
/* 79 */     (new ToneGenerator()).generateTone((byte)i, j, (byte)k, (byte)m);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallMidiOut.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */