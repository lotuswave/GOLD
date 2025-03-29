package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Login;
import TestLib.Common;

public class TEST_DGLD_DB_US_SUB_ST_191_GuestUser_Simple_product_Checkout_with_GiftCode_FullRedeem {
	
	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_Simple_product_Checkout_with_GiftCode_FullRedeem () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.search_product("Detox_SUB_Product");  
			Drybar.subcribe_product_Add_to_Cart("Detox_SUB_Product");
			Drybar.Guest_SUB_minicart_Checkout();
			Drybar.login_Drybar("AccountDetails");
			Drybar.minicart_Checkout();
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.gitCard("GiftCode Full Redeem");
			Drybar.updatePaymentAndSubmitOrder("CCMastercard");
			//Drybar.giftCardSubmitOrder();  // need to confirm with the amy about the full payment
			Drybar.subcription_Profile();
			

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
