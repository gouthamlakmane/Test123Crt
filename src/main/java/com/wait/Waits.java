package com.wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utils.Base;

public class Waits extends Base{
	
//		Explicitly Wait
		public static void waitForElementPresent(WebDriver driver, WebElement ele )
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
	
//	implicitly Wait
	public static void waitForPageToLoad()
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
	}
	
//	Explicitly Wait title wise
	public static void waitForTitleContains(WebDriver driver, String title)
	{
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	
}
