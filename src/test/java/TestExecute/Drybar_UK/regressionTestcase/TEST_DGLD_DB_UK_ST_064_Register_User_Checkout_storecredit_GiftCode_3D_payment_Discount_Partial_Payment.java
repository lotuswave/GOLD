package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_064_Register_User_Checkout_storecredit_GiftCode_3D_payment_Discount_Partial_Payment {

	

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Reg_User_Checkout_Storecredit_GiftCode_3d_payment_Discount_Partial_Payment () throws Exception {

		try {
			
			
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			String Price= Drybar.Store_Credit_balance();
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.gitCard("Partial GiftCode");
			Drybar.Apply_Store_Credit(Price);
			Drybar.Secure_Payment_details("3d_Secure");

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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}
}
