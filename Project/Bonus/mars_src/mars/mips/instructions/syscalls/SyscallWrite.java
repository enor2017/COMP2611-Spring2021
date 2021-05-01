/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.Globals;
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.AddressErrorException;
/*    */ import mars.mips.hardware.RegisterFile;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SyscallWrite
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallWrite() {
/* 50 */     super(15, "Write");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 58 */     int i = RegisterFile.getValue(5);
/* 59 */     byte b = 0;
/* 60 */     int j = RegisterFile.getValue(6);
/* 61 */     byte b1 = 0;
/* 62 */     byte[] arrayOfByte = new byte[RegisterFile.getValue(6) + 1];
/*    */     
/*    */     try {
/* 65 */       b = (byte)Globals.memory.getByte(i);
/* 66 */       while (b1 < j) {
/*    */ 
/*    */         
/* 69 */         arrayOfByte[b1++] = b;
/* 70 */         i++;
/* 71 */         b = (byte)Globals.memory.getByte(i);
/*    */       } 
/*    */       
/* 74 */       arrayOfByte[b1] = 0;
/*    */     }
/* 76 */     catch (AddressErrorException addressErrorException) {
/*    */       
/* 78 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/* 80 */     int k = SystemIO.writeToFile(
/* 81 */         RegisterFile.getValue(4), arrayOfByte, 
/*    */         
/* 83 */         RegisterFile.getValue(6));
/* 84 */     RegisterFile.updateRegister(2, k);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallWrite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */