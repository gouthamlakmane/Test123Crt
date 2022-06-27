package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utils.Base;
import com.Utils.CallJavascriptExecutor;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Sales_Create_STO_Order extends Base
{
	

	public static void createSTO(List<HashMap<String, String>> data,String flowName) 
	  {
		
		WebdriverWait.findElement("xpath", "//a[.='Sales']").click();
		WebdriverWait.findElement("xpath", "//a[.=' Stock Transport Order']").click();
		WebdriverWait.findElement("xpath", "//a[.='Create Stock Transport Order']").click();
		WebdriverWait.findElement("name", "orderName").sendKeys(flowName);
		Select ToFacility_DD = new Select(WebdriverWait.findElement("id", "toFacilityId"));
		ToFacility_DD.selectByVisibleText(data.get(10).get("Column2"));
		Select prdStore_DD = new Select(WebdriverWait.findElement("id", "productStoreId"));
		prdStore_DD.selectByVisibleText(data.get(10).get("Column3"));
		Select salesChannel_DD = new Select(WebdriverWait.findElement("id", "salesChannelEnumId"));
		salesChannel_DD.selectByVisibleText("Web Channel");
		WebdriverWait.findElement("xpath", "//a[.='Continue']").click();
	}

	public static void addShpDetail(List<HashMap<String, String>> data) 
	{
		WebdriverWait.findElement("name", "internal_order_notes").sendKeys("In the past, the standard workhorse for inter and intra"
				+ "-office communication was the memorandum or memo. According to Merriam-Webster, a memo is"
				+ " “a usually brief written message"
				+ " or report from one person or department in a company or organization to another.”");
		
		
		WebdriverWait.findElement("name", "shippingNotes").sendKeys("where possible, you should avoid completing the "
				+ "Standard Shipping Note by hand because handwritten "
				+ "documents are often difficult to read and are more likely to contain inaccuracies");
	
		 Select shpFromFacility_DD = new Select(WebdriverWait.findElement("id", "0_shipGroupFacilityId"));
		 shpFromFacility_DD.selectByVisibleText(data.get(1).get("Column3"));
		 
		 Select shpMethod_DD = new Select(WebdriverWait.findElement("id", "0_shipping_method"));
		 shpMethod_DD.selectByVisibleText("Standard");
		 WebdriverWait.findElement("xpath", "//a[.='Continue']").click();
		 
	}

	public static void addPrd(List<HashMap<String, String>> data, int rowCount) 
	{
		for(int i = 0; i<= rowCount;i++)
		{
			 WebdriverWait.findElement("name", "add_product_id").sendKeys(data.get(i).get("Column2"));
			 WebdriverWait.findElement("id", "quantity").sendKeys(data.get(i).get("Column6"));
			
			 Select dept_DD = new Select(WebdriverWait.findElement("id", "departmentName"));
			 dept_DD.selectByVisibleText(data.get(i).get("Column4"));
			 
			 Select uom_DD = new Select(WebdriverWait.findElement("id", "quantityUomId"));
			 uom_DD.selectByVisibleText(data.get(i).get("Column5"));
			
			
			 WebdriverWait.findElement("id", "itemComment").sendKeys("This is a comment for the product");
			 WebdriverWait.findElement("xpath", "//input[@value='Add']").click();
		}
	// WebdriverWait.findElement("xpath", "//a[.='Continue']").click();
	}

	public static void reviewSTO(String excelPath, String sheet)
	{
		WebdriverWait.findElement("xpath", "//a[.='Continue']").click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebdriverWait.findElement("xpath", "//a[.='Create']").click();

		try {
			
			
		WebElement ele	=WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[2]/a");
		CallJavascriptExecutor.highLighterElement(ele);
			String stoID = WebdriverWait
					.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[2]/a")
					.getText();
			System.out.println("STO ID: " + stoID);
			ExcelWriter.writeExcelFile2(excelPath, sheet, 12, 4, stoID);
			System.out.println("Data is saved in sheet");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void reCalculateLineLevelSTO(List<HashMap<String, String>> data,int count) 
	{
		for(int i=1; i<=count;i++)
		{
		String priceValueFromGui = WebdriverWait.findElement("xpath", "(//input[@class='input_editable_tiny'])["+i+"]").getAttribute("value");
		System.out.println("priceValue: "+priceValueFromGui);
		String priceValueFromExc = data.get(i-1).get("Column7");
		if(!(priceValueFromExc.equals(priceValueFromGui)))
		   {
			 WebdriverWait.findElement("xpath", "(//input[@class='input_editable_tiny'])["+i+"]").clear();
			 WebdriverWait.findElement("xpath", "(//input[@class='input_editable_tiny'])["+i+"]").sendKeys(data.get(i-1).get("Column7"));
			 WebdriverWait.findElement("xpath", "(//input[@name='selectedItem'])["+i+"]").click();
			}
		}
	
		WebdriverWait.findElement("xpath", "//a[.='Recalculate Order']").click();
	 }
	
	
}
