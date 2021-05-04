package mars.ext.game;

import mars.ext.game.util.Sprite;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.HashMap;
import java.awt.Image;

public class GameConfigFile {

    public static Properties properties;
    public static int[] prevImagePackIndex;

    static Random rand;
    static int[] imagePackCount;
    static Hashtable<String, Image> imageArchive;
    static ArrayList<ArrayList> routeList;
    static int prevRouteIndex = -1;

    static final int WALL = 1;
    static final int GRID = 2;
    static final int GRASS = -1;
    static final int WATER = 4;
    static final int ICE = 5;
    static final int HOME = 9;
    static final int ANOTHREHOME = 8;

    /********  Add Props  ***********/
    static final int HEART = 10;
    static final int INVINCIBLE = 11;
    static final int SPEEDUP = 12;
    static final int EXTRABULLETS = 13;
    public static int[] heart_position = new int[2];
    public static int[] invincible_position = new int[2];
    public static int[] speedup_position = new int[2];
    public static int[] extrabullets_position = new int[2];

    /*****  Store props images  *****/
    private final String heart_img_addr = "../../../game/images/heart.png";
    private final String invincible_img_addr = "/game/images/invincible.png";
    private final String speedup_img_addr = "/game/images/speed.png";
    private final String extrabullets_img_addr = "/game/images/extrabullets.png";
    public static BufferedImage heart_img;
    public static BufferedImage invincible_img;
    public static BufferedImage speedup_img;
    public static BufferedImage extrabullets_img;

    static final int offsetX = 32; //主游戏区的X偏移量
    static final int offsetY = 16;//主游戏区的Y偏移量
    static final int tileSize = 16;	//地图块的大小
    static final int tankSize = 16;	//地图块的大小
    static final int homeSize = 32; //家图标的大小

    static final int  UP = 0;
    static final int  DOWN = 1;
    static final int  LEFT = 2;
    static final int  RIGHT = 3;


    static final String SOUND_START = "/game/sounds/start.wav";
    static final String SOUND_MOVE = "/game/sounds/move.wav";
    static final String SOUND_TANKCRACK = "/game/sounds/tankCrack.wav";
    static final String SOUND_BULLETCRACK = "/game/sounds/bulletCrack.wav";
    static final String SOUND_ATTACK = "/game/sounds/attack.wav";
    static final String SOUND_PLAYERCRACK = "/game/sounds/playerCrack.wav";
    static final String SOUND_PROP = "/game/sounds/prop.wav";

    static final String RESOURCE_IMAGE = "/game/images/tankAll.gif";

    static String[] usedGameSounds = {SOUND_START, SOUND_MOVE, SOUND_TANKCRACK, SOUND_BULLETCRACK, SOUND_ATTACK, SOUND_PLAYERCRACK, SOUND_PROP};
    public static int[][] playerPosiontions;
    public static Map<String, int[]> POS = new HashMap<String, int[]>();
    static {
        GameConfigFile.POS.put("selectTank", new int[]{128, 96});
        GameConfigFile.POS.put("stageLevel", new int[]{396, 96});
        GameConfigFile.POS.put("num", new int[]{256, 96});
        GameConfigFile.POS.put("map", new int[]{0, 96});
        GameConfigFile.POS.put("home", new int[]{256, 0});
        GameConfigFile.POS.put("score", new int[]{0, 112});
        GameConfigFile.POS.put("tank", new int[]{0, 0});
        GameConfigFile.POS.put("protected", new int[]{160, 96});
        GameConfigFile.POS.put("enemyBefore", new int[]{256, 32});
        GameConfigFile.POS.put("enemy", new int[]{0, 32});
        GameConfigFile.POS.put("enemy2", new int[]{128, 32});
        GameConfigFile.POS.put("enemy3", new int[]{0, 64});
        GameConfigFile.POS.put("bullet", new int[]{80, 96});
        GameConfigFile.POS.put("enemyBullet", new int[]{80, 96});
        GameConfigFile.POS.put("tankBomb", new int[]{0, 160});
        GameConfigFile.POS.put("bulletBomb", new int[]{320, 0});
        GameConfigFile.POS.put("over", new int[]{384, 64});
        GameConfigFile.POS.put("prop", new int[]{256, 110});
    }


    public GameConfigFile() {
        // TODO Auto-generated constructor stub

        /*****  Load props images into BufferedImage variables.  *****/
//        Sprite heart_spr = new Sprite(heart_img_addr);
//        heart_img = heart_spr.getCurrentImage();
//        Sprite inv_spr = new Sprite(invincible_img_addr);
//        invincible_img = inv_spr.getCurrentImage();
//        Sprite speed_spr = new Sprite(speedup_img_addr);
//        speedup_img = speed_spr.getCurrentImage();
//        Sprite bullet_spr = new Sprite(extrabullets_img_addr);
//        extrabullets_img = bullet_spr.getCurrentImage();
//        System.out.println("Props Images Loaded!");
    }

}
