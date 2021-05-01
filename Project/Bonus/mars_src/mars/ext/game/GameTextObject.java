package mars.ext.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This class represents the Text Object, to be drawn in the game.
 * 
 * @author jasonwangm
 * 
 */
public class GameTextObject extends GameObject {
    private String text;
    private Font font;
    private Color color;

    public GameTextObject(int id, int x, int y) {
        super(id, x, y);
        text = "";
        font = new Font(Font.SANS_SERIF, Font.BOLD, 28);
        color = Color.red;
    }

    public GameTextObject(int id) {
        this(id, 0, 0);
    }

    @Override
    public void paint(Graphics graph) {
        Graphics2D g2 = (Graphics2D) graph;
        g2.setColor(color);
        g2.setFont(font);
        g2.drawString(text, xLoc, yLoc);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(int size)
            throws GameException {
        if (size < 1) {
            throw new GameException("Invalid font size: " + size + "\n");
        }
        font = font.deriveFont((float) size);

    }

    public void setFont(String fontName, int size, boolean isBold)
            throws GameException {
        if (size < 1) {
            throw new GameException("Invalid font size: " + size + "\n");
        }
        int style = isBold ? Font.BOLD : Font.PLAIN;
        try {
            font = new Font(fontName, style, size);
        } catch (Exception e) {
            throw new GameException("Invalid font name: " + fontName + "\n");
        }
    }

    public void setColor(int colorNum) throws GameException {
        try {
            this.color = new Color(colorNum);
        } catch (Exception e) {
            throw new GameException("Invalid color number: 0x"
                    + Integer.toHexString(colorNum) + "\n");
        }
    }
}
