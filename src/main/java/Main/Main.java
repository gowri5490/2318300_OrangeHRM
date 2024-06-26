package Main;
import dataExcel.ExcelData;
import locate.LocateElement;
public class Main extends LocateElement {
	
	//Use main method to call all methods for implementation 
	public static void main(String args[]) throws Exception
	{
		LocateElement orangeHrm = new LocateElement();
		 orangeHrm.setupDriver();
		 orangeHrm.loginPage();
		 String dash= dashBoard();
		 String jRole= addJobRole();
	 	 ExcelData.excelOutput(dash,jRole);
		 orangeHrm.submit();
		 orangeHrm.LogOut();
		 orangeHrm.saveExtent();
		
		
		
	}

}
