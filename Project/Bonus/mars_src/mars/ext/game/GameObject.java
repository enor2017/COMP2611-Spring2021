package mars.ext.game;

import java.awt.Graphics;

/**
 * This is the Abstract Base Class of all types of objects to be used in the
 * course project of COMP2611 in HKUST.
 * 
 * @author jasonwangm
 * 
 */
public abstract class GameObject {
	protected int id; // each GameObject has a unique non-negative id
	protected int xLoc; // the x-coordinate
	protected int yLoc; // the y-coordinate
	protected int type; // object type
	public int direction;
	public int direction_;

	public GameObject(int id) {
		this(id, 0);
	}

	public GameObject(int id, int x, int y) {
		this(id, x, y, 0);
	}

  
	public GameObject(int id, int type) {
		this(id, -1, -1, type);
	}

	public GameObject(int id, int x, int y, int type) {
		this.id = id;
		this.xLoc = x;
		this.yLoc = y;
		this.type = type;
	}

	/**
	 * A concrete type of GameObject has its own implementation of how to paint
	 * itself on the Graphics.
	 * 
	 * @param graph
	 */
	public abstract void paint(Graphics graph);

	public int getId() {
		return this.id;
	}

	public void setXLoc(int x) {
		this.xLoc = x;
	}

	public int getXLoc() {
		return this.xLoc;
	}

	public void setYLoc(int y) {
		this.yLoc = y;
	}

	public int getYLoc() {
		return this.yLoc;
	}
//	no direction, 0: north, 1: west, 2: south, 3: east
	public void setDirection(int x, int y){
		if(x<xLoc)
			direction_=1;
		else if(x>xLoc)
			direction_=3;
		else if(y>yLoc)
			direction_=2;
		else if(y<yLoc)
			direction_=0;
	}
	public void setLocations(int x, int y) {
		this.xLoc = x;
		this.yLoc = y;

	}

	public int getType() {
		return this.type;
	}
	
}
