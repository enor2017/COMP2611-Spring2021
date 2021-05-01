package mars.ext.game;

public interface ActiveElementInterface
{
    public int getScore();
    public void deductHitPoint(int p);
    public void setDirection(boolean right);
    public boolean getDirection();
    public void setSpeed(int v);
    public int getSpeed();
    public void updateLocation();
    public int getHitPoint();
}
