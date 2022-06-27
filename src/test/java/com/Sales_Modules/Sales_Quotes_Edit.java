package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.support.ui.Select;

import com.Crest_ERP_Login.Crest_Login;
import com.Utils.Base;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class Sales_Quotes_Edit extends Base {
	@Rule
	public static JUnitSoftAssertions Sales_QuotesEdit_softAssert = new JUnitSoftAssertions();

	public static void Capture_SO_QuoteID(String sheetname) {

		String quoteNum = WebdriverWait.findElement("xpath", "//*[@id='updateQuote']/div/table/tbody[1]/tr[1]/td[2]").getText();
		System.out.println("SO Quote ID = " + quoteNum);
		ExcelWriter.writeExcelFile(sheetname, 15, 3, quoteNum);
	}
	
	public static void SO_Accept_Quote(String sheetname) {
		
		String QuoteID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div[2]/table/tbody/tr[1]/td[2]").getText();
		System.out.println(QuoteID);
		ExcelWriter.writeExcelFile(sheetname, 15, 3, QuoteID);
		Crest_Login.PurchaseVP_login();
		WebdriverWait.findElement("xpath", "//*[@class='num']").click();
		WebdriverWait.findElement("xpath", "(//*[@class='buttontext'])[1]").click();
		WebdriverWait.findElement("id", "workflowComments").sendKeys("Ok");
		WebdriverWait.findElement("xpath", "//*[text()='Approve']").click();
		
		Crest_Login.PurchaseCEO_login();
		WebdriverWait.findElement("xpath", "//*[@class='num']").click();
		WebdriverWait.findElement("xpath", "(//*[@class='buttontext'])[1]").click();
		WebdriverWait.findElement("id", "workflowComments").sendKeys("Ok");
		WebdriverWait.findElement("xpath", "//*[text()='Approve']").click();
		
		String approvestatus = WebdriverWait.findElement("xpath", "//*[@class='basic-table']/tbody/tr[2]/td[4]").getText();
		Sales_QuotesEdit_softAssert.assertThat(approvestatus).isEqualTo("Approved");
		
//		WebdriverWait.findElement("xpath", "//*[text()=' Edit']").click();
//		Select status = new Select(WebdriverWait.findElement("id", "statusId"));
//		status.selectByVisibleText("Accept Quote");
//		WebdriverWait.findElement("xpath", "//*[@value='Update']").click();


	}
	
	
	////////////////////////////////////////////Negative Scenario////////////////////////////////////////////////
	
	public static String verify_Validation_Msg(List<HashMap<String, String>> data, int row, String colName) {
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesEdit_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));
		return validationMsg;
	}	
	
	public static void AcceptQuote_without_adding_lineItem_and_Validate(List<HashMap<String, String>> data, int row, String colName) {
		Select status_dd = new Select(WebdriverWait.findElement("id", "statusId"));
		status_dd.selectByVisibleText("Accept Quote");
		WebdriverWait.findElement("xpath", "//*[@id= 'updateQuote']/div/table/tbody[1]/tr[15]/td/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		Sales_QuotesEdit_softAssert.assertThat(validationMsg).isEqualTo(data.get(row).get(colName));	
	}
	
	public static void Validate_AcceptQuote_successMsg(List<HashMap<String, String>> data) {
		String successMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/p").getText();
		Sales_QuotesEdit_softAssert.assertThat(successMsg).isEqualTo(data.get(14).get("Column17"));
	}
	
	public static void Sales_Quote_Edit_assertions() {
		Sales_QuotesEdit_softAssert.assertAll();
	}
}
