package mars.ext.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameRemoteBombObject extends GameObject implements
        ActiveElementInterface
{
    public static final int HIT_POINT = 1;
    private Image activeImage;
    private Image inactiveImage;
    private Image explodeImage;
    private boolean isActive;
    private int hitPoint;
    private int speed;

    public GameRemoteBombObject(int id, int x, int y, String activeImgName,
            String inactiveImgName, String explodeImgName)
    {
        super(id, x, y);
        hitPoint = HIT_POINT;
        speed = 0;
        isActive = false;
        URL im = this.getClass().getResource(activeImgName);
        if (im == null)
        {
        	System.err.println("Internal Error: images folder or file not found");
            System.exit(0);
        }
		activeImage = Toolkit.getDefaultToolkit().getImage(im);
		im = this.getClass().getResource(inactiveImgName);
        if (im == null)
        {
        	System.err.println("Internal Error: images folder or file not found");
            System.exit(0);
        }
		inactiveImage = Toolkit.getDefaultToolkit().getImage(im);
		im = this.getClass().getResource(explodeImgName);
        if (im == null)
        {
        	System.err.println("Internal Error: images folder or file not found");
            System.exit(0);
        }
		explodeImage = Toolkit.getDefaultToolkit().getImage(im);
    }

    public GameRemoteBombObject(int id, String activeImgName,
            String inactiveImgName, String explodeImgName)
    {
        this(id, 0, 0, activeImgName, inactiveImgName, explodeImgName);
    }

    public boolean isActive()
    {
        return this.isActive;
    }

    public void setActive(boolean active)
    {
        this.isActive = active;
    }

    @Override
    public void paint(Graphics graph)
    {
        Graphics2D g2 = (Graphics2D) graph;
        boolean isExploded = (this.hitPoint == 0) ? true : false;
        if (isExploded)
        {
            g2.drawImage(explodeImage, xLoc, yLoc, null);
        }
        else if (isActive)
        {
            g2.drawImage(activeImage, xLoc, yLoc, null);
        }
        else
        {
            g2.drawImage(inactiveImage, xLoc, yLoc, null);
        }

    }

    @Override
    public int getScore()
    {
        return 0;
    }

    @Override
    public void deductHitPoint(int p)
    {
        this.hitPoint -= p;
        if (this.hitPoint < 0)
        {
            this.hitPoint = 0;
        }
    }

    @Override
    public int getHitPoint()
    {
        return this.hitPoint;
    }

    @Override
    public void setDirection(boolean right)
    {
        return;
    }

    @Override
    public boolean getDirection()
    {
        return true;
    }

    public void setSpeed(int v)
    {
        this.speed = v;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public void updateLocation()
    {
        yLoc -= this.speed;
    }
}
