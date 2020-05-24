/*
 * @(#)TextFieldTest.java  1.0  13 February 2005
 *
 * Copyright (c) 2004 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package test;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

/**
 * TextFieldTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0  13 February 2005  Created.
 */
public class TextFieldTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public TextFieldTest() {
        initComponents();
        
         
        for (JComponent c:new JComponent[]{smallField1,smallField2,smallField3,smallLabel}) {
            c.putClientProperty("JComponent.sizeVariant","small");
        }
        for (JComponent c:new JComponent[]{miniField1,miniField2,miniField3,miniLabel}) {
            c.putClientProperty("JComponent.sizeVariant","mini");
        }
        /*
        JPopupMenu m = new JPopupMenu();
        m.add(new DefaultEditorKit.CutAction());
        m.add(new DefaultEditorKit.CopyAction());
        m.add(new DefaultEditorKit.PasteAction());
        jTextField1.setComponentPopupMenu(m);
         */
        
        // Prevent text components from becoming too small in GridBagLayout
        for (Component c : getComponents()) {
            if (c instanceof JTextComponent) {
                c.setMinimumSize(c.getPreferredSize());
            }
        }
        
        
        // Try to get a better layout with J2SE6
        try {
            int BASELINE_LEADING = GridBagConstraints.class.getDeclaredField("BASELINE_LEADING").getInt(null);
            GridBagLayout layout = (GridBagLayout) getLayout();
            for (Component c : getComponents()) {
                GridBagConstraints gbc = layout.getConstraints(c);
                if (gbc.anchor == GridBagConstraints.WEST) {
                    gbc.anchor = BASELINE_LEADING;
                    layout.setConstraints(c, gbc);
                }
            }
        } catch (Exception ex) {
            // bail
        }

    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        smallField1 = new javax.swing.JTextField();
        smallLabel = new javax.swing.JLabel();
        smallField2 = new javax.swing.JTextField();
        smallField3 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        miniField1 = new javax.swing.JTextField();
        miniLabel = new javax.swing.JLabel();
        miniField2 = new javax.swing.JTextField();
        miniField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());

        jTextField1.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jTextField1, gridBagConstraints);

        jLabel7.setText("Regular Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel7, gridBagConstraints);

        jTextField2.setEditable(false);
        jTextField2.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jTextField2, gridBagConstraints);

        jTextField3.setText("Ångström H");
        jTextField3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jTextField3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        add(jSeparator2, gridBagConstraints);

        smallField1.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallField1, gridBagConstraints);

        smallLabel.setText("Small Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(smallLabel, gridBagConstraints);

        smallField2.setEditable(false);
        smallField2.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallField2, gridBagConstraints);

        smallField3.setText("Ångström H");
        smallField3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallField3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        add(jSeparator3, gridBagConstraints);

        miniField1.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(miniField1, gridBagConstraints);

        miniLabel.setText("Mini Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(miniLabel, gridBagConstraints);

        miniField2.setEditable(false);
        miniField2.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(miniField2, gridBagConstraints);

        miniField3.setText("Ångström H");
        miniField3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(miniField3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 99;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField miniField1;
    private javax.swing.JTextField miniField2;
    private javax.swing.JTextField miniField3;
    private javax.swing.JLabel miniLabel;
    private javax.swing.JTextField smallField1;
    private javax.swing.JTextField smallField2;
    private javax.swing.JTextField smallField3;
    private javax.swing.JLabel smallLabel;
    // End of variables declaration//GEN-END:variables
    
}