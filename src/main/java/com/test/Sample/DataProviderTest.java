package com.test.Sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class DataProviderTest {

        WebDriver driver;
        @Test(dataProvider = "GmailLogin")
        public void loginBonzai(String username, String password) throws InterruptedException {
            System.setProperty("webDriver.chrome.driver", "F:/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.get("https://accounts.google.com");
            driver.findElement(By.id("userId")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.xpath("//*[@class='btn-group']//*[contains(text(),'Continue')]")).click();
            Thread.sleep(5000);
            //System.out.println(driver.getTitle());
            Assert.assertTrue(driver.getPageSource().contains("Gmail"),"Login is incorrect");
            System.out.println("Login successfully");
            driver.quit();
        }

        @AfterMethod
        public void Teardown()
        {
            driver.quit();
        }

        @DataProvider(name = "GmailLogin")
        public Object[][] passdata()
        {
            Object[][] data = new Object[3][2];
            data[0][0] = "abc";
            data[0][1] = "demo";
            data[1][0] = "TestGmail";
            data[1][1] = "Gmail123";
            data[2][0] = "abcd";
            data[2][1] = "demo1";
            return data;
        }
    }


