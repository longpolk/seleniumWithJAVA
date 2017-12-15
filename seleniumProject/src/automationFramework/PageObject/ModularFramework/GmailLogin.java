package automationFramework.PageObject.ModularFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailLogin {
	private WebElement identifierId;
	private WebElement identifierNext;
	@FindBy(name="password")
	private WebElement password;
	private WebElement passwordNext;
	public GmailLogin() {
		PageFactory.initElements(Browser.driver(), this);
	}
	public void login(String id, String pass){
		identifierId.clear();
		identifierId.sendKeys(id);
		identifierNext.click();
		WebDriverWait wait = new WebDriverWait(Browser.driver(), 60);
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.clear();
		password.sendKeys(pass);
		passwordNext.click();
	}

}
