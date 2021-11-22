package bll.helpers;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class Validations {

    private static final Pattern VALID_EMAIL_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /*
        Tối thiểu tám ký tự, ít nhất một chữ cái và một số:
        "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
    
        Tối thiểu tám ký tự, ít nhất một chữ cái, một số và một ký tự đặc biệt:
        "^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"

        Tối thiểu tám ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường và một số:
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"

        Tối thiểu tám ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt:
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"

        Tối thiểu tám và tối đa 10 ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt:
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$"
     */
    private static final Pattern VALID_PASSWORD_REGEX
            = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_NAME_REGEX
            = Pattern.compile("^[a-z A-Z]{1,50}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean isPassword(String passStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passStr);
        return matcher.find();
    }

//    public static boolean isName(String nameStr) {
//        if (nameStr != null) {
//            Matcher matcher = VALID_NAME_REGEX.matcher(nameStr.trim());
//            return matcher.find();
//        }
//        return false;
//    }
    public static boolean isName(String nameStr) {
        if (nameStr != null) {
            if (nameStr.length() >= 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        Tối thiểu tám ký tự, ít nhất một chữ cái và một số
        System.out.println(Validations.isName(null));
    }
}
