package components.Server;

import handlers.HandleBase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.FileDownloadInfo;
import models.FileEvent;
import models.HandleResult;
import models.MembersOnline;
import models.ObjectRequest;
import models.Users;

public class ListenThread implements Runnable {

    private Socket clientSocket;
    private String socketId;
    private BufferedWriter out;
    private BufferedReader in;
    private boolean isDisconnect = false;

    private static ObjectInputStream objInputStream;
    private static ObjectOutputStream objOutputStream;
    private File dstFile;
    private FileOutputStream fileOutputStream;
    private static HandleBase handlerBase;
    private static MembersOnline member;

    public ListenThread(Socket clientSocket, String id) {
        try {
            this.clientSocket = clientSocket;
            this.socketId = id;
            out = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            objOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            objInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ListenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return clientSocket;
    }

    public String getSocketId() {
        return socketId;
    }

    public static MembersOnline getMember() {
        return member;
    }

    public static void setMember(MembersOnline member) {
        ListenThread.member = member;
    }

    public static ArrayList<ListenThread> getListClient() {
        return Server.getListClient();
    }

    // <editor-fold defaultstate="collapsed" desc="Xử lý gửi - nhận file phía Server (không dùng trong đây)">
    public void saveFile() {
        try {
            // Bước 1: đọc đối tượng truyền từ client qua
            FileEvent fileEvent = (FileEvent) objInputStream.readObject();

            // Bước 2: Kiểm tra trạng thái có bị "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // Nếu có: Thông báo lỗi => Chấm dứt chương trình
                System.err.println("Xảy ra lỗi..");
            }

            // OK => 
            // kiểm tra đường dẫn đích tồn tại chưa
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                // Nếu chưa => tạo mới đường dẫn
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

            // tạo file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            dstFile = new File(outputFile);

            // FileOutputStream dùng để ghi dữ liệu vào file theo định dạng byte
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());    // bđ ghi vô luồng
            fileOutputStream.flush();   // đẩy luồng ghi vô file
            fileOutputStream.close();   // đóng quá trình ghi sau khi hoàn tất

            System.out.println("Output file : " + outputFile + " được ghi thành công. ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String sourceFilePath, String destinationPath) {

        // tạo đối tượng FileEvent
        FileEvent fileEvent = new FileEvent();

        // lấy ra tên file
        String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());

        fileEvent.setFilename(fileName);
        fileEvent.setSourceDirectory(sourceFilePath);
        fileEvent.setDestinationDirectory(destinationPath);

        File file = new File(sourceFilePath);
        if (file.isFile()) {
            try {
                // DataInputStream đọc dữ liệu nguyên thủy từ luồng đầu vào ( ở đây là file đầu vào)
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
            System.out.println("Không tìm thấy tệp ở vị trí đã chỉ định.");
            fileEvent.setStatus("Error");
        }

        //Ghi đối tượng tệp vào thư mục
        try {
            objOutputStream.writeObject(fileEvent);
            System.out.println("Đang gửi file...");
            Thread.sleep(3000);
            System.out.println("Hoàn tất!");
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

            // Bước 2: Kiểm tra trạng thái có bị "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // Nếu có: Thông báo lỗi => Chấm dứt chương trình
                System.err.println("Xảy ra lỗi..");
            }

            // OK => 
            // kiểm tra đường dẫn đích tồn tại chưa
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                // Nếu chưa => tạo mới đường dẫn
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

            // tạo file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            dstFile = new File(outputFile);

            // FileOutputStream dùng để ghi dữ liệu vào file theo định dạng byte
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());    // bđ ghi vô luồng
            fileOutputStream.flush();   // đẩy luồng ghi vô file
            fileOutputStream.close();   // đóng quá trình ghi sau khi hoàn tất

            System.out.println("Output file : " + outputFile + " được ghi thành công. ");

        } catch (IOException ex) {
            System.err.println("Lỗi khi lưu file");
        }
    }

    public void responseDownloadFile(String message, FileDownloadInfo fileDownloadInfo) {
        File file = new File(fileDownloadInfo.getSourceFilePath() + "/" + fileDownloadInfo.getFileName());

        // tạo đối tượng FileEvent
        FileEvent fileEvent = new FileEvent();

        fileEvent.setFilename(fileDownloadInfo.getFileName());
        fileEvent.setDestinationDirectory(fileDownloadInfo.getDestinationPath());

        if (file.isFile()) {
            try {
                // DataInputStream đọc dữ liệu nguyên thủy từ luồng đầu vào ( ở đây là file đầu vào)
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
                System.out.println("Download file xuống thành công");
            } catch (IOException ex) {
                System.err.println("Download file xuống lên thất bại" + ex);
                fileEvent.setStatus("Error");
            }
        } else {
            System.out.println("Không tìm thấy tệp ở vị trí đã chỉ định.");
            fileEvent.setStatus("Error");
        }
        try {
            objOutputStream.writeObject(new ObjectRequest(message, fileEvent));
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

//    private void accept_disconnect() {
//        isDisconnect = true;
//        sendMessage("ACCCEP_DISCONNECT");
//    }
    private void accept_disconnect() {
        try {
            isDisconnect = true;
            objOutputStream.writeObject(new ObjectRequest("ACCCEP_DISCONNECT"));
        } catch (IOException ex) {
            System.err.println("Server xảy ra lỗi IOException khi chấp nhận client DISCONNECT - " + ex);
        }
    }

//    public String getMessage() {
//        String message;
//        try {
//            message = in.readLine().replace("\n", "").replace("\r", "");
//            if (String.valueOf(message.charAt(0)).equalsIgnoreCase("y")) {
//                message = message.substring(1, message.length());
//            }
//        } catch (IOException ex) {
//            message = "DISCONNECT";
//        }
//        return message;
//    }
    public void registerThread(HandleBase handler) {
        handler.setListenThread(this);
        ListenThread.handlerBase = handler;
    }

//------------------------------------------------------------------------
    public void registerMemberOnline(Users user) {
        MembersOnline member = new MembersOnline(this, user);
        setMember(member);
        Server.registerMemberOnline(member);
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
//                client.sendMessage("chào client " + id);
//            }
//        }
//    }
    public void notification(String email) {
        for (MembersOnline member : getMembersOnline()) {
            if (member.getUsers().getEmail().trim().equals(email.trim())) {
                member.getListenThread().response("response_notification", "Chào bạn");
                break;
            }
        }
    }

//    public Users getObjectUser() throws IOException, ClassNotFoundException {
//        Users user = (Users) objInputStream.readObject();
//        return user;
//    }
//    public Folders getObjectFolder() throws IOException, ClassNotFoundException {
//        Folders folder = (Folders) objInputStream.readObject();
//        return folder;
//    }
    // bắn message + Object
    public void response(String message, Object object) {
        try {
            objOutputStream.writeObject(new ObjectRequest(message, object));
            objOutputStream.reset();
        } catch (IOException ex) {
            System.err.println("Server xảy ra lỗi function response IOException - " + ex);
        }
    }

    // Gửi chỉ object HandleResult đơn luồng
    public void responseHandleResult(HandleResult result) {
        try {
            objOutputStream.writeObject(result);
            objOutputStream.reset();
        } catch (IOException ex) {
            System.err.println("Server xảy ra lỗi function responseHandleResult IOException - " + ex);
        }
    }

    @Override
    public void run() {
        System.out.println("Một client id: " + getSocketId() + " vừa kết nối - [Thông tin] " + this.clientSocket);
        try {
            while (!isDisconnect) {

//                String message = getMessage();
                try {
                    ObjectRequest request;
                    synchronized (objInputStream) {
                        request = (ObjectRequest) objInputStream.readObject();
                    }
                    if (request.getMessage().toUpperCase().equals("DISCONNECT")) {
                        System.err.println("Client with port " + clientSocket.getPort() + " with disconnect");
                        accept_disconnect();
                        Thread.sleep(3000);
                        removeMemberDisconnect(getMember());
//                        Server.removeClientDisconnect(this);
                    }
                    handlerBase.handleRequest(request);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ListenThread.class.getName()).log(Level.SEVERE, null, ex);
                }
//                if (message.toUpperCase().equals("DISCONNECT")) {
//                    System.err.println("Client with port " + clientSocket.getPort() + " with disconnect");
//                    accept_disconnect();
//                    Thread.sleep(3000);
//                    Server.removeClientDisconnect(this);
//                }
//                handlerBase.handleRequest(message);
            }
            in.close();
            out.close();
            clientSocket.close();
        } catch (InterruptedException ex) {
            System.err.println("Server error InterruptedException - " + ex);
        } catch (EOFException ex) {
            System.err.println("Server error EOFException - " + ex);
        } catch (IOException ex) {
            System.err.println("Server error IOException - " + ex);
        }
    }
}
