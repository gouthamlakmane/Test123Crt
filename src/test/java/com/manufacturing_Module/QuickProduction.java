package com.manufacturing_Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;
import com.wait.Waits;

import io.cucumber.java.it.Data;

public class QuickProduction extends Base
{
	  public static JUnitSoftAssertions qp_softAssert = new JUnitSoftAssertions();
	  public static void createQuickproductionRun(List<HashMap<String, String>> data) throws Exception
	   {
		  WebdriverWait.findElement("xpath", "//a[.='Manufacturing']").click();
		  Thread.sleep(300);
		  WebdriverWait.findElement("xpath", "//a[.=' Production Run']").click();
		  Thread.sleep(300);
		  WebdriverWait.findElement("xpath", "//a[.='Quick Create Production Run']").click();
		  Thread.sleep(300);
		  WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column2"));
		  WebdriverWait.findElement("id", "quantity").sendKeys(data.get(0).get("Column6"));
		  Select routing = new Select(WebdriverWait.findElement("id", "routingId"));
		  routing.selectByVisibleText(data.get(0).get("Column14"));
		  Select facility = new Select(WebdriverWait.findElement("id", "facilityId"));
		  facility.selectByVisibleText(data.get(0).get("Column13"));
		  WebdriverWait.findElement("xpath", "//input[@type='submit']").click();
		 
	   }
	  
	  public static void createProdctionRunTran() throws Exception{
		  Thread.sleep(300);
		  WebdriverWait.findElement("xpath", "//input[@value='Create']").click();
		  Waits.waitForPageToLoad();
	  }
	  
	  public static void clickQuickCompleteLink() throws Exception
	   {
		  WebdriverWait.findElement("xpath", "//a[.='Quick Complete']").click();
		  Thread.sleep(200);
		  
	   }
	  
	  public static void capturetranId(String sheetName) throws Exception
	  {
		   Thread.sleep(500);
		   String productionID= WebdriverWait.findElement("xpath", "//a[.='Quick Complete']/../preceding-sibling::li").getText();
		    String arr[]= productionID.split("-");
		    System.out.println("Production run ID: "+arr[1].trim());
		    ExcelWriter.writeExcelFile2("./TestData/QuikProductionRun.xlsx", sheetName,2, 9, arr[1].trim());
	  }
	  
	  public static void validateRawMaterial(int loopcount, List<HashMap<String, String>> data)throws Exception
	  {
		  ArrayList prdFromExc = new ArrayList();
		  for(int i=0; i <loopcount; i++) 
		  {
			  prdFromExc.add(data.get(i+6).get("Column1"));
		  }
		  
		  for(int i=0;i<loopcount;i++) 
		  {
			 int size = WebdriverWait.findElements("xpath", "//div[@id='list']/div/table/tbody/tr").size();
			 String prdName= WebdriverWait.findElement("xpath", "//div[@id='list']/div/table/tbody/tr["+(i+1)+"]/td[3]").getText();
			 String prd[] =prdName.split("-");
			 if( prdFromExc.contains(prd[0].trim()))
			 {
				 String bomQtyFromGui= WebdriverWait.findElement("xpath", "//div[@id='list']/div/table/tbody/tr["+(i+1)+"]/td[3]").getText();
				 String bomQtyFromExc = data.get(i+6).get("Column2");
				 qp_softAssert.assertThat(bomQtyFromGui).as("BOM qty is not matching").isEqualTo(bomQtyFromExc);
			 }
			
		  }
		  
	  }
	  public static void declareFg() throws Exception {
		  Thread.sleep(200);
		  WebdriverWait.findElement("xpath", "//input[@value='Quick Complete Production Run']").click();
		  Waits.waitForPageToLoad();
		  String msg= driver.findElement(By.xpath("//div[@id='content-messages']/div/p")).getText();
		  qp_softAssert.assertThat(msg).as("production run is not created").isEqualTo("Stock has been received successfully");
	  }
	  
	  public static void searchProductionRun(List<HashMap<String, String>> data) throws Exception
	  {
		  Thread.sleep(200);
		  WebdriverWait.findElement("xpath", "//a[.='Manufacturing']").click();
		  Thread.sleep(200);
		  WebdriverWait.findElement("xpath", "//a[.=' Production Run']").click();
		  Thread.sleep(200);
		  WebdriverWait.findElement("id", "workEffortId").sendKeys(data.get(0).get("Column9"));
		  WebdriverWait.findElement("xpath","//input[@value='Search']").click();
		  Thread.sleep(200);
		  WebdriverWait.findElement("xpath","//*[@id='example']/tbody/tr/td[1]/a").click();
		  Thread.sleep(200);
		  WebdriverWait.findElement("xpath", "//a[.=' Cost Components']").click(); 
		  Thread.sleep(200);
	  }
	  
	  
	  public static void validateActualCostsSummary(List<HashMap<String, String>> data)throws Exception
	  {
//		int actualCostsSize= WebdriverWait.findElements("xpath", "(//table[@id='export_table'])[1]/tbody/tr").size();
		
			String materialCostGUI    = WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[2]/td[2]").getText();
			String labourCostGUI      = WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[3]/td[2]").getText();
			String routeCostGUI       = WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[4]/td[2]").getText();
			String generalCostGUI	  = WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[5]/td[2]").getText();
			String otherCostGUI       = WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[6]/td[2]").getText();
			String indirectCostGUI    = WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[7]/td[2]").getText();
			String totalCostGUI       = WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[8]/td[2]").getText();
			String totalPerUnitCostGUI= WebdriverWait.findElement("xpath", "(//table[@id='export_table'])[1]/tbody/tr[12]/td[2]").getText();
			
			
			String materialCostExc     = data.get(12).get("Column11");
			String labourCostExc       = data.get(13).get("Column11");
			String routeCostExc        = data.get(14).get("Column11");
			String generalCostExc      = data.get(15).get("Column11");
			String otherCostGUIExc     = data.get(16).get("Column11");
			String indirectCostExc     = data.get(17).get("Column11");
			String totalCostExc        = data.get(18).get("Column11");
			String totalPerUnitCostExc = data.get(20).get("Column11");
		
			qp_softAssert.assertThat(materialCostGUI).as("material cost is not matching").isEqualTo(materialCostExc);
			qp_softAssert.assertThat(labourCostGUI).as("labour cost is not matching").isEqualTo(labourCostExc);
			qp_softAssert.assertThat(routeCostGUI).as("route cost is not matching").isEqualTo(routeCostExc);
			qp_softAssert.assertThat(generalCostGUI).as("general cost is not matching").isEqualTo(generalCostExc);
			qp_softAssert.assertThat(otherCostGUI).as("other cost is not matching").isEqualTo(otherCostGUIExc);
			qp_softAssert.assertThat(indirectCostGUI).as("indirect cost is not matching").isEqualTo(indirectCostExc);
			qp_softAssert.assertThat(totalCostGUI).as("total cost is not matching").isEqualTo(totalCostExc);
			qp_softAssert.assertThat(totalPerUnitCostGUI).as("totalPerUnitCost  is not matching").isEqualTo(totalPerUnitCostExc);
}
	  
	  
	  
	  public static void qp_assertions() {
		  qp_softAssert.assertAll();
		}

	public static void addAdditionalRM(List<HashMap<String, String>> data,int loopcount) {
		
		for(int i=0; i< loopcount;i++)
		{
			WebdriverWait.findElement("name", "productIdRoutingTask").sendKeys(data.get(i+6).get("Column4"));
			WebdriverWait.findElement("id", "qtyRou").sendKeys(data.get(i+6).get("Column5"));
			Select routingId=new Select(WebdriverWait.findElement("id","routingTaskId"));
			routingId.selectByVisibleText(data.get(0).get("Column4"));
			WebdriverWait.findElement("xpath", "//a[.='Add']").click();
		}
	}

	public static void cancelRawMaterialFromList(List<HashMap<String, String>> data, int loopCount) throws Exception
	{
//		ArrayList prdID = new ArrayList();
//		for(int i = 0; i< loopCount ; i++) 
//		{
//			prdID.add(data.get(i+6).get("Column4"));
//		}
//		
//		// Consumption table size 
//		
		int tableSize= WebdriverWait.findElements("xpath", "//table[@class='basic-table light-grid']/tbody/tr").size();
		for(int i = 0; i< tableSize; i++)
		{
			String prdName = WebdriverWait.findElement("xpath", "//table[@class='basic-table light-grid']/tbody/tr["+(i+1)+"]/td[3]").getText();
			String prname[]= prdName.split("-");
			if(prname[0].trim().equals(data.get(6).get("Column1")))
			{
				WebdriverWait.findElement("xpath", "//table[@class='basic-table light-grid']/tbody/tr["+(i+1)+"]/td[8]/a[2]").click();
				Thread.sleep(100);
				driver.switchTo().alert().accept();
				Waits.waitForPageToLoad();
				break;
			}
		}
		
	}

	public static void updateRawMaterialFromList(List<HashMap<String, String>> data, int i) throws Exception
	{
		int tableSize= WebdriverWait.findElements("xpath", "//table[@class='basic-table light-grid']/tbody/tr").size();
		
		for(int k = 0; k< tableSize; k++)
		{
			String prdName = WebdriverWait.findElement("xpath", "//table[@class='basic-table light-grid']/tbody/tr["+(k+1)+"]/td[3]").getText();
			String prname[]= prdName.split("-");
			if(prname[0].trim().equals(data.get(6).get("Column1")))
			{
				WebdriverWait.findElement("xpath", "//table[@class='basic-table light-grid']/tbody/tr[1]/td[4]/input").clear();
				WebdriverWait.findElement("xpath", "//table[@class='basic-table light-grid']/tbody/tr[1]/td[4]/input").sendKeys("3");
				WebdriverWait.findElement("xpath", "//table[@class='basic-table light-grid']/tbody/tr["+(k+1)+"]/td[8]/a[1]").click();
				Thread.sleep(100);
				Waits.waitForPageToLoad();
				break;
			}
		}
		
	}

	public static void updateByProduct(List<HashMap<String, String>>data) {
		WebdriverWait.findElement("xpath", "//a[.=' By Products']").click();
		Waits.waitForPageToLoad();
		WebdriverWait.findElement("name", "productId").sendKeys(data.get(0).get("Column7"));
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column8"));
		WebdriverWait.findElement("xpath", "//input[@value='Create']").sendKeys(data.get(0).get("Column8"));
		
		WebdriverWait.findElement("xpath", "//a[.=' Production Reporting']").click();
	} 
	
}
