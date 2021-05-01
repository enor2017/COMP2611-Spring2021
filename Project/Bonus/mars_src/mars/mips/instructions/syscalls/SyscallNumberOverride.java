/*    */ package mars.mips.instructions.syscalls;
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
/*    */ public class SyscallNumberOverride
/*    */ {
/*    */   private String serviceName;
/*    */   private int newServiceNumber;
/*    */   
/*    */   public SyscallNumberOverride(String paramString1, String paramString2) {
/* 54 */     this.serviceName = paramString1;
/*    */     try {
/* 56 */       this.newServiceNumber = Integer.parseInt(paramString2.trim());
/*    */     }
/* 58 */     catch (NumberFormatException numberFormatException) {
/* 59 */       System.out.println("Error processing Syscall number override: '" + paramString2.trim() + "' is not a valid integer");
/* 60 */       System.exit(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 70 */     return this.serviceName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNumber() {
/* 78 */     return this.newServiceNumber;
/*    */   }
/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallNumberOverride.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */