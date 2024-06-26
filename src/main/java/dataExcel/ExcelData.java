package dataExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelData {
	
	//Read the data from Excel and return the inputs
	public static String[] getExcel()
	{
		
		String[] inputs=new String[3];
	try
	{
		FileInputStream fl=new FileInputStream(new File("C:\\Users\\2318300\\eclipse-workspace\\2318300_Hrm\\testdata\\InputFile.xlsx"));
		
		XSSFWorkbook workbook=new XSSFWorkbook(fl);
		
		XSSFSheet sheet=workbook.getSheetAt(0);
		
		XSSFRow row=sheet.getRow(1);
		
		XSSFCell cell1=row.getCell(0);
		XSSFCell cell2=row.getCell(1);
		XSSFCell cell3=row.getCell(2);
		
		inputs[0]=cell1.toString();
		inputs[1]=cell2.toString();
		inputs[2]=cell3.toString();
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
	
	return inputs;
	
}
	
	//Write the data into Excel file 
	public static void excelOutput(String dash,String jobRole) throws Exception
	{
	
		
		FileOutputStream file=new FileOutputStream("C:\\Users\\2318300\\eclipse-workspace\\2318300_OrangeHRM\\ExcelData\\ExcelOutput.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		
		XSSFSheet sheet=workbook.createSheet();
		
		XSSFRow row=sheet.createRow(0);
		
		row.createCell(0).setCellValue("TEST DESCRIPTION");
		
		row.createCell(1).setCellValue("EXPECTED OUTPUT");
		
		row.createCell(2).setCellValue("ACTUAL OUTPUT");
		
		row.createCell(3).setCellValue("RESULT");
		
		
		XSSFRow row1=sheet.createRow(1);
		
		row1.createCell(0).setCellValue("String Contains Dashboard(P/F)");
		
		row1.createCell(1).setCellValue("String should contain Dashboard");
		
		row1.createCell(2).setCellValue("Current page have 'Dashboard'");
		
		row1.createCell(3).setCellValue(dash);
		
		
		XSSFRow row2=sheet.createRow(2);
		
		row2.createCell(0).setCellValue("Check 'JobTitles' field is there or not");
		
		row2.createCell(1).setCellValue("JobTitles field should be display");
		
		row2.createCell(2).setCellValue("JobTitles field displayed");
		
		row2.createCell(3).setCellValue(jobRole);
		
		workbook.write(file);
		
		workbook.close();
		
		file.close();
	}

}
