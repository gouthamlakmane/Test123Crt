package com.AccountsPayable_Modules;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.Utils.Base;
import com.Utils.CallJavascriptExecutor;
import com.Utils.ExcelWriter;
import com.Utils.WebdriverWait;

public class AP_PurchaseInvoices_Overview extends Base {

	@Rule
	public static JUnitSoftAssertions PI_View_softAssert = new JUnitSoftAssertions();
	public static JUnitSoftAssertions PI_View_OutstandingAmt_softAssert = new JUnitSoftAssertions();

	public static void PI_Post() throws InterruptedException {

		String partyName = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody/tr[2]/td[4]")
				.getText();

		if (partyName.contains("APPL-TN")) {
			WebdriverWait.findElement("link", "Post").click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} else {
			WebdriverWait.findElement("link", "Post").click();

			String InvID_Status = WebdriverWait
					.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[7]/td[2]")
					.getText();
			Assert.assertEquals("Posted", InvID_Status);
		}
	}

	public static void PI_Inv1_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String subTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String SubTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(SubTotal).as(" Inv1 Validating invoice subtotal value: ").isEqualTo(data.get(row).get(col));
				System.out.println(" ... Inv1  Sub Total validated Successfully ... ");
			}
		}
	}

	public static void PI_Inv1_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String totalTax = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			if (totalTax.contains("Tax Amount (Consolidated)")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String TotalTax = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(TotalTax).as("Inv1 TotalTax is validate: ").isEqualTo(data.get(row).get(col));
				System.out.println("Inv1 PI Tax Amt validated Successfully");
			}
		}
	}

	public static void PI_Inv1_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating GrandTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String GrandTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).as("Inv1 GrandTotal validation :").isEqualTo(data.get(row).get(col));

				System.out.println("Inv1 PI Grand Total validated Successfully");
			}
		}
	}

	public static void PI_Inv2_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String subTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String SubTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");

				PI_View_softAssert.assertThat(SubTotal).as("Inv2 SubTotal validating: ").isEqualTo(data.get(row).get(col));
				System.out.println("Inv2 PI Sub Total validated Successfully");
			}
		}
	}

	public static void PI_Inv2_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String totalTax = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (totalTax.contains("Tax Amount (Consolidated)")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String TotalTax = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(TotalTax).as("Inv2 TotalTax is validating: ").isEqualTo(data.get(row).get(col));

				System.out.println("PI Tax Amt validated Successfully");
			}
		}
	}

	public static void PI_Inv2_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");

		for (int a = 1; a <= orderItemsTable.size(); a++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String grandTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String GrandTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).as("Inv2 GrandTotal validating: ").isEqualTo(data.get(row).get(col));

				System.out.println("PI Grand Total validated Successfully");
			}
		}
	}

	public static void PI_Inv3_SubTotal_Validations(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating SubTotal");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String subTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (subTotal.contains("Sub Total (Item base Price)")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String SubTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");

				
				PI_View_softAssert.assertThat(SubTotal).as("Inv3 Sub Total validating").isEqualTo(data.get(row).get(col));
				System.out.println("Inv3 Sub Total validated Successfully");
			}
		}
	}

	public static void PI_Inv3_Tax_Val(List<HashMap<String, String>> data, int row, String col) {

		log.info("Validating Total Tax");
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String totalTax = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();
			System.out.println(totalTax);

			if (totalTax.contains("Tax Amount (Consolidated)")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String TotalTax = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(TotalTax).as("Inv3 Tax Amt validating: ").isEqualTo(data.get(row).get(col));
				
				System.out.println("Inv3 Tax Amt validated Successfully");
			}
		}
	}

	public static void PI_Inv3_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr");
		

		for (int a = 1; a <= orderItemsTable.size(); a++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String grandTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String GrandTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).as("Inv3 GrandTotal values are validating: ").isEqualTo(data.get(row).get(col));
				
				System.out.println("Inv3 Grand Total validated Successfully");
			}
		}
	}

	public static void Inv1_HeaderAmt_Val(List<HashMap<String, String>> data) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String headerAmt = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[4]/td[2]")
				.getText().replace(",", "");

		PI_View_softAssert.assertThat(headerAmt).as("Inv1 header amount validation: ").isEqualTo(data.get(13).get("Column9"));

	}

	public static void Inv2_HeaderAmt_Val(List<HashMap<String, String>> data) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String headerAmt = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[4]/td[2]")
				.getText().replace(",", "");

		PI_View_softAssert.assertThat(headerAmt).as("Inv2 header amount validation: ").isEqualTo(data.get(14).get("Column9"));

	}

	public static void Inv3_HeaderAmt_Val(List<HashMap<String, String>> data) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String headerAmt = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[4]/td[2]")
				.getText().replace(",", "");

		PI_View_softAssert.assertThat(headerAmt).as("Inv3 header amount validation: ").isEqualTo(data.get(15).get("Column9"));

	}

	public static void PI_Inv1_ThirdPartyTax_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[1]").click();// 3rd
																												// party
																												// Taxes
																												// Button

		List<WebElement> thirdPartyTaxTable = WebdriverWait.findElements("xpath",
				"//*[@id='tab_tab1_tax']/table/tbody/tr");
		int b = thirdPartyTaxTable.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String thirdPartyTotalTax = WebdriverWait
				.findElement("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
		PI_View_softAssert.assertThat(thirdPartyTotalTax).as("Inv1 thirdPartyTaxTable values are validating ").isEqualTo(data.get(13).get(col));
		System.out.println("Inv1 Third Party Tax Table validated successfully");
	}

	public static void PI_Inv2_ThirdPartyTax_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[1]").click();// 3rd
																												// party
																												// Taxes
																												// Button

		List<WebElement> thirdPartyTaxTable = WebdriverWait.findElements("xpath",
				"//*[@id='tab_tab1_tax']/table/tbody/tr");
		int b = thirdPartyTaxTable.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String thirdPartyTotalTax = WebdriverWait
				.findElement("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "");
		PI_View_softAssert.assertThat(thirdPartyTotalTax).as("Inv2 thirdPartyTaxTable values are validating ").isEqualTo(data.get(14).get(col));
		System.out.println("Inv2 Third Party Tax Table validated successfully");
	}

	public static void PI_Inv3_ThirdPartyTax_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[1]").click();// 3rd
																												// party
																												// Taxes
																												// Button

		List<WebElement> thirdPartyTaxTable = WebdriverWait.findElements("xpath",
				"//*[@id='tab_tab1_tax']/table/tbody/tr");
		int b = thirdPartyTaxTable.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String thirdPartyTotalTax = WebdriverWait
				.findElement("xpath", "//*[@id='tab_tab1_tax']/table/tbody/tr[" + b + "]/td[2]").getText()
				.replace(",", "");
		PI_View_softAssert.assertThat(thirdPartyTotalTax).as("Inv3 thirdPartyTaxTable values are validating ").isEqualTo(data.get(15).get(col));
		System.out.println("Inv3 Third Party Tax Table validated successfully");
	}

	public static void PI_Inv1_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();// 3rd
																												// party
																												// Charges
																												// Button

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath",
				"//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();
// Dollar sign is not required
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String thirdPartyTotalChargesGui = WebdriverWait
				.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "").replace("₹", "");
	
		String thirdPartyTotalChargesExc = data.get(15).get(col);
		String tpartyCharge=thirdPartyTotalChargesExc.replace("₹", "");
		
		PI_View_softAssert.assertThat(thirdPartyTotalChargesGui).as("Inv1 Third Party Charges Table validating: ").isEqualTo(tpartyCharge);
		System.out.println("Inv1 Third Party Charges Table validated successfully");

	}

	public static void PI_Inv2_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath",
				"//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String thirdPartyTotalChargesGui = WebdriverWait
				.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "").replace("₹", "");;
			
		String thirdPartyTotalChargesExc = data.get(15).get(col);
		String tpartyCharge= thirdPartyTotalChargesExc.replace("₹", "");
				
		PI_View_softAssert.assertThat(thirdPartyTotalChargesGui).as("Inv2 Third Party Charges Table validating: ").isEqualTo(tpartyCharge);
		System.out.println("Inv2 Third Party Charges Table validated successfully");

	}

	public static void PI_Inv3_ThirdPartyCharges_Table_Val(List<HashMap<String, String>> data, String col) {

		WebdriverWait.findElement("xpath", "//*[@id='taxAndChargesContainer']/div/div[1]/div/div[2]").click();

		List<WebElement> thirdPartyChargesTable = WebdriverWait.findElements("xpath",
				"//*[@id='tab_tab1_charges']/table/tbody/tr");
		int b = thirdPartyChargesTable.size();

		if(b>1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String thirdPartyTotalChargesGui = WebdriverWait
					.findElement("xpath", "//*[@id='tab_tab1_charges']/table/tbody/tr[" + b + "]/td[2]").getText().replace(",", "").replace("₹", "");;
					
					String thirdPartyTotalChargesExc = data.get(15).get(col);
					String tpartyCharge= thirdPartyTotalChargesExc.replace("₹", "");
					
					PI_View_softAssert.assertThat(thirdPartyTotalChargesGui).as("Inv3 Third Party Charges Table validating: ").isEqualTo(tpartyCharge);
		}
		
		System.out.println("Inv3 Third Party Charges Table validated successfully");

	}

	public static void PI_Inv1_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath",
				"//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(totalTax).as("Inv1 Tax Breakup Table Validating: ").isEqualTo(data.get(13).get("Column8"));

		System.out.println("Inv1 Tax Breakup Table Validated Successfully");
	}

	public static void PI_Inv2_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath",
				"//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(totalTax).as("Inv2 Tax Breakup Table Validating: ").isEqualTo(data.get(14).get("Column8"));

		System.out.println("Inv2 Tax Breakup Table Validated Successfully");
	}

	public static void PI_Inv3_TaxBreakup_Table_Val(List<HashMap<String, String>> data) {

		List<WebElement> taxBreakupTable = WebdriverWait.findElements("xpath",
				"//*[@id='taxContainer']/table/tbody/tr");
		int b = taxBreakupTable.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String totalTax = WebdriverWait.findElement("xpath", "//*[@id='taxContainer']/table/tbody/tr[" + b + "]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(totalTax).as("Inv3 Tax Breakup Table Validating: ").isEqualTo(data.get(15).get("Column8"));

		System.out.println("Inv3 Tax Breakup Table Validated Successfully");
	}

	public static void Inv1_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(13).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
		Thread.sleep(500);
		
		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String invOutstandingAmt = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]")
				.getText().replace(",", "");

		PI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).as("Inv1 Outstanding Amount Validating: ").isEqualTo(data.get(13).get("Column14"));
	}

	public static void Inv2_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(14).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
																													// Button
		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

		String invOutstandingAmt = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]")
				.getText().replace(",", "");

		PI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).as("Inv2 Outstanding Amount Validating: ").isEqualTo(data.get(14).get("Column14"));
	}

	public static void Inv3_OutstandingAmt_Val(List<HashMap<String, String>> data) throws InterruptedException {

		WebdriverWait.findElement("link", "ACCOUNTS PAYABLE").click();
		WebdriverWait.findElement("link", "Purchase Invoices").click();
		WebdriverWait.findElement("id", "invoiceId").sendKeys(data.get(15).get("Column6"));
		WebdriverWait.findElement("xpath", "//*[@id='FindPurchaseInvoice']/table/tbody[1]/tr[7]/td/input").click();// Search
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}																										// Button
		WebElement element = WebdriverWait.findElement("xpath", "//*[@id='example']/tbody/tr/td[1]/a");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String invOutstandingAmt = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[5]/td[2]")
				.getText().replace(",", "");

		PI_View_OutstandingAmt_softAssert.assertThat(invOutstandingAmt).as("Inv3 Outstanding Amount Validating: ").isEqualTo(data.get(15).get("Column14"));
	}

	public static void PayTerm_Inv1_PaidAmt_Val(List<HashMap<String, String>> data) {

		CallJavascriptExecutor.scrollToElement(WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a").click();
		
		WebElement wbElement = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]");
		CallJavascriptExecutor.scrollToElement(wbElement);
		CallJavascriptExecutor.highLighterElement(wbElement);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).as("Inv1 payterm1 validating: ").isEqualTo(data.get(13).get("Column18"));

		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[6]").getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).as("Inv1 payterm2 validating: ").isEqualTo(data.get(13).get("Column19"));
}

	public static void PayTerm_Inv2_PaidAmt_Val(List<HashMap<String, String>> data)
	{
		
		CallJavascriptExecutor.scrollToElement(WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a"));
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a").click();
		 WebElement wbElement = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]");
		 CallJavascriptExecutor.scrollToElement(wbElement);
		 CallJavascriptExecutor.highLighterElement(wbElement);
		
		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]").getText().replace(",", "");
		
		PI_View_softAssert.assertThat(payterm1).as("Inv2 payterm1 validating: ").isEqualTo(data.get(14).get("Column18"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[6]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).as("Inv2 payterm2 validating: ").isEqualTo(data.get(14).get("Column19"));

	}

	public static void PayTerm_Inv3_PaidAmt_Val(List<HashMap<String, String>> data) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a").click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[6]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).as("Inv3 payterm1 validating: ").isEqualTo(data.get(15).get("Column18"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[6]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).as("Inv3 payterm2 validating: ").isEqualTo(data.get(15).get("Column19"));

	}

	public static void PayTerm_Inv1_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a").click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).as("validating Inv1 payterm1: ").isEqualTo(data.get(13).get("Column18"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).as("validating Inv1 payterm2: ").isEqualTo(data.get(13).get("Column19"));

	}

	public static void PayTerm_Inv2_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a").click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).as("validating Inv2 payterm1: ").isEqualTo(data.get(14).get("Column18"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).as("validating Inv2 payterm2: ").isEqualTo(data.get(14).get("Column19"));

	}

	public static void PayTerm_Inv3_Val(List<HashMap<String, String>> data) {

		WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[4]/div/div[1]/ul/li[2]/a").click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm1 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[2]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm1).isEqualTo(data.get(15).get("Column18"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String payterm2 = WebdriverWait.findElement("xpath", "//*[@id='termsContainer']/table/tbody/tr[1]/td[5]")
				.getText().replace(",", "");
		PI_View_softAssert.assertThat(payterm2).isEqualTo(data.get(15).get("Column19"));

	}

	public static void verifySalesReturnInvoiceDetails_Prd_N_Qty(List<HashMap<String, String>> data) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String getProductID = WebdriverWait.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[4]").getText();
		String getProductID = WebdriverWait.findElement("xpath", "(//*[@class='screenlet-body'])[6]/table/tbody/tr/td[4]").getText();
		Assert.assertTrue(getProductID, getProductID.contains(data.get(0).get("Column25")));

		String getReturnedQty = WebdriverWait
//				.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[1]/td[6]")
				.findElement("xpath", "(//*[@class='screenlet-body'])[6]/table/tbody/tr/td[6]").getText();
		Assert.assertEquals(getReturnedQty, data.get(0).get("Column27"));
	}

	public static void verify_SalesReturn_SubTotal_Val(List<HashMap<String, String>> data) {

		log.info("Validating SubTotal");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String subTotal = WebdriverWait
				.findElement("xpath", "(//*[@class='screenlet-body'])[6]/table/tbody/tr[2]/td[3]").getText()
				.replace(",", "");
		System.out.println("SubTotal" + subTotal);
		PI_View_softAssert.assertThat(data.get(13).get("Column7")).isEqualTo(subTotal);
		System.out.println("Sub Total validated successfully");
	}

	public static void verify_SO_Tax_Val(List<HashMap<String, String>> data) {

		log.info("Validating Tax");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String grandTotal = WebdriverWait
//				.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[4]/td[3]").getText().replace(",", "");
				.findElement("xpath", "(//*[@class='screenlet-body'])[6]/table/tbody/tr[4]/td[3]").getText().replace(",", "");

		System.out.println("GrandTotal" + grandTotal);
		PI_View_softAssert.assertThat(data.get(17).get("Column8")).isEqualTo(grandTotal);
		log.info("Tax validated Successfully");
		System.out.println("Tax Validated Successfully");
	}
	

	public static void verify_SO_GrdTotal_Val(List<HashMap<String, String>> data) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String grandTotal = WebdriverWait
//				.findElement("xpath", "//*[@id='content-main-section']/div[6]/div[2]/table/tbody/tr[5]/td[3]/span").getText().replace(",", "");
				.findElement("xpath", "(//*[@class='screenlet-body'])[6]/table/tbody/tr[5]/td[3]").getText().replace(",", "");
		System.out.println("GrandTotal" + grandTotal);
		PI_View_softAssert.assertThat(data.get(17).get("Column9")).isEqualTo(grandTotal);
		log.info("Grand Total validated Successfully");
		System.out.println("Grand Total Validated Successfully");
	}

	public static void AddtionalCostPI_GrdTotal_Val(List<HashMap<String, String>> data, int row, String col) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> orderItemsTable = WebdriverWait.findElements("xpath",
				"//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr");
		System.out.println(orderItemsTable.size());

		for (int a = 1; a <= orderItemsTable.size(); a++) {

			String grandTotal = WebdriverWait.findElement("xpath",
					"//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[2]").getText();

			if (grandTotal.contains("Grand Total")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String GrandTotal = WebdriverWait
						.findElement("xpath",
								"//*[@id='content-main-section']/div[5]/div[2]/table/tbody/tr[" + a + "]/td[3]")
						.getText().replace(",", "");
				PI_View_softAssert.assertThat(GrandTotal).isEqualTo(data.get(row).get(col));
				log.info("PI Grand Total validated Successfully");
				System.out.println("Addtional Cost PI Grand Total validated Successfully");
			}
		}
	}

	public static void AdditionalCost_InvoiceIDCapture(String sheetname) {

		WebdriverWait.findElement("link", "Overview").click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inv_ID = WebdriverWait
				.findElement("xpath", "//*[@id='content-main-section']/div[2]/div[2]/table/tbody[1]/tr[1]/td[2]")
				.getText();
		ExcelWriter.writeExcelFile(sheetname, 16, 6, inv_ID);
	}

	public static void PI_View_softAssert() {
		PI_View_softAssert.assertAll();
	}

	public static void PI_View_OutstandingAmt_softAssert() {
		PI_View_OutstandingAmt_softAssert.assertAll();
	}

}
