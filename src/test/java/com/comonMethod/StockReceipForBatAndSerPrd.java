package com.comonMethod;

import java.util.HashMap;
import java.util.List;

import com.Utils.WebdriverWait;

public class StockReceipForBatAndSerPrd {

	public static void addStockForBatchProduct(List<HashMap<String, String>> data, int i)
	{
		WebdriverWait.findElement("id", "noOfLots_o_0").sendKeys("1");
		WebdriverWait.findElement("id", "getButton").click();
		WebdriverWait.findElement("id","acceptedQty_o_0_o_1").sendKeys(data.get(i).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[.='Save']").click();
		
	}

	public static void addStockForSerialProduct(List<HashMap<String, String>> data)
	{
		WebdriverWait.findElement("id", "getButton").click();
		WebdriverWait.findElement("xpath", "//a[.='Save']").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
