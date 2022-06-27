package com.AccountReceivable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class AR_StockTransferInvoice_Overview extends Base 
{
	public static JUnitSoftAssertions SI_View_softAssert = new JUnitSoftAssertions();
	
	public static void Inv1_HeaderAmt_Val(List<HashMap<String, String>> data) 
	{
		String headerAmtFromGui = WebdriverWait.findElement("xpath", "//*[@id=\"content-main-section\"]/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]").getText().replace(",",
						"").replace("₹", "");

		SI_View_softAssert.assertThat(headerAmtFromGui).as("Validating stock invoice1 header amount").isEqualTo(data.get(10).get("Column12").replace("₹", "").replace(",", ""));

	}

	public static void STO_Inv1_SubTotal_Validations(List<HashMap<String, String>> data,int row, String col) 
	{
		String subTotal=WebdriverWait.findElement("xpath", "//td[.='Sub Total']/following-sibling::td").getText().replace(",", "").replace("₹", "");
		SI_View_softAssert.assertThat(subTotal).as("In Stock transfer Note, sub Total is not matching: ").isEqualTo(data.get(row).get(col));
	}

	public static void STO_Inv1_Tax_Val(List<HashMap<String, String>> data, int row, String col) 
	{
		String totalTax= WebdriverWait.findElement("xpath", "//button[@id='myBtn']/../following-sibling::td").getText().replace(",", "").replace("₹", "");
		SI_View_softAssert.assertThat(totalTax).as("In Stock transfer Note, sub Total is not matching: ").isEqualTo(data.get(row).get(col));
		System.out.println("STO Tax Amt validated Successfully");
	}

	public static void STO_Inv1_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col)
	{
		String grandTotal = WebdriverWait.findElement("xpath","//td[contains(text(),'Grand Total')]/following-sibling::td").getText().replace(",", "").replace("₹", "");
		SI_View_softAssert.assertThat(grandTotal).as("In Stock transfer Note, sub Total is not matching: ").isEqualTo(data.get(row).get(col));
	}

	public static void STO_View_softAssert() {
		SI_View_softAssert.assertAll();
	}
	
}
