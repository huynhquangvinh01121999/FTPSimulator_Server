package components;

import bll.FileBLL;
import bll.FolderBLL;
import bll.SharesBLL;
import bll.UserBLL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import middlewares.HandleVerify;
import models.DataShare;
import models.FileDownloadInfo;
import models.FileEvent;
import models.Files;
import models.Folders;
import models.HandleResult;
import models.MembersOnline;
import models.ObjectRequest;
import models.Users;

public class ListenThread extends Thread {

    private Socket clientSocket;
    private String socketId;
    private boolean ANONYMOUS_PERMISSION = true;
    private BufferedWriter out;
    private BufferedReader in;
    private boolean isDisconnect = false;

    private ObjectInputStream objInputStream;
    private ObjectOutputStream objOutputStream;
    private File dstFile;
    private FileOutputStream fileOutputStream;
    private MembersOnline member;
    private ObjectRequest request;

    public ListenThread(Socket clientSocket, String id) {
        this.clientSocket = clientSocket;
        this.socketId = id;
    }

    public Socket getSocket() {
        return clientSocket;
    }

    public String getSocketId() {
        return socketId;
    }

    public boolean isANONYMOUS_PERMISSION() {
        return ANONYMOUS_PERMISSION;
    }

    public void setANONYMOUS_PERMISSION(boolean ANONYMOUS_PERMISSION) {
        this.ANONYMOUS_PERMISSION = ANONYMOUS_PERMISSION;
    }

    public MembersOnline getMember() {
        return member;
    }

    public void setMember(MembersOnline memberInfo) {
        member = memberInfo;
    }

    public static ArrayList<ListenThread> getListClient() {
        return Server.getListClient();
    }

    // <editor-fold defaultstate="collapsed" desc="X??? l?? g???i - nh???n file ph??a Server (kh??ng d??ng trong ????y)">
    public void saveFile() {
        try {
            // B?????c 1: ?????c ?????i t?????ng truy???n t??? client qua
            FileEvent fileEvent = (FileEvent) objInputStream.readObject();

            // B?????c 2: Ki???m tra tr???ng th??i c?? b??? "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // N???u c??: Th??ng b??o l???i => Ch???m d???t ch????ng tr??nh
                System.err.println("X???y ra l???i..");
            }

            // OK => 
            // ki???m tra ???????ng d???n ????ch t???n t???i ch??a
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                // N???u ch??a => t???o m???i ???????ng d???n
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

            // t???o file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            dstFile = new File(outputFile);

            // FileOutputStream d??ng ????? ghi d??? li???u v??o file theo ?????nh d???ng byte
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());    // b?? ghi v?? lu???ng
            fileOutputStream.flush();   // ?????y lu???ng ghi v?? file
            fileOutputStream.close();   // ????ng qu?? tr??nh ghi sau khi ho??n t???t

            System.out.println("Output file : " + outputFile + " ???????c ghi th??nh c??ng. ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String sourceFilePath, String destinationPath) {

        // t???o ?????i t?????ng FileEvent
        FileEvent fileEvent = new FileEvent();

        // l???y ra t??n file
        String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());

        fileEvent.setFilename(fileName);
        fileEvent.setSourceDirectory(sourceFilePath);
        fileEvent.setDestinationDirectory(destinationPath);

        File file = new File(sourceFilePath);
        if (file.isFile()) {
            try {
                // DataInputStream ?????c d??? li???u nguy??n th???y t??? lu???ng ?????u v??o ( ??? ????y l?? file ?????u v??o)
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }

                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
            } catch (Exception e) {
                e.printStackTrace();
                fileEvent.setStatus("Error");
            }
        } else {
            System.out.println("Kh??ng t??m th???y t???p ??? v??? tr?? ???? ch??? ?????nh.");
            fileEvent.setStatus("Error");
        }

        //Ghi ?????i t?????ng t???p v??o th?? m???c
        try {
            objOutputStream.writeObject(fileEvent);
            System.out.println("??ang g???i file...");
            Thread.sleep(3000);
            System.out.println("Ho??n t???t!");
            objOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // </editor-fold> 
//    public Files FileSaver() throws IOException, ClassNotFoundException {
//        new FileHandler().ServerSaver(objInputStream);
//
//        Files file = (Files) objInputStream.readObject();
//        return file;
//    }
    public void saveFile(FileEvent fileEvent) {
        try {

            // B?????c 2: Ki???m tra tr???ng th??i c?? b??? "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // N???u c??: Th??ng b??o l???i => Ch???m d???t ch????ng tr??nh
                System.err.println("X???y ra l???i..");
            }

            // OK => 
            // ki???m tra ???????ng d???n ????ch t???n t???i ch??a
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                // N???u ch??a => t???o m???i ???????ng d???n
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

            // t???o file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            dstFile = new File(outputFile);

            // FileOutputStream d??ng ????? ghi d??? li???u v??o file theo ?????nh d???ng byte
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());    // b?? ghi v?? lu???ng
            fileOutputStream.flush();   // ?????y lu???ng ghi v?? file
            fileOutputStream.close();   // ????ng qu?? tr??nh ghi sau khi ho??n t???t

            System.out.println("Output file : " + outputFile + " ???????c ghi th??nh c??ng. ");

        } catch (IOException ex) {
            System.err.println("L???i khi l??u file");
        }
    }

    public void responseDownloadFile(String message, FileDownloadInfo fileDownloadInfo) {
        File file = new File(fileDownloadInfo.getSourceFilePath() + "/" + fileDownloadInfo.getFileName());

        // t???o ?????i t?????ng FileEvent
        FileEvent fileEvent = new FileEvent();

        fileEvent.setFilename(fileDownloadInfo.getFileName());
        fileEvent.setDestinationDirectory(fileDownloadInfo.getDestinationPath());

        if (file.isFile()) {
            try {
                // DataInputStream ?????c d??? li???u nguy??n th???y t??? lu???ng ?????u v??o ( ??? ????y l?? file ?????u v??o)
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }

                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
                System.out.println("Download file xu???ng th??nh c??ng");
            } catch (IOException ex) {
                System.err.println("Download file xu???ng l??n th???t b???i" + ex);
                fileEvent.setStatus("Error");
            }
        } else {
            System.out.println("Kh??ng t??m th???y t???p ??? v??? tr?? ???? ch??? ?????nh.");
            fileEvent.setStatus("Error");
        }
        try {
            objOutputStream.writeObject(new ObjectRequest(message, fileEvent));
            objOutputStream.flush();
            objOutputStream.reset();
        } catch (IOException ex) {
            Logger.getLogger(ListenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ListenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void accept_disconnect() {
        try {
            isDisconnect = true;
            objOutputStream.writeObject(new ObjectRequest("ACCCEP_DISCONNECT"));
        } catch (IOException ex) {
            System.err.println("Server x???y ra l???i IOException khi ch???p nh???n client DISCONNECT - " + ex);
        }
    }

    public String getMessage() {
        String message;
        try {
            message = in.readLine().replace("\n", "").replace("\r", "");
            if (String.valueOf(message.charAt(0)).equalsIgnoreCase("y")) {
                message = message.substring(1, message.length());
            }
        } catch (IOException ex) {
            System.err.println("l???i ??? ????y getMessage - " + ex);
            message = "DISCONNECT";
        }
        return message;
    }

//------------------------------------------------------------------------
    public void registerMemberOnline(Users user) {
        MembersOnline memberInfo = new MembersOnline(this, user);
        setMember(memberInfo);
        Server.registerMemberOnline(memberInfo);
    }

    public void removeMemberDisconnect(MembersOnline member) {
        Server.removeMemberDisconnect(member);
    }

    public ArrayList<MembersOnline> getMembersOnline() {
        return Server.getMembersOnline();
    }
//------------------------------------------------------------------------

//    public void notification(String id) {
//        for (ListenThread client : getListClient()) {
//            if (client.getSocketId().equals(id)) {
//                client.sendMessage("ch??o client " + id);
//            }
//        }
//    }
    public void notification(String email) {
        for (MembersOnline member : getMembersOnline()) {
            if (member.getUsers().getEmail().trim().equals(email.trim())) {
                member.getListenThread().response("response_notification", "Ch??o b???n");
                break;
            }
        }
    }

    public void notificationShared(List<String> listEmailShared, String message) {
        for (String emailShared : listEmailShared) {
            for (MembersOnline member : getMembersOnline()) {
                if (member.getUsers().getEmail().trim().equals(emailShared.trim())) {
                    member.getListenThread().response("notifi_shareddata", message);
                }
            }
        }

    }

    public void response(String data) {
        try {
            out.write(data);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            System.err.println("Server x???y ra l???i function response IOException - " + ex);
        }
    }

    // b???n message + Object
    public void response(String message, Object object) {
        try {
            objOutputStream.writeObject(new ObjectRequest(message, object));
            objOutputStream.flush();
        } catch (IOException ex) {
            System.err.println("Server x???y ra l???i function response IOException - " + ex);
        }
    }

    // G???i ch??? object HandleResult ????n lu???ng
    public void responseHandleResult(HandleResult result) {
        try {
            objOutputStream.writeObject(result);
            objOutputStream.flush();
            objOutputStream.reset();
        } catch (IOException ex) {
            System.err.println("Server x???y ra l???i function responseHandleResult IOException - " + ex);
        }
    }

    @Override
    public void run() {
        System.out.println("M???t client id: " + getSocketId() + " v???a k???t n???i - [Th??ng tin] " + this.clientSocket);
        try {
            out = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            objOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            objInputStream = new ObjectInputStream(this.clientSocket.getInputStream());

            // add client connect v?? danh s??ch
            Server.registerClient(this);

            // reload l???i Server UI khi client connect
            ServerUI.reloadClientConnect(this);

            while (!isDisconnect) {

                try {

                    // ?????ng b??? read object
                    synchronized (objInputStream) {
                        request = (ObjectRequest) objInputStream.readObject();
                    }

                    String message = request.getMessage();
                    switch (message.toUpperCase()) {

                        // <editor-fold defaultstate="collapsed" desc="SIGNOUT">
                        case "SIGNOUT": {
                            // X??a user ???? offline kh???i ds user ??ang online
                            removeMemberDisconnect(getMember());
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="DISCONNECT">
                        case "DISCONNECT": {
                            System.err.println("Client with port " + clientSocket.getPort() + " with disconnect");
                            accept_disconnect();
                            Thread.sleep(3000);

                            try {
                                // X??a user ???? offline kh???i ds user ??ang online
                                removeMemberDisconnect(getMember());
                            } catch (Exception ex) {
                            }

                            // X??a client b??? ????ng k???t n???i kh???i ds client ??ang connect
                            Server.removeClientDisconnect(this);

                            // reload l???i Server UI khi client disconnect
                            ServerUI.reloadClientDisconnect(this);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="DOWNLOAD_FILE">
                        case "DOWNLOAD_FILE": {
                            System.out.println("Client ????i " + message);
                            FileDownloadInfo fileDownloadInfo = (FileDownloadInfo) request.getObject();
                            responseDownloadFile("accept_download_file", fileDownloadInfo);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="UPLOAD_FILE">
                        case "UPLOAD_FILE": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            Files filesInfo = (Files) request.getObject();
                            FileEvent fileEvent = (FileEvent) request.getFileUpload();

                            new FileBLL().insertNewFile(filesInfo);

                            // convert byte sang file -> l???i file l??u tr??n server
                            saveFile(fileEvent);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="UPLOAD_FILE_SHARE">
                        case "UPLOAD_FILE_SHARE": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            Files filesInfo = (Files) request.getObject();
                            FileEvent fileEvent = (FileEvent) request.getFileUpload();
                            new FileBLL().insertNewFileShare(filesInfo);
                            saveFile(fileEvent);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="NOTIFICATION">
                        // b???n th??ng b??o v??? cho client b???ng id or email
                        case "NOTIFICATION": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            String email = (String) request.getObject();
                            notification(email);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="VERIFY_REGISTER">
                        // CASE ????NG K??
                        case "VERIFY_REGISTER": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            Users user = (Users) request.getObject();
                            HandleResult result = HandleVerify.verifyRegisterUser(user);
                            response("response_verify_register", result);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="REGISTER">
                        case "REGISTER": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);

                            Users user = (Users) request.getObject();
                            HandleResult result = new UserBLL().registerUser(user);
                            response("response_register", result);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="AUTHENTICATE">
                        // CASE LOGIN
                        case "AUTHENTICATE": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);

                            Users user = (Users) request.getObject();
                            UserBLL userBLL = new UserBLL();
                            HandleResult result = userBLL.authenticate(user);
                            response("response_authenticate", result);
                            if (result.isSuccessed()) {
                                // user ko b??? lock quy???n anonymous
                                if (result.getUser().getAnonymousPermission().trim().equals("unlock")) {
                                    responseHandleResult(userBLL
                                            .getAuthenData(result.getFolder().getFolderId(), result.getUser().getEmail()));
                                    responseHandleResult(userBLL.getAuthenDataShare(result.getUser().getEmail()));

                                    // khi user login th??nh c??ng -> add user v??o ds user ??ang online
                                    registerMemberOnline(result.getUser());
                                } else {    // user b??? lock quy???n anonymous
                                    responseHandleResult(userBLL
                                            .getAuthenDataLockAnonymous(result.getFolder().getFolderId(), result.getUser().getEmail()));
                                    responseHandleResult(userBLL.getAuthenDataShare(result.getUser().getEmail()));

                                    // khi user login th??nh c??ng -> add user v??o ds user ??ang online
                                    registerMemberOnline(result.getUser());
                                }
                            }
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="AUTHENTICATE_ANONYMOUS_PERMISSION">
                        case "AUTHENTICATE_ANONYMOUS_PERMISSION": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);

                            String anonymousUser = (String) request.getObject();
                            HandleResult result = new UserBLL()
                                    .authenticateWithAnonymousPermission(anonymousUser);
                            response("response_authenticate_anonymous", result);
                            if (result.isSuccessed()) {
                                responseHandleResult(new UserBLL()
                                        .getAuthenDataWithAnonymousPermission());
                            }
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="RELOAD_WITH_AUTHENTICATE">
                        // CASE RELOAD WITH USER AUTHENTICATE
                        case "RELOAD_WITH_AUTHENTICATE": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);

                            Users user = (Users) request.getObject();
                            UserBLL userBLL = new UserBLL();
                            HandleResult result = userBLL.authenticate(user);
                            response("response_authenticate", result);
                            if (result.isSuccessed()) {
                                // user ko b??? lock quy???n anonymous
                                if (result.getUser().getAnonymousPermission().trim().equals("unlock")) {
                                    responseHandleResult(userBLL
                                            .getAuthenData(result.getFolder().getFolderId(), result.getUser().getEmail()));
                                    responseHandleResult(userBLL.getAuthenDataShare(result.getUser().getEmail()));

                                } else {    // user b??? lock quy???n anonymous
                                    responseHandleResult(userBLL
                                            .getAuthenDataLockAnonymous(result.getFolder().getFolderId(), result.getUser().getEmail()));
                                    responseHandleResult(userBLL.getAuthenDataShare(result.getUser().getEmail()));

                                }
                            }
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="RELOAD_WITH_ANONYMOUS">
                        case "RELOAD_WITH_ANONYMOUS": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);

                            String anonymousUser = (String) request.getObject();
                            HandleResult result = new UserBLL()
                                    .authenticateWithAnonymousPermission(anonymousUser);
                            response("response_authenticate_anonymous", result);
                            if (result.isSuccessed()) {
                                responseHandleResult(new UserBLL()
                                        .getAuthenDataWithAnonymousPermission());
                            }
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="NEW_FOLDER">
                        // CASE T???O FOLDER CON M???I
                        case "NEW_FOLDER": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            Folders folder = (Folders) request.getObject();
                            new FolderBLL().generateFolderChild(folder);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="SHARE_FILES">
                        case "SHARE_FILES": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            DataShare dataShare = (DataShare) request.getObject();
                            new SharesBLL().shareFiles(dataShare);
                            String notifiShareFile = "B???n v???a ???????c chia s??? m???t t???p tin t??? email " + dataShare.getFromEmail();
                            notificationShared(dataShare.getListUserShare(), notifiShareFile);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="SHARE_FOLDER">
                        case "SHARE_FOLDER": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            DataShare dataShare = (DataShare) request.getObject();
//                            new SharesBLL().shareFolder(dataShare);
                            new SharesBLL().shareFolder_Dequy(dataShare);
                            String notifiShareFolder = "B???n v???a ???????c chia s??? m???t th?? m???c t??? email " + dataShare.getFromEmail();
                            notificationShared(dataShare.getListUserShare(), notifiShareFolder);
                            break;
                        }
                        // </editor-fold>

                        // <editor-fold defaultstate="collapsed" desc="FETCH_USERS">
                        case "FETCH_USERS": {
                            System.out.println("Client[port " + getSocket().getPort() + "] said: " + message);
                            String authenEmail = (String) request.getObject();
                            List<Users> listUsers = new UserBLL().getAllUser();
                            response("fetch_users_success", new HandleResult(listUsers));
                            break;
                        }
                        // </editor-fold>

                        default: {
                            System.out.println("Client[port " + getSocket().getPort() + "] said default: " + message);
                            response("test", "test");
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ListenThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (InterruptedException ex) {
            System.err.println("Server error InterruptedException - " + ex);
        } catch (IOException ex) {
            System.err.println("Server error IOException - " + ex);
        } finally {
            // X??a client b??? ????ng k???t n???i kh???i ds client ??ang connect
            Server.removeClientDisconnect(this);

            // reload l???i Server UI khi client disconnect
            ServerUI.reloadClientDisconnect(this);
        }
    }
}
