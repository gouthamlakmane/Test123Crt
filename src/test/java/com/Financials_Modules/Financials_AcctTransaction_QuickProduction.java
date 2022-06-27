package com.Financials_Modules;

import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.Common_Methods;
import com.Utils.WebdriverWait;
import com.wait.Waits;

public class Financials_AcctTransaction_QuickProduction extends Base
{
   public void validateFgInv() 
   {
	   WebdriverWait.findElement("link", "GL Transaction View/Entry").click();
	   Waits.waitForPageToLoad();
	   Select tranType = new Select(WebdriverWait.findElement("id", "acctgTransTypeId"));
	   tranType.selectByVisibleText("Inventory");
	   
	   WebdriverWait.findElement("name", "fromDate_i18n").sendKeys(Common_Methods.getCurrentDate());
	   
	   
}
	
	
}
