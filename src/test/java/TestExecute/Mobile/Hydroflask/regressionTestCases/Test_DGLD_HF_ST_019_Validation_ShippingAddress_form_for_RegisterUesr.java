package TestExecute.Mobile.Hydroflask.regressionTestCases;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHelper_Mobile;
import TestLib.Common;
import TestLib.Login;
public class Test_DGLD_HF_ST_019_Validation_ShippingAddress_form_for_RegisterUesr {
    String datafile = "Hydroflask//GoldHydroTestData.xlsx";
//   Sheet name
    GoldHydroHelper_Mobile Hydro = new GoldHydroHelper_Mobile(datafile,"Sheet1");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Validation_ShippingAddress_form_for_RegisterUesr() throws Exception {
        try {
            Hydro.verifingHomePage();
            Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
            Hydro.search_product("Product");      
            Hydro.addtocart("Product");                    
            Hydro.minicart_Checkout();
            Hydro.addDeliveryAddress_registerUser("InvalidAddress");
            Hydro.selectStandedshippingaddress();
            Hydro.clickSubmitbutton_Shippingpage();
            Hydro.validatingErrormessageShippingpage_negative();
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
    	String device=System.getProperty("dev","IOS");
		System.setProperty("configFile", "Hydroflask/mobile_config.properties");
		if(device.equalsIgnoreCase("ios")) {
			System.setProperty("configFile", "Hydroflask/mobile_config_ios.properties");
		}
		  Login.mobilesignIn(device);
        Hydro.close_add();

    }
}