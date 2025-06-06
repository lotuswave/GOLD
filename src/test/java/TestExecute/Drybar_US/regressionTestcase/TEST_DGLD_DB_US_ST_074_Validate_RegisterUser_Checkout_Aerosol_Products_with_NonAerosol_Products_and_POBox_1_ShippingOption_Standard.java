package TestExecute.Drybar_US.regressionTestcase;
         
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_074_Validate_RegisterUser_Checkout_Aerosol_Products_with_NonAerosol_Products_and_POBox_1_ShippingOption_Standard {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Checkout_Aerosol_Products_with_NonAerosol_Products_and_POBox_1_ShippingOption_Standard () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetail");
			Drybar.HairTools_headerlinks("Hair Tools"); 
		    Drybar.addtocart("PLP Product");
			Drybar.add_aerosolproduct("Aerosol");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("PO Box Address");
			Drybar.selectshippingmethod("StandardShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
	//		Drybar.tax_validation_Paymentpage("Address");
			Drybar.same_Blling_and_Shipping_SubmitOrder("PaymentDetails");
		

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
