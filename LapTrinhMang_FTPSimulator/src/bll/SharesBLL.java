package bll;

import bll.helpers.DateHelper;
import dal.Services.FileShareServices;
import dal.Services.FolderShareServices;
import dal.Services.FileServices;
import dal.Services.FolderServices;
import java.util.ArrayList;
import java.util.List;
import models.DataShare;
import models.FileShares;
import models.Files;
import models.FolderShares;
import models.Folders;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class SharesBLL {

    private final FileShareServices fileShareServices = new FileShareServices();
    private final FolderShareServices folderShareServices = new FolderShareServices();
    private FileServices fileServices = new FileServices();

    public SharesBLL() {
    }

    public void shareFiles(DataShare data) {
        // check file 
        for (String fileId : data.getListIdFileShare()) {
            for (String toEmail : data.getListUserShare()) {
                if (!toEmail.trim().equals(data.getFromEmail().trim())) {
                    boolean checkFileShareExist = fileShareServices.checkFileShare(fileId, data.getFromEmail(), toEmail);

                    FileShares fileShares = new FileShares(fileId, data.getFromEmail(),
                            toEmail, data.getPermissionId(), DateHelper.Now());
                    if (checkFileShareExist) {  // đã tồn tại
                        fileShareServices.Update(fileShares);
                    } else {
                        fileShareServices.Create(fileShares);
                    }
                }
            }
        }
    }

    public void shareFolder(DataShare data) {
        // check folder đã dc chia sẻ với ai chưa
        // nếu rồi thì update <-> chưa thì thêm mới
        for (String toEmail : data.getListUserShare()) {
            if (!toEmail.trim().equals(data.getFromEmail().trim())) {
                boolean checkFolderShareExist = folderShareServices
                        .checkFolderShare(data.getIdFolderShare(), data.getFromEmail(), toEmail);

                FolderShares folderShares = new FolderShares(data.getIdFolderShare(), data.getFromEmail(),
                        toEmail, data.getPermissionId(), DateHelper.Now());
                if (checkFolderShareExist) {  // đã tồn tại
                    folderShareServices.Update(folderShares);
                } else {
                    folderShareServices.Create(folderShares);
                }
            }
        }

        // chia sẻ toàn bộ file của folder đó cho user đc thêm
        // kiểm tra file đó đc chia sẻ cho user đó rồi chưa
        // + nếu rồi thì update, chưa thì add
        List<Files> listFileInFolder = fileServices.GetListFileByFolderId(data.getIdFolderShare());
        for (String toEmail : data.getListUserShare()) {
            for (Files file : listFileInFolder) {
                boolean checkFileShareExist = fileShareServices.checkFileShare(file.getFileId(), data.getFromEmail(), toEmail);

                FileShares fileShares = new FileShares(file.getFileId(), data.getFromEmail(),
                        toEmail, data.getPermissionId(), DateHelper.Now());
                if (checkFileShareExist) {  // đã tồn tại
                    fileShareServices.Update(fileShares);
                } else {
                    fileShareServices.Create(fileShares);
                }
            }
        }
    }

    private List<Folders> getAllFolderChild(String folderId) {

        // lấy ra danh sách các folder con cháu bên trong của đc share
        List<Folders> listChild = new FolderServices().FindListChildFolder(folderId);
        List<Folders> folderGrandChildren = new FolderBLL().getFolderGrandChildren(listChild);
        if (!folderGrandChildren.isEmpty()) {
            listChild.addAll(folderGrandChildren);
        }
        return listChild;
    }

    public void shareFolder_Dequy(DataShare data) {
        String idFolderShare = data.getIdFolderShare();
        String fromEmail = data.getFromEmail().trim();
        String permission = data.getPermissionId().trim();

        // thao tác trên folder bậc 1
        // check folder bậc 1 đã dc chia sẻ với ai chưa
        // nếu rồi thì update <-> chưa thì thêm mới
        for (String toEmail : data.getListUserShare()) {

            // so sánh email đc share có trùng vs email người share ko???
            if (!toEmail.trim().equals(fromEmail)) {

                // kiểm tra folder đc share chưa???
                boolean checkFolderShareExist = folderShareServices
                        .checkFolderShare(idFolderShare, fromEmail, toEmail);

                FolderShares folderShares = new FolderShares(idFolderShare, fromEmail,
                        toEmail, permission, DateHelper.Now());

                if (checkFolderShareExist) {  // đã đc chia sẻ rồi
                    folderShareServices.Update(folderShares);
                } else {
                    folderShareServices.Create(folderShares);
                }
            }
        }

        // chia sẻ toàn bộ file của folder đó cho user đc thêm
        // kiểm tra file đó đc chia sẻ cho user đó rồi chưa
        // + nếu rồi thì update, chưa thì add
        List<Files> listFileInFolder = fileServices.GetListFileByFolderId(idFolderShare);
        for (String toEmail : data.getListUserShare()) {

            // so sánh email đc share có trùng vs email người share ko???
            if (!toEmail.trim().equals(fromEmail.trim())) {
                for (Files file : listFileInFolder) {

                    boolean checkFileShareExist = fileShareServices.checkFileShare(file.getFileId(), fromEmail, toEmail);

                    FileShares fileShares = new FileShares(file.getFileId(), fromEmail,
                            toEmail, permission, DateHelper.Now());
                    if (checkFileShareExist) {  // đã tồn tại
                        fileShareServices.Update(fileShares);
                    } else {
                        fileShareServices.Create(fileShares);
                    }
                }
            }
        }

        // kiểm tra và share các folder con cháu
        // lấy ra các folder con cháu của folder bậc 1 đc chia sẻ
        for (Folders folder : getAllFolderChild(idFolderShare)) {
            for (String toEmail : data.getListUserShare()) {

                // so sánh email đc share có trùng vs email người share ko???
                if (!toEmail.trim().equals(fromEmail.trim())) {

                    // kiểm tra folder con này đc share chưa???
                    boolean checkFolderShareExist = folderShareServices
                            .checkFolderShare(folder.getFolderId(), fromEmail, toEmail);

                    FolderShares folderShares = new FolderShares(folder.getFolderId(), fromEmail,
                            toEmail, permission, DateHelper.Now());

                    if (checkFolderShareExist) {  // đã đc chia sẻ rồi
                        folderShareServices.Update(folderShares);
                    } else {
                        folderShareServices.Create(folderShares);
                    }
                }
            }

            // chia sẻ toàn bộ file của folder đó cho user đc thêm
            // kiểm tra file đó đc chia sẻ cho user đó rồi chưa
            // + nếu rồi thì update, chưa thì add
            for (String toEmail : data.getListUserShare()) {
                
                // so sánh email đc share có trùng vs email người share ko???
                if (!toEmail.trim().equals(fromEmail)) {
                    for (Files file : fileServices.GetListFileByFolderId(folder.getFolderId())) {

                        boolean checkFileShareExist = fileShareServices.checkFileShare(file.getFileId(), fromEmail, toEmail);

                        FileShares fileShares = new FileShares(file.getFileId(), fromEmail,
                                toEmail, permission, DateHelper.Now());
                        if (checkFileShareExist) {  // đã tồn tại
                            fileShareServices.Update(fileShares);
                        } else {
                            fileShareServices.Create(fileShares);
                        }
                    }
                }
            }
        }
    }
}
