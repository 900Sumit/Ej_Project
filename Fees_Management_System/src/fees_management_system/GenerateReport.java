/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_management_system;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author sumit kumar
 */
public class GenerateReport extends javax.swing.JFrame {

    /**
     * Creates new form GenerateReport
     */
    DefaultTableModel model;
    public GenerateReport() {
        initComponents();
        fillComboBox();
        
    }
    
    public void fillComboBox(){
          try{
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
              Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Fees_management","root","root");
              PreparedStatement pst = con.prepareStatement("select cname from course");
              ResultSet rs = pst.executeQuery();
              while(rs.next()){
                  combo_courseDetails.addItem(rs.getString("cname"));                  
              } 
          }catch(Exception e){
              e.printStackTrace();
          }
      }
    
    public void setRecordTotable(){
        String cname = combo_courseDetails.getSelectedItem().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String fromDate = dateFormat.format(dateChooser_from.getDate());
        String toDate = dateFormat.format(dateChooser_to.getDate());
        
        Float amountTotal = 0.0f;
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from fees_details where date between ? and ? and course_name =? ");
            
            pst.setString(1, fromDate);
            pst.setString(2, toDate);
            pst.setString(3, cname);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                String RecieptNo = rs.getString("reciept_no");
                String RollNo = rs.getString("roll_no");
                String StudentName = rs.getString("student_name");
                String Course = rs.getString("course_name");
                float Amount = rs.getFloat("total_amount");
                String Remark = rs.getString("remark");
                
                amountTotal = amountTotal + Amount;
                
                Object[] obj={RecieptNo,RollNo,StudentName,Course,Amount,Remark};
                model = (DefaultTableModel)tbl_feesDetails.getModel();
                model.addRow(obj);
            }
            lbl_course.setText(cname);
            lbl_totalAmount.setText(amountTotal.toString());
            lbl_totalInWords.setText(NumberToWordsConverter.convert(amountTotal.intValue()));
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel)tbl_feesDetails.getModel();
        model.setRowCount(1);
    }
    public void exportToExcel(){
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        DefaultTableModel model = (DefaultTableModel)tbl_feesDetails.getModel();
        
        TreeMap<String, Object[]>map = new TreeMap<>();
        map.put("0", new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),
        model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
        
        for(int i = 1; i<model.getRowCount(); i++){
            map.put(Integer.toString(i), new Object[]{model.getValueAt(i,0),model.getValueAt(i,1),model.getValueAt(i,2),model.getValueAt(i,3),
                model.getValueAt(i,4),model.getValueAt(i,5)});
        }
        
        Set<String> id = map.keySet();
        XSSFRow fRow;
        int rowId = 0;
        for(String key : id){
            fRow = ws.createRow(rowId++);
            
            Object [] value = map.get(key);
            
            int cellId = 0;
            for(Object object : value){
                XSSFCell cell =fRow.createCell(cellId++);
                cell.setCellValue(object.toString());
            }
        }
        
        try(FileOutputStream fos = new FileOutputStream(new File(txt_filePath.getText()));){
            
            wb.write(fos);
            JOptionPane.showMessageDialog(this, "file exported Successfully : "+txt_filePath.getText());
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combo_courseDetails = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateChooser_from = new com.toedter.calendar.JDateChooser();
        dateChooser_to = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_filePath = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_feesDetails = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_totalAmount = new javax.swing.JLabel();
        lbl_totalInWords = new javax.swing.JLabel();

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
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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

        javax.swing.GroupLayout panelEditcourseLayout = new javax.swing.GroupLayout(panelEditcourse);
        panelEditcourse.setLayout(panelEditcourseLayout);
        panelEditcourseLayout.setHorizontalGroup(
            panelEditcourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditcourse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewallrecordMouseClicked(evt);
            }
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
            .addGroup(panelBackLayout.createSequentialGroup()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
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

        getContentPane().add(panelsidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 1050));

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Select Course : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 52, 120, 40));

        combo_courseDetails.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(combo_courseDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 330, 40));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Select Date:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 100, 50));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("From Date:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 90, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("To Date:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 70, 40));
        jPanel1.add(dateChooser_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 160, 40));
        jPanel1.add(dateChooser_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 160, 40));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 110, 50));

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 110, 50));

        txt_filePath.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txt_filePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 420, 40));

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setText("Browser");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 110, 50));

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton4.setText("Export to Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 150, 50));

        tbl_feesDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Reciept No", "Roll No", "Student name", "Course", "Amount", "Remark"
            }
        ));
        jScrollPane1.setViewportView(tbl_feesDetails);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 1130, 560));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Course Selected:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Total Amount Collected:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 82, -1, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Total Amount In Words:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 132, -1, 30));

        lbl_course.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel2.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 280, 40));

        lbl_totalAmount.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel2.add(lbl_totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 250, 40));

        lbl_totalInWords.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel2.add(lbl_totalInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 490, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 530, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 1270, 1050));

        setSize(new java.awt.Dimension(1890, 1097));
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

    private void btnEditcourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditcourseMouseClicked
        EditCourse edit = new EditCourse();
        edit.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditcourseMouseClicked

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

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        home homepage = new home();
        homepage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearTable();
        setRecordTotable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-dd"); 
        String datefrom=  Date_Format.format(dateChooser_from.getDate());
      String dateto=  Date_Format.format(dateChooser_to.getDate());
       
        MessageFormat header=new MessageFormat("Report From "+datefrom+" To " +dateto);
        MessageFormat footer=new MessageFormat("page{0,number,integer}");
        try {
            tbl_feesDetails.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        } catch (Exception e) {
            e.getMessage();
        } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        SimpleDateFormat dateFormat =new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(new Date());
        
        
        try{
            File f = fileChooser.getSelectedFile();
            String path =f.getAbsolutePath();
            path = path+"_"+date+".xlsx";
            txt_filePath.setText(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        exportToExcel();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        home homepage = new home();
        homepage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnSearchrecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchrecordMouseClicked
        SearchRecord search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchrecordMouseClicked

    private void btnViewallrecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewallrecordMouseClicked
        ViewAllRecords view = new ViewAllRecords();
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewallrecordMouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutMouseClicked
     
   
    
    
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
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCourselist;
    private javax.swing.JLabel btnEditcourse;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSearchrecord;
    private javax.swing.JLabel btnViewallrecord;
    private javax.swing.JComboBox<String> combo_courseDetails;
    private com.toedter.calendar.JDateChooser dateChooser_from;
    private com.toedter.calendar.JDateChooser dateChooser_to;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_totalAmount;
    private javax.swing.JLabel lbl_totalInWords;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelCourselist;
    private javax.swing.JPanel panelEditcourse;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelSearchrecord;
    private javax.swing.JPanel panelViewallrecord;
    private javax.swing.JPanel panelsidebar;
    private javax.swing.JTable tbl_feesDetails;
    private javax.swing.JTextField txt_filePath;
    // End of variables declaration//GEN-END:variables
}
