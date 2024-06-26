package locate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Browser.DriverSetup;
import dataExcel.ExcelData;



public class LocateElement{
	
	//Declaring static variable for implement driver and htmlReport
	static WebDriver driver;
	static String[] Elements=null;
	static int count=1;
	static ExtentSparkReporter htmlReporter;
	static ExtentReports extent;
	static ExtentTest test;
	
	//Get driver from DriverSetup class and return the Driver
	public WebDriver setupDriver()
	{
		//Initialize the htmlReporter path for store the HTML file
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\ExtentReport\\extentHtmlReporter.html");
	    
		//Use this class to extent the report
		extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        //use these methods to design the report
        htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Final Report");
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		htmlReporter.config().setTheme(Theme.DARK);
		
		//Get driver from DriverSetup class
		driver = DriverSetup.getWebDriver();
		
		 //Using implicit wait to handle the synchronization issue
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Using maximize the window
		driver.manage().window().maximize();
		
		//return the driver
		return driver;
		

	     
	}
	
	
	
	//Implement this static method for take a Screenshot wherever needed and store it in a file
	public static void TakeScreenShot(WebDriver driver) throws IOException
	{
		//type cast chrome driver to TakesScreenshot 
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(System.getProperty("user.dir")+"\\ScreenShot\\img"+count+".png");
		FileUtils.copyFile(src, trg);
		count++;
				
	}
	
	//Launch the browser using this method
	public void loginPage() throws Exception
	{
		//Launch the browser
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//Call this method to get the excel data
		Elements=ExcelData.getExcel();
		Thread.sleep(5000);
		
		// creates a toggle for the given test, adds all log events under it
		ExtentTest test1=extent.createTest("Orange HRM login page");
		
		
		
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
		userName.sendKeys(Elements[0]);
		  
		WebElement pass = driver.findElement(By.xpath("//div/input[@name='password']"));
		pass.sendKeys(Elements[1]);
		
		test1.log(Status.INFO, "This step shows usage of user login status");
		
		test1.log(Status.PASS, "Admin id and password validated");
	

		//Taking Screenshot
		TakeScreenShot(driver);
		
		//Add the screenshot to ExtentedHtmlReport.
		test1.addScreenCaptureFromPath("C:\\Users\\2318300\\eclipse-workspace\\2318300_OrangeHRM\\ScreenShot\\img1.png");
		
		WebElement login = driver.findElement(By.cssSelector("button[type='submit']"));
		login.click();
		Thread.sleep(2000);
		
		
	}
	
	
	//Verify current URL have dashboard label text using this method and return
	public static String dashBoard() throws IOException, InterruptedException
	{
		String dashResult=null;
		
		//Create the ExtentTest for ExtenthtmlReport
		ExtentTest test2=extent.createTest("Current URL contains Dashboard");
		
		//Store the value into string for get the Label text
		String label=driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")).getText();
		String e_label="Dashboard";
		
		test2.log(Status.INFO, "Check the url have Dashboard(Pass/Faill)");
		System.out.println();
		
		//Check label text have Dashboard or not
		if(label.equals(e_label))
		{
			dashResult="TestCase Passed";
			System.out.println("Check 'Dashboard' Labeltext is there or not");
			System.out.println(dashResult);
			test2.log(Status.PASS, "URL have the Dashboard Content");
		}
		else
		{
			dashResult="TestCase Failed";
			System.out.println("Check Dashboard is there or not");
			System.out.println(dashResult);
			test2.log(Status.FAIL, "URL didn't have Dashboard content");
		}
		return dashResult;
	}
	
	
	//Use this method to add Job role 
	public static String addJobRole()throws Exception
	{
		//Create ExtentTest for Admin  status in ExtentedHtmlreport 
		ExtentTest test3=extent.createTest("Click Admin and go to Job field");
		
		WebElement admin=driver.findElement(By.xpath("//span[normalize-space()='Admin']"));
		admin.click();
		test3.log(Status.INFO, "Loading Admin page and wait to get job field");
		
		WebElement job=driver.findElement(By.xpath("//span[normalize-space()='Job']"));
		job.click();
		test3.log(Status.PASS, "Job field was successfully loaded");
		Thread.sleep(2000);
		
		//Taking Screenshot
		TakeScreenShot(driver);
		
		//Test with Screenshot
		test3.addScreenCaptureFromPath("C:\\Users\\2318300\\eclipse-workspace\\2318300_OrangeHRM\\ScreenShot\\img2.png");
	
		//Create the ExtentTest for Job title enable or not
		ExtentTest test4=extent.createTest("Check the job Titles there are not");
		
		WebElement jobTtl=driver.findElement(By.xpath("//a[normalize-space()='Job Titles']"));
		String a_jobTtl=jobTtl.getText();
		String e_jobTtl="Job Titles";
		String jRole=null;
		
		//Check weather job titles is there or not
		test4.log(Status.INFO, "After click the Job field wait for get the Job Titles");
		System.out.println();
		
		if(a_jobTtl.equals(e_jobTtl))
		{
			jRole="TestCase Passed";
			System.out.println("Check 'JobTitles' field is there or not");
			System.out.println(jRole);
			jobTtl.click();
			test4.log(Status.PASS, "Job Titles field is founded");
		}
		else
		{
			jRole="TestCase Failed";
			System.out.println("Check 'JobTitles' field is there or not");
			System.out.println(jRole);
			test4.log(Status.FAIL, "Job Titles field is founded");
		}
		Thread.sleep(2000);
		
		//Taking Screenshot
		TakeScreenShot(driver);
		
		//Test with Screenshot
		test4.addScreenCaptureFromPath("C:\\Users\\2318300\\eclipse-workspace\\2318300_OrangeHRM\\ScreenShot\\img3.png");
		
		
		//Create  ExtentTest for Job role added or not in ExtentedHtmlReport
		ExtentTest test5=extent.createTest("Add job Role in OrangeHRM(Automation Tester)");
		WebElement add=driver.findElement(By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary"));
		
		test5.log(Status.INFO, "After click add Button wait for Job role Data");
		add.click();
		
		test5.log(Status.PASS, "Successfully get the Job Role data");
		Thread.sleep(2000);
		
		//Taking Screenshot
		TakeScreenShot(driver);
		
		//Test with Screenshot for Add button
		test5.addScreenCaptureFromPath("C:\\Users\\2318300\\eclipse-workspace\\2318300_OrangeHRM\\ScreenShot\\img4.png");
		
		WebElement jobTitle=driver.findElement(By.cssSelector("input[class=\"oxd-input oxd-input--active\"]:nth-child(1)"));
		jobTitle.click();
		jobTitle.clear();
		jobTitle.sendKeys(Elements[2]);
		
		return jRole;
		
	}
	
	
	//Use this method to add job role and save it
	public void submit() throws IOException, InterruptedException
	{
		//Create ExtentTest for save button status in ExtentHtmlReport
		ExtentTest test6=extent.createTest("Click the submit button to save the Job Role");
		
		WebElement save=driver.findElement(By.cssSelector("button[type='submit']"));
		
		//Test the status pass or fail
		test6.log(Status.INFO, "Wait for the Submition process");
		save.click();
		test6.log(Status.PASS, "Form Successfully submited");
		
		Thread.sleep(2000);
		//Taking Screenshot
		TakeScreenShot(driver);
		
		//Test the Screenshot and show in ExtentReport
		test6.addScreenCaptureFromPath("C:\\Users\\2318300\\eclipse-workspace\\2318300_OrangeHRM\\ScreenShot\\img5.png");
	}
	
	
	//Use this method to create new Report
	public void saveExtent()
	{
		extent.flush();
	}
	
	
	//Use this method to Logout the user
	public void LogOut() throws IOException, InterruptedException
	{
		//Create ExtentTest for list test case
		ExtentTest test7=extent.createTest("Check Job Title List");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewJobTitleList");
		Thread.sleep(3000);
		
		test7.log(Status.INFO, "Check JobRole status added or not");
		test7.log(Status.PASS, "Job Role added list verified Successfully");
		
		//Take Screenshot
		TakeScreenShot(driver);
		
		//Test screenshot and show final report
		test7.addScreenCaptureFromPath("C:\\Users\\2318300\\eclipse-workspace\\2318300_OrangeHRM\\ScreenShot\\img6.png");
		
		//Go to Menu
		WebElement menu=driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));		
		menu.click();
		
		//logout the user
		WebElement logout=driver.findElement(By.xpath("//a[text()='Logout']"));
		logout.click();
		Thread.sleep(3000);
		
		//Take Screenshot
		TakeScreenShot(driver);
		
	}

	
}
	