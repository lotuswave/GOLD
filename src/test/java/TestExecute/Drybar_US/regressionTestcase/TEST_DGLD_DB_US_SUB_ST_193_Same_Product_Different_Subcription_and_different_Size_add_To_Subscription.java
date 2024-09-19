package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_SUB_ST_193_Same_Product_Different_Subcription_and_different_Size_add_To_Subscription {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Same_Product_Different_Subcription_and_different_Size  () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Detox_SUB_Plans_Product");  
			Drybar.subcribe_product_all_types_plan_Add_to_Cart ("Detox_SUB_Plans_Product");
			Drybar.size_selection("Detox_SUB_Plans_Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.updatePaymentAndSubmitOrder("CCMastercard");
			String Profile_id="6";
			String profileID_Number=Drybar.Verify_Profile_ids(Profile_id);
			Drybar.search_product("Triple_SUB_Product");
			Drybar.selecting_the_Previous_Subscription("Triple_SUB_Product");
			Drybar.add_To_Subscription(profileID_Number);

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
