package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_079_Checkout_with_RegisterUserCC_Simple_Configurable_Engraving_Grouped_and_Bundle_products {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile,"Engraving");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Checkout_with_RegisterUserCC_Simple_Configurable_Engraving_Grouped_and_Bundle_products () throws Exception {
		

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.search_product("Product");     
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.search_product("Bundle product"); 
			Hydro.Addtocart_Bundle("Bundle product");
//			Hydro.search_product("Grouped Bundle");    
//			Hydro.Add_Grouped_Bundle("Grouped Bundle");
			Hydro.search_product("Engraving Product");   
			Hydro.Text_Engraving("Engraving Product");
			Hydro.enraving_Checkout("Horizontal Text");
			Hydro.RegaddDeliveryAddress("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
			Hydro.updatePaymentAndSubmitOrder("CCAmexcard");

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
