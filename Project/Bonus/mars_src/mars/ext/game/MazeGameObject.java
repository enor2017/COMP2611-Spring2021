package mars.ext.game;

import mars.ext.game.util.Sprite;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Ship Object
 *
 * @author jasonwangm
 *
 */
public class MazeGameObject extends GameObject implements ActiveElementInterface {

	private ArrayList<Image> images = new ArrayList<Image>();
	private int hitPoint;
	private boolean isRight = true; // indicating current direction

	private int speed;
	public int curImageIndex;
	public int delImageIndex;
	private ArrayList route = null;
	private String action;
	private int destX, destY; // -1: no direction, 0: north, 1: west, 2: south, 3: east
	private int routeStepIndex;
	private int[][] markedRoute; // 0 if a cell is not visited before; 1 if a cell is visited once; 2 if a cell
	// is visited twice
	private boolean seekTarget = false;
	private int images_per_dir=1;
	private int cur_dir_indx =0;
//	public int direction;

	// for bomb
	public boolean bombUsed = false;
	public int bombPower = 1;
	public boolean bombPowerUp = false;
	public int bombTimerThreshold = 50;
	public int bombTimer = bombTimerThreshold;

	// for bomb flame
	public int flame_x;
	public int flame_y;
	int imgIndex = 0; // [1,3] for each part
	public int blastSteps = 0; // [1,3]
	int flameStepTimerThreshold = 4;
	int flameTimer = flameStepTimerThreshold;
	public int nEnd, sEnd, wEnd, eEnd;
	public boolean bombPlaySound = false;
	ArrayList<Image> flameImgs = new ArrayList<Image>();

	// powerText
	public int powerUpId = -10;
	public int textType = 3;
	public int textDisplayX = 100;
	public int textDisplayY = 70;
	public int textColor = 11674146;
	public int powerTextTimerThres = 20;
	public int powerTextTimer = -1;

	public int pair;

	// monster
	public static int remaining_monster_num = 0;
	public static int total_monster_num = 0;
	public boolean survival = true;
	//    public static int updateMonsterNum(int value) {
//    	remaining_monster_num += value;
//    	return  ;
//    }
//	enemy
	int remianingSteps=0;
	int enemySpeed=2;
	public int[] enemyRoute;
	public int latestMove;
	int routIdx=0;
	int enemyIdx;//0,1
	public int enemyBulletNum=1;
	public  boolean destroyed=false;
	public boolean hitWall=false;
	//bullet bomb
	int interval=4;
	int bulletSpeed=10;
	public int bulletMove(int [][]brickWallPos){
		int newX, newY;
		GameScreen screen=GameScreen.getInstance();
		int[][]playerPos=GameConfigFile.playerPosiontions;
		int len=0;
		int res=0;
		if(direction_==0){
			newX=xLoc-32;
			newY=yLoc-16-bulletSpeed;
			if (newY<0)
				return 2;
			if (screen.getBitmapCell(newX,newY)==2||screen.getBitmapCell(newX+6,newY)==2)
				res=2;
			if(screen.getBitmapCell(newX,newY)==1||screen.getBitmapCell(newX+6,newY)==1){
				res=1;
				if(screen.getBitmapCell(newX,newY)==1) {
					brickWallPos[len][0]=newX;
					brickWallPos[len][1]=newY;
					len++;
				}
				if(screen.getBitmapCell(newX+6,newY)==1) {
					brickWallPos[len][0]=newX+6;
					brickWallPos[len][1]=newY;
					len++;
				}
			}
			else if (screen.getBitmapCell(newX,newY)==8||screen.getBitmapCell(newX+6,newY)==8||screen.getBitmapCell(newX,newY)==9||screen.getBitmapCell(newX+6,newY)==9)
				res=8;

			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||((newX+6)/16==playerPos[i][0]&&newY/16==playerPos[i][1]))
					res=3;
			}

		}else if (direction_==2){
			newX=xLoc-32;
			newY=yLoc-16+bulletSpeed;
			if (newY+6>Integer.parseInt(GameConfigFile.properties.getProperty("mazeHeight")))
				return 2;
			if (screen.getBitmapCell(newX,newY+6-1)==2||screen.getBitmapCell(newX+6,newY+6-1)==2)
				res=2;
			if(screen.getBitmapCell(newX,newY+6-1)==1||screen.getBitmapCell(newX+6,newY+6-1)==1){
				res=1;
				if(screen.getBitmapCell(newX,newY+6-1)==1) {
					brickWallPos[len][0]=newX;
					brickWallPos[len][1]=newY+6-1;
					len++;
				}
				if(screen.getBitmapCell(newX+6,newY+6-1)==1) {
					brickWallPos[len][0]=newX+6;
					brickWallPos[len][1]=newY+6-1;
					len++;
				}
			}
			else if (screen.getBitmapCell(newX,newY+6)==8||screen.getBitmapCell(newX+6,newY+6-1)==8||screen.getBitmapCell(newX,newY+6)==9||screen.getBitmapCell(newX+6,newY+6-1)==9)
				res=8;



			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&(newY+6-1)/16==playerPos[i][1])||((newX+6)/16==playerPos[i][0]&&(newY+6-1)/16==playerPos[i][1]))
					res=3;
			}
		}else if (direction_==1){
			newX=xLoc-32-bulletSpeed;
			newY=yLoc-16;
			if (newX<0)
				return 2;
			if (screen.getBitmapCell(newX,newY)==2||screen.getBitmapCell(newX,newY+6)==2)
				res=2;
			if(screen.getBitmapCell(newX,newY)==1||screen.getBitmapCell(newX,newY+6)==1){
				res=1;
				if(screen.getBitmapCell(newX,newY)==1) {
					brickWallPos[len][0]=newX;
					brickWallPos[len][1]=newY;
					len++;
				}
				if(screen.getBitmapCell(newX,newY+6)==1) {
					brickWallPos[len][0]=newX;
					brickWallPos[len][1]=newY+6;
					len++;
				}
			}
			else if(screen.getBitmapCell(newX,newY)==8||screen.getBitmapCell(newX,newY+6)==8||screen.getBitmapCell(newX,newY)==9||screen.getBitmapCell(newX,newY+6)==9)
				res=8;

			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||((newX)/16==playerPos[i][0]&&(newY+6)/16==playerPos[i][1]))
					res=3;
			}
		}else{
			newX=xLoc-32+bulletSpeed;
			newY=yLoc-16;
			if (newX+6>Integer.parseInt(GameConfigFile.properties.getProperty("mazeWidth")))
				return 2;

			if (screen.getBitmapCell(newX+6-1,newY)==2||screen.getBitmapCell(newX+6-1,newY+6)==2)
				res=2;
			if(screen.getBitmapCell(newX+6-1,newY)==1||screen.getBitmapCell(newX+6-1,newY+6)==1){
				res=1;
				if(screen.getBitmapCell(newX+6-1,newY)==1) {
					brickWallPos[len][0]=newX+6-1;
					brickWallPos[len][1]=newY;
					len++;
				}
				if(screen.getBitmapCell(newX+6-1,newY+6)==1) {
					brickWallPos[len][0]=newX+6-1;
					brickWallPos[len][1]=newY+6;
					len++;
				}
			}
			else if(screen.getBitmapCell(newX+6-1,newY)==8||screen.getBitmapCell(newX+6-1,newY+6)==8||screen.getBitmapCell(newX+6-1,newY)==9||screen.getBitmapCell(newX+6-1,newY+6)==9)
				res=8;
			for (int i = 0; i < playerPos.length; i++) {
				if(((newX+6-1)/16==playerPos[i][0]&&newY/16==playerPos[i][1])||((newX+6-1)/16==playerPos[i][0]&&(newY+6)/16==playerPos[i][1]))
					res=3;
			}
		}
		if (res==0)
			setLocations(newX+32,newY+16);
		return res;
	}



	public void enemyChasePlayer(){
		if(destroyed)
			return;
		GameScreen screen=GameScreen.getInstance();
		if(remianingSteps==0){
			remianingSteps=16;
//			GameScreen sc=GameScreen.getInstance();
			MazeGameObject player=(MazeGameObject) screen.getGameObject(0,1);
			int[] dirs=new int[2];
			int xp=player.xLoc;
			int yp=player.yLoc;
			int xe=xLoc;
			int ye=yLoc;
			if(xp<xe)
				dirs[0]=1;
			else
				dirs[0]=3;
			if(yp<ye)
				dirs[1]=0;
			else
				dirs[1]=2;
			double rand=Math.random();
			if(rand<0.4)
				latestMove=dirs[0];
			else if(rand<0.8)
				latestMove=dirs[1];
			else if(rand<0.9)
				latestMove=(dirs[0]+2)%4;
			else
				latestMove=(dirs[1]+2)%4;
			direction_=latestMove;
			incImageIndex();
			hitWall=false;

		}
		remianingSteps--;
		int[][]playerPos=GameConfigFile.playerPosiontions;
		int newX, newY;

		if(latestMove==0){
			newX=xLoc-32;
			newY=yLoc-16-enemySpeed;
			if (newY<0)
				return;
			if(screen.getBitmapCell(newX,newY)>0||screen.getBitmapCell(newX+16,newY)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}

			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&newY/16==playerPos[i][1]))
					return;
			}
		}else if (latestMove==2){
			newX=xLoc-32;
			newY=yLoc-16+enemySpeed;
			if (newY+32>Integer.parseInt(GameConfigFile.properties.getProperty("mazeHeight")))
				return;
			if(screen.getBitmapCell(newX,newY+32-1)>0||screen.getBitmapCell(newX+16,newY+32-1)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}
			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&(newY+32-1)/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&(newY+32-1)/16==playerPos[i][1]))
					return;
			}

		}else if (latestMove==1){
			newX=xLoc-32-enemySpeed;
			newY=yLoc-16;
			if (newX<0)
				return;
			if(screen.getBitmapCell(newX,newY)>0||screen.getBitmapCell(newX,newY+16)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}
			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&newY/16+1==playerPos[i][1])) {

					return;
				}
			}
		}else if (latestMove==3){
			newX=xLoc-32+enemySpeed;
			newY=yLoc-16;
			if (newX+32>Integer.parseInt(GameConfigFile.properties.getProperty("mazeWidth")))
				return;
			if(screen.getBitmapCell(newX+32-1,newY)>0||screen.getBitmapCell(newX+32-1,newY+16)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}
			for (int i = 0; i < playerPos.length; i++) {
				if(((newX+32-1)/16==playerPos[i][0]&&newY/16==playerPos[i][1])||((newX+32-1)/16==playerPos[i][0]&&newY/16+1==playerPos[i][1]))
					return;
			}
		}
		else return;
		if (hitWall) {
			hitWall = false;
			remianingSteps=15;
		}
		setLocations(newX+32,newY+16);
	}

	public void enemyChaseHome(){
		if(destroyed)
			return;
		GameScreen screen=GameScreen.getInstance();
		if(remianingSteps==0){
			remianingSteps=16;
//			GameScreen sc=GameScreen.getInstance();
//			MazeGameObject player=(MazeGameObject) screen.getGameObject(0,1);
			int[] dirs=new int[2];
			int xp=192+32;
			int yp=384+16;
			int xe=xLoc;
			int ye=yLoc;
			if(xp<xe)
				dirs[0]=1;
			else
				dirs[0]=3;
			if(yp<ye)
				dirs[1]=0;
			else
				dirs[1]=2;
			double rand=Math.random();
			if(rand<0.4)
				latestMove=dirs[0];
			else if(rand<0.8)
				latestMove=dirs[1];
			else if(rand<0.9)
				latestMove=(dirs[0]+2)%4;
			else
				latestMove=(dirs[1]+2)%4;
			direction_=latestMove;
			incImageIndex();
			hitWall=false;

		}
		remianingSteps--;
		int[][]playerPos=GameConfigFile.playerPosiontions;
		int newX, newY;

		if(latestMove==0){
			newX=xLoc-32;
			newY=yLoc-16-enemySpeed;
			if (newY<0)
				return;
			if(screen.getBitmapCell(newX,newY)>0||screen.getBitmapCell(newX+16,newY)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}

			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&newY/16==playerPos[i][1]))
					return;
			}
		}else if (latestMove==2){
			newX=xLoc-32;
			newY=yLoc-16+enemySpeed;
			if (newY+32>Integer.parseInt(GameConfigFile.properties.getProperty("mazeHeight")))
				return;
			if(screen.getBitmapCell(newX,newY+32-1)>0||screen.getBitmapCell(newX+16,newY+32-1)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}
			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&(newY+32-1)/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&(newY+32-1)/16==playerPos[i][1]))
					return;
			}

		}else if (latestMove==1){
			newX=xLoc-32-enemySpeed;
			newY=yLoc-16;
			if (newX<0)
				return;
			if(screen.getBitmapCell(newX,newY)>0||screen.getBitmapCell(newX,newY+16)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}
			for (int i = 0; i < playerPos.length; i++) {
				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&newY/16+1==playerPos[i][1])) {

					return;
				}
			}
		}else if (latestMove==3){
			newX=xLoc-32+enemySpeed;
			newY=yLoc-16;
			if (newX+32>Integer.parseInt(GameConfigFile.properties.getProperty("mazeWidth")))
				return;
			if(screen.getBitmapCell(newX+32-1,newY)>0||screen.getBitmapCell(newX+32-1,newY+16)>0) {
				if (remianingSteps==15)
					hitWall=true;
				return;
			}
			for (int i = 0; i < playerPos.length; i++) {
				if(((newX+32-1)/16==playerPos[i][0]&&newY/16==playerPos[i][1])||((newX+32-1)/16==playerPos[i][0]&&newY/16+1==playerPos[i][1]))
					return;
			}
		}
		else return;
		if (hitWall) {
			hitWall = false;
			remianingSteps=15;
		}
		setLocations(newX+32,newY+16);
	}

//	public void enemyMove(){
//		if(destroyed)
//			return;
//		if(remianingSteps==0){
//			remianingSteps=16;
//			routIdx++;
//			if (routIdx==enemyRoute.length)
//				routIdx=0;
//		}
//		if(remianingSteps==16&&enemyRoute[routIdx]!=-1){
//			direction_=enemyRoute[routIdx];
//		}
//		remianingSteps--;
//		incImageIndex();
//		int newX, newY;
//		GameScreen screen=GameScreen.getInstance();
//		int[][]playerPos=GameConfigFile.playerPosiontions;
//		if(enemyRoute[routIdx]==0){
//			newX=xLoc-32;
//			newY=yLoc-16-enemySpeed;
//			if (newY<0)
//				return;
//			if(screen.getBitmapCell(newX,newY)>0||screen.getBitmapCell(newX+16,newY)>0)
//				return;
//
//			for (int i = 0; i < playerPos.length; i++) {
//				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&newY/16==playerPos[i][1]))
//					return;
//			}
//		}else if (enemyRoute[routIdx]==2){
//			newX=xLoc-32;
//			newY=yLoc-16+enemySpeed;
//			if (newY+32>Integer.parseInt(GameConfigFile.properties.getProperty("mazeHeight")))
//				return;
//			if(screen.getBitmapCell(newX,newY+32-1)>0||screen.getBitmapCell(newX+16,newY+32-1)>0)
//				return;
//			for (int i = 0; i < playerPos.length; i++) {
//				if((newX/16==playerPos[i][0]&&(newY+32-1)/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&(newY+32-1)/16==playerPos[i][1]))
//					return;
//			}
//
//		}else if (enemyRoute[routIdx]==1){
//			newX=xLoc-32-enemySpeed;
//			newY=yLoc-16;
//			if (newX<0)
//				return;
//			if(screen.getBitmapCell(newX,newY)>0||screen.getBitmapCell(newX,newY+16)>0)
//				return;
//			for (int i = 0; i < playerPos.length; i++) {
//				if((newX/16==playerPos[i][0]&&newY/16==playerPos[i][1])||(newX/16+1==playerPos[i][0]&&newY/16+1==playerPos[i][1]))
//					return;
//			}
//		}else if (enemyRoute[routIdx]==3){
//			newX=xLoc-32+enemySpeed;
//			newY=yLoc-16;
//			if (newX+32>Integer.parseInt(GameConfigFile.properties.getProperty("mazeWidth")))
//				return;
//			if(screen.getBitmapCell(newX+32-1,newY)>0||screen.getBitmapCell(newX+32-1,newY+16)>0)
//				return;
//			for (int i = 0; i < playerPos.length; i++) {
//				if(((newX+32-1)/16==playerPos[i][0]&&newY/16==playerPos[i][1])||((newX+32-1)/16==playerPos[i][0]&&newY/16+1==playerPos[i][1]))
//					return;
//			}
//		}
//		else return;
//		setLocations(newX+32,newY+16);
//
//	}
	public int coordinateTransform(int x, int y){
		return (y/16)*26+x/16;
	}

	public int[] tankPositions1(){
		return tankPositions1(xLoc-32,yLoc-16);
	}
	public int[] tankPositions1(int x,int y){
//		if (x==-1 && y==-1)
//		x=xLoc-32;
//		y=yLoc-16;
		int[] res;
		int x_,y_;
		assert x%16==0 || y%16==0;
		if(x%16==0 && y%16==0){
			res=new int[4];
			x_=x/16;
			y_=y/16;
			res[0]=y_*26+x_;
			res[1]=y_*26+x_+1;
			res[2]=(y_+1)*26+x_;
			res[3]=(y_+1)*26+x_+1;
		}
		else if(x%16!=0){
			res=new int[6];
			x_=x/16;
			y_=y/16;
			res[0]=y_*26+x_;
			res[1]=y_*26+x_+1;
			res[2]=y_*26+x_+2;
			res[3]=(y_+1)*26+x_;
			res[4]=(y_+1)*26+x_+1;
			res[5]=(y_+1)*26+x_+2;
		}
		else{
			res=new int[6];
			x_=x/16;
			y_=y/16;
			res[0]=y_*26+x_;
			res[1]=y_*26+x_+1;
			res[2]=(y_+1)*26+x_;
			res[3]=(y_+1)*26+x_+1;
			res[4]=(y_+2)*26+x_;
			res[5]=(y_+2)*26+x_+1;
		}
		return res;
	}
	public int[][] tankPositions2(){
		int x=xLoc-32,y=yLoc-16;
		int[][] res;
		int x_,y_;
		assert x%16==0 || y%16==0;
		if(x%16==0 && y%16==0){
			res=new int[4][2];
			x_=x/16;
			y_=y/16;
			res[0][0]=x_;
			res[0][1]=y_;
			res[1][0]=x_+1;
			res[1][1]=y;
			res[2][0]=x_;
			res[2][1]=y_+1;
			res[3][0]=x_+1;
			res[3][1]=y_+1;

		}
		else if(x%16!=0){
			res=new int[6][2];
			x_=x/16;
			y_=y/16;
			res[0][0]=x_;
			res[0][1]=y_;
			res[1][0]=x_+1;
			res[1][1]=y_;
			res[2][0]=x_;
			res[2][1]=y_+1;
			res[3][0]=x_+1;
			res[3][1]=y_+1;
			res[4][0]=x_+2;
			res[4][1]=y_;
			res[5][0]=x_+2;
			res[5][1]=y_+1;
		}
		else{
			res=new int[6][2];
			x_=x/16;
			y_=y/16;
			res[0][0]=x_;
			res[0][1]=y_;
			res[1][0]=x_+1;
			res[1][1]=y;
			res[2][0]=x_;
			res[2][1]=y_+1;
			res[3][0]=x_+1;
			res[3][1]=y_+1;
			res[4][0]=x_;
			res[4][1]=y_+2;
			res[5][0]=x_+1;
			res[5][1]=y_+2;
		}
		return res;
	}

	public int useBomb(int timerThreshold) {
		if (bombUsed == true)
			return 1;
		GameScreen gameScreen = GameScreen.getInstance();
		GameObject gameObject = gameScreen.getGameObject(0, 1); // get bombman
		bombUsed = true;
		bombTimerThreshold = timerThreshold;
		bombTimer = bombTimerThreshold;
		nEnd = gameScreen.mazeHeight;
		sEnd = gameScreen.mazeHeight;
		wEnd = gameScreen.mazeWidth;
		eEnd = gameScreen.mazeWidth;

		int bombman_x = gameObject.getXLoc();
		int bombman_y = gameObject.getYLoc();
		this.xLoc = (bombman_x / gameScreen.cellWidth) * gameScreen.cellWidth;
		this.yLoc = (bombman_y / gameScreen.cellHeight) * gameScreen.cellHeight;
//		System.out.printf("[Bomb] Place Bomb, place: " + this.xLoc + " " + this.yLoc + " \n");
		// flame
		flame_x = this.xLoc;
		flame_y = this.yLoc;
		blastSteps = 0;
		flameTimer = flameStepTimerThreshold;

		// change map
//    	gameScreen.editBitmapCell(this.yLoc / gameScreen.cellHeight, this.xLoc / gameScreen.cellWidth, 1);
//    	gameScreen.editBitmapCell(this.yLoc, this.xLoc, 1);
		gameScreen.editBitmapCell(this.xLoc, this.yLoc, 1);
		return 0;
	}

	public void bombExplosion() {
		// play sound, todo (play sound, only once)
		if (!bombPlaySound) {
			GameScreen gameScreen = GameScreen.getInstance();
			gameScreen.playSound(4, false);
			bombPlaySound = true;
		}
		// update flame steps
//    	System.out.printf("explosion, blastSteps: %d, blastStepsTimer: %d\n", blastSteps, flameTimer);
		flameTimer -= 1;
		if (flameTimer <= 0) {
			flameTimer = flameStepTimerThreshold;
			blastSteps += 1;
		}
		if (blastSteps > 4) {
			blastSteps = 0;
			// power up
			bombPowerUpAct();
			return; // explosion end
		}
		flameImgs.clear();
		String fireFileCenter = GameConfigFile.properties.getProperty("blastCenterImage" + (blastSteps - 1)); // 0,1,2,3
		String fireFileMiddel = GameConfigFile.properties.getProperty("blastMiddileImage" + (blastSteps - 1));
		String fireFileEdge = GameConfigFile.properties.getProperty("blastEdgeImage" + (blastSteps - 1));
		if (fireFileCenter != null) {
//    		System.out.printf("reading flame pic 1\n");
			flameImgs.add(createImage(fireFileCenter));
		}
		if (fireFileMiddel != null) {
//    		System.out.printf("reading flame pic 2\n");
			flameImgs.add(createImage(fireFileMiddel));
		}
		if (fireFileEdge != null) {
//    		System.out.printf("reading flame pic 3\n");
			flameImgs.add(createImage(fireFileEdge));
		}
	}

	public void bombPowerUp() {
		// power up
		bombPowerUp = true;

		GameScreen gameScreen = GameScreen.getInstance();
		GameTextObject textObject = (GameTextObject) gameScreen.getGameObject(powerUpId, textType);
		if (textObject == null) {
			String powerStr = "Power Up !!!";
			textObject = new GameTextObject(powerUpId, textDisplayX, textDisplayY);
			try {
				textObject.setColor(textColor);
			} catch (GameException e) {
				e.printStackTrace();
			}
			textObject.setText(powerStr);
			gameScreen.addGameObject(powerUpId, (GameObject) textObject, textType);
		}
		textObject.setXLoc(textDisplayX);
		textObject.setYLoc(textDisplayY);
		powerTextTimer = powerTextTimerThres;
	}

	public void bombPowerUpAct() {
		if (bombPowerUp) {
			bombPower++;
			bombPowerUp = false;
		}
	}


	public void destroyBomb() {
		bombUsed = false;
		bombTimer = bombTimerThreshold;
		GameScreen gameScreen = GameScreen.getInstance();
//    	gameScreen.editBitmapCell(this.yLoc / gameScreen.cellHeight, this.xLoc / gameScreen.cellWidth, 0);
//    	gameScreen.editBitmapCell(this.yLoc, this.xLoc, 0);
		gameScreen.editBitmapCell(this.xLoc, this.yLoc, 0);
		this.xLoc = 1000;
		this.yLoc = 1000;
		// flame
		blastSteps = 1;
		bombPlaySound = false;
	}

	/**
	 * check explosion collision
	 *
	 * @param x
	 * @param y
	 * @return 0, no collision; 1, collision
	 */
	public int checkExplosionCollision(int x, int y) {
		int centerX = flame_x;
		int centerY = flame_y;
		int delta = Integer.parseInt(GameConfigFile.properties.getProperty("objectSize"));
		/*
		 * power = 1 if (y + delta - 1 < centerY - delta + 1) { return 0; } if (y >
		 * centerY + 2*delta - 1) return 0; if (x > centerX + 2*delta - 1) return 0; if
		 * (x < centerX - delta + 1) return 0; if (x > centerX + delta - 1 && y + delta
		 * - 1 < centerY) return 0; if (x +delta - 1 < centerX && y + delta -1 <
		 * centerY) return 0; if (x > centerX + delta -1 && y > centerY + delta -1)
		 * return 0; if (x +delta -1 < centerX && y > centerY + delta -1) return 0;
		 */
//    	System.out.printf("check collision, flame & obj: " +centerX+" "+centerY+" "+x+" "+y+ "\n");
//    	System.out.printf("delta: %d\n", delta);
		if (y < centerY - (1 + Math.min(nEnd, bombPower)) * delta + 1)
			return 0;
		if (y > centerY + (1 + Math.min(sEnd, bombPower)) * delta - 1)
			return 0;
		if (x > centerX + (1 + Math.min(eEnd, bombPower)) * delta - 1)
			return 0;
		if (x < centerX - (1 + Math.min(wEnd, bombPower)) * delta + 1)
			return 0;

		if (x > centerX + delta - 1 && y + delta - 1 < centerY)
			return 0;
		if (x + delta - 1 < centerX && y + delta - 1 < centerY)
			return 0;
		if (x > centerX + delta - 1 && y > centerY + delta - 1)
			return 0;
		if (x + delta - 1 < centerX && y > centerY + delta - 1)
			return 0;
		return 1;
	}

	public MazeGameObject(int id, int x, int y, int type) {
		super(id, x, y, type);

		if (type == 2) {
			remaining_monster_num += 1;
			total_monster_num += 1;
		}

		if (type<=4 && type>=1){
			int x_=GameConfigFile.POS.get(GameConfigFile.properties.getProperty("type"+type))[0];
			int y_=GameConfigFile.POS.get(GameConfigFile.properties.getProperty("type"+type))[1];
			int w=Integer.parseInt(GameConfigFile.properties.getProperty("object"+type+"Size"));
			MultiDirImages(x_,y_,w);
		}
		else
		if(type==5){//Exploded tank
			int numImages=3;
			int x_=GameConfigFile.POS.get("bulletBomb")[0];
			int y_=GameConfigFile.POS.get("bulletBomb")[1];
			int w=32;
			String f=GameConfigFile.properties.getProperty("ImageAll");
			Sprite sp=new Sprite(f);
			int[][] arr={{0,0},{1,0},{2,0}};
			for (int i = 0; i < arr.length; i++) {
				Image im=sp.getImage(x_+arr[i][0]*w,y_+arr[i][1]*w,w,w);
				images.add(im);
			}
		}
		else if(type==6){//broken home
			int x_=GameConfigFile.POS.get("home")[0]+32;
			int y_=GameConfigFile.POS.get("home")[1];
			int w=32;
			String f=GameConfigFile.properties.getProperty("ImageAll");
			Sprite sp=new Sprite(f);
			Image im=sp.getImage(x_,y_,w,w);
			images.add(im);
		}
		else if(type==7){//Game Over
			int x_=GameConfigFile.POS.get("over")[0];
			int y_=GameConfigFile.POS.get("over")[1];
//				int w=32;
			String f=GameConfigFile.properties.getProperty("ImageAll");
			Sprite sp=new Sprite(f);
			Image im=sp.getImage(x_,y_,64,32);
			images.add(im);
		}

	}

	public void useImagePack(int pack) {
		Image img;
		BufferedImage imgCopy;
		curImageIndex = 0;
		int imageNum = 0;

		images.clear();
		String file = GameConfigFile.properties.getProperty("object" + type + "Pack" + pack + "Image" + imageNum);

		while (file != null) {

			img = GameConfigFile.imageArchive.get(file);
			if (img == null) {
				img = createImage(file); // MARS brash here

				GameConfigFile.imageArchive.put(file, img);
			}
			imgCopy = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			imgCopy.createGraphics().drawImage(img, 0, 0, null); // todo, change pack
			images.add(imgCopy);
			imageNum++;
			file = GameConfigFile.properties.getProperty("object" + type + "Pack" + pack + "Image" + imageNum);
		}
	}

	public void MultiDirImages(int x, int y, int w){
		String f=GameConfigFile.properties.getProperty("ImageAll");
		Sprite sp=new Sprite(f);
		int[][] arr={{0,0},{2,0},{1,0},{3,0}};
		for (int i = 0; i < arr.length; i++) {
			Image im=sp.getImage(x+arr[i][0]*w,y+arr[i][1]*w,w,w);
			images.add(im);
		}

	}


	private Image createImage(String imgName) {

		try {
			GameScreen gameScreen = GameScreen.getInstance();
		} catch (Exception e) {
			System.out.printf(imgName);
			System.err.println("Internal Error: images folder or file not found");

			System.exit(0); // MARS crash here
		}
		URL im = this.getClass().getResource(imgName);

		if (im == null) {
			System.err.println("Internal Error: images folder or file not found");
			System.exit(0);
		}
		try {
			// return ImageIO.read(im);
			return (new ImageIcon(im)).getImage();
		} catch (Exception e) {
			System.err.println("Internal Error: images folder or file not found");
			System.exit(0);
		}
		return null;
	}

	/**
	 * 对图片进行旋转
	 *
	 * @param src   被旋转图片
	 * @param angel 旋转角度
	 * @return 旋转后的图片
	 */
	public BufferedImage Rotate(Image src, int angel) {
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		// 计算旋转后图片的尺寸
		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = res.createGraphics();
		// 进行转换
		g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
		g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

		g2.drawImage(src, null, null);
		return res;
	}

	/**
	 * 计算旋转后的图片
	 *
	 * @param src   被旋转的图片
	 * @param angel 旋转角度
	 * @return 旋转后的图片
	 */
	public Rectangle CalcRotatedSize(Rectangle src, int angel) {
		// 如果旋转的角度大于90度做相应的转换
		if (angel >= 90) {
			if (angel / 90 % 2 == 1) {
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new Rectangle(new Dimension(des_width, des_height));
	}

	ArrayList<Integer> xypair;
	int objectSize;
	boolean enableDrawBrickWall = false;
	int drawBrickWall = 1;

	public void drawBrickWallBreak(ArrayList<Integer> xypairTmp, int objectSizeTmp) {
		xypair = xypairTmp;
		objectSize = objectSizeTmp;
		enableDrawBrickWall = true;
		drawBrickWall = 1;
	}

	@Override
	public void paint(Graphics graph) {
		Graphics2D g2 = (Graphics2D) graph;
//        g2.drawImage(images.get(curImageIndex), xLoc, yLoc, null);

		int objectSize = Integer.parseInt(GameConfigFile.properties.getProperty("object"+type+"Size"));

		// where to draw objects
//        g2.drawImage(images.get(curImageIndex), xLoc, yLoc, objectSize, objectSize, null);
		int objectScale = 0;
		if (type!=7)
			g2.drawImage(images.get(curImageIndex), xLoc, yLoc, objectSize - objectScale, objectSize - objectScale, null);
		else{
			int width=Integer.parseInt(GameConfigFile.properties.getProperty("obj7width"));
			g2.drawImage(images.get(0), xLoc, yLoc, 64, 32, null);
		}
//        System.out.printf("[flame] blastSteps: "+blastSteps + "\n");
		GameScreen gameScreen = GameScreen.getInstance();
		if (blastSteps > 0) { // draw flame
			// center
//        	System.out.printf("size: " + flameImgs.size()+"\n");
//        	System.out.printf("size: %d\n", flameImgs.size());
			if (flameImgs.size() > 0)
				g2.drawImage(flameImgs.get(0), flame_x, flame_y, objectSize - objectScale, objectSize - objectScale,
						null);
			// middle
//        	System.out.printf("size: %d\n", flameImgs.size());
			if (flameImgs.size() > 1) {
				for (int i = 1; i < bombPower; ++i) {
					// n
					if (gameScreen.getBitmapCell(flame_x, flame_y - objectSize * i) == 0 && nEnd >= i)
						g2.drawImage(Rotate(flameImgs.get(1), 0), flame_x, flame_y - objectSize * i + 1,
								objectSize - objectScale, objectSize - objectScale, null);
					else
						nEnd = Math.min(i, nEnd);
					// s
					if (gameScreen.getBitmapCell(flame_x, flame_y + objectSize * i) == 0 && sEnd >= i)
						g2.drawImage(Rotate(flameImgs.get(1), 180), flame_x, flame_y + objectSize * i - 1,
								objectSize - objectScale, objectSize - objectScale, null);
					else
						sEnd = Math.min(i, sEnd);
					// w
					if (gameScreen.getBitmapCell(flame_x - objectSize * i, flame_y) == 0 && wEnd >= i)
						g2.drawImage(Rotate(flameImgs.get(1), 270), flame_x - objectSize * i + 1, flame_y,
								objectSize - objectScale, objectSize - objectScale, null);
					else
						wEnd = Math.min(i, wEnd);
					// e
					if (gameScreen.getBitmapCell(flame_x + objectSize * i, flame_y) == 0 && eEnd >= i)
						g2.drawImage(Rotate(flameImgs.get(1), 90), flame_x + objectSize * i - 1, flame_y,
								objectSize - objectScale, objectSize - objectScale, null);
					else
						eEnd = Math.min(i, eEnd);
				}
			}
//        	System.out.printf("size: %d\n", flameImgs.size());
			if (flameImgs.size() > 2) {
				// edge
				// n
				if (gameScreen.getBitmapCell(flame_x, flame_y - objectSize * bombPower) == 0 && nEnd >= bombPower)
					g2.drawImage(Rotate(flameImgs.get(2), 0), flame_x, flame_y - objectSize * bombPower + 1,
							objectSize - objectScale, objectSize - objectScale, null);
				// s
				if (gameScreen.getBitmapCell(flame_x, flame_y + objectSize * bombPower) == 0 && sEnd >= bombPower)
					g2.drawImage(Rotate(flameImgs.get(2), 180), flame_x, flame_y + objectSize * bombPower - 1,
							objectSize - objectScale, objectSize - objectScale, null);
				// w
				if (gameScreen.getBitmapCell(flame_x - objectSize * bombPower, flame_y) == 0 && wEnd >= bombPower)
					g2.drawImage(Rotate(flameImgs.get(2), 270), flame_x - objectSize * bombPower + 1, flame_y,
							objectSize - objectScale, objectSize - objectScale, null);
				// e
				if (gameScreen.getBitmapCell(flame_x + objectSize * bombPower, flame_y) == 0 && eEnd >= bombPower)
					g2.drawImage(Rotate(flameImgs.get(2), 90), flame_x + objectSize * bombPower - 1, flame_y,
							objectSize - objectScale, objectSize - objectScale, null);
			}
//        	System.out.printf("After Pic End: %d, %d, %d, %d\n", nEnd,sEnd,wEnd,eEnd);
//        	System.out.printf("cell: "+gameScreen.getBitmapCell(flame_x, flame_y+objectSize)+"\n");
//        	System.out.printf("true? : "+(gameScreen.getBitmapCell(flame_x, flame_y+objectSize) == 0)+"\n");
		}
//        System.out.printf("pic: %d, %b\n", drawBrickWall, enableDrawBrickWall);
		if (enableDrawBrickWall) {
			for (int i = 0; i < xypair.size(); i += 2) {
				int x = xypair.get(i);
				int y = xypair.get(i + 1);
				String figPath;
				String tileFile;
				if (drawBrickWall == 7) { // grass
					tileFile = GameConfigFile.properties.getProperty("grass");
					gameScreen.mazeImage.graphics2D.drawImage(gameScreen.mazeImage.createImage(tileFile, 0), x, y,
							objectSize, objectSize, null);
				} else {
					figPath = "brick_wall_break" + drawBrickWall;
					tileFile = GameConfigFile.properties.getProperty(figPath); // 1->6
					gameScreen.mazeImage.graphics2D.drawImage(gameScreen.mazeImage.createImage(tileFile, 0), x, y,
							objectSize, objectSize, null);
				}
//            	System.out.printf("pic: %s, %d, %b\n", tileFile, drawBrickWall, enableDrawBrickWall);
			}
			if (drawBrickWall == 7) {
//				System.out.printf("brick wall crashed\n");
				enableDrawBrickWall = false;
			}
			drawBrickWall++;
		}
	}

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public void deductHitPoint(int p) {

	}

	public int getHitPoint() {
		return this.hitPoint;
	}

	@Override
	public void setDirection(boolean right) {
		this.isRight = right;
	}

	@Override
	public boolean getDirection() {
		return this.isRight;
	}

	@Override
	public void setSpeed(int v) {
		this.speed = v;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	public void setHitPoint(int point) {
		this.hitPoint = point;
	}

	@Override
	public void updateLocation() {
		if (this.isRight) {
			xLoc += speed;
		} else {
			xLoc -= speed;
		}
	}

	public boolean isIntersected(int x, int y, int width, int height) {
		Image img = images.get(curImageIndex);
		int objWidth = img.getWidth(null);
		int objHeight = img.getHeight(null);

		if (xLoc > (x + width - 1) || x > (xLoc + objWidth - 1) || yLoc > (y + height - 1)
				|| y > (yLoc + objHeight - 1))
			return false;
		return true;
	}

	private double getSqDist(int x1, int y1, int x2, int y2) {
		return Math.abs(Math.pow((double) (x1 - x2), 2.0) + Math.pow((double) (x1 - x2), 2.0));
	}

	private void resetMarkedRoute() {
		for (int i = 0; i < markedRoute.length; i++)
			for (int j = 0; j < markedRoute[i].length; j++)
				markedRoute[i][j] = 0;
	}

	private void resetRouteState() {
		seekTarget = false;
		direction = -1;

	}

	private void markRoute(int x, int y, int cellWidth, int cellHeight) {
		int row = y / cellHeight;
		int col = x / cellWidth;
		if (x % cellWidth == 0 && y % cellHeight == 0) {
			if (markedRoute[row][col] == Integer.MAX_VALUE) // too large
				markedRoute[row][col] = 2;
			else
				markedRoute[row][col]++;
		}

		// debug
//		System.out.printf("[Route] row, col: "+row+" "+col+" , value: "+markedRoute[row][col]+"\n");

	}

	private int getMarkedRoute(int x, int y, int cellWidth, int cellHeight) {
		return markedRoute[y / cellHeight][x / cellWidth];
	}

	private void loadRoute(GameScreen game) {
		GameConfigFile.prevRouteIndex = (GameConfigFile.prevRouteIndex + 1) % GameConfigFile.routeList.size();

//	  System.out.printf("GameConfigFile.routeList.size(): "+GameConfigFile.routeList.size()+ "\n");

		route = GameConfigFile.routeList.get(GameConfigFile.prevRouteIndex);
		routeStepIndex = 0;
		action = null;
		markedRoute = new int[game.bitmapRow][];
		for (int i = 0; i < markedRoute.length; i++)
			markedRoute[i] = new int[game.bitmapColumn];
		resetRouteState();
	}

	// route algorithm
	public void nextRouteStep(GameScreen game, MazeGameObject mainTarget) {
		incImageIndex();
		if (route == null) {
			loadRoute(game);
		}

		boolean reached = false;
		int randLoc[];
		int realDestX, realDestY;
		int centerX = xLoc + images.get(curImageIndex).getWidth(null) / 2;
		int centerY = yLoc + images.get(curImageIndex).getHeight(null) / 2;

		// check whether previous route step reaches the destination
		if (action == null) // initial value
			reached = true;
		else if (action.equals("r")) {
			if (destX == centerX && destY == centerY) {
				routeStepIndex++;
				reached = true;
//				System.err.println("r " + destX + " " + destY);
			}
		} else if (action.equals("d")) {
			if (destX == centerX && destY == centerY) {
				routeStepIndex += 3; // "d x y"
				reached = true;
				// System.err.println("d " + destX + " " + destY);
			}
		} else if (action.equals("o")) {
			Image img = mainTarget.images.get(mainTarget.curImageIndex);
			if (isIntersected(mainTarget.getXLoc(), mainTarget.getYLoc(), img.getWidth(null), img.getHeight(null))) {
				routeStepIndex++;
				reached = true;
				// System.err.println("a " + xLoc + " " + yLoc);
			}
		}

		// load next route step
		if (reached) {
			resetMarkedRoute();
			routeStepIndex %= route.size();
			action = (String) route.get(routeStepIndex);

//		  System.out.printf("action: "+action+ " , route.size="+route.size()+"\n");

			if (action.equals("r")) {
				randLoc = game.getRandomPath();
				destX = randLoc[0] + images.get(curImageIndex).getWidth(null) / 2;
				destY = randLoc[1] + images.get(curImageIndex).getHeight(null) / 2;
				// System.err.println("r create " + destX + " " + destY);
			} else if (action.equals("d")) {
				destX = ((Integer) route.get(routeStepIndex + 1)).intValue()
						+ images.get(curImageIndex).getWidth(null) / 2;
				destY = ((Integer) route.get(routeStepIndex + 2)).intValue()
						+ images.get(curImageIndex).getHeight(null) / 2;
			}
		}

		if (action.equals("o")) {
			destX = mainTarget.getXLoc() + mainTarget.images.get(mainTarget.curImageIndex).getWidth(null) / 2;
			destY = mainTarget.getYLoc() + mainTarget.images.get(mainTarget.curImageIndex).getHeight(null) / 2;
			if (destX < 0)
				destX = 0;
			else if (destX >= game.mazeWidth)
				destX = game.mazeWidth;

			if (destY < 0)
				destY = 0;
			else if (destY >= game.mazeHeight)
				destY = game.mazeHeight;
		}
		realDestX = destX;
		realDestY = destY;
//		System.out.printf("[Route] target, x, y "+destX+" "+destY+ "; my x y:"+this.xLoc+ ", "+ this.yLoc +" \n");

		int seekDist = Integer.parseInt(GameConfigFile.properties.getProperty("seekDistance"));

		// random tranport for level 2
//		if (game.getLevel() >= 2 && (Math.abs(xLoc - mainTarget.getXLoc()) >= farDist || Math.abs(yLoc - mainTarget.getYLoc()) >= farDist)) // too far from main target
//			if (GameConfigFile.rand.nextFloat() < Float.parseFloat(GameConfigFile.properties.getProperty("teleportChance")) ) { // chance for teleport
//			  randLoc = game.getRandomPath();
//			  while (Math.abs(randLoc[0] - mainTarget.getXLoc()) > seekDist || Math.abs(randLoc[1] - mainTarget.getYLoc()) > seekDist)
//			    randLoc = game.getRandomPath();
//			  resetRouteState();
//			  resetMarkedRoute();
//			  setLocations(randLoc[0], randLoc[1]);
//			  return;
//			}

		// seek target when it is nearby
		if (Math.abs(xLoc - mainTarget.getXLoc()) <= seekDist && Math.abs(yLoc - mainTarget.getYLoc()) <= seekDist) {
			realDestX = mainTarget.getXLoc() + mainTarget.images.get(mainTarget.curImageIndex).getWidth(null) / 2;
			realDestY = mainTarget.getYLoc() + mainTarget.images.get(mainTarget.curImageIndex).getHeight(null) / 2;
			if (!seekTarget) {
				seekTarget = true;
				resetMarkedRoute();
			}
//			System.out.printf("Seeking Target!\n");
		} // get escaped
		else if (seekTarget) {
			seekTarget = false;
			resetMarkedRoute();
		}

		// find out which direction is not blocked by wall
		int[] dir = {0, 0, 0, 0}; // 1 for a path

		// bug here, pic width should be cell width
		int rightX = xLoc + images.get(curImageIndex).getWidth(null) - 1;
		int bottomY = yLoc + images.get(curImageIndex).getHeight(null) - 1;
		// this code makes the maze border block this object's movement; if not, (x,y)
		// outside the border may make the distance computed

		// debug

		if (game.getBitmapCell(xLoc, yLoc - speed) == 0 && game.getBitmapCell(rightX, yLoc - speed) == 0) // north move
		// not
		// blocked

		{
			dir[0] = 1;

		}
		if (game.getBitmapCell(xLoc - speed, yLoc) == 0 && game.getBitmapCell(xLoc - speed, bottomY) == 0) // west move
		// not
		// blocked
		{
			dir[1] = 1;

		}
		if (game.getBitmapCell(xLoc, bottomY + speed) == 0 && game.getBitmapCell(rightX, bottomY + speed) == 0) // south
		// move
		// not
		// blocked
		{
			dir[2] = 1;

		}
		if (game.getBitmapCell(rightX + speed, yLoc) == 0 && game.getBitmapCell(rightX + speed, bottomY) == 0) // east
		// move
		// not
		// blocked
		{
			dir[3] = 1;

		}

//		GameScreen gameScreen = GameScreen.getInstance();
//		MazeGameObject mazeGameObject = (MazeGameObject)gameScreen.getGameObject(0, 0);
//		if (mazeGameObject.bombUsed == true) {
//			System.out.printf("[Route] route dir: "+dir[0]+" "+dir[1]+" "+dir[2]+" "+ dir[3] + "\n");
//			System.out.printf("[Route] west maze " + game.getBitmapCell(xLoc - speed, yLoc)+" "+ game.getBitmapCell(xLoc - speed, bottomY)+ "\n");
//			System.out.printf("[Route] direction " + direction+ "\n");
//		}
//		System.out.printf("pic width: %d, height: %d\n", images.get(curImageIndex).getWidth(null), images.get(curImageIndex).getHeight(null));
//		System.out.printf("[Route] north maze " + game.getBitmapCell(xLoc, yLoc - speed)+" "+ game.getBitmapCell(rightX, yLoc - speed)+ "\n");
//		System.out.printf("[Route] west maze " + game.getBitmapCell(xLoc - speed, yLoc)+" "+ game.getBitmapCell(xLoc - speed, bottomY)+ "\n");
//		System.out.printf("[Route] south maze " + game.getBitmapCell(xLoc, bottomY + speed)+" "+ game.getBitmapCell(rightX, bottomY + speed)+ "\n");
//		System.out.printf("[Route] east maze " + game.getBitmapCell(rightX + speed, yLoc)+" "+ game.getBitmapCell(rightX + speed, yLoc)+ "\n");
//		// debug

////		System.out.printf("[Route] north loc " + xLoc+" "+ (yLoc-speed)+" "+ game.getBitmapCell(xLoc, yLoc - speed)+ "\n");
//		System.out.printf("[Route] south loc " + xLoc+" "+ (bottomY + speed)+" "+ " " +" "+ "\n");

//		System.out.printf("hahaha");
//		
//		

		int pathNum = dir[0] + dir[1] + dir[2] + dir[3];
		int[][] dirChange = {{0, -speed}, {-speed, 0}, {0, speed}, {speed, 0}}; // (x, y) change
		int[][] dirCellChange = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // (row, col) change
		int row, col, oppositeDir;

		if (pathNum == 1) { // dead end
			markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight); // mark the current cell as going back from it
			for (int i = 0; i < 4; i++)
				if (dir[i] != 0) {
					direction = i;
					setLocations(xLoc + dirChange[i][0], yLoc + dirChange[i][1]);
					markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight);
					return;
				}
		} else if (pathNum == 2) {
			if (direction < 0)
				markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight); // mark the junction cell of the still object as
				// visited
			else
				for (int i = 0; i < 4; i++)
					if (dir[i] != 0 && i != (direction + 2) % 4) { // ignore the direction that goes back if object is
						// not still
						// not turn back
						direction = i;
						setLocations(xLoc + dirChange[i][0], yLoc + dirChange[i][1]);
						markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight);
						return;
					}
		} else {
			if (direction < 0)
				markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight); // mark the junction cell of the still object as
				// visited
			else {
				row = yLoc / game.cellHeight;
				col = xLoc / game.cellWidth;
				oppositeDir = (direction + 2) % 4;

				// key idea
				// problem here

				if (direction >= 0 && markedRoute[row][col] > 1
						&& markedRoute[row + dirCellChange[oppositeDir][0]][col + dirCellChange[oppositeDir][1]] == 1) { // visited
					// junction
					// is
					// dead
					// end
					// when
					// going
					// to
					// it
					// from
					// a
					// new
					// passage
					markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight); // mark the current cell as going back from
					// it
					direction = oppositeDir;
					System.out.printf("turn back\n");
					setLocations(xLoc + dirChange[direction][0], yLoc + dirChange[direction][1]);
					markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight);
					return;
				}
			}
		}

		// if 2 non-blocked ways with still object or at least 3 non-blocked ways, find
		// the way with min distance to go
		int newCenterX, newCenterY;
		double dirDist, minDist0 = Double.MAX_VALUE, minDist1 = Double.MAX_VALUE; // min dist of passage with 0 or 1 in
		// markedRoute
		int minDir0 = -1, minDir1 = -1;

		// System.err.println("dir: " + dir[0] + dir[1] + dir[2] + dir[3]);
		// System.err.println("dead end: " + deadEndDirection[0] + deadEndDirection[1] +
		// deadEndDirection[2] + deadEndDirection[3]);

		row = yLoc / game.cellHeight;
		col = xLoc / game.cellWidth;
		int cellMark;
		for (int i = 0; i < 4; i++) {
			if (dir[i] != 0) {
				newCenterX = centerX + dirChange[i][0];
				/*
				 * if (newCenterX < 0) // needed if this object can move outside and wrap around
				 * the maze newCenterX += game.mazeWidth; else if (newCenterX >= game.mazeWidth)
				 * newCenterX -= game.mazeWidth;
				 */

				newCenterY = centerY + dirChange[i][1];
				/*
				 * if (newCenterY < 0) // needed if this object can move outside and wrap around
				 * the maze newCenterY += game.mazeHeight; else if (newCenterY >=
				 * game.mazeHeight) newCenterY -= game.mazeHeight;
				 */

				cellMark = markedRoute[row + dirCellChange[i][0]][col + dirCellChange[i][1]];
				if (cellMark > 1)
					continue;
				dirDist = getSqDist(newCenterX, newCenterY, realDestX, realDestY);
				if (cellMark == 0 && dirDist < minDist0) {
					minDist0 = dirDist;
					minDir0 = i;
				} else if (cellMark == 1 && dirDist < minDist1) {
					minDist1 = dirDist;
					minDir1 = i;
				}
			}
		}

		if (minDir0 >= 0)
			direction = minDir0;
		else if (minDir1 >= 0)
			direction = minDir1;
		else {
			resetMarkedRoute();
			for (int i = 0; i < 4; i++) {
				if (dir[i] != 0) {
					newCenterX = centerX + dirChange[i][0];
					/*
					 * if (newCenterX < 0) // needed if this object can move outside and wrap around
					 * the maze newCenterX += game.mazeWidth; else if (newCenterX >= game.mazeWidth)
					 * newCenterX -= game.mazeWidth;
					 */

					newCenterY = centerY + dirChange[i][1];
					/*
					 * if (newCenterY < 0) // needed if this object can move outside and wrap around
					 * the maze newCenterY += game.mazeHeight; else if (newCenterY >=
					 * game.mazeHeight) newCenterY -= game.mazeHeight;
					 */

					dirDist = getSqDist(newCenterX, newCenterY, realDestX, realDestY);
					if (dirDist < minDist0) {
						minDist0 = dirDist;
						direction = i;
					}
				}
			}
		}

		// real place to set new location
		if (direction >= 0) {
			setLocations(xLoc + dirChange[direction][0], yLoc + dirChange[direction][1]);
			markRoute(xLoc, yLoc, game.cellWidth, game.cellHeight);
		}
	}

	private void setLocationsWrap(int x, int y, int width, int height) {
		int objWidth = images.get(curImageIndex).getWidth(null);
		int objHeight = images.get(curImageIndex).getHeight(null);
		if ((x + objWidth) < 0)
			x = width - objWidth;
		else if (x >= width)
			x = 0;

		if ((y + objHeight) < 0)
			y = height - objHeight;
		else if (y >= height)
			y = 0;
		setLocations(x, y);
	}
	//	public void setCurImageIndex
	public void incImageIndex() {

		if(type==5){
			interval--;
			if(interval<=0){
				interval=5;
				curImageIndex++;
			}
			if (curImageIndex>=3) {
				setLocations(1000, 1000);
				curImageIndex%=images.size();
			}
		}
		else if (type==6)
			curImageIndex=0;
		else {
			cur_dir_indx++;
			cur_dir_indx = cur_dir_indx % images_per_dir;
			curImageIndex = direction_ * images_per_dir + cur_dir_indx;

			curImageIndex %= images.size();
		}
	}
}
