package test;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import static org.testng.AssertJUnit.assertEquals;


@Test
public class dkthongtin {
    public static void testLaunchBrowser() {
        WebDriver driver = driverFactory.getChromeDriver();
        //Debug only
        try {
            driver.get("https://www.phucanh.vn/laptop-lenovo-thinkpad-x1-carbon-gen-9-20xw00g8vn.html");
            driver.findElement(By.xpath("//td[4]/div[1]/div[2]/input[1]")).sendKeys("vgiakhang@gmail.com");
            driver.findElement(By.xpath("//tr[1]/td[4]/div[1]/div[2]/a[1]")).click();
            String alertMessage= driver.switchTo().alert().getText();
            String expectedHeading = "Email này đã tồn tại";
            System.out.println(" title1 "+alertMessage);
            try {
                assertEquals(alertMessage, expectedHeading);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ignored) {
        }
        driver.close();
        driver.quit();
    }


}