package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_130_Register_user_1_item_with_10_Qty_StoreCredit_Full_Redeem_Checkout_and_validate_the_StoreCredit_balance_in_MYAccountpage {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_user_1_item_with_10_Qty_StoreCredit_Full_Redeem_Checkout_and_validate_the_StoreCredit_balance_in_MYAccountpage () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			String Price= Drybar.Store_Credit_balance();
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("10 qty product");
			Drybar.minicart_Checkout();
	    	Drybar.RegaddDeliveryAddress("AccountDetails");
	    	Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.Apply_Store_Credit(Price);
			Drybar.giftCardSubmitOrder();

			

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
