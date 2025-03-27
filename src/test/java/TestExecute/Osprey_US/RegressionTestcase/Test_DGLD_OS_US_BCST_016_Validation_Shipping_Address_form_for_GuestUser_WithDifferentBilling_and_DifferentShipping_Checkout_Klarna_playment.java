package TestExecute.Osprey_US.RegressionTestcase;
 
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;
public class Test_DGLD_OS_US_BCST_016_Validation_Shipping_Address_form_for_GuestUser_WithDifferentBilling_and_DifferentShipping_Checkout_Klarna_playment {
	
	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validation_Shipping_Address_form_for_GuestUser_Checkout_WithDifferentBilling_and_DifferentShipping_Klarna_playment () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("poco Product");
        Osprey_ReEu.addtocart("poco Product");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.Shippingform_Guestuser("Invalid Address");
        Osprey_ReEu.validatingErrormessageShippingpage_negative();
        Osprey_ReEu.addDeliveryAddress_Guestuser("Account");
        Osprey_ReEu.BillingAddress("BillingDetails");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");
        Osprey_ReEu.Kalrna_Payment("Klarna Visa Payment");
        
        
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
