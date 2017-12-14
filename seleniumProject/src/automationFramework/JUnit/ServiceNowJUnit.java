package automationFramework.JUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
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

public class ServiceNowJUnit {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		// driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		baseUrl = "https://csc.service-now.com/";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void testCase01() throws Exception {
		driver.get(baseUrl);
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame |
		// gsft_main | ]]
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("user_name")));
		WebElement username = driver.findElement(By.id("user_name"));
		username.clear();
		username.sendKeys("lnguyen209");
		WebElement password = driver.findElement(By.id("user_password"));
		password.clear();
		password.sendKeys("123456");
		driver.findElement(By.id("language_select")).click();
		driver.findElement(By.cssSelector("option[value=\"en\"]")).click();
		driver.findElement(By.cssSelector("label.checkbox-label")).click();
		driver.findElement(By.id("remember_me")).click();
		driver.findElement(By.id("sysverb_login")).click();
		try {
			assertEquals("User name or password invalid",
					driver.findElement(By.xpath("//div[@id='output_messages']/div/div")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
