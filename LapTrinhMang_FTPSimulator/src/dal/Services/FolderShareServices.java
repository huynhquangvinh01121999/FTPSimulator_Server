/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.Services;

import dal.Base.BaseServices;
import dal.Base.IServices;
import dal.Commons.GetConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.FolderShares;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FolderShareServices extends BaseServices implements IServices<FolderShares> {

    public FolderShareServices() {
    }

    @Override
    public List<FolderShares> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FolderShares Find(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Create(FolderShares entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(FolderShares entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Remove(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FolderShares> getFolderShareToMe(String myEmail) {
        List<FolderShares> list = new ArrayList<>();
        try {
            String sql = "select * from foldershares where ToEmail = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, myEmail);
            rs = ps.executeQuery();
            while (rs.next()) {
                FolderShares file = new FolderShares();
                file.setFolderId(rs.getString(1));
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
