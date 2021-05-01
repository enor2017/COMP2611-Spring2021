/*     */ package mars.mips.instructions.syscalls;
/*     */ 
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.Executors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ToneGenerator
/*     */ {
/*     */   public static final byte DEFAULT_PITCH = 60;
/*     */   public static final int DEFAULT_DURATION = 1000;
/*     */   public static final byte DEFAULT_INSTRUMENT = 0;
/*     */   public static final byte DEFAULT_VOLUME = 100;
/*  86 */   private static Executor threadPool = Executors.newCachedThreadPool();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateTone(byte paramByte1, int paramInt, byte paramByte2, byte paramByte3) {
/* 105 */     Tone tone = new Tone(paramByte1, paramInt, paramByte2, paramByte3);
/* 106 */     threadPool.execute(tone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateToneSynchronously(byte paramByte1, int paramInt, byte paramByte2, byte paramByte3) {
/* 126 */     Tone tone = new Tone(paramByte1, paramInt, paramByte2, paramByte3);
/* 127 */     tone.run();
/*     */   }
/*     */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/ToneGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */