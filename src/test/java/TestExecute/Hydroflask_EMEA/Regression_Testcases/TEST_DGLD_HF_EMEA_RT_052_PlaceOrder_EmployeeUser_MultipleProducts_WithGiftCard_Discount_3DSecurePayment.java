package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_052_PlaceOrder_EmployeeUser_MultipleProducts_WithGiftCard_Discount_3DSecurePayment {
	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Placeorder_Employee_user_multipleproducts_with_giftcardcode_discount_and_3DSecure_Payment_Method () throws Exception {
		
		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("Employee_id");
			Hydro.search_product("Product");      
			Hydro.addtocart("Product"); 
			Hydro.employee_discount();
			Hydro.bottles_headerlinks("bottles-drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.employee_discount();
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("Employee_id");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.discountCode("Discount");
            Hydro.Gift_card("Giftcard_Partial_1");  // Beforre executing Partial Gift code needs to be Added in Testdata
            Hydro.Secure_Payment_details("3d_Secure");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}


	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}

