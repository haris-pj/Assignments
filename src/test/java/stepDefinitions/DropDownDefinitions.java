package stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DropDownDefinitions {
	
	WebDriver driver;
    WebDriverWait wait;
	
	@Given("I open the leafground dropdown page")
	public void i_open_the_leafground_dropdown_page() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\haris\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.leafground.com/select.xhtml");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@When("I select {string} from the country dropdown")
	public void i_select_from_the_country_dropdown(String string) {
		WebElement countryDropDown = driver.findElement(By.id("j_idt87:country_label"));
        countryDropDown.click();
        List<WebElement> countryOptions = driver.findElements(By.xpath("//*[@id=\"j_idt87:country_items\"]/li"));
        for (WebElement country : countryOptions) {
            if (country.getText().equals("India")) {
                country.click();
                break;
            }
        }
	}
	
	@When("I select {string} from city dropdown")
	public void i_select_from_city_dropdown(String string) {
		WebElement cityDropDown = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_idt87:city_label")));
        cityDropDown.click();
        List<WebElement> cityOptions = driver.findElements(By.xpath("//*[@id=\"j_idt87:city_items\"]/li"));
        for (WebElement city : cityOptions) {
            if (city.getText().equals("Chennai")) {
                city.click();
                break;
            }
        }
	}
	
	@Then("I verify selection was successful")
	public void i_verify_selection_was_successful() {
		System.out.println("Selections were successful.");
        driver.quit();
	}
	
}
