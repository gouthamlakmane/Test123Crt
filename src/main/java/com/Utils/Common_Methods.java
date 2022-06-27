package com.Utils;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Common_Methods extends Base {

	public static void Val_Msg(int num) {

		String Val_Msg = WebdriverWait.findElement("xpath", "//*[@id= 'content-messages']/div/ul/li[num]").getText();
	}



	private static void userdir() {

		String userdir = "D:\\Projects\\";
	}
	
	
	//for excel file
	public static String getCellValue(String excelPath,String sheet,int row,int cell)
	{
	String v="";
	try
	{
	Workbook wb=WorkbookFactory.create(new FileInputStream(excelPath));
	v=wb.getSheet(sheet).getRow(row).getCell(cell).toString();
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return v;
	}


	public static String getCurrentDate() {
		Date date = Calendar.getInstance().getTime();
	
	// Display a date in day, month, year format
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
		return today;
	}


	
    

}