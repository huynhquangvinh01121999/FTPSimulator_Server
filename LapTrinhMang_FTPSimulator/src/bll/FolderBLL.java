/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bll.helpers.DateHelper;
import bll.helpers.FileExtensions;
import bll.helpers.ThreadRandoms;
import dal.Services.FolderServices;
import java.util.List;
import models.FolderUserItem;
import models.Folders;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FolderBLL {

    public FolderBLL() {
    }

    public boolean generateFolderChild(Folders folder) {
        if (FileExtensions.generateFolder(folder.getFolderPath())) {
            if (new FolderServices().Create(folder)) {
                return true;
            }
        }
        return false;
    }

    public boolean UpdateFolderUserPermission(String folderId, String permission) {
        return new FolderServices().UpdateFolderUserPermission(folderId, permission);
    }

    public FolderUserItem GetListFolderChildUser(String email) {
        FolderServices folderServices = new FolderServices();
        Folders folderInfo = folderServices.FindFolderParentByEmail(email);
        if (folderInfo != null) {
            List<Folders> listFolderChild = folderServices.FindListChildFolder(folderInfo.getFolderId());
            if (!listFolderChild.isEmpty()) {
                return new FolderUserItem(email, listFolderChild);
            }
        }
        return null;
    }

    public Folders getFolderIdByEmail(String email) {
        return new FolderServices().FindFolderParentByEmail(email);
    }

    public void UpdateFolderSize(Folders folder, long size) {
        FolderServices folderServices = new FolderServices();
        // lấy ra dung lượng lưu trữ cũ của folder
        String oldSize = folder.getSize().replaceAll(",", "");

        // lấy ra kích thước còn lại cũ của folder
        String oldRemanningSize = folder.getRemainingSize().replaceAll(",", "");

        // lấy ra kích thước folder đã sử dụng
        String usedSize = String.valueOf(Long.parseLong(oldSize) - Long.parseLong(oldRemanningSize));

        // cập nhật dung lượng còn lại cho folder của user
        String newRemanningSize = String.valueOf(size - Long.parseLong(usedSize));
        if (Long.parseLong(newRemanningSize) <= 0) {  // nếu âm -> gán = 0 luôn
            folderServices.UpdateRemainingSize("0", folder.getFolderId());
        } else {
            folderServices.UpdateRemainingSize(newRemanningSize, folder.getFolderId());
        }
        folderServices.UpdateFolderSize(String.valueOf(size), folder.getFolderId());
    }
}
