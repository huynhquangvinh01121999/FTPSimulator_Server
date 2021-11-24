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
import models.Files;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FileServices extends BaseServices implements IServices<Files> {

    public FileServices() {
    }

    @Override
    public List<Files> GetAll() {
        List<Files> list = new ArrayList<>();
        try {
            String sql = "select * from files";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Files file = new Files();
                file.setFileId(rs.getString(1));
                file.setFileName(rs.getString(2));
                file.setSourcePath(rs.getString(3));
                file.setFileSize(rs.getString(4));
                file.setFileExtension(rs.getString(5));
                file.setStatus(rs.getString(6));
                file.setFolderId(rs.getString(7));
                file.setUploadAt(rs.getString(8));
                file.setPrexEmail(rs.getString(9));

                list.add(file);
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

    @Override
    public Files Find(String value) {
        try {
            String sql = "select * from files where FileId = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                Files file = new Files();
                file.setFileId(rs.getString(1));
                file.setFileName(rs.getString(2));
                file.setSourcePath(rs.getString(3));
                file.setFileSize(rs.getString(4));
                file.setFileExtension(rs.getString(5));
                file.setStatus(rs.getString(6));
                file.setFolderId(rs.getString(7));
                file.setUploadAt(rs.getString(8));
                return file;
            }
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi cập nhật thông tin file - " + ex);
        }
        return null;
    }

    @Override
    public boolean Create(Files entity) {
        try {
            String sql = "insert into files(FileId,FileName,SourcePath,FileSize,FileExtension,Status,FolderId,UploadAt,PrexEmail) values (?,?,?,?,?,?,?,?,?)";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, entity.getFileId());
            ps.setString(2, entity.getFileName());
            ps.setString(3, entity.getSourcePath());
            ps.setString(4, entity.getFileSize());
            ps.setString(5, entity.getFileExtension());
            ps.setString(6, entity.getStatus());
            ps.setString(7, entity.getFolderId());
            ps.setString(8, entity.getUploadAt());
            ps.setString(9, entity.getPrexEmail());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi lưu mới thông tin file - " + ex);
            return false;
        }
    }

    @Override
    public boolean Update(Files entity) {
        try {
            String sql = "";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, entity.getFileId());
            ps.setString(2, entity.getFileName());
            ps.setString(3, entity.getSourcePath());
            ps.setString(4, entity.getFileSize());
            ps.setString(5, entity.getFileExtension());
            ps.setString(6, entity.getStatus());
            ps.setString(7, entity.getFolderId());
            ps.setString(8, entity.getUploadAt());
            ps.setString(9, entity.getPrexEmail());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi cập nhật thông tin file - " + ex);
            return false;
        }
    }

    @Override
    public boolean Remove(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Files> GetListFileByFolderId(String folderId) {
        List<Files> list = new ArrayList<>();
        try {
            String sql = "select * from files where FolderId = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, folderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Files file = new Files();
                file.setFileId(rs.getString(1));
                file.setFileName(rs.getString(2));
                file.setSourcePath(rs.getString(3));
                file.setFileSize(rs.getString(4));
                file.setFileExtension(rs.getString(5));
                file.setStatus(rs.getString(6));
                file.setFolderId(rs.getString(7));
                file.setUploadAt(rs.getString(8));
                file.setPrexEmail(rs.getString(9));

                list.add(file);
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
    
     public List<Files> GetListFileByPrexEmail(String prexEmail) {
        List<Files> list = new ArrayList<>();
        try {
            String sql = "select * from files where PrexEmail = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, prexEmail);
            rs = ps.executeQuery();
            while (rs.next()) {
                Files file = new Files();
                file.setFileId(rs.getString(1));
                file.setFileName(rs.getString(2));
                file.setSourcePath(rs.getString(3));
                file.setFileSize(rs.getString(4));
                file.setFileExtension(rs.getString(5));
                file.setStatus(rs.getString(6));
                file.setFolderId(rs.getString(7));
                file.setUploadAt(rs.getString(8));
                file.setPrexEmail(rs.getString(9));

                list.add(file);
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

    public Files FindByNameAndSourcePath(String name, String sourcePath) {
        Files file;
        try {
            String sql = "select * from files where FileName = ? and SourcePath = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, sourcePath);
            rs = ps.executeQuery();
            if (rs.next()) {
                file = new Files();
                file.setFileId(rs.getString(1));
                file.setFileName(rs.getString(2));
                file.setSourcePath(rs.getString(3));
                file.setFileSize(rs.getString(4));
                file.setFileExtension(rs.getString(5));
                file.setStatus(rs.getString(6));
                file.setFolderId(rs.getString(7));
                file.setUploadAt(rs.getString(8));
                file.setPrexEmail(rs.getString(9));

                return file;
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

    public boolean UpdateFileIsExist(Files file) {
        try {
            String sql = "update files set FileSize = ?, FileExtension = ?, UploadAt = ?, PrexEmail = ? where FileName = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, file.getFileSize());
            ps.setString(2, file.getFileExtension());
            ps.setString(3, file.getUploadAt());
            ps.setString(4, file.getPrexEmail());
            ps.setString(5, file.getFileName());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi cập nhật thông tin file - " + ex);
            return false;
        }
    }
}
