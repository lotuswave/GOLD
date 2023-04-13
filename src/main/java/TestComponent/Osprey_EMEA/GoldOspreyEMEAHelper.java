package TestComponent.Osprey_EMEA;

import static org.testng.Assert.fail;

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
import groovyjarjarantlr.CommonAST;

public class GoldOspreyEMEAHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldOspreyEMEAHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Osprey_EMEA");
			report.createTestcase("Osprey_EMEATestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public int getpageresponce(String url) throws MalformedURLException, IOException {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		int r = c.getResponseCode();

		return r;
	}

	public void verifingHomePage() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@aria-label='Home page link']").size();
			Common.assertionCheckwithReport(
					size > 0 && Common.getPageTitle().contains("Home page"),
					"validating store logo on the homwpage", "System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed", "Failed to navigate to the homepage and logo is not displayed");	
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo on the homwpage", "System directs the user to the Homepage and store logo should display",
					"Unable to navigate to the homepage and logo is not displayed", "Failed to navigate to the homepage and logo is not displayed");
			
			Assert.fail();
		}
		
	}
	
	public void social_Links(String dataSet) {

		String socalLinks = data.get(dataSet).get("Links");
		String[] socallinksarry = socalLinks.split(",");
		int i = 0;
		try {
			for (i = 0; i < socallinksarry.length; i++) {
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//span[text()='" + socallinksarry[i] + "']");
				Common.switchWindows();
				System.out.println(Common.getCurrentURL());

				if (socallinksarry[i].equals("Instagram")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("instagram"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].equals("Facebook")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.facebook.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].equals("Twitter")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("twitter"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].equals("YouTube")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("youtube"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].equals("Pinterest")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("pinterest"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying Social link ",
					"click the social links it will navigating to particular page",
					"User unable to navigate Social link page", Common.getscreenShotPathforReport("socialpage"));
			Assert.fail();
		}
	}
	
	public void header_OutdoorPacks(String Dataset) {
	
		String names = data.get(Dataset).get("Outdoorpacks");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Outdoor Packs')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Outdoor Packs",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Outdoor Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	
	public void header_KidsPacks(String Dataset) {
		
		String names = data.get(Dataset).get("KidsPacksCarriers");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Kids Packs & Carriers')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Kids Packs",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kids Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
public void header_DayPacks(String Dataset) {
		
		String names = data.get(Dataset).get("DayPacks");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Day Packs')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Day Packs",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Day Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
public void header_Travel(String Dataset) {
	
	String names = data.get(Dataset).get("Travel");
	String[] Links = names.split(",");
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Travel')]");
			Common.clickElement("xpath", "//span[contains(text(),'Travel')]");

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
			String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
			Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
					"verifying the header link " + Links[i] + "Under Travel",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

		}
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Travel",
				"User should navigate to the " + Links[i] + "pages",
				" unable to navigate to the " + Links[i] + "pages",
				Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
		Assert.fail();
	}

}
public void header_Accessories(String Dataset) {
	
	String names = data.get(Dataset).get("Accessories");
	String[] Links = names.split(",");
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Accessories')]");
			Common.clickElement("xpath", "//span[contains(text(),'Accessories')]");

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
			String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
			Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
					"verifying the header link " + Links[i] + "Under Accessories",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

		}
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Accessories",
				"User should navigate to the " + Links[i] + "pages",
				" unable to navigate to the " + Links[i] + "pages",
				Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
		Assert.fail();
	}

}

public void header_Featured(String Dataset) {
	
	String names = data.get(Dataset).get("Featured");
	String[] Links = names.split(",");
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
			Common.clickElement("xpath", "//span[contains(text(),'Featured')]");

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
			String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
			Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
					"verifying the header link " + Links[i] + "Under Accessories",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

		}
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Accessories",
				"User should navigate to the " + Links[i] + "pages",
				" unable to navigate to the " + Links[i] + "pages",
				Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
		Assert.fail();
	}

}

public void header_ShopAll(String Dataset) {
	
	String names=data.get(Dataset).get("Shop all");
	String[] Links=names.split(",");
	int i=0;
	try
{
		
			for(i=0;i<Links.length;i++){
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
			Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),' "+Links[i]+"')]");       
			Common.clickElement("xpath", "//span[contains(text(),' "+Links[i]+"')]");
			Common.clickElement("xpath", "//a[contains(@aria-label,'" +Links[i]+ "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
			Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Featured","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
	
}
}

	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "",
				"User should navigate to the "+Links[i]+"pages",
				" unable to navigate to the "+Links[i]+"pages",
				Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
		Assert.fail();
	}
	
}

}