package com.twitter.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.twitter.base.TwitterBase;

public class Loginpage extends TwitterBase {
	
	@FindBy(xpath= "//a[text()='Log in']")
	public WebElement loginBtn1;
	
	@FindBy(name= "session[username_or_email]")
	public WebElement username;
	
	@FindBy(name= "session[password]")
	public WebElement password;
	
	@FindBy(xpath= "//*[@value='Log in']")
	public WebElement loginBtn2;
	
	//Constructor for intializing current class instance variables
	public Loginpage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String loginPageTitle()
	{
		return driver.getTitle();
	}
	
	public Homepage loginT(String un, String pwd)
	{
		loginBtn1.click();
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn2.click();
		return new Homepage();
	}

}
