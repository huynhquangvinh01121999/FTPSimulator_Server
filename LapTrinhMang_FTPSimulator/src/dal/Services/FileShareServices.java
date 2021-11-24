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
import models.FileShares;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FileShareServices extends BaseServices implements IServices<FileShares> {

    public FileShareServices() {
    }

    @Override
    public List<FileShares> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FileShares Find(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Create(FileShares entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(FileShares entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Remove(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FileShares> GetFileShareToMe(String myEmail) {
        List<FileShares> list = new ArrayList<>();
        try {
            String sql = "select * from fileshares where ToEmail = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, myEmail);
            rs = ps.executeQuery();
            while (rs.next()) {
                FileShares file = new FileShares();
                file.setFileId(rs.getString(1));
                file.setFromEmail(rs.getString(2));
                file.setToEmail(rs.getString(3));
                file.setPermissionId(rs.getString(4));
                file.setShareAt(rs.getString(5));
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
}
