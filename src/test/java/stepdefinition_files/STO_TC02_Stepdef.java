package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.AccountReceivable_Modules.AR_SalesInvoice_Overview;
import com.AccountReceivable_Modules.AR_StockTransferInvoice_Overview;
import com.AccountReceivable_Modules.AR_Stock_Transfer_Note;
import com.Facilities_Modules.Facilities_Shipments_Edit;
import com.Facilities_Modules.Facilities_Shipments_ShipmentPlan;
import com.Facilities_Modules.Facilities_StockManagement_StockReceipt;
import com.Financials_Modules.Financials_AcctTransaction;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntry;
import com.Financials_Modules.Financials_AcctTransaction_GLTransactionViewEntryForSTO;
import com.Sales_Modules.Sales_Create_STO_Order;
import com.Sales_Modules.Sales_SalesOrder_View;
import com.Sales_Modules.Sales_Stock_Transfer_Order_View;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class STO_TC02_Stepdef extends Base 
{
	List<HashMap<String, String>> data = CrestTestDataReader.get_STO_TC02_Data();
	String excelPath = "./TestData/StockTransferOrder.xlsx";
	
// @STO_TC02-01
// Scenario: Create Stock transfer Order - STO_TC02

	@Given("^Create New Stock transfer Order - STO_TC_Two_One$")
	public void create_new_stock_transfer_order() throws Throwable {
//    	Facilities_StockManagement_StockReceipt.unplannedStockReceipt(data, 4, excelPath, "STO_TC02", 12, 14);
		Sales_Create_STO_Order.createSTO(data,"STO_TC02");
	}

	@Then("^Add Shipping Details - STO_TC_Two_One$")
	public void Add_Shipping_Details() throws Throwable {
		Sales_Create_STO_Order.addShpDetail(data);
	}

	@Then("^Add Order Items - STO_TC_Two_One$")
	public void Add_Order_Items() throws Throwable {
		Sales_Create_STO_Order.addPrd(data, 4);
	}

	@Then("^Review & Create STO - STO_TC_Two_One$")
	public void Review_and_Create_STO() throws Throwable {
		Sales_Create_STO_Order.reviewSTO(excelPath,"STO_TC02");
	}

	@Then ("^Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_Two_One$")
	public void Validat_SubTotal() throws Throwable {
		Sales_Stock_Transfer_Order_View.ValidatSubTotal(data);
		Sales_Stock_Transfer_Order_View.ValidateTax(data);
		Sales_Stock_Transfer_Order_View.ValidateGrandTotal(data);
		Sales_Stock_Transfer_Order_View.STO_View_assertions();
	}

	
//	@STO_TC02-02	
//	Scenario: Create a Shipment - STO_TC02
	
	@Given("^Create Shipment One - STO_TC_Two_Two$")
	public void first_Transfer_Outgoing_Shipment() throws Throwable 
	{
		Sales_Stock_Transfer_Order_View.creatfirstOutgoingShip(data,"STO_TC02","Column14","Column15","Column16",12,excelPath,"STO_TC02");
		Facilities_Shipments_Edit.STO_ShpStatus();
		Sales_Stock_Transfer_Order_View.capture_InvoiceId(excelPath,"STO_TC02",12,9);
	}
	
	@Then("^Create Shipment two - STO_TC_Two_Two$")
	public void update_Shipment_Status() throws Throwable 
	{
		Sales_Stock_Transfer_Order_View.creatfirstOutgoingShip(data,"STO_TC02","Column21","Column15","Column22",13,excelPath,"STO_TC02");
		Facilities_Shipments_Edit.STO_ShpStatus();
		Sales_Stock_Transfer_Order_View.capture_InvoiceId2(excelPath,"STO_TC02",13,9);
	}
	
	@Then("^Create Shipment Three - STO_TC_Two_Two$")
	public void captureInvoiceId() throws Throwable 
	{
		Sales_Stock_Transfer_Order_View.creatfirstOutgoingShip(data,"STO_TC02","Column27","Column15","Column28",14,excelPath,"STO_TC02");
		Facilities_Shipments_Edit.STO_ShpStatus();
		Sales_Stock_Transfer_Order_View.capture_InvoiceId3(excelPath,"STO_TC02",14,9);
	}
	
//	@STO_TC02-03
//	Scenario: Update and post the Stock transfer Note - STO_TC02
	
	@Given("^Invoice Posting for Generated Invoices - STO_TC_Two_three$")
	public void Update_Stock_transfer_Note()
	{
		AR_Stock_Transfer_Note.STO_Note_Posting(data,10);
		AR_StockTransferInvoice_Overview.Inv1_HeaderAmt_Val(data);
		AR_StockTransferInvoice_Overview.STO_Inv1_SubTotal_Validations(data,10,"Column10");
		AR_StockTransferInvoice_Overview.STO_Inv1_Tax_Val(data,10,"Column11");
		AR_StockTransferInvoice_Overview.STO_Inv1_GrdTotal_Val(data,10,"Column12");
		AR_StockTransferInvoice_Overview.STO_View_softAssert();
	}
	
// @STO_TC02-04
// Scenario: Receive the Transfer Incoming Shipment - STO_TC02
	@Given("^Search the Order No - STO_TC_Two_four$")
	public void searchSTOorder()
	{
		Sales_Stock_Transfer_Order_View.searchSTOorder(data);
		
	}
	
	@Then("^Create incoming Shipment one - STO_TC_Two_four$")
	public void createIncomingShpOne()
	{
		Sales_Stock_Transfer_Order_View.createIncomingShp1(excelPath,"STO_TC02",12);
		Sales_Stock_Transfer_Order_View.receiveInventory("STO_TC02");
	}
	
	@Then("^Create Shipment Two - STO_TC_Two_four$")
	public void createIncomingShpTwo()
	{
		Sales_Stock_Transfer_Order_View.searchSTOorder(data);
		Sales_Stock_Transfer_Order_View.createIncomingShp2(excelPath,"STO_TC02",13);
		Sales_Stock_Transfer_Order_View.receiveInventory("STO_TC02");
	}
	
	@Then("^Create Shipment Three - STO_TC_Two_four$")
	public void createIncomingShpThree()
	{
		Sales_Stock_Transfer_Order_View.searchSTOorder(data);
		Sales_Stock_Transfer_Order_View.createIncomingShp3(excelPath,"STO_TC06",14);
		Sales_Stock_Transfer_Order_View.receiveInventory("STO_TC02");
	}
	

// @STO_TC02-05
// Transactions Validations under Finance Module - STO_TC02
   @Given("^Validate Accounting transactions under Finance Module - STO_TC_Two_Five$")
	public void validateAccountingTran()
    {
	   Financials_AcctTransaction.Acct_Trans_Val();
	   Financials_AcctTransaction_GLTransactionViewEntryForSTO.Acct_Trans_Val_ShipID_1(data, 10, "Column14", "Column20");
	   Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();
    }
}
