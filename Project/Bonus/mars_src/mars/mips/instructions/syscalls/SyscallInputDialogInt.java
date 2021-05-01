/*     */ package mars.mips.instructions.syscalls;
/*     */ 
/*     */ import javax.swing.JOptionPane;
/*     */ import mars.Globals;
/*     */ import mars.ProcessingException;
/*     */ import mars.ProgramStatement;
/*     */ import mars.mips.hardware.AddressErrorException;
/*     */ import mars.mips.hardware.RegisterFile;
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
/*     */ public class SyscallInputDialogInt
/*     */   extends AbstractSyscall
/*     */ {
/*     */   public SyscallInputDialogInt() {
/*  46 */     super(51, "InputDialogInt");
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
/*     */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/*  63 */     String str1 = new String();
/*  64 */     int i = RegisterFile.getValue(4);
/*  65 */     char[] arrayOfChar = { ' ' };
/*     */     
/*     */     try {
/*  68 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*  69 */       while (arrayOfChar[0] != '\000')
/*     */       {
/*  71 */         str1 = str1.concat(new String(arrayOfChar));
/*  72 */         i++;
/*  73 */         arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*     */       }
/*     */     
/*  76 */     } catch (AddressErrorException addressErrorException) {
/*     */       
/*  78 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     String str2 = null;
/*  86 */     str2 = JOptionPane.showInputDialog(str1);
/*  87 */     if (str2 == null) {
/*     */       
/*  89 */       RegisterFile.updateRegister(4, 0);
/*  90 */       RegisterFile.updateRegister(5, -2);
/*     */     }
/*  92 */     else if (str2.length() == 0) {
/*     */       
/*  94 */       RegisterFile.updateRegister(4, 0);
/*  95 */       RegisterFile.updateRegister(5, -3);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 101 */         int j = Integer.parseInt(str2);
/*     */ 
/*     */         
/* 104 */         RegisterFile.updateRegister(4, j);
/* 105 */         RegisterFile.updateRegister(5, 0);
/*     */       }
/* 107 */       catch (NumberFormatException numberFormatException) {
/*     */ 
/*     */         
/* 110 */         RegisterFile.updateRegister(4, 0);
/* 111 */         RegisterFile.updateRegister(5, -1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallInputDialogInt.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */