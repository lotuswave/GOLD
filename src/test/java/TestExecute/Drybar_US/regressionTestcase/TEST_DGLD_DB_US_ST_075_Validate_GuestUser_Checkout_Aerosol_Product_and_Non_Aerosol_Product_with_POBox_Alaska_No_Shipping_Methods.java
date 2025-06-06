package TestExecute.Drybar_US.regressionTestcase;
      
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_075_Validate_GuestUser_Checkout_Aerosol_Product_and_Non_Aerosol_Product_with_POBox_Alaska_No_Shipping_Methods {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_Checkout_Aerosol_Product_and_Non_Aerosol_Product_with_POBox_Alaska_No_Shipping_Methods () throws Exception {

		try {
		

		Drybar.Verify_Homepage();
		Drybar.HairTools_headerlinks("Hair Tools"); 
	    Drybar.addtocart("PLP Product");
		Drybar.add_aerosolproduct("Aerosol");
		Drybar.minicart_Checkout();
		Drybar.addDeliveryAddress_Guestuser("Po_Box_Alaska");
		Drybar.Validate_shipping_methods();
		
		
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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
       Drybar.close_add();
        

	}

}
