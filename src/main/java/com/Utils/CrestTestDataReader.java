package com.Utils;

import java.util.HashMap;
import java.util.List;


public class CrestTestDataReader {

	private static String Testdatapath = "C:\\Users\\goutham\\eclipse-workspace\\Cucumber_POM_CREST_ERP\\src\\main\\java\\com\\Utils\\";

	public static  List<HashMap<String, String>> getPOInvData() {
		return ExcelReader.readExcelDatafromFile(
						Testdatapath + "Crest_TestData.xlsx", "PO_Inv");
	}


	public static List<HashMap<String, String>> getPOInvAdhocData() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "PO_Adhoc");
	}


	public static List<HashMap<String, String>> getPOEncData() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "PO_Enc");
	}

	public static List<HashMap<String, String>> getSO_Offline_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_Offline");
	}

	public static List<HashMap<String, String>> getSO_BillingAcct_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_BillingAcct");
	}

	public static List<HashMap<String, String>> get_ML_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "ML_Data");
	}
	public static List<HashMap<String, String>> get_PO_Edit_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "PO_Edit");
	}

	public static List<HashMap<String, String>> get_TC02_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC02");
	}
	public static List<HashMap<String, String>> get_PO_MultiCharges_Discounts_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx",
						"PO_MultiCharges_Disc");
	}

	public static List<HashMap<String, String>> get_TC01_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC01");
	}
	public static List<HashMap<String, String>> get_TC03_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC03");
	}
	public static List<HashMap<String, String>> get_TC04_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC04");
	}
	public static List<HashMap<String, String>> get_TC07_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC07");
	}
	public static List<HashMap<String, String>> get_TC08_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC08");
	}
	public static List<HashMap<String, String>> get_TC05_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC05");
	}
	public static List<HashMap<String, String>> get_Test_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "Test");
	}
	public static List<HashMap<String, String>> get_TC06_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC06");
	}
	public static List<HashMap<String, String>> get_SO_TC01_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC01");
	}
	public static List<HashMap<String, String>> get_SO_TC02_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC02");
	}
	public static List<HashMap<String, String>> get_SO_TC05_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC05");
	}
	public static List<HashMap<String, String>> get_SO_TC06_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC06");
	}
	public static List<HashMap<String, String>> get_SO_TC07_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC07");
	}
	public static List<HashMap<String, String>> get_SO_TC10_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC10");
	}
	public static List<HashMap<String, String>> get_SO_TC11_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC11");
	}

	public static List<HashMap<String, String>> get_Sales_Validation_Msg_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "Sales_Val_Msg");
	}
	public static List<HashMap<String, String>> get_Proc_Val_Msg() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "Proc_Val_Msg");
	}
	
	public static List<HashMap<String, String>> get_Proc_Positive_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx",
						"Proc_Valid_Data_NegFlow");
	}

	public static List<HashMap<String, String>> get_Sales_PositveData_For_NegativeFlow() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx",
						"Sales_Valid_data_NegFlow");
	}
	public static List<HashMap<String, String>> get_TC01_2_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC01_2");
	}
	public static List<HashMap<String, String>> get_SO_Return_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC03");
	}
	public static List<HashMap<String, String>> get_SO_ReturnReplacement_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC04");
	}
	public static List<HashMap<String, String>> get_SO_DropShip_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC08");
	}
	public static List<HashMap<String, String>> get_PR01_TC03_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "PR01_TC03");
	}
	public static List<HashMap<String, String>> get_SO_TC09_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "SO_TC09");
	}
	public static List<HashMap<String, String>> get_AC_PO_TC12_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "AC_PO_TC12");
	}
	public static List<HashMap<String, String>> get_AC_PO_TC12_Data1() {
		return ExcelReader_FourDecimals.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx",
						"AC_PO_TC12");
	}
	public static List<HashMap<String, String>> get_AC_SO_TC12_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "AC_SO_TC12");
	}
	public static List<HashMap<String, String>> get_AC_PO_TC11_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "AC_PO_TC11");
	}
	public static List<HashMap<String, String>> get_AC_PO_TC11_Data1() {
		return ExcelReader_FourDecimals.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx",
						"AC_PO_TC11");
	}
	
	
	public static List<HashMap<String, String>> get_Sub_Con_Data() {
		return ExcelReader.readExcelDatafromFile(
						"D:\\Projects\\Cucumber_POM_CREST_ERP\\src\\main\\java\\com\\Utils\\Crest_TestData_SubCon..xlsx", "Sub_Con_Data");
	}
	public static List<HashMap<String, String>> get_MF_TC01_Data() {
		return ExcelReader.readExcelDatafromFile("./TestData/Manufacturing.xlsx"," MF01");
	}
	
	public static List<HashMap<String, String>> get_STO_TC06_Data() {
		return ExcelReader.readExcelDatafromFile("./TestData/StockTransferOrder.xlsx","STO_TC06");
	}
	
	public static List<HashMap<String, String>> get_STO_TC07_Data() {
		return ExcelReader.readExcelDatafromFile("./TestData/StockTransferOrder.xlsx","STO_TC07");
	}

	public static List<HashMap<String, String>> get_PrdOrder_TC01_Data() {
		return ExcelReader.readExcelDatafromFile("./TestData/Manufacturing.xlsx","PRD_01");
    }
	
	public static List<HashMap<String, String>> get_STO_TC02_Data(){
		return ExcelReader.readExcelDatafromFile("./TestData/StockTransferOrder.xlsx","STO_TC02");
	}
	
	public static List<HashMap<String, String>> get_STO_TC03_Data(){
		return ExcelReader.readExcelDatafromFile("./TestData/StockTransferOrder.xlsx","STO_TC03");
	}
	
	public static List<HashMap<String, String>> get_STO_TC04_Data(){
		return ExcelReader.readExcelDatafromFile("./TestData/StockTransferOrder.xlsx","STO_TC04");
	}
	
	public static List<HashMap<String, String>> get_STO_TC05_Data()
	{
		return ExcelReader.readExcelDatafromFile("./TestData/StockTransferOrder.xlsx", "STO_TC05");
	}
	
	public static List<HashMap<String, String>> get_QPR_TC01_Data()
	{
		return ExcelReader.readExcelDatafromFile("./TestData/QuikProductionRun.xlsx", "QPR_TC01");
	}
	
	public static List<HashMap<String, String>> get_QPR_TC02_Data()
	{
		return ExcelReader.readExcelDatafromFile("./TestData/QuikProductionRun.xlsx", "QPR_TC02");
	}
	
	public static List<HashMap<String, String>> get_QPR_TC03_Data()
	{
		return ExcelReader.readExcelDatafromFile("./TestData/QuikProductionRun.xlsx", "QPR_TC03");
	}
	
	public static List<HashMap<String, String>> get_QPR_TC04_Data()
	{
		return ExcelReader.readExcelDatafromFile("./TestData/QuikProductionRun.xlsx", "QPR_TC04");
	}
	
	public static List<HashMap<String, String>> get_TC09_Data() {
		return ExcelReader.readExcelDatafromFile(Testdatapath + "Crest_TestData.xlsx", "TC09");
	}
}