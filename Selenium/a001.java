
package test;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;



@Test
public class a001 {
    public static void testLaunchBrowser() {
        WebDriver driver = driverFactory.getChromeDriver();
        //Debug only
        try {
            driver.get("https://www.phucanh.vn/may-tinh-xach-tay-laptop.html");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[1]/form[1]/input[1]")).sendKeys("cap mang 999");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[1]/form[1]/input[2]")).click();
            Thread.sleep(2000);
           String expectedHeading = "Tìm thấy 1 sản phẩm";
            String heading = driver.findElement(By.xpath("//div[1]/h2[1]")).getText();
            if(expectedHeading.equalsIgnoreCase(heading))
                System.out.println("The expected heading is same as actual heading --- "+heading);
            else
                System.out.println("The expected heading doesn't match the actual heading --- "+heading);
        } catch (Exception ignored) {
        }
        driver.close();
        driver.quit();
   }


}