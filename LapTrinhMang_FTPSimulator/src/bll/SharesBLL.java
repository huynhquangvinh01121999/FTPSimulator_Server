package bll;

import bll.helpers.DateHelper;
import dal.Services.FileShareServices;
import dal.Services.FolderShareServices;
import models.DataShare;
import models.FileShares;
import models.FolderShares;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class SharesBLL {

    private final FileShareServices fileShareServices = new FileShareServices();
    private final FolderShareServices folderShareServices = new FolderShareServices();

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
                boolean checkFolderShareExist = folderShareServices.checkFolderShare(data.getIdFolderShare(), data.getFromEmail(), toEmail);

                FolderShares folderShares = new FolderShares(data.getIdFolderShare(), data.getFromEmail(),
                        toEmail, data.getPermissionId(), DateHelper.Now());
                if (checkFolderShareExist) {  // đã tồn tại
                    folderShareServices.Update(folderShares);
                } else {
                    folderShareServices.Create(folderShares);
                }
            }
        }
    }
}
