/*
 * @(#)VisuallyLayoutableIcon.java  
 *
 * Copyright (c) 2005-2013 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package ke.co.shardx.zuhura;

import java.awt.*;
import javax.swing.*;
/**
 * VisuallyLayoutableIcon takes an icon and a layout rectangle. The layout rectangle is
 * relative to the upper left corner of the icon.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class VisuallyLayoutableIcon implements Icon {
    private Icon icon;
    private Rectangle layoutRect;
    
    /**
     * Creates a new instance.
     */
    public VisuallyLayoutableIcon(Icon icon, int x, int y, int width, int height) {
        this(icon, new Rectangle(x, y, width, height));
    }
    
    /**
     * Creates a new instance.
     */
    public VisuallyLayoutableIcon(Icon icon, Rectangle layoutRect) {
        this.icon = icon;
        this.layoutRect = layoutRect;
    }
    
    public int getIconHeight() {
        return layoutRect.height;
    }
    
    public int getIconWidth() {
        return layoutRect.width;
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y) {
        icon.paintIcon(c, g, x - layoutRect.x, y - layoutRect.y);
    }
    
}
