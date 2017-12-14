package automationFramework.TestNG;

import java.util.regex.Pattern;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestNG01 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  //private String username;
  //private String password;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver","./Debug/geckodriver.exe");
	System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
    //driver = new FirefoxDriver();
    driver = new ChromeDriver();
	baseUrl = "https://github.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @DataProvider
  public Object[][] testData(){
	  return new Object[][]{
		 new Object[] {"longpolk@gmail.com","Marvel2409@"}
	  };
  }
  @Test(dataProvider="testData")
  public void testCase01(String username, String password) throws Exception {
    driver.get(baseUrl + "/login");
    driver.findElement(By.id("login_field")).clear();
    driver.findElement(By.id("login_field")).sendKeys(username);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.name("commit")).click();
    driver.findElement(By.xpath(".//*[@id='your_repos']/div/div[2]/ul/li[1]/a/span/span")).click();
  }

  @AfterClass(alwaysRun = true)
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
