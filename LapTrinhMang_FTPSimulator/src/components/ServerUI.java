package components;

import bll.UserBLL;
import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.MembersOnline;
import models.Users;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class ServerUI extends javax.swing.JFrame implements Runnable {

    private DefaultTableModel tblUserSettingModel;

    private final UserBLL userBLL = new UserBLL();

    public ServerUI() {
        initComponents();
        setLocationRelativeTo(this);

        // set table header
        setDefaultTableModel();

        // load data table
        loadDataUserSettingModel();
    }

    private void setDefaultTableModel() {
        tblUserSettingModel = (DefaultTableModel) tblUserSetting.getModel();
        tblUserSettingModel.setColumnIdentifiers(new Object[]{
            "Email", "Trạng thái", "Max size upload", "Max size download", "Quyền", "Quyền rút gọn"
        });
        hiddenColumn(tblUserSetting, 5);
    }

    private void loadDataUserSettingModel() {
        tblUserSettingModel.setRowCount(0);
        userBLL.getAllUser().forEach((user) -> {
            tblUserSettingModel.addRow(new Object[]{
                user.getEmail().trim(),
                user.getStatus().trim().equals("unblock") ? "Active" : "Bị khóa",
                Long.parseLong(user.getFileSizeUpload().trim()) / 1024 + "KB",
                Long.parseLong(user.getFileSizeDownload().trim()) / 1024 + "KB",
                user.getPermissionId().trim().equals("all") ? "All"
                : (user.getPermissionId().trim().equals("u") ? "Chỉ được phép upload"
                : (user.getPermissionId().trim().equals("d") ? "Chỉ được phép download" : "Chỉ được phép đọc")),
                user.getPermissionId().trim()
            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlSection = new javax.swing.JPanel();
        pnlUserSettings = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUserSetting = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        btnUserSettingLockUpload = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnUserSettingAllowUpload = new javax.swing.JButton();
        btnUserSettingLockDownload = new javax.swing.JButton();
        btnUserSettingAllowDownload = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnlUserPermissions = new javax.swing.JPanel();
        pnlAnonymousSettings = new javax.swing.JPanel();
        pnlViewUserOnline = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUserOnlineView = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAllUserView = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1110, 570));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(null);

        jLabel2.setFont(new java.awt.Font("VNI-Aztek", 0, 42)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 92, 198));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons-cloud-42.png"))); // NOI18N
        jLabel2.setText("  FCloud");
        pnlMain.add(jLabel2);
        jLabel2.setBounds(30, 0, 230, 60);

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Welcome to Administration");
        pnlMain.add(jLabel4);
        jLabel4.setBounds(465, 0, 270, 30);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-help-25.png"))); // NOI18N
        pnlMain.add(jLabel6);
        jLabel6.setBounds(990, 0, 40, 30);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-setting-25.png"))); // NOI18N
        pnlMain.add(jLabel7);
        jLabel7.setBounds(1030, 0, 40, 30);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-notification-25.png"))); // NOI18N
        pnlMain.add(jLabel8);
        jLabel8.setBounds(950, 0, 40, 30);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-online-22.png"))); // NOI18N
        jLabel1.setText(" View User Online");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        pnlMain.add(jLabel1);
        jLabel1.setBounds(50, 373, 160, 20);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-setting-25.png"))); // NOI18N
        jLabel5.setText(" User Settings");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        pnlMain.add(jLabel5);
        jLabel5.setBounds(50, 134, 160, 20);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-anonymous-20.png"))); // NOI18N
        jLabel9.setText(" Anonymous Settings");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        pnlMain.add(jLabel9);
        jLabel9.setBounds(50, 292, 160, 20);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-access-denied-22.png"))); // NOI18N
        jLabel10.setText(" User Permissions");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        pnlMain.add(jLabel10);
        jLabel10.setBounds(50, 212, 160, 20);

        pnlSection.setBackground(new java.awt.Color(255, 255, 255));
        pnlSection.setLayout(new java.awt.CardLayout());

        pnlUserSettings.setBackground(new java.awt.Color(255, 255, 255));

        tblUserSetting.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tblUserSetting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUserSetting.setRowHeight(30);
        jScrollPane3.setViewportView(tblUserSetting);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("All Users");

        btnUserSettingLockUpload.setBackground(new java.awt.Color(255, 0, 0));
        btnUserSettingLockUpload.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingLockUpload.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingLockUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-lock-22.png"))); // NOI18N
        btnUserSettingLockUpload.setText(" Lock upload");
        btnUserSettingLockUpload.setBorderPainted(false);
        btnUserSettingLockUpload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingLockUpload.setFocusPainted(false);
        btnUserSettingLockUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingLockUploadActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Block/Unblock user with download/upload features:");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btnUserSettingAllowUpload.setBackground(new java.awt.Color(51, 204, 0));
        btnUserSettingAllowUpload.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingAllowUpload.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingAllowUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-unlock-22.png"))); // NOI18N
        btnUserSettingAllowUpload.setText(" Unlock upload");
        btnUserSettingAllowUpload.setBorderPainted(false);
        btnUserSettingAllowUpload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingAllowUpload.setFocusPainted(false);
        btnUserSettingAllowUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingAllowUploadActionPerformed(evt);
            }
        });

        btnUserSettingLockDownload.setBackground(new java.awt.Color(255, 0, 0));
        btnUserSettingLockDownload.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingLockDownload.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingLockDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-lock-22.png"))); // NOI18N
        btnUserSettingLockDownload.setText(" Lock download");
        btnUserSettingLockDownload.setBorderPainted(false);
        btnUserSettingLockDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingLockDownload.setFocusPainted(false);
        btnUserSettingLockDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingLockDownloadActionPerformed(evt);
            }
        });

        btnUserSettingAllowDownload.setBackground(new java.awt.Color(51, 204, 0));
        btnUserSettingAllowDownload.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingAllowDownload.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingAllowDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-unlock-22.png"))); // NOI18N
        btnUserSettingAllowDownload.setText(" Unlock download");
        btnUserSettingAllowDownload.setBorderPainted(false);
        btnUserSettingAllowDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingAllowDownload.setFocusPainted(false);
        btnUserSettingAllowDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingAllowDownloadActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Setting max capacity (max 100TB = 100,099,511,627,776 byte):");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(1L), Long.valueOf(1L), Long.valueOf(100099511627776L), Long.valueOf(1L)));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Max capacity upload:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Max capacity download:");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(1L), Long.valueOf(1L), Long.valueOf(100099511627776L), Long.valueOf(1L)));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-update-20.png"))); // NOI18N
        jButton5.setText(" Update");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-update-20.png"))); // NOI18N
        jButton6.setText(" Update");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusPainted(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText("(byte)");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText("(byte)");

        jButton1.setText("test");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserSettingsLayout = new javax.swing.GroupLayout(pnlUserSettings);
        pnlUserSettings.setLayout(pnlUserSettingsLayout);
        pnlUserSettingsLayout.setHorizontalGroup(
            pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jSeparator1)
            .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner1)
                    .addComponent(jSpinner2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addGap(60, 60, 60)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUserSettingLockDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUserSettingLockUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUserSettingAllowDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUserSettingAllowUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 65, Short.MAX_VALUE))
        );
        pnlUserSettingsLayout.setVerticalGroup(
            pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserSettingsLayout.createSequentialGroup()
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserSettingLockUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserSettingAllowUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUserSettingLockDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserSettingAllowDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlSection.add(pnlUserSettings, "pnlUserSettings");

        pnlUserPermissions.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlUserPermissionsLayout = new javax.swing.GroupLayout(pnlUserPermissions);
        pnlUserPermissions.setLayout(pnlUserPermissionsLayout);
        pnlUserPermissionsLayout.setHorizontalGroup(
            pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        pnlUserPermissionsLayout.setVerticalGroup(
            pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        pnlSection.add(pnlUserPermissions, "pnlUserPermissions");

        pnlAnonymousSettings.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlAnonymousSettingsLayout = new javax.swing.GroupLayout(pnlAnonymousSettings);
        pnlAnonymousSettings.setLayout(pnlAnonymousSettingsLayout);
        pnlAnonymousSettingsLayout.setHorizontalGroup(
            pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        pnlAnonymousSettingsLayout.setVerticalGroup(
            pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        pnlSection.add(pnlAnonymousSettings, "pnlAnonymousSettings");

        pnlViewUserOnline.setBackground(new java.awt.Color(255, 255, 255));

        tblUserOnlineView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblUserOnlineView);

        tblAllUserView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblAllUserView);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-online-22.png"))); // NOI18N
        jLabel11.setText(" User Online");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("All Users");

        javax.swing.GroupLayout pnlViewUserOnlineLayout = new javax.swing.GroupLayout(pnlViewUserOnline);
        pnlViewUserOnline.setLayout(pnlViewUserOnlineLayout);
        pnlViewUserOnlineLayout.setHorizontalGroup(
            pnlViewUserOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
            .addGroup(pnlViewUserOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlViewUserOnlineLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlViewUserOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlViewUserOnlineLayout.createSequentialGroup()
                            .addGap(460, 460, 460)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlViewUserOnlineLayout.createSequentialGroup()
                            .addGroup(pnlViewUserOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlViewUserOnlineLayout.setVerticalGroup(
            pnlViewUserOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(pnlViewUserOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlViewUserOnlineLayout.createSequentialGroup()
                    .addGap(0, 52, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlViewUserOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlViewUserOnlineLayout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pnlSection.add(pnlViewUserOnline, "pnlViewUserOnline");

        pnlMain.add(pnlSection);
        pnlSection.setBounds(310, 70, 790, 490);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/background.png"))); // NOI18N
        pnlMain.add(background);
        background.setBounds(0, 0, 1110, 570);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int value = JOptionPane.showConfirmDialog(this, "You will shutdown then click OK.!\nAre you sure.?", "Warnning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (value == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Thanks for using FCloud.!!!", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        tranferLayout("pnlUserSettings");
        loadDataUserSettingModel();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        tranferLayout("pnlUserPermissions");
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        tranferLayout("pnlAnonymousSettings");
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        tranferLayout("pnlViewUserOnline");
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnUserSettingLockUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingLockUploadActionPerformed
        // all -> d
        // u -> r
        int selectedRow = tblUserSetting.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user cần lock chức năng upload.!!!");
        } else {
            String email = tblUserSetting.getValueAt(selectedRow, 0).toString();
            String perId = tblUserSetting.getValueAt(selectedRow, 5).toString();
            switch (perId) {
                case "all": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "d");
                        Message("Chức năng upload của user " + email + " đã bị khóa.!!!");
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "d;Chức năng upload của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user upload file.!!!");
                        System.err.println("Đã xảy ra lỗi khi lock user upload file - " + ex);
                    }
                    break;
                }
                case "u": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "r");
                        Message("Chức năng upload của user " + email + " đã bị khóa.!!!");
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "r;Chức năng upload của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user upload file.!!!");
                        System.err.println("Đã xảy ra lỗi khi lock user upload file - " + ex);
                    }
                    break;
                }
                default: {
                    Message("User này đã bị lock upload file rồi, lấy gì lock nữa");
                }
            }
        }
    }//GEN-LAST:event_btnUserSettingLockUploadActionPerformed

    private void btnUserSettingAllowUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingAllowUploadActionPerformed
        // d -> all
        // r -> u
        int selectedRow = tblUserSetting.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user cần mở khóa chức năng upload.!!!");
        } else {
            String email = tblUserSetting.getValueAt(selectedRow, 0).toString();
            String perId = tblUserSetting.getValueAt(selectedRow, 5).toString();
            switch (perId) {
                case "d": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "all");
                        Message("Đã mở khóa chức năng upload cho user " + email);
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "all;Giờ đây bạn đã có thể sử dụng chức năng upload");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user upload file.!!!");
                        System.err.println("Đã xảy ra lỗi khi unlock user upload file - " + ex);
                    }
                    break;
                }
                case "r": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "u");
                        Message("Đã mở khóa chức năng upload cho user " + email);
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "u;Giờ đây bạn đã có thể sử dụng chức năng upload");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user upload file.!!!");
                        System.err.println("Đã xảy ra lỗi khi unlock user upload file - " + ex);
                    }
                    break;
                }
                default: {
                    Message("Đã mở khóa chức năng upload cho user này rồi, lấy gì mở nữa.!!!");
                }
            }
        }
    }//GEN-LAST:event_btnUserSettingAllowUploadActionPerformed

    private void btnUserSettingLockDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingLockDownloadActionPerformed
        // all -> u
        // d -> r
        int selectedRow = tblUserSetting.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user cần lock chức năng download.!!!");
        } else {
            String email = tblUserSetting.getValueAt(selectedRow, 0).toString();
            String perId = tblUserSetting.getValueAt(selectedRow, 5).toString();
            switch (perId) {
                case "all": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "u");
                        Message("Chức năng download của user " + email + " đã bị khóa.!!!");
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "u;Chức năng download của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user download file.!!!");
                        System.err.println("Đã xảy ra lỗi khi lock user download file - " + ex);
                    }
                    break;
                }
                case "d": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "r");
                        Message("Chức năng download của user " + email + " đã bị khóa.!!!");
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "r;Chức năng download của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user download file.!!!");
                        System.err.println("Đã xảy ra lỗi khi lock user download file - " + ex);
                    }
                    break;
                }
                default: {
                    Message("User này đã bị lock download file rồi, lấy gì lock nữa");
                }
            }
        }
    }//GEN-LAST:event_btnUserSettingLockDownloadActionPerformed

    private void btnUserSettingAllowDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingAllowDownloadActionPerformed
        // u -> all
        // r -> d
        int selectedRow = tblUserSetting.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user cần mở khóa chức năng download.!!!");
        } else {
            String email = tblUserSetting.getValueAt(selectedRow, 0).toString();
            String perId = tblUserSetting.getValueAt(selectedRow, 5).toString();
            switch (perId) {
                case "u": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "all");
                        Message("Đã mở khóa chức năng download cho user " + email);
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "all;Giờ đây bạn đã có thể sử dụng chức năng download");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user download file.!!!");
                        System.err.println("Đã xảy ra lỗi khi unlock user download file - " + ex);
                    }
                    break;
                }
                case "r": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "d");
                        Message("Đã mở khóa chức năng download cho user " + email);
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features_file", "d;Giờ đây bạn đã có thể sử dụng chức năng download");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user download file.!!!");
                        System.err.println("Đã xảy ra lỗi khi unlock user download file - " + ex);
                    }
                    break;
                }
                default: {
                    Message("Đã mở khóa chức năng download cho user này rồi, lấy gì mở nữa.!!!");
                }
            }
        }
    }//GEN-LAST:event_btnUserSettingAllowDownloadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        handleUpdateDataClientThread("huynhquangvinh1999@gmail.com", "test_from_serverui", "xin chào");
    }//GEN-LAST:event_jButton1ActionPerformed

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Gửi data update qua cho client đc chỉ định trước">
    private void handleUpdateDataClientThread(String email, String message, Object object) {
        if (Server.getMembersOnline() != null) {     // kiểm tra khác null
            for (MembersOnline member : Server.getMembersOnline()) {
                if (member.getUsers().getEmail().trim().equals(email)) {
                    member.getListenThread().response(message, object);
                    break;
                }
            }
        }
    }
    //</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Chuyển layout">
    public void tranferLayout(String panelName) {
        repaint();
        CardLayout layout = (CardLayout) (pnlSection.getLayout());
        layout.show(pnlSection, panelName);
    }
    //</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Ẩn cột trong table theo chỉ định">
    private void hiddenColumn(JTable table, int columnIndex) {
        table.getColumnModel().getColumn(columnIndex).setMinWidth(0);
        table.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
        table.getColumnModel().getColumn(columnIndex).setWidth(0);
    }
    //</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Hiển thị modal thông báo">
    private void Message(String message) {
        JOptionPane.showMessageDialog(rootPane, message);
    }
    //</editor-fold>

    @Override
    public void run() {
        new ServerUI().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnUserSettingAllowDownload;
    private javax.swing.JButton btnUserSettingAllowUpload;
    private javax.swing.JButton btnUserSettingLockDownload;
    private javax.swing.JButton btnUserSettingLockUpload;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JPanel pnlAnonymousSettings;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSection;
    private javax.swing.JPanel pnlUserPermissions;
    private javax.swing.JPanel pnlUserSettings;
    private javax.swing.JPanel pnlViewUserOnline;
    private javax.swing.JTable tblAllUserView;
    private javax.swing.JTable tblUserOnlineView;
    private javax.swing.JTable tblUserSetting;
    // End of variables declaration//GEN-END:variables
}
