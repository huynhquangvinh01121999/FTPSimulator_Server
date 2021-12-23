package components;

import java.io.IOException;
import java.util.UUID;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import models.MembersOnline;

public class Server {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="tạo thread pool server giới hạn thread">
//    public final static int NUM_OF_THREAD = 4;
//    public final static int SERVER_PORT = 42000;

//    public static void main(String[] args) throws IOException {
//        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);
//
//        ServerSocket serverSocket = null;
//
//        try {
//            serverSocket = new ServerSocket(SERVER_PORT);
//            System.out.println("Server started: " + serverSocket + ". Waiting for a client ...");
//            while (true) {
//                try {
//                    Socket socket = serverSocket.accept();
//                    String id = UUID.randomUUID().toString();
//                    ListenThread handler = new ListenThread(socket, id);
//                    executor.execute(handler);
//                } catch (IOException e) {
//                    System.err.println(" Connection Error: " + e);
//                }
//            }
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        } finally {
//            if (serverSocket != null) {
//                serverSocket.close();
//            }
//        }
//    }
    // </editor-fold>
    
    private static ExecutorService executor;
    private static ServerSocket serverSocket;
    private static int port;
    private static final ArrayList<ListenThread> list_Client = new ArrayList<>();
    private static final ArrayList<MembersOnline> list_members = new ArrayList<>();

    public static void setPort(int port) {
        Server.port = port;
    }

    public static void Start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is waiting for client on port " + port
                    + "...");

            executor = Executors.newFixedThreadPool(100);    // giới hạn 10 client
            executor.execute(new ServerUI());
            
            listenClientConnect();
        } catch (IOException ex) {
            System.err.println("Server socket xảy ra lỗi khi khởi tạo" + ex);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    System.err.println("Server socket xảy ra lỗi khi cố gắng close " + ex);
                }
            }
        }
    }

    private static void listenClientConnect() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                String id = UUID.randomUUID().toString();
                ListenThread client = new ListenThread(socket, id);
                executor.execute(client);
            } catch (IOException ex) {
                System.err.println(" Connection Error: " + ex);
            }
        }
    }
//------------------------------------------------------------------------

//------------------- DANH SÁCH CLIENT CONNECT TỚI SERVER ----------------
    public static void registerClient(ListenThread listenThread) {
        list_Client.add(listenThread);
        System.out.println("Total client connect: " + list_Client.size());
    }

    public static void removeClientDisconnect(ListenThread listenThread) {
        list_Client.remove(listenThread);
        System.out.println("Total client connect: " + list_Client.size());
    }

    public static ArrayList<ListenThread> getListClient() {
        return list_Client;
    }
//------------------------------------------------------------------------

//------------------- DANH SÁCH USER THÀNH VIÊN ONLINE -------------------
    public static void registerMemberOnline(MembersOnline member) {
        list_members.add(member);
        System.out.println("Total user online: " + list_members.size());
    }

    public static void removeMemberDisconnect(MembersOnline member) {
        list_members.remove(member);
        System.out.println("Total user online: " + list_members.size());
    }

    public static ArrayList<MembersOnline> getMembersOnline() {
        return list_members;
    }
//------------------------------------------------------------------------

    public static void main(String[] args) {
        Server.setPort(42000);
        Server.Start();
    }
}
