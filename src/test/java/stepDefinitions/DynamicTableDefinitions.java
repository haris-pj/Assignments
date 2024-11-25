package stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DynamicTableDefinitions {
	
	WebDriver driver;
    WebDriverWait wait;
    int cpuColumnIndex = -1;
    double chromeCpuValue = 0;
    ExtentReports extendReport;
	ExtentSparkReporter sparkReporter;
	ExtentTest testCase;
	
	@Given("I open the dynamic table page")
	public void i_open_the_dynamic_table_page() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\haris\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		extendReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        extendReport.attachReporter(sparkReporter);
        testCase=extendReport.createTest("Verify Dynamic Table");
        driver = new ChromeDriver();
        testCase.log(Status.INFO, "Navigating to Dynamic table page");
        driver.get("https://practice.expandtesting.com/dynamic-table");
        testCase.log(Status.INFO, "Entered Dynamic table page");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@When("I find the cpu column")
	public void i_find_the_cpu_column() {
		List<WebElement> headers = driver.findElements(By.xpath("//table//th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equalsIgnoreCase("CPU")) {
                cpuColumnIndex = i + 1;
                System.out.println("CPU column found at index: " + cpuColumnIndex);
                return;
            }
        }
        throw new RuntimeException("CPU column not found!");
	}
	
	@When("I find the row for {string}")
	public void i_find_the_row_for(String string) {
		List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty() && cells.get(0).getText().equalsIgnoreCase("Chrome")) {
                WebElement cpuCell = row.findElement(By.xpath("./td[" + cpuColumnIndex + "]"));
                String chromeCpuText = cpuCell.getText();
                System.out.println(" CPU percentage is: " + chromeCpuText);
                chromeCpuValue = Double.parseDouble(chromeCpuText.replace("%", "").trim());
                return;
            }
        }
	}
	@Then("I compare the chrome cpu value with the yellow label")
	public void i_compare_the_chrome_cpu_value_with_the_yellow_label() {
		WebElement yellowLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Chrome CPU:')]")));
        String yellowLabelText = yellowLabel.getText();
        System.out.println("Yellow label text: " + yellowLabelText);

        String numericPart = yellowLabelText.replace("Chrome CPU:", "").replace("%", "").trim();
        double yellowLabelValue = Double.parseDouble(numericPart);

        if (chromeCpuValue == yellowLabelValue) {
            System.out.println("CPU values match!");
        } else {
            System.out.println("Mismatch in CPU values!");
            System.out.println("Table CPU: " + chromeCpuValue + "%, Label CPU: " + yellowLabelValue + "%");
        }

        driver.quit();
        extendReport.flush();
	}
}
