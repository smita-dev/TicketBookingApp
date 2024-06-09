package ticket.booking.utils;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtils {

    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashPassword){
        return BCrypt.checkpw(plainPassword,hashPassword);
    }

}
