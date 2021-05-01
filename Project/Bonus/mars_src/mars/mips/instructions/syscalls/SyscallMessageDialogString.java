/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import javax.swing.JOptionPane;
/*    */ import mars.Globals;
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.mips.hardware.AddressErrorException;
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
/*    */ public class SyscallMessageDialogString
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallMessageDialogString() {
/* 46 */     super(59, "MessageDialogString");
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
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 58 */     String str1 = new String();
/* 59 */     int i = RegisterFile.getValue(4);
/* 60 */     char[] arrayOfChar = { ' ' };
/*    */     
/*    */     try {
/* 63 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/* 64 */       while (arrayOfChar[0] != '\000')
/*    */       {
/* 66 */         str1 = str1.concat(new String(arrayOfChar));
/* 67 */         i++;
/* 68 */         arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*    */       }
/*    */     
/* 71 */     } catch (AddressErrorException addressErrorException) {
/*    */       
/* 73 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/*    */ 
/*    */     
/* 77 */     String str2 = new String();
/* 78 */     i = RegisterFile.getValue(5);
/*    */     
/*    */     try {
/* 81 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/* 82 */       while (arrayOfChar[0] != '\000')
/*    */       {
/* 84 */         str2 = str2.concat(new String(arrayOfChar));
/* 85 */         i++;
/* 86 */         arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*    */       }
/*    */     
/* 89 */     } catch (AddressErrorException addressErrorException) {
/*    */       
/* 91 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 96 */     JOptionPane.showMessageDialog(null, str1 + str2, null, 1);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallMessageDialogString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */