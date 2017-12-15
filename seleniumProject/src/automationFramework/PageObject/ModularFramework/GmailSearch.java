package automationFramework.PageObject.ModularFramework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailSearch {
	@FindBy(id="gbqfq")
	WebElement gbqfq;
	@FindBy(id="gbqfb")
	WebElement gbqfb;
	public GmailSearch() {
		PageFactory.initElements(Browser.driver(), this);
	}
	public SearchResults searchInStore(String query) {
		WebDriverWait wait = new WebDriverWait(Browser.driver(), 60);
		wait.until(ExpectedConditions.elementToBeClickable(gbqfq));
		gbqfq.sendKeys(query);
		gbqfb.click();
		return new SearchResults(query);
		}

}
