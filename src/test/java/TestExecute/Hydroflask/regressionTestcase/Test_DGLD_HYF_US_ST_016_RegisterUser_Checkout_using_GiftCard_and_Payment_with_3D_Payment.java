package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_US_ST_016_RegisterUser_Checkout_using_GiftCard_and_Payment_with_3D_Payment {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Register_user_checkout_Partial_Payment_Using_GiftCard_And_3Ds_Authentication_Payment () throws Exception {
		
		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.minicart_Checkout();
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
            Hydro.Gift_card("Giftcard_Partial_2");
            Hydro.Secure_Payment_details("3d_Secure");

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
