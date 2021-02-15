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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DeptTestHtml {
	
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/Department/Department.html";
	private static ExtentReports report;
	private static ExtentTest test;
	WebDriverWait wait = new WebDriverWait(driver, 30);

	@BeforeAll
	public static void beforeAll() {
		report = new ExtentReports("target/reports/TDLSiteReportDepartment.html", true); // the true overwrites any report in
																					// there

		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
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
	public void homeFromDept() {

		test = report.startTest("home nav bar test");

		driver.get(URL);

		targ = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[1]/a"));
		targ.click();

		String title = driver.getTitle();
		String expected = "TDL Home";

		if (expected.equals(title)) {
			test.log(LogStatus.PASS, "working link");
		} else {
			test.log(LogStatus.FAIL, "link not working");
		}
	}
	
	@Test
	public void tasksFromDept() {
		test = report.startTest("task nav bar test");

		driver.get(URL);

		targ = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[2]/a"));
		targ.click();

		String title = driver.getTitle();
		String expected = "Task Page";

		if (expected.equals(title)) {
			test.log(LogStatus.PASS, "working link");
		} else {
			test.log(LogStatus.FAIL, "link not working");
		}
	}

	@Test
	public void departmentFromDept() {
		test = report.startTest("dept nav bar test");

		driver.get(URL);

		targ = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[3]/a"));
		targ.click();

		String title = driver.getTitle();
		String expected = "Department Page";

		if (expected.equals(title)) {
			test.log(LogStatus.PASS, "working link");
		} else {
			test.log(LogStatus.FAIL, "link not working");
		}
	}

	@Test
	public void failedCreateTask() {

		test = report.startTest("Incorrect create task ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createname")));

		targ = driver.findElement(By.xpath("//*[@id=\"createMethod\"]/div/div/div[3]/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));

		targ = driver.findElement(By.className("alert-danger"));
		String result = targ.getText();
		String expected = "Department has not been successfully created!";

		if (expected.equals(result)) {
			test.log(LogStatus.PASS, "Working create alert + function");
		} else {
			test.log(LogStatus.FAIL, "alert + function not working ");
		}

	}

	@Test
	public void createTaskSuccess() throws InterruptedException {

		test = report.startTest("Correct create task ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createname")));

		targ = driver.findElement(By.id("createname"));
		targ.sendKeys("testing");

		targ = driver.findElement(By.xpath("//*[@id=\"createMethod\"]/div/div/div[3]/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));

		targ = driver.findElement(By.className("alert-success"));
		String result = targ.getText();
		String expected = "Department has been successfully created!";

		if (expected.equals(result)) {
			test.log(LogStatus.PASS, "Working create alert + function");
		} else {
			test.log(LogStatus.FAIL, "alert + function not working ");
		}
	}

	@Test
	public void readAll() {

		test = report.startTest("Correct read all ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/button[1]"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h4")));

		targ = driver.findElement(By.tagName("h4"));
		String result = targ.getText();
		String expected = "ID: 1 - Front-End";

		if (expected.equals(result)) {
			test.log(LogStatus.PASS, "Working read all");
		} else {
			test.log(LogStatus.FAIL, "read all not working ");
		}
	}

	@Test
	public void readOne() {

		test = report.startTest("Correct read one ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/button[2]"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("readoneid")));

		targ = driver.findElement(By.id("readoneid"));
		targ.sendKeys("1");

		targ = driver.findElement(By.xpath("//*[@id=\"readOneMethod\"]/div/div/div[3]/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h4")));

		targ = driver.findElement(By.tagName("h4"));
		String result = targ.getText();
		String expected = "ID: 1 - Front-End";

		if (expected.equals(result)) {
			test.log(LogStatus.PASS, "Working read one");
		} else {
			test.log(LogStatus.FAIL, "read one not working ");
		}

	}

	@Test
	public void clear() {

		test = report.startTest("Correct clear ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/button[3]"));
		targ.click();

		try {
			targ = driver.findElement(By.tagName("h4"));
		} catch (Exception e) {
			targ = null;
		}

		if (targ == null) {
			test.log(LogStatus.PASS, "Clear not working ");
		} else {
			test.log(LogStatus.FAIL, "Working clear");
		}
	}

	@Test
	public void update() {

		test = report.startTest("Correct update ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("updateid")));

		targ = driver.findElement(By.id("updateid"));
		targ.sendKeys("2");

		targ = driver.findElement(By.id("updatename"));
		targ.sendKeys("testing");

		targ = driver.findElement(By.xpath("//*[@id=\"updateMethod\"]/div/div/div[3]/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));

		targ = driver.findElement(By.className("alert-success"));
		String result = targ.getText();
		String expected = "Department has been successfully updated!";

		if (expected.equals(result)) {
			test.log(LogStatus.PASS, "Working update alert + function");
		} else {
			test.log(LogStatus.FAIL, "alert + function not working ");
		}
	}

	@Test
	public void failedUpdateTask() {

		test = report.startTest("Incorrect update ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("updatename")));

		targ = driver.findElement(By.xpath("//*[@id=\"updateMethod\"]/div/div/div[3]/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));

		targ = driver.findElement(By.className("alert-danger"));
		String result = targ.getText();
		String expected = "Department has not been successfully updated!";

		if (expected.equals(result)) {
			test.log(LogStatus.PASS, "Working update alert + function");
		} else {
			test.log(LogStatus.FAIL, "alert + function not working ");
		}

	}

	@Test
	public void delete() {

		test = report.startTest("Correct delete method ");
		driver.get(URL);

		targ = driver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteid")));

		targ = driver.findElement(By.id("deleteid"));
		targ.sendKeys("3");

		targ = driver.findElement(By.xpath("//*[@id=\"deleteMethod\"]/div/div/div[3]/button"));
		targ.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));

		targ = driver.findElement(By.className("alert-danger"));
		String result = targ.getText();
		String expected = "Department has been successfully deleted!";

		if (expected.equals(result)) {
			test.log(LogStatus.PASS, "Working delete alert + function");
		} else {
			test.log(LogStatus.FAIL, "alert + function not working ");
		}
	}

}
