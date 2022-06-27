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

public class QPR_TC03_Stepdef extends Base
{
	List<HashMap<String, String>> QPR_TC03_data = CrestTestDataReader.get_QPR_TC03_Data();
	
	//@QPR_TC03-01 
    //Scenario: Verify Stock of the Product and create a quick production run transaction - QPR_TC03-01

	@Given("^Verify Stock of the Products before transactions - QPR_TC03_One$")
	public void getStockDetailBeforTran() throws Exception
	{
//		On_Hand_Stock_Report.getProductDetailsBeforeTran(QPR_TC03_data);
	}
	
	@Then("^add a finished goods id - QPR_TC03_One$")
	public void navigateToManufacturingModule() throws Exception
	{
		QuickProduction.createQuickproductionRun(QPR_TC03_data);
		Thread.sleep(200);
	}
	
	@Then("^create the production run transaction - QPR_TC03_One$")
	public void productionReporting() throws Exception {
		Thread.sleep(200);
		QuickProduction.createProdctionRunTran();
		QuickProduction.capturetranId("QPR_TC03");
	}

	@Then("^update the raw material quantity - QPR_TC03_One$")
    public void cancel_the_raw_material() throws Exception
    {
		QuickProduction.updateRawMaterialFromList(QPR_TC03_data,4);
		Thread.sleep(200);
		QuickProduction.clickQuickCompleteLink();
    }

	@Then("^declare the production run - QPR_TC03_One$")
	public void declareTran() throws Exception
	{
		QuickProduction.declareFg();
	}
	
	//@QPR_TC03-02
	//Scenario: Validate the cost and Stock of the Products - @QPR_TC03-02

	@Then("^Validate the cost of the transaction - QPR_TC03_Two$")
	public void validate_Cost_of_the_transaction() throws Exception
	{
		QuickProduction.searchProductionRun(QPR_TC03_data);
		QuickProduction.validateActualCostsSummary(QPR_TC03_data);
	}
	@Then("^Verify the Stock of the Products After transactions - QPR_TC03_Two$")
	public void getStockDetailAfterTran() throws Exception
	{
		On_Hand_Stock_Report.getProductDetailsAfterTran(QPR_TC03_data);
	}
	
//	//@QPR_TC03-03
//	//Scenario: Transactions Validations under Finance Module -@QPR_TC03-03
//	@Given("^Validate Accounting transactions -@QPR_TC03-03$")
//	public void validateGLentries() throws Exception
//	{
//		Financials_AcctTransaction.Acct_Trans_Val();
//		
//	}
}
