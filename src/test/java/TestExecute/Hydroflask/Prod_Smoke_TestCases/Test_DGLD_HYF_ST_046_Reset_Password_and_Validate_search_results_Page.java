package TestExecute.Hydroflask.Prod_Smoke_TestCases;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_ST_046_Reset_Password_and_Validate_search_results_Page {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Reset_Password_Validate_search_GuestCheckout_validate_Minicart_and_Login_from_shipping_Page_Express_Paypal() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.Forgot_password("AccountDetails");		
			Hydro.search_product("Product");      
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.click_minicart();
			Hydro.clickontheproduct_and_image("Product");
			Hydro.minicart_freeshipping();
			Hydro.minicart_delete("Product");
			Hydro.minicart_product_close();
			Hydro.minicart_validation("Product Qunatity");	                 
			Hydro.minicart_Checkout();	
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
            		Hydro.selectshippingaddress("GroundShipping method");
			Hydro.Express_Paypal("PaypalDetails");
			Hydro.paypal_close();                              
           	        Hydro.click_singin_Shippingpage();                           
			Hydro.Signin_Checkoutpage("AccountDetails");       
			
			
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}