package com.twitter.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;

import com.twitter.utility.TestUtil;

public class TwitterBase {
	
	public static Properties prop;
	public static WebDriver driver;
	
	public TwitterBase()
	{
		prop= new Properties();
		
		File f= new File("/media/gaurav/BA92AE4892AE0949/Selenium/Online_Classes/Learning_Java/Module1/MavenTP1/src/main/java/com/twitter/config/config.properties");
		
		FileInputStream fis;
		try 
		{
			fis = new FileInputStream(f);
			prop.load(fis);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void InvokeBrowser()
	{
		if (prop.getProperty("browser").equals("chrome"))
		{
			System.setProperty(prop.getProperty("arg0"), prop.getProperty("arg1"));
			driver= new ChromeDriver();
		}
		
		else if(prop.getProperty("browser").equals("firefox"))
		{
			System.setProperty(prop.getProperty("arg2"), prop.getProperty("arg3"));
			driver= new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static void logout()
	{
		
		String str= driver.getTitle();
		
		if(str.equals("Twitter. It's what's happening."))
		{
			driver.quit();
		}
		
		else if(str.equals("Login on Twitter"))
		{
			driver.quit();
		}
		
		else
		{
		WebElement ele= driver.findElement(By.xpath("//*[@id='user-dropdown']/a[@id='user-dropdown-toggle']"));
		
		Actions a= new Actions(driver);
		a.moveToElement(ele).click().build().perform();
		driver.findElement(By.xpath("//button[@class='dropdown-link' and text()='Log out']")).click();
		driver.quit();
		}
	}

}
