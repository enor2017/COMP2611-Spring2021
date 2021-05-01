/*    */ package mars.mips.instructions.syscalls;

/*    */ 
/*    */

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.ext.game.GameConfigFile;
import mars.ext.game.GameScreen;
import mars.ext.game.MazeGameObject;
import mars.mips.hardware.RegisterFile;

/*    */ public class Syscall112EnemyShoot
/*    */   extends AbstractSyscall
/*    */ {
/*    */   public Syscall112EnemyShoot() {
/* 17 */     super(112, "Create enemies");
/*    */   }
/*    */
/*    */   public void simulate(ProgramStatement paramProgramStatement) throws ProcessingException {
				int i=RegisterFile.getValue(4); //id1

				GameScreen sc=GameScreen.getInstance();
				MazeGameObject enemy=(MazeGameObject) sc.getGameObject(i,2);
				if(enemy.destroyed)
					return;
				MazeGameObject bullet=(MazeGameObject) sc.getGameObject(enemy.pair,4);
//				bullet.direction_=enemy.direction_;
				bullet.incImageIndex();

				int [][]brickWallPos=new int[2][2];
				int res=-1;
				for (int j = 0; j < brickWallPos.length; j++) {
					for (int k = 0; k < brickWallPos[0].length; k++) {
						brickWallPos[j][k]=-1;
					}
				}
				RegisterFile.updateRegister(3,0);
				RegisterFile.updateRegister(5,0);
				if (enemy.enemyBulletNum==1){
					if(enemy.hitWall)
						return;
					enemy.enemyBulletNum=0;
					int xe=enemy.getXLoc();
					int ye=enemy.getYLoc();
					int bulletSize= Integer.parseInt(GameConfigFile.properties.getProperty("object4Size"));
					int tankSize= Integer.parseInt(GameConfigFile.properties.getProperty("object2Size"));
					bullet.direction_=enemy.direction_;
					if (enemy.direction_==0){
						bullet.setLocations(xe+tankSize/2-bulletSize/2,ye-bulletSize);
					}
					else if (enemy.direction_==2){
						bullet.setLocations(xe+tankSize/2-bulletSize/2,ye+tankSize);
					}
					else if (enemy.direction_==1){
						bullet.setLocations(xe-bulletSize,ye+tankSize/2-bulletSize/2);
					}
					else if (enemy.direction_==3){
						bullet.setLocations(xe+tankSize,ye+tankSize/2-bulletSize/2);
					}

				}
				else{
					MazeGameObject playerBullet=(MazeGameObject) sc.getGameObject(0,3);
					if(playerBullet.getXLoc()!=bullet.getXLoc()&&playerBullet.getYLoc()!=bullet.getYLoc()) {
						res=bullet.bulletMove(brickWallPos);

					}
					else if((playerBullet.getXLoc()==bullet.getXLoc()&&playerBullet.direction_==0&&bullet.direction_==2&&bullet.getYLoc()<=playerBullet.getYLoc())||
							(playerBullet.getXLoc()==bullet.getXLoc()&&playerBullet.direction_==2&&bullet.direction_==0&&bullet.getYLoc()>=playerBullet.getYLoc())){
						if (Math.abs(bullet.getYLoc()-playerBullet.getYLoc())<20) {
							enemy.enemyBulletNum=1;
							res=0;
							bulletCrash(bullet, playerBullet);
						}
						else
							res=bullet.bulletMove(brickWallPos);
					}
					else if((playerBullet.getYLoc()==bullet.getYLoc()&&playerBullet.direction_==1&&bullet.direction_==3&&bullet.getXLoc()<=playerBullet.getXLoc())||
							(playerBullet.getYLoc()==bullet.getYLoc()&&playerBullet.direction_==3&&bullet.direction_==1&&bullet.getXLoc()>=playerBullet.getXLoc())){
						if (Math.abs(bullet.getYLoc()-playerBullet.getYLoc())<20) {
							enemy.enemyBulletNum=1;
							res=0;

							bulletCrash(bullet, playerBullet);
						}
						else
							res=bullet.bulletMove(brickWallPos);
					}
					if (res!=0){
						enemy.enemyBulletNum=1;
						bullet.setLocations(1000,1000);
						int len=0;

						for (int j = 0; j < brickWallPos.length; j++) {
							if (brickWallPos[j][0]!=-1) {
								len++;
								sc.editBitmapCell(brickWallPos[j][0],brickWallPos[j][1],0);
							}

						}
						sc.loadMaze(GameConfigFile.properties,1);
						assert len<=2;
						RegisterFile.updateRegister(3,len);
						for (int j = 0; j < len; j++) {
							RegisterFile.updateRegister(4+j,bullet.coordinateTransform(brickWallPos[j][0],brickWallPos[j][1]));
						}
						if (res==3){
							MazeGameObject player=(MazeGameObject) sc.getGameObject(0,1);
//							MazeGameObject tankBomb=(MazeGameObject) sc.getGameObject(0,5);
							MazeGameObject bulletBomb = new MazeGameObject(5+i,player.getXLoc(),player.getYLoc(),5);
//							tankBomb.setLocations(player.getXLoc(),player.getYLoc());
							sc.addGameObject(5+i,bulletBomb,5);
							player.setLocations(5000,5000);
							RegisterFile.updateRegister(2,1);
							sc.playSound(2, false);
						}
						else if (res==8){
							MazeGameObject brokenHome=(MazeGameObject) sc.getGameObject(0,6);
							brokenHome.setLocations(192+32,384+16);
							RegisterFile.updateRegister(2,1);
							sc.playSound(2, false);
						}
						else{
							RegisterFile.updateRegister(2,0);
						}
					}
				}

/*    */   }
			public void bulletCrash(MazeGameObject b1, MazeGameObject b2){
				RegisterFile.updateRegister(2,2);
				b1.setLocations(1000,1000);
				b2.setLocations(2000,2000);
			}
/*    */ }