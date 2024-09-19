package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_SUB_ST_014_Registered_user_checkout_with_Amex_Credit_card {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Registered_User_Checkout_Funtionality_Amex_card () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Triple_SUB_Product");  
			Drybar.subcribe_product_Add_to_Cart("Triple_SUB_Product");
			Drybar.subscription_One_Time_Purchase();
			Drybar.search_product("Liquid_Glass_Sub_Product");  
			Drybar.subcribe_product_Add_to_Cart("Liquid_Glass_Sub_Product");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			String rewardpoints=Drybar.Twentypercent_Reward_Points("$20 Off (400 points)");
			Drybar.updatePaymentAndSubmitOrder("CCAmexcard");
			String Profile_id="2";
			Drybar.Verify_Profile_ids(Profile_id);
			Drybar.verify_RewardPoints(rewardpoints);
			

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
