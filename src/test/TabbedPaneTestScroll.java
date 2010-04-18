/*
 * @(#)TabbedPaneTestScroll.java
 *
 * Copyright (c) 2004-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package test;

import ch.randelshofer.quaqua.*;
import ch.randelshofer.quaqua.util.*;
import javax.swing.*;
/**
 * TabbedPaneTest.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class TabbedPaneTestScroll extends javax.swing.JPanel {
    
    /** Creates new form. */
    public TabbedPaneTestScroll() {
        initComponents();
        
        JTabbedPane[] panes = {
            tabbedPane1,
            tabbedPane2,
            tabbedPane3,
            tabbedPane4,
            tabbedPane5,
            tabbedPane6,
            tabbedPane7,
            tabbedPane8,
            tabbedPane9,
            tabbedPane10,
            tabbedPane11,
            tabbedPane12,
            tabbedPane13,
            tabbedPane14,
            tabbedPane15,
            tabbedPane16,
        };
        for (int i=0; i < panes.length; i++) {
            Methods.invokeIfExists(panes[i],"setTabLayoutPolicy", 1);
           // panes[i].setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        }

        //tabbedPane1.putClientProperty("Quaqua.TabbedPane.tabAlignment", new Integer(SwingConstants.LEADING));
        
        tabbedPane1.setIconAt(0, new ImageIcon(getClass().getResource("/ch/randelshofer/quaqua/images/FileView.computerIcon.png")));
       tabbedPane1.setIconAt(1, new ImageIcon(getClass().getResource("/ch/randelshofer/quaqua/images/FileView.hardDriveIcon.png")));
        tabbedPane1.setIconAt(2, new ImageIcon(getClass().getResource("/ch/randelshofer/quaqua/images/FileView.floppyDriveIcon.png")));
     }
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("TabbedPaneTestScroll: "+UIManager.getLookAndFeel().getName());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new TabbedPaneTestScroll());
        f.pack();
        f.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tabbedPane1 = new javax.swing.JTabbedPane();
        tabbedPane2 = new javax.swing.JTabbedPane();
        tabbedPane3 = new javax.swing.JTabbedPane();
        tabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tabbedPane5 = new javax.swing.JTabbedPane();
        tabbedPane6 = new javax.swing.JTabbedPane();
        tabbedPane7 = new javax.swing.JTabbedPane();
        tabbedPane8 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        enabledLabel = new javax.swing.JLabel();
        disabledLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tabbedPane9 = new javax.swing.JTabbedPane();
        tabbedPane10 = new javax.swing.JTabbedPane();
        tabbedPane11 = new javax.swing.JTabbedPane();
        tabbedPane12 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        tabbedPane13 = new javax.swing.JTabbedPane();
        tabbedPane14 = new javax.swing.JTabbedPane();
        tabbedPane15 = new javax.swing.JTabbedPane();
        tabbedPane16 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        smallLabel = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        tabbedPane2.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        tabbedPane2.setEnabled(false);

        tabbedPane3.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabbedPane3.setEnabled(false);

        tabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabbedPane4.addTab("HHH", jPanel1);
        tabbedPane4.addTab("tab2", jPanel2);
        tabbedPane4.addTab("tab3", jPanel3);

        tabbedPane3.addTab("HHH", tabbedPane4);
        tabbedPane3.addTab("tab2", tabbedPane5);
        tabbedPane3.addTab("tab3", tabbedPane6);

        tabbedPane2.addTab("HHH", tabbedPane3);
        tabbedPane2.addTab("tab2", tabbedPane7);
        tabbedPane2.addTab("tab3", tabbedPane8);

        tabbedPane1.addTab("HHH", tabbedPane2);
        tabbedPane1.addTab("tab2", jPanel7);
        tabbedPane1.addTab("tab3", jPanel8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        add(tabbedPane1, gridBagConstraints);

        enabledLabel.setText("Enabled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(enabledLabel, gridBagConstraints);

        disabledLabel.setText("Disabled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(disabledLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jSeparator1, gridBagConstraints);

        tabbedPane9.setFont(new java.awt.Font("Lucida Grande", 0, 11));

        tabbedPane10.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        tabbedPane10.setEnabled(false);
        tabbedPane10.setFont(new java.awt.Font("Lucida Grande", 0, 11));

        tabbedPane11.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabbedPane11.setEnabled(false);
        tabbedPane11.setFont(new java.awt.Font("Lucida Grande", 0, 11));

        tabbedPane12.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabbedPane12.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        tabbedPane12.addTab("HHH", jPanel4);
        tabbedPane12.addTab("tab2", jPanel5);
        tabbedPane12.addTab("tab3", jPanel6);

        tabbedPane11.addTab("HHH", tabbedPane12);

        tabbedPane13.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        tabbedPane11.addTab("tab2", tabbedPane13);
        tabbedPane11.addTab("tab3", tabbedPane14);

        tabbedPane10.addTab("HHH", tabbedPane11);
        tabbedPane10.addTab("tab2", tabbedPane15);
        tabbedPane10.addTab("tab3", tabbedPane16);

        tabbedPane9.addTab("HHH", tabbedPane10);
        tabbedPane9.addTab("tab2", jPanel9);
        tabbedPane9.addTab("tab3", jPanel10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        add(tabbedPane9, gridBagConstraints);

        smallLabel.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        smallLabel.setText("Small");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(smallLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel disabledLabel;
    private javax.swing.JLabel enabledLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel smallLabel;
    private javax.swing.JTabbedPane tabbedPane1;
    private javax.swing.JTabbedPane tabbedPane10;
    private javax.swing.JTabbedPane tabbedPane11;
    private javax.swing.JTabbedPane tabbedPane12;
    private javax.swing.JTabbedPane tabbedPane13;
    private javax.swing.JTabbedPane tabbedPane14;
    private javax.swing.JTabbedPane tabbedPane15;
    private javax.swing.JTabbedPane tabbedPane16;
    private javax.swing.JTabbedPane tabbedPane2;
    private javax.swing.JTabbedPane tabbedPane3;
    private javax.swing.JTabbedPane tabbedPane4;
    private javax.swing.JTabbedPane tabbedPane5;
    private javax.swing.JTabbedPane tabbedPane6;
    private javax.swing.JTabbedPane tabbedPane7;
    private javax.swing.JTabbedPane tabbedPane8;
    private javax.swing.JTabbedPane tabbedPane9;
    // End of variables declaration//GEN-END:variables
    
}
