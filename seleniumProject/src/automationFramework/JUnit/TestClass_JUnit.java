package automationFramework.JUnit;

import java.util.regex.Pattern;
import java.util.*;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(value = Parameterized.class)
public class TestClass_JUnit {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String username;
	private String password;
	private String error;
	
	public TestClass_JUnit(String username, String password, String error){
		this.username = username;
		this.password = password;
		this.error = error;
	}
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "./Debug/IEDriverServer.exe");
		driver = new ChromeDriver();
		baseUrl = "https://github.com/login";
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	@Parameters
	public static Collection testData(){
		return Arrays.asList(
				new Object[][]{
					{"longpolk","123456","Incorrect username or password."},
					{"longpolk","456789","Incorrect username or password."}
				});
	}
	
	@Test
	public void testCase01() throws Exception {
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// null | ]]
		driver.get(baseUrl);
		driver.findElement(By.id("login_field")).clear();
		driver.findElement(By.id("login_field")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("commit")).click();
		try{
			WebElement label = driver.findElement(By.xpath(".//*[@id='js-flash-container']/div/div"));
			assertEquals(error, label.getAttribute("innerHTML"));
		}catch(NoSuchElementException e){
			verificationErrors.append(e.toString());
			System.out.println("Error: "+verificationErrors.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
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