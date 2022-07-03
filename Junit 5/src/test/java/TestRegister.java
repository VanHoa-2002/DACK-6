import org.junit.Test;

import static org.junit.Assert.*;

public class TestRegister {
    @Test
    //Không nhập email
    public void TestisEmailBlank(){
        Register lg = new Register();
        boolean isblank = lg.registercheck("Nguyen","Thuan","123456","");
        assertFalse(isblank);
    }

    //Email không có ký tự @
    @Test
    public void TestisEmailInvalid1(){
        Register lg = new Register();
        boolean isInValid = lg.registercheck("Nguyen","Thuan","123456","thanhthuangmail.com");
        assertFalse(isInValid);
    }
    //Email có hơn 3 ký tự
    @Test
    public void TestisEmailInvalid2(){
        Register lg = new Register();
        boolean isInValid = lg.registercheck("Nguyen","ThanhThuan","123456","t@");
        assertFalse(isInValid);
    }
    //Email ít hơn 257 ký tự
    @Test
    public void TestisEmailInvalid3(){
        Register lg = new Register();
        boolean isInValid = lg.registercheck("Nguyen","ThanhThuan","123456","Món đặc sản tại Nha Trang, sốt trứng kết hợp với gà ta dai mềm, cơm thì nấu với nước luộc gà thơm vàng óng ánh nhaMón đặc sản tại Nha Trang, sốt trứng kết hợp với gà ta dai mềm, cơm thì nấu với nước luộc gà thơm vàng óng ánh nhaMón đặc sản tại Nha Trang, sốt trứng kết hợp với gà ta dai mềm, cơm thì nấu với nước luộc gà thơm vàng óng ánh nha, có kèm thêm chén soup lòng đào nữa, nước mắm gừng kẹo kẹo ăn với cơm thì thôi rồi luôn@gmail.com");
        assertFalse(isInValid);
    }

    //Không nhập họ
    @Test
    public void TestisFirstNameBlank(){
        Register lg = new Register();
        boolean isblank = lg.registercheck("","Thuan","123456","thanhthuan@gmail.com");
        assertFalse(isblank);
    }

    //Không nhập tên
    @Test
    public void TestisLastNameBlank(){
        Register lg = new Register();
        boolean isblank = lg.registercheck("Nguyen","","123456","thanhthuan@gmail.com");
        assertFalse(isblank);
    }

    //Khong nhap Mat khau
    @Test
    public void TestisPasswordBlank(){
        Register lg = new Register();
        boolean isblank = lg.registercheck("Nguyen","Thuan","","thanhthuan@gmail.com");
        assertFalse(isblank);
    }

    //Mat khau phai co hon 6 ky tu
    @Test
    public void TestPasswordLength(){
        Register lg = new Register();
        boolean passlength = lg.registercheck("Nguyen","Thuan","1234","thanhthuan@gmail.com");
        assertFalse(passlength);
    }

    //Đăng ký thành công
    @Test
    public void TestisRegisterSuccess(){
        Register lg = new Register();
        boolean success = lg.registercheck("Nguyen","Thuan","123456","thanhthuan@gmail.com");
        assertTrue(success);
    }
}
