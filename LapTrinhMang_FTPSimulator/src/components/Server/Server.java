package components.Server;

import handlers.Handler;
import java.io.IOException;
import java.util.UUID;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MembersOnline;
import models.Users;

public class Server {

    private static ExecutorService executor;
    private static ServerSocket serverSocket;
    private static int port;
    private static final ArrayList<ListenThread> list_Client = new ArrayList<>();
    private static final ArrayList<MembersOnline> list_members = new ArrayList<>();
    //    private static Socket socket;
    //    private Thread serverThread;

    public static void setPort(int port) {
        Server.port = port;
    }

    public static void Start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is waiting for client on port " + port
                    + "...");

            executor = Executors.newCachedThreadPool();
            executor.execute(new ServerUI());
            listenClientConnect();
        } catch (IOException ex) {
            Logger.getLogger(ServerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void listenClientConnect() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                String id = UUID.randomUUID().toString();
                ListenThread client = new ListenThread(socket, id);
                client.registerThread(new Handler());
                executor.execute(client);
//                list_Client.add(client);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//------------------------------------------------------------------------
    public static ArrayList<ListenThread> getListClient() {
        return list_Client;
    }

    public static void removeClientDisconnect(ListenThread listenThread) {
        list_Client.remove(listenThread);
    }
//------------------------------------------------------------------------

//------------------------------------------------------------------------
    public static void registerMemberOnline(MembersOnline member) {
        list_members.add(member);
    }

    public static void removeMemberDisconnect(MembersOnline member) {
        list_members.remove(member);
        System.out.println(list_members.size());
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
