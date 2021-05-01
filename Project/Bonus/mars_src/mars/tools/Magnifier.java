/*     */ package mars.tools;
/*     */ 
/*     */ import java.awt.AWTException;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Robot;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.SpinnerModel;
/*     */ import javax.swing.SpinnerNumberModel;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import mars.Globals;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Magnifier
/*     */   extends JFrame
/*     */   implements ComponentListener
/*     */ {
/*     */   static Robot robot;
/*     */   JButton close;
/*     */   JButton capture;
/*     */   JButton settings;
/*     */   JSpinner scaleAdjuster;
/*     */   JScrollPane view;
/*     */   Dimension frameSize;
/*     */   Dimension viewSize;
/*     */   MagnifierImage magnifierImage;
/*     */   ActionListener captureActionListener;
/*     */   CaptureModel captureResize;
/*     */   CaptureModel captureMove;
/*     */   CaptureModel captureRescale;
/*     */   CaptureModel captureDisplayCenter;
/*     */   CaptureModel captureDisplayUpperleft;
/*     */   CaptureModel dialogDisplayCenter;
/*     */   ScribblerSettings scribblerSettings;
/*     */   static final double SCALE_MINIMUM = 1.0D;
/*     */   static final double SCALE_MAXIMUM = 4.0D;
/*     */   static final double SCALE_INCREMENT = 0.5D;
/*     */   static final double SCALE_DEFAULT = 2.0D;
/* 102 */   double scale = 2.0D;
/*     */   CaptureDisplayAlignmentStrategy alignment;
/* 104 */   CaptureRectangleStrategy captureLocationSize = new CaptureMagnifierRectangle();
/*     */   JFrame frame;
/*     */   static final String CAPTURE_TOOLTIP_TEXT = "Capture, scale, and display pixels that lay beneath the Magnifier.";
/*     */   static final String SETTINGS_TOOLTIP_TEXT = "Show dialog for changing tool settings.";
/*     */   static final String SCALE_TOOLTIP_TEXT = "Magnification scale for captured image.";
/*     */   static final String CLOSE_TOOLTIP_TEXT = "Exit the Screen Magnifier.  Changed settings are NOT retained.";
/*     */   
/*     */   Magnifier() {
/* 112 */     super("Screen Magnifier 1.0");
/* 113 */     this.frame = this;
/* 114 */     createSettings();
/*     */     
/*     */     try {
/* 117 */       setIconImage(Globals.getGui().getIconImage());
/*     */     }
/* 119 */     catch (Exception e) {}
/* 120 */     getContentPane().setLayout(new BorderLayout());
/*     */     
/* 122 */     addComponentListener(this);
/*     */     
/* 124 */     try { robot = new Robot(); }
/*     */     
/* 126 */     catch (AWTException e) {  }
/* 127 */     catch (SecurityException e) {}
/*     */     
/* 129 */     this.close = new JButton("Close");
/* 130 */     this.close.setToolTipText("Exit the Screen Magnifier.  Changed settings are NOT retained.");
/* 131 */     this.close.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 134 */             Magnifier.this.setVisible(false);
/*     */           }
/*     */         });
/* 137 */     this.settings = new JButton("Settings...");
/* 138 */     this.settings.setToolTipText("Show dialog for changing tool settings.");
/* 139 */     this.settings.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 142 */             new SettingsDialog(Magnifier.this.frame);
/*     */           }
/*     */         });
/* 145 */     this.magnifierImage = new MagnifierImage(this);
/* 146 */     this.view = new JScrollPane(this.magnifierImage);
/* 147 */     this.viewSize = new Dimension(200, 150);
/* 148 */     this.view.setSize(this.viewSize);
/*     */     
/* 150 */     this.capture = new JButton("Capture");
/* 151 */     this.capture.setToolTipText("Capture, scale, and display pixels that lay beneath the Magnifier.");
/* 152 */     this.captureActionListener = new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 155 */           Magnifier.this.magnifierImage.setImage(MagnifierImage.getScaledImage(Magnifier.this.captureScreenSection(Magnifier.this.captureLocationSize.getCaptureRectangle(Magnifier.this.getFrameRectangle())), Magnifier.this.scale));
/* 156 */           Magnifier.this.alignment.setScrollBarValue(Magnifier.this.view.getHorizontalScrollBar());
/* 157 */           Magnifier.this.alignment.setScrollBarValue(Magnifier.this.view.getVerticalScrollBar());
/*     */         }
/*     */       };
/* 160 */     JLabel scaleLabel = new JLabel("Scale: ");
/* 161 */     SpinnerModel scaleModel = new SpinnerNumberModel(2.0D, 1.0D, 4.0D, 0.5D);
/* 162 */     this.scaleAdjuster = new JSpinner(scaleModel);
/* 163 */     this.scaleAdjuster.setToolTipText("Magnification scale for captured image.");
/* 164 */     JSpinner.NumberEditor scaleEditor = new JSpinner.NumberEditor(this.scaleAdjuster, "0.0");
/* 165 */     scaleEditor.getTextField().setEditable(false);
/* 166 */     this.scaleAdjuster.setEditor(scaleEditor);
/* 167 */     this.scaleAdjuster.addChangeListener(new ChangeListener()
/*     */         {
/*     */           public void stateChanged(ChangeEvent e) {
/* 170 */             Magnifier.this.scale = ((Double)Magnifier.this.scaleAdjuster.getValue()).doubleValue();
/* 171 */             if (Magnifier.this.captureRescale.isEnabled()) {
/* 172 */               Magnifier.this.captureActionListener.actionPerformed(new ActionEvent(Magnifier.this.frame, 0, "capture"));
/*     */             }
/*     */           }
/*     */         });
/*     */     
/* 177 */     JPanel scalePanel = new JPanel();
/* 178 */     scalePanel.add(scaleLabel);
/* 179 */     scalePanel.add(this.scaleAdjuster);
/* 180 */     this.capture.addActionListener(this.captureActionListener);
/* 181 */     Box buttonRow = Box.createHorizontalBox();
/* 182 */     buttonRow.add(Box.createHorizontalStrut(4));
/* 183 */     buttonRow.add(this.capture);
/* 184 */     buttonRow.add(Box.createHorizontalGlue());
/* 185 */     buttonRow.add(this.settings);
/* 186 */     buttonRow.add(scalePanel);
/* 187 */     buttonRow.add(Box.createHorizontalGlue());
/* 188 */     buttonRow.add(getHelpButton());
/* 189 */     buttonRow.add(Box.createHorizontalGlue());
/* 190 */     buttonRow.add(this.close);
/* 191 */     buttonRow.add(Box.createHorizontalStrut(4));
/* 192 */     getContentPane().add(this.view, "Center");
/* 193 */     getContentPane().add(buttonRow, "South");
/* 194 */     pack();
/* 195 */     setSize(500, 400);
/* 196 */     setLocationRelativeTo((Component)null);
/* 197 */     setVisible(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     this.captureActionListener.actionPerformed(new ActionEvent(this.frame, 0, "capture"));
/*     */     
/* 205 */     this.captureActionListener.actionPerformed(new ActionEvent(this.frame, 0, "capture"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createSettings() {
/* 218 */     this.captureResize = new CaptureModel(false);
/* 219 */     this.captureMove = new CaptureModel(false);
/* 220 */     this.captureRescale = new CaptureModel(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     this.alignment = new CaptureDisplayCentered();
/*     */     
/* 228 */     this.captureDisplayCenter = new CaptureModel(this.alignment instanceof CaptureDisplayCentered);
/* 229 */     this.captureDisplayUpperleft = new CaptureModel(this.alignment instanceof CaptureDisplayUpperleft);
/*     */     
/* 231 */     this.scribblerSettings = new ScribblerSettings(2, Color.RED);
/*     */     
/* 233 */     this.dialogDisplayCenter = new CaptureModel(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton getHelpButton() {
/* 238 */     String helpContent = "Use this utility tool to display a magnified image of a\nscreen section and highlight things on the image.  This\nwill be of interest mainly to instructors.\n\nTo capture an image, size and position the Screen Magnifier\nover the screen segment to be magnified and click \"Capture\".\nThe pixels beneath the magnifier will be captured, scaled,\nand displayed in a scrollable window.\n\nTo highlight things in the image, just drag the mouse over\nthe image to make a scribble line.  This line is ephemeral\n(is not repainted if covered then uncovered).\n\nThe magnification scale can be adjusted using the spinner.\nOther settings can be adjusted through the Settings dialog.\nSettings include: justification of displayed image, automatic\ncapture upon certain tool events, and the thickness and color\nof the scribble line.\n\nLIMITS: The image is static; it is not updated when the\nunderlying pixels change.  Scale changes do not take effect\nuntil the next capture (but you can set auto-capture).  The\nMagnifier does not capture frame contents of other tools.\nSetting changes are not saved when the tool is closed.\n\nContact Pete Sanderson at psanderson@otterbein.edu with\nquestions or comments.\n";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     JButton help = new JButton("Help");
/* 267 */     help.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 270 */             JOptionPane.showMessageDialog(Magnifier.this.frame, "Use this utility tool to display a magnified image of a\nscreen section and highlight things on the image.  This\nwill be of interest mainly to instructors.\n\nTo capture an image, size and position the Screen Magnifier\nover the screen segment to be magnified and click \"Capture\".\nThe pixels beneath the magnifier will be captured, scaled,\nand displayed in a scrollable window.\n\nTo highlight things in the image, just drag the mouse over\nthe image to make a scribble line.  This line is ephemeral\n(is not repainted if covered then uncovered).\n\nThe magnification scale can be adjusted using the spinner.\nOther settings can be adjusted through the Settings dialog.\nSettings include: justification of displayed image, automatic\ncapture upon certain tool events, and the thickness and color\nof the scribble line.\n\nLIMITS: The image is static; it is not updated when the\nunderlying pixels change.  Scale changes do not take effect\nuntil the next capture (but you can set auto-capture).  The\nMagnifier does not capture frame contents of other tools.\nSetting changes are not saved when the tool is closed.\n\nContact Pete Sanderson at psanderson@otterbein.edu with\nquestions or comments.\n");
/*     */           }
/*     */         });
/* 273 */     return help;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BufferedImage captureScreenSection(Rectangle section) {
/* 286 */     setVisible(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 293 */       Globals.getGui().update(Globals.getGui().getGraphics());
/*     */     }
/* 295 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 298 */     BufferedImage imageOfSection = robot.createScreenCapture(section);
/* 299 */     setVisible(true);
/* 300 */     return imageOfSection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Rectangle getFrameRectangle() {
/* 309 */     return new Rectangle((getLocation()).x, (getLocation()).y, (getSize()).width, (getSize()).height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Rectangle getScreenRectangle() {
/* 319 */     return new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {
/* 353 */     if (this.captureMove.isEnabled()) {
/* 354 */       this.captureActionListener.actionPerformed(new ActionEvent(e.getComponent(), e.getID(), "capture"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 370 */     if (this.captureResize.isEnabled())
/* 371 */       this.captureActionListener.actionPerformed(new ActionEvent(e.getComponent(), e.getID(), "capture")); 
/*     */   }
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/tools/Magnifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */