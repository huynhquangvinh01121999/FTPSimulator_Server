package dal.Services;

import dal.Base.IServices;
import dal.Base.BaseServices;
import dal.Commons.GetConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Users;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class UserServices extends BaseServices implements IServices<Users> {

    public UserServices() {
    }

    @Override
    public List<Users> GetAll() {
        List<Users> list = new ArrayList<>();
        try {
            String sql = "select * from users";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setEmail(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setFullName(rs.getString(3));
                user.setSex(rs.getString(4));
                user.setDob(rs.getString(5));
                user.setStatus(rs.getString(6));
                user.setCreateAt(rs.getString(7));
                user.setFileSizeUpload(rs.getString(8));
                user.setFileSizeDownload(rs.getString(9));
                user.setPermissionId(rs.getString(10));

                list.add(user);
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
    public Users Find(String value) {
        Users user;
        try {
            String sql = "select * from users where Email = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setEmail(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setFullName(rs.getString(3));
                user.setSex(rs.getString(4));
                user.setDob(rs.getString(5));
                user.setStatus(rs.getString(6));
                user.setCreateAt(rs.getString(7));
                user.setFileSizeUpload(rs.getString(8));
                user.setFileSizeDownload(rs.getString(9));
                user.setPermissionId(rs.getString(10));

                return user;
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
    public boolean Create(Users entity) {
        try {
            String sql = "insert into users(Email,Password,FullName,Sex,Dob,Status,CreateAt) values (?,?,?,?,?,?,?)";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getFullName());
            ps.setString(4, entity.getSex());
            ps.setString(5, entity.getDob());
            ps.setString(6, entity.getStatus());
            ps.setString(7, entity.getCreateAt());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Xảy ra lỗi khi đăng ký tài khoản - " + ex);
            return false;
        }
    }

    @Override
    public boolean Update(Users entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Remove(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean VerifyEmail(String email) {
        String sql = "select * from users where Email = ?";
        try {
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, email.trim());
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Xảy ra lỗi khi check email" + ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                System.err.println("đóng connect ko thành công - " + ex);
            }
        }
        return false;
    }

    public boolean Authenticate(Users entity) {
        try {
            String sql = "select * from users where Email = ? and Password = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Xảy ra lỗi khi truy vấn authenticate" + ex);
        }
        return false;
    }

    public boolean CheckStatusAuthor(String email, String status) {
        try {
            String sql = "select * from users where Email = ? and Status = ?";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, status);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Xảy ra lỗi khi truy vấn check author" + ex);
        }
        return false;
    }

    public String GetFileSizeUpload() {
        try {
            String sql = "select FileSizeUpload from users";
            dbContext = GetConnection.getInstance().getConn();
            ps = dbContext.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
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

}
