package fees_management_system;
import java.awt.Color;
public class home extends javax.swing.JFrame {
    public home() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelAddfees = new javax.swing.JPanel();
        btnAddfees = new javax.swing.JLabel();
        panelSeaechrecord = new javax.swing.JPanel();
        btnSearchrecord = new javax.swing.JLabel();
        panelViewrecord = new javax.swing.JPanel();
        btnViewrecord = new javax.swing.JLabel();
        panelEditcourse = new javax.swing.JPanel();
        btnEditcourse = new javax.swing.JLabel();
        panelViewcourse = new javax.swing.JPanel();
        btnViewcourse = new javax.swing.JLabel();
        panelViewreport = new javax.swing.JPanel();
        btnViewreport = new javax.swing.JLabel();
        panelAbout = new javax.swing.JPanel();
        btnAbout = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jLabel1.setText("Old Magardas road, dr.radhakrishna marg, andheri east, Mumbai 400020 ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 810, 60));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel10.setText("Golden Institute");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 290, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1630, 180));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAddfees.setBackground(new java.awt.Color(255, 204, 255));
        panelAddfees.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 102), null));
        panelAddfees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAddfeesMouseEntered(evt);
            }
        });
        panelAddfees.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAddfees.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnAddfees.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/button.png"))); // NOI18N
        btnAddfees.setText("      Add Fees");
        btnAddfees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddfeesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddfeesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddfeesMouseExited(evt);
            }
        });
        panelAddfees.add(btnAddfees, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 210));

        jPanel2.add(panelAddfees, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 320, 210));

        panelSeaechrecord.setBackground(new java.awt.Color(255, 204, 255));
        panelSeaechrecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 51), null));
        panelSeaechrecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearchrecord.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnSearchrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/search-documentpng.png"))); // NOI18N
        btnSearchrecord.setText(" Search Record");
        btnSearchrecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchrecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchrecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchrecordMouseExited(evt);
            }
        });
        panelSeaechrecord.add(btnSearchrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 4, 300, 200));

        jPanel2.add(panelSeaechrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 320, 210));

        panelViewrecord.setBackground(new java.awt.Color(255, 204, 255));
        panelViewrecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 102), null));
        panelViewrecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewrecord.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnViewrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/book.png"))); // NOI18N
        btnViewrecord.setText("    View Record");
        btnViewrecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewrecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewrecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewrecordMouseExited(evt);
            }
        });
        panelViewrecord.add(btnViewrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 1, 310, 210));

        jPanel2.add(panelViewrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 130, 320, 210));

        panelEditcourse.setBackground(new java.awt.Color(255, 204, 255));
        panelEditcourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 102), null));
        panelEditcourse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditcourse.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnEditcourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/edit2.png"))); // NOI18N
        btnEditcourse.setText("   Edit Course");
        btnEditcourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditcourseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditcourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditcourseMouseExited(evt);
            }
        });
        panelEditcourse.add(btnEditcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 7, 310, 200));

        jPanel2.add(panelEditcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 320, 210));

        panelViewcourse.setBackground(new java.awt.Color(255, 204, 255));
        panelViewcourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 102), null));
        panelViewcourse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewcourse.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnViewcourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/checklist.png"))); // NOI18N
        btnViewcourse.setText("Generate Report");
        btnViewcourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewcourseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewcourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewcourseMouseExited(evt);
            }
        });
        panelViewcourse.add(btnViewcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 13, 300, 184));

        jPanel2.add(panelViewcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 320, 210));

        panelViewreport.setBackground(new java.awt.Color(255, 204, 255));
        panelViewreport.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 102), null));
        panelViewreport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnViewreport.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnViewreport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/printer.png"))); // NOI18N
        btnViewreport.setText("Print Reciept");
        btnViewreport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewreportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewreportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewreportMouseExited(evt);
            }
        });
        panelViewreport.add(btnViewreport, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 13, 300, 184));

        jPanel2.add(panelViewreport, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 450, 320, 210));

        panelAbout.setBackground(new java.awt.Color(255, 204, 255));
        panelAbout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 102), null));
        panelAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAboutMouseEntered(evt);
            }
        });
        panelAbout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAbout.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/profile.png"))); // NOI18N
        btnAbout.setText("  About");
        btnAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAboutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAboutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAboutMouseExited(evt);
            }
        });
        panelAbout.add(btnAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 90));

        jPanel2.add(panelAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 690, 180, 90));

        panelLogout.setBackground(new java.awt.Color(255, 204, 255));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), new java.awt.Color(255, 255, 102), null));
        panelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLogoutMouseEntered(evt);
            }
        });
        panelLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        panelLogout.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 90));

        jPanel2.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 180, 90));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 1630, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAboutMouseEntered
       
    }//GEN-LAST:event_panelAboutMouseEntered

    private void btnAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(255,153,255);
        panelAbout.setBackground(clr);
        
    }//GEN-LAST:event_btnAboutMouseEntered

    private void btnAboutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseExited
        // TODO add your handling code here:
        Color clr=new Color(255,204,255);
        panelAbout.setBackground(clr);
    }//GEN-LAST:event_btnAboutMouseExited

    private void btnSearchrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchrecordMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(255,153,255);
  panelSeaechrecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchrecordMouseEntered

    private void btnSearchrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchrecordMouseExited
         Color clr=new Color(255,204,255);
  panelSeaechrecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchrecordMouseExited

    private void btnViewrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewrecordMouseEntered
        Color clr=new Color(255,153,255);
        panelViewrecord.setBackground(clr);
    }//GEN-LAST:event_btnViewrecordMouseEntered

    private void btnViewrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewrecordMouseExited
         Color clr=new Color(255,204,255);
        panelViewrecord.setBackground(clr);
    }//GEN-LAST:event_btnViewrecordMouseExited

    private void btnEditcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditcourseMouseEntered
         Color clr=new Color(255,153,255);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_btnEditcourseMouseEntered

    private void btnEditcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditcourseMouseExited
         Color clr=new Color(255,204,255);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_btnEditcourseMouseExited

    private void btnViewcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewcourseMouseEntered
        Color clr=new Color(255,153,255);
        panelViewcourse.setBackground(clr);
    }//GEN-LAST:event_btnViewcourseMouseEntered

    private void btnViewcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewcourseMouseExited
         Color clr=new Color(255,204,255);
        panelViewcourse.setBackground(clr);
    }//GEN-LAST:event_btnViewcourseMouseExited

    private void btnViewreportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewreportMouseEntered
        Color clr=new Color(255,153,255);
        panelViewreport.setBackground(clr);
    }//GEN-LAST:event_btnViewreportMouseEntered

    private void btnViewreportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewreportMouseExited
        Color clr=new Color(255,204,255);
        panelViewreport.setBackground(clr);
    }//GEN-LAST:event_btnViewreportMouseExited

    private void btnAddfeesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddfeesMouseEntered
        Color clr=new Color(255,153,255);
        panelAddfees.setBackground(clr);
    }//GEN-LAST:event_btnAddfeesMouseEntered

    private void btnAddfeesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddfeesMouseExited
        Color clr=new Color(255,204,255);
        panelAddfees.setBackground(clr);
    }//GEN-LAST:event_btnAddfeesMouseExited

    private void panelAddfeesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAddfeesMouseEntered
//        Color clr=new Color(255,153,255);
//        jPanel9.setBackground(clr);
    }//GEN-LAST:event_panelAddfeesMouseEntered

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        Color clr=new Color(255,153,255);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        Color clr=new Color(255,204,255);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseExited

    private void panelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseEntered
//        Color clr=new Color(255,153,255);
//        jPanel9.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseEntered

    private void btnAddfeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddfeesMouseClicked
        // TODO add your handling code here:
        AddFees addFees=new AddFees();
        addFees.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddfeesMouseClicked

    private void btnSearchrecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchrecordMouseClicked
        // TODO add your handling code here:
        SearchRecord search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchrecordMouseClicked

    private void btnEditcourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditcourseMouseClicked
        
        EditCourse edit = new EditCourse();
        edit.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnEditcourseMouseClicked

    private void btnViewrecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewrecordMouseClicked
        
        ViewAllRecords view = new ViewAllRecords();
        view.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnViewrecordMouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseClicked
        CopyRightPage copy = new CopyRightPage();
        copy.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAboutMouseClicked

    private void btnViewreportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewreportMouseClicked
        PrintReciept print = new PrintReciept();
        print.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewreportMouseClicked

    private void btnViewcourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewcourseMouseClicked
        GenerateReport generate = new GenerateReport();
        generate.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewcourseMouseClicked

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAbout;
    private javax.swing.JLabel btnAddfees;
    private javax.swing.JLabel btnEditcourse;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearchrecord;
    private javax.swing.JLabel btnViewcourse;
    private javax.swing.JLabel btnViewrecord;
    private javax.swing.JLabel btnViewreport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelAbout;
    private javax.swing.JPanel panelAddfees;
    private javax.swing.JPanel panelEditcourse;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelSeaechrecord;
    private javax.swing.JPanel panelViewcourse;
    private javax.swing.JPanel panelViewrecord;
    private javax.swing.JPanel panelViewreport;
    // End of variables declaration//GEN-END:variables
}
