package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.InputSource;
import org.openqa.selenium.support.ui.Select;

import com.Facilities_Modules.Facilities_Shipments_ShipmentPlan;
import com.Facilities_Modules.Facilities_Shipments_View;
import com.Utils.CallJavascriptExecutor;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Sales_Stock_Transfer_Order_View 
{
    @Rule
	public static JUnitSoftAssertions STO_View_softAssert = new JUnitSoftAssertions();

	public static void ValidatSubTotal(List<HashMap<String, String>> data)
			{
					String subTotalGui = WebdriverWait
							.findElement("xpath", "//*[contains(text(),'Sub Total')]/following-sibling::td").getText().replace(",", "").replace("₹", "");
					System.out.println("STO SubTotalGui: " + subTotalGui);
					String SubTotalExc = data.get(14).get("Column5");
					System.out.println("STO SubTotalExc: " + SubTotalExc);
					STO_View_softAssert.assertThat(subTotalGui).as("In Stock transfer view page, Subtotal is displaying wrong value")
							.isEqualTo(SubTotalExc);
					System.out.println("ValidatSubTotal is completed");
			}

	public static void ValidateTax(List<HashMap<String, String>> data) 
			{
				String totalTaxGui = WebdriverWait.findElement("xpath", "//*[@id='myBtn']/..//following-sibling::td").getText().replace(",", "").replace("₹", "");
				System.out.println("STO Total tax from Gui: " + totalTaxGui);
				String TotalTaxExc = data.get(14).get("Column6");
				System.out.println("STO Total tax from Exc: " + TotalTaxExc);
				STO_View_softAssert.assertThat(totalTaxGui)
						.as("In Stock transfer view page, Total Tax is displaying wrong value").isEqualTo(TotalTaxExc);
				System.out.println("ValidateTax is completed");
			}

	public static void ValidateGrandTotal(List<HashMap<String, String>> data) 
			{
				String grandTotalGui = WebdriverWait
						.findElement("xpath", "//*[contains(text(),'Grand Total')]/../following-sibling::td/span").getText().replace(",", "");
				System.out.println("STO Total tax from Gui: " + grandTotalGui);
				String grandTotalExc = data.get(14).get("Column7");
				System.out.println("STO Total tax from Exc: " + grandTotalExc);
				STO_View_softAssert.assertThat(grandTotalGui).as("In Stock transfer view page, grand Total is displaying wrong value")
						.isEqualTo(grandTotalExc);
				System.out.println("ValidateGrandTotal is completed");
			}
	public static void STO_View_assertions() 
	  {
		STO_View_softAssert.assertAll();
	 }

	public static void creatfirstOutgoingShip(List<HashMap<String, String>> data, String sheetname, String prdID_column, String ordQty_colnum, String plndQty_colnum,
			int row,String excPath,String flow) 
	{
		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", "Shipments").click();
		WebdriverWait.findElement("link", "Create Shipment").click();

		Select ShpType_DD = new Select(WebdriverWait.findElement("id", "shipmentTypeId"));
		ShpType_DD.selectByVisibleText("Transfer Outgoing Shipment");
		WebdriverWait.findElement("id", "0_lookupId_primaryOrderId").sendKeys(data.get(10).get("Column4"));
		WebdriverWait.findElement("id", "picklistBinId").click();
		WebdriverWait.findElement("id", "createSubmit").click();
		Facilities_Shipments_View.Capture_Stock_OutGoing_ShpID(excPath,sheetname, row);
		try
		   {
			 Facilities_Shipments_ShipmentPlan.Add_ShipmentPlan(data, plndQty_colnum);
		   } 
		
		catch (InterruptedException e) 
		 {
			e.printStackTrace();
		 }
	
		try {
			Thread.sleep(100);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		Facilities_Shipments_ShipmentPlan.ShipmentQty_Val(data, prdID_column, ordQty_colnum, plndQty_colnum);
		Facilities_Shipments_ShipmentPlan.issueInventory(data, 4,flow);
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("id", "submitButton").click();
		
	}

	public static void capture_InvoiceId(String excPath, String sheetName,int row, int cellNo) 
	 {  
		String STO_InvId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[1]/td[6]/a").getText();
		System.out.println("STO_InvId: = " + STO_InvId);
		ExcelWriter.writeExcelFile2(excPath, sheetName, row, cellNo, STO_InvId);
	 }
	
	public static void capture_InvoiceId2(String excPath, String sheetName,int row, int cellNo) 
	 {  
		String STO_InvId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[6]/a").getText();
		System.out.println("STO_InvId: = " + STO_InvId);
		ExcelWriter.writeExcelFile2(excPath, sheetName, row, cellNo, STO_InvId);
	 }
	
	public static void capture_InvoiceId3(String excPath, String sheetName,int row, int cellNo) 
	 {  
		String STO_InvId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[3]/td[6]/a").getText();
		System.out.println("STO_InvId: = " + STO_InvId);
		ExcelWriter.writeExcelFile2(excPath, sheetName, row, cellNo, STO_InvId);
	 }

	public static void searchSTOorder(List<HashMap<String, String>> exdata)
	{
		WebdriverWait.findElement("xpath", "//a[.='Facilities']").click();
		WebdriverWait.findElement("xpath", "//a[.=' Stock Management']").click();
		WebdriverWait.findElement("xpath", "//a[.=' Transfer Incoming Shipment']").click();
		
		WebdriverWait.findElement("id", "referenceDocumentId").sendKeys(exdata.get(10).get("Column4"));
		WebdriverWait.findElement("xpath", "//input[@type='submit']").click();
		WebElement ele= WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr["+1+"]/td[9]/a");
		CallJavascriptExecutor.highLighterElement(ele);
		ele.click();
	}

	public static void createIncomingShp1(String excelPath,String sheetname, int row) {
		
//		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[1]/td[9]/a").click();
		WebdriverWait.findElement("id", "createSubmit").click();
		WebdriverWait.findElement("xpath", "//a[.='View']").click();
		String shipID1 = WebdriverWait.findElement("xpath","//div[@class='screenlet']/div[2]/table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile2(excelPath,sheetname, row, 14, shipID1);
		System.out.println(" ... 1st shipment id captured successfuly..... ");
	}
public static void createIncomingShp2(String excelPath,String sheetname, int row) {
//	    WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[2]/td[9]/a").click();
		WebdriverWait.findElement("id", "createSubmit").click();
		WebdriverWait.findElement("xpath", "//a[.='View']").click();
		String shipID2 = WebdriverWait.findElement("xpath","//div[@class='screenlet']/div[2]/table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile2(excelPath,sheetname, row, 14, shipID2);
		System.out.println(" ... 2nd shipment id captured successfuly..... ");
	}
public static void createIncomingShp3(String excelPath,String sheetname, int row) {
//		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[3]/td[9]/a").click();
		WebdriverWait.findElement("id", "createSubmit").click();
		WebdriverWait.findElement("xpath", "//a[.='View']").click();
		String shipID3 = WebdriverWait.findElement("xpath","//div[@class='screenlet']/div[2]/table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile2(excelPath,sheetname, row, 14, shipID3);
		System.out.println(" ... 3rd shipment id captured successfuly..... ");
}
	

	public static void receiveInventory(String flowType) 
	{
		WebdriverWait.findElement("xpath", "//a[.='Order Items']").click();
		
		
		if(flowType.equals("STO_TC07"))
		{
			int tableSize=WebdriverWait.findElements("xpath", "//*[@id='AddOrderItems']/table/tbody/tr").size();
			for(int i=1; i< (tableSize);i++)
			 {
				 WebdriverWait.findElement("xpath", "(//a[contains(text(),'Map Lot/Serial No')])["+i+"]").click();
				 try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 WebdriverWait.findElement("xpath", "//input[@value='Save']").click();
			 }
		}
	else {
			WebdriverWait.findElement("xpath", "//*[@id='subTab']/ul/li/ul/li[3]/a").click();
			WebdriverWait.findElement("xpath", "//*[@id='subTab']/ul/li/ul/li[3]/a").click();
		}
	 
		WebdriverWait.findElement("xpath", "//input[@value='Receive']").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void editStockTransferOrder(List<HashMap<String, String>> data)
	{
		WebElement wb= WebdriverWait.findElement("xpath", "//a[.='Edit Order']");
		CallJavascriptExecutor.scrollToElement(wb);
		wb.click();
		WebElement tbody= WebdriverWait.findElement("xpath", "//form[@name='updateItemInfo']/table/tbody/tr[1]");
		CallJavascriptExecutor.scrollToElement(tbody);
		
		for(int i =1;i<6;i++)
		{
//			WebdriverWait.findElement("xpath", "//form[@name='updateItemInfo']/table/tbody/tr["+i+"]/td[8]").click();
			WebElement input =WebdriverWait.findElement("xpath", "//form[@name='updateItemInfo']/table/tbody/tr["+i+"]/td[8]/input");
			input.clear();
			input.sendKeys(data.get(i-1).get("Column21"));
		}
		WebdriverWait.findElement("name","selectAll").click();
		WebdriverWait.findElement("xpath", "//a[.='Recalculate Selected']").click();
		System.out.println("Qty is updated successfully...");
		
	}
	
	
  public static void createNewStockTransferOrder(String excelPath, String sheet)
	{
		WebElement wb= WebdriverWait.findElement("xpath", "//a[.='Create as New Transport Order']");
		CallJavascriptExecutor.scrollToElement(wb);
		wb.click();
		WebdriverWait.findElement("id", "chk").click();
		WebdriverWait.findElement("xpath", "//a[.='Approve Order']").click();
		String updatedSTONO = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[1]/div[2]/table/tbody/tr[1]/td[2]/a").getText();
		System.out.println("Updated STONO"+updatedSTONO );
		ExcelWriter.writeExcelFile2(excelPath, sheet, 12, 4, updatedSTONO);
		
	}

public static void addNewProduct(List<HashMap<String, String>> data,String prdName)
{
	WebElement wb= WebdriverWait.findElement("xpath", "//a[.='Edit Order']");
	CallJavascriptExecutor.scrollToElement(wb);
	wb.click();
	
	WebElement prd= WebdriverWait.findElement("id", "0_lookupId_productId");
	CallJavascriptExecutor.scrollToElement(prd);
	prd.sendKeys(prdName);
	WebdriverWait.findElement("id", "quantity").sendKeys(data.get(0).get("Column21"));

	 Select dept_DD = new Select(WebdriverWait.findElement("id", "departmentName"));
	 dept_DD.selectByVisibleText(data.get(0).get("Column4"));
	
	 WebdriverWait.findElement("name", "submitItems").click();
	 
	
	
}

public static void cancelOrder() 
{
	
	
}
	
}
