package com.twitter.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.twitter.base.TwitterBase;
import com.twitter.pages.Homepage;
import com.twitter.pages.Loginpage;
import com.twitter.utility.TestUtil;

public class LoginpageTest extends TwitterBase {
	
	
	public static Homepage homepage;
	Loginpage lp;
	
	public LoginpageTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp()
	{
		InvokeBrowser();
		lp= new Loginpage();
		
	}
	
	@Test(priority=1)
	public void loginTitleTest()
	{
		String title1= lp.loginPageTitle();
		System.out.println(title1);
		Assert.assertEquals(title1, "Twitter. It's what's happening.");
	}
	
	@Test(priority=2,dataProvider="TwitterData")
	public void login(String username, String password)
	{
		homepage= lp.loginT(username, password);
		WebElement ele= driver.findElement(By.xpath("//*[text()='Notifications']"));
		
		Assert.assertTrue(ele.isDisplayed());
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			TestUtil.takeScreenshot(driver,result.getEndMillis());
		}
		TwitterBase.logout();
	}
	
	@DataProvider(name="TwitterData")

	public Object[][] getData()
	{
		
		TestUtil util= new TestUtil(prop.getProperty("ExcelPath"));
		
		int rcount= util.getRowCount(0);
		int ccount= util.getColCount(0);
		
		Object[][] arr= new Object[rcount-1][ccount];
		
		for (int i=1;i<rcount;i++)
		{
			for(int j=0;j<ccount;j++)
			{
				arr[i-1][j]= util.getExcelData(0,i,j);
			}	
		}
		
		return arr;
		
	}
			
}
