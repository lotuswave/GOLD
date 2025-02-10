package TestExecute.Hydroflask.Archive;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_063_Guest_User_Checkout_with_Bundle_product {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"Bundle");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_User_Checkout_with_Bundle_product () throws Exception {
		
		
//		for(int i=0;i<2;i++)
//		{
		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Bundle product"); 
			Hydro.Addtocart_Bundle("Bundle product");   
//			Hydro.search_product("Bundle product1"); 
//			Hydro.Addtocart_Bundle("Bundle product1"); 
//			Hydro.search_product("Bundle product2"); 
//			Hydro.Addtocart_Bundle("Bundle product2"); 
//			Hydro.search_product("Color warranty");     
//			Hydro.Configurable_addtocart_pdp("Color warranty");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
//	}
	

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
