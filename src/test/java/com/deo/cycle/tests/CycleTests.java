package com.deo.cycle.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CycleTests {
	
	ExtentReports extent ;
	ExtentTest test ;
	WebDriver driver ;
	
	@BeforeSuite
	public void beforeSuite(){
		extent = new ExtentReports("filePath.html");
	}
	
//	@BeforeMethod
//	public void beforeMethod(){
//		driver = new FirefoxDriver();
//		driver.manage().window().maximize();
//	}
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browser){
		if (browser.equalsIgnoreCase("firefox"))
            driver = new FirefoxDriver();
        else if (browser.equalsIgnoreCase("chrome"))
            driver = new ChromeDriver();
        else {
            throw new IllegalArgumentException("Invalid browser value!!");
        }
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void checkA(){
		test = extent.startTest("checkA", "chcking for failures") ;
		driver.get("http://jabong.com");
		test.log(LogStatus.INFO, "Opening the URL");
		
		String title = driver.getTitle();
		test.log(LogStatus.INFO, "capturing the browser title");
		test.log(LogStatus.INFO, "asserting on browser title : " + title);
		AssertJUnit.assertTrue(title.contains("Buy"));
		test.log(LogStatus.PASS, "Title verified");
	}
	
	@Test
	public void checkB(){
		test = extent.startTest("checkB", "chcking for failures") ;
		driver.get("http://amazon.com");
		test.log(LogStatus.INFO, "Opening the URL");
		
		String title = driver.getTitle();
		test.log(LogStatus.INFO, "capturing the browser title");
		test.log(LogStatus.INFO, "asserting on browser title : " + title);
		AssertJUnit.assertTrue(title.contains("Online"));
		test.log(LogStatus.PASS, "Title verified");
	}
	
	@Test
	public void checkC(){
		test = extent.startTest("checkC", "chcking for failures") ;
		driver.get("http://flipkart.com");
		
		test.log(LogStatus.INFO, "Opening the URL");
		String title = driver.getTitle();
		test.log(LogStatus.INFO, "capturing the browser title");
		test.log(LogStatus.INFO, "asserting on browser title : " + title);
		AssertJUnit.assertTrue(title.contains("Chandra"));
		test.log(LogStatus.PASS, "Title verified");
	}
	
	@Test
	public void checkD(){
		test = extent.startTest("checkD", "chcking for failures") ;
		driver.get("http://myntra.com");
		
		test.log(LogStatus.INFO, "Opening the URL");
		String title = driver.getTitle();
		test.log(LogStatus.INFO, "capturing the browser title");
		test.log(LogStatus.INFO, "asserting on browser title : " + title);
		AssertJUnit.assertTrue(title.contains("Chandra"));
		test.log(LogStatus.PASS, "Title verified");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result){
	        if (result.getStatus() == ITestResult.FAILURE) {
	            test.log(LogStatus.FAIL, result.getThrowable());
	            try {
	    			TakesScreenshot ts=(TakesScreenshot)driver;
	    			 
	    			// Call method to capture screenshot
	    			File source=ts.getScreenshotAs(OutputType.FILE);
	    			 
	    			// Copy files to specific location here it will save all screenshot in our project home directory and
	    			// result.getName() will return name of test case so that screenshot name will be same
	    			String screenshot = "./Screenshots/" + result.getMethod().getMethodName() + System.currentTimeMillis() +".png";
	    			FileUtils.copyFile(source, new File(screenshot));
	    			System.out.println("Screenshot taken");
	    			
	    			String img = test.addScreenCapture(screenshot);
	    		     test.log(LogStatus.FAIL, "Image", "Image example: " + img);
	    		} catch (Exception e) {
	    			System.out.println("Exception while taking screenshot "+e.getMessage());
	    		}
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
	        } else {
	            test.log(LogStatus.PASS, "Test passed");
	        }
	        
	        extent.endTest(test);        
	        extent.flush();
	        driver.close();
	        driver.quit();
	    }
	
	
	@AfterSuite
	public void afterSuite(){
	extent.close();
	}
	
}
