package automationFramework.PageObject.ModularFramework;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailObject {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String request_timer;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "./Debug/IEDriverServer.exe");
		driver = new ChromeDriver();
		baseUrl = "https://mail.google.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCase01() throws Exception {
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// null | ]]
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("identifierId"))));
		driver.findElement(By.id("identifierId")).clear();
		driver.findElement(By.id("identifierId")).sendKeys("longpolk");
		driver.findElement(By.id("identifierNext")).click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("password"))));
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("24099300");
		driver.findElement(By.id("passwordNext")).click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("gbqfq"))));
		driver.findElement(By.id("gbqfq")).clear();
		driver.findElement(By.id("gbqfq")).sendKeys("test");
		driver.findElement(By.id("gbqfb")).click();
//		try {
//			assertEquals("96", driver.findElement(By.cssSelector("span.panel-content-count")).getText());
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}
//		driver.findElement(By.cssSelector("h2.panel-title")).click();
//		try {
//			assertEquals("Skype for Business - Join a Skype for Business Meeting",
//					driver.findElement(By.xpath("//div[@id='knowledgePanel-content']/a[6]/div/h4")).getText());
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}
//		driver.findElement(By.xpath("//div[@id='knowledgePanel-content']/a[6]/div/h4")).click();

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
