package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_028_EmployeeUser_1Simple_1Configurable_5QtyEach_PartialGift_Discount_Paypal {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Registeruser_checkout_with_1_simple_1Configurable_5QtyEach_Discount_Giftcode_Paypal () throws Exception {
 
		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("Employee_id");
			Hydro.search_product("Product_5QTY");      
			Hydro.addtocart("Product_5QTY"); 
			Hydro.bottles_headerlinks("bottles-drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product_5QTY");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.Gift_card("Giftcard_Partial_6");
            Hydro.payPal_Payment("PaypalDetails");
			
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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}
 
}