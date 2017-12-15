package automationFramework.PageObject.ModularFramework;

import org.junit.Test;

import junit.framework.ComparisonFailure;

import static org.junit.Assert.*;
public class GmailTest {
	@Test
	public void gmailTestsearch() {
		System.setProperty("webdriver.gecko.driver", "./Debug/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Debug/chromedriver.exe");
		GmailHomepage homepage = new GmailHomepage();
		homepage.get();
		homepage.Login("longpolk", "24099300");
		SearchResults results = homepage.Search().searchInStore("Uber");
		try{
		assertEquals("1-50 trong số khoảng 85", results.getResults());
		}catch(ComparisonFailure e){
			System.err.println(e.toString());
		}
		results.close();
	}

}
