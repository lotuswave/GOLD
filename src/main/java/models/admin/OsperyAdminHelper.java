package models.admin;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;

public class OsperyAdminHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public OsperyAdminHelper(String datafile, String DataSet) {
		excelData = new ExcelReader(datafile, DataSet);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Admin");
			report.createTestcase("AdminTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}
	}