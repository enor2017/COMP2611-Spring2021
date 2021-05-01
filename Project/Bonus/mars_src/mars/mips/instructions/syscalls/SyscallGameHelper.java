/*    */ package mars.mips.instructions.syscalls;
/*    */ 
/*    */ import mars.Globals;
/*    */ import mars.mips.hardware.AddressErrorException;
/*    */ import mars.mips.hardware.Memory;
import mars.util.SystemIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SyscallGameHelper
/*    */ {
/*    */   public static String getStringFromMIPS(int paramInt) {
/* 17 */     StringBuffer stringBuffer = new StringBuffer();
/*    */     
/*    */     try {
/* 20 */       Memory memory = Globals.memory;
/* 21 */       char c = Character.MIN_VALUE;
/* 22 */       c = (char)(memory.getByte(paramInt) & 0xFF);
/* 23 */       paramInt++;
/* 24 */       while (c != '\000')
/*    */       {
/* 26 */         stringBuffer.append(c);
/* 27 */         c = (char)(memory.getByte(paramInt) & 0xFF);
/* 28 */         paramInt++;
/*    */       }
/*    */     
/* 31 */     } catch (AddressErrorException addressErrorException) {
/*    */       
/* 33 */       System.err.println("Invalid byte address for the string!");
/* 34 */       addressErrorException.printStackTrace();
/*    */     } 
/* 36 */     return stringBuffer.toString();
/*    */   }

			public static int getIntFromMIPS (int paramInt, int offset) {
				try {
					Memory memory = Globals.memory;
					int value = (int)memory.getByte(paramInt + offset);
					
					// SystemIO.printString("read array, offset: "+offset+" data: "+value+"\n");
					
					return value;
					
				} catch (AddressErrorException addressErrorException) {
					System.err.println("Invalid data array!");
					addressErrorException.printStackTrace();
				}
				return -1;
			}
			
			
			public static int setIntToMIPS (int addr, int offset, int value) {
				try {
					Memory memory = Globals.memory;
					memory.setWord(addr + offset, value);
					
					// SystemIO.printString("set array, offset: "+offset+" data: "+value+"\n");
					return value;
					
				} catch (AddressErrorException addressErrorException) {
					System.err.println("Invalid data array!");
					addressErrorException.printStackTrace();
				}
				return -1;
			}

/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/SyscallGameHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */