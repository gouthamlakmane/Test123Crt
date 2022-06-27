package com.Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CallJavascriptExecutor extends Base{
 public static void scrollToElement(WebElement element)
 {
	 JavascriptExecutor je = (JavascriptExecutor) driver;
	 je.executeScript("arguments[0].scrollIntoView(true);",element);
 }
 
 public static void clickObject(WebElement element)
 {
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].click();", element);
 }
 
 public static void highLighterElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

 
 
 
}
