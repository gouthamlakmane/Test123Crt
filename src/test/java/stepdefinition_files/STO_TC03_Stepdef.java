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

public class STO_TC03_Stepdef extends Base 
{
	List<HashMap<String, String>> data = CrestTestDataReader.get_STO_TC03_Data();
	String excelPath = "./TestData/StockTransferOrder.xlsx";
	
// @STO_TC03-01
// Scenario: Create Stock transfer Order - STO_TC03

	@Given("^Create New Stock transfer Order - STO_TC_Three_One$")
	public void create_new_stock_transfer_order() throws Throwable {
//    	Facilities_StockManagement_StockReceipt.unplannedStockReceipt(data, 4, excelPath, "STO_TC03", 12, 14);
		Sales_Create_STO_Order.createSTO(data,"STO_TC03");
	}

	@Then("^Add Shipping Details - STO_TC_Three_One$")
	public void Add_Shipping_Details() throws Throwable {
		Sales_Create_STO_Order.addShpDetail(data);
	}

	@Then("^Add Order Items - STO_TC_Three_One$")
	public void Add_Order_Items() throws Throwable {
		Sales_Create_STO_Order.addPrd(data, 4);
	}

	@Then("^Review & Create STO - STO_TC_Three_One$")
	public void Review_and_Create_STO() throws Throwable {
		Sales_Create_STO_Order.reviewSTO(excelPath,"STO_TC03");
	}

	@Then ("^Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_Three_One$")
	public void Validat_SubTotal() throws Throwable {
		Sales_Stock_Transfer_Order_View.ValidatSubTotal(data);
		Sales_Stock_Transfer_Order_View.ValidateTax(data);
		Sales_Stock_Transfer_Order_View.ValidateGrandTotal(data);
		Sales_Stock_Transfer_Order_View.STO_View_assertions();
	}
	@Then("^Create as New Transport Order - STO_TC_Three_One$")
	public void Create_as_New_Transport_Order() throws Throwable 
	{
		Sales_Stock_Transfer_Order_View.createNewStockTransferOrder(excelPath,"STO_TC03");
	}
	
	@Then("^Edit the order - STO_TC_Three_One$")
	public void edit_Order() throws Throwable 
	{
		Sales_Stock_Transfer_Order_View.editStockTransferOrder(data);
//		Sales_Stock_Transfer_Order_View.addNewProduct(data,"STO006");
	}

	
//	@STO_TC03-02	
//	Scenario:Create a Shipment - STO_TC03
	
	@Given("^Create Shipment One - STO_TC_Three_Two$")
	public void first_Transfer_Outgoing_Shipment() throws Throwable 
	{
		Sales_Stock_Transfer_Order_View.creatfirstOutgoingShip(data,"STO_TC03","Column14","Column15","Column16",12,excelPath,"STO_TC03");
		Facilities_Shipments_Edit.STO_ShpStatus();
		Sales_Stock_Transfer_Order_View.capture_InvoiceId(excelPath,"STO_TC03",12,9);
	}
	
	
//	@STO_TC03-03
//	Scenario: Update and post the Stock transfer Note - STO_TC03
	
	@Given("^Invoice Posting for Generated Invoice - STO_TC_Three_three$")
	public void Update_Stock_transfer_Note()
	{
		AR_Stock_Transfer_Note.STO_Note_Posting(data,10);
		AR_StockTransferInvoice_Overview.Inv1_HeaderAmt_Val(data);
		AR_StockTransferInvoice_Overview.STO_Inv1_SubTotal_Validations(data,10,"Column10");
		AR_StockTransferInvoice_Overview.STO_Inv1_Tax_Val(data,10,"Column11");
		AR_StockTransferInvoice_Overview.STO_Inv1_GrdTotal_Val(data,10,"Column12");
		AR_StockTransferInvoice_Overview.STO_View_softAssert();
	}
	
// @STO_TC03-04
// Scenario: Receive the Transfer Incoming Shipment - STO_TC03
	@Given("^Search the Order No - STO_TC_Three_four$")
	public void searchSTOorder()
	{
		Sales_Stock_Transfer_Order_View.searchSTOorder(data);
		
	}
	
	@Then("^Create incoming Shipment one - STO_TC_Three_four$")
	public void createIncomingShpOne()
	{
		Sales_Stock_Transfer_Order_View.createIncomingShp1(excelPath,"STO_TC03",12);
		Sales_Stock_Transfer_Order_View.receiveInventory("STO_TC03");
	}
	

// @STO_TC03-05
// Scenario: Transactions Validations under Finance Module - STO_TC03
   @Given("^Validate Accounting transactions under Finance Module - STO_TC_Three_Five$")
	public void validateAccountingTran()
    {
	   Financials_AcctTransaction.Acct_Trans_Val();
	   Financials_AcctTransaction_GLTransactionViewEntryForSTO.Acct_Trans_Val_ShipID_1(data, 10, "Column14", "Column20");
	   Financials_AcctTransaction_GLTransactionViewEntry.AcctTrans_ShipID_softAssert();
    }
}
