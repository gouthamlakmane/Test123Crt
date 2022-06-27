package stepdefinition_files;

import java.util.HashMap;
import java.util.List;

import com.Financials_Modules.Financials_AcctTransaction;
import com.Utils.Base;
import com.Utils.CrestTestDataReader;
import com.manufacturing_Module.QuickProduction;
import com.reports.On_Hand_Stock_Report;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class QPR_TC04_Stepdef extends Base
{
	List<HashMap<String, String>> QPR_TC04_data = CrestTestDataReader.get_QPR_TC04_Data();
	
	//@QPR_TC04-01 
	//Scenario: Verify the Stock of the Product and create a quick production run transaction - QPR_TC04-01
	
	@Given("^Verify the Stock of the Products before transactions - QPR_TC04_One$")
	public void getStockDetailBeforTran() throws Exception
	{
//		On_Hand_Stock_Report.getProductDetailsBeforeTran(QPR_TC02_data);
	}
	
	@Then("^add a finished goods id under production Reporting table - QPR_TC04_One$")
	public void navigateToManufacturingModule() throws Exception
	{
		QuickProduction.createQuickproductionRun(QPR_TC04_data);
		Thread.sleep(200);
	}
	@And("^add additional raw material - QPR_TC04_One$")
	public void add_additionalRawMaterial() {
		QuickProduction.addAdditionalRM(QPR_TC04_data,1);
	}
	
	@Then("^create the transaction - QPR_TC04_One$")
	public void productionReporting() throws Exception {
		Thread.sleep(200);
		QuickProduction.createProdctionRunTran();
		QuickProduction.capturetranId("QPR_TC04");
	}

	@Then("^adding the byproduct - QPR_TC04_One$")
    public void cancel_the_raw_material() throws Exception
    {
		QuickProduction.updateByProduct(QPR_TC04_data);
		Thread.sleep(200);
		QuickProduction.clickQuickCompleteLink();
    }

	@Then("^declare the production - QPR_TC04_One$")
	public void declareTran() throws Exception
	{
		QuickProduction.declareFg();
	}
	
	//@QPR_TC04-02	
	//Scenario: Validate the cost of the transaction and Stock of the Products - @QPR_TC04-02

	@Then("^Validate the cost of the transaction - QPR_TC04_Two$")
	public void validate_Cost_of_the_transaction() throws Exception
	{
		QuickProduction.searchProductionRun(QPR_TC04_data);
		QuickProduction.validateActualCostsSummary(QPR_TC04_data);
		QuickProduction.qp_assertions();
	}
	@Then("^Verify the Stock of the Products After transactions - QPR_TC04_Two$")
	public void getStockDetailAfterTran() throws Exception
	{
		On_Hand_Stock_Report.getProductDetailsAfterTran(QPR_TC04_data);
	}
	
//	//@QPR_TC04-03
//	//Scenario: Transactions Validations under Finance Module -@QPR_TC04-03
//	@Given("^Validate Accounting transactions -QPR_TC04-03$")
//	public void validateGLentries() throws Exception
//	{
//		Financials_AcctTransaction.Acct_Trans_Val();
//		
//	}
}
