package TestExecute.Drybar_US.regressionTestcase;
      
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_076_Validate_RegisterUser_Chechout_Aerosol_Products_and_Non_Aerosol_Products_with_POBox_Hawaii_with_No_Shipping_Methods {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Chechout_Aerosol_Products_and_Non_Aerosol_Products_with_POBox_Hawaii_with_No_Shipping_Methods () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetail");
			Drybar.HairTools_headerlinks("Hair Tools"); 
		    Drybar.addtocart("PLP Product");
			Drybar.add_aerosolproduct("Aerosol");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("NoTaxAddress");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.No_Tax_Validation();
			Drybar.same_Blling_and_Shipping_SubmitOrder("CCDiscovercard");
			

		

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
