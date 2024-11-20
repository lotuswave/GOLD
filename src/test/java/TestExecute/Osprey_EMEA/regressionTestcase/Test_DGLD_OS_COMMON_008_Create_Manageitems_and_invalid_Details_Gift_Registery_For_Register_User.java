package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_008_Create_Manageitems_and_invalid_Details_Gift_Registery_For_Register_User {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"GiftRegistry");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Create_share_and_Delete_Gift_Registery_For_Register_User () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Giftaccount");
        Osprey_ReEu.giftCreation("Birthday");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.minicart_viewcart();
        Osprey_ReEu.additems_giftregistry("Product Qunatity");
        Osprey_ReEu.noitems_giftregistry("Baby Registry");
        Osprey_ReEu.share_invalid_details("Baby Registry");
        
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
