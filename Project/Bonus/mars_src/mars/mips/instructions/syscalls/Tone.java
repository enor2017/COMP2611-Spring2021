/*     */ package mars.mips.instructions.syscalls;
/*     */ 
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import javax.sound.midi.InvalidMidiDataException;
/*     */ import javax.sound.midi.MidiEvent;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.sound.midi.Sequence;
/*     */ import javax.sound.midi.Sequencer;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ import javax.sound.midi.Track;
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
/*     */ class Tone
/*     */   implements Runnable
/*     */ {
/*     */   public static final int TEMPO = 1000;
/*     */   public static final int DEFAULT_CHANNEL = 0;
/*     */   private byte pitch;
/*     */   private int duration;
/*     */   private byte instrument;
/*     */   private byte volume;
/*     */   
/*     */   public Tone(byte paramByte1, int paramInt, byte paramByte2, byte paramByte3) {
/* 172 */     this.pitch = paramByte1;
/* 173 */     this.duration = paramInt;
/* 174 */     this.instrument = paramByte2;
/* 175 */     this.volume = paramByte3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 182 */     playTone();
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
/* 201 */   private static Lock openLock = new ReentrantLock();
/*     */ 
/*     */   
/*     */   private void playTone() {
/*     */     try {
/* 206 */       Sequencer sequencer = null;
/* 207 */       openLock.lock();
/*     */       try {
/* 209 */         sequencer = MidiSystem.getSequencer();
/* 210 */         sequencer.open();
/*     */       } finally {
/* 212 */         openLock.unlock();
/*     */       } 
/*     */       
/* 215 */       Sequence sequence = new Sequence(0.0F, 1);
/* 216 */       sequencer.setTempoInMPQ(1000.0F);
/* 217 */       Track track = sequence.createTrack();
/*     */ 
/*     */       
/* 220 */       ShortMessage shortMessage1 = new ShortMessage();
/* 221 */       shortMessage1.setMessage(192, 0, this.instrument, 0);
/* 222 */       MidiEvent midiEvent1 = new MidiEvent(shortMessage1, 0L);
/* 223 */       track.add(midiEvent1);
/*     */       
/* 225 */       ShortMessage shortMessage2 = new ShortMessage();
/* 226 */       shortMessage2.setMessage(144, 0, this.pitch, this.volume);
/* 227 */       MidiEvent midiEvent2 = new MidiEvent(shortMessage2, 0L);
/* 228 */       track.add(midiEvent2);
/*     */       
/* 230 */       ShortMessage shortMessage3 = new ShortMessage();
/* 231 */       shortMessage3.setMessage(128, 0, this.pitch, this.volume);
/* 232 */       MidiEvent midiEvent3 = new MidiEvent(shortMessage3, this.duration);
/* 233 */       track.add(midiEvent3);
/*     */       
/* 235 */       sequencer.setSequence(sequence);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       EndOfTrackListener endOfTrackListener = new EndOfTrackListener();
/* 244 */       sequencer.addMetaEventListener(endOfTrackListener);
/*     */       
/* 246 */       sequencer.start();
/*     */       
/*     */       try {
/* 249 */         endOfTrackListener.awaitEndOfTrack();
/*     */       }
/* 251 */       catch (InterruptedException interruptedException) {
/*     */       
/*     */       } finally {
/* 254 */         sequencer.close();
/*     */       }
/*     */     
/*     */     }
/* 258 */     catch (MidiUnavailableException midiUnavailableException) {
/* 259 */       midiUnavailableException.printStackTrace();
/*     */     }
/* 261 */     catch (InvalidMidiDataException invalidMidiDataException) {
/* 262 */       invalidMidiDataException.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Tone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */