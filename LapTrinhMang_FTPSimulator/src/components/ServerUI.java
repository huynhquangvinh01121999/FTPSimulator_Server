package components;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class ServerUI extends javax.swing.JFrame implements Runnable{

    public ServerUI() {
        initComponents();
        setLocationRelativeTo(this);
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        pnlLogin.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup_Sex = new javax.swing.ButtonGroup();
        pnlContainer = new javax.swing.JPanel();
        pnlLogin = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new RoundedPanel(100, new Color(51,37,78));
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        txtUser = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        cbRemember = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        lblSignIn = new RoundedSignIn(30, new Color(83,187,98));
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new RoundedLable(50, new Color(69,156,81));
        jPanel3 = new RoundedPanel(100, new Color(83,187,98));
        jLabel16 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1110, 570));

        pnlContainer.setLayout(new java.awt.CardLayout());

        pnlLogin.setBackground(new java.awt.Color(34, 92, 198));
        pnlLogin.setLayout(null);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LeftPanelBackGround.jpg"))); // NOI18N
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlLogin.add(jLabel9);
        jLabel9.setBounds(210, 30, 255, 508);

        jPanel1.setBackground(new java.awt.Color(67, 0, 66));
        jPanel1.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Password:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(40, 270, 120, 30);

        jLabel11.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Email");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(30, 160, 120, 30);

        txtPass.setBackground(new java.awt.Color(51, 37, 78));
        txtPass.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPass.setForeground(new java.awt.Color(204, 204, 204));
        txtPass.setBorder(null);
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });
        jPanel1.add(txtPass);
        txtPass.setBounds(70, 300, 280, 40);

        txtUser.setBackground(new java.awt.Color(51, 37, 78));
        txtUser.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtUser.setForeground(new java.awt.Color(204, 204, 204));
        txtUser.setBorder(null);
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
        });
        jPanel1.add(txtUser);
        txtUser.setBounds(70, 190, 280, 40);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(70, 340, 280, 10);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(70, 230, 280, 10);

        cbRemember.setBackground(new java.awt.Color(51, 37, 78));
        cbRemember.setBorder(null);
        cbRemember.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(cbRemember);
        cbRemember.setBounds(70, 360, 20, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Remember password");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(100, 360, 180, 30);

        lblSignIn.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        lblSignIn.setForeground(new java.awt.Color(255, 255, 255));
        lblSignIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSignIn.setText("Sign In");
        lblSignIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSignInMouseClicked(evt);
            }
        });
        jPanel1.add(lblSignIn);
        lblSignIn.setBounds(150, 420, 120, 40);

        jLabel13.setFont(new java.awt.Font("Pristina", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Login FCloud");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 30, 350, 100);

        pnlLogin.add(jPanel1);
        jPanel1.setBounds(520, 20, 390, 530);

        jLabel14.setBackground(new java.awt.Color(69, 156, 81));
        jLabel14.setOpaque(true);
        pnlLogin.add(jLabel14);
        jLabel14.setBounds(500, 70, 29, 430);

        jPanel3.setLayout(null);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel16);
        jLabel16.setBounds(29, 11, 255, 508);

        pnlLogin.add(jPanel3);
        jPanel3.setBounds(170, 20, 390, 530);

        pnlContainer.add(pnlLogin, "pnlLogin");

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Upload New File");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(jLabel1);
        jLabel1.setBounds(52, 130, 160, 30);

        jLabel2.setFont(new java.awt.Font("VNI-Aztek", 0, 42)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 92, 198));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons-cloud-42.png"))); // NOI18N
        jLabel2.setText("  FCloud");
        pnlMain.add(jLabel2);
        jLabel2.setBounds(30, 0, 230, 60);
        pnlMain.add(jTextField1);
        jTextField1.setBounds(360, 50, 310, 30);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-shutdown-25.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        pnlMain.add(jLabel3);
        jLabel3.setBounds(1070, 0, 30, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Xin chào admin: Huỳnh Quang Vinh");
        pnlMain.add(jLabel4);
        jLabel4.setBounds(775, 0, 250, 30);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-logout-25.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        pnlMain.add(jLabel5);
        jLabel5.setBounds(1030, 0, 40, 30);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-help-25.png"))); // NOI18N
        pnlMain.add(jLabel6);
        jLabel6.setBounds(810, 50, 40, 30);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-setting-25.png"))); // NOI18N
        pnlMain.add(jLabel7);
        jLabel7.setBounds(860, 50, 40, 30);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-notification-25.png"))); // NOI18N
        pnlMain.add(jLabel8);
        jLabel8.setBounds(760, 50, 40, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background.png"))); // NOI18N
        pnlMain.add(background);
        background.setBounds(0, 0, 1110, 570);

        pnlContainer.add(pnlMain, "pnlMain");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        }
    }//GEN-LAST:event_txtPassKeyReleased

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtUserKeyReleased

    private void lblSignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignInMouseClicked
        tranferLayout("pnlMain");
    }//GEN-LAST:event_lblSignInMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        tranferLayout("pnlLogin");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int value = JOptionPane.showConfirmDialog(this, "You will shutdown then click OK.!\nAre you sure.?", "Warnning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (value == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Thanks for using FCloud.!!!", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    public void tranferLayout(String panelName) {
        repaint();
        CardLayout layout = (CardLayout) (pnlContainer.getLayout());
        layout.show(pnlContainer, panelName);
    }

    @Override
    public void run() {
        new ServerUI().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Rounded">
    class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draws the rounded panel with borders
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setBackground(getBackground());
            }
            graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcs.width, arcs.height);
            graphics.setColor(getForeground());
        }
    }

    class RoundedLable extends JLabel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedLable(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedLable(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draws the rounded panel with borders
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setBackground(getBackground());
            }
            graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcs.width, arcs.height);
            graphics.setColor(getForeground());
        }
    }

    class RoundedSignIn extends JLabel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedSignIn(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedSignIn(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draws the rounded panel with borders
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setBackground(getBackground());
            }
            graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcs.width, arcs.height);
            graphics.setColor(getForeground());
            graphics.drawString("Sign In", 37, 26);
        }
    }

    class RoundedRegister extends JLabel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedRegister(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedRegister(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draws the rounded panel with borders
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setBackground(getBackground());
            }
            graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcs.width, arcs.height);
            graphics.setColor(getForeground());
            graphics.drawString("Register", 35, 23);
        }
    }
// </editor-fold> 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.ButtonGroup btnGroup_Sex;
    private javax.swing.JCheckBox cbRemember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblSignIn;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
