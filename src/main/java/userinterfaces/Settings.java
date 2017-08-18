/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userinterfaces;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import settings.OWASPT10Config;
import static userinterfaces.NewProjectWindow.setUIFont;

/**
 *
 * @author CHAM PC
 */
public class Settings extends javax.swing.JFrame {

    /**
     * Creates new form Settings
     */
    public Settings() {
        
        try {
            setUIFont(new javax.swing.plaf.FontUIResource("Segoe UI", Font.PLAIN, 14));
        } catch (Exception e) {
            
        }

        //Changing look and feel
        //for metal - javax.swing.plaf.metal.MetalLookAndFeel
        //for windows - com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            
        }
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        initComponents();
        populate_OWASPT10_table();
    }

    private void populate_OWASPT10_table(){
        ArrayList<String[]> OWASP_T10_list;
        OWASPT10Config readConfigs = new OWASPT10Config();
        
        try {
            OWASP_T10_list = readConfigs.loadConfigFile();
            
            DefaultTableModel tModel = (DefaultTableModel) this.OWASP_table.getModel();
            tModel.setRowCount(0);
            
            for (String[] OWASPType : OWASP_T10_list){
                tModel.addRow(OWASPType);
            }
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SettingsTabPane = new javax.swing.JTabbedPane();
        OWASP_top_10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OWASP_table = new javax.swing.JTable();
        nextBtn1 = new javax.swing.JButton();
        OWASP_Proactives = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        proactive_table = new javax.swing.JTable();
        addBtn1 = new javax.swing.JButton();
        deleteBtn1 = new javax.swing.JButton();
        nextBtn2 = new javax.swing.JButton();
        OWASP_mapping = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        OWASP_mapping_table = new javax.swing.JTable();
        saveBtn1 = new javax.swing.JButton();
        attackers_perspective = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        attackers_table = new javax.swing.JTable();
        nextBtn3 = new javax.swing.JButton();
        defensive_perspective = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        defensive_table = new javax.swing.JTable();
        addBtn2 = new javax.swing.JButton();
        deleteBtn2 = new javax.swing.JButton();
        nextBtn4 = new javax.swing.JButton();
        defensive_mapping = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        saveBtn2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");

        SettingsTabPane.setBackground(java.awt.Color.white);
        SettingsTabPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        SettingsTabPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        SettingsTabPane.setToolTipText("");
        SettingsTabPane.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        OWASP_top_10.setBackground(java.awt.Color.white);
        OWASP_top_10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        OWASP_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A1", null, null},
                {"A2", null, null},
                {"A3", null, null},
                {"A4", null, null},
                {"A5", null, null},
                {"A6", null, null},
                {"A7", null, null},
                {"A8", null, null},
                {"A9", null, null},
                {"A10", null, null}
            },
            new String [] {
                "ID", "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OWASP_table.setRowHeight(20);
        jScrollPane1.setViewportView(OWASP_table);

        nextBtn1.setText("Next");
        nextBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OWASP_top_10Layout = new javax.swing.GroupLayout(OWASP_top_10);
        OWASP_top_10.setLayout(OWASP_top_10Layout);
        OWASP_top_10Layout.setHorizontalGroup(
            OWASP_top_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OWASP_top_10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextBtn1)
                .addContainerGap())
        );
        OWASP_top_10Layout.setVerticalGroup(
            OWASP_top_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OWASP_top_10Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nextBtn1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        SettingsTabPane.addTab("OWASP TOP 10", OWASP_top_10);

        OWASP_Proactives.setBackground(java.awt.Color.white);

        proactive_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        proactive_table.setRowHeight(20);
        jScrollPane2.setViewportView(proactive_table);

        addBtn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addBtn1.setText("+");
        addBtn1.setMaximumSize(new java.awt.Dimension(37, 23));
        addBtn1.setMinimumSize(new java.awt.Dimension(37, 23));
        addBtn1.setPreferredSize(new java.awt.Dimension(37, 23));

        deleteBtn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteBtn1.setText("-");

        nextBtn2.setText("Next");

        javax.swing.GroupLayout OWASP_ProactivesLayout = new javax.swing.GroupLayout(OWASP_Proactives);
        OWASP_Proactives.setLayout(OWASP_ProactivesLayout);
        OWASP_ProactivesLayout.setHorizontalGroup(
            OWASP_ProactivesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(OWASP_ProactivesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextBtn2)
                .addContainerGap())
        );
        OWASP_ProactivesLayout.setVerticalGroup(
            OWASP_ProactivesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OWASP_ProactivesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(OWASP_ProactivesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextBtn2)
                    .addComponent(deleteBtn1))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        SettingsTabPane.addTab("OWASP Proactives", OWASP_Proactives);

        OWASP_mapping.setBackground(java.awt.Color.white);

        OWASP_mapping_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"C1", null, null, null, null, null, null, null, null, null, null},
                {"C2", null, null, null, null, null, null, null, null, null, null},
                {"C3", null, null, null, null, null, null, null, null, null, null},
                {"C4", null, null, null, null, null, null, null, null, null, null},
                {"C4", null, null, null, null, null, null, null, null, null, null},
                {"C5", null, null, null, null, null, null, null, null, null, null},
                {"C6", null, null, null, null, null, null, null, null, null, null},
                {"C7", null, null, null, null, null, null, null, null, null, null},
                {"C8", null, null, null, null, null, null, null, null, null, null},
                {"C9", null, null, null, null, null, null, null, null, null, null},
                {"C10", null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OWASP_mapping_table.setRowHeight(20);
        jScrollPane3.setViewportView(OWASP_mapping_table);

        saveBtn1.setText("Save");

        javax.swing.GroupLayout OWASP_mappingLayout = new javax.swing.GroupLayout(OWASP_mapping);
        OWASP_mapping.setLayout(OWASP_mappingLayout);
        OWASP_mappingLayout.setHorizontalGroup(
            OWASP_mappingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(OWASP_mappingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn1)
                .addContainerGap())
        );
        OWASP_mappingLayout.setVerticalGroup(
            OWASP_mappingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OWASP_mappingLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(saveBtn1)
                .addContainerGap())
        );

        SettingsTabPane.addTab("OWASP Top 10 Mapping", OWASP_mapping);

        attackers_perspective.setBackground(java.awt.Color.white);

        attackers_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"S", "Spoofing", "Authentication", null},
                {"T", "Tampering", "Integrity", null},
                {"R", "Repudiation", "Non-Repudiation", null},
                {"I", "Information Disclosure", "Confidentiality", null},
                {"D", "Denial of Service", "Availability", null},
                {"E", "Elevation of Privileges", "Authorization", null}
            },
            new String [] {
                "ID", "Category", "Security Control", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        attackers_table.setRowHeight(20);
        jScrollPane4.setViewportView(attackers_table);

        nextBtn3.setText("Next");
        nextBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout attackers_perspectiveLayout = new javax.swing.GroupLayout(attackers_perspective);
        attackers_perspective.setLayout(attackers_perspectiveLayout);
        attackers_perspectiveLayout.setHorizontalGroup(
            attackers_perspectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attackers_perspectiveLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextBtn3)
                .addContainerGap())
        );
        attackers_perspectiveLayout.setVerticalGroup(
            attackers_perspectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attackers_perspectiveLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nextBtn3)
                .addGap(0, 111, Short.MAX_VALUE))
        );

        SettingsTabPane.addTab("STRIDE - Attacker's Perspective", attackers_perspective);

        defensive_perspective.setBackground(java.awt.Color.white);

        defensive_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        defensive_table.setRowHeight(20);
        jScrollPane5.setViewportView(defensive_table);

        addBtn2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addBtn2.setText("+");
        addBtn2.setMaximumSize(new java.awt.Dimension(37, 23));
        addBtn2.setMinimumSize(new java.awt.Dimension(37, 23));
        addBtn2.setPreferredSize(new java.awt.Dimension(37, 23));
        addBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtn2ActionPerformed(evt);
            }
        });

        deleteBtn2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteBtn2.setText("-");
        deleteBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn2ActionPerformed(evt);
            }
        });

        nextBtn4.setText("Next");
        nextBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtn4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout defensive_perspectiveLayout = new javax.swing.GroupLayout(defensive_perspective);
        defensive_perspective.setLayout(defensive_perspectiveLayout);
        defensive_perspectiveLayout.setHorizontalGroup(
            defensive_perspectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(defensive_perspectiveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextBtn4)
                .addContainerGap())
        );
        defensive_perspectiveLayout.setVerticalGroup(
            defensive_perspectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defensive_perspectiveLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(defensive_perspectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextBtn4)
                    .addComponent(deleteBtn2))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        SettingsTabPane.addTab("STRIDE - Defensive Perspective", defensive_perspective);

        defensive_mapping.setBackground(java.awt.Color.white);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Authentication", null, null, null, null, null, null},
                {"Authorization", null, null, null, null, null, null},
                {"Configuration Management", null, null, null, null, null, null},
                {"Data Protection in Storage and Transit", null, null, null, null, null, null},
                {"Data Validation / Parameter Validation", null, null, null, null, null, null},
                {"Error Handling and Exception Management", null, null, null, null, null, null},
                {"User and Session Management", null, null, null, null, null, null},
                {"Auditing and Logging", null, null, null, null, null, null}
            },
            new String [] {
                "", "Spoofing", "Tampering", "Repudiation", "Information Disclosure", "Denial of Service", "Elevation of Privilege"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane6.setViewportView(jTable1);

        saveBtn2.setText("Save");

        javax.swing.GroupLayout defensive_mappingLayout = new javax.swing.GroupLayout(defensive_mapping);
        defensive_mapping.setLayout(defensive_mappingLayout);
        defensive_mappingLayout.setHorizontalGroup(
            defensive_mappingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, defensive_mappingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn2)
                .addContainerGap())
        );
        defensive_mappingLayout.setVerticalGroup(
            defensive_mappingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defensive_mappingLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveBtn2)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        SettingsTabPane.addTab("Defensive Mapping", defensive_mapping);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SettingsTabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SettingsTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextBtn1ActionPerformed

    private void nextBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextBtn3ActionPerformed

    private void deleteBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBtn2ActionPerformed

    private void nextBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nextBtn4ActionPerformed

    private void addBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBtn2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OWASP_Proactives;
    private javax.swing.JPanel OWASP_mapping;
    private javax.swing.JTable OWASP_mapping_table;
    public javax.swing.JTable OWASP_table;
    private javax.swing.JPanel OWASP_top_10;
    public javax.swing.JTabbedPane SettingsTabPane;
    private javax.swing.JButton addBtn1;
    private javax.swing.JButton addBtn2;
    private javax.swing.JPanel attackers_perspective;
    private javax.swing.JTable attackers_table;
    private javax.swing.JPanel defensive_mapping;
    private javax.swing.JPanel defensive_perspective;
    private javax.swing.JTable defensive_table;
    private javax.swing.JButton deleteBtn1;
    private javax.swing.JButton deleteBtn2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton nextBtn1;
    private javax.swing.JButton nextBtn2;
    private javax.swing.JButton nextBtn3;
    private javax.swing.JButton nextBtn4;
    private javax.swing.JTable proactive_table;
    private javax.swing.JButton saveBtn1;
    private javax.swing.JButton saveBtn2;
    // End of variables declaration//GEN-END:variables
}
