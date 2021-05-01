/*     */ package mars.tools;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.border.TitledBorder;
/*     */ 

/*     */ 
/*     */ class SettingsDialog2 extends JDialog {
/*     */   JButton applyButton;
/*     */   JButton cancelButton;
/*     */   JCheckBox captureResizeCheckBox;
/*     */   JCheckBox captureMoveCheckBox;
/*     */   JCheckBox captureRescaleCheckBox;
/*     */   JRadioButton captureDisplayCenteredButton;
/*     */   JRadioButton captureDisplayUpperleftButton;
/* 397 */   Integer[] scribblerLineWidthSettings = new Integer[] { new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5), new Integer(6), new Integer(7), new Integer(8) };
/*     */   
/*     */   JComboBox lineWidthSetting;
/*     */   
/*     */   JButton lineColorSetting;
/*     */   
/*     */   JCheckBox dialogCentered;
/*     */   
/*     */   JDialog dialog;
/*     */   
/*     */   Color scribblerLineColorSetting;
/*     */   
/*     */   static final String SETTINGS_APPLY_TOOLTIP_TEXT = "Apply current settings and close the dialog.";
/*     */   
/*     */   static final String SETTINGS_CANCEL_TOOLTIP_TEXT = "Close the dialog without applying any setting changes.";
/*     */   static final String SETTINGS_SCRIBBLER_WIDTH_TOOLTIP_TEXT = "Scribbler line thickness in pixels.";
/*     */   static final String SETTINGS_SCRIBBLER_COLOR_TOOLTIP_TEXT = "Click here to change Scribbler line color.";
/*     */   static final String SETTINGS_DIALOG_CENTERED_TOOLTIP_TEXT = "Whether to center this dialog over the Magnifier.";
/*     */   
/*     */   SettingsDialog2(JFrame frame) {
/* 417 */     super(frame, "Magnifier Tool Settings");
/* 418 */     this.dialog = this;
/* 419 */     setDefaultCloseOperation(2);
/* 420 */     Container contentPane = getContentPane();
/* 421 */     contentPane.setLayout(new BorderLayout());
/* 422 */     JPanel settingsPanel = new JPanel();
/* 423 */     JPanel selectionsPanel = new JPanel(new GridLayout(2, 1));
/* 424 */     selectionsPanel.add(getCaptureDisplayPanel());
/* 425 */     JPanel secondRow = new JPanel(new GridLayout(1, 2));
/* 426 */     secondRow.add(getAutomaticCaptureSettingsPanel());
/* 427 */     secondRow.add(getScribblerPanel(this));
/* 428 */     selectionsPanel.add(secondRow);
/* 429 */     contentPane.add(selectionsPanel);
/* 430 */     contentPane.add(getButtonRowPanel(), "South");
/* 431 */     pack();
/* 432 */     if (this.dialogCentered.isSelected()) {
/* 433 */       setLocationRelativeTo(frame);
/*     */     }
/* 435 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel getButtonRowPanel() {
/* 440 */     JPanel buttonRow = new JPanel();
/* 441 */     this.applyButton = new JButton("Apply and Close");
/* 442 */     this.applyButton.setToolTipText("Apply current settings and close the dialog.");
/*     */     
/* 444 */     this.applyButton.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 448 */             ((Magnifier)SettingsDialog2.this.getOwner()).captureResize.setEnabled(SettingsDialog2.this.captureResizeCheckBox.isSelected());
/* 449 */             ((Magnifier)SettingsDialog2.this.getOwner()).captureMove.setEnabled(SettingsDialog2.this.captureMoveCheckBox.isSelected());
/* 450 */             ((Magnifier)SettingsDialog2.this.getOwner()).captureRescale.setEnabled(SettingsDialog2.this.captureRescaleCheckBox.isSelected());
/* 451 */             ((Magnifier)SettingsDialog2.this.getOwner()).captureDisplayCenter.setEnabled(SettingsDialog2.this.captureDisplayCenteredButton.isSelected());
/* 452 */             ((Magnifier)SettingsDialog2.this.getOwner()).captureDisplayUpperleft.setEnabled(SettingsDialog2.this.captureDisplayUpperleftButton.isSelected());
/* 453 */             ((Magnifier)SettingsDialog2.this.getOwner()).dialogDisplayCenter.setEnabled(SettingsDialog2.this.dialogCentered.isSelected());
/* 454 */             if (SettingsDialog2.this.captureDisplayCenteredButton.isSelected()) {
/* 455 */               ((Magnifier)SettingsDialog2.this.getOwner()).alignment = new CaptureDisplayCentered();
/*     */             }
/* 457 */             else if (SettingsDialog2.this.captureDisplayUpperleftButton.isSelected()) {
/* 458 */               ((Magnifier)SettingsDialog2.this.getOwner()).alignment = new CaptureDisplayUpperleft();
/*     */             } 
/* 460 */             ((Magnifier)SettingsDialog2.this.getOwner()).scribblerSettings.setLineWidth(SettingsDialog2.this.scribblerLineWidthSettings[SettingsDialog2.this.lineWidthSetting.getSelectedIndex()].intValue());
/* 461 */             ((Magnifier)SettingsDialog2.this.getOwner()).scribblerSettings.setLineColor(SettingsDialog2.this.lineColorSetting.getBackground());
/* 462 */             SettingsDialog2.this.dialog.dispose();
/*     */           }
/*     */         });
/* 465 */     this.cancelButton = new JButton("Cancel");
/* 466 */     this.cancelButton.setToolTipText("Close the dialog without applying any setting changes.");
/* 467 */     this.cancelButton.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 470 */             SettingsDialog2.this.dialog.dispose();
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 480 */     this.dialogCentered = new JCheckBox("Dialog centered", ((Magnifier)getOwner()).dialogDisplayCenter.isEnabled());
/* 481 */     this.dialogCentered.setToolTipText("Whether to center this dialog over the Magnifier.");
/* 482 */     buttonRow.add(this.applyButton);
/* 483 */     buttonRow.add(this.cancelButton);
/* 484 */     buttonRow.add(this.dialogCentered);
/* 485 */     return buttonRow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JPanel getAutomaticCaptureSettingsPanel() {
/* 492 */     JPanel automaticCaptureSettings = new JPanel();
/* 493 */     automaticCaptureSettings.setBorder(new TitledBorder("Automatic Capture"));
/* 494 */     Box automaticCaptureSettingsBox = Box.createHorizontalBox();
/* 495 */     automaticCaptureSettings.add(automaticCaptureSettingsBox);
/* 496 */     this.captureResizeCheckBox = new JCheckBox("Capture upon resize", ((Magnifier)getOwner()).captureResize.isEnabled());
/* 497 */     this.captureMoveCheckBox = new JCheckBox("Capture upon move", ((Magnifier)getOwner()).captureMove.isEnabled());
/* 498 */     this.captureRescaleCheckBox = new JCheckBox("Capture upon rescale", ((Magnifier)getOwner()).captureRescale.isEnabled());
/* 499 */     JPanel checkboxColumn = new JPanel(new GridLayout(3, 1));
/* 500 */     checkboxColumn.add(this.captureResizeCheckBox);
/* 501 */     checkboxColumn.add(this.captureMoveCheckBox);
/* 502 */     checkboxColumn.add(this.captureRescaleCheckBox);
/* 503 */     automaticCaptureSettingsBox.add(checkboxColumn);
/* 504 */     return automaticCaptureSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JPanel getCaptureDisplayPanel() {
/* 512 */     JPanel captureDisplaySetting = new JPanel();
/* 513 */     captureDisplaySetting.setBorder(new TitledBorder("Capture and Display"));
/* 514 */     Box captureDisplaySettingsBox = Box.createHorizontalBox();
/* 515 */     captureDisplaySetting.add(captureDisplaySettingsBox);
/* 516 */     this.captureDisplayCenteredButton = new JRadioButton("Capture area behind magnifier and display centered", ((Magnifier)getOwner()).captureDisplayCenter.isEnabled());
/*     */     
/* 518 */     this.captureDisplayUpperleftButton = new JRadioButton("Capture area behind magnifier and display upper-left", ((Magnifier)getOwner()).captureDisplayUpperleft.isEnabled());
/*     */     
/* 520 */     ButtonGroup displayButtonGroup = new ButtonGroup();
/* 521 */     displayButtonGroup.add(this.captureDisplayCenteredButton);
/* 522 */     displayButtonGroup.add(this.captureDisplayUpperleftButton);
/* 523 */     JPanel radioColumn = new JPanel(new GridLayout(2, 1));
/* 524 */     radioColumn.add(this.captureDisplayCenteredButton);
/* 525 */     radioColumn.add(this.captureDisplayUpperleftButton);
/* 526 */     JPanel radioLabelColumn = new JPanel(new GridLayout(1, 1));
/* 527 */     captureDisplaySettingsBox.add(radioColumn);
/* 528 */     return captureDisplaySetting;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JPanel getScribblerPanel(final JDialog dialog) {
/* 535 */     JPanel scribblerSettings = new JPanel();
/* 536 */     scribblerSettings.setBorder(new TitledBorder("Scribbler"));
/* 537 */     Box scribblerSettingsBox = Box.createHorizontalBox();
/* 538 */     scribblerSettings.add(scribblerSettingsBox);
/* 539 */     this.lineWidthSetting = new JComboBox<Integer>(this.scribblerLineWidthSettings);
/* 540 */     this.lineWidthSetting.setToolTipText("Scribbler line thickness in pixels.");
/* 541 */     this.lineWidthSetting.setSelectedIndex(((Magnifier)getOwner()).scribblerSettings.getLineWidth() - 1);
/* 542 */     this.lineColorSetting = new JButton("   ");
/* 543 */     this.lineColorSetting.setToolTipText("Click here to change Scribbler line color.");
/* 544 */     this.lineColorSetting.setBackground(((Magnifier)getOwner()).scribblerSettings.getLineColor());
/* 545 */     this.lineColorSetting.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 548 */             Color newColor = JColorChooser.showDialog(dialog, "Scribbler line color", SettingsDialog2.this.lineColorSetting.getBackground());
/* 549 */             SettingsDialog2.this.lineColorSetting.setBackground(newColor);
/*     */           }
/*     */         });
/* 552 */     this.scribblerLineColorSetting = this.lineColorSetting.getBackground();
/* 553 */     JPanel settingsColumn = new JPanel(new GridLayout(2, 1, 5, 5));
/* 554 */     settingsColumn.add(this.lineWidthSetting);
/* 555 */     settingsColumn.add(this.lineColorSetting);
/* 556 */     JPanel labelColumn = new JPanel(new GridLayout(2, 1, 5, 5));
/* 557 */     labelColumn.add(new JLabel("Line width ", 2));
/* 558 */     labelColumn.add(new JLabel("Line color ", 2));
/* 559 */     scribblerSettingsBox.add(labelColumn);
/* 560 */     scribblerSettingsBox.add(settingsColumn);
/* 561 */     return scribblerSettings;
/*     */   }
/*     */ }
/*     */ 
/*     */ 

///*     */ class SettingsDialog extends JDialog {
///*     */   JButton applyButton;
///*     */   JButton cancelButton;
///*     */   JCheckBox captureResizeCheckBox;
///*     */   JCheckBox captureMoveCheckBox;
///*     */   JCheckBox captureRescaleCheckBox;
///*     */   JRadioButton captureDisplayCenteredButton;
///*     */   JRadioButton captureDisplayUpperleftButton;
///* 397 */   Integer[] scribblerLineWidthSettings = new Integer[] { new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5), new Integer(6), new Integer(7), new Integer(8) };
///*     */   
///*     */   JComboBox lineWidthSetting;
///*     */   
///*     */   JButton lineColorSetting;
///*     */   
///*     */   JCheckBox dialogCentered;
///*     */   
///*     */   JDialog dialog;
///*     */   
///*     */   Color scribblerLineColorSetting;
///*     */   
///*     */   static final String SETTINGS_APPLY_TOOLTIP_TEXT = "Apply current settings and close the dialog.";
///*     */   
///*     */   static final String SETTINGS_CANCEL_TOOLTIP_TEXT = "Close the dialog without applying any setting changes.";
///*     */   static final String SETTINGS_SCRIBBLER_WIDTH_TOOLTIP_TEXT = "Scribbler line thickness in pixels.";
///*     */   static final String SETTINGS_SCRIBBLER_COLOR_TOOLTIP_TEXT = "Click here to change Scribbler line color.";
///*     */   static final String SETTINGS_DIALOG_CENTERED_TOOLTIP_TEXT = "Whether to center this dialog over the Magnifier.";
///*     */   
///*     */   SettingsDialog(JFrame frame) {
///* 417 */     super(frame, "Magnifier Tool Settings");
///* 418 */     this.dialog = this;
///* 419 */     setDefaultCloseOperation(2);
///* 420 */     Container contentPane = getContentPane();
///* 421 */     contentPane.setLayout(new BorderLayout());
///* 422 */     JPanel settingsPanel = new JPanel();
///* 423 */     JPanel selectionsPanel = new JPanel(new GridLayout(2, 1));
///* 424 */     selectionsPanel.add(getCaptureDisplayPanel());
///* 425 */     JPanel secondRow = new JPanel(new GridLayout(1, 2));
///* 426 */     secondRow.add(getAutomaticCaptureSettingsPanel());
///* 427 */     secondRow.add(getScribblerPanel(this));
///* 428 */     selectionsPanel.add(secondRow);
///* 429 */     contentPane.add(selectionsPanel);
///* 430 */     contentPane.add(getButtonRowPanel(), "South");
///* 431 */     pack();
///* 432 */     if (this.dialogCentered.isSelected()) {
///* 433 */       setLocationRelativeTo(frame);
///*     */     }
///* 435 */     setVisible(true);
///*     */   }
///*     */ 
///*     */   
///*     */   private JPanel getButtonRowPanel() {
///* 440 */     JPanel buttonRow = new JPanel();
///* 441 */     this.applyButton = new JButton("Apply and Close");
///* 442 */     this.applyButton.setToolTipText("Apply current settings and close the dialog.");
///*     */     
///* 444 */     this.applyButton.addActionListener(new ActionListener()
///*     */         {
///*     */           public void actionPerformed(ActionEvent e)
///*     */           {
///* 448 */             ((Magnifier)SettingsDialog.this.getOwner()).captureResize.setEnabled(SettingsDialog.this.captureResizeCheckBox.isSelected());
///* 449 */             ((Magnifier)SettingsDialog.this.getOwner()).captureMove.setEnabled(SettingsDialog.this.captureMoveCheckBox.isSelected());
///* 450 */             ((Magnifier)SettingsDialog.this.getOwner()).captureRescale.setEnabled(SettingsDialog.this.captureRescaleCheckBox.isSelected());
///* 451 */             ((Magnifier)SettingsDialog.this.getOwner()).captureDisplayCenter.setEnabled(SettingsDialog.this.captureDisplayCenteredButton.isSelected());
///* 452 */             ((Magnifier)SettingsDialog.this.getOwner()).captureDisplayUpperleft.setEnabled(SettingsDialog.this.captureDisplayUpperleftButton.isSelected());
///* 453 */             ((Magnifier)SettingsDialog.this.getOwner()).dialogDisplayCenter.setEnabled(SettingsDialog.this.dialogCentered.isSelected());
///* 454 */             if (SettingsDialog.this.captureDisplayCenteredButton.isSelected()) {
///* 455 */               ((Magnifier)SettingsDialog.this.getOwner()).alignment = new CaptureDisplayCentered();
///*     */             }
///* 457 */             else if (SettingsDialog.this.captureDisplayUpperleftButton.isSelected()) {
///* 458 */               ((Magnifier)SettingsDialog.this.getOwner()).alignment = new CaptureDisplayUpperleft();
///*     */             } 
///* 460 */             ((Magnifier)SettingsDialog.this.getOwner()).scribblerSettings.setLineWidth(SettingsDialog.this.scribblerLineWidthSettings[SettingsDialog.this.lineWidthSetting.getSelectedIndex()].intValue());
///* 461 */             ((Magnifier)SettingsDialog.this.getOwner()).scribblerSettings.setLineColor(SettingsDialog.this.lineColorSetting.getBackground());
///* 462 */             SettingsDialog.this.dialog.dispose();
///*     */           }
///*     */         });
///* 465 */     this.cancelButton = new JButton("Cancel");
///* 466 */     this.cancelButton.setToolTipText("Close the dialog without applying any setting changes.");
///* 467 */     this.cancelButton.addActionListener(new ActionListener()
///*     */         {
///*     */           public void actionPerformed(ActionEvent e) {
///* 470 */             SettingsDialog.this.dialog.dispose();
///*     */           }
///*     */         });
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */     
///* 480 */     this.dialogCentered = new JCheckBox("Dialog centered", ((Magnifier)getOwner()).dialogDisplayCenter.isEnabled());
///* 481 */     this.dialogCentered.setToolTipText("Whether to center this dialog over the Magnifier.");
///* 482 */     buttonRow.add(this.applyButton);
///* 483 */     buttonRow.add(this.cancelButton);
///* 484 */     buttonRow.add(this.dialogCentered);
///* 485 */     return buttonRow;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   private JPanel getAutomaticCaptureSettingsPanel() {
///* 492 */     JPanel automaticCaptureSettings = new JPanel();
///* 493 */     automaticCaptureSettings.setBorder(new TitledBorder("Automatic Capture"));
///* 494 */     Box automaticCaptureSettingsBox = Box.createHorizontalBox();
///* 495 */     automaticCaptureSettings.add(automaticCaptureSettingsBox);
///* 496 */     this.captureResizeCheckBox = new JCheckBox("Capture upon resize", ((Magnifier)getOwner()).captureResize.isEnabled());
///* 497 */     this.captureMoveCheckBox = new JCheckBox("Capture upon move", ((Magnifier)getOwner()).captureMove.isEnabled());
///* 498 */     this.captureRescaleCheckBox = new JCheckBox("Capture upon rescale", ((Magnifier)getOwner()).captureRescale.isEnabled());
///* 499 */     JPanel checkboxColumn = new JPanel(new GridLayout(3, 1));
///* 500 */     checkboxColumn.add(this.captureResizeCheckBox);
///* 501 */     checkboxColumn.add(this.captureMoveCheckBox);
///* 502 */     checkboxColumn.add(this.captureRescaleCheckBox);
///* 503 */     automaticCaptureSettingsBox.add(checkboxColumn);
///* 504 */     return automaticCaptureSettings;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   private JPanel getCaptureDisplayPanel() {
///* 512 */     JPanel captureDisplaySetting = new JPanel();
///* 513 */     captureDisplaySetting.setBorder(new TitledBorder("Capture and Display"));
///* 514 */     Box captureDisplaySettingsBox = Box.createHorizontalBox();
///* 515 */     captureDisplaySetting.add(captureDisplaySettingsBox);
///* 516 */     this.captureDisplayCenteredButton = new JRadioButton("Capture area behind magnifier and display centered", ((Magnifier)getOwner()).captureDisplayCenter.isEnabled());
///*     */     
///* 518 */     this.captureDisplayUpperleftButton = new JRadioButton("Capture area behind magnifier and display upper-left", ((Magnifier)getOwner()).captureDisplayUpperleft.isEnabled());
///*     */     
///* 520 */     ButtonGroup displayButtonGroup = new ButtonGroup();
///* 521 */     displayButtonGroup.add(this.captureDisplayCenteredButton);
///* 522 */     displayButtonGroup.add(this.captureDisplayUpperleftButton);
///* 523 */     JPanel radioColumn = new JPanel(new GridLayout(2, 1));
///* 524 */     radioColumn.add(this.captureDisplayCenteredButton);
///* 525 */     radioColumn.add(this.captureDisplayUpperleftButton);
///* 526 */     JPanel radioLabelColumn = new JPanel(new GridLayout(1, 1));
///* 527 */     captureDisplaySettingsBox.add(radioColumn);
///* 528 */     return captureDisplaySetting;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   private JPanel getScribblerPanel(final JDialog dialog) {
///* 535 */     JPanel scribblerSettings = new JPanel();
///* 536 */     scribblerSettings.setBorder(new TitledBorder("Scribbler"));
///* 537 */     Box scribblerSettingsBox = Box.createHorizontalBox();
///* 538 */     scribblerSettings.add(scribblerSettingsBox);
///* 539 */     this.lineWidthSetting = new JComboBox<Integer>(this.scribblerLineWidthSettings);
///* 540 */     this.lineWidthSetting.setToolTipText("Scribbler line thickness in pixels.");
///* 541 */     this.lineWidthSetting.setSelectedIndex(((Magnifier)getOwner()).scribblerSettings.getLineWidth() - 1);
///* 542 */     this.lineColorSetting = new JButton("   ");
///* 543 */     this.lineColorSetting.setToolTipText("Click here to change Scribbler line color.");
///* 544 */     this.lineColorSetting.setBackground(((Magnifier)getOwner()).scribblerSettings.getLineColor());
///* 545 */     this.lineColorSetting.addActionListener(new ActionListener()
///*     */         {
///*     */           public void actionPerformed(ActionEvent e) {
///* 548 */             Color newColor = JColorChooser.showDialog(dialog, "Scribbler line color", SettingsDialog.this.lineColorSetting.getBackground());
///* 549 */             SettingsDialog.this.lineColorSetting.setBackground(newColor);
///*     */           }
///*     */         });
///* 552 */     this.scribblerLineColorSetting = this.lineColorSetting.getBackground();
///* 553 */     JPanel settingsColumn = new JPanel(new GridLayout(2, 1, 5, 5));
///* 554 */     settingsColumn.add(this.lineWidthSetting);
///* 555 */     settingsColumn.add(this.lineColorSetting);
///* 556 */     JPanel labelColumn = new JPanel(new GridLayout(2, 1, 5, 5));
///* 557 */     labelColumn.add(new JLabel("Line width ", 2));
///* 558 */     labelColumn.add(new JLabel("Line color ", 2));
///* 559 */     scribblerSettingsBox.add(labelColumn);
///* 560 */     scribblerSettingsBox.add(settingsColumn);
///* 561 */     return scribblerSettings;
///*     */   }
///*     */ }


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/tools/SettingsDialog2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */