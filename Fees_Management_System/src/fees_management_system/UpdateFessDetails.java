/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_management_system;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author sumit kumar
 */
public class UpdateFessDetails extends javax.swing.JFrame {

    /**
     * Creates new form AddFees
     */
    public UpdateFessDetails() {
        initComponents();
        displayCashFirst();
        fillComboBox();
        
        int receiptNo=getReceiptNo();
        txt_receiptNo.setText(Integer.toString(receiptNo));
        
        setRecord();
    }

//instent method for displaycashfirst    
    public void displayCashFirst() {
        lbl_DDno.setVisible(false);
        lbl_chequeNo.setVisible(false);
        lbl_bankName.setVisible(false);

        txt_DDNo.setVisible(false);
        txt_ChequeNo.setVisible(false);
        txt_bankName.setVisible(false);

    }

    public boolean validation() {
        if (txt_ReceivedFrom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Name in Received From ");
            return false;
        }
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Date");
            return false;
        }
        if (txt_amount.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount in Number ");
            return false;
        }
        if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("cheque")){
            
            if (txt_ChequeNo.getText().equals("") || txt_amount.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Please Enter Cheque Number ");
            return false;
            }
            if (txt_bankName.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter Bank Name ");
            return false;
            }  
        }
        
        if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("DD")){
            
            if (txt_DDNo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter DD Number ");
            return false;
            }
            if (txt_bankName.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter Bank Name ");
            return false;
            }  
        }
        
        if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("card")){
            
           
            if (txt_bankName.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter Bank Name ");
            return false;
            }  
        }
        
        return true;
    }

    public void fillComboBox(){
          try{
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
              Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Fees_management","root","root");
              PreparedStatement pst = con.prepareStatement("select cname from course");
              ResultSet rs = pst.executeQuery();
              while(rs.next()){
                  combocourse.addItem(rs.getString("cname"));                  
              } 
          }catch(Exception e){
              e.printStackTrace();
          }
      }
    
     public int getReceiptNo(){
         int receiptNo = 0;
         try{
             Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement("select max(reciept_no) from fees_Details");
             ResultSet rs = pst.executeQuery();
             
             if(rs.next()== true){
                 receiptNo=rs.getInt(1);
             }
         }catch(Exception e){
             e.printStackTrace();
         }
         return receiptNo+1;
     }
     
     public String updateData(){
         
         String status = "";
         
         int recieptNo = Integer.parseInt(txt_receiptNo.getText());
         String StudentName = txt_ReceivedFrom.getText();
         String rollNo = txt_rollNo.getText();
         String paymentMode = combo_PaymentMode.getSelectedItem().toString();
         String chequeNo = txt_ChequeNo.getText();
         String bankName = txt_bankName.getText();
         String ddNo = txt_DDNo.getText();
         String courseName = txt_courseName.getText();
         float totalAmount = Float.parseFloat(txt_total.getText());
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String date = dateFormat.format(dateChooser.getDate());
         float initialAmount = Float.parseFloat(txt_amount.getText());
         float cgst = Float.parseFloat(txt_cgst.getText());
         float sgst = Float.parseFloat(txt_sgst.getText());
         String totalInWord = txt_total_in_word.getText();
         String remark =txt_remark.getText();
         int year1 = Integer.parseInt(txt_Year1.getText());
         int year2 = Integer.parseInt(txt_Year2.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("update fees_details set student_Name =?,roll_no =?,payment_mode =?,cheque_no =?,bank_name =?"
                    + ",dd_no =?,course_name =?,total_amount =?,date =?,"
                    + "amount =?,cgst =?,sgst =?,total_in_words =?,remark =?,year1 =?,year2 =? where reciept_no =?");
            
            
            pst.setString(1, StudentName);
            pst.setString(2, rollNo);
            pst.setString(3, paymentMode);
            pst.setString(4, chequeNo);
            pst.setString(5, bankName);
            pst.setString(6, ddNo);
            pst.setString(7, courseName);
            pst.setFloat(8, totalAmount);
            pst.setString(9, date);
            pst.setFloat(10, initialAmount);
            pst.setFloat(11, cgst);
            pst.setFloat(12, sgst);
            pst.setString(13, totalInWord);
            pst.setString(14, remark);
            pst.setInt(15, year1);
            pst.setInt(16, year2);
            pst.setInt(17,recieptNo);
            
            int rowCount = pst.executeUpdate();
            if(rowCount == 1){
                 status = "Success";
            }else{
                status = "Failed";
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
         return status;
     }
     //edit page ke liye code
     public void setRecord(){
         
         
         try{
             Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from fees_details order by reciept_no desc fetch first 1 rows only");
             ResultSet rs = pst.executeQuery();
             rs.next();
             
             txt_receiptNo.setText(rs.getString("reciept_no"));
             combo_PaymentMode.setSelectedItem(rs.getString("payment_mode"));
             txt_ChequeNo.setText(rs.getString("cheque_no"));
             txt_DDNo.setText(rs.getString("dd_no"));
             txt_bankName.setText(rs.getString("bank_name"));
             txt_ReceivedFrom.setText(rs.getString("student_Name"));
             dateChooser.setDate(rs.getDate("date"));
             txt_Year1.setText(rs.getString("year1"));
             txt_Year2.setText(rs.getString("year2"));
             txt_rollNo.setText(rs.getString("roll_no"));
             combocourse.setSelectedItem(rs.getString("course_name"));
             txt_amount.setText(rs.getString("amount"));
             txt_cgst.setText(rs.getString("cgst"));
             txt_sgst.setText(rs.getString("sgst"));
             txt_total.setText(rs.getString("total_amount"));
             txt_total_in_word.setText(rs.getString("total_in_words"));
             txt_remark.setText(rs.getString("remark"));        
         }catch(Exception e){
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

        Fees_managementPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("Fees_managementPU").createEntityManager();
        courseQuery = java.beans.Beans.isDesignTime() ? null : Fees_managementPUEntityManager.createQuery("SELECT c FROM Course c");
        courseList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : courseQuery.getResultList();
        courseQuery1 = java.beans.Beans.isDesignTime() ? null : Fees_managementPUEntityManager.createQuery("SELECT c FROM Course c");
        courseList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : courseQuery1.getResultList();
        courseQuery2 = java.beans.Beans.isDesignTime() ? null : Fees_managementPUEntityManager.createQuery("SELECT c FROM Course c");
        courseList2 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : courseQuery2.getResultList();
        panelsidebar = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelSearchrecord = new javax.swing.JPanel();
        btnSearchrecord = new javax.swing.JLabel();
        panelEditcourse = new javax.swing.JPanel();
        btnEditcourse = new javax.swing.JLabel();
        panelCourselist = new javax.swing.JPanel();
        btnCourselist = new javax.swing.JLabel();
        panelViewallrecord = new javax.swing.JPanel();
        btnViewallrecord = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        panelParent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_DDno = new javax.swing.JLabel();
        lbl_chequeNo = new javax.swing.JLabel();
        lbl_bankName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        txt_ChequeNo = new javax.swing.JTextField();
        txt_DDNo = new javax.swing.JTextField();
        txt_bankName = new javax.swing.JTextField();
        combo_PaymentMode = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        panelChild = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_Year1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_Year2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        combocourse = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_courseName = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        txt_sgst = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_total_in_word = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_ReceivedFrom = new javax.swing.JTextField();
        txt_rollNo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsidebar.setBackground(new java.awt.Color(204, 204, 255));
        panelsidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHome.setBackground(new java.awt.Color(204, 204, 255));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), java.awt.Color.black, java.awt.Color.darkGray, null));

        btnHome.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/home.png"))); // NOI18N
        btnHome.setText("       Home");
        btnHome.setIconTextGap(5);
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        panelsidebar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 360, 70));

        panelSearchrecord.setBackground(new java.awt.Color(204, 204, 255));
        panelSearchrecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), java.awt.Color.black, java.awt.Color.darkGray, null));

        btnSearchrecord.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnSearchrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/search-user.png"))); // NOI18N
        btnSearchrecord.setText("       Search Record");
        btnSearchrecord.setIconTextGap(5);
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

        javax.swing.GroupLayout panelSearchrecordLayout = new javax.swing.GroupLayout(panelSearchrecord);
        panelSearchrecord.setLayout(panelSearchrecordLayout);
        panelSearchrecordLayout.setHorizontalGroup(
            panelSearchrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchrecordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSearchrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelSearchrecordLayout.setVerticalGroup(
            panelSearchrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearchrecord, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        panelsidebar.add(panelSearchrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 360, 70));

        panelEditcourse.setBackground(new java.awt.Color(204, 204, 255));
        panelEditcourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), java.awt.Color.black, java.awt.Color.darkGray, null));

        btnEditcourse.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnEditcourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/edit2.png"))); // NOI18N
        btnEditcourse.setText("   Edit Course");
        btnEditcourse.setIconTextGap(5);
        btnEditcourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditcourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditcourseMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelEditcourseLayout = new javax.swing.GroupLayout(panelEditcourse);
        panelEditcourse.setLayout(panelEditcourseLayout);
        panelEditcourseLayout.setHorizontalGroup(
            panelEditcourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditcourseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditcourse, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelEditcourseLayout.setVerticalGroup(
            panelEditcourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditcourse, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        panelsidebar.add(panelEditcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 360, 70));

        panelCourselist.setBackground(new java.awt.Color(204, 204, 255));
        panelCourselist.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), java.awt.Color.black, java.awt.Color.darkGray, null));

        btnCourselist.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnCourselist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/list.png"))); // NOI18N
        btnCourselist.setText("       Course List");
        btnCourselist.setIconTextGap(5);
        btnCourselist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCourselistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCourselistMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelCourselistLayout = new javax.swing.GroupLayout(panelCourselist);
        panelCourselist.setLayout(panelCourselistLayout);
        panelCourselistLayout.setHorizontalGroup(
            panelCourselistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCourselistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCourselist, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCourselistLayout.setVerticalGroup(
            panelCourselistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCourselistLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCourselist, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelsidebar.add(panelCourselist, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, 360, 70));

        panelViewallrecord.setBackground(new java.awt.Color(204, 204, 255));
        panelViewallrecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), java.awt.Color.black, java.awt.Color.darkGray, null));

        btnViewallrecord.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        btnViewallrecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/view report2.png"))); // NOI18N
        btnViewallrecord.setText("View All Record");
        btnViewallrecord.setIconTextGap(5);
        btnViewallrecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewallrecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewallrecordMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelViewallrecordLayout = new javax.swing.GroupLayout(panelViewallrecord);
        panelViewallrecord.setLayout(panelViewallrecordLayout);
        panelViewallrecordLayout.setHorizontalGroup(
            panelViewallrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewallrecordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnViewallrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelViewallrecordLayout.setVerticalGroup(
            panelViewallrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewallrecordLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnViewallrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelsidebar.add(panelViewallrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 650, 360, 70));

        panelBack.setBackground(new java.awt.Color(204, 204, 255));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), java.awt.Color.black, java.awt.Color.darkGray, null));

        btnBack.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/back-button.png"))); // NOI18N
        btnBack.setText("       Back");
        btnBack.setIconTextGap(5);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelBackLayout = new javax.swing.GroupLayout(panelBack);
        panelBack.setLayout(panelBackLayout);
        panelBackLayout.setHorizontalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );
        panelBackLayout.setVerticalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        panelsidebar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 770, 360, 70));

        panelLogout.setBackground(new java.awt.Color(204, 204, 255));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), java.awt.Color.black, java.awt.Color.darkGray, null));

        btnLogout.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/logout.png"))); // NOI18N
        btnLogout.setText("       Logout");
        btnLogout.setIconTextGap(5);
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelLogoutLayout = new javax.swing.GroupLayout(panelLogout);
        panelLogout.setLayout(panelLogoutLayout);
        panelLogoutLayout.setHorizontalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoutLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelLogoutLayout.setVerticalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        panelsidebar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 900, 360, 70));

        getContentPane().add(panelsidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 1100));

        panelParent.setBackground(new java.awt.Color(102, 153, 255));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Receipt No :");
        panelParent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 52, -1, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Mode of Payment : ");
        panelParent.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        lbl_DDno.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_DDno.setText("DD No:");
        panelParent.add(lbl_DDno, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        lbl_chequeNo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_chequeNo.setText("Cheque No:");
        panelParent.add(lbl_chequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        lbl_bankName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_bankName.setText("Banke Name :");
        panelParent.add(lbl_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 232, -1, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Date :");
        panelParent.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 60, 30));

        txt_receiptNo.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        panelParent.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 48, 60, 40));

        txt_ChequeNo.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        panelParent.add(txt_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 190, 40));

        txt_DDNo.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        panelParent.add(txt_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 158, 190, 40));

        txt_bankName.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        panelParent.add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 260, 40));

        combo_PaymentMode.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combo_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card", " " }));
        combo_PaymentMode.setSelectedIndex(2);
        combo_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_PaymentModeActionPerformed(evt);
            }
        });
        panelParent.add(combo_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 200, 40));

        dateChooser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        panelParent.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 170, 30));

        panelChild.setBackground(new java.awt.Color(102, 153, 255));
        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Received From :");
        panelChild.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 140, 30));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("The following paymet in the college offlice for the year :");
        panelChild.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 72, -1, 30));

        txt_Year1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Year1ActionPerformed(evt);
            }
        });
        panelChild.add(txt_Year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 70, 40));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("to");
        panelChild.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, -1, 30));

        txt_Year2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        panelChild.add(txt_Year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 70, 40));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Roll No :");
        panelChild.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 90, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Course");
        panelChild.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, 30));

        combocourse.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combocourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocourseActionPerformed(evt);
            }
        });
        panelChild.add(combocourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 260, 40));
        panelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 1160, 10));
        panelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 1160, 40));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Sr. No");
        panelChild.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Heads");
        panelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 90, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Amount (Rs)");
        panelChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 190, -1, -1));

        txt_courseName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_courseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courseNameActionPerformed(evt);
            }
        });
        panelChild.add(txt_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 480, 40));

        txt_amount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 280, 180, 30));

        txt_cgst.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cgstActionPerformed(evt);
            }
        });
        panelChild.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 330, 180, 30));

        txt_sgst.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        panelChild.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 380, 180, 30));

        txt_total.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        panelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 450, 180, 30));
        panelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 430, 270, 10));

        txt_total_in_word.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_total_in_word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_in_wordActionPerformed(evt);
            }
        });
        panelChild.add(txt_total_in_word, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, 470, 40));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Total in word :");
        panelChild.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 130, 30));
        panelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 520, 320, 10));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("Remark:");
        panelChild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, 30));

        txt_remark.setColumns(20);
        txt_remark.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txt_remark.setRows(5);
        jScrollPane1.setViewportView(txt_remark);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 560, 470, 60));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Receiver Signature");
        panelChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 530, -1, -1));

        btn_print.setBackground(new java.awt.Color(204, 204, 255));
        btn_print.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        panelChild.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 580, 90, 50));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("CGST 9%");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 330, -1, -1));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("SGST 9%");
        panelChild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, -1, -1));

        txt_ReceivedFrom.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        panelChild.add(txt_ReceivedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 310, 40));

        txt_rollNo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        panelChild.add(txt_rollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 12, 50, 40));

        panelParent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 1470, 800));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 1460, 1100));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(255, 153, 255);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        // TODO add your handling code here:
        Color clr = new Color(204, 204, 255);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnSearchrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchrecordMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(255, 153, 255);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchrecordMouseEntered

    private void btnSearchrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchrecordMouseExited
        // TODO add your handling code here:
        Color clr = new Color(204, 204, 255);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchrecordMouseExited

    private void btnEditcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditcourseMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(255, 153, 255);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_btnEditcourseMouseEntered

    private void btnEditcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditcourseMouseExited
        // TODO add your handling code here:
        Color clr = new Color(204, 204, 255);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_btnEditcourseMouseExited

    private void btnCourselistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourselistMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(255, 153, 255);
        panelCourselist.setBackground(clr);
    }//GEN-LAST:event_btnCourselistMouseEntered

    private void btnCourselistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourselistMouseExited
        // TODO add your handling code here:
        Color clr = new Color(204, 204, 255);
        panelCourselist.setBackground(clr);
    }//GEN-LAST:event_btnCourselistMouseExited

    private void btnViewallrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewallrecordMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(255, 153, 255);
        panelViewallrecord.setBackground(clr);
    }//GEN-LAST:event_btnViewallrecordMouseEntered

    private void btnViewallrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewallrecordMouseExited
        // TODO add your handling code here:
        Color clr = new Color(204, 204, 255);
        panelViewallrecord.setBackground(clr);
    }//GEN-LAST:event_btnViewallrecordMouseExited

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(255, 153, 255);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        // TODO add your handling code here:
        Color clr = new Color(204, 204, 255);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseExited

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(255, 153, 255);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        // TODO add your handling code here:
        Color clr = new Color(204, 204, 255);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_btnLogoutMouseExited

    private void txt_Year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Year1ActionPerformed

    private void txt_courseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_courseNameActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        if (validation() == true) {
            
            String result = updateData();
            
            if(result.equals("Success")){
                JOptionPane.showMessageDialog(this,"Record Update Sucessfully");
                PrintReciept p = new  PrintReciept();
                p.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"Record Updation Failed");
            }
        }

    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        
        Float amnt = Float.parseFloat(txt_amount.getText());
        
        Float cgst = (float)(amnt * 0.09);
        Float sgst = (float)(amnt * 0.09);
        
        txt_cgst.setText(cgst.toString());
        txt_sgst.setText(sgst.toString());
        
        float total = amnt+cgst+sgst;
        txt_total.setText(Float.toString(total));
        
        txt_total_in_word.setText(NumberToWordsConverter.convert((int)total));
        
    }//GEN-LAST:event_txt_amountActionPerformed

    private void txt_total_in_wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_in_wordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_in_wordActionPerformed

    private void txt_cgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cgstActionPerformed

    private void combo_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_PaymentModeActionPerformed
// For Zero Index
        if (combo_PaymentMode.getSelectedIndex() == 0) {
            lbl_DDno.setVisible(true);
            txt_DDNo.setVisible(true);
            lbl_chequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
// For first Index
        if (combo_PaymentMode.getSelectedIndex() == 1) {
            lbl_DDno.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_chequeNo.setVisible(true);
            txt_ChequeNo.setVisible(true);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
// For second Index
        if (combo_PaymentMode.getSelectedIndex() == 2) {
            lbl_DDno.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_chequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(false);
            txt_bankName.setVisible(false);
        }
//For third Index
        if (combo_PaymentMode.getSelectedIndex() == 3) {
            lbl_DDno.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_chequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
    }//GEN-LAST:event_combo_PaymentModeActionPerformed

    private void combocourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocourseActionPerformed
        
        txt_courseName.setText(combocourse.getSelectedItem().toString());
        
    }//GEN-LAST:event_combocourseActionPerformed

    private void btnSearchrecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchrecordMouseClicked
        SearchRecord search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchrecordMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        home homepage = new home();
        homepage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        home homepage = new home();
        homepage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

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
            java.util.logging.Logger.getLogger(UpdateFessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateFessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateFessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateFessDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateFessDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager Fees_managementPUEntityManager;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCourselist;
    private javax.swing.JLabel btnEditcourse;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearchrecord;
    private javax.swing.JLabel btnViewallrecord;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> combo_PaymentMode;
    private javax.swing.JComboBox<String> combocourse;
    private java.util.List<fees_management_system.Course> courseList;
    private java.util.List<fees_management_system.Course> courseList1;
    private java.util.List<fees_management_system.Course> courseList2;
    private javax.persistence.Query courseQuery;
    private javax.persistence.Query courseQuery1;
    private javax.persistence.Query courseQuery2;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_DDno;
    private javax.swing.JLabel lbl_bankName;
    private javax.swing.JLabel lbl_chequeNo;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelCourselist;
    private javax.swing.JPanel panelEditcourse;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelSearchrecord;
    private javax.swing.JPanel panelViewallrecord;
    private javax.swing.JPanel panelsidebar;
    private javax.swing.JTextField txt_ChequeNo;
    private javax.swing.JTextField txt_DDNo;
    private javax.swing.JTextField txt_ReceivedFrom;
    private javax.swing.JTextField txt_Year1;
    private javax.swing.JTextField txt_Year2;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bankName;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_courseName;
    private javax.swing.JTextField txt_receiptNo;
    private javax.swing.JTextArea txt_remark;
    private javax.swing.JTextField txt_rollNo;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_in_word;
    // End of variables declaration//GEN-END:variables
}
