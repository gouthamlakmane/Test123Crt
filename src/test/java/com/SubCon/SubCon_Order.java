package com.SubCon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;


public class SubCon_Order extends Base {
	
	static String capturedata = "D:\\Projects\\Cucumber_POM_CREST_ERP\\src\\main\\java\\com\\Utils\\Crest_TestData_SubCon..xlsx";
	
	public static JUnitSoftAssertions validation = new JUnitSoftAssertions();
	
	public static void createSubConOrder(List<HashMap<String, String>> data) throws InterruptedException {
				
		int i = 1;
		String Pro_id = data.get(i).get("Column8");
		String Valpro_id = data.get(i).get("Column8");
		
		
		WebdriverWait.findElement("xpath", "//*[text()='SUB-CONTRACTING']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Subcontracting Order']").click();
		WebdriverWait.findElement("xpath", "//*[text()='New Subcontracting order']").click();
		
		Select organization = new Select(WebdriverWait.findElement("id", "organizationPartyId"));
		organization.selectByVisibleText("UIDAI");
		
		Select ordertype_ID = new Select(WebdriverWait.findElement("id", "orderTypeId"));
		ordertype_ID.selectByVisibleText("Normal Order");
		
		Select subcontractor = new Select(WebdriverWait.findElement("id", "subconPartyId"));
		subcontractor.selectByVisibleText("Ashok ENGINEERS Unit I");
		
		Select currency = new Select(WebdriverWait.findElement("id", "currencyUomId"));
		currency.selectByVisibleText("INR");
		
		Select department = new Select(WebdriverWait.findElement("id", "departmentPartyId"));
		department.selectByVisibleText("UIDAI - Purchase Dept");
		
		Select facility = new Select(WebdriverWait.findElement("id", "facilityId"));
		facility.selectByVisibleText("UIDAI - Inventory - WH");
		
		WebdriverWait.findElement("id", "description").sendKeys(data.get(i).get("Column7"));
		
		WebdriverWait.findElement("id", "0_lookupId_productId").sendKeys(data.get(i).get("Column8"));
		
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(i).get("Column9"));
		
		WebdriverWait.findElement("id", "rate").sendKeys(data.get(i).get("Column10"));
		
		WebdriverWait.findElement("name", "Add").click();
		
		WebdriverWait.findElement("id", "costBtn").click();
		
		for (HashMap<String, String> hashMap : data) {

			if(data.get(i).get("Column13")!=null) {
			
			if(Pro_id.equals(Valpro_id)) {
				
				List<WebElement> materialcosttable = WebdriverWait.findElements("xpath", "//*[@id='costSubContainer']//table/tbody/tr");
				
				for (int j = 1; j <= materialcosttable.size(); j++) {
					
					if(data.get(i).get("Column13")==null || ! Pro_id.equals(Valpro_id))
					{
						WebdriverWait.findElement("xpath", "//a[@id='addCost']").click();
						break;
					}	
					else {
					
					WebdriverWait.findElement("xpath", "//*[@id='costSubContainer']//table/tbody/tr["+ j +"]/td[1]/input").sendKeys(data.get(i).get("Column13"));
					WebdriverWait.findElement("xpath", "//*[@id='costSubContainer']//table/tbody/tr["+ j +"]/td[2]/input").sendKeys(data.get(i).get("Column14"));
					WebdriverWait.findElement("xpath", "//*[@id='costSubContainer']//table/tbody/tr["+ j +"]/td[3]/input").sendKeys(data.get(i).get("Column15"));
					WebdriverWait.findElement("xpath", "//*[@id='costSubContainer']//table/tbody/tr["+ j +"]/td[5]/select").sendKeys("Each");
					i++;
					Valpro_id = data.get(i).get("Column8");
					}	 
				}	
			}		
		}				
	}
		
		WebdriverWait.findElement("xpath", "//*[@class='button mainAction']").click();
		WebdriverWait.findElement("xpath", "//*[text()='Confirm']").click();
		String confirmStatus = WebdriverWait.findElement("xpath", "(//*[@class='screenlet-body'])[2]/table/tbody/tr[2]/td[4]").getText();
		validation.assertThat(confirmStatus).isEqualTo("Confirmed");
		
		Thread.sleep(3000);
		String orderno = WebdriverWait.findElement("xpath", "(//*[@id='content-main-section']//table)[2]/tbody/tr[2]/td[2]").getText();
		System.out.println(orderno);
		ExcelWriter.writeExcelFile2(capturedata, "Sub_Con_Data", i, 19, orderno);
		
	}
	
	public static void createStockIssue(List<HashMap<String, String>> data) {
		int i =1;
		
		WebdriverWait.findElement("xpath", "//*[text()='Facilities']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Stock Management']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Stock Issue']").click();
		WebdriverWait.findElement("xpath", "//*[text()='Create Stock Issue']").click();
		
		Select documentType = new Select(WebdriverWait.findElement("id", "type"));
		documentType.selectByValue("S");
		
		WebdriverWait.findElement("id", "facilityId").sendKeys(data.get(i).get("Column21"));
		WebdriverWait.findElement("id", "0_lookupId_productionRunId").sendKeys(data.get(i).get("Column19"));
			
		Select numberingType = new Select(WebdriverWait.findElement("id", "numberingFormatId"));
		numberingType.selectByVisibleText("STKISS");
		
		WebdriverWait.findElement("id", "submitButton").click();
		WebdriverWait.findElement("xpath", "//*[@name='selectAll']").click();
		
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		WebdriverWait.findElement("xpath", "//*[text()='Approve']").click();
		
		String issueID = WebdriverWait.findElement("xpath", "(//*[@class='screenlet-body'])[2]/table/tbody/tr[1]/td[2]").getText();
		System.out.println(issueID);
		
		ExcelWriter.writeExcelFile2(capturedata, "Sub_Con_Data", i+2, 26, issueID);
		
		String issuesucessmsg = WebdriverWait.findElement("xpath", "//*[@class='content']/p").getText();
		validation.assertThat(issuesucessmsg).isEqualTo("Products Issued Successfully.");
	
	}
	
	
	public static void createStockReceipt(List<HashMap<String, String>> data) throws InterruptedException {
		int i = 1;
		
		WebdriverWait.findElement("xpath", "//*[text()='Facilities']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Stock Management']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Stock Receipt']").click();
		WebdriverWait.findElement("xpath", "//*[text()='Create Stock Receipt']").click();
		
		Select documentType = new Select(WebdriverWait.findElement("id", "docType"));
		documentType.selectByValue("S");
		
		WebdriverWait.findElement("id", "facilityId").sendKeys(data.get(i).get("Column28"));
		WebdriverWait.findElement("id", "1_lookupId_productionRunId").sendKeys(data.get(i).get("Column19"));
		
		WebdriverWait.findElement("id", "submitButton").click();
		
		WebdriverWait.findElement("id", "quantity").sendKeys(data.get(i).get("Column31"));
		
		WebdriverWait.findElement("xpath", "//*[@value='Create']").click();
		
		String stockReceivemsg = WebdriverWait.findElement("xpath", "//*[@class='content']/p").getText();
		validation.assertThat(stockReceivemsg).isEqualTo("Stock has been received successfully");
		
		Thread.sleep(3000);
		
		WebdriverWait.findElement("xpath", "//*[text()='Approve']").click();	
		String receiptID = WebdriverWait.findElement("xpath", "(//*[@class='screenlet-body'])[2]/table/tbody/tr[2]/td[2]").getText();
		System.out.println(receiptID);
		
		ExcelWriter.writeExcelFile2(capturedata, "Sub_Con_Data", i+2, 33, receiptID);
	}
	
	public static void validatingnOrderStatus(List<HashMap<String, String>> data) {
		int i=1;
		
		WebdriverWait.findElement("xpath", "//*[text()='SUB-CONTRACTING']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Subcontracting Order']").click();
		
		WebdriverWait.findElement("id", "subConId").sendKeys(data.get(i).get("Column19"));
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		WebdriverWait.findElement("xpath", "//*[@id='table_wrapper']//table/tbody/tr[1]/td[1]/a").click();
		
		String status = WebdriverWait.findElement("xpath", "(//*[@class='screenlet-body'])[2]/table/tbody/tr[2]/td[4]").getText();
		validation.assertThat(status).as("Order status issue").isEqualTo("Completed");
						
	}
	
	public static void postingInvoice() {
		
		WebdriverWait.findElement("xpath", "//*[text()='Accounts Payable']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Purchase Invoices']").click();
		
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		String todayDate = (date.format(new Date()));
		
//		System.out.println(todayDate);
		
		WebdriverWait.findElement("name", "fromInvoiceDate_i18n").sendKeys(todayDate);
		WebdriverWait.findElement("xpath", "//*[@value='Search']").click();
		
		String supplier = WebdriverWait.findElement("xpath", "//*[@id='example_wrapper']/table/tbody/tr[1]/td[6]/a").getText();
		
		String supplierID = supplier.substring(supplier.indexOf('[') + 1,supplier.indexOf(']'));
		System.out.println(supplierID);
		
		if(supplierID.equals("200002")) {
		WebdriverWait.findElement("xpath", "//*[@id='example_wrapper']/table/tbody/tr[1]/td[1]/a").click();
		}
		
		WebdriverWait.findElement("id", "referenceNumber").sendKeys("Suborder" +" " + todayDate);
		WebdriverWait.findElement("xpath", "//*[text()='Update']").click();
			
		try {
		Alert alt = driver.switchTo().alert();
		alt.accept();
		} 
		catch (Exception e) {
		}	
		
		WebdriverWait.findElement("xpath", "//*[text()=' Approve']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Post']").click();
		
		String invoiceStatus = WebdriverWait.findElement("xpath", "(//*[@class='screenlet-body'])[2]/table/tbody/tr[7]/td[2]").getText();
		validation.assertThat(invoiceStatus).isEqualTo("Posted");
		
		
	}
	
	public static void assertions() {
		
		validation.assertAll();		

	}
	

}
