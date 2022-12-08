package TestComponent.Hydroflask;

import static org.testng.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import groovyjarjarantlr.CommonAST;

public class GoldHydroAlgolia_Helper{

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	
	public GoldHydroAlgolia_Helper(String datafile,String sheetname) {
		
		excelData = new ExcelReader(datafile,sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
		
	}
	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page") || Common.getPageTitle().contains("Hydro Flask"),
					"validating store logo", "System directs the user to the Homepage",
					"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}

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
public void search_Algolia_results(String search) {

try {
	Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
	String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
	Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
			"User should able to click on the search button", "Search expands to the full page",
			"Sucessfully search bar should be expand");
	Common.textBoxInput("xpath", "//input[@id='search']", search);
//	Common.actionsKeyPress(Keys.ENTER);
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


public void Validating_search1(String search) {
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




public void Hydroflask_logo() {
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

public void close_add() throws Exception {
	// TODO Auto-generated method stub
	 Thread.sleep(3000);
     int sizesframe=Common.findElements("xpath", "//div[@class='preloaded_lightbox']/iframe").size();
     if(sizesframe>0){
     Common.actionsKeyPress(Keys.PAGE_UP);
    
    Thread.sleep(4000);
     Sync.waitElementVisible("xpath" , "//div[@class='sidebar-iframe-close']");
     Common.clickElement("xpath", "//div[@class='sidebar-iframe-close']");
     }
     else {
         int sizeofpopup=Common.findElements("id", "wpx-newsletter-popup").size();
         if(sizeofpopup>0){
             
             
             Sync.waitElementClickable("xpath" , "//button[@aria-label='close']");
             Common.clickElement("xpath" , "//button[@aria-label='close']");
     }
        
     }
}
 	
public void acceptPrivacy() {
		
		Common.clickElementStale("id", "truste-consent-button");
	}

	public void updtePayementcrditcard_WithInvalidData(String dataSet) throws Exception {
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();

			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//span[text()='New payment method']");
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//span[text()='Place Order']");
				expectedResult = "credit card fields are filled with the data";
				String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
				Common.assertionCheckwithReport(errorTexts.isEmpty()||errorTexts.contains("Please complete your payment details."), "validating the credit card information with valid data",
						expectedResult, "Filled the Card detiles", "missing field data it showinng error");
		
				
			} else {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//span[text()='Place Order']");
				expectedResult = "credit card fields are filled with the data";
				String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();

				Common.assertionCheckwithReport(errorTexts.isEmpty()||errorTexts.contains("Please complete your payment details."), "validating the credit card information with valid data",
						expectedResult, "Filled the Card detiles", "missing field data it showinng error");
				

			}
			
		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}

		
	}
	public void AcceptAll() {



       try {



           Thread.sleep(5000);
            if (Common.findElement("xpath", "//button[@id='truste-consent-button']") != null) {



               Common.clickElement("xpath", "//button[@id='truste-consent-button']");
            }
        } catch (Exception e) {



           e.printStackTrace();
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
	public void clickWarrantylink() {
		// TODO Auto-generated method stub
		
			String expectedResult = "It should land successfully on the Warranty page";
			
			Common.actionsKeyPress(Keys.END);
			try {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//a[text()='Warranty']");
				Common.clickElement("xpath", "//a[text()='Warranty']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				
				Sync.waitPageLoad();	
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Warranty"),"Validating the Warranty page navigation" ,
						expectedResult, "successfully land to Warranty page", "unabel to load the Warranty page");
			} catch (Exception | Error e) {
				ExtenantReportUtils.addFailedLog("validating Warranty page", expectedResult,
						"unabel to load the Warrantyt page", Common.getscreenShotPathforReport("Warranty page link"));
				Assert.fail();

			}
			
		} 
	public void Warrantysearch_results(String search)  {
			 String expectedResult = "It should opens the search resluts of searched prodcuts.";
			
			try {
				Common.clickElement("xpath", "//input[@class='form-control form-control-search']");
				//Common.textBoxInput("xpath", "//input[@id='search']", search);
				String searchresults = Common.findElement("xpath", "//input[@class='form-control form-control-search']").getText();
				System.out.println(searchresults);
				Common.textBoxInput("xpath", "//input[@id='searchInput']", search);
				
				
				Common.actionsKeyPress(Keys.ENTER);
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitPageLoad();
				
				  String searchresults1 = Common.findElement("xpath", "//span[@class='search-results-number']").getText();
				  System.out.println(searchresults1);
		            Common.assertionCheckwithReport(searchresults1.contains("30 results found for  'CAP LAGUNA'"),
		                    "verifying search resluts", expectedResult, "search resluts should be displayed",
		                    "search resluts not displayed");
				
				
				
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the search functionality",
						"enter product name will display in the search box",
						" unable to enter the product name in  search box",
						Common.getscreenShot("Failed to see the product name"));
				Assert.fail();
			}
		}
	public void orderprodcut_search(String search) {
		// TODO Auto-generated method stub
	
	try
	{
	Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
		"User should able to click on the search button", "	Search expands to the full page",
		"Sucessfully search bar should be expand");
Common.textBoxInput("xpath", "//input[@id='search']", search);
Common.actionsKeyPress(Keys.ENTER);
Sync.waitPageLoad();
Thread.sleep(4000);
String searchresults = Common.getText("xpath", "//span[text()='oxo pop container']");
String productsearch1 = Common.findElement("xpath", "(//div[@id='algolia-right-container'])[1]").getText();
System.out.println(productsearch1);
Common.assertionCheckwithReport(searchresults.contains("oxo pop container"), "validating the search functionality",
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


			
public void unorderprodcut_search(String search) {
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
		String searchresults = Common.getText("xpath", "//span[text()='pop container oxo']");
		String productsearch = Common.findElement("xpath", "(//div[@id='algolia-right-container'])[1]").getText();
		System.out.println(productsearch);
		Common.assertionCheckwithReport(searchresults.contains("pop container oxo"), "validating the search functionality",
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













