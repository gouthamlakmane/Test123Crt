package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_Stock_Transfer_Note extends Base
 {

  public static void STO_Note_Posting(List<HashMap<String, String>> data,int row) 
	{
		WebdriverWait.findElement("link", "ACCOUNTS RECEIVABLE").click();
		WebdriverWait.findElement("id", "findOutgoingStockTransfer").click();
		WebdriverWait.findElement("id", "stockTransferNoteId").sendKeys(data.get(row).get("Column9"));
		WebdriverWait.findElement("id","submit").click();
		WebdriverWait.findElement("xpath","//*[@id='example']/tbody/tr/td[1]/a").click();
		try {
			AR_SalesInvoice_Header.STO_Invoice_Approve();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AR_SalesInvoice_Overview.STO_Invoice_Post();
		System.out.println("STO Invoice ID's Posted Successfully");
	}

}
