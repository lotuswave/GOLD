package TestExecute.OXO.regressionTestcase;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_000_Order_Placement {

	String datafile = "OXO//GoldOxoTestData1.xlsx";	
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"order_Placement");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_Checkout_Funtionality_Visa_Card() throws Exception {

		try {
			
			FileInputStream datafile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OXO\\GoldOxoTestData1.xlsx");
			XSSFWorkbook workbook= new XSSFWorkbook(datafile);
			XSSFSheet sheet=workbook.getSheet("order_Placement");
			int rowcount=sheet.getLastRowNum();
			int colcount=sheet.getRow(1).getLastCellNum();
			Oxo.prepareTaxData("Oxo_OrderNumbers.xlsx");
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			
			 for(int i=1;i<=rowcount;i++)
			 {
				 
			XSSFRow celldata=sheet.getRow(i);
			String Account=celldata.getCell(0).getStringCellValue();
		    Thread.sleep(10000);
		    Oxo.reorder();
		    Oxo.minicart_Checkout();
		    Oxo.addDeliveryAddress_registerUser(Account);
		    Oxo.select_Shipping_Method(Account);
		    Oxo.clickSubmitbutton_Shippingpage();
			String Ordernumber= Oxo.updatePaymentAndSubmitOrder(Account);
			Oxo.writeResultstoXLSx(Ordernumber);
			
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
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();

	}

}
