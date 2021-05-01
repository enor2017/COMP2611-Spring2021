package mars.ext.game;
import mars.ext.game.util.Sprite;
import java.awt.*;


public class GameBulletObject extends GameObject {
    private Image image;

    private int owner; //子弹的所属者
    private int type;  //1、玩家  2、敌方
    private int dir ;
    private int speed = 3;
    private int size = 6;
    private boolean hit = false;
	private boolean isDestroyed = false;

    public GameBulletObject(int id, int x, int y, int owner, int type, int dir) {
        super(id, x, y);
        this.owner = owner;
        this.type = type;
        this.dir = dir;

        Sprite sprite = new Sprite(GameConfigFile.RESOURCE_IMAGE);
        this.image = sprite.getImage(GameConfigFile.POS.get("bullet")[0]+ dir * size, GameConfigFile.POS.get("bullet")[1], size, size);

    }

    public void paint(Graphics graph) {
        Graphics2D g2 = (Graphics2D) graph;
        g2.drawImage(image, xLoc, yLoc, null);
    }

	public void updateLocation() {
        switch (dir) {
            case GameConfigFile.UP:
                yLoc -= this.speed;
            case GameConfigFile.DOWN:
                yLoc += this.speed;
            case GameConfigFile.LEFT:
                xLoc -= this.speed;
            case GameConfigFile.RIGHT:
                xLoc += speed;
        }
    }

}

