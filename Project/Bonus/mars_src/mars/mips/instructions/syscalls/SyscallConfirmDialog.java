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
/*    */ public class SyscallConfirmDialog
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallConfirmDialog() {
/* 46 */     super(50, "ConfirmDialog");
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
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 59 */     String str = new String();
/* 60 */     int i = RegisterFile.getValue(4);
/* 61 */     char[] arrayOfChar = { ' ' };
/*    */     
/*    */     try {
/* 64 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/* 65 */       while (arrayOfChar[0] != '\000')
/*    */       {
/* 67 */         str = str.concat(new String(arrayOfChar));
/* 68 */         i++;
/* 69 */         arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*    */       }
/*    */     
/* 72 */     } catch (AddressErrorException addressErrorException) {
/*    */       
/* 74 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 82 */     RegisterFile.updateRegister(4, JOptionPane.showConfirmDialog(null, str));
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallConfirmDialog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */