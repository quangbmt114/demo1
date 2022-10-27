/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author quang nguyen
 */
public class DangNhap extends javax.swing.JFrame {
    File file = new File(System.getProperty("user.home")+"/Desktop/save.txt");
    
    /**
     * Creates new form NewJFrame
     */
     Random ram = new Random();
    int Code = ram.nextInt(999999);
    public DangNhap() {
        initComponents();
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/images/blue-home-icon.png");
        setIconImage(icon.getImage());
        UPDATE();
        panelForm.add(panelLogin,"login");
        panelForm.add(panelForgot, "forgot");
        panelForm.add(panelChangePassword,"changed");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForm = new javax.swing.JPanel();
        panelLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        disable = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        remember = new javax.swing.JCheckBox();
        btnDangNhap = new javax.swing.JButton();
        labelForgot = new javax.swing.JLabel();
        panelForgot = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSendEmail = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCodeEmail = new javax.swing.JTextField();
        btnVeriftyCode = new javax.swing.JButton();
        btnBackLogin = new javax.swing.JButton();
        txthour = new javax.swing.JLabel();
        panelChangePassword = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JTextField();
        txtVeritfyPassword = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelForm.setLayout(new java.awt.CardLayout());

        panelLogin.setBackground(new java.awt.Color(255, 255, 255));
        panelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile-128px.png"))); // NOI18N
        panelLogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 140));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ĐĂNG NHẬP");
        panelLogin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 26, 186, 28));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tài khoản");
        panelLogin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 72, 232, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mật khẩu");
        panelLogin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 232, -1));
        panelLogin.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 94, 232, -1));
        panelLogin.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 155, 232, -1));

        disable.setForeground(new java.awt.Color(51, 51, 51));
        disable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-eye.png"))); // NOI18N
        disable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        disable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });
        panelLogin.add(disable, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 34, 40));

        show.setForeground(new java.awt.Color(51, 51, 51));
        show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-open-eyes.png"))); // NOI18N
        show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        show.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        panelLogin.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 34, 40));

        remember.setText("Ghi nhớ mật khẩu");
        panelLogin.add(remember, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 232, -1));

        btnDangNhap.setBackground(new java.awt.Color(51, 204, 0));
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("LOGIN");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        panelLogin.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 230, 34));

        labelForgot.setText("Forgot Username/ Password ?");
        labelForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelForgotMouseClicked(evt);
            }
        });
        panelLogin.add(labelForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 276, 170, 20));

        panelForm.add(panelLogin, "card2");

        panelForgot.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Enter-Email");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("FORGOT USERNAME/PASSWORD");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/security-128px.png"))); // NOI18N

        btnSendEmail.setText("Send");
        btnSendEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendEmailActionPerformed(evt);
            }
        });

        jLabel9.setText("Verify-Code");

        btnVeriftyCode.setText("Verify Code");
        btnVeriftyCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVeriftyCodeActionPerformed(evt);
            }
        });

        btnBackLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        btnBackLogin.setText("Back");
        btnBackLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackLoginActionPerformed(evt);
            }
        });

        txthour.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelForgotLayout = new javax.swing.GroupLayout(panelForgot);
        panelForgot.setLayout(panelForgotLayout);
        panelForgotLayout.setHorizontalGroup(
            panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelForgotLayout.createSequentialGroup()
                .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelForgotLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
                    .addGroup(panelForgotLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBackLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelForgotLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelForgotLayout.createSequentialGroup()
                                        .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel9))
                                        .addGap(49, 49, 49)
                                        .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                            .addComponent(txtCodeEmail)))
                                    .addComponent(btnVeriftyCode)
                                    .addGroup(panelForgotLayout.createSequentialGroup()
                                        .addComponent(txthour, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSendEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelForgotLayout.setVerticalGroup(
            panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelForgotLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelForgotLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSendEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txthour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(panelForgotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVeriftyCode))
                    .addGroup(panelForgotLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBackLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 66, Short.MAX_VALUE))
        );

        panelForm.add(panelForgot, "card3");

        panelChangePassword.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/security-128px.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("RESET PASSWORD");

        jLabel11.setText("New Passowrd");

        jLabel12.setText("Veritfy Password");

        jButton4.setText("RESET PASSWORD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChangePasswordLayout = new javax.swing.GroupLayout(panelChangePassword);
        panelChangePassword.setLayout(panelChangePasswordLayout);
        panelChangePasswordLayout.setHorizontalGroup(
            panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChangePasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelChangePasswordLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelChangePasswordLayout.createSequentialGroup()
                        .addGroup(panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVeritfyPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        panelChangePasswordLayout.setVerticalGroup(
            panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChangePasswordLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChangePasswordLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(panelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtVeritfyPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChangePasswordLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelForm.add(panelChangePassword, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        // TODO add your handling code here:
        txtpassword.setEchoChar((char)0);
        disable.setVisible(false);
        disable.setEnabled(false);
        show.setVisible(true);
        show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        // TODO add your handling code here:
        txtpassword.setEchoChar('*');
        disable.setVisible(true);
        disable.setEnabled(true);
        show.setVisible(false);
        show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        String password = String.valueOf(txtpassword.getPassword()) ;
        if(remember.isSelected()){
                  SAVE(); //Save This UserName and his PassWord     
}
        System.out.println(txtpassword.getPassword());
        if(txtusername.getText().equals("admin")&&password.equals("admin")){
            System.out.println("oke");
            this.setVisible(false);
            new TrangChu().setVisible(true);
        }
        
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void labelForgotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelForgotMouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelForm.getLayout();
        layout.show(panelForm, "forgot");
    }//GEN-LAST:event_labelForgotMouseClicked

    private void btnBackLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackLoginActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelForm.getLayout();
        layout.show(panelForm, "login");
    }//GEN-LAST:event_btnBackLoginActionPerformed

    private void btnVeriftyCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVeriftyCodeActionPerformed
        // TODO add your handling code here:
        if(txtCodeEmail.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Ô nhập không được để trống !!");
        }else if(txtCodeEmail.getText().equals(String.valueOf(Code))){
            CardLayout layout = (CardLayout) panelForm.getLayout();
            layout.show(panelForm, "changed");
        }else{
            JOptionPane.showMessageDialog(this, "Mã Code không hợp lệ, vui lòng nhập lại !!");
        }
        
    }//GEN-LAST:event_btnVeriftyCodeActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) panelForm.getLayout();
        layout.show(panelForm, "login");
        
    }//GEN-LAST:event_jButton4ActionPerformed
   
    private void btnSendEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendEmailActionPerformed
       
        String  Email = txtEmail.getText();
        if (Email.equals("")) {
            JOptionPane.showMessageDialog(this, "Ô nhập không được để trống !!");
        }else{
            String checkEmail = "\\w+@\\w+\\.\\w+";
        if(!Email.matches(checkEmail)){
            JOptionPane.showMessageDialog(this, "Không đúng định dạng email");
        }else{
            SendVeriftyCode(Email);
            txtCodeEmail.setText("");
        }
        //123123
        }
        
        
    }//GEN-LAST:event_btnSendEmailActionPerformed
    
    public void SendVeriftyCode(String x) throws HeadlessException {
        // TODO add your handling code here:
        int Verifty_Code = ram.nextInt(999999);
        Code = Verifty_Code;
        System.out.println(Code);
        System.out.println(Verifty_Code);
        final String username = "quangnvpk02150@fpt.edu.vn";
        final String password = "Quang01255215639";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("LAMHOUSE"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(x)
            );
            message.setSubject("DEMO TITLE");
            message.setText(String.valueOf(Verifty_Code));
            
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Mã Code đã được gửi đến email của bạn,Vui lòng check email !!");
            Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 45;
                    btnSendEmail.setEnabled(false);
                    
                
                    try {
                        for (int i = 0; i <= 45; i++) {
                     txthour.setText("00:"+String.valueOf(time)+"s");
                     time--;
                      Thread.sleep(1000);
                }
                       
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Gioithieu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                card();
            }
        });
        thread.start();
       
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        }
        
    }
    public void card(){
        btnSendEmail.setEnabled(true);
        txthour.setText("");
    }
    public void SAVE(){      //Save the UserName and Password (for one user)



        try {
            if(!file.exists()) file.createNewFile();  //if the file !exist create a new one
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
                bw.write(txtusername.getText()); //write the name
                bw.newLine(); //leave a new Line
                bw.write(txtpassword.getPassword()); //write the password
                //close the BufferdWriter
                //123123
            } //write the name

        } catch (IOException e) { e.printStackTrace(); }        

 }//End Of Save




  public void UPDATE(){ //UPDATE ON OPENING THE APPLICATION

        try {
          if(file.exists()){    //if this file exists

            Scanner scan = new Scanner(file);   //Use Scanner to read the File

            txtusername.setText(scan.nextLine());  //append the text to name field
            txtpassword.setText(scan.nextLine()); //append the text to password field
            scan.close();
          }

        } catch (FileNotFoundException e) {         
            e.printStackTrace();
        }                

   }//End OF UPDATE

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackLogin;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnSendEmail;
    private javax.swing.JButton btnVeriftyCode;
    private javax.swing.JLabel disable;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelForgot;
    private javax.swing.JPanel panelChangePassword;
    private javax.swing.JPanel panelForgot;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JCheckBox remember;
    private javax.swing.JLabel show;
    private javax.swing.JTextField txtCodeEmail;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNewPassword;
    private javax.swing.JTextField txtVeritfyPassword;
    private javax.swing.JLabel txthour;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
