/*
 * @(#)QuaquaEditorPaneUI.java  
 *
 * Copyright (c) 2004-2013 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package ke.co.shardx.zuhura;

import ke.co.shardx.zuhura.util.Debug;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.text.*;
import javax.swing.border.*;

/**
 * QuaquaEditorPaneUI.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class QuaquaEditorPaneUI extends BasicEditorPaneUI implements VisuallyLayoutable
{
    boolean oldDragState = false;
     private MouseListener popupListener;
   
    public static ComponentUI createUI(JComponent jcomponent) {
	return new QuaquaEditorPaneUI();
    }
    
    @Override
    protected void installListeners() {
        popupListener = createPopupListener();
        if (popupListener != null) {
            getComponent().addMouseListener(popupListener);
        }
        QuaquaTextCursorHandler.getInstance().installListeners(getComponent());
    }
    
    @Override
    protected void uninstallListeners() {
        if (popupListener != null) {
            getComponent().removeMouseListener(popupListener);
            popupListener = null;
        }
        QuaquaTextCursorHandler.getInstance().uninstallListeners(getComponent());
    }
    
    protected MouseListener createPopupListener() {
        return (MouseListener) UIManager.get(getPropertyPrefix()+".popupHandler");
    }
    
    @Override
    protected void installDefaults() {
	if (!QuaquaUtilities.isHeadless()) {
	    oldDragState = Methods.invokeGetter(getComponent(), "getDragEnabled", true);
	    Methods.invokeIfExists(getComponent(), "setDragEnabled", true);
	}
	super.installDefaults();
    }
    
    @Override
    protected void uninstallDefaults() {
	if (!QuaquaUtilities.isHeadless()) {
	    Methods.invokeIfExists(getComponent(), "setDragEnabled", oldDragState);
            }
	super.uninstallDefaults();
    }

    @Override
    protected void paintSafely(Graphics g) {
	Object oldHints = QuaquaUtilities.beginGraphics((Graphics2D) g);
	super.paintSafely(g);
	QuaquaUtilities.endGraphics((Graphics2D) g, oldHints);
        Debug.paint(g, getComponent(), this);
    }

    @Override
    protected Caret createCaret() {
	Window window = SwingUtilities.getWindowAncestor(getComponent());
	QuaquaCaret caret = new QuaquaCaret(window, getComponent());
	return caret;
    }
    
    @Override
    protected Highlighter createHighlighter() {
	return new QuaquaHighlighter();
    }
    
    public Insets getVisualMargin(JTextComponent tc) {
        return new Insets(0, 0, 0 ,0);
        /*
        Insets margin = (Insets) tc.getClientProperty("Quaqua.Component.visualMargin");
        if (margin == null) margin = UIManager.getInsets("Component.visualMargin");
        return (margin == null) ? new Insets(0, 0, 0 ,0) : margin;
         */
    }
    
    @Override
    public int getBaseline(JComponent c, int width, int height) {
        JTextComponent tc = (JTextComponent) c;
        Insets insets = tc.getInsets();
        FontMetrics fm = tc.getFontMetrics(tc.getFont());
        return insets.top + fm.getAscent();
    }
    public Rectangle getVisualBounds(JComponent c, int type, int width, int height) {
        Rectangle bounds = new Rectangle(0,0,width,height);
        if (type == VisuallyLayoutable.CLIP_BOUNDS) {
            return bounds;
        }
        
        JTextComponent b = (JTextComponent) c;
        if (type == VisuallyLayoutable.COMPONENT_BOUNDS
        && b.getBorder() != null) {
            Border border = b.getBorder();
            if (border instanceof UIResource) {
                InsetsUtil.subtractInto(getVisualMargin(b), bounds);
                // FIXME - Should derive this value from border
                // FIXME - If we had layout managers that supported baseline alignment,
                //              we wouldn't have to subtract one here
                bounds.height -= 1;
            }
        } else {
            bounds = getVisibleEditorRect();
            
            int baseline = getBaseline(b, width, height);
            Rectangle textBounds = Fonts.getPerceivedBounds(b.getText(), b.getFont(), c);
            if (bounds == null) {
                bounds = textBounds;
                bounds.y += baseline;
            } else {
                bounds.y = baseline + textBounds.y;
                bounds.height = textBounds.height;
            }
            bounds.x += 1;
            bounds.width -= 2;
        }
        return bounds;
    }
}