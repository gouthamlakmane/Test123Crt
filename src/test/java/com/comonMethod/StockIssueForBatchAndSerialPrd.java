package com.comonMethod;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.Facilities_Modules.Facilities_Shipments_ShipmentPlan;
import com.Utils.Base;
import com.Utils.CallJavascriptExecutor;
import com.Utils.WebdriverWait;

public class StockIssueForBatchAndSerialPrd extends Base{

	public static void issueSerialPrdQty() 
	{
		
		String qty = WebdriverWait.findElement("xpath", "//*[@id='list']/div[2]/table/tbody/tr[2]/td[4]").getText();
		int count=Integer.parseInt(qty);
		
		for(int i =0; i<count; i++)
		{
			WebElement ele = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/form/table/tbody/tr["+(i+1)+"]/td[9]/input");
			CallJavascriptExecutor.scrollToElement(ele);
			WebdriverWait.findElement("id","quantity_o_" + (i)).sendKeys("1");
			ele.click();
			Facilities_Shipments_ShipmentPlan.rowIncreement();
		}
		
		WebdriverWait.findElement("id", "sub1").click();
		
	}

	public static void issueBatchPrdQty()
	{
		String qty = WebdriverWait.findElement("xpath", "//*[@id='list']/div[2]/table/tbody/tr[2]/td[4]").getText();
		int count=Integer.parseInt(qty);
		WebdriverWait.findElement("id","quantity_o_0").sendKeys(qty);
		WebdriverWait.findElement("name", "_rowSubmit_o_0").click();
		WebdriverWait.findElement("id", "sub1").click();
	}

}
