/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fees_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AAYUSH
 */
public class addfees extends javax.swing.JFrame {

    /**
     * Creates new form addfees
     */
    public void displaycash(){
        lbl_cheque.setVisible(false);
        lbl_dd.setVisible(false);
        cheque.setVisible(false);
        dd1.setVisible(false);
        lbl_bank.setVisible(false);
        bank.setVisible(false);
        lbl_upi.setVisible(false);
        upi.setVisible(false);
        
    }
    
    public String insertdata(){
        int a=Integer.parseInt(reciept.getText());
        String b=name.getText();
        int c=Integer.parseInt(enrollno.getText());
        String d=mop.getSelectedItem().toString();
        String e=cheque.getText();
        String f=bank.getText();
        String g=dd1.getText();
        String h=course.getSelectedItem().toString();
        String i=gst.getText();
        float j=Float.parseFloat(total.getText());
        float k=Float.parseFloat(amount.getText());
        float l=Float.parseFloat(cgst.getText());
        float m=Float.parseFloat(sgst.getText());
        String n=text.getText();
        String status="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/feesm?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con=DriverManager.getConnection(url,"root","aayush07");
            String sql="insert into fees_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement st=con.prepareStatement(sql);
                st.setInt(1, a);
                st.setString(2, b);
                st.setInt(3, c);
                st.setString(4, d);
                st.setString(5, e);
                st.setString(6, f);
                st.setString(7, g);
                st.setString(8, h);
                st.setString(9, i);
                st.setFloat(10, j);
                st.setFloat(11, k);
                st.setFloat(12, l);
                st.setFloat(13, m);
                st.setString(14, n);
                int o=st.executeUpdate();
                if (o==1){
                    status="success";
                }
                else{
                    status="failed";
                    
                }
                
                
                
        }
        catch(Exception e1){
           e1.printStackTrace();
        }
    return status;
    }
    
    
    public addfees() {
        initComponents();
        displaycash();
        fill();
        //int r=getr();
        //r++;
        //reciept.setText(Integer.toString(r));
    }
    
    public class NumberToWordsConverter {

	public static final String[] units = { "", "One", "Two", "Three", "Four",
			"Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
			"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen" };

	public static final String[] tens = { 
			"", 		// 0
			"",		// 1
			"Twenty", 	// 2
			"Thirty", 	// 3
			"Forty", 	// 4
			"Fifty", 	// 5
			"Sixty", 	// 6
			"Seventy",	// 7
			"Eighty", 	// 8
			"Ninety" 	// 9
	};

	public static String convert(final int n) {
		if (n < 0) 
                {
			return "Minus " + convert(-n);
		}

		if (n < 20) 
                {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}

	public static void main(final String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Amount : ");
		int n=sc.nextInt();

		
		System.out.println( convert(n)+ " Only");

	
	}
}
    
    
    
    
    
    
    boolean validation(){
       
        if (name.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter reciever name");
            return false;
        }
        if (reciept.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter reciept number");
            return false;
        }
        if (enrollno.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter enrollment number");
            return false;
        
        }
        if (amount.getText().equals("") || amount.getText().matches("[0-9]+")==false){
            JOptionPane.showMessageDialog(this, "please enter amount(number)");
            return false;
        
        }
        if(mop.getSelectedItem().toString().equals("cheque")){
            if (cheque.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter cheque number");
            return false;
            }
            if (bank.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter bank number");
            return false;
            }
        } 
        if(mop.getSelectedItem().toString().equals("DD")){
            if (dd1.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter DD number");
            return false;
            }
            if (bank.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter bank number");
            return false;
            }
        }
        if(mop.getSelectedItem().toString().equals("BHIM UPI")){
            if (upi.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please enter UPI ID");
            return false;
            }
        }
        return true;
        
    }
               
    public void fill(){
        int id=0;
        ResultSet rs=null;
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/feesm?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Connection con=DriverManager.getConnection(url,"root","aayush07");
            String sql="select cname from course";
            Statement st=con.createStatement();
            rs=st.executeQuery(sql);
            while (rs.next()){
                course.addItem(rs.getString("cname"));
            }
            coursename.setText((String) course.getSelectedItem());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }   
    
    ///public int getr(){
       // ResultSet rs=null;
       // int rno=0;
        //try{
        //   Class.forName("com.mysql.cj.jdbc.Driver");
          //  String url="jdbc:mysql://localhost:3306/feesm?zeroDateTimeBehavior=CONVERT_TO_NULL";
            //Connection con=DriverManager.getConnection(url,"root","aayush07");
       //     String sql="select max(reciept) from fees_details";
         //   Statement st=con.createStatement();
           // rs=st.executeQuery(sql);
      //      if (rs.next()==true){
        //        rs.getInt("reciept");
          //      rno=rs.getInt(2);
            //    rno++;
              //  reciept.setText(Integer.toString(rno));
      //      }
        //}
      //  catch(Exception e){
        //    e.printStackTrace();
   //     }
     //   return rno;
   // }
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        gst = new javax.swing.JLabel();
        reciept = new javax.swing.JTextField();
        lbl_dd = new javax.swing.JLabel();
        lbl_cheque = new javax.swing.JLabel();
        lbl_receipt = new javax.swing.JLabel();
        cheque = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbl_enroll = new javax.swing.JLabel();
        enrollno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        course = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_course = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        text = new javax.swing.JTextField();
        cgst = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        sgst = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        coursename = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        lbl_mop = new javax.swing.JLabel();
        mop = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        dd1 = new javax.swing.JTextField();
        lbl_upi = new javax.swing.JLabel();
        upi = new javax.swing.JTextField();
        lbl_name = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        bank = new javax.swing.JTextField();
        lbl_bank = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jButton4.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton4.setText("SEARCH RECORD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton6.setText("VIEW RECORD");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton8.setText("EDIT COURSE");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton9.setText("VIEW COURSE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton5.setText("BACK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jButton10.setText("LOG OUT");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 570));

        gst.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        gst.setText("AVXX2198XXOR");
        jPanel1.add(gst, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));
        jPanel1.add(reciept, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 100, -1));

        lbl_dd.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_dd.setText("DD Number");
        jPanel1.add(lbl_dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 120, 30));

        lbl_cheque.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_cheque.setText("Cheque Number");
        jPanel1.add(lbl_cheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 130, 30));

        lbl_receipt.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_receipt.setText("Reciept Number");
        jPanel1.add(lbl_receipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 120, 30));

        cheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chequeActionPerformed(evt);
            }
        });
        jPanel1.add(cheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 100, -1));

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_enroll.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_enroll.setText("Enrollment No.");
        jPanel3.add(lbl_enroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 120, 30));
        jPanel3.add(enrollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 160, -1));

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel1.setText("CGST 7%");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 90, 30));

        jPanel3.add(course, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(204, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(204, 255, 255));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 54, 568, 20));

        lbl_course.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_course.setText("Course");
        jPanel3.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 140, 30));

        jLabel12.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel12.setText("Amount");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 90, 30));

        jLabel13.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel13.setText("Serial No.");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, 30));
        jPanel3.add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 240, 30));
        jPanel3.add(cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 120, 30));
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 120, 30));

        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });
        jPanel3.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 120, 30));

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel3.setText("Total in Words");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, 30));

        jLabel6.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel6.setText("Head");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 90, 30));

        jSeparator3.setBackground(new java.awt.Color(204, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(204, 255, 255));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 574, 30));

        jSeparator4.setBackground(new java.awt.Color(204, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(204, 255, 255));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 140, -1));

        sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sgstActionPerformed(evt);
            }
        });
        jPanel3.add(sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 120, 30));

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel14.setText("SGST 7%");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 90, 30));
        jPanel3.add(coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 190, 30));

        jLabel15.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel15.setText("Reciever Signature");
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 140, 30));

        jLabel16.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jLabel16.setText("Total");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 90, 30));

        jSeparator5.setBackground(new java.awt.Color(204, 255, 255));
        jSeparator5.setForeground(new java.awt.Color(204, 255, 255));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 150, 20));

        jButton1.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 70, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 570, 380));

        lbl_mop.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_mop.setText("Mode of Payment");
        jPanel1.add(lbl_mop, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 140, 30));

        mop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "CASH", "BHIM UPI", "CHEQUE", " " }));
        mop.setSelectedIndex(1);
        mop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mopActionPerformed(evt);
            }
        });
        jPanel1.add(mop, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("GSTIN");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, -1, -1));
        jPanel1.add(dd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 100, -1));

        lbl_upi.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_upi.setText("UPI ID");
        jPanel1.add(lbl_upi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 120, 30));
        jPanel1.add(upi, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 100, -1));

        lbl_name.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_name.setText("Reciever Name");
        jPanel1.add(lbl_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 120, 30));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 100, -1));
        jPanel1.add(bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 100, -1));

        lbl_bank.setFont(new java.awt.Font("Palatino Linotype", 0, 16)); // NOI18N
        lbl_bank.setText("Bank Name");
        jPanel1.add(lbl_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        editcourse b=new editcourse();
        b.show();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        viewcourse a4=new viewcourse();
        a4.show();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void mopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mopActionPerformed
        if(mop.getSelectedIndex()==0){
            lbl_dd.setVisible(true);
            dd1.setVisible(true);
            lbl_bank.setVisible(true);
            bank.setVisible(true);
            lbl_cheque.setVisible(false);
            cheque.setVisible(false);
            lbl_upi.setVisible(false);
            upi.setVisible(false);
        }
        if(mop.getSelectedIndex()==1){
            lbl_cheque.setVisible(false);
            lbl_dd.setVisible(false);
            cheque.setVisible(false);
            dd1.setVisible(false);
            lbl_bank.setVisible(false);
            bank.setVisible(false);
            lbl_upi.setVisible(false);
            upi.setVisible(false);
        }
        if(mop.getSelectedIndex()==2){
            lbl_cheque.setVisible(false);
            lbl_dd.setVisible(false);
            cheque.setVisible(false);
            dd1.setVisible(false);
            lbl_bank.setVisible(false);
            bank.setVisible(false);
            lbl_upi.setVisible(true);
            upi.setVisible(true);
        }
        if(mop.getSelectedIndex()==3){
            lbl_dd.setVisible(false);
            dd1.setVisible(false);
            lbl_bank.setVisible(true);
            bank.setVisible(true);
            lbl_cheque.setVisible(true);
            cheque.setVisible(true);
            lbl_upi.setVisible(false);
            upi.setVisible(false);
        }
    }//GEN-LAST:event_mopActionPerformed

    
    
    
    boolean calc(){
        String s=amount.getText();
        float amt=Float.parseFloat(s);
        float cg=(float) (amt*0.07);
        float sg=(float) (amt*0.07);
        cgst.setText(Float.toString(cg));
        sgst.setText(Float.toString(sg));
        float t=amt+sg+cg;
        total.setText(Float.toString(t));
        text.setText(NumberToWordsConverter.convert((int)t));
        
        return true;
    }
    private void chequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chequeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validation()==true){
          String s=insertdata();
          if (s.equals("success")){
              JOptionPane.showMessageDialog(this, "Record Sucessfully Inserted");
                }
          else{
              JOptionPane.showMessageDialog(this, "Record Not Inserted");
                }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        calc();
        
    }//GEN-LAST:event_amountActionPerformed

    private void sgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sgstActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        searchfees a2=new searchfees();
        a2.show();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        viewrecord a3=new viewrecord();
        a3.show();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       login l1=new login();
     l1.show();
     this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addfees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JTextField bank;
    private javax.swing.JTextField cgst;
    private javax.swing.JTextField cheque;
    private javax.swing.JComboBox<String> course;
    private javax.swing.JTextField coursename;
    private javax.swing.JTextField dd1;
    private javax.swing.JTextField enrollno;
    private javax.swing.JLabel gst;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lbl_bank;
    private javax.swing.JLabel lbl_cheque;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_dd;
    private javax.swing.JLabel lbl_enroll;
    private javax.swing.JLabel lbl_mop;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_receipt;
    private javax.swing.JLabel lbl_upi;
    private javax.swing.JComboBox<String> mop;
    private javax.swing.JTextField name;
    private javax.swing.JTextField reciept;
    private javax.swing.JTextField sgst;
    private javax.swing.JTextField text;
    private javax.swing.JTextField total;
    private javax.swing.JTextField upi;
    // End of variables declaration//GEN-END:variables
}

