/*     */ package mars.tools;
/*     */ 
/*     */ import java.awt.Color;

/*     */ class ScribblerSettings2 {
/*     */   private int width;
/*     */   private Color color;
/*     */   
/*     */   public ScribblerSettings2(int width, Color color) {
/* 791 */     this.width = width;
/* 792 */     this.color = color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineWidth() {
/* 799 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getLineColor() {
/* 806 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineWidth(int newWidth) {
/* 813 */     this.width = newWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineColor(Color newColor) {
/* 820 */     this.color = newColor;
/*     */   }
/*     */ }

///*     */ class ScribblerSettings {
///*     */   private int width;
///*     */   private Color color;
///*     */   
///*     */   public ScribblerSettings(int width, Color color) {
///* 791 */     this.width = width;
///* 792 */     this.color = color;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public int getLineWidth() {
///* 799 */     return this.width;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public Color getLineColor() {
///* 806 */     return this.color;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public void setLineWidth(int newWidth) {
///* 813 */     this.width = newWidth;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public void setLineColor(Color newColor) {
///* 820 */     this.color = newColor;
///*     */   }
///*     */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/tools/ScribblerSettings.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */