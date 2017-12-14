package automationFramework.tests.PageObject.ExposingElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.AssertionFailedError;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;

@RunWith(value = Parameterized.class)
public class BmiCalculatorTests {
	public BmiCalcPage bmiCalcPage;
	public WebDriver driver;
	public String tweight, weight1, weight2, theight, height1, height2, bmi;

	@Before
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.slimmingworld.co.uk/losing-weight/bmi-calculator.aspx");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Create instance of BmiCalcPage and pass the driver
		bmiCalcPage = new BmiCalcPage(driver);
		
	}

	@After
	public void Teardown() {
		//this.driver.quit();
		System.out.println("Completed");
	}

	public BmiCalculatorTests(String tweight, String weight1, String weight2, String theight, String height1,
			String height2, String bmi) {
		this.tweight = tweight;
		this.theight = theight;
		this.weight1 = weight1;
		this.weight2 = weight2;
		this.height1 = height1;
		this.height2 = height2;
		this.bmi = bmi;
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
	@Parameters
	public static Collection testData() throws IOException{
		return getTestadata("./Data/Selenium-testdata.csv");
	}
	@Test
	public void testBmiCalculation() {
		// Enter Height & Weight
		bmiCalcPage.cphContent_cphContent_swcWeight_WeightSelector.click();
		bmiCalcPage.cphContent_cphContent_swcWeight_WeightSelector.sendKeys(tweight);
		bmiCalcPage.cphContent_cphContent_swcHeight_HeightSelector.click();
		bmiCalcPage.cphContent_cphContent_swcHeight_HeightSelector.sendKeys(theight);
		bmiCalcPage.cphContent_cphContent_swcWeight_WeightKilos.sendKeys(weight1);
		bmiCalcPage.cphContent_cphContent_swcWeight_WeightKilosFraction.sendKeys(weight2);
		bmiCalcPage.cphContent_cphContent_swcHeight_HeightMetres.sendKeys(height1);
		bmiCalcPage.cphContent_cphContent_swcHeight_HeightCentimetres.sendKeys(height2);
		// Click on Calculate button
		bmiCalcPage.cphContent_cphContent_btnSubmit.click();
		// Verify Bmi & Bmi Category values
		try{
		assertEquals(bmi, bmiCalcPage.cphContent_cphContent_swcBmiResultLow.getAttribute("innerHTML"));
		}catch(AssertionFailedError e){
			System.out.println("Failed: "+e.toString());
		}
		// assertEquals("Normal",
		// bmiCalcPage.bmi_category.getAttribute("value"));
	}

}