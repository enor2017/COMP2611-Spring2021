package mars.ext.game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

/**
 * This class is the GUI component of the game.
 * 
 * @author jasonwangm
 * 
 */
public class GameScreen extends JFrame {
    private BufferedImage offscreenImage;
    public int width; // the width of the screen
    public int height; // the height of the screen
    private String title; // the title of the screen
    private int score; // the score of the game
    private int simpleBombLeft; // the number of simple bombs left
    private int remoteBombLeft; // the number of remote bombs left
    private Font font = new Font("Serif", Font.BOLD, 24);

    // the vector of game objects
    private ArrayList<Hashtable<Integer, GameObject>> gameObjs;
    private SoundPlayer soundPlayer;
    private MMIOInput keyListener; // the memeory mapped IO
    private GameImage bgImage;
    private ScreenPanel panel;

    private boolean useBomb = false;

    // the real maze figure
    public GameImage mazeImage;
    public int mazeWidth, cellWidth; // the maze or cell width
    public int mazeHeight, cellHeight; // the maze or cell height
    public int bitmapRow; // number of rows in bitmap
    public int bitmapColumn; // number of columns in bitmap
    private int[][] mazeBitmap; // maze bitmap
    private ArrayList<Integer> pathList; // list of (row, column) for grid cells whose values are 0 (paths)      
    private int topObjectType;
    private int level; // the level of the game

    private static GameScreen instance = null;

    public static GameScreen getInstance() {
        return instance;
    }

    public static synchronized GameScreen createIntance(String propertyFile) throws Exception {
        if (instance != null)
            instance.dispose();
        instance = new GameScreen(propertyFile);
        return instance;
    }

    private class ScreenPanel extends JPanel {
        public ScreenPanel() {
            super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            redrawScreen(offscreenImage.createGraphics());
            g2.drawImage(offscreenImage, 0, 0, null);

        }
    }

    private void initGameScreen() {
        offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.soundPlayer = new SoundPlayer();
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GameConfigFile.rand = new Random();
        this.level = 1;

        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                keyListener.destroy();
                soundPlayer.stopAll();
                bgImage = null;
                for (int i = 0; i < gameObjs.size(); i++)
                    gameObjs.get(i).clear();
                instance = null;
            }
        });

        this.panel = new ScreenPanel();
        this.getContentPane().add(panel);
        this.keyListener = new MMIOInput(true);
        this.addKeyListener(keyListener);

        Dimension dim = new Dimension(width, height);
        this.panel.setMaximumSize(dim);
        this.panel.setMinimumSize(dim);
        this.panel.setPreferredSize(dim);
        panel.setSize(dim);
        this.setResizable(false);
        this.setVisible(true);

        pack();

    }

    public void loadMaze(Properties properties, int gameLevel)  {
        String propertyStr;
        this.mazeWidth = 0;
        propertyStr = properties.getProperty("mazeWidth");
        if (propertyStr != null)
            this.mazeWidth = Integer.parseInt(propertyStr);
        this.mazeHeight = 0;

        propertyStr = properties.getProperty("mazeHeight");
        if (propertyStr != null)
            this.mazeHeight = Integer.parseInt(propertyStr);

        this.mazeImage = null;
        propertyStr = properties.getProperty("level" + gameLevel + "mazeBitmap");
        if (propertyStr == null)
            propertyStr = properties.getProperty("mazeBitmap"); // default one
        if (propertyStr != null) {
            if(mazeBitmap==null)
                createMazeBitmap(propertyStr);
            this.mazeImage = new GameImage(-2, 0, 0, mazeBitmap, width, height, mazeWidth, mazeHeight, gameLevel);
        }
    }

    // constructor for creating a maze game
    private GameScreen(String propertyFile) throws Exception {
        InputStream in = this.getClass().getResourceAsStream(propertyFile);
        Properties properties;
        GameConfigFile.properties = properties = new Properties();
        properties.load(in);
        in.close();

        this.title = properties.getProperty("title");
        this.width = Integer.parseInt(properties.getProperty("width"));
        this.height = Integer.parseInt(properties.getProperty("height"));

        // objects
        int objectTypeCount = Integer.parseInt(properties.getProperty("objectTypeCount"));
        this.gameObjs = new ArrayList<Hashtable<Integer, GameObject>>();
        for (int i = 0; i < objectTypeCount; i++)
            gameObjs.add(new Hashtable<Integer, GameObject>());

        String topType = properties.getProperty("topObjectType"); // back-end iter
        if (topType != null)
            topObjectType = Integer.parseInt(topType);
        else
            topObjectType = gameObjs.size() - 1;

        String prop;
        GameConfigFile.prevImagePackIndex = new int[objectTypeCount];
        GameConfigFile.imagePackCount = new int[objectTypeCount];

        for (int i = 0; i < GameConfigFile.prevImagePackIndex.length; i++) {
            GameConfigFile.prevImagePackIndex[i] = -1;
            prop = properties.getProperty("object" + i + "ImagePackCount"); // for one obj, how many pic packets
            if (prop != null)
                GameConfigFile.imagePackCount[i] = Integer.parseInt(prop);
            else
                GameConfigFile.imagePackCount[i] = 0;
        }
        GameConfigFile.imageArchive = new Hashtable<String, Image>(); // string->img

        loadMaze(properties, 1);
        loadRouteFile();
        initGameScreen();
    }


    public void playSound(int soundId, boolean toLoop) {
        this.soundPlayer.play(soundId, toLoop);
    }

    public void stopSound(int soundId) {
        this.soundPlayer.stopSound(soundId);
    }


    public void addGameObject(int id, GameObject obj, int type) {
        this.gameObjs.get(type).put(id, obj);
    }


    public GameObject getGameObject(int id, int type) {
        return gameObjs.get(type).get(id);
    }

    public void removeAllObjects(int type) {
        gameObjs.get(type).clear();
    }

    public Hashtable<Integer, GameObject> getAllObjects(int type) {
        return gameObjs.get(type);
    }


    public void refreshScreen() {
        this.panel.repaint();
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public void updateLevel(int level) {
        if (this.level != level) {
            if (GameConfigFile.prevImagePackIndex != null)
                for (int i = 0; i < GameConfigFile.prevImagePackIndex.length; i++)
                    GameConfigFile.prevImagePackIndex[i] = -1;
            GameConfigFile.prevRouteIndex = -1;
        }
        this.level = level;
        try {
            loadMaze(GameConfigFile.properties, level);
        } catch (Exception e) {
        }
    }

    public int getLevel() {
        return level;
    }

    // return false if target is not found
    // target is the routing target, like monster seeking for bombman
    public boolean moveAllObj(int moveObjectType, int seekTargetId, int seekTargetType) {
        GameObject target = getGameObject(seekTargetId, seekTargetType);
        Hashtable<Integer, GameObject> objs = getAllObjects(moveObjectType);
        if (target == null || !(target instanceof MazeGameObject))
            return false;
        else
            for (Map.Entry<Integer, GameObject> entry : objs.entrySet()) {
                MazeGameObject obj = (MazeGameObject) entry.getValue();
                if (obj.survival == false) continue;
                obj.nextRouteStep(this, (MazeGameObject) target);
            }
        return true;
    }


    public int[] getRandomPath() {
        int index = GameConfigFile.rand.nextInt(pathList.size() / 2) * 2;
        int[] result = new int[2];
        result[0] = pathList.get(index).intValue();
        result[1] = pathList.get(index + 1).intValue();
        return result;
    }

    // where to draw objects
    private void redrawScreen(Graphics2D g2) {
        if (bgImage != null) {
            bgImage.paint(g2);
        }
        if (mazeImage != null) {
            mazeImage.paint(g2);
        }

        for (int i = 0; i < topObjectType; i++)
            for (Map.Entry<Integer, GameObject> entry : gameObjs.get(i).entrySet()) {
                GameObject obj = entry.getValue();
                obj.paint(g2);
            }
        if (useBomb) {
            g2.drawString(Integer.toString(simpleBombLeft), 470, 40);
            g2.drawString(Integer.toString(remoteBombLeft), 640, 40);
        }
        for (int i = topObjectType; i < gameObjs.size(); i++)
            for (Map.Entry<Integer, GameObject> entry : gameObjs.get(i).entrySet()) {
                GameObject obj = entry.getValue();
                obj.paint(g2);
            }
    }


    // return bitmap cell value or -1 if (x,y) is outside the grid
    public int getBitmapCell(int x, int y) {
        if (x < 0 || x >= mazeWidth || y < 0 || y >= mazeHeight)
            return -1;
        return mazeBitmap[y / cellHeight][x / cellWidth];
    }

    public void editBitmapCell(int x, int y, int value) {
        if (x < 0 || x >= mazeWidth || y < 0 || y >= mazeHeight)
            return;
        mazeBitmap[y / cellHeight][x / cellWidth] = value;
    }
    public void editBitmapCell(int x, int value) {
//        if (x < 0 || x >= mazeWidth || y < 0 || y >= mazeHeight)
//            return;
        mazeBitmap[x / 26][x % 26] = value;
    }

    private void createMazeBitmap(String bitmapFile) {

        this.bitmapRow = Integer.parseInt(GameConfigFile.properties.getProperty("bitmapRow"));
        this.bitmapColumn = Integer.parseInt(GameConfigFile.properties.getProperty("bitmapColumn"));

        int value;
        cellHeight = mazeHeight / bitmapRow;
        cellWidth = mazeWidth / bitmapColumn;
        Scanner scanner = new Scanner(this.getClass().getResourceAsStream(bitmapFile));
        pathList = new ArrayList<Integer>();
        mazeBitmap = new int[bitmapRow][];
        for (int i = 0; i < bitmapRow; i++) {
            mazeBitmap[i] = new int[bitmapColumn];
            for (int j = 0; j < bitmapColumn; j++) {
                value = scanner.nextInt();
                mazeBitmap[i][j] = value;

                if (value == 0) {
                    pathList.add(new Integer(j * cellWidth));
                    pathList.add(new Integer(i * cellHeight));
                }
            }

        }
    }


    private void loadRouteFile() {
        int routeIndex = 0;
        String routeFile = GameConfigFile.properties.getProperty("routeFile" + routeIndex);
        ArrayList route;

        GameConfigFile.prevRouteIndex = -1;
        GameConfigFile.routeList = new ArrayList<ArrayList>();
        String action;
        Scanner scanner;
        while (routeFile != null) {

            scanner = new Scanner(this.getClass().getResourceAsStream(routeFile));
            route = new ArrayList();
            GameConfigFile.routeList.add(route);
            while (scanner.hasNext()) {
                action = scanner.next();
                route.add(action);
                if (action.equals("d")) {// arguments for route step
                    route.add(new Integer(scanner.nextInt()));
                    route.add(new Integer(scanner.nextInt()));
                }
            }
            routeIndex++;
            routeFile = GameConfigFile.properties.getProperty("routeFile" + routeIndex);
        }
    }

}
