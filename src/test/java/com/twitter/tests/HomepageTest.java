package com.twitter.tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.twitter.base.TwitterBase;
import com.twitter.pages.Homepage;
import com.twitter.pages.Loginpage;
import com.twitter.utility.TestUtil;

public class HomepageTest extends TwitterBase {
	
	Homepage homepage;
	
	public HomepageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		InvokeBrowser();
		Loginpage loginpage= new Loginpage();
		homepage= loginpage.loginT(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void validateHomePageTitleTest()
	{
		String str=homepage.validateHomePageTitle();
		Assert.assertTrue(str.contains("Twitter"), "Home Page title does not get matched");
		
	}
	
	@Test(dataProvider="TweetData",priority=2)
	public void validatePostTweetTest(String str1) throws InterruptedException
	{
		homepage.validatePostTweet(str1);
		
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
	
	@DataProvider(name="TweetData")

	public Object[][] getData()
	{
		
		TestUtil util= new TestUtil(prop.getProperty("ExcelPath"));
		
		int rcount= util.getRowCount(1);
		int ccount= util.getColCount(1);
		
		Object[][] arr= new Object[rcount-1][ccount];
		
		for (int i=1;i<rcount;i++)
		{
			for(int j=0;j<ccount;j++)
			{
				arr[i-1][j]= util.getExcelData(1,i,j);
			}	
		}
		
		return arr;
		
	}
	

}
