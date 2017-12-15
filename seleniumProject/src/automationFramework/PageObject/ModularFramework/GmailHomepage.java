package automationFramework.PageObject.ModularFramework;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class GmailHomepage extends LoadableComponent<GmailHomepage> {
	static String url = "https://gmail.com";
	//private static String username;
	private String title = "Gmail";

	public GmailHomepage() {
		PageFactory.initElements(Browser.driver(), this);
	}

	@Override
	public void isLoaded() throws Error {
		assertTrue(Browser.driver().getTitle().contains(title));
	}

	@Override
	public void load() {
		Browser.open(url);
	}

	public void close() {
		Browser.close();
	}
	
	public GmailLogin Login(String id, String pass){
		GmailLogin login = new GmailLogin();
		login.login(id, pass);
		return login;
	}
	
	public GmailSearch Search() {
		GmailSearch search = new GmailSearch();
		return search;
	}

}
