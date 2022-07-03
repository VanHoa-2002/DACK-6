package test;

import driver.driverFactory;
import org.openqa.selenium.*;

import org.testng.annotations.Test;

@Test
public class TC07 {
    public static void testTC07(){
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("https://www.phucanh.vn/laptop-asus-zenbook-ux425ea-ki839w.html");
        try {

            driver.findElement(By.xpath("//div[1]/div[5]/a[2]")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id='cart-header']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//tr[1]/td[5]/a[1]/img[1]")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();

            Thread.sleep(5000);
        } catch (Exception ignored) {
        }
        driver.quit();
    }
}
