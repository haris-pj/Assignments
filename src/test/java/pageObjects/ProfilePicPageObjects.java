package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePicPageObjects {
	
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div")
	public static WebElement employeesLeave;
	
	@FindBy(xpath="//*[text()='My Info']")
	public static WebElement myInfo;
	
	@FindBy(xpath="//input[@name='firstName']")
	public static WebElement firstName;
	
	@FindBy(xpath="//input[@name='lastName']")
	public static WebElement lastName;
	
	@FindBy(xpath="//*[@class='employee-image']")
	public static WebElement profilePic;
	
	@FindBy(xpath="(//*[@type='button'])[4]")
	public static WebElement uploadPic;
	
	@FindBy(xpath="//*[@type='submit']")
	public static WebElement submit;
	
}
