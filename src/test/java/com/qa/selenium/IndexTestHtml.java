package com.qa.selenium;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class IndexTestHtml {
	
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/index.html";
	private static ExtentReports report;
	private static ExtentTest test;

	@BeforeAll
	public static void beforeAll() {
		report = new ExtentReports("target/reports/TDLSiteReportIndex.html", true); // the true overwrites any report in there
		
		System.setProperty(
				"webdriver.chrome.driver", 
				"src/main/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());

	}
	
	@AfterEach
	public void endTest() {
		report.endTest(test);
	}

	@AfterAll
	public static void afterAll() {
		
		driver.quit();
		
		report.flush();
		report.close();

	}
	
	public static ChromeOptions chromeCfg() {
	 Map<String, Object> prefs = new HashMap<String, Object>();
	 ChromeOptions cOptions = new ChromeOptions();
	  
	 prefs.put("profile.default_content_setting_values.cookies", 2);
	 prefs.put("network.cookie.cookieBehavior", 2);
	 prefs.put("profile.block_third_party_cookies", true);

	 cOptions.setExperimentalOption("prefs", prefs);

	 return cOptions;
	 }

	public static FirefoxOptions firefoxCfg() {
		FirefoxOptions cOptions = new FirefoxOptions(); 
		cOptions.addPreference("profile.default_content_setting_values.cookies", 2); 
		cOptions.addPreference("network.cookie.cookieBehavior", 2); 
		cOptions.addPreference("profile.block_third_party_cookies", true);
		
		return cOptions; 
	}
	
	@Test
	public void homeFromHome() {
		
		test = report.startTest("nav bar test - home");
		
		driver.get(URL);
		
		targ = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[1]/a"));
		targ.click();
		
		String title = driver.getTitle();
		String expected = "TDL Home";
		
		if(expected.equals(title)) {
			test.log(LogStatus.PASS, "working link");
		}
		else {
			test.log(LogStatus.FAIL, "link not working");
		}
	}
	
	@Test
	public void tasksFromHome() {
		test = report.startTest("nav bar test - tasks");
		
		driver.get(URL);
		
		targ = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[2]/a"));
		targ.click();
		
		String title = driver.getTitle();
		String expected = "Task Page";
		
		if(expected.equals(title)) {
			test.log(LogStatus.PASS, "working link");
		}
		else {
			test.log(LogStatus.FAIL, "link not working");
		}
	}
	
	@Test
	public void departmentFromHome() {
		test = report.startTest("nav bar test - department");
		
		driver.get(URL);
		
		targ = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[3]/a"));
		targ.click();
		
		String title = driver.getTitle();
		String expected = "Department Page";
		
		if(expected.equals(title)) {
			test.log(LogStatus.PASS, "working link");
		}
		else {
			test.log(LogStatus.FAIL, "link not working");
		}
	}

}
