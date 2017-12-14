package automationFramework.PageObject.ExposingOperations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMapObject {
	private WebElement searchboxinput;
	@FindBy(id="searchbox-searchbutton")
	private WebElement searchbutton;
	@FindBy(xpath = "//*[@id='pane']/div/div[2]/div/div/div[1]/button[2]/div/div")
	private WebElement redirect;
	@FindBy(css = "#sb_ifc51 > input.tactile-searchbox-input")
	private WebElement redInput;
	@FindBy(css = "#directions-searchbox-0 > button.searchbox-searchbutton")
	private WebElement redSearchbutton;
	private WebElement verifyString;
	private WebDriver driver;
	
	public GoogleMapObject() {
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		 driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
	}
	public void Test(String input, String red){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		searchboxinput.sendKeys(input);
		searchbutton.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(redirect));
		redirect.click();
		redInput.sendKeys(red);
		redSearchbutton.click();
	}
	public void load(String url){
		this.driver.get(url);
	}
	public void close(){
		this.driver.close();
	}
	public String verifyString(){
		return verifyString.getAttribute("innerHTML");
	}

}
