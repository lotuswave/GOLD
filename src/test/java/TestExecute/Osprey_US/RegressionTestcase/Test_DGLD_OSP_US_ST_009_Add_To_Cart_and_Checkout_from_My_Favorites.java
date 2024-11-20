package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_ST_009_Add_To_Cart_and_Checkout_from_My_Favorites {
	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"MyFavorites");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Add_To_Cart_and_Checkout_from_My_Favorites () throws Exception {

		try {
		 	Osprey_ReEu.verifingHomePage();
	         Osprey_ReEu.click_singinButton();
	       	  Osprey_ReEu.Login_Account("Account");
	        	Osprey_ReEu.My_Favorites();
	       	 	Osprey_ReEu.Addtocart_From_MyFavorites("Product");
	        	Osprey_ReEu.RegaddDeliveryAddress("Account");
	       	 	Osprey_ReEu.selectshippingmethod("GroundShipping method");
	        	Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
        
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}
