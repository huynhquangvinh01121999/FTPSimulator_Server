package bll;

import bll.helpers.DateHelper;
import bll.helpers.Encryptions;
import bll.helpers.FileExtensions;
import bll.helpers.ThreadRandoms;
import dal.Services.*;
import java.util.ArrayList;
import java.util.List;
import middlewares.HandleVerify;
import models.*;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class UserBLL {

    private final UserServices userServices = new UserServices();
    private final FileServices fileServices = new FileServices();
    private final FolderServices folderServices = new FolderServices();
    private final FolderShareServices folderShareServices = new FolderShareServices();
    private final FileShareServices fileShareServices = new FileShareServices();
    private final PermissionServices permissionServices = new PermissionServices();

    public UserBLL() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Register">
    public HandleResult registerUser(Users user) {
        user.setPassword(Encryptions.md5(user.getPassword()));
        String prefixEmail = user.getEmail().split("@")[0].toString();
        String desPath = FileExtensions.getLocalWorkSortPath() + "/src/DATA/" + prefixEmail;

        Folders folder = new Folders();
        folder.setFolderId(ThreadRandoms.uuid());
        folder.setFolderName(prefixEmail);
        folder.setFolderPath(desPath);
        folder.setEmail(user.getEmail());
        folder.setSize("1,073,741,824");    // 1GB
        folder.setRemainingSize("1,073,741,824"); // 1GB
        folder.setCreateAt(DateHelper.Now());

        boolean generateFolder = FileExtensions.generateFolder(desPath); // tạo folder trong server
        boolean createUser = userServices.Create(user);
        boolean createFolder = folderServices.Create(folder);
        if (createUser && generateFolder && createFolder) {
            return new HandleResult(true, "Đăng ký tài khoản thành công!!!");
        }
        return new HandleResult(false, "Đã xảy ra lỗi khi đăng ký.\nVui lòng kiểm tra lại thông tin.!!!");
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Authenticate with user">
    public HandleResult authenticate(Users user) {
        HandleResult verifyResult = HandleVerify.verifyAuthor(user);
        if (verifyResult.isSuccessed()) {
            Users userInfo = userServices.Find(user.getEmail());
            Folders folderInfo = folderServices.FindFolderParentByEmail(user.getEmail());
            /* trả về client: 
            + trạng thái
            + thông tin user 
            + thông tin thư mục cha
            + danh sách thư mục con
            + danh sách file
            
            NOTE: vì do vấn đề sending qa socket bị giới hạn dung lượng byte 
            nên ta phải tách ra 2 hàm để return Object về cho Client
            * hàm 1: trả về thông tin đơn
            * hàm 2: trả về danh sách file + folder con 
             */
            if (userInfo != null) {
                return new HandleResult(true, verifyResult.getMessage(),
                        ((userInfo != null) ? userInfo : null),
                        folderInfo);
            } else {
                return new HandleResult(false, "Không tìm thấy email này.");
            }
        }
        return verifyResult;
    }

    public HandleResult getAuthenData(String folderParentId, String email) {
        // lấy ra danh sách các folder con
        List<Folders> listFolderChildInfo = new ArrayList<>();
        listFolderChildInfo = folderServices.FindListChildFolder(folderParentId);

        // lấy ra toàn bộ tất cả các file của user + anonymous
        String prexEmail = email.split("@")[0];
        List<Files> listFileInfo = new ArrayList<>();
        listFileInfo = new FileBLL().GetFilesByPrexEmail(prexEmail);

        List<Files> listFileAnonymous = new ArrayList<>();
        listFileAnonymous = new FileBLL().GetFilesByPrexEmail("anonymous");

        // thêm toàn bộ file anonymous vào trong list file info
        listFileInfo.addAll(listFileAnonymous);

        return new HandleResult(listFolderChildInfo, listFileInfo);
    }

    public HandleResult getAuthenDataShare(String email) {

        List<FileShares> listFileShares = fileShareServices.GetFileShareToMe(email);
        List<Files> listFiles = new ArrayList<>();
        for (FileShares item : listFileShares) {
            listFiles.add(fileServices.Find(item.getFileId()));
        }

        List<FolderShares> listFolderShare = folderShareServices.getFolderShareToMe(email);
        List<Folders> listFolders = new ArrayList<>();
        for (FolderShares item : listFolderShare) {
            listFolders.add(folderServices.Find(item.getFolderId()));
        }

        List<Permissions> listPermission = permissionServices.GetAll();

        return new HandleResult(listFileShares, listFolderShare, listFiles, listFolders, listPermission);
    }

    // </editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Authenticate with anonymous permission">
    public HandleResult authenticateWithAnonymousPermission(String anonymous) {
        Users userInfo = new Users();
        userInfo.setFullName(anonymous);
        userInfo.setEmail(anonymous);
        userInfo.setFileSizeUpload(userServices.GetFileSizeUpload());

        Folders folderInfo = new Folders();
        folderInfo.setFolderId(anonymous);
        folderInfo.setFolderName(anonymous);
        folderInfo.setEmail(anonymous);
        folderInfo.setFolderPath(FileExtensions.getLocalWorkSortPath() + "/src/DATA/" + anonymous);
        return new HandleResult(true, "Welcome to FCLOUD with anonymous permission.!!!",
                userInfo, folderInfo);
    }

    public HandleResult getAuthenDataWithAnonymousPermission() {
        // lấy ra danh sách các folder con
        List<Files> listFileAnonymous = new FileBLL().GetFilesByPrexEmail("anonymous");

        return new HandleResult(null, listFileAnonymous);
    }
    // </editor-fold>
}
