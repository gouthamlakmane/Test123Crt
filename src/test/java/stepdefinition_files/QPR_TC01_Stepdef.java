package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Financials_Modules.Financials_AcctTransaction;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;
import com.manufacturing_Module.QuickProduction;
import com.reports.On_Hand_Stock_Report;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class QPR_TC01_Stepdef extends Base
{
	List<HashMap<String, String>> QPR_TC01_data = CrestTestDataReader.get_QPR_TC01_Data();
	
	//@QPR_TC01-01 
    //Scenario: Verify the Stock of the Product -QPR_TC01-01
	
	@Given("^Verify the Stock of the Products before transactions -QPR_TC01_One$")
	public void getStockDetailBeforTran() throws Exception
	{
		On_Hand_Stock_Report.getProductDetailsBeforeTran(QPR_TC01_data);
		
	}
	
	@Then("^Navigate to manufacturing and select the Quick production run link and Enter the finished goods detils under Production Reporting table- QPR_TC01_One$")
	public void navigateToManufacturingModule() throws Exception
	{
		QuickProduction.createQuickproductionRun(QPR_TC01_data);
		QuickProduction.createProdctionRunTran();
		QuickProduction.capturetranId("QPR_TC01");
	}
	
	@Then("^Once raw material is displaying under the Consumption table, Validate the raw material quantity as per the BOM and complete the task- QPR_TC01_One$")
    public void validateAndCompleteTask() throws Exception
    {
		QuickProduction.validateRawMaterial(4,QPR_TC01_data);
		QuickProduction.clickQuickCompleteLink();
		
    }

	@Then("^declare the production transactions by clicking Quick Complete Production Run button - QPR_TC01_Two$")
	public void declareTran() throws Exception
	{
		QuickProduction.declareFg();
		
		
	}
	
	//@QPR_TC01-02	
	//Scenario: Validate the cost of the transaction and Stock of the Products - @QPR_TC01-02

	@Then("^Validate the cost of the transaction - QPR_TC01_Two$")
	public void validate_Cost_of_the_transaction() throws Exception
	{
		QuickProduction.searchProductionRun(QPR_TC01_data);
		QuickProduction.validateActualCostsSummary(QPR_TC01_data);
	}
	@Then("^Verify the Stock of the Products After transactions - QPR_TC01_Two$")
	public void getStockDetailAfterTran() throws Exception
	{
		On_Hand_Stock_Report.getProductDetailsAfterTran(QPR_TC01_data);
	}
	
//	//@QPR_TC01-03
//	//Scenario: Transactions Validations under Finance Module -@QPR_TC01-03
//	@Given("^Validate Accounting transactions -@QPR_TC01-03$")
//	public void validateGLentries() throws Exception
//	{
//		Financials_AcctTransaction.Acct_Trans_Val();
//		
//	}
}
