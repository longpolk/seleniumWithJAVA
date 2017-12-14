package automationFramework.TestNG;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(value = Parameterized.class)
public class BMITestNG {
	private static WebDriver driver;
	private static StringBuffer verificationErrors = new StringBuffer();
	private String theight;
	private String height1;
	private String height2;
	private String tweight;
	private String weight1;
	private String weight2;
	private String bmi;
	// private String bmiCategory;
	private String baseUrl;
	// private WebDriverWait wait;

	@Parameters
	public static Collection testData() throws IOException {
		return getTestadata("D:\\Training Document/Automation/Selenium/Selenium-testdata.txt");
		//Arrays.asList(new Object[][] {
		//{ "kilograms", "58", ".0", "metres", "1", "66", "21.0" },
		//{ "kilograms", "60", ".0", "metres", "1", "70", "20.8" },
		//{ "kilograms", "70", ".0", "metres", "2", "70", "9.6" } });
	}

	public static Collection<String[]> getTestadata(String filename) throws IOException {
		List<String[]> records = new ArrayList<String[]>();
		String record;
		BufferedReader file = new BufferedReader(new FileReader(filename));
		while ((record = file.readLine()) != null) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();
		return records;
	}

	public BMITestNG(String tweight, String weight1, String weight2, String theight, String height1, String height2,
			String bmi) {
		this.theight = theight;
		this.height1 = height1;
		this.height2 = height2;
		this.tweight = tweight;
		this.weight1 = weight1;
		this.weight2 = weight2;
		this.bmi = bmi;
		// this.bmiCategory = bmiCategory;
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "./Debug/IEDriverServer.exe");
		driver = new ChromeDriver();
		baseUrl = "http://www.slimmingworld.co.uk/losing-weight/bmi-calculator.aspx";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@Test
	public void testBMICalculator() throws Exception {
		driver.get(baseUrl);
		
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cphContent_cphContent_swcWeight_WeightSelector")));

		Select wunit = new Select(driver.findElement(By.id("cphContent_cphContent_swcWeight_WeightSelector")));
		wunit.selectByIndex(3);
		//wunit.selectByVisibleText(tweight);
		WebElement weightField1 = driver.findElement(By.id("cphContent_cphContent_swcWeight_WeightKilos"));
		weightField1.clear();
		weightField1.sendKeys(weight1);
		Select weightField2 = new Select(
				driver.findElement(By.id("cphContent_cphContent_swcWeight_WeightKilosFraction")));
		weightField2.selectByVisibleText(weight2);

		Select hunit = new Select(driver.findElement(By.id("cphContent_cphContent_swcHeight_HeightSelector")));
		//hunit.selectByVisibleText(theight);
		hunit.selectByIndex(2);
		WebElement heightField1 = driver.findElement(By.id("cphContent_cphContent_swcHeight_HeightMetres"));
		heightField1.clear();
		heightField1.sendKeys(height1);
		WebElement heightField2 = driver.findElement(By.id("cphContent_cphContent_swcHeight_HeightCentimetres"));
		heightField2.clear();
		heightField2.sendKeys(height2);

		WebElement calculateButton = driver.findElement(By.id("cphContent_cphContent_btnSubmit"));
		calculateButton.click();
		try {

			WebElement bmiLabel = driver
					.findElement(By.xpath(".//*[@id='cphContent_cphContent_swcBmiResultLow']/strong"));
			assertEquals(bmi, bmiLabel.getAttribute("innerHTML"));

			// WebElement bmiCategoryLabel =
			// driver.findElement(By.name("bmi_category"));
			// assertEquals(bmiCategory,
			// bmiCategoryLabel.getAttribute("value"));
		} catch (Error e) {

			verificationErrors.append(e.toString());
			System.err.println("Assertion Fail " + verificationErrors.toString());
		}
	}
}
