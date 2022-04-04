package com.datadriven.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mahadev.b\\Desktop\\driver\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        DataProviderClass d = new DataProviderClass();
        d.getData(driver,"Sheet1");
    }
}
