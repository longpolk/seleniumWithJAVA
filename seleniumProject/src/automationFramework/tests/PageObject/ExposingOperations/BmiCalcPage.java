package automationFramework.tests.PageObject.ExposingOperations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BmiCalcPage {
	private WebElement cphContent_cphContent_swcWeight_WeightSelector;
	private WebElement cphContent_cphContent_swcHeight_HeightSelector;
	private WebElement cphContent_cphContent_swcWeight_WeightKilos;
	private WebElement cphContent_cphContent_swcWeight_WeightKilosFraction;
	private WebElement cphContent_cphContent_swcHeight_HeightMetres;
	private WebElement cphContent_cphContent_swcHeight_HeightCentimetres;
	private WebElement cphContent_cphContent_btnSubmit;
	@FindBy(xpath = ".//*[@id='cphContent_cphContent_swcBmiResultLow']/strong")
	private WebElement cphContent_cphContent_swcBmiResultLow;
	private WebDriver driver;
	String url = "http://www.slimmingworld.co.uk/losing-weight/bmi-calculator.aspx";

	public BmiCalcPage() {
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
	}
	public void load(){
		this.driver.get(url);
	}
	public void close(){
		this.driver.close();
	}
	public void calculateBmi(String tweight, String weight1, String weight2, String theight, String height1, String height2){
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		cphContent_cphContent_swcWeight_WeightSelector.click();
		cphContent_cphContent_swcWeight_WeightSelector.sendKeys(tweight);
		cphContent_cphContent_swcHeight_HeightSelector.click();
		cphContent_cphContent_swcHeight_HeightSelector.sendKeys(theight);
		cphContent_cphContent_swcWeight_WeightKilos.sendKeys(weight1);
		cphContent_cphContent_swcWeight_WeightKilosFraction.sendKeys(weight2);
		cphContent_cphContent_swcHeight_HeightMetres.sendKeys(height1);
		cphContent_cphContent_swcHeight_HeightCentimetres.sendKeys(height2);
		cphContent_cphContent_btnSubmit.click();
		
	}
	public String getBmi(){
		return cphContent_cphContent_swcBmiResultLow.getAttribute("innerHTML");
	}

}
