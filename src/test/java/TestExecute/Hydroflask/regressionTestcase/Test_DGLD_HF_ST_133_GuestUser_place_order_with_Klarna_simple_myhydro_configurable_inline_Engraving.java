package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_133_GuestUser_place_order_with_Klarna_simple_myhydro_configurable_inline_Engraving {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"Engraving");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_place_order_with_Klarna_simple_myhydro_configurable_inline_Engraving() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product");   
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.search_product("Myhydro Product");   
			Hydro.Add_Myhydro("Myhydro Product");  
			Hydro.search_product("Engraving Product"); 
			Hydro.Graphic_Engraving("Engraving Product");
			Hydro.enraving_Checkout("Graphic");
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
			Hydro.Klarna_Saved_Payment("Klarna Visa Payment");
          
            
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
