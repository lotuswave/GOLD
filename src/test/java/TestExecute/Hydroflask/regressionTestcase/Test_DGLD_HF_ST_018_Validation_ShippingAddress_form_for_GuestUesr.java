package TestExecute.Hydroflask.regressionTestcase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;
public class Test_DGLD_HF_ST_018_Validation_ShippingAddress_form_for_GuestUesr {
    String datafile = "Hydroflask//GoldHydroTestData.xlsx";
//   Sheet name
    GoldHydroHelper Hydro = new GoldHydroHelper(datafile,"Sheet1");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Validation_ShippingAddress_form_for_GuestUesr_with_Errormessages() throws Exception {
        try {
            Hydro.verifingHomePage();
            Hydro.search_product("Product");      
            Hydro.addtocart("Product");                    
            Hydro.minicart_Checkout();
            Hydro.addDeliveryAddress_Gustuser("InvalidAddress");
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
        System.setProperty("configFile", "Hydroflask\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();
    }
}