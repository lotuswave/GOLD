package TestExecute.Hydroflask.Archive;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_US_ST_008_Placeorder_Register_user_mutipleproducts_with_giftcardcode_discount_and_CC {
	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_RegisterUser_mutipleproducts_with_giftcardcode_discount_and_CC () throws Exception {

		try {
        Hydro.verifingHomePage();
        Hydro.click_singinButton();
		Hydro.login_Hydroflask("AccountDetails");
		Hydro.search_product("Product");     
		Hydro.addtocart("Product");
		Hydro.bottles_headerlinks("Bottles & Drinkware"); 
		Hydro.Configurable_addtocart_pdp("Product");
		Hydro.minicart_Checkout();
		Hydro.RegaddDeliveryAddress("AccountDetails");
        Hydro.selectshippingaddress("GroundShipping method");
        Hydro.clickSubmitbutton_Shippingpage();
        Hydro.discountCode("Discount");
        Hydro.Gift_card("Giftcard");
        Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
        
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