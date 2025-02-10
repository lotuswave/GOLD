package TestExecute.Hydroflask.Archive;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;
public class Test_DGLD_HF_ST_062_Guest_user_Checkout_with_2_DayShipping_method {
    String datafile = "Hydroflask//GoldHydroTestData.xlsx";
//   Sheet name
    GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Validation_Guest_user_Checkout_with_2_DayShipping_method() throws Exception {
        try {
            Hydro.verifingHomePage();
            Hydro.search_product("Product");   
            Hydro.addtocart_PLP("Product");
            Hydro.bottles_headerlinks("Bottles & Drinkware"); 
            Hydro.Configurable_addtocart_pdp("Product");
            Hydro.minicart_Checkout();
            Hydro.addDeliveryAddress_Guestuser("AccountDetails");
            Hydro.selectshippingaddress("2 Day method");
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