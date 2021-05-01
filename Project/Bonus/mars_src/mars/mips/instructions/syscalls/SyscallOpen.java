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
/*    */ public class SyscallOpen
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallOpen() {
/* 49 */     super(13, "Open");
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 74 */     String str = new String();
/* 75 */     int i = RegisterFile.getValue(4);
/* 76 */     char[] arrayOfChar = { ' ' };
/*    */     
/*    */     try {
/* 79 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/* 80 */       while (arrayOfChar[0] != '\000')
/*    */       {
/* 82 */         str = str.concat(new String(arrayOfChar));
/* 83 */         i++;
/* 84 */         arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*    */       }
/*    */     
/*    */     }
/* 88 */     catch (AddressErrorException addressErrorException) {
/*    */       
/* 90 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/* 92 */     int j = SystemIO.openFile(str, 
/* 93 */         RegisterFile.getValue(5));
/* 94 */     RegisterFile.updateRegister(2, j);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallOpen.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */