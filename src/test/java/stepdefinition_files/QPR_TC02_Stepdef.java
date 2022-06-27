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

public class QPR_TC02_Stepdef extends Base
{
	List<HashMap<String, String>> QPR_TC02_data = CrestTestDataReader.get_QPR_TC02_Data();
	
	//@QPR_TC02-01 
    //Scenario: Verify the Stock of the Product -QPR_TC02-01
	
	@Given("^Verify the Stock of the Products before transactions - QPR_TC02_One$")
	public void getStockDetailBeforTran() throws Exception
	{
//		On_Hand_Stock_Report.getProductDetailsBeforeTran(QPR_TC02_data);
	}
	
	@Then("^add a finished goods id under production Reporting table - QPR_TC02_One$")
	public void navigateToManufacturingModule() throws Exception
	{
		QuickProduction.createQuickproductionRun(QPR_TC02_data);
		Thread.sleep(200);
	}
	@And("^add additional raw material - QPR_TC02_One$")
	public void add_additionalRawMaterial() {
		QuickProduction.addAdditionalRM(QPR_TC02_data,4);
	}
	
	@Then("^create the transaction - QPR_TC02_One$")
	public void productionReporting() throws Exception {
		Thread.sleep(200);
		QuickProduction.createProdctionRunTran();
		QuickProduction.capturetranId("QPR_TC02");
	}

	@Then("^cancel the raw material which is derived from BOM and create a transaction - QPR_TC02_One$")
    public void cancel_the_raw_material() throws Exception
    {
		QuickProduction.cancelRawMaterialFromList(QPR_TC02_data,4);
		Thread.sleep(200);
		QuickProduction.clickQuickCompleteLink();
    }

	@Then("^declare the production transactions by clicking Quick Complete Production Run button - QPR_TC02_One$")
	public void declareTran() throws Exception
	{
		QuickProduction.declareFg();
	}
	
	//@QPR_TC02-02	
	//Scenario: Validate the cost of the transaction and Stock of the Products - @QPR_TC02-02

	@Then("^Validate the cost of the transaction - QPR_TC02_Two$")
	public void validate_Cost_of_the_transaction() throws Exception
	{
		QuickProduction.searchProductionRun(QPR_TC02_data);
		QuickProduction.validateActualCostsSummary(QPR_TC02_data);
	}
	@Then("^Verify the Stock of the Products After transactions - QPR_TC02_Two$")
	public void getStockDetailAfterTran() throws Exception
	{
		On_Hand_Stock_Report.getProductDetailsAfterTran(QPR_TC02_data);
	}
	
//	//@QPR_TC02-03
//	//Scenario: Transactions Validations under Finance Module -@QPR_TC02-03
//	@Given("^Validate Accounting transactions -@QPR_TC02-03$")
//	public void validateGLentries() throws Exception
//	{
//		Financials_AcctTransaction.Acct_Trans_Val();
//		
//	}
}
