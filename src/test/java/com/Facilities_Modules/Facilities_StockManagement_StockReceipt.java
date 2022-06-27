package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.Select;

import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;
import com.comonMethod.StockReceipForBatAndSerPrd;

public class Facilities_StockManagement_StockReceipt {
	public static void unplannedStockReceipt(List<HashMap<String, String>> data, int rowCount,String filePath,String sheetName,int rowNum,int colNo)
	{
		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("xpath", " //*[.=' Stock Receipt']").click();
		WebdriverWait.findElement("link", "Create Stock Receipt").click();
	
	 for (int i = 0; i <= rowCount; i++) 
		{
			Select docType = new Select(WebdriverWait.findElement("id", "docType"));
			docType.selectByVisibleText("Unplanned");
			Select facility = new Select(WebdriverWait.findElement("id", "facilityId"));
			facility.selectByVisibleText(data.get(0).get("Column3"));
			WebdriverWait.findElement("name", "productId_o_0").sendKeys(data.get(i).get("Column2"));
			WebdriverWait.findElement("name", "quantityAccepted_o_0").clear();
			WebdriverWait.findElement("name", "quantityAccepted_o_0").sendKeys(data.get(i).get("Column6"));
			
			if (data.get(i).get("Column2").startsWith("Ser") || data.get(i).get("Column2").startsWith("Batch"))
			{
				WebdriverWait.findElement("id", "mapLink").click();
				 if(data.get(i).get("Column2").startsWith("Batch"))
				 {
				     StockReceipForBatAndSerPrd.addStockForBatchProduct(data,i);
				 }
				 else {
					 StockReceipForBatAndSerPrd.addStockForSerialProduct(data);
				 }
			}
			WebdriverWait.findElement("id", "unitCost").sendKeys(data.get(i).get("Column7"));
			WebdriverWait.findElement("id", "submitButton").click();
		  }
		WebdriverWait.findElement("xpath", "(//*[@id='submitButton'])[2]").click();
		
		String receiptId= WebdriverWait.findElement("xpath", "//*[@id='approveRequestForm']/div[1]/div[2]/table/tbody[1]/tr[2]/td[2]").getText();
		System.out.println("Stock receipt Id:"+receiptId);
		ExcelWriter.writeExcelFile2(filePath, sheetName, rowNum, colNo, receiptId);
		
		WebdriverWait.findElement("link", "Approve").click();
	}
	
 public static void productionRunStockReceipt(List<HashMap<String, String>> data, int rowCount,String filePath,String sheetName,int rowNum,int colNo)
	{
		WebdriverWait.findElement("link", "FACILITIES").click();
		WebdriverWait.findElement("link", "Stock Management").click();
		WebdriverWait.findElement("link", " Stock Receipt").click();

		WebdriverWait.findElement("link", "Create Stock Receipt").click();
		Select docType = new Select(WebdriverWait.findElement("id", "docType"));
		docType.selectByVisibleText("Production Run");	
			
		Select facility = new Select(WebdriverWait.findElement("id", "facilityId"));
		facility.selectByVisibleText(data.get(13).get("Column2"));
		
		WebdriverWait.findElement("name", "productionRunId").sendKeys(data.get(13).get("Column6"));
		WebdriverWait.findElement("id", "submitButton").click();
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(13).get("Column3"));
		
		// Reject part need to implement  
		
		WebdriverWait.findElement("xpath", "//*[@id='productionRunProduce']/div/input").click();
		String proreceiptId = WebdriverWait.findElement("xpath", "//*[@id='approveRequestForm']/div[1]/div[2]/table/tbody[1]/tr[2]/td[2]").getText();
		ExcelWriter.writeExcelFile2(filePath, sheetName, rowNum, colNo, proreceiptId);
		WebdriverWait.findElement("link", "Approve").click();
	}
	
}
	






