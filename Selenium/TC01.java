package test;

import driver.driverFactory;
import org.openqa.selenium.*;

import org.testng.annotations.Test;

@Test
public class TC01 {
    public static void testTC01(){
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("https://www.phucanh.vn/laptop-asus-zenbook-ux425ea-ki839w.html");
        try {

            driver.findElement(By.xpath("//*[@id='overview-left']/div[5]/a[2]")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//header[1]/div[1]/a[2]")).click();

            Thread.sleep(5000);
        } catch (Exception ignored) {
        }
        driver.quit();
    }
}
