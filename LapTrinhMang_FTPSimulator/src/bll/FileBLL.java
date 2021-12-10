package bll;

import bll.helpers.DateHelper;
import dal.Services.FileServices;
import dal.Services.FileShareServices;
import dal.Services.FolderServices;
import dal.Services.FolderShareServices;
import java.util.List;
import models.FileShares;
import models.Files;
import models.FolderShares;

public class FileBLL {

    private final FileShareServices fileShareServices = new FileShareServices();
    private final FileServices fileService = new FileServices();

    public FileBLL() {
    }

    /* Ý tưởng chức năng upload insert file:
        khi upload fie => kiểm tra tên file kèm đường dẫn đc upload đã tồn tại trong db chưa
    => rồi: - lấy ra dung lượng còn lại của folder từ folderId đc gửi đính kèm lên
            - CT tính: Cộng dung lượng file cũ vào dung lượng còn của folder rồi mới Trừ đi dung lượng file mới gửi lên
            - tiến hành update dung lượng cho folder của user
            - tiến hành update lại thông tin file
    => chưa: - lấy ra dung lượng còn lại của folder từ folderId đc gửi đính kèm lên
             - CT tính: lấy dung lượng còn của folder Trừ đi dung lượng file mới gửi lên
             - tiến hành update dung lượng cho folder của user
             - tiến hành thêm mới thông tin file vào database
    
    * kiểm tra folder mà user upload file lên có dc chia sẻ cho ai chưa
    ** nếu có -> chia sẻ file đó vs những ng đc chia sẻ trong folder đó, quyền sẽ lấy theo quyền truy cập folder đc gán sẵn của ng đó
     */
    public boolean insertNewFile(Files file) {
        String remainingSizeFolder = new FolderServices().GetRemainingSizeFolder(file.getFolderId());
        Files fileExist = fileService.FindByNameAndSourcePath(file.getFileName(), file.getSourcePath());
        if (fileExist != null) {
            double remainingSizeFolderConvert
                    = Double.parseDouble(remainingSizeFolder.replaceAll(",", ""))
                    + Double.parseDouble(fileExist.getFileSize().replaceAll(",", ""))
                    - Double.parseDouble(file.getFileSize().replaceAll(",", ""));
            try {
                new FolderServices().
                        UpdateRemainingSize(String.valueOf(remainingSizeFolderConvert), file.getFolderId());
            } catch (Exception e) {
                System.err.println("Xảy ra lỗi khi update dung lượng còn lại của folder" + e);
            }
            return fileService.UpdateFileIsExist(file);
        }

        double remainingSizeFolderConvert
                = Double.parseDouble(remainingSizeFolder.replaceAll(",", ""))
                - Double.parseDouble(file.getFileSize().replaceAll(",", ""));
        try {
            new FolderServices().
                    UpdateRemainingSize(String.valueOf(remainingSizeFolderConvert), file.getFolderId());
        } catch (Exception e) {
            System.err.println("Xảy ra lỗi khi update dung lượng còn lại của folder" + e);
        }

        // kiểm tra folder mà user upload file lên có dc chia sẻ cho ai chưa
        List<FolderShares> folderShareses = new FolderShareServices().getListFolderShared(file.getFolderId());
        if (!folderShareses.isEmpty()) {
            for (FolderShares folderShares : folderShareses) {
                fileShareServices.Create(new FileShares(file.getFileId(),
                        folderShares.getFromEmail(), folderShares.getToEmail(), folderShares.getPermissionId(), DateHelper.Now()));
            }
        }
        return fileService.Create(file);
    }

    public boolean insertNewFileShare(Files file) {
        String remainingSizeFolder = new FolderServices().GetRemainingSizeFolder(file.getFolderId());
        Files fileExist = fileService.FindByNameAndSourcePath(file.getFileName(), file.getSourcePath());
        if (fileExist != null) {
            double remainingSizeFolderConvert
                    = Double.parseDouble(remainingSizeFolder.replaceAll(",", ""))
                    + Double.parseDouble(fileExist.getFileSize().replaceAll(",", ""))
                    - Double.parseDouble(file.getFileSize().replaceAll(",", ""));
            try {
                new FolderServices().
                        UpdateRemainingSize(String.valueOf(remainingSizeFolderConvert), file.getFolderId());
            } catch (Exception e) {
                System.err.println("Xảy ra lỗi khi update dung lượng còn lại của folder" + e);
            }
            return fileService.UpdateFileIsExist(file);
        }
        
        double remainingSizeFolderConvert
                = Double.parseDouble(remainingSizeFolder.replaceAll(",", ""))
                - Double.parseDouble(file.getFileSize().replaceAll(",", ""));
        try {
            new FolderServices().
                    UpdateRemainingSize(String.valueOf(remainingSizeFolderConvert), file.getFolderId());
        } catch (Exception e) {
            System.err.println("Xảy ra lỗi khi update dung lượng còn lại của folder" + e);
        }
        fileShareServices.Create(new FileShares(file.getFileId(), file.getEmailShare(), file.getEmailShare(), "a", DateHelper.Now()));
        return fileService.Create(file);
    }

    public List<Files> GetFilesByFolderId(String folderId) {
        return fileService.GetListFileByFolderId(folderId.trim());
    }

    public List<Files> GetFilesByPrexEmail(String prexEmail) {
        return fileService.GetListFileByPrexEmail(prexEmail.trim());
    }
}
