/*
 * @(#)Quaqua15ColorPicker.java  2.0  2007-06-12
 *
 * Copyright (c) 2005-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package ch.randelshofer.quaqua.colorchooser;

import ch.randelshofer.quaqua.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;
import java.security.*;
/**
 * Quaqua15ColorPicker.
 *
 *
 *
 * @author Werner Randelshofer
 * @version 2.0 2007-06-12 Added support for multiple screens.
 * <br>1.2 2006-04-23 Retrieve labels from UIManager.
 * <br>1.1.1 2006-03-15 Forgot to create robot instance.
 * <br>1.1 2006-03-06 Abort picker when the user presses the Escape-Key.
 * <br>1.0 December 18, 2005 Created.
 */
public class Quaqua15ColorPicker extends AbstractColorChooserPanel {
    /**
     * This frame is constantly moved to the current location of the mouse.
     * This ensures that we can trap mouse clicks while the picker cursor is
     * showing. Also, by tying the picker cursor to this frame, we ensure that
     * the picker cursor (the magnifying glass) is shown.
     */
    private Dialog pickerFrame;
    
    private Timer pickerTimer;
    
    
    /**
     * Holds the image of the picker cursor.
     */
    private BufferedImage cursorImage;
    /**
     * Graphics object used for drawing on the cursorImage.
     */
    private Graphics2D cursorGraphics;
    
    /**
     * The picker cursor.
     */
    private Cursor pickerCursor;
    
    /**
     * The hot spot of the cursor.
     */
    private Point hotSpot;
    
    /**
     * Offset from the hot spot of the pickerCursor to the pixel that we want to pick.
     * We can't pick the color at the hotSpot of the cursor, because this point
     * is obscured by the pickerFrame.
     */
    private Point pickOffset;
    
    
    /**
     * The magnifying glass image.
     */
    private BufferedImage magnifierImage;
    
    /**
     * The robot is used for creating screen captures.
     */
    private Robot robot;
    
    /**
     * The graphics device for which we created the robot.
     */
    private GraphicsDevice captureScreen;
    
    /**
     * This is true, if we can't capture from this screen.
     */
    private boolean isBadScreen = false;
    
    private Color previousColor = Color.white;
    private Point previousLoc = new Point();
    private Point pickLoc = new Point();
    private Point captureOffset = new Point();
    private Rectangle captureRect;
    private final static Color transparentColor = new Color(0,true);
    private Rectangle zoomRect;
    private Rectangle glassRect;
    
    /**
     * Creates a new instance.
     */
    public Quaqua15ColorPicker() {
        // Try immediately to create a screen capture in order to fail quickly, when
        // we can't provide a color picker functionality.
        try {
            captureScreen = GraphicsEnvironment.
                    getLocalGraphicsEnvironment().
                    getDefaultScreenDevice();
            
            robot = new Robot();
            robot.createScreenCapture(new Rectangle(0,0,1,1));
        } catch (AWTException e) {
            throw new AccessControlException("Unable to capture screen");
        }
        
    }
    
    /**
     * Gets the picker frame.
     * If the frame does not yet exist, it is created along with all the other
     * objects that are needed to make the picker work.
     */
    private Dialog getPickerFrame() {
        if (pickerFrame == null) {
            Window owner = SwingUtilities.getWindowAncestor(this);
            if (owner instanceof Dialog) {
                pickerFrame = new Dialog((Dialog) owner);
            } else if (owner instanceof Frame) {
                pickerFrame = new Dialog((Frame) owner);
            } else {
                pickerFrame = new Dialog(new JFrame());
            }
            
            pickerFrame.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    pickFinish();
                }
                public void mouseExited(MouseEvent evt) {
                    updatePicker();
                }
            });
            
            pickerFrame.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent evt) {
                    updatePicker();
                }
            });
            pickerFrame.setSize(3,3);
            pickerFrame.setUndecorated(true);
            pickerFrame.setAlwaysOnTop(true);
            
            pickerFrame.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ESCAPE :
                            pickCancel();
                            break;
                        case KeyEvent.VK_ENTER :
                            pickFinish();
                            break;
                    }
                }
            });
            
            magnifierImage = (BufferedImage) UIManager.get("ColorChooser.colorPickerMagnifier");
            glassRect = (Rectangle) UIManager.get("ColorChooser.colorPickerGlassRect");
            zoomRect = (Rectangle) UIManager.get("ColorChooser.colorPickerZoomRect");
            hotSpot = (Point) UIManager.get("ColorChooser.colorPickerHotSpot");//new Point(29, 29);
            captureRect = new Rectangle((Rectangle) UIManager.get("ColorChooser.colorPickerCaptureRect"));
            pickOffset = (Point) UIManager.get("ColorChooser.colorPickerPickOffset");
            captureOffset = new Point(captureRect.x, captureRect.y);
            cursorImage = getGraphicsConfiguration().createCompatibleImage(magnifierImage.getWidth(), magnifierImage.getHeight(), Transparency.TRANSLUCENT);
            cursorGraphics = cursorImage.createGraphics();
            cursorGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            
            pickerTimer = new Timer(5, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    updatePicker();
                }
            });
        }
        return pickerFrame;
    }
    
    
    /**
     * Updates the color picker.
     */
    protected void updatePicker() {
        if (pickerFrame != null && pickerFrame.isShowing()) {
            PointerInfo info = MouseInfo.getPointerInfo();
            
            
            // Get a robot for the screen
            GraphicsDevice screen = info.getDevice();
            if (captureScreen != screen) {
                isBadScreen = false;
                try {
                    robot = new Robot(screen);
                    captureScreen = screen;
                } catch (AWTException ex) {
                    ex.printStackTrace();
                    isBadScreen = true;
                }
            }
            
            Point mouseLoc = info.getLocation();
            pickerFrame.setLocation(mouseLoc.x - pickerFrame.getWidth() / 2, mouseLoc.y - pickerFrame.getHeight() / 2);
            
            pickLoc.x = mouseLoc.x + pickOffset.x;
            pickLoc.y = mouseLoc.y + pickOffset.y;
            
            if (pickLoc.x >= 0 && pickLoc.y >= 0) {
                Color c = robot.getPixelColor(pickLoc.x, pickLoc.y);
                if (! c.equals(previousColor) || ! mouseLoc.equals(previousLoc)) {
                    previousColor = c;
                    previousLoc = mouseLoc;
                    
                    captureRect.setLocation(mouseLoc.x + captureOffset.x, mouseLoc.y + captureOffset.y);
                    if (robot != null && ! isBadScreen && captureRect.x >= 0 && captureRect.y >= 0) {
                        // Clear the cursor graphics
                        cursorGraphics.setComposite(AlphaComposite.Src);
                        cursorGraphics.setColor(transparentColor);
                        cursorGraphics.fillRect(0, 0, cursorImage.getWidth(), cursorImage.getHeight());
                        
                        try {
                            // Fill the area for the zoomed screen capture with the
                            // color on the screen
                            cursorGraphics.setColor(robot.getPixelColor(pickLoc.x, pickLoc.y));
                            cursorGraphics.fillOval(glassRect.x,glassRect.y,glassRect.width,glassRect.height);
                            
                            // Paint the screen capture with a zoom factor of 5
                            BufferedImage capture = robot.createScreenCapture(captureRect);
                            cursorGraphics.setComposite(AlphaComposite.SrcIn);
                            cursorGraphics.drawImage(capture, zoomRect.x, zoomRect.y, zoomRect.width, zoomRect.height, this);
                        } catch (Throwable e) {
                            // we can't capture on this screen for some reason
                            isBadScreen = true;
                        }
                    }
                    if (isBadScreen) {
                    // Fill the area for the zoomed screen capture with the
                    // color on the screen
                    cursorGraphics.setComposite(AlphaComposite.SrcOver);
                    cursorGraphics.setColor(robot.getPixelColor(pickLoc.x, pickLoc.y));
                    cursorGraphics.fillOval(glassRect.x,glassRect.y,glassRect.width,glassRect.height);
                    }
                    
                    // Draw the magnifying glass image
                    cursorGraphics.setComposite(AlphaComposite.SrcOver);
                    cursorGraphics.drawImage(magnifierImage, 0 , 0, this);
                    
                    // We need to create a new subImage. This forces that
                    // the color picker uses the new imagery.
                    BufferedImage subImage = cursorImage.getSubimage(0,0,cursorImage.getWidth(),cursorImage.getHeight());
                    pickerFrame.setCursor(getToolkit().createCustomCursor(cursorImage, hotSpot, "ColorPicker"));
                }
            }
        }
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        pickerButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pickerButton.setBorderPainted(false);
        pickerButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        pickerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickBegin(evt);
            }
        });

        add(pickerButton, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    private void pickBegin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickBegin
        getPickerFrame();
        pickerTimer.start();
        getPickerFrame().setVisible(true);
    }//GEN-LAST:event_pickBegin
    
    protected void pickFinish() {
        pickerTimer.stop();
        pickerFrame.setVisible(false);
        PointerInfo info = MouseInfo.getPointerInfo();
        Point loc = info.getLocation();
        Color c = robot.getPixelColor(loc.x + pickOffset.x, loc.y + pickOffset.y);
        getColorSelectionModel().setSelectedColor(c);
    }
    protected void pickCancel() {
        pickerTimer.stop();
        pickerFrame.setVisible(false);
    }
    
    protected void buildChooser() {
        initComponents();
        pickerButton.setIcon(UIManager.getIcon("ColorChooser.colorPickerIcon"));
    }
    
    public String getDisplayName() {
        return UIManager.getString("ColorChooser.colorPicker");
    }
    
    public Icon getLargeDisplayIcon() {
        return UIManager.getIcon("ColorChooser.colorPickerIcon");
    }
    
    public Icon getSmallDisplayIcon() {
        return getLargeDisplayIcon();
    }
    
    public void updateChooser() {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton pickerButton;
    // End of variables declaration//GEN-END:variables
    
}
