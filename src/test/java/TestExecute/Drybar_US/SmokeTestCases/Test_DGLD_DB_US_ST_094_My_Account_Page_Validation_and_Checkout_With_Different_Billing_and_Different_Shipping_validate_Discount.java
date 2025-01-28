package TestExecute.Drybar_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_US_ST_094_My_Account_Page_Validation_and_Checkout_With_Different_Billing_and_Different_Shipping_validate_Discount {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar1 = new GoldDrybarusHelper2(datafile,"My AccountPage");
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verify_My_Account_Page_Validation_and_Checkout_With_Different_Billing_and_Different_Shipping_validate_Discount () throws Exception {

		try {
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar1.login_Drybar("AccountDetails");
			Drybar1.Account_page_Validation("My Account page validation"); 
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.BillingAddress("BillingDetails");
			Drybar.discountCode("Discount");
			Drybar.updatePaymentAndSubmitOrder("CCMastercard");
		

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
