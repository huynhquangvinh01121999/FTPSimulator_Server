package handlers;

import bll.FileBLL;
import bll.FolderBLL;
import bll.UserBLL;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import middlewares.HandleVerify;
import models.FileDownloadInfo;
import models.FileEvent;
import models.Files;
import models.Folders;
import models.HandleResult;
import models.ObjectRequest;
import models.Users;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class Handler extends HandleBase {

    private String sourceFilePath = "/Users/HP/Desktop/Đề tài lập trình mạng.png";
    private String destinationPath = "/Users/HP/Desktop/test1/";

    @Override
    public void handleRequest(ObjectRequest request) {
//        System.out.println(message);
//        String[] command = message.split(";");
//        message = command[0].toString();
        String message = request.getMessage();

        switch (message.toUpperCase()) {
            case "DOWNLOAD_FILE": {
                System.out.println("Client đòi " + message);
                FileDownloadInfo fileDownloadInfo = (FileDownloadInfo) request.getObject();
                listenThread.responseDownloadFile("accept_download_file", fileDownloadInfo);
                break;
            }
            case "UPLOAD_FILE": {
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said: " + message);
                Files filesInfo = (Files) request.getObject();
                FileEvent fileEvent = (FileEvent) request.getFileUpload();
                new FileBLL().insertNewFile(filesInfo);
                listenThread.saveFile(fileEvent);

//                try {
//                    new FileBLL().insertNewFile(listenThread.FileSaver());
//                new FileBLL().insertNewFile((Files) request.getObject());
//                listenThread.saveFile();
//                } catch (IOException ex) {
//                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
            break;

            // bắn thông báo về cho client bằng id or email
            case "NOTIFICATION": {
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said: " + message);
                String email = (String) request.getObject();
                listenThread.notification(email);
                break;
            }

            // CASE ĐĂNG KÝ
            case "VERIFY_REGISTER": {
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said: " + message);
                Users user = (Users) request.getObject();
                HandleResult result = HandleVerify.verifyRegisterUser(user);
                listenThread.response("response_verify_register", result);

//                Users user = listenThread.getObjectUser();
//                HandleResult result = HandleVerify.verifyRegisterUser(user);
//                listenThread.sendMessage("response_verify_register");
//                listenThread.responseHandleResult(result);
                break;
            }
            case "REGISTER": {
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said: " + message);

                Users user = (Users) request.getObject();
                HandleResult result = new UserBLL().registerUser(user);
                listenThread.response("response_register", result);

//                Users user = listenThread.getObjectUser();
//                HandleResult result = new UserBLL().registerUser(user);
//                listenThread.sendMessage("response_register");
//                listenThread.responseHandleResult(result);
                break;
            }

            // CASE LOGIN
            case "AUTHENTICATE": {
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said: " + message);

                try {
                    Users user = (Users) request.getObject();
                    HandleResult result = new UserBLL().authenticate(user);

                    listenThread.response("response_authenticate", result);
                    Thread.sleep(3000);
                    if (result.isSuccessed()) {
                        listenThread.responseHandleResult(new UserBLL()
                                .getAuthenData(result.getFolder().getFolderId(), result.getUser().getEmail()));
                        listenThread.registerMemberOnline(result.getUser());
                        System.out.println(listenThread.getMembersOnline().size());
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

//                try {
//                    Users user = listenThread.getObjectUser();
//                    HandleResult result = new UserBLL().authenticate(user);
//                    listenThread.sendMessage("response_authenticate");
//                    listenThread.responseHandleResult(result);
//                    Thread.sleep(3000);
//                    if (result.isSuccessed()) {
//                        listenThread.responseHandleResult(new UserBLL().getAuthenData(result.getFolder().getFolderId()));
//                    }
//                } catch (IOException | ClassNotFoundException ex) {
//                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
//                }
                break;
            }

            case "AUTHENTICATE_ANONYMOUS_PERMISSION": {
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said: " + message);

                try {
                    String anonymousUser = (String) request.getObject();
                    HandleResult result = new UserBLL()
                            .authenticateWithAnonymousPermission(anonymousUser);

                    listenThread.response("response_authenticate", result);
                    Thread.sleep(3000);
                    if (result.isSuccessed()) {
                        listenThread.responseHandleResult(new UserBLL()
                                .getAuthenDataWithAnonymousPermission());
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            // CASE TẠO FOLDER CON MỚI
            case "NEW_FOLDER": {
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said: " + message);
                Folders folder = (Folders) request.getObject();
                new FolderBLL().generateFolderChild(folder);

//                Folders folder = listenThread.getObjectFolder();
//                new FolderBLL().generateFolderChild(folder);
//                System.out.println("folderParentId: " + folder.getFolderParentId());
//                System.out.println("emailUser: " + folder.getEmail());
//                System.out.println("rootPath: " + folder.getFolderPath());
//                System.out.println("folderChildName: " + folder.getFolderName());
                break;
            }

            default:
                System.out.println("Client[port " + listenThread.getSocket().getPort() + "] said default: " + message);
                break;
        }
    }

}
