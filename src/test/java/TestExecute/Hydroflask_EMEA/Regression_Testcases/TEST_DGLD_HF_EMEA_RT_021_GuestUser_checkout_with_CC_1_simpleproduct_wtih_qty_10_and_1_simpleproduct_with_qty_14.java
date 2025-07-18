package TestExecute.Hydroflask_EMEA.Regression_Testcases;
 
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;
 
public class TEST_DGLD_HF_EMEA_RT_021_GuestUser_checkout_with_CC_1_simpleproduct_wtih_qty_10_and_1_simpleproduct_with_qty_14 {
 
	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_checkout_with_CC_1_simpleproduct_wtih_qty_10_and_1_simpleproduct_with_qty_14 () throws Exception {
 
		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product");      
			Hydro.Tenqtyaddtocart("Product"); 
			Hydro.search_product("Bottle Brush Product");      
			Hydro.AboveTenqtyaddtocart("Bottle Brush Product");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}
 
}