package automationFramework.PageObject.ExposingElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BmiCalcPage {
	public WebElement cphContent_cphContent_swcWeight_WeightSelector;
	public WebElement cphContent_cphContent_swcHeight_HeightSelector;
	public WebElement cphContent_cphContent_swcWeight_WeightKilos;
	public WebElement cphContent_cphContent_swcWeight_WeightKilosFraction;
	public WebElement cphContent_cphContent_swcHeight_HeightMetres;
	public WebElement cphContent_cphContent_swcHeight_HeightCentimetres;
	public WebElement cphContent_cphContent_btnSubmit;
	@FindBy(xpath = ".//*[@id='cphContent_cphContent_swcBmiResultLow']/strong")
	public WebElement cphContent_cphContent_swcBmiResultLow;
	
	public BmiCalcPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
