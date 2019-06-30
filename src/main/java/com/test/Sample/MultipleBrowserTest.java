package com.test.Sample;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MultipleBrowserTest {
    public WebDriver driver;
    public static final String USERNAME = "Gmail";
    public static final String AUTOMATE_KEY = "Hdgtdjkanbhfc7FQSuqAqYC";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Test(dataProvider = "Environmentdetails")
    public void multipleBroswerTest(Platform platform, String browserName , String browserVersion) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(platform);
        caps.setBrowserName(browserName);
        caps.setVersion(browserVersion);
        caps.setCapability("browserstack.debug", "true");

        java.net.URL browserStackURL = new URL(URL);

        driver = new RemoteWebDriver(browserStackURL, caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com");
        Thread.sleep(10000);
        driver.findElement(By.id("userId")).sendKeys("TestGmail");
        driver.findElement(By.id("password")).sendKeys("Gmail123");
        driver.findElement(By.xpath("//*[@class='btn-group']//*[contains(text(),'Continue')]")).click();
        Thread.sleep(5000);
        System.out.println(driver.getTitle());
    }

    @DataProvider(name = "Environmentdetails")
    public Object[][] getdate() {
        Object[][] testdata = new Object[][]
                {
                        {Platform.MAC, "chrome", "62.0"},
                        {Platform.WIN10, "chrome", "75.0"},
                        {Platform.WINDOWS, "firefox", "57"}
                };
        return testdata;
    }
}
