/*
 * @(#)QuaquaLookAndFeel.java  
 *
 * Copyright (c) 2003-2013 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package ke.co.shardx.zuhura;

/**
 * A J2SE5 backwards compatible version of the {@link QuaquaLookAndFeel} class.
 *
 * @author Werner Randelshofer, Switzerland
 * @version $Id$
 */
public class QuaquaLookAndFeel15 extends LookAndFeelProxy15 {
    
    /** Creates a new instance of QuaquaLookAndFeel */
    public QuaquaLookAndFeel15() {
        super(QuaquaManager.getLookAndFeel());
    }
    
}
