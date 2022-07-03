
package test;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;


import static org.testng.AssertJUnit.assertEquals;


@Test
public class a006 {
    public static void testLaunchBrowser() {
        WebDriver driver = driverFactory.getChromeDriver();
        //Debug only
        try {
            driver.get("https://www.phucanh.vn/may-tinh-xach-tay-laptop.html");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[1]/form[1]/input[1]")).sendKeys("bAlO");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[1]/form[1]/input[2]")).click();
            Thread.sleep(2000);
            String title1 = driver.findElement(By.xpath("//div[1]/h2[1]")).getText();
            String expectedHeading = "Tìm thấy 172 sản phẩm";
            System.out.println(" title1 "+title1);
            try {
                assertEquals(title1, expectedHeading);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ignored) {
        }
       driver.close();
        driver.quit();
    }


}