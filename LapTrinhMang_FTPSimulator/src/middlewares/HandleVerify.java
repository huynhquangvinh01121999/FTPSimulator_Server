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
        boolean checkStatusAuthorResult = new UserServices().CheckStatusAuthor(email, "unlock");
        if (checkStatusAuthorResult) {
            return new HandleResult(true);
        }
        return new HandleResult(false, "Tài khoản đã bị khóa");
    }

    // func verify for register
    public static HandleResult verifyRegisterUser(Users user) {
        if (Validations.isEmail(user.getEmail())) {
            if (!user.getEmail().trim().toLowerCase().equals("anonymous@gmail.com")) {
                if (Validations.isPassword(user.getPassword())) {
                    if (Validations.isName(user.getFullName())) {
                        if (!user.getFullName().trim().toLowerCase().equals("anonymous")) {
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
                        return new HandleResult(false, "Tên người dùng không hợp lệ.\nVui lòng không sử dụng tên người dùng là anonymous.");
                    }
                    return new HandleResult(false, "Họ và tên không được bỏ trống!!!");
                }
                return new HandleResult(false, "Tối thiểu tám ký tự.\nÍt nhất một chữ cái , một số, một ký tự in hoa, một ký tự đặc biệt (nếu có)!!!");
            }
            return new HandleResult(false, "Không được phép sử dụng email có tiền tố là anonymous!!!");
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
