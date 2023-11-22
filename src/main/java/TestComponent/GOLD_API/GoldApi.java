package TestComponent.GOLD_API;
   
import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import groovyjarjarantlr.CommonASTWithHiddenTokens;
import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;

public class GoldApi {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	private String String;
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldApi(String datafile, String sheetname) {

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
	
	public void verifingHomePage() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			if(Common.getCurrentURL().contains("osprey.com/gb/"))
			{
				Close_Geolocation();
			     acceptPrivacy();
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else if(Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod"))
			{
				close_add();
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else if(Common.getCurrentURL().contains("preprod.osprey.com/gb/"))
			{
				
				 acceptPrivacy();
				 Close_Geolocation();
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else
			{
//			Close_Geolocation();
//			close_add();
		     acceptPrivacy();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey") || size > 0,
					"validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed",
					"Failed to navigate to the homepage and logo is not displayed");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Unable to navigate to the homepage and logo is not displayed",
					"Failed to navigate to the homepage and logo is not displayed");

			Assert.fail();
		}

	}
	
	
	
	
	public void Login_Account(String dataSet) {
		// TODO Auto-generated method stub
		try {
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("getpostman") || Common.getCurrentURL().contains("preprod")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "username", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'primary sign-in-btn')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Postman"),
					"To validate the user lands on Home page after successfull login",
					"After clicking on the signIn button it should navigate to the Home page",
					"user Sucessfully navigate to the Home page after clicking on the signIn button",
					"Failed to signIn and not navigated to the Home page ");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user Navigate to Home page after successfull login",
					"After clicking on the signin button it should navigate to the Home page",
					"Unable to navigate the user to the home after clicking on the SignIn button",
					Common.getscreenShotPathforReport("Failed to signIn and not navigated to the Home page "));

			Assert.fail();
		}

	}
	
	
	public void WorkSpace() {
		
		try {
			int  Recent_workSpace= Common.findElements("xpath", "//h2[contains(text(),'Recently visited workspaces')]").size();
	        System.out.println(Recent_workSpace);
	        if (Recent_workSpace > 0) {
	           
	            Thread.sleep(4000);
	            Sync.waitElementPresent("xpath", "//span[contains(text(),'My Workspace')]");
	            Common.clickElement("xpath", "//span[contains(text(),'My Workspace')]");
	        }
	        
	        
	        else {

	           
	            Sync.waitElementPresent("xpath", "//span[contains(text(),'My Workspace')]");
	            Common.clickElement("xpath", "//span[contains(text(),'My Workspace')]");
	          
	            }
	        Sync.waitElementPresent("xpath", "//div[text()='Integration2']");
	        Common.clickElement("xpath", "//div[text()='Integration2']");
		
	} catch (Exception e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the user Navigate to Home page after successfull login",
				"After clicking on the signin button it should navigate to the Home page",
				"Unable to navigate the user to the home after clicking on the SignIn button",
				Common.getscreenShotPathforReport("Failed to signIn and not navigated to the Home page "));

		Assert.fail();
	}

}		
		
	public void Close_Geolocation() {
		// TODO Auto-generated method stub
		try {
			
			Sync.waitElementPresent("xpath", "(//button[@data-role='closeBtn'])[2]");
			Common.clickElement("xpath", "(//button[@data-role='closeBtn'])[2]");
			
		
	}catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
	

	}
	
	public void acceptPrivacy() {

		Common.clickElement("id", "truste-consent-button");
	}
	
	public void close_add() throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(3000);
        int sizesframe = Common.findElements("xpath", "//div[@data-testid='POPUP']").size();
        System.out.println(sizesframe);
        if (sizesframe > 0) {
            Common.actionsKeyPress(Keys.PAGE_UP);
            Thread.sleep(4000);
            Sync.waitElementPresent("xpath", "//button[contains(@class,'needsclick klaviyo-close-form kl-private-reset-css-Xuajs1')]");
            Common.clickElement("xpath", "//button[contains(@class,'needsclick klaviyo-close-form kl-private-reset-css-Xuajs1')]");
        }
        else {

            Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
            Sync.waitElementPresent("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.clickElement("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.switchToDefault();
            }

 

    }
	
}