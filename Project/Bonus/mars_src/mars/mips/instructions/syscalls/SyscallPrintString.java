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
/*    */ public class SyscallPrintString
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallPrintString() {
/* 45 */     super(4, "PrintString");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 52 */     int i = RegisterFile.getValue(4);
/* 53 */     char c = Character.MIN_VALUE;
/*    */     
/*    */     try {
/* 56 */       c = (char)Globals.memory.getByte(i);
/*    */       
/* 58 */       while (c != '\000')
/*    */       {
/* 60 */         SystemIO.printString((new Character(c)).toString());
/* 61 */         i++;
/* 62 */         c = (char)Globals.memory.getByte(i);
/*    */       }
/*    */     
/* 65 */     } catch (AddressErrorException addressErrorException) {
/*    */       
/* 67 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallPrintString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */