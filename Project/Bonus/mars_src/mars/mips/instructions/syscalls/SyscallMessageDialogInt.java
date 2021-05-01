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
/*    */ public class SyscallMessageDialogInt
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallMessageDialogInt() {
/* 46 */     super(56, "MessageDialogInt");
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
/* 58 */     String str = new String();
/* 59 */     int i = RegisterFile.getValue(4);
/* 60 */     char[] arrayOfChar = { ' ' };
/*    */     
/*    */     try {
/* 63 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/* 64 */       while (arrayOfChar[0] != '\000')
/*    */       {
/* 66 */         str = str.concat(new String(arrayOfChar));
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
/*    */     
/* 78 */     JOptionPane.showMessageDialog(null, str + 
/* 79 */         Integer.toString(RegisterFile.getValue(5)), null, 1);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallMessageDialogInt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */