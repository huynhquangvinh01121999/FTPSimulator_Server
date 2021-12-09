package components;

import bll.FolderBLL;
import bll.UserBLL;
import dal.Services.FolderServices;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.FolderUserItem;
import models.Folders;
import models.MembersOnline;
import models.UpdateResultFolderUserPermission;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class ServerUI extends javax.swing.JFrame implements Runnable {

    // TABLE MODEL
    private DefaultTableModel tblUserSettingModel;
    private static DefaultTableModel tblClientConnectAnonymousSettingsModel;
    private DefaultTableModel tblAllUserAnonymousSettingsModel;
    private DefaultTableModel tblAllUserOfUserPermissionModel;
    private DefaultTableModel tblFolderOwnOfUserModel;

    // CONSTRUCTOR
    private final UserBLL userBLL = new UserBLL();

    // LIST DATA
    private static List<ListenThread> listenThread = new ArrayList<>();
    private static List<Folders> folderChildOfUser = new ArrayList<>();

    public ServerUI() {
        initComponents();
        setLocationRelativeTo(this);

        // set table header
        setDefaultTableModel();

        // load data table
        loadDataUserSettingModel();
        loadDataUserAnonymousSettingsModel();
        loadDataClientConnectionAnonymousSettingsModel();
        loadDataUserPermissionModel();

        userBLL.genarateAnonymous();
        
        // set edit table
        setDefaultEditorTable();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="set edit table">
    private void setDefaultEditorTable(){
        tblUserSetting.setDefaultEditor(Object.class, null);
        tblAllUserOfUserPermission.setDefaultEditor(Object.class, null);
        tblFolderOwnOfUser.setDefaultEditor(Object.class, null);
        tblClientConnectAnonymousSettings.setDefaultEditor(Object.class, null);
        tblAllUserAnonymousSettings.setDefaultEditor(Object.class, null);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="RELOAD CLIENT IN TABLE MODEL">
    public static void reloadClientConnect(ListenThread client) {
        listenThread.add(client);

        // load lại bảng table client
        loadDataClientConnectionAnonymousSettingsModel();
    }

    public static void reloadClientDisconnect(ListenThread client) {
        listenThread.remove(client);

        // load lại bảng table client
        loadDataClientConnectionAnonymousSettingsModel();
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="SET HEADER TABLE">
    private void setDefaultTableModel() {
        tblUserSettingModel = (DefaultTableModel) tblUserSetting.getModel();
        tblUserSettingModel.setColumnIdentifiers(new Object[]{
            "Email", "Trạng thái", "Max size upload", "Max size download", "Quyền", "Quyền rút gọn"
        });
        hiddenColumn(tblUserSetting, 5);

        tblClientConnectAnonymousSettingsModel = (DefaultTableModel) tblClientConnectAnonymousSettings.getModel();
        tblClientConnectAnonymousSettingsModel.setColumnIdentifiers(new Object[]{
            "ID", "Address", "Host name", "Port", "Local port", "Ẩn danh"
        });

        tblAllUserAnonymousSettingsModel = (DefaultTableModel) tblAllUserAnonymousSettings.getModel();
        tblAllUserAnonymousSettingsModel.setColumnIdentifiers(new Object[]{
            "Email", "Chế độ sử dụng chức năng ẩn danh", "AnonymousPermission"
        });
        hiddenColumn(tblAllUserAnonymousSettings, 2);

        tblAllUserOfUserPermissionModel = (DefaultTableModel) tblAllUserOfUserPermission.getModel();
        tblAllUserOfUserPermissionModel.setColumnIdentifiers(new Object[]{
            "Email", "Tên người dùng", "Trạng thái"
        });

        tblFolderOwnOfUserModel = (DefaultTableModel) tblFolderOwnOfUser.getModel();
        tblFolderOwnOfUserModel.setColumnIdentifiers(new Object[]{
            "FolderID", "Tên thư mục", "Chủ sở hữu", "Quyền user"
        });
        hiddenColumn(tblFolderOwnOfUser, 0);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="LOAD DATA INTO TABLE">     
    private void loadDataUserSettingModel() {
        tblUserSettingModel.setRowCount(0);
        userBLL.getAllUser().forEach((user) -> {
            tblUserSettingModel.addRow(new Object[]{
                user.getEmail().trim(),
                user.getStatus().trim().equals("unlock") ? "Active" : "Bị khóa",
                Long.parseLong(user.getFileSizeUpload().trim()) / 1024 + "KB",
                Long.parseLong(user.getFileSizeDownload().trim()) / 1024 + "KB",
                user.getPermissionId().trim().equals("all") ? "All"
                : (user.getPermissionId().trim().equals("u") ? "Chỉ được phép upload"
                : (user.getPermissionId().trim().equals("d") ? "Chỉ được phép download" : "Chỉ được phép đọc")),
                user.getPermissionId().trim()
            });
        });
    }

    private void loadDataUserAnonymousSettingsModel() {
        tblAllUserAnonymousSettingsModel.setRowCount(0);
        userBLL.getAllUser().forEach((user) -> {
            tblAllUserAnonymousSettingsModel.addRow(new Object[]{
                user.getEmail().trim(),
                user.getAnonymousPermission().trim().equals("unlock") ? "Cho phép" : "Không cho phép",
                user.getAnonymousPermission().trim()
            });
        });

    }

    private static void loadDataClientConnectionAnonymousSettingsModel() {
        tblClientConnectAnonymousSettingsModel.setRowCount(0);
        listenThread.forEach((client) -> {
            tblClientConnectAnonymousSettingsModel.addRow(new Object[]{
                client.getSocketId(), client.getSocket().getInetAddress().getHostAddress(),
                client.getSocket().getInetAddress().getHostName(),
                client.getSocket().getPort(),
                client.getSocket().getLocalPort(),
                client.isANONYMOUS_PERMISSION() ? "Cho phép" : "Không cho phép"
            });
        });
    }

    private void loadDataUserPermissionModel() {
        tblAllUserOfUserPermissionModel.setRowCount(0);
        userBLL.getAllUser().forEach((user) -> {
            tblAllUserOfUserPermissionModel.addRow(new Object[]{
                user.getEmail(), user.getFullName(),
                user.getStatus().trim().equals("unlock") ? "Active" : "Bị khóa",});
        });
    }

    // </editor-fold>
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
        btnUpdateCapacityUpload = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel28 = new javax.swing.JLabel();
        btnUpdateCapacityUpload1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        pnlUserPermissions = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblAllUserOfUserPermission = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblFolderOwnOfUser = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnUserSettingLockUpload3 = new javax.swing.JButton();
        btnUserSettingAllowUpload3 = new javax.swing.JButton();
        pnlAnonymousSettings = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblClientConnectAnonymousSettings = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAllUserAnonymousSettings = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnUserSettingLockUpload1 = new javax.swing.JButton();
        btnUserSettingAllowUpload1 = new javax.swing.JButton();
        btnUserSettingLockUpload2 = new javax.swing.JButton();
        btnUserSettingAllowUpload2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
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
        jLabel15.setText("Setting max capacity:");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(1L), Long.valueOf(1L), Long.valueOf(100099511627776L), Long.valueOf(1L)));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Max capacity upload:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Max capacity download:");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(1L), Long.valueOf(1L), Long.valueOf(100099511627776L), Long.valueOf(1L)));

        btnUpdateCapacityUpload.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateCapacityUpload.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUpdateCapacityUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-update-20.png"))); // NOI18N
        btnUpdateCapacityUpload.setText(" Update");
        btnUpdateCapacityUpload.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnUpdateCapacityUpload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateCapacityUpload.setFocusPainted(false);
        btnUpdateCapacityUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCapacityUploadActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-update-20.png"))); // NOI18N
        jButton6.setText(" Update");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText("(byte)");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText("(byte)");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Max storage capacity:");

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(1L), Long.valueOf(1L), Long.valueOf(100099511627776L), Long.valueOf(1L)));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setText("(byte)");

        btnUpdateCapacityUpload1.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateCapacityUpload1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUpdateCapacityUpload1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-update-20.png"))); // NOI18N
        btnUpdateCapacityUpload1.setText(" Update");
        btnUpdateCapacityUpload1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnUpdateCapacityUpload1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateCapacityUpload1.setFocusPainted(false);
        btnUpdateCapacityUpload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCapacityUpload1ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("(max 100TB = 100,099,511,627,776 byte):");

        javax.swing.GroupLayout pnlUserSettingsLayout = new javax.swing.GroupLayout(pnlUserSettings);
        pnlUserSettings.setLayout(pnlUserSettingsLayout);
        pnlUserSettingsLayout.setHorizontalGroup(
            pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jSeparator1)
            .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUserSettingLockDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUserSettingLockUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUserSettingAllowDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUserSettingAllowUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinner1)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUpdateCapacityUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdateCapacityUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(btnUserSettingAllowDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlUserSettingsLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(1, 1, 1)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateCapacityUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUpdateCapacityUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlUserSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserSettingsLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlSection.add(pnlUserSettings, "pnlUserSettings");

        pnlUserPermissions.setBackground(new java.awt.Color(255, 255, 255));

        tblAllUserOfUserPermission.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAllUserOfUserPermission.setRowHeight(30);
        tblAllUserOfUserPermission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAllUserOfUserPermissionMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblAllUserOfUserPermission);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("All Users");

        jLabel24.setFont(new java.awt.Font("Thanhoa", 1, 20)); // NOI18N
        jLabel24.setText("User Permission Use Folder");

        tblFolderOwnOfUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFolderOwnOfUser.setRowHeight(30);
        jScrollPane7.setViewportView(tblFolderOwnOfUser);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Folder own:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("<=>");

        btnUserSettingLockUpload3.setBackground(new java.awt.Color(255, 0, 0));
        btnUserSettingLockUpload3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingLockUpload3.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingLockUpload3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-lock-22.png"))); // NOI18N
        btnUserSettingLockUpload3.setText(" Lock user permission");
        btnUserSettingLockUpload3.setBorderPainted(false);
        btnUserSettingLockUpload3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingLockUpload3.setFocusPainted(false);
        btnUserSettingLockUpload3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingLockUpload3ActionPerformed(evt);
            }
        });

        btnUserSettingAllowUpload3.setBackground(new java.awt.Color(51, 204, 0));
        btnUserSettingAllowUpload3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingAllowUpload3.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingAllowUpload3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-unlock-22.png"))); // NOI18N
        btnUserSettingAllowUpload3.setText(" Unlock user permission");
        btnUserSettingAllowUpload3.setBorderPainted(false);
        btnUserSettingAllowUpload3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingAllowUpload3.setFocusPainted(false);
        btnUserSettingAllowUpload3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingAllowUpload3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserPermissionsLayout = new javax.swing.GroupLayout(pnlUserPermissions);
        pnlUserPermissions.setLayout(pnlUserPermissionsLayout);
        pnlUserPermissionsLayout.setHorizontalGroup(
            pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserPermissionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserPermissionsLayout.createSequentialGroup()
                        .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlUserPermissionsLayout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlUserPermissionsLayout.createSequentialGroup()
                        .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserPermissionsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUserSettingAllowUpload3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(btnUserSettingLockUpload3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlUserPermissionsLayout.setVerticalGroup(
            pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserPermissionsLayout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUserSettingLockUpload3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUserSettingAllowUpload3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserPermissionsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUserPermissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlUserPermissionsLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlSection.add(pnlUserPermissions, "pnlUserPermissions");

        pnlAnonymousSettings.setBackground(new java.awt.Color(255, 255, 255));

        tblClientConnectAnonymousSettings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblClientConnectAnonymousSettings.setRowHeight(30);
        jScrollPane4.setViewportView(tblClientConnectAnonymousSettings);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("All Users");

        tblAllUserAnonymousSettings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAllUserAnonymousSettings.setRowHeight(30);
        jScrollPane5.setViewportView(tblAllUserAnonymousSettings);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-online-22.png"))); // NOI18N
        jLabel21.setText(" Client connected");

        jLabel22.setFont(new java.awt.Font("Thanhoa", 1, 20)); // NOI18N
        jLabel22.setText("Anonymous Settings");

        btnUserSettingLockUpload1.setBackground(new java.awt.Color(255, 0, 0));
        btnUserSettingLockUpload1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingLockUpload1.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingLockUpload1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-lock-22.png"))); // NOI18N
        btnUserSettingLockUpload1.setText(" Lock");
        btnUserSettingLockUpload1.setBorderPainted(false);
        btnUserSettingLockUpload1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingLockUpload1.setFocusPainted(false);
        btnUserSettingLockUpload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingLockUpload1ActionPerformed(evt);
            }
        });

        btnUserSettingAllowUpload1.setBackground(new java.awt.Color(51, 204, 0));
        btnUserSettingAllowUpload1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingAllowUpload1.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingAllowUpload1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-unlock-22.png"))); // NOI18N
        btnUserSettingAllowUpload1.setText(" Unlock");
        btnUserSettingAllowUpload1.setBorderPainted(false);
        btnUserSettingAllowUpload1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingAllowUpload1.setFocusPainted(false);
        btnUserSettingAllowUpload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingAllowUpload1ActionPerformed(evt);
            }
        });

        btnUserSettingLockUpload2.setBackground(new java.awt.Color(255, 0, 0));
        btnUserSettingLockUpload2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingLockUpload2.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingLockUpload2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-lock-22.png"))); // NOI18N
        btnUserSettingLockUpload2.setText(" Lock");
        btnUserSettingLockUpload2.setBorderPainted(false);
        btnUserSettingLockUpload2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingLockUpload2.setFocusPainted(false);
        btnUserSettingLockUpload2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingLockUpload2ActionPerformed(evt);
            }
        });

        btnUserSettingAllowUpload2.setBackground(new java.awt.Color(51, 204, 0));
        btnUserSettingAllowUpload2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUserSettingAllowUpload2.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSettingAllowUpload2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-unlock-22.png"))); // NOI18N
        btnUserSettingAllowUpload2.setText(" Unlock");
        btnUserSettingAllowUpload2.setBorderPainted(false);
        btnUserSettingAllowUpload2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSettingAllowUpload2.setFocusPainted(false);
        btnUserSettingAllowUpload2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSettingAllowUpload2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAnonymousSettingsLayout = new javax.swing.GroupLayout(pnlAnonymousSettings);
        pnlAnonymousSettings.setLayout(pnlAnonymousSettingsLayout);
        pnlAnonymousSettingsLayout.setHorizontalGroup(
            pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnonymousSettingsLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUserSettingAllowUpload1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUserSettingLockUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUserSettingAllowUpload2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(btnUserSettingLockUpload2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                    .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                        .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 10, Short.MAX_VALUE)))
        );
        pnlAnonymousSettingsLayout.setVerticalGroup(
            pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                                .addComponent(btnUserSettingLockUpload2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUserSettingAllowUpload2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                                .addComponent(btnUserSettingLockUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUserSettingAllowUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(295, Short.MAX_VALUE))
            .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlAnonymousSettingsLayout.createSequentialGroup()
                    .addGap(0, 204, Short.MAX_VALUE)
                    .addGroup(pnlAnonymousSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
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
        loadDataUserPermissionModel();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        tranferLayout("pnlAnonymousSettings");
        loadDataClientConnectionAnonymousSettingsModel();
        loadDataUserAnonymousSettingsModel();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
//        tranferLayout("pnlViewUserOnline");
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
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "d;Chức năng upload của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user upload file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi lock user upload file - " + ex);
                    }
                    break;
                }
                case "u": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "r");
                        Message("Chức năng upload của user " + email + " đã bị khóa.!!!");
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "r;Chức năng upload của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user upload file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi lock user upload file - " + ex);
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
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "all;Giờ đây bạn đã có thể sử dụng chức năng upload");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user upload file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi unlock user upload file - " + ex);
                    }
                    break;
                }
                case "r": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "u");
                        Message("Đã mở khóa chức năng upload cho user " + email);
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "u;Giờ đây bạn đã có thể sử dụng chức năng upload");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user upload file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi unlock user upload file - " + ex);
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
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "u;Chức năng download của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user download file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi lock user download file - " + ex);
                    }
                    break;
                }
                case "d": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "r");
                        Message("Chức năng download của user " + email + " đã bị khóa.!!!");
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "r;Chức năng download của bạn đã bị khóa");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi lock user download file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi lock user download file - " + ex);
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
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "all;Giờ đây bạn đã có thể sử dụng chức năng download");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user download file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi unlock user download file - " + ex);
                    }
                    break;
                }
                case "r": {
                    try {
                        userBLL.UpdatePermissionForUser(email, "d");
                        Message("Đã mở khóa chức năng download cho user " + email);
                        loadDataUserSettingModel();
                        handleUpdateDataClientThread(email, "update_lock_unlock_features", "d;Giờ đây bạn đã có thể sử dụng chức năng download");
                    } catch (Exception ex) {
                        Message("Đã xảy ra lỗi khi unlock user download file.!!!");
//                        System.err.println("Đã xảy ra lỗi khi unlock user download file - " + ex);
                    }
                    break;
                }
                default: {
                    Message("Đã mở khóa chức năng download cho user này rồi, lấy gì mở nữa.!!!");
                }
            }
        }
    }//GEN-LAST:event_btnUserSettingAllowDownloadActionPerformed

    private void btnUpdateCapacityUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCapacityUploadActionPerformed
        int selectedRow = tblUserSetting.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user cần cấu hình kích thước file upload.!!!");
        } else {
            String email = tblUserSetting.getValueAt(selectedRow, 0).toString();
            long value = (long) jSpinner1.getValue();
            userBLL.UpdateFileSizeUpload(email, value);
            loadDataUserSettingModel();
            // bắn message qua cho client để update
            handleUpdateDataClientThread(email, "update_file_size_upload", String.valueOf(value));
            Message("Cập nhật thành công.!!!");
        }
    }//GEN-LAST:event_btnUpdateCapacityUploadActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int selectedRow = tblUserSetting.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user cần cấu hình kích thước file upload.!!!");
        } else {
            String email = tblUserSetting.getValueAt(selectedRow, 0).toString();
            long value = (long) jSpinner2.getValue();
            userBLL.UpdateFileSizeDownload(email, value);
            loadDataUserSettingModel();
            // bắn message qua cho client để update
            handleUpdateDataClientThread(email, "update_file_size_download", String.valueOf(value));
            Message("Cập nhật thành công.!!!");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnUserSettingLockUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingLockUpload1ActionPerformed
        int selectedRow = tblClientConnectAnonymousSettings.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn client muốn chặn chức năng anonymous.!!!");
        } else {
            String address = tblClientConnectAnonymousSettings.getValueAt(selectedRow, 1).toString();
            String port = tblClientConnectAnonymousSettings.getValueAt(selectedRow, 3).toString();

            handleUpdateDataClientThreadWithPort(port, "update_client_anonymous_permission", false);
            Message("Đã chặn chức năng anonymous của client có thông tin:\nAddress: "
                    + address + "\nPort: " + port);

            // update lại quyền trên table
            for (ListenThread client : listenThread) {
                if (String.valueOf(client.getSocket().getPort()).equals(port)) {
                    client.setANONYMOUS_PERMISSION(false);
                    break;
                }
            }
            loadDataClientConnectionAnonymousSettingsModel();
        }
    }//GEN-LAST:event_btnUserSettingLockUpload1ActionPerformed

    private void btnUserSettingAllowUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingAllowUpload1ActionPerformed
        int selectedRow = tblClientConnectAnonymousSettings.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn client muốn kích hoạt chức năng anonymous.!!!");
        } else {
            String address = tblClientConnectAnonymousSettings.getValueAt(selectedRow, 1).toString();
            String port = tblClientConnectAnonymousSettings.getValueAt(selectedRow, 3).toString();

            handleUpdateDataClientThreadWithPort(port, "update_client_anonymous_permission", true);
            Message("Đã kích hoạt chức năng anonymous của client có thông tin:\nAddress: "
                    + address + "\nPort: " + port);

            // update lại quyền trên table
            for (ListenThread client : listenThread) {
                if (String.valueOf(client.getSocket().getPort()).equals(port)) {
                    client.setANONYMOUS_PERMISSION(true);
                    break;
                }
            }
            loadDataClientConnectionAnonymousSettingsModel();
        }
    }//GEN-LAST:event_btnUserSettingAllowUpload1ActionPerformed

    private void btnUserSettingLockUpload2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingLockUpload2ActionPerformed
        int selectedRow = tblAllUserAnonymousSettings.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user muốn chặn chức năng anonymous.!!!");
        } else {
            String email = tblAllUserAnonymousSettings.getValueAt(selectedRow, 0).toString();
            userBLL.UpdateAnonymousPermission(email, "lock");
            handleUpdateDataClientThread(email, "lock_user_anonymous_permission", "lock;Quyền truy cập anonymous của bạn đã bị vô hiệu hóa.!!!");
            Message("Đã chặn chức năng anonymous của user " + email);
            loadDataUserAnonymousSettingsModel();
        }
    }//GEN-LAST:event_btnUserSettingLockUpload2ActionPerformed

    private void btnUserSettingAllowUpload2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingAllowUpload2ActionPerformed
        int selectedRow = tblAllUserAnonymousSettings.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user muốn kích hoạt chức năng anonymous.!!!");
        } else {
            String email = tblAllUserAnonymousSettings.getValueAt(selectedRow, 0).toString();
            userBLL.UpdateAnonymousPermission(email, "unlock");
            handleUpdateDataClientThread(email, "unlock_user_anonymous_permission", "unlock;Giờ đây bạn đã có thể sử dụng chức năng anonymous.!!!");
            Message("Đã kích hoạt chức năng anonymous của user " + email);
            loadDataUserAnonymousSettingsModel();
        }
    }//GEN-LAST:event_btnUserSettingAllowUpload2ActionPerformed

    private void tblAllUserOfUserPermissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllUserOfUserPermissionMouseClicked
        int selectedRow = tblAllUserOfUserPermission.getSelectedRow();

        String email = tblAllUserOfUserPermission.getValueAt(selectedRow, 0).toString();

        FolderUserItem folderUserItem = new FolderBLL().GetListFolderChildUser(email);

        tblFolderOwnOfUserModel.setRowCount(0);
        if (folderUserItem != null) {
            folderChildOfUser = folderUserItem.getFolderChild();
            folderChildOfUser.forEach((folder) -> {
                tblFolderOwnOfUserModel.addRow(new Object[]{
                    folder.getFolderId(), folder.getFolderName(), email,
                    folder.getFolderUserPermission().trim().equals("unlock") ? "Cho phép" : "Vô hiệu hóa"
                });
            });
        }
    }//GEN-LAST:event_tblAllUserOfUserPermissionMouseClicked

    private void btnUserSettingLockUpload3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingLockUpload3ActionPerformed

        int selectedRow = tblFolderOwnOfUser.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn thư mục mà bạn muốn\nvô hiệu hóa quyền user của thư mục.!!!");
        } else {
            FolderBLL folderBLL = new FolderBLL();
            String folderId = tblFolderOwnOfUser.getValueAt(selectedRow, 0).toString();
            String folderName = tblFolderOwnOfUser.getValueAt(selectedRow, 1).toString();
            String email = tblFolderOwnOfUser.getValueAt(selectedRow, 2).toString();

            // tiến hành update giá trị FolderUserPermission = 'lock' cho folder con của user:
            // + update trên Table
            tblFolderOwnOfUserModel.setValueAt("Vô hiệu hóa", selectedRow, 3);

            // + update folder đc chọn trên database
            folderBLL.UpdateFolderUserPermission(folderId, "lock");

            // + lấy ra các folder con bên trong folder đc chọn update
            List<Folders> listFolderChild = new FolderServices().FindListChildFolder(folderId);
            if (!listFolderChild.isEmpty()) {
                List<Folders> folderGrandChildren = folderBLL.getFolderGrandChildren(listFolderChild);
                if (!folderGrandChildren.isEmpty()) {
                    listFolderChild.addAll(folderGrandChildren);
                }
                for (Folders folder : listFolderChild) {
                    // update các folder con bên trong
                    folderBLL.UpdateFolderUserPermission(folder.getFolderId(), "lock");
                }
            }
            UpdateResultFolderUserPermission result
                    = new UpdateResultFolderUserPermission(folderId.trim(), listFolderChild, "lock", "Quyền truy cập thư mục " + folderName.trim() + " của bạn đã bị vô hiệu hóa");

            // dispatch message qua cho client -> update lại FolderUserPermission = 'unlock' folder con của client
            handleUpdateDataClientThread(email, "update_folder_child_user_permission", result);
            tblFolderOwnOfUserModel.setRowCount(0);
            loadDataUserPermissionModel();
            Message("Vô hiệu hóa quyền user thành công.!!!");
        }

    }//GEN-LAST:event_btnUserSettingLockUpload3ActionPerformed

    private void btnUserSettingAllowUpload3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSettingAllowUpload3ActionPerformed
        int selectedRow = tblFolderOwnOfUser.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn thư mục mà bạn muốn\nkích hoạt quyền user của thư mục.!!!");
        } else {
            FolderBLL folderBLL = new FolderBLL();
            String folderId = tblFolderOwnOfUser.getValueAt(selectedRow, 0).toString();
            String folderName = tblFolderOwnOfUser.getValueAt(selectedRow, 1).toString();
            String email = tblFolderOwnOfUser.getValueAt(selectedRow, 2).toString();

            // tiến hành update giá trị FolderUserPermission = 'unlock' cho folder con của user:
            // + update trên Table
            tblFolderOwnOfUserModel.setValueAt("Cho phép", selectedRow, 3);
            
            // + update folder đc chọn trên database
            folderBLL.UpdateFolderUserPermission(folderId, "unlock");

            // + lấy ra các folder con bên trong folder đc chọn update
            List<Folders> listFolderChild = new FolderServices().FindListChildFolder(folderId);
            if (!listFolderChild.isEmpty()) {
                List<Folders> folderGrandChildren = folderBLL.getFolderGrandChildren(listFolderChild);
                if (!folderGrandChildren.isEmpty()) {
                    listFolderChild.addAll(folderGrandChildren);
                }

                for (Folders folder : listFolderChild) {
                    // update các folder con bên trong
                    folderBLL.UpdateFolderUserPermission(folder.getFolderId(), "unlock");
                }
            }

            UpdateResultFolderUserPermission result
                    = new UpdateResultFolderUserPermission(folderId.trim(), listFolderChild, "unlock", "Giờ đây bạn đã có thể toàn quyền truy cập vào thư mục " + folderName.trim());

            // dispatch message qua cho client -> update lại FolderUserPermission = 'unlock' folder con của client
            handleUpdateDataClientThread(email, "update_folder_child_user_permission", result);
            tblFolderOwnOfUserModel.setRowCount(0);
            loadDataUserPermissionModel();
            Message("Kích hoạt quyền user thành công.!!!");
        }
    }//GEN-LAST:event_btnUserSettingAllowUpload3ActionPerformed

    private void btnUpdateCapacityUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCapacityUpload1ActionPerformed
        int selectedRow = tblUserSetting.getSelectedRow();
        if (selectedRow == -1) {
            Message("Vui lòng chọn user cần cấu hình duong lượng lưu trữ.!!!");
        } else {
            String email = tblUserSetting.getValueAt(selectedRow, 0).toString();
            long value = (long) jSpinner3.getValue();
            Folders folder = new FolderBLL().getFolderIdByEmail(email);
            if (folder != null) {
                new FolderBLL().UpdateFolderSize(folder, value);
                loadDataUserSettingModel();
                // bắn socket message qua client
                handleUpdateDataClientThread(email, "update_folder_size_user", String.valueOf(value));
                Message("Cập nhật thành công.!!!");
            }
        }
    }//GEN-LAST:event_btnUpdateCapacityUpload1ActionPerformed

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Gửi data update qua cho user đc chỉ định email trước">
    private void handleUpdateDataClientThread(String email, String message, Object object) {
        if (Server.getMembersOnline() != null) {     // kiểm tra khác null
            for (MembersOnline member : Server.getMembersOnline()) {
                if (member.getUsers().getEmail().trim().equals(email.trim())) {
                    member.getListenThread().response(message, object);
//                    break;
                }
            }
        }
    }
    //</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Gửi data update qua cho client so sánh theo port">
    private void handleUpdateDataClientThreadWithPort(String port, String message, Object object) {
        for (ListenThread client : listenThread) {
            if (String.valueOf(client.getSocket().getPort()).equals(port.trim())) {
                client.response(message, object);
                break;
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
    private javax.swing.JButton btnUpdateCapacityUpload;
    private javax.swing.JButton btnUpdateCapacityUpload1;
    private javax.swing.JButton btnUserSettingAllowDownload;
    private javax.swing.JButton btnUserSettingAllowUpload;
    private javax.swing.JButton btnUserSettingAllowUpload1;
    private javax.swing.JButton btnUserSettingAllowUpload2;
    private javax.swing.JButton btnUserSettingAllowUpload3;
    private javax.swing.JButton btnUserSettingLockDownload;
    private javax.swing.JButton btnUserSettingLockUpload;
    private javax.swing.JButton btnUserSettingLockUpload1;
    private javax.swing.JButton btnUserSettingLockUpload2;
    private javax.swing.JButton btnUserSettingLockUpload3;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JPanel pnlAnonymousSettings;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSection;
    private javax.swing.JPanel pnlUserPermissions;
    private javax.swing.JPanel pnlUserSettings;
    private javax.swing.JPanel pnlViewUserOnline;
    private javax.swing.JTable tblAllUserAnonymousSettings;
    private javax.swing.JTable tblAllUserOfUserPermission;
    private javax.swing.JTable tblAllUserView;
    private javax.swing.JTable tblClientConnectAnonymousSettings;
    private javax.swing.JTable tblFolderOwnOfUser;
    private javax.swing.JTable tblUserOnlineView;
    private javax.swing.JTable tblUserSetting;
    // End of variables declaration//GEN-END:variables
}
