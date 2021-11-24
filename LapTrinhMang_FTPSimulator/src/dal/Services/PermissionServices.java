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
import models.Permissions;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class PermissionServices extends BaseServices implements IServices<Permissions> {

    public PermissionServices() {
    }

    @Override
    public List<Permissions> GetAll() {
        List<Permissions> list = new ArrayList<>();
        try {
            String sql = "select * from permissions";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Permissions permission = new Permissions();
                permission.setPermissionId(rs.getString(1));
                permission.setPermissionName(rs.getString(2));
                
                list.add(permission);
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
    public Permissions Find(String value) {
        try {
            String sql = "select * from permissions where PermissionId = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                Permissions permission = new Permissions();
                permission.setPermissionId(rs.getString(1));
                permission.setPermissionName(rs.getString(2));
                return permission;
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

    @Override
    public boolean Create(Permissions entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(Permissions entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Remove(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
