package commonFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CommonFunctions {
	public static WebDriver driver=null;
	public static Properties properties=null;
	ExtentReports extendReport;
	ExtentSparkReporter sparkReporter;
	protected ExtentTest testCase;
	
	public Properties loadPropertyFile() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("config.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		return properties;
	}
	
	@BeforeSuite
	public void launchBrowser() throws IOException {
		loadPropertyFile();
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		String chromedriverLocation = properties.getProperty("chromedriverLocation");
		String firefoxdriverLocation = properties.getProperty("fireFoxdriverLocation");
		String edgedriverLocation = properties.getProperty("edgedriverLocation");

        extendReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        extendReport.attachReporter(sparkReporter);
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromedriverLocation);
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", firefoxdriverLocation);
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", edgedriverLocation);
			driver = new EdgeDriver();
		}else {
	        throw new IllegalArgumentException("Unsupported browser: " + browser);
	    }
		driver.manage().window().maximize();
		testCase=extendReport.createTest("Verify Login Page");
		testCase.log(Status.INFO, "Navigating to login page");
		driver.get(url);
		testCase.log(Status.INFO, "Login Page entered and enter credentials");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
		extendReport.flush();
		
	}

}
