package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_078_Checkout_with_GuestUserCC_Simple_Configurable_Engraving_Grouped_and_Bundle_products {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile,"Engraving");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Checkout_with_GuestUserCC_Simple_Configurable_Engraving_Grouped_and_Bundle_products () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product");     
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.search_product("Bundle product"); 
			Hydro.Addtocart_Bundle("Bundle product");
//			Hydro.search_product("Grouped Bundle");    
//			Hydro.Add_Grouped_Bundle("Grouped Bundle");
			Hydro.search_product("Engraving Product"); 
			Hydro.Graphic_Engraving("Engraving Product");
			Hydro.enraving_Checkout("Graphic");
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
            Hydro.selectshippingaddress("2 Day method");
            Hydro.clickSubmitbutton_Shippingpage();
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
