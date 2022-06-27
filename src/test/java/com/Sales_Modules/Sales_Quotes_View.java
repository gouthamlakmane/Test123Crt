package com.Sales_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Crest_ERP_Login.Crest_Login;
import com.Utils.Base;
import com.Utils.Common_Methods;
import com.Utils.WebdriverWait;

public class Sales_Quotes_View extends Base {
	
	static String exepath = "./src/main/java/com/Utils/Crest_TestData.xlsx";
	@Rule
	public static JUnitSoftAssertions SO_QuotesView_softAssert = new JUnitSoftAssertions();

	public static void Quote_Header_Val() {
		
		WebdriverWait.findElement("xpath", "//*[text()='Create']").click();
		String quotesHeader = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/p").getText();
		Assert.assertEquals("Quote created successfully", quotesHeader);
		System.out.println("Quote Accepted Successfully");
	}

	public static void SO_Quotes_CreateOrderLink() {

		WebdriverWait.findElement("link", "Create Order").click();
		WebdriverWait.findElement("xpath", "//*[@id= 'paymentMethodTypeAndIdOFF'][@value = 'EXT_OFFLINE']").click();

		// WebdriverWait.findElement("xpath",
		// "//*[@id='content-main-section']/div[1]/div[1]/ul/li[4]/a").click();

	}

	public static void SO_Quotes_GrndTotal_Val(List<HashMap<String, String>> data, String Header) throws InterruptedException {

		log.info("Validating SO Quotes GrandTotal");
//		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
//				"//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr");
		
		Thread.sleep(1000);
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath","(//*[@class='basic-table light-grid'])[3]/tbody/tr");
		
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath",
					"(//*[@class='basic-table light-grid'])[3]/tbody/tr[" + a + "]/td[1]").getText();

			if (grandTotal.contains("Grand Total")) {

				String GrandTotal = WebdriverWait
						.findElement("xpath",
								"(//*[@class='basic-table light-grid'])[3]/tbody/tr[" + a + "]/td[2]")
						.getText().replace(",", "");
				System.out.println("GrandTotal" + GrandTotal);
				SO_QuotesView_softAssert.assertThat(GrandTotal).isEqualTo(data.get(17).get(Header));
				log.info("Grand Total validated Successfully");
				System.out.println("Grand Total Validated Successfully");
				
				
			}
		}

	}
	
	public static void createOrder(List<HashMap<String, String>> data, String sheetname) {
		
		
		WebdriverWait.findElement("id", "profileImage").click();
		WebdriverWait.findElement("link", "Logout").click();
		WebdriverWait.findElement("id", "login-username").sendKeys(prop.getProperty("username"));
		WebdriverWait.findElement("id", "login-password").sendKeys(prop.getProperty("password"));
		WebdriverWait.findElement("xpath", "//*[@id='loginform']/div[3]/div/input").click();
		
		String QuoteID = Common_Methods.getCellValue(exepath, sheetname, 15, 3);
		System.out.println(QuoteID);
		
		WebdriverWait.findElement("xpath", "//*[text()='Sales']").click();
		WebdriverWait.findElement("xpath", "//*[text()=' Quotes']").click();
		WebdriverWait.findElement("id", "quoteId").sendKeys(QuoteID);
		WebdriverWait.findElement("xpath", "//*[@Value='Search']").click();
		WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr[1]/td[3]/a").click();

	}

	public static void SO_View_assertions() {
		SO_QuotesView_softAssert.assertAll();
	}

}
