package com.AccountReceivable_Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Sales_Modules.Sales_SalesOrder;
import com.Utils.Base;
import com.Utils.CallJavascriptExecutor;
import com.Utils.WebdriverWait;

public class AR_Receipts_Applications extends Base {
	public static Logger log = Logger.getLogger(Sales_SalesOrder.class);

	@Rule
	public static JUnitSoftAssertions AR_ReceiptsAppln_softAssert = new JUnitSoftAssertions();
	public static void SelectInvoiceID(List<HashMap<String, String>> data, String PaymentType) throws InterruptedException {

		WebdriverWait.findElement("link", "Applications").click();// Application
		// Link
		ArrayList<String> Invlist = new ArrayList<String>();

		for (int j = 13; j <= 16; j++) 
		{
			if(!(data.get(j).get("Column6").equals("."))) 
			{
			   Invlist.add(data.get(j).get("Column6"));
			}
		}
		
		System.out.println(Invlist);

		List<WebElement> Inv_Pay_tble = WebdriverWait.findElements("xpath", "//*[@id='example5']/tbody/tr");
		
		String totalTabletext = null;
		int count = 0;
		for (int y = 2; y <= Inv_Pay_tble.size(); y++) 
		{
			String before_xpath = "//*[@id='example5']/tbody/tr[";
			String after_xpath = "]/td";
			if (count > 6) {
				break;
			}
			totalTabletext = WebdriverWait.findElement("xpath", before_xpath + y + after_xpath).getText();

			if (Invlist.contains(totalTabletext)) 
			 {

				String before_xpath1 = "//*[@id='example5']/tbody/tr[";
				String after_xpath1 = "]/td[9]/input";

				if (PaymentType.contains("FullPayment")) 
				{

					WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();

				}
				
				else if (PaymentType.contains("PartialPayment")) 
				   {

					WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();

					    if (totalTabletext.contains(data.get(13).get("Column6"))) 
					      {

						    WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[" + y + "]/td[7]/input").clear();
						    WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[" + y + "]/td[7]/input").sendKeys("10000");

					      }
				} 
				else if (PaymentType.contains("ExchangeRate")) 
				{

					if (totalTabletext.contains(data.get(13).get("Column6"))) {
						WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();
					}
				}
				
				Thread.sleep(2000);
				count++;
			}
		}
		WebdriverWait.findElement("xpath", "//*[@type='submit']").click();
		System.out.println("before clicking Receive link");
		WebElement e = WebdriverWait.findElement("xpath", "//a[.=' Receive']");
		WebDriverWait w = new WebDriverWait(Base.driver, 5000);
		w.until(ExpectedConditions.elementToBeClickable(e));
	    e.click();
	    
//	    System.out.println("after clicking Receive link");
	    
	    Thread.sleep(1000);
	    driver.navigate().refresh();
		//CallJavascriptExecutor.getJavascriptExecutor(e);
	    WebdriverWait.findElement("xpath", "//a[.=' Overview']").click();
	    driver.navigate().refresh();
	    Thread.sleep(1000);
	    System.out.println("verifying application displaying overview screen");
	}

	public static void SelectInvoiceID_PayTerm(List<HashMap<String, String>> data, String PaymentType) throws InterruptedException {

		WebdriverWait.findElement("link", "Applications").click();// Application
		// Link
		ArrayList<String> Invlist = new ArrayList<String>();

		for (int j = 13; j <= 16; j++) {
			Invlist.add(data.get(j).get("Column6"));
		}
		System.out.println(Invlist);

		List<WebElement> Inv_Pay_tble = WebdriverWait.findElements("xpath", "//*[@id='example5']/tbody/tr");
		String totalTabletext = null;
		String payTermText = null;

		for (int y = 2; y <= Inv_Pay_tble.size(); y++) {
			String before_xpath = "//*[@id='example5']/tbody/tr[";
			String after_xpath = "]/td";

			totalTabletext = WebdriverWait.findElement("xpath", before_xpath + y + after_xpath).getText();
			payTermText = WebdriverWait.findElement("xpath", "//*[@id='example5']/tbody/tr[" + (y - 1) + "]/td[4]").getText();
			if (Invlist.contains(totalTabletext) && (payTermText.contains("Payment net days, part 1"))) {

				String before_xpath1 = "//*[@id='example5']/tbody/tr[";
				String after_xpath1 = "]/td[9]/input";

				WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();

			} else if (Invlist.contains(totalTabletext) && (payTermText.contains("Payment net days, part 2"))) {
				String before_xpath1 = "//*[@id='example5']/tbody/tr[";
				String after_xpath1 = "]/td[9]/input";

				WebdriverWait.findElement("xpath", before_xpath1 + y + after_xpath1).click();
			}
			 Thread.sleep(3000);
		}

		WebdriverWait.findElement("xpath", "//*[@type='submit']").click();
		
		WebElement e = WebdriverWait.findElement("xpath", "//a[.=' Receive']");
		WebDriverWait w = new WebDriverWait(Base.driver, 100);
		w.until(ExpectedConditions.elementToBeClickable(e));
		
		
		WebdriverWait.findElement("link", "Receive").click();
	}

	/////////////////////////////////////////////////Negative Flows/////////////////////////////////////

	public static void clickApplnTab() {
		WebdriverWait.findElement("link", "Applications").click();
	}

	public static void validate_Applying_Payment_without_selecting_any_checkbox(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div[1]/input").click();
		String validationMsg = WebdriverWait.findElement("xpath", "//*[@id='content-messages']/div/ul/li").getText();
		AR_ReceiptsAppln_softAssert.assertThat(validationMsg).isEqualTo(data.get(6).get("Column16"));
	}

	public static void validate_by_dismissing_PaymentConfirmationAlert(List<HashMap<String, String>> data) {
		WebdriverWait.findElement("name", "_rowSubmit_o_17").click();
		WebdriverWait.findElement("xpath", "//*[@id='createPaymentApplication']/div[2]/input").click();
	}

	public static void validate_by_dismissing_RemovalConfirmationAlert(List<HashMap<String, String>> data, boolean isLineItemExist_T_or_F) {

		WebdriverWait.findElement("link", "Remove").click();
		driver.switchTo().alert().dismiss();
		boolean isLineItemExist = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[3]/div[2]/table/tbody/tr[2]/td[1]").isDisplayed();
		AR_ReceiptsAppln_softAssert.assertThat(isLineItemExist).isEqualTo(isLineItemExist_T_or_F);
	}



	public static void AR_ReceiptsAppln_softAssert() {
		AR_ReceiptsAppln_softAssert.assertAll();
	}

}
