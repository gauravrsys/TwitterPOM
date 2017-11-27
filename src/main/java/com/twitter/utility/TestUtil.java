package com.twitter.utility;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;





import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.twitter.base.TwitterBase;
//import org.testng.annotations.Test;

public class TestUtil extends TwitterBase {
	
	public FileInputStream fis;
	public XSSFWorkbook wbo;
	public XSSFSheet wso;
	public XSSFRow row;
	int rcount,ccount;
	String str;
	
	public TestUtil(String path) 
	{
		File f= new File(path);
		
		try 
		{
			 fis= new FileInputStream(f);
			 wbo= new XSSFWorkbook(fis);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int getRowCount(int sheetno)
	{
		
		wso= wbo.getSheetAt(sheetno);
		row= wso.getRow(0);
		
		rcount= wso.getLastRowNum();
		rcount=rcount+1;
		return rcount;	
	}
	
	public int getColCount(int sheetno)
	{
		ccount= wbo.getSheetAt(sheetno).getRow(0).getLastCellNum();
		return ccount;
		
	}
	public String getExcelData(int sheetno, int rcount, int ccount)
	{
		
		str= wbo.getSheetAt(sheetno).getRow(rcount).getCell(ccount).toString();
		return str;	
		
	}
	
	
	public static void takeScreenshot(WebDriver driver, long name)
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(src,new File("/media/gaurav/BA92AE4892AE0949/Selenium/Online_Classes/Learning_Java/Module1/MavenTP1/Screenshots"+"/"+name+".png"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}


