//package stepdefinition_files;
//
//import java.util.HashMap;
//import java.util.List;
//
//import com.Manufacturing_Modules.Manufacturing_BOM_Create;
//import com.Utils.Base;
//import com.Utils.CrestTestDataReader;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//
//public class MF_TC01_Stepdef extends Base {
//
//	List<HashMap<String, String>> MF_TC01_data = CrestTestDataReader.get_MF_TC01_Data();
//	
//	
//	// @MF_TC01-01
//	// Scenario: Create BOM - MF01
//	
//	@Given("^Enter the Finish Good Id - MF_one$")
//	public void create_a_bom_mf_one()
//	{
//		Manufacturing_BOM_Create.createBOM(MF_TC01_data);
//	}
//
//	@Then("^Map the Raw materials - MF_one$")
//	public void Map_Raw_materials()
//	{
//		Manufacturing_BOM_Create.map_Raw_material(MF_TC01_data,4);
//	}
//	
//	
//	//@MF_TC01-02
//	// Scenario: BOM Simulate - MF01
//	@Given("^Simulate created  BOM - MF_one$")
//	public void simulate_created_bom() {
//		Manufacturing_BOM_Create.simulateBOM(MF_TC01_data);
//	}
//	
//	@Then("^verify the Similuted raw material quatity - MF_one$")
//	public void verify_simulated_RM_qty() {
//		Manufacturing_BOM_Create.verifySimiluted_rm_quatity(MF_TC01_data,4);
//	}
//	
//	//@MF_TC01-03
//	// Scenario: Edit the existing BOM- MF01
//	
//	@Given("^Edit the existing BOM - MF_one$")
//	public void Edit_the_existing_BOM() {
//		Manufacturing_BOM_Create.editBOM(MF_TC01_data);
//		Manufacturing_BOM_Create.validate_Rm_Qty(MF_TC01_data,4);
//	}
//}
