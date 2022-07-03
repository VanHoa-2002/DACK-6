public class Register {

    public boolean registercheck (String firstname,String lastname,String password,String EmailAddress) {
        // không nhập Email
        if ( EmailAddress == "" )
            return false;
        // Email không có ký tự @
        if ( EmailAddress.indexOf("@") == -1 )
            return false;
        // Email có hơn 3 ký tự hoặc Email ít hơn 257 ký tự
        if ( EmailAddress.length() < 3 || EmailAddress.length() > 257)
            return false;
        // Không nhập họ
        if ( firstname == "" )
            return false;
        // Không nhập tên
        if ( lastname == "" )
            return false;
        // Không nhập mật khẩu
        if ( password.length() == 0)
            return false;
        // Mật khẩu phải dài hơn 6
        if ( password.length() < 6 )
            return false;
        return true;
    }
}
