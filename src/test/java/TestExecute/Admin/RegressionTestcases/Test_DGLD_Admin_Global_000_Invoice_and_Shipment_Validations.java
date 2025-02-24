package TestExecute.Admin.RegressionTestcases;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Global_000_Invoice_and_Shipment_Validations {
	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"InvoiceShipment");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Invoice_and_Shipment_Validations() throws Exception {

		try {
			
			FileInputStream datafile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Admin\\GoldAdminTestData.xlsx");
			XSSFWorkbook workbook= new XSSFWorkbook(datafile);
			XSSFSheet sheet=workbook.getSheet("InvoiceShipment");
			int rowcount=sheet.getLastRowNum();
			int colcount=sheet.getRow(1).getLastCellNum();
			Admin.prepareTaxData("Admin_Invoice_OrderNumbers.xlsx");
			Admin.Admin_signin("AccountDetails");
			Admin.click_Sales();
			 for(int i=1;i<=rowcount;i++)
			 {
			XSSFRow celldata=sheet.getRow(i);
			String Account=celldata.getCell(0).getStringCellValue();
		    String Status=Admin.ordersearch_status(Account);
			String Invoice=Admin.Invoice_Number(Account);
			String Shipment=Admin.Shipment_Number();
		    Admin.Information_Page();
		    Admin.writeOrderNumber(Status,Invoice,Shipment);
			 }
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Admin\\config.properties");
		Login.signIn();

	}

	
	

}
