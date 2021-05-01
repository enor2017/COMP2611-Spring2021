/*    */ package mars.mips.instructions.syscalls;
import java.io.FileWriter;

/*    */ 
/*    */ import mars.ProcessingException;
/*    */ import mars.ProgramStatement;
/*    */ import mars.ext.game.GameConfigFile;
/*    */ import mars.ext.game.GameObject;
/*    */ import mars.ext.game.GameScreen;
/*    */ import mars.ext.game.MazeGameObject;
/*    */ import mars.mips.hardware.RegisterFile;
/*    */ import mars.util.SystemIO;
/*    */ 
/*    */ 
/*    */ public class Syscall103CreateGameObject
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall103CreateGameObject() {
/* 17 */     super(103, "Create Game Object");
/*    */   }
/*    */
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
/* 28 */     int i = RegisterFile.getValue(4); // id
/* 29 */     int j = RegisterFile.getValue(5)+32; // x
/* 30 */     int k = RegisterFile.getValue(6)+16; // y
/* 31 */     int m = RegisterFile.getValue(7); // type

	  		 int tank_id=0;
	         if(m==3){
	         	tank_id=RegisterFile.getValue(9);

/*    */     }
/* 33 */     GameScreen gameScreen = GameScreen.getInstance();
/* 34 */     if (gameScreen == null) {
/*    */       
/* 36 */       SystemIO.printString("In Creating Object, but GameScreen has not been created first!");
/* 37 */       throw new ProcessingException();
/*    */     }
/*    */     
///* 40 */     if (m < 0 || m > 3) {
///* 41 */       SystemIO.printString("Object type=" + m + " is invalid!");
///* 42 */       throw new ProcessingException();
///*    */     }
/*    */
/* 47 */     MazeGameObject mazeGameObject = new MazeGameObject(i, j, k, m);
/*    */     
/* 49 */     if (m == 0) {
			   // type 0, bomb
/* 50 */       int n = 5 + 5 * GameConfigFile.prevImagePackIndex[m];
/* 51 */       mazeGameObject.setHitPoint(n);
/* 52 */       RegisterFile.updateRegister(2, n);
/*    */     }
			 else if (m==1) { // tank
				 mazeGameObject.remaining_monster_num = 0;
				 mazeGameObject.total_monster_num = 0;
			 }
/* 54 */     else if (m == 2) {
				int speed = Integer.parseInt(GameConfigFile.properties.getProperty("object" + m + "Speed"));
				if (i%5 == 3) { // 0,1,2,3
					speed += 1;
				}
/* 55 */       	mazeGameObject.setSpeed(speed);
/* 56 */     }
			if(m==3){
				MazeGameObject tank=(MazeGameObject)gameScreen.getGameObject(tank_id,1);
				tank.pair=i;
				mazeGameObject.pair=tank_id;
			}
			if(m==1){
				GameConfigFile.playerPosiontions= mazeGameObject.tankPositions2();
			}
             gameScreen.addGameObject(i, (GameObject)mazeGameObject, m);
			
/*    */   }

/*    */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall205CreateGameObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */