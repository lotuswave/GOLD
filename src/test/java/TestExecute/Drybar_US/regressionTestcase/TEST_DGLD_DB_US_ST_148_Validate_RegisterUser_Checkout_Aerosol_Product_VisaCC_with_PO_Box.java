package TestExecute.Drybar_US.regressionTestcase;
       
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_148_Validate_RegisterUser_Checkout_Aerosol_Product_VisaCC_with_PO_Box {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Checkout_Aerosol_Product_VisaCC_with_PO_Box () throws Exception {

		try {
	
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Aerosol");  
			Drybar.addtocart("Aerosol");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("PO Box Address");
			Drybar.selectshippingmethod("POBox Shipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");
			
			
		

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
