package com.comonMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utils.Base;

public class WebDriverCommonlib extends Base
{
	//implicitly Wait
		public static void waitForPageToLoad()
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
		}

	   //Explicitly Wait
		public static void waitForElementPresent(WebDriver driver, WebElement wb)
		{
			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.visibilityOf(wb));	
		}
		
}
