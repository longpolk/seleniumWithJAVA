package automationFramework.JUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import automationFramework.*;
import utility.SpreadsheetData;

@RunWith(value = Parameterized.class)
public class DataDrivenTestsWithSpreadsheetTest {

	private double a;
	private double b;
	private double aTimesB;
	private String weight1, weight2, height1, height2, bmi;

	@Parameters
	public static Collection spreadsheetData() throws IOException {
		InputStream spreadsheet = new FileInputStream("./Data/Selenium-testdata.xlsx");
		return new SpreadsheetData(spreadsheet).getData();
	}

	// public DataDrivenTestsWithSpreadsheetTest(double a, double b, double
	// aTimesB) {
	// super();
	// this.a = a;
	// this.b = b;
	// this.aTimesB = aTimesB;
	// }
	public DataDrivenTestsWithSpreadsheetTest(String weight1, String height1, String bmi) {
		this.weight1 = weight1;
		this.height1 = height1;
		this.bmi = bmi;
	}

	@Test
	public void shouldCalculateATimesB() {
		//double calculatedValue = Math.round((weight1)/(height1*height1));//a * b;
		//assertThat(calculatedValue, is(bmi));
	}
}
