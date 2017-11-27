package com.twitter.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.twitter.base.TwitterBase;

public class Homepage extends TwitterBase  {
	
	@FindBy(xpath= "//button[@id='global-new-tweet-button']")
	WebElement tweetButtonHome;
	
	@FindBy(xpath= "//div[@id='tweet-box-global']")
	WebElement tweetBox;
	
	@FindBy(xpath= "(//*[@class='button-text tweeting-text'])[2]")
	WebElement tweetButtonBox;
	
	public Homepage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle()
	{
		return driver.getTitle();
		
	}
	
	public void validatePostTweet(String str) throws InterruptedException
	{
		tweetButtonHome.click();
		tweetBox.sendKeys(str);
		tweetButtonBox.click();	
		Thread.sleep(2000);
		
		try
		{
			driver.switchTo().alert().dismiss();
		}
		catch (Exception e)
		{
			System.out.println("Tweet Posted");
		}
	}
	

}
