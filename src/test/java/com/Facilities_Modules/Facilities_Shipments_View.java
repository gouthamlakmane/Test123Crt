package com.Facilities_Modules;

import javax.sound.midi.MidiDevice.Info;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;

import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Facilities_Shipments_View extends Base {

	@Rule
	public static JUnitSoftAssertions SalesShipmentView_softAssert = new JUnitSoftAssertions();
	public static Logger log = Logger.getLogger(Facilities_Shipments_View.class);

	public static void Shpmnt_Status_Val() {

		String shipStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//tr//td[4]").getText();
		Assert.assertEquals("Shipped", shipStatus);
	}
	

	public static void enrout_Status_Val() {
		String shipStatus = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']//tr//td[4]").getText();
		Assert.assertEquals("En-Route", shipStatus);
	}

	public static void Capture_ShpID(String sheetname, int row) {

		WebdriverWait.findElement("link", "View").click();
		String shipID = WebdriverWait.findElement("xpath","//div[@class='screenlet']/div[2]//table/tbody/tr[1]/td[2]").getText();
		ExcelWriter.writeExcelFile(sheetname, row, 5, shipID);
		System.out.println(" ... 1st shipment id captured successfuly..... ");
	}
	
	public static void Capture_Stock_OutGoing_ShpID(String excelPath,String sheetname, int row) {

		WebdriverWait.findElement("link", "View").click();
		String shipID = WebdriverWait.findElement("xpath","//div[@class='screenlet']/div[2]/table/tbody/tr[1]/td[2]").getText();
		System.out.println("Outgoing Shipment ID: "+shipID);
		ExcelWriter.writeExcelFile2(excelPath,sheetname, row, 8, shipID);
		System.out.println(" ... 1st shipment id captured successfuly..... ");
	}

	public static void PO_Shpmnt_Status_Val() {

		String shipStatus = WebdriverWait.findElement("xpath", "//div[@class='screenlet']/div[2]/table/tbody/tr[2]/td[4]").getText();
		Assert.assertEquals("Received", shipStatus);
		System.out.println(" .......PO shipment validating successfully...... ");
	}

	/////////////////////////////////// Sales Shipment negative
	/////////////////////////////////// flow//////////////////////////////////////////////

	public static void SalesShipmentView_assertions() {
		SalesShipmentView_softAssert.assertAll();
	}

	public static void clickPoLink() {
		WebdriverWait.findElement("xpath", "//*[@id='primaryOrderIdRowAndPrimaryShipGroupSeqId']/td[2]/a").click();
		
	}

}
