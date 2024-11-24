package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPAgeObjects {
	
	@FindBy(name="username")
	public static WebElement userName;
	
	@FindBy(name="password")
	public static WebElement password;
	
	@FindBy(xpath="//*[@type='submit']")
	public static WebElement loginButton;
}
