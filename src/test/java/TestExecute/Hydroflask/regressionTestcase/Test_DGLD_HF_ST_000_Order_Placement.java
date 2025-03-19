package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import java.io.FileInputStream;
import TestLib.Login;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Test_DGLD_HF_ST_000_Order_Placement {

	String datafile = "Hydroflask//GoldHydroTestData1.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"order_Placement");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_Checkout_Funtionality_Visa_card () {

		try {
			FileInputStream datafile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Hydroflask\\GoldHydroTestData1.xlsx");
			XSSFWorkbook workbook= new XSSFWorkbook(datafile);
			XSSFSheet sheet=workbook.getSheet("order_Placement");
			int rowcount=sheet.getLastRowNum();
			int colcount=sheet.getRow(1).getLastCellNum();
			Hydro.prepareTaxData("Hydro_OrderNumbers.xlsx");
			
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			 for(int i=1;i<=rowcount;i++)
			 {
				 
			XSSFRow celldata=sheet.getRow(i);
			String Account=celldata.getCell(0).getStringCellValue();
		    Thread.sleep(10000);
//			String website = Hydro.website();
		    Hydro.reorder();
	        Hydro.minicart_Checkout();
	        Hydro.RegaddDeliveryAddress(Account);
            Hydro.selectshippingaddress(Account);
            Hydro.clickSubmitbutton_Shippingpage();
			String Ordernumber= Hydro.updatePaymentAndSubmitOrder(Account);
			Hydro.writeResultstoXLSx(Ordernumber);
			
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
