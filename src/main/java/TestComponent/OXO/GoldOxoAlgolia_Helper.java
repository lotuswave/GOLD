package TestComponent.OXO;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class GoldOxoAlgolia_Helper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public int getpageresponce(String url) throws MalformedURLException, IOException{
        HttpURLConnection c=(HttpURLConnection)new URL(url).openConnection();
          c.setRequestMethod("HEAD");
          c.connect();
          int r = c.getResponseCode();
          
          return r;
   
}

public void Validating_Algolia_search(String search) {
	// TODO Auto-generated method stub
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(open.contains("active"),
				"To validate the global search box is opened when we click on the search icon",
				"User should able to click on the search icon and search box opens",
				"Sucessfully the gobal search box opend when user clicks on search icon",
				"Failed to open the search box when user clicks on the search icon");
		Common.textBoxInput("xpath", "//input[@id='search']", search);	
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[contains(@class,'icon-header__s')]");
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String close = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(close.contains("c-header-search"),
				"To validate the global search box is Closed when user click on the close icon",
				"User should able to click on the close icon and search box should be closed",
				"Sucessfully the gobal search box closed when user clicks on close icon",
				"Failed to close the search box when user clicks on the close icon");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"To validate the global search box is Closed when user click on the close icon",
				"User should able to click on the close icon and search box should be closed",
				"Unable to close the gobal search box when user clicks on close icon",
				Common.getscreenShotPathforReport(
						"Failed to close the search box when user clicks on the close icon"));

		Assert.fail();
	}

}

public void Algolia_search_results(String search) {
	
	try {
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
				"User should able to click on the search button", "Search expands to the full page",
				"Sucessfully search bar should be expand");
		Common.textBoxInput("xpath", "//input[@id='search']", search);
		//Common.actionsKeyPress(Keys.ENTER);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		int Searchresult = Common.findElements("xpath", "//div[contains(@class,'m-search__container')]")
				.size();

	Common.assertionCheckwithReport(Searchresult > 0,
			"To validate the search result",
			"should navigate add engraving page", "successfully navigate to add engraving page",
			"Failed to navigate to add engraving page");

		    

	} catch (Exception | Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("validating add engraving page",
		"should navigate add engraving page",
		"Failed to navigate to add engraving page",
		Common.getscreenShotPathforReport("Failed to navigate to add engraving pages"));
	Assert.fail();
	}
	}

public void Validating_search(String search) {
	// TODO Auto-generated method stub
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(open.contains("active"),
				"To validate the global search box is opened when we click on the search icon",
				"User should able to click on the search icon and search box opens",
				"Sucessfully the gobal search box opend when user clicks on search icon",
				"Failed to open the search box when user clicks on the search icon");
		Common.textBoxInput("xpath", "//input[@id='search']", search);	
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[contains(@class,'icon-header__s')]");
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String close = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(close.contains("c-header-search"),
				"To validate the global search box is Closed when user click on the close icon",
				"User should able to click on the close icon and search box should be closed",
				"Sucessfully the gobal search box closed when user clicks on close icon",
				"Failed to close the search box when user clicks on the close icon");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"To validate the global search box is Closed when user click on the close icon",
				"User should able to click on the close icon and search box should be closed",
				"Unable to close the gobal search box when user clicks on close icon",
				Common.getscreenShotPathforReport(
						"Failed to close the search box when user clicks on the close icon"));

		Assert.fail();
	}

}
public void search_results1(String search) {
	
	try {
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
				"User should able to click on the search button", "Search expands to the full page",
				"Sucessfully search bar should be expand");
		Common.textBoxInput("xpath", "//input[@id='search']", search);
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String productsearch = Common.findElement("xpath", "//h3[@class='c-srp-title__results']").getText();
		System.out.println(productsearch);
		Common.assertionCheckwithReport(productsearch.contains("Showing search results"), "validating the search functionality",
				"enter any search term will display no results in the search box", "user enter the search term in  search box",
				"Failed to see the search term");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the search functionality",
				"enter product name will display in the search box",
				" unable to enter the product name in  search box",
				Common.getscreenShot("Failed to see the product name"));
		Assert.fail();
	}

	
}

public void oxo_logo() {
    try {
        Common.clickElement("xpath","//a[@class='a-logo']");
        Sync.waitPageLoad();
        int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
        Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
                "validating store logo", "System directs the user to the Homepage",
                "Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
    } catch (Exception | Error e) {
        e.printStackTrace();



       ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
                " user unable navigates to the home page", "Failed to navigate to the homepage");
        Assert.fail();
    }

}
    public void Validating_searchs(String search) {
        // TODO Auto-generated method stub
        



           try {
                Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
                String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
                Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
                        "User should able to click on the search button", "Search expands to the full page",
                        "Sucessfully search bar should be expand");
                Common.textBoxInput("xpath", "//input[@id='search']", search);
                Common.actionsKeyPress(Keys.ENTER);
                Sync.waitPageLoad();
                Thread.sleep(4000);
                String productsearch = Common.findElement("xpath", "(//div[contains(@id,'algolia-right-container')])[1]").getText();
                System.out.println(productsearch);
                Common.assertionCheckwithReport(productsearch.contains(search), "validating the search functionality",
                        "enter any search term will display no results in the search box", "user enter the search term in  search box",
                        "Failed to see the search term");



           } catch (Exception | Error e) {
                e.printStackTrace();
                ExtenantReportUtils.addFailedLog("validating the search functionality",
                        "enter product name will display in the search box",
                        " unable to enter the product name in  search box",
                        Common.getscreenShot("Failed to see the product name"));
                Assert.fail();
            }
    }


}





