package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctions.CommonFunctions;
import pageObjects.DashboardPageObjects;
import pageObjects.LoginPAgeObjects;

public class DashboardTestCases extends CommonFunctions {
	
	String actualMessage =null;
	
	public void login() {
		PageFactory.initElements(driver, LoginPAgeObjects.class);
		LoginPAgeObjects.userName.sendKeys(properties.getProperty("username"));
		LoginPAgeObjects.password.sendKeys(properties.getProperty("password"));
		LoginPAgeObjects.loginButton.click();
	}
	
	public void getEmployeeName() {
		PageFactory.initElements(driver, DashboardPageObjects.class);
        actualMessage = DashboardPageObjects.employeesLeave.getText();
        System.out.println(actualMessage);
	}
	@Test
	public void verifyDashboardPage() { 
		
		login();
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.titleContains("OrangeHRM"));
		String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        Assert.assertTrue(actualTitle.contains("OrangeHRM"));
        
        getEmployeeName();
        Assert.assertEquals(actualMessage, "Time at Work");
		
	}
}
