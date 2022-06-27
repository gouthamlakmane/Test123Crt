package com.Facilities_Modules;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.CallJavascriptExecutor;
import com.Utils.WebdriverWait;
import com.comonMethod.StockIssueForBatchAndSerialPrd;
import com.comonMethod.WebDriverCommonlib;

public class Facilities_Shipments_ShipmentPlan extends Base {

	@Rule
	public static JUnitSoftAssertions SalesShipmentPlan_softAssert = new JUnitSoftAssertions();
	static int row=0;
	
	public static void rowIncreement()
	{
		row++;
	}
	
	public static void Add_ShipmentPlan(List<HashMap<String, String>> data, String plndQty_colnum) throws InterruptedException 
	{
		WebdriverWait.findElement("link", "Shipment Plan").click();
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		List<WebElement> Shpqty_List = WebdriverWait.findElements("xpath", "//*[@id='AddOrderItems']/table/tbody/tr");
		System.out.println(Shpqty_List.size());
		for (int a = 0; a <= Shpqty_List.size() - 2; a++) {

			WebdriverWait.findElement("name", "quantity_o_" + (a)).clear();
			WebdriverWait.findElement("name", "quantity_o_" + (a)).sendKeys(data.get(a).get(plndQty_colnum));
		}
		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();
	}

	public static void ShipmentQty_Val(List<HashMap<String, String>> data, String prdID, String ordQty_colnum, String plndQty_colnum) 
	{
		List<WebElement> Shpqty_List = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println("Shipmment plan table size" + Shpqty_List.size());
		int j = 0;
		for (int a = 2; a <= Shpqty_List.size(); a++) {
			String PrdId = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[4]").getText();

			System.out.println(PrdId);

			if (PrdId.equals(data.get(j).get(prdID))) {

				String totalOrdQty = WebdriverWait.findElement("xpath", "//*[@id='selectAllForm']/div[5]/table/tbody/tr[" + a + "]/td[8]")
								.getText();
				String totalPlannedQty = WebdriverWait.findElement("xpath", "//*[@id='selectAllForm']/div[5]/table/tbody/tr[" + a + "]/td[9]")
								.getText();
				Assert.assertEquals(data.get(j).get(ordQty_colnum), totalOrdQty);
				Assert.assertEquals(data.get(j).get(plndQty_colnum), totalPlannedQty);
				j++;
			}
		}
	}
	
  public static void issueInventory(List<HashMap<String, String>> data, int count,String flowType) 
	{
	  WebdriverWait.findElement("xpath", "//a[.='Order Items']").click();
	  if(flowType.equals("STO_TC07"))
	   {
		 int tableSize=WebdriverWait.findElements("xpath", "//*[@id='form']/table/tbody/tr").size();
		 LinkedHashSet<String> prdNameList = new LinkedHashSet<String>();

	    for(int a=0;a<= count; a++)
	    {
				prdNameList.add(data.get(a).get("Column2"));
			  }
		 
		 for(int i =1; i<=(tableSize)-1; i++)
			{
			    WebElement element= WebdriverWait.findElement("xpath", "//*[@id='form']/table/tbody/tr[" +(i+1)+ "]/td[2]");
			    CallJavascriptExecutor.scrollToElement(element);
			    CallJavascriptExecutor.highLighterElement(element);
			    String prdIDinGui = WebdriverWait.findElement("xpath", "//*[@id='form']/table/tbody/tr[" +(i+1)+ "]/td[2]").getText();
				String[] splitprd = prdIDinGui.split("\\[");
				String productIDinDet = splitprd[1].replaceAll("]", "");
				System.out.println("productIDinDet " + productIDinDet);
			
				if(prdNameList.contains(productIDinDet))
				{
					WebdriverWait.findElement("xpath", "//*[@id='form']/table/tbody/tr["+(i+1)+"]/td[3]/a").click();
					if (productIDinDet.startsWith("Ser")) 
					    {
						  WebDriverCommonlib.waitForPageToLoad();
						  StockIssueForBatchAndSerialPrd.issueSerialPrdQty();
					    }
			    else {
			    	   WebDriverCommonlib.waitForPageToLoad();
			    	   StockIssueForBatchAndSerialPrd.issueBatchPrdQty();
					 }
				}
			}
	    }  
	 
	 else if(flowType.equals(""))
	   {
		 int tableSize=WebdriverWait.findElements("xpath", "//*[@id='form']/table/tbody/tr").size();
			
			LinkedHashSet<String> prdNameList = new LinkedHashSet<String>();
			for(int a=3;a<= count; a++)
			  {
				prdNameList.add(data.get(a).get("Column2"));
			  }
			
			LinkedHashSet<String> prdNameList1 = new LinkedHashSet<String>();
			for(int a=0;a<= count; a++)
			  {
				prdNameList1.add(data.get(a).get("Column2"));
			  }
			 for(int i =1; i<=(tableSize+row)-1; i++)
				{
				    WebElement element= WebdriverWait.findElement("xpath", "//*[@id='form']/table/tbody/tr[" +(i+1)+ "]/td[2]");
				    CallJavascriptExecutor.scrollToElement(element);
				    CallJavascriptExecutor.highLighterElement(element);
				    String prdIDinGui = WebdriverWait.findElement("xpath", "//*[@id='form']/table/tbody/tr[" +(i+1)+ "]/td[2]").getText();
					String[] splitprd = prdIDinGui.split("\\[");
					String productIDinDet = splitprd[1].replaceAll("]", "");
					System.out.println("productIDinDet " + productIDinDet);
					if(prdNameList.contains(productIDinDet))
					{
						WebdriverWait.findElement("xpath", "//*[@id='form']/table/tbody/tr["+(i+1)+"]/td[3]/a").click();
						
						if (productIDinDet.startsWith("Ser")) 
						    {
							  WebDriverCommonlib.waitForPageToLoad();
							  StockIssueForBatchAndSerialPrd.issueSerialPrdQty();
						    }
						 
				    else {
				    	   WebDriverCommonlib.waitForPageToLoad();
				    	   StockIssueForBatchAndSerialPrd.issueBatchPrdQty();
						 }
					}
				}
	}
//	 else {
//		 
//		 int tableSize=WebdriverWait.findElements("xpath", "//*[@id='form']/table/tbody/tr").size();
//		 for(int i=0 ; i< (tableSize-1) ; i++)
//		 {
//			WebElement reservedQty = WebdriverWait.findElement("xpath", "(//input[@type='checkbox'])["+i+"]/../preceding-sibling::td[3]");
//			CallJavascriptExecutor.highLighterElement(reservedQty);
//			String qtyToenter= reservedQty.getText();
//			WebdriverWait.findElement("xpath", "(//input[@type='checkbox'])["+i+"]/../preceding-sibling::td[1]").clear();
//			WebdriverWait.findElement("xpath", "(//input[@type='checkbox'])["+i+"]/../preceding-sibling::td[1]").sendKeys(qtyToenter);
//			
//		 }
//		 
//	 }
 
	 
	}
  
	//////////////////////////////////// Sales Shipment Negative Flow ///////////////////////////////////////

	public static void AddShipmentPlan_NegFlow() {
		WebdriverWait.findElement("link", "Shipment Plan").click();
		WebdriverWait.findElement("xpath", "//*[@value='Submit']").click();
		WebdriverWait.findElement("xpath", "//*[@value='Add']").click();
	}

	public static void SalesShipmentPlan_assertions() {
		SalesShipmentPlan_softAssert.assertAll();
	}

	
}