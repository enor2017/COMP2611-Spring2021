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
/*     */ public class SyscallInputDialogString
/*     */   extends AbstractSyscall
/*     */ {
/*     */   public SyscallInputDialogString() {
/*  46 */     super(54, "InputDialogString");
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
/*     */ 
/*     */   
/*     */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/*  65 */     String str1 = new String();
/*  66 */     int i = RegisterFile.getValue(4);
/*  67 */     char[] arrayOfChar = { ' ' };
/*     */     
/*     */     try {
/*  70 */       arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*  71 */       while (arrayOfChar[0] != '\000')
/*     */       {
/*  73 */         str1 = str1.concat(new String(arrayOfChar));
/*  74 */         i++;
/*  75 */         arrayOfChar[0] = (char)Globals.memory.getByte(i);
/*     */       }
/*     */     
/*  78 */     } catch (AddressErrorException addressErrorException) {
/*     */       
/*  80 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     String str2 = null;
/*  88 */     str2 = JOptionPane.showInputDialog(str1);
/*  89 */     i = RegisterFile.getValue(5);
/*  90 */     int j = RegisterFile.getValue(6);
/*     */ 
/*     */     
/*     */     try {
/*  94 */       if (str2 == null)
/*     */       {
/*  96 */         RegisterFile.updateRegister(5, -2);
/*     */       }
/*  98 */       else if (str2.length() == 0)
/*     */       {
/* 100 */         RegisterFile.updateRegister(5, -3);
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */         
/* 106 */         for (byte b = 0; b < str2.length() && b < j - 1; b++)
/*     */         {
/* 108 */           Globals.memory.setByte(i + b, str2
/* 109 */               .charAt(b));
/*     */         }
/* 111 */         if (str2.length() < j - 1)
/*     */         {
/* 113 */           Globals.memory.setByte(i + Math.min(str2.length(), j - 2), 10);
/*     */         }
/* 115 */         Globals.memory.setByte(i + Math.min(str2.length() + 1, j - 1), 0);
/*     */         
/* 117 */         if (str2.length() > j - 1)
/*     */         {
/*     */           
/* 120 */           RegisterFile.updateRegister(5, -4);
/*     */         }
/*     */         else
/*     */         {
/* 124 */           RegisterFile.updateRegister(5, 0);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 129 */     } catch (AddressErrorException addressErrorException) {
/*     */       
/* 131 */       throw new ProcessingException(paramProgramStatement, addressErrorException);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallInputDialogString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */