/*
Test Steps
Step 1. Go to http://live.techpanda.org/Step 2. Verify Title of the page
Step 3. Click on -> MOBILE -> menu
Step 4. In the list of all mobile , select SORT BY -> dropdown as name
Step 5. Verify all products are sorted by name
*/
package test;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.io.File;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class test4 {
    public static void testLaunchBrowser() {
        WebDriver driver = driverFactory.getChromeDriver();
        //Debug only
        try {

            driver.get("https://www.phucanh.vn/dang-nhap");
            driver.findElement(By.xpath("//*[@id='email']")).sendKeys("vgiakhang@gmail.com");
            driver.findElement(By.xpath("//*[@id='password']")).sendKeys("123456789");
            driver.findElement(By.xpath("//div[1]/input[1]")).click();
           Thread.sleep(2000);
            driver.findElement(By.xpath("//div[1]/form[1]/input[1]")).sendKeys("vaa");
            driver.findElement(By.xpath("//div[1]/form[1]/input[2]")).click();
            String expectedHeading = "Tìm thấy 25 sản phẩm";


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