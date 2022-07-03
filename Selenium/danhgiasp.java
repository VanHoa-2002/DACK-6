
package test;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;


import static org.testng.AssertJUnit.assertEquals;


@Test
public class danhgiasp {
    public static void testLaunchBrowser() {
        WebDriver driver = driverFactory.getChromeDriver();
        //Debug only
        try {
            driver.get("https://www.phucanh.vn/laptop-lenovo-thinkpad-x1-carbon-gen-9-20xw00g8vn.html");
            driver.findElement(By.xpath("//*[@id='rating-content']")).sendKeys("san pham toi");
            driver.findElement(By.xpath("//*[@id='rating-name']")).sendKeys("khang");
            driver.findElement(By.xpath("//*[@id='rating-email']")).sendKeys("vgiakhang@gmail.com");
            driver.findElement(By.xpath("//div[1]/table[1]/tbody[1]/tr[5]/td[2]/a[1]")).click();
        } catch (Exception ignored) {
        }
        driver.close();
        driver.quit();
    }


}