
package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_054_Add_Gift_Card_Code_in_My_Account_Page_and_Select_the_Gift_Card_Code_at_Payment_Page_and_Place_an_Order {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void verifying_Add_Gift_Card_Code_in_My_Account_Page_and_Select_the_Gift_Card_Code_at_Payment_Page_and_Place_an_Order () throws Exception {

		try {
			
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.Add_GiftCode_Myaccount("Full_RedeemGiftcard");
			Hydro.search_product("Product");       
			Hydro.addtocart("Product");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
			Hydro.Select_Gift_Code("Full_RedeemGiftcard");
			Hydro.giftCardSubmitOrder();
            Hydro.Remove_GiftCode();
            
      
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

