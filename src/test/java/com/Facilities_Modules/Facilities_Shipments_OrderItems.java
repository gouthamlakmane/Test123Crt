package com.Facilities_Modules;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.Crest_ERP_Login.Crest_Login;
import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_OrderItems extends Base {

	@Rule
	public static JUnitSoftAssertions purchaseShipmentOrderItems_softAssert = new JUnitSoftAssertions();

	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	public static void SO_Issue_Shpmnt() {

		WebdriverWait.findElement("link", "Order Items").click();
		// WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']//div//tr[3]//td//input").click();
		WebdriverWait.findElement("name", "selectAll").click();
		WebdriverWait.findElement("id", "submitButton").click();
	}
	
	// Added by goutham, purpose: issue the partial shipment for Sales order
	public static void SO_Issue_Shpmnt1(List<HashMap<String, String>> data, String plndQty_colnum) 
	{
		WebdriverWait.findElement("link", "Order Items").click();
		
		
		//calculate the size of the table
		List<WebElement> Add_Item_to_Ship = WebdriverWait.findElements("xpath", "//*[@id='form']/table/tbody/tr");
		int j = 0;
		for (int a = 0; a <= Add_Item_to_Ship.size() - 2; a++) {
			
			String shpQty = data.get(a).get(plndQty_colnum);
			System.out.println("Shipmment planed qty: " + shpQty);
			
			WebdriverWait.findElement("xpath","//*[@id='form']/table/tbody/tr["+(a+2)+"]/td[8]").sendKeys(data.get(a).get(plndQty_colnum));
			WebdriverWait.findElement("xpath","//*[@id='form']/table/tbody/tr["+(a+2)+"]/td[9]").click();
		 }
	
		WebdriverWait.findElement("id", "submitButton").click();
	}
	
	public static void PO_Recv_Shpmnt(List<HashMap<String, String>> data, String plndQty_column) throws InterruptedException {

		WebdriverWait.findElement("link", "Order Items").click();
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();
		List<WebElement> Shpqty_List = WebdriverWait.findElements("xpath", "//*[@id='AddOrderItems']/table/tbody/tr");
		for (int a = 0; a <= Shpqty_List.size() - 2; a++)
				{
				   WebdriverWait.findElement("id", "quantity_o_" + (a)).clear();
				   WebdriverWait.findElement("id", "quantity_o_" + (a)).sendKeys(data.get(a).get(plndQty_column));
			    }
		
		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();
		WebdriverWait.findElement("link", "Receive Inventory").click();
	}

	public static void PO_Return_IssueInv(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("link", "Order Items").click();
		WebdriverWait.findElement("id", "0_lookupId_button").click();
		WebdriverWait.findElement("xpath", "//*[@id='0_lookupId']/table/tbody/tr[4]/td[1]/a").click();
		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(0).get("Column27"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[2]/td[10]/input").click();
	}

	//////////////////////////////////////// Purchase Shipment negative
	//////////////////////////////////////// flow///////////////////////////////////////

	public static void InvalidQtyVal_AddToShipmentPlan_Table(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "Order Items").click();

		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		// Empty Qty 
//		Lakshmi
		WebdriverWait.findElement("id", "quantity_o_0").clear();

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String qty_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		// String qty_num = WebdriverWait.findElement("xpath", "//*[@id=
		// 'content-messages']/div/ul/li[2]").getText();

//		purchaseShipmentOrderItems_softAssert.assertThat(qty_req).isEqualTo(data.get(10).get("Column10"));
//		Lakshmi
		purchaseShipmentOrderItems_softAssert.assertThat(qty_req).isEqualTo(data.get(28).get("Column10"));
		//purchaseShipmentOrderItems_softAssert.assertThat(qty_num).isEqualTo(data.get(11).get("Column10"));

		// Adding Qty more than the Shipment Qty
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		WebdriverWait.findElement("id", "quantity_o_0").clear();
		WebdriverWait.findElement("id", "quantity_o_0").sendKeys("1000");

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();
		
//		Lakshmi - 28-09-2021
		Thread.sleep(3000);

		String valMsg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
//		Lakshmi
//		purchaseShipmentOrderItems_softAssert.assertThat(valMsg).contains("Not adding Order Item to plan for shipment");
		purchaseShipmentOrderItems_softAssert.assertThat(valMsg).contains("Quantity exceeds the Ordered plus Tolerance quantity.");

		// Adding Qty with special characters
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		WebdriverWait.findElement("id", "quantity_o_0").clear();
		WebdriverWait.findElement("id", "quantity_o_0").sendKeys(data.get(0).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String qty_num1 = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
//		Lakshmi
//		purchaseShipmentOrderItems_softAssert.assertThat(qty_num1).contains(data.get(12).get("Column10"));
		purchaseShipmentOrderItems_softAssert.assertThat(qty_num1).contains(data.get(29).get("Column10"));

		// Adding Qty with Negative Value
		WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();

		WebdriverWait.findElement("id", "quantity_o_0").clear();
		WebdriverWait.findElement("id", "quantity_o_0").sendKeys(data.get(1).get("Column1"));

		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();

		String qty_neg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
//		purchaseShipmentOrderItems_softAssert.assertThat(qty_neg).contains(data.get(12).get("Column10"));
		purchaseShipmentOrderItems_softAssert.assertThat(qty_neg).contains(data.get(30).get("Column10"));

	}

	public static void AddingComments_255Charac_Val(List<HashMap<String, String>> data) {
		
     	WebdriverWait.findElement("xpath", "//*[@id='FindOrderItems']/table/tbody/tr[3]/td/input").click();
//		WebdriverWait.findElement("id", "shipmentContentDescription_o_0").clear();
//		WebdriverWait.findElement("id", "shipmentContentDescription_o_0").sendKeys(data.get(6).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='AddOrderItems']/div/input").click();
		
		
		
	}

	public static void RemoveItemsIncluded() {
		
//		Lakshmi
//		List<WebElement> rows1 = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr");
		List<WebElement> rows1 = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]//tbody/tr");
		rows1.size();
		
		WebdriverWait.findElement("xpath", "//*[@id='removeOrderShipmentFromShipment']/a").click();

		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		List<WebElement> rows2 = WebdriverWait.findElements("xpath", "//*[@id='content-main-section']/div[5]/div[2]//tbody/tr");
		rows2.size();

		purchaseShipmentOrderItems_softAssert.assertThat(rows2.size()).isEqualTo(rows1.size() -1);

	}

	public static void PO_Recv_Shpmnt_Negative() throws InterruptedException {

		WebdriverWait.findElement("link", "Order Items").click();
		WebdriverWait.findElement("link", "Receive Inventory").click();
	}

	public static void ReceiveInvLink() {

		WebdriverWait.findElement("link", "Receive Inventory").click();
	}

	public static void OrderItemsLink() {

		WebdriverWait.findElement("link", "Order Items").click();
	}

	public static void MandatoryFields_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String invID = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		String qty_req = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[2]").getText();

		purchaseShipmentOrderItems_softAssert.assertThat(invID).contains(data.get(31).get("Column21"));
		purchaseShipmentOrderItems_softAssert.assertThat(qty_req).isEqualTo(data.get(31).get("Column10"));
	}
	

	public static void Invalid_Qty(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(3).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String zerovalue = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(zerovalue).contains(data.get(17).get("Column10"));

		// Negative Qty
		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(1).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String negvalue = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(negvalue).contains(data.get(18).get("Column10"));

		// More than the Order Qty
		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys("1000");
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

//		Lakshmi - 29-09-2021
//		String moreQty = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
//		purchaseShipmentOrderItems_softAssert.assertThat(moreQty).contains(data.get(19).get("Column10"));

		// Spl Charc
		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys("142140");

		WebdriverWait.findElement("name", "quantity").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

//		String SplCharc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
//		purchaseShipmentOrderItems_softAssert.assertThat(SplCharc).contains(data.get(2).get("Column10"));
	}

	public static void InvalidInventoryID_Val(List<HashMap<String, String>> data) {

		// Spl Charc
		WebdriverWait.findElement("name", "quantity").sendKeys("10");

		WebdriverWait.findElement("id", "0_lookupId_inventoryItemId").sendKeys(data.get(5).get("Column1"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[10]/input").click();

		String SplCharc = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[1]").getText();
		purchaseShipmentOrderItems_softAssert.assertThat(SplCharc).contains(data.get(17).get("Column21"));
	}

	public static void PurchaseShipmentOrderItems_assertions() {
		purchaseShipmentOrderItems_softAssert.assertAll();
	}
}
