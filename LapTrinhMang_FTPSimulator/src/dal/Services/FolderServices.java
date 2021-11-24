/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.Services;

import dal.Base.IServices;
import dal.Base.BaseServices;
import dal.Commons.GetConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Folders;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FolderServices extends BaseServices implements IServices<Folders> {

    public FolderServices() {
    }

    @Override
    public List<Folders> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Folders Find(String value) {
        try {
            String sql = "select * from folders where FolderId = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                Folders folder = new Folders();
                folder.setFolderId(rs.getString(1));
                folder.setFolderName(rs.getString(2));
                folder.setFolderPath(rs.getString(3));
                folder.setEmail(rs.getString(4));
                folder.setSize(rs.getString(5));
                folder.setRemainingSize(rs.getString(6));
                folder.setCreateAt(rs.getString(7));
                return folder;
            }
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi tìm thông tin folder - " + ex);
        }
        return null;
    }

    @Override
    public boolean Create(Folders entity) {
        try {
            String sql = "insert into folders(FolderId,FolderName,FolderPath,Email,Size,RemainingSize,CreateAt,FolderParentId) values (?,?,?,?,?,?,?,?)";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, entity.getFolderId());
            ps.setString(2, entity.getFolderName());
            ps.setString(3, entity.getFolderPath());
            ps.setString(4, entity.getEmail());
            ps.setString(5, entity.getSize());
            ps.setString(6, entity.getRemainingSize());
            ps.setString(7, entity.getCreateAt());
            ps.setString(8, entity.getFolderParentId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi khởi tạo thư mục - " + ex);
            return false;
        }
    }

    @Override
    public boolean Update(Folders entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Remove(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Folders FindFolderParentByEmail(String email) {
        Folders folder;
        try {
            String sql = "select * from folders where Email = ? and FolderParentId is null";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                folder = new Folders();
                folder.setFolderId(rs.getString(1));
                folder.setFolderName(rs.getString(2));
                folder.setFolderPath(rs.getString(3));
                folder.setEmail(rs.getString(4));
                folder.setSize(rs.getString(5));
                folder.setRemainingSize(rs.getString(6));
                folder.setCreateAt(rs.getString(7));
                folder.setFolderParentId(rs.getString(8));

                return folder;
            }

        } catch (SQLException ex) {
            System.err.println("Lỗi khi đọc dữ liệu - " + ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                System.err.println("đóng connect ko thành công - " + ex);
            }
        }
        return null;
    }

    public List<Folders> FindListChildFolder(String folderParentId) {
        List<Folders> list = new ArrayList<>();
        try {
            String sql = "select * from folders where FolderParentId = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, folderParentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Folders folder = new Folders();
                folder.setFolderId(rs.getString(1));
                folder.setFolderName(rs.getString(2));
                folder.setFolderPath(rs.getString(3));
                folder.setEmail(rs.getString(4));
                folder.setSize(rs.getString(5));
                folder.setRemainingSize(rs.getString(6));
                folder.setCreateAt(rs.getString(7));
                folder.setFolderParentId(rs.getString(8));

                list.add(folder);
            }

        } catch (SQLException ex) {
            System.err.println("Lỗi khi đọc dữ liệu - " + ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                System.err.println("đóng connect ko thành công - " + ex);
            }
        }
        return list;
    }

    public boolean UpdateRemainingSize(String remainSize, String folderId) {
        try {
            String sql = "update folders set RemainingSize = ? where FolderId = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, remainSize);
            ps.setString(2, folderId);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Xảy ra lỗi khi update kích thước còn lại của thư mục" + ex);
            return false;
        }
    }

    public String GetRemainingSizeFolder(String folderId) {
        try {
            String sql = "select RemainingSize from folders where FolderId = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, folderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi cập nhật thông tin file - " + ex);
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "1,048,576".replaceAll(",", "").toString();
        double a = Double.parseDouble(s);
        System.out.println(new FolderServices().GetRemainingSizeFolder("a224bc34-4598-42a6-bae1-d6c23d9902ba"));
    }
}
