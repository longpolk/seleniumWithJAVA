package automationFramework.PageObject.ModularFramework;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class SearchResults extends LoadableComponent<SearchResults> {
	private String query;

	public SearchResults(String query) {
		PageFactory.initElements(Browser.driver(), this);
	}

	@Override
	public void isLoaded() {
		assertTrue(Browser.driver().getTitle().contains("Kết quả tìm kiếm"));
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}

	public String getResults() {
		//List<String> strResults = new ArrayList<String>();
		String result="";
		List<WebElement> resultList = Browser.driver().findElements(By.xpath("//*[@id=':117']"));
		for (WebElement item : resultList) {
			result = result + item.getAttribute("innerHTML");
		}
		return result;
	}

	public void close() {
		Browser.close();
	}

	public GmailSearch Search() {
		GmailSearch search = new GmailSearch();
		return search;
	}
}
