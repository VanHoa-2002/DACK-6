import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLogin {
    @Test
    //Khong nhap email va mat khau dang nhap
    public void TestisEmailandPasswordBlank(){
        Login lg = new Login();
        boolean isblank = lg.Logincheck("","");
        assertFalse(isblank);
    }

    //Khong nhap email
    @Test
    public void TestisEmailBlank(){
        Login lg = new Login();
        boolean isblank = lg.Logincheck("","123456");
        assertFalse(isblank);
    }


    //Email chua duoc dang ky hoac nhap sai email
    @Test
    public void TestisEmailnotexistOrWrongEmail(){
        Login lg = new Login();
        boolean isValid = lg.Logincheck("thanhthuan123@gmail.com","123456");
        assertFalse(isValid);
    }

    //Khong nhap mat khau
    @Test
    public void TestisPasswordBlank(){
        Login lg = new Login();
        boolean isValid = lg.Logincheck("thanhthuan@gmail.com","");
        assertFalse(isValid);
    }

    //Nhap sai mat khau
    @Test
    public void TestisWrongPassword(){
        Login lg = new Login();
        boolean isValid = lg.Logincheck("thanhthuan@gmail.com","thanhthuan");
        assertFalse(isValid);
    }

    //Dang nhap thanh cong
    @Test
    public void TestisLoginSuccess(){
        Login lg = new Login();
        boolean isValid = lg.Logincheck("thanhthuan@gmail.com","123456");
        assertTrue(isValid);
    }
}
