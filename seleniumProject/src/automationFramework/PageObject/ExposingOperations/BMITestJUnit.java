package automationFramework.PageObject.ExposingOperations;

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
public class BMITestJUnit {
	
	public String theight;
	public String height1;
	public String height2;
	public String tweight;
	public String weight1;
	public String weight2;
	public String bmi;
	public BmiCalcPage bmiCalcPage;

	@Parameters
	 public static Collection testData() throws IOException {
	 return getTestadata("./Data/Selenium-testdata.csv");
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

	public BMITestJUnit(String tweight, String weight1, String weight2, String theight, String height1, String height2,
			String bmi) {
		this.theight = theight;
		this.height1 = height1;
		this.height2 = height2;
		this.tweight = tweight;
		this.weight1 = weight1;
		this.weight2 = weight2;
		this.bmi = bmi;
	}

	@Before
	public void setUp() throws Exception {
		bmiCalcPage = new BmiCalcPage();
	}

	@After
	public void Teardown() {
		bmiCalcPage.close();
		System.out.println("Completed");
	}

	@Test
	public void testBMICalculator() throws Exception {
		// BmiCalcPage bmiCalcPage = new BmiCalcPage();
		bmiCalcPage.load();
		bmiCalcPage.calculateBmi(tweight, weight1, weight2, theight, height1, height2);
		assertEquals(bmi, bmiCalcPage.getBmi());
		//bmiCalcPage.close();
	}
}
