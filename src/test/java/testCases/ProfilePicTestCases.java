package testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.LoginPAgeObjects;
import pageObjects.ProfilePicPageObjects;

public class ProfilePicTestCases extends CommonFunctions {
	
	String actualMessage =null;
	Wait<WebDriver> wait;
	
	
	public void login() throws IOException, InterruptedException {
		PageFactory.initElements(driver, LoginPAgeObjects.class);
		LoginPAgeObjects.userName.sendKeys(properties.getProperty("username"));
		LoginPAgeObjects.password.sendKeys(properties.getProperty("password"));
		LoginPAgeObjects.loginButton.click();
		testCase.log(Status.PASS, "Succesfully opened the HRM page");
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.titleContains("OrangeHRM"));
		String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        testCase.log(Status.INFO, "Actual title is: "+actualTitle);
        testCase.log(Status.INFO, "Expected title is: "+"OrangeHRM");
        Assert.assertTrue(actualTitle.contains("OrangeHRM"));
        
        Thread.sleep(2000);
        
        File folder = new File(properties.getProperty("screenShotLocation"));
      	TakesScreenshot screenshot =(TakesScreenshot) driver;
      	File sourceFile= screenshot.getScreenshotAs(OutputType.FILE);
      	File destinationFile= new File(folder, "profilePicScreenshot.png");
      	FileHandler.copy(sourceFile, destinationFile);
		
	}
	
	public void enterDetails() throws IOException, InterruptedException {
		PageFactory.initElements(driver, ProfilePicPageObjects.class);
		ProfilePicPageObjects.myInfo.click();
		testCase.log(Status.INFO, "Click my info element");
		testCase.log(Status.PASS, "Entered into my info screen");
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ProfilePicPageObjects.firstName));
		
		ProfilePicPageObjects.firstName.clear();
		ProfilePicPageObjects.firstName.sendKeys("Batman");
		testCase.log(Status.INFO, "Going to write the first name");
		testCase.log(Status.PASS, "First name entered");
		ProfilePicPageObjects.lastName.sendKeys("Dark");
		testCase.log(Status.INFO, "Going to write the last name");
		testCase.log(Status.PASS, "Last name entered");
        
	}
	
	public void ProfilePicUpload() throws InterruptedException, AWTException, IOException {
		PageFactory.initElements(driver, ProfilePicPageObjects.class);
		ProfilePicPageObjects.profilePic.click();
		testCase.log(Status.PASS, "Click the profile pic");
		ProfilePicPageObjects.uploadPic.click();
		testCase.log(Status.PASS, "Entered into the pofile pic screen");
		
		Thread.sleep(1000);
		String file=properties.getProperty("profilePic");
		StringSelection selection =new StringSelection(file);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		testCase.log(Status.INFO, "Upload the profile pic");
		ProfilePicPageObjects.submit.click();
		testCase.log(Status.PASS, "Pofile pic uploaded and saved");
	}
	
	@Test
	public void verifyHRMPage() throws InterruptedException, AWTException, IOException { 
		
		login();
        enterDetails();
        ProfilePicUpload();
		
	}

}
