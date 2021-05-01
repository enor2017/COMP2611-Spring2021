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
/*    */ public class SyscallReadString
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallReadString() {
/* 45 */     super(8, "ReadString");
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
/* 56 */     String str = "";
/* 57 */     int i = RegisterFile.getValue(4);
/* 58 */     int j = RegisterFile.getValue(5) - 1;
/* 59 */     boolean bool = true;
/*    */     
/* 61 */     if (j < 0) {
/*    */       
/* 63 */       j = 0;
/* 64 */       bool = false;
/*    */     } 
/* 66 */     str = SystemIO.readString(getNumber(), j);
/* 67 */     int k = Math.min(j, str.length());
/*    */     
/*    */     try {
/* 70 */       for (byte b = 0; b < k; b++)
/*    */       {
/* 72 */         Globals.memory.setByte(i + b, str
/* 73 */             .charAt(b));
/*    */       }
/* 75 */       if (k < j) {
/*    */         
/* 77 */         Globals.memory.setByte(i + k, 10);
/* 78 */         k++;
/*    */       } 
/* 80 */       if (bool) Globals.memory.setByte(i + k, 0);
/*    */     
/* 82 */     } catch (AddressErrorException addressErrorException) {
/*    */       
/* 84 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallReadString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */