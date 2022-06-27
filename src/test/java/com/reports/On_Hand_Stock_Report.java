package com.reports;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.Common_Methods;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class On_Hand_Stock_Report extends Base{
   
	
	public static void getProductDetailsBeforeTran(List<HashMap<String, String>> data) throws Exception
	{
		WebdriverWait.findElement("xpath", "//a[.='Facilities']").click();
		for(int i = 0; i< 9 ;i++) {
			
			WebdriverWait.findElement("xpath", "//a[.=' Reports']").click();
			Thread.sleep(200);
			WebdriverWait.findElement("xpath", "//a[.='On-Hand Stock']").click();
			Select organisation= new Select(WebdriverWait.findElement("id", "company"));
			organisation.selectByVisibleText(data.get(0).get("Column12"));
			
			Select facility= new Select(WebdriverWait.findElement("id", "facility"));
			facility.selectByVisibleText(data.get(0).get("Column13"));
			
			WebdriverWait.findElement("name","productId").sendKeys(data.get(i+12).get("Column14"));
			
			WebdriverWait.findElement("name","asOnDate_i18n").sendKeys(Common_Methods.getCurrentDate());
			
			WebdriverWait.findElement("xpath", "//input[@type='submit']").click();
			
		   String atp = WebdriverWait.findElement("xpath", "//*[@id='physicalInventoryData_tableRow_0']/td[7]").getText();
		   String qoh = WebdriverWait.findElement("xpath", "//*[@id='physicalInventoryData_tableRow_0']/td[8]").getText();
		   String val = WebdriverWait.findElement("xpath", "//*[@id='physicalInventoryData_tableRow_0']/td[9]").getText();
		   ExcelWriter.writeExcelFile2("./TestData/QuikProductionRun.xlsx", "QPR_TC01", (i+14), 15, atp);
		   ExcelWriter.writeExcelFile2("./TestData/QuikProductionRun.xlsx", "QPR_TC01", (i+14), 16, qoh);
		   ExcelWriter.writeExcelFile2("./TestData/QuikProductionRun.xlsx", "QPR_TC01", (i+14), 17, val);
			
		}
		System.out.println("--- report is done ---");
	}
	
	public static void getProductDetailsAfterTran(List<HashMap<String, String>> data) throws Exception
	{
		WebdriverWait.findElement("xpath", "//a[.='Facilities']").click();
		for(int i = 0; i< 9 ;i++) {
			
			WebdriverWait.findElement("xpath", "//a[.=' Reports']").click();
			Thread.sleep(200);
			WebdriverWait.findElement("xpath", "//a[.='On-Hand Stock']").click();
			Select organisation= new Select(WebdriverWait.findElement("id", "company"));
			organisation.selectByVisibleText(data.get(0).get("Column12"));
			
			Select facility= new Select(WebdriverWait.findElement("id", "facility"));
			facility.selectByVisibleText(data.get(0).get("Column13"));
			
			WebdriverWait.findElement("name","productId").sendKeys(data.get(i+12).get("Column14"));
			
			WebdriverWait.findElement("name","asOnDate_i18n").sendKeys(Common_Methods.getCurrentDate());
			
			WebdriverWait.findElement("xpath", "//input[@type='submit']").click();
			
		   String atp = WebdriverWait.findElement("xpath", "//*[@id='physicalInventoryData_tableRow_0']/td[7]").getText();
		   String qoh = WebdriverWait.findElement("xpath", "//*[@id='physicalInventoryData_tableRow_0']/td[8]").getText();
		   String val = WebdriverWait.findElement("xpath", "//*[@id='physicalInventoryData_tableRow_0']/td[9]").getText();
		   ExcelWriter.writeExcelFile2("./TestData/QuikProductionRun.xlsx", "QPR_TC01", (i+14), 18, atp);
		   ExcelWriter.writeExcelFile2("./TestData/QuikProductionRun.xlsx", "QPR_TC01", (i+14), 19, qoh);
		   ExcelWriter.writeExcelFile2("./TestData/QuikProductionRun.xlsx", "QPR_TC01", (i+14), 20, val);
			
		}
		System.out.println("--- report is done ---");
	}
}
