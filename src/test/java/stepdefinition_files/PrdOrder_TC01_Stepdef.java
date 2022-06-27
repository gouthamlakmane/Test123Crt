//package stepdefinition_files;
//
//import java.util.HashMap;
//import java.util.List;
//
//import com.Facilities_Modules.Facilities_StockManagement_StockIssue;
//import com.Facilities_Modules.Facilities_StockManagement_StockReceipt;
//import com.Manufacturing_Modules.Manufacturing_ProductionOrder_Create;
//import com.Utils.Base;
//import com.Utils.CrestTestDataReader;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class PrdOrder_TC01_Stepdef extends Base 
//{
//	List<HashMap<String, String>> prddata = CrestTestDataReader.get_PrdOrder_TC01_Data();
//	String excFilePath= "./TestData/StockTransferOrder.xlsx";
//	//@PRDORDER_TC01-01
//	//Scenario: Create a production order - PRDORDER_TC01-01
//	
//	@Given("^Create a production order - PRDORDER_TC01-one$")
//	public void create_Production_Order()
//	{
//		Facilities_StockManagement_StockReceipt.unplannedStockReceipt(prddata, 2, excFilePath, "PRD_01", 12,4);
//		Manufacturing_ProductionOrder_Create.createPrdOrder(prddata,"PrdOrder_TC01","No");
//	}
//	
//	@Then("^Validate BOM qty - PRDORDER_TC01-one$")
//	public void validate_BOM_qty()
//	{
//		Manufacturing_ProductionOrder_Create.validateBOMQty(prddata);
//		Manufacturing_ProductionOrder_Create.prd_View_assertions();
//	}
//	
//	@Then("^Confirm the order - PRDORDER_TC01-one$")
//	public void confirm_Production_Order()
//	{
//		Manufacturing_ProductionOrder_Create.confirmOrder();
//		
//	}
//	
//	@Then("^Start the order - PRDORDER_TC01-one$")
//	public void start_Production_Order()
//	{
//		Manufacturing_ProductionOrder_Create.startOrder(excFilePath,"PRD_01",12,6);
//	}
//	
//	//@PRDORDER_TC01-02
//	//Scenario: Issue the raw terial as per production order requeast - PRDORDER_TC01-02
//	
//	@Given("^Create a stock issue for production order - PRDORDER_TC01-02$")
//	public void create_stock_issue()
//	{
//		Facilities_StockManagement_StockIssue.createProdcutionStockIssue(prddata,excFilePath,4,"PRD_01",12,7);
//	}
//	
//	//@PRDORDER_TC01-03
//	//Scenario: Update the production order Task - PRDORDER_TC01-03
//	@When("^Material status is fully issued - PRDORDER_TC01-03$")
//	public void verifyMaterialIssue_Status()
//	{
//		Manufacturing_ProductionOrder_Create.verifyMaterialIssueStatus(prddata);
//	}
//	
//	@Then("^Update the order task status - PRDORDER_TC01-03$")
//	public void update_order_task_status()
//	{
//		Manufacturing_ProductionOrder_Create.updateOrderStatus(prddata);
//	}
//	
//	@Then("^complete the task - PRDORDER_TC01-03$")
//	public void complete_task_status()
//	{
//		//Manufacturing_ProductionOrder_Create.completeOrderTask();
//	}
//	
//}
