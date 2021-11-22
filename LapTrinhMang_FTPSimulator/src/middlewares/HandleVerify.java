/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewares;

import bll.helpers.ThreadRandoms;
import bll.helpers.Validations;
import dal.Services.UserServices;
import handlers.SendMail;
import models.HandleResult;
import models.MailSender;
import models.Users;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class HandleVerify {

    // func check email existed???
    public static boolean existedEmail(String email) {
        return new UserServices().VerifyEmail(email);
    }

    // func check author unblock???
    public static HandleResult verifyAuthorUnblock(String email) {
        boolean checkStatusAuthorResult = new UserServices().CheckStatusAuthor(email, "unblock");
        if (checkStatusAuthorResult) {
            return new HandleResult(true);
        }
        return new HandleResult(false, "Tài khoản đã bị khóa");

    }

    // func verify for register
    public static HandleResult verifyRegisterUser(Users user) {
        if (Validations.isEmail(user.getEmail())) {
            if (Validations.isPassword(user.getPassword())) {
                if (Validations.isName(user.getFullName())) {
                    if (!existedEmail(user.getEmail())) {
                        int verifyCode = ThreadRandoms.generateCode(); // random ra 1 verify code
                        try {
                            new SendMail(verifyCode, new MailSender(user.getEmail()));
                            return new HandleResult(true, "Một mã xác thực vừa được gửi đến email của bạn!!!", verifyCode);
                        } catch (Exception ex) {
                            return new HandleResult(false, "Gửi mail thất bại!!!" + ex);
                        }
                    }
                    return new HandleResult(false, "Email đã tồn tại!!!");
                }
                return new HandleResult(false, "Họ và tên không được bỏ trống!!!");
            }
            return new HandleResult(false, "Tối thiểu 8 ký tự, ít nhất một chữ cái và một số!!!");
        }
        return new HandleResult(false, "Email không đúng định dạng!!!");
    }

    // func verify author for login
    public static HandleResult verifyAuthor(Users user) {
        // kiểm tra email có hợp lệ ko
        if (Validations.isEmail(user.getEmail())) {
            // check email có tồn tại chưa
            if (existedEmail(user.getEmail())) {
                // check email chưa bị block
                HandleResult verifyAuthorUnblockResult = verifyAuthorUnblock(user.getEmail());
                if (verifyAuthorUnblockResult.isSuccessed()) {    // ko bị khóa
                    if (new UserServices().Authenticate(user)) {  // tiến hành xác thực đăng nhập
                        return new HandleResult(true, "Đăng nhập thành công.!!!");
                    }
                    return new HandleResult(false,
                            "Email hoặc mật khẩu chưa chính xác.\nVui lòng kiểm tra lại.!!!");
                }
                return verifyAuthorUnblockResult;
            }
            return new HandleResult(false, "Email chưa được đăng ký.!!!");
        }
        return new HandleResult(false, "Email không hợp lệ.!!!");
    }

}
