package kelompok2.marketplace.com.kuepenjual.util;

import android.util.Patterns;

public class FormHelper {
    public static boolean checkEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean checkPhone(String phone){
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean checkUsername(String username){
        username = username.replaceAll("\\s","");
        return username.matches("[a-zA-Z]+$");
    }
}
