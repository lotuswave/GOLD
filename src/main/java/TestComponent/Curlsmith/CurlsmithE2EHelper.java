package TestComponent.Curlsmith;

import static org.testng.Assert.fail;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
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
  
public class CurlsmithE2EHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public CurlsmithE2EHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("curlsmith");
			report.createTestcase("CurlsmithTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public void verify_Homepage() {
		// TODO Auto-generated method stub
		
		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			int size = Common.findElements("xpath", "//a[@class='site-header__logo-link']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Curlsmith USA Dev"), "validating the Navigation to the Home page",
					"System directs the user to the Homepage", "Sucessfully user navigates to the home page",
					"Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Home page", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}
		
	}

	
}



			


		



			





		


		



			





		

	