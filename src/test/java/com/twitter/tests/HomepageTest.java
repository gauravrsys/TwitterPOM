package com.twitter.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.twitter.base.TwitterBase;
import com.twitter.pages.Homepage;
import com.twitter.pages.Loginpage;

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
	
	@Test(priority=2)
	public void validatePostTweetTest() throws InterruptedException
	{
		homepage.validatePostTweet("Jai Bhagwaan Ji Ki");
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		TwitterBase.logout();
	}
	

}
