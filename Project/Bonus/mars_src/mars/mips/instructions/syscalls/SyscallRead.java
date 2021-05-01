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
/*    */ public class SyscallRead
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallRead() {
/* 49 */     super(14, "Read");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 57 */     int i = RegisterFile.getValue(5);
/* 58 */     boolean bool = false;
/* 59 */     byte b = 0;
/* 60 */     byte[] arrayOfByte = new byte[RegisterFile.getValue(6)];
/*    */     
/* 62 */     int j = SystemIO.readFromFile(
/* 63 */         RegisterFile.getValue(4), arrayOfByte, 
/*    */         
/* 65 */         RegisterFile.getValue(6));
/* 66 */     RegisterFile.updateRegister(2, j);
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
/*    */     try {
/* 83 */       while (b < j)
/*    */       {
/* 85 */         Globals.memory.setByte(i++, arrayOfByte[b++]);
/*    */       
/*    */       }
/*    */     }
/* 89 */     catch (AddressErrorException addressErrorException) {
/*    */       
/* 91 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallRead.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */