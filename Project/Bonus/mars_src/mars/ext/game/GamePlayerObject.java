package mars.ext.game;

import mars.ext.game.util.Sprite;

import java.awt.*;
import java.net.URL;


public class GamePlayerObject extends GameObject {
    /**
     * 坦克基类
     * @returns
     */
    private int size = 32;//坦克的大小
    private int dir = GameConfigFile.UP;//方向0：上 1：下 2：左3：右
    private int speed = 2;//坦克的速度
    private int frame = 0;//控制敌方坦克切换方向的时间
    private boolean hit = false; //是否碰到墙或者坦克
    private boolean isAI = false; //是否自动
    private boolean isShooting = false;//子弹是否在运行中
    private GameBulletObject bullet = null;//子弹
    private double shootRate = 0.6;//射击的概率
    private boolean isDestroyed = false;
    private int tempX = 0;
    private int tempY = 0;

    private Image image;

    private int lives = 3;//生命值
    private boolean isProtected = true;//是否受保护
    private int protectedTime = 500;//保护时间

    public GamePlayerObject(int id, int x, int y) {
        super(id, x, y);
        Sprite sprite = new Sprite(GameConfigFile.RESOURCE_IMAGE);
        this.image = sprite.getImage(GameConfigFile.POS.get("player")[0] + GameConfigFile.offsetX + dir * size, GameConfigFile.POS.get("player")[1], size, size);

    }

    @Override
    public void paint(Graphics graph) {
        Graphics2D g2 = (Graphics2D) graph;
        this.hit = false;
        g2.drawImage(image, xLoc, yLoc, null);


    }


}
