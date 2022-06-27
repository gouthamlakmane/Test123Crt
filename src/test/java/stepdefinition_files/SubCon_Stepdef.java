package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.SubCon.SubCon_Order;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;

import io.cucumber.java.en.Given;

public class SubCon_Stepdef extends Base {
	
	List<HashMap<String, String>> Sub_Con_Data = CrestTestDataReader.get_Sub_Con_Data();
	
	@Given("^Create new Sub-Contracting order$")
	public void Create_new_Sub_Contracting_order() throws InterruptedException {
		
		SubCon_Order.createSubConOrder(Sub_Con_Data);
		SubCon_Order.assertions();
	}
	
	@Given("^Create Stock Issue for Sub-con order$")
	public void Create_Stock_issue_for_Sub_con_order() throws InterruptedException {
		
		SubCon_Order.createStockIssue(Sub_Con_Data);
		SubCon_Order.assertions();
	}
	
	@Given("^Create Stock Receipt for Sub-con order$")
	public void Create_Stock_Receipt_for_Sub_con_order() throws InterruptedException {
		
		SubCon_Order.createStockReceipt(Sub_Con_Data);
		SubCon_Order.assertions();
	}
	
	@Given("^Validating the order status$")
	public void Validating_the_order_status() throws InterruptedException {
		
		SubCon_Order.validatingnOrderStatus(Sub_Con_Data);
		SubCon_Order.assertions();
	}
	
	@Given("^Posting the Invoice for created order$")
	public void Posting_the_Invoice_for_created_order() throws InterruptedException {
		
		SubCon_Order.postingInvoice();
		SubCon_Order.assertions();
	}


}
