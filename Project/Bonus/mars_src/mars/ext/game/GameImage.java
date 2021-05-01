package mars.ext.game;
import mars.ext.game.util.Sprite;

import java.awt.*;
import java.awt.image.*;



public class GameImage extends GameObject {
    public Image image;
    public Graphics2D graphics2D;
    private Sprite sprite = new Sprite(GameConfigFile.RESOURCE_IMAGE);


    // draw the maze
    public GameImage(int id, int x, int y, int[][] mazeBitmap, int screenWigth, int screenHeight, int mazeWidth, int mazeHeight, int gameLevel) {
        super(id, x, y);

        BufferedImage backImg = new BufferedImage(screenWigth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = backImg.createGraphics();
        graphics2D = g2;

        /*
         * 地图数组
         * 1：水泥墙 2：铁墙 3：草 4：水 5：冰 9：家
         */

        g2.setColor(Color.gray);
        g2.fillRect(0, 0, screenWigth, screenHeight);
        g2.setColor(Color.black);
        g2.fillRect(GameConfigFile.offsetX, GameConfigFile.offsetY, mazeWidth, mazeHeight);


        for (int i = 0; i < mazeBitmap.length; i++) {
            for (int j = 0; j < mazeBitmap[i].length; j++) {

                if(mazeBitmap[i][j] == GameConfigFile.WALL
                        || mazeBitmap[i][j] == GameConfigFile.GRID
                        || mazeBitmap[i][j] == GameConfigFile.WATER
                        || mazeBitmap[i][j] == GameConfigFile.ICE) {
                    Image image = sprite.getImage(GameConfigFile.tileSize*(mazeBitmap[i][j]-1) + GameConfigFile.POS.get("map")[0],
                            GameConfigFile.POS.get("map")[1],
                            GameConfigFile.tileSize, GameConfigFile.tileSize);

                    g2.drawImage(image,
                            j * GameConfigFile.tileSize + GameConfigFile.offsetX,
                            i * GameConfigFile.tileSize + GameConfigFile.offsetY,
                            GameConfigFile.tileSize, GameConfigFile.tileSize, null);

                } else if(mazeBitmap[i][j] == GameConfigFile.GRASS) {
                    Image image = sprite.getImage(GameConfigFile.tileSize*2 + GameConfigFile.POS.get("map")[0],
                            GameConfigFile.POS.get("map")[1],
                            GameConfigFile.tileSize, GameConfigFile.tileSize);

                    g2.drawImage(image,
                            j * GameConfigFile.tileSize + GameConfigFile.offsetX,
                            i * GameConfigFile.tileSize + GameConfigFile.offsetY,
                            GameConfigFile.tileSize, GameConfigFile.tileSize, null);

                } else if(mazeBitmap[i][j] == GameConfigFile.HOME) {
                    Image image = sprite.getImage(GameConfigFile.POS.get("home")[0],
                            GameConfigFile.POS.get("home")[1],GameConfigFile.homeSize, GameConfigFile.homeSize);

                    g2.drawImage(image,
                            j * GameConfigFile.tileSize + GameConfigFile.offsetX,
                            i * GameConfigFile.tileSize + GameConfigFile.offsetY,
                            GameConfigFile.homeSize, GameConfigFile.homeSize, null);
                }
            }
        }
        image = backImg;
    }


    public Image createImage(String type, int direction) {
        if (type.endsWith("player"))
            return sprite.getImage(GameConfigFile.POS.get("player")[0]+ GameConfigFile.offsetX + direction * GameConfigFile.tankSize,
                    GameConfigFile.POS.get("player")[1], GameConfigFile.tankSize, GameConfigFile.tankSize);
        return null;
    }

    @Override
    public void paint(Graphics graph) {
        Graphics2D g2 = (Graphics2D) graph;
        g2.drawImage(image, xLoc, yLoc, null);
    }


}
