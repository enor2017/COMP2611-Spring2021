package mars.ext.game;

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


    private GameConfigFile() {
        // TODO Auto-generated constructor stub

    }

}
