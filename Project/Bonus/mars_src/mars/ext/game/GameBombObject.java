package mars.ext.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;


public class GameBombObject extends GameObject implements
        ActiveElementInterface {
    public static final int HIT_POINT = 1;
    private Image image;
    private Image explodeImage;
    private int speed;
    private int hitPoint;

    public GameBombObject(int id, int x, int y, String imgName,
                          String explodeImgName) {
        super(id, x, y);
        speed = 0;
        hitPoint = HIT_POINT;
        URL im = this.getClass().getResource(imgName);
        if (im == null) {
            System.err.println("Internal Error: images folder or file not found");
            System.exit(0);
        }
        image = Toolkit.getDefaultToolkit().getImage(im);
        URL expim = this.getClass().getResource(explodeImgName);
        if (expim == null) {
            System.err.println("Internal Error: images folder or file not found");
            System.exit(0);
        }
        explodeImage = Toolkit.getDefaultToolkit().getImage(expim);
    }

    public GameBombObject(int id, String imgName, String explodeImgName) {
        this(id, 0, 0, imgName, explodeImgName);
    }

    @Override
    public void paint(Graphics graph) {
        Graphics2D g2 = (Graphics2D) graph;
        boolean isDestroyed = (this.hitPoint == 0) ? true : false;
        if (!isDestroyed) {
            g2.drawImage(image, xLoc, yLoc, null);
        } else {
            g2.drawImage(explodeImage, xLoc, yLoc, null);
        }
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public void deductHitPoint(int p) {
        this.hitPoint = this.hitPoint - p;
        if (this.hitPoint < 0) {
            this.hitPoint = 0;
        }
    }

    @Override
    public int getHitPoint() {
        return this.hitPoint;
    }

    @Override
    public void setDirection(boolean right) {
        return;
    }

    @Override
    public boolean getDirection() {
        return true;
    }

    @Override
    public void setSpeed(int v) {
        this.speed = v;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void updateLocation() {
        yLoc -= this.speed;
    }

}
