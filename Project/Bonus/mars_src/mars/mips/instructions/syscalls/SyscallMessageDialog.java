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
/*    */ public class SyscallMessageDialog
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public SyscallMessageDialog() {
/* 46 */     super(55, "MessageDialog");
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
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 63 */     String str = new String();
/* 64 */     int i = RegisterFile.getValue(4);
/* 65 */     char[] arrayOfChar = { ' ' };
/*    */     
/*    */     try {
/* 68 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/* 69 */       while (arrayOfChar[0] != '\000')
/*    */       {
/* 71 */         str = str.concat(new String(arrayOfChar));
/* 72 */         i++;
/* 73 */         arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*    */       }
/*    */     
/* 76 */     } catch (AddressErrorException addressErrorException) {
/*    */       
/* 78 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 83 */     int j = RegisterFile.getValue(5);
/* 84 */     if (j < 0 || j > 3) j = -1; 
/* 85 */     JOptionPane.showMessageDialog(null, str, null, j);
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallMessageDialog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */