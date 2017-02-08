package com.deo.cycle.tests;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;

public class LogTests {
	
	WebDriver driver ; 
	
	// syntax from log4j2
	static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(LogTests.class.getName());
    String baseURL = "http://automationpractice.com/index.php";
	
	
	
	@BeforeMethod
	public void setUp(){
		
		driver = new FirefoxDriver();
		LOGGER.info("opening the firefox browser");
		driver.get(baseURL);
		LOGGER.info("launching our webPage");
		
	}
	
	@Test
	public void testA(){
		
		  LOGGER.trace("Trace Message!");
	      LOGGER.debug("Debug Message!");
	      LOGGER.info("Info Message!");
	      LOGGER.warn("Warn Message!");
	      LOGGER.error("Error Message!");
	      LOGGER.fatal("Fatal Message!");
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
		
	}

}
