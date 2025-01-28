package TestExecute.Osprey_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUS_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_ST_053_Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu = new GoldOspreyUS_PRODHyvaHelper(datafile,"Search");
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu1 = new GoldOspreyUS_PRODHyvaHelper(datafile,"PDP");
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu2 = new GoldOspreyUS_PRODHyvaHelper(datafile,"Giftcard Payments");


	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Invalid_search_product("Invalid_Product");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.Sort_By("SortBy");
        Osprey_ReEu.Filter();
        Osprey_ReEu1.Bagpacks_headerlinks("Backpacks & Bags");     
        Osprey_ReEu1.view_PLP_page();
//        Osprey_ReEu1.sort_By("SortBy");
        Osprey_ReEu1.filter_By("Fliters");
        Osprey_ReEu1.color_validation("PLP Color");
        Osprey_ReEu1.price_filter_validation("PLP Color");
        Osprey_ReEu1.Bagpacks_headerlinks("Backpacks & Bags");       
        Osprey_ReEu1.Simple_PDP("Simple product");
        Osprey_ReEu1.search_product("Product");  
        Osprey_ReEu1.Configurable_PDP("Product");
        Osprey_ReEu1.addtocart("Product");//
	    Osprey_ReEu2.Gift_cards("Osprey Gift Card");
        Osprey_ReEu2.Card_Value("Gift Details");
        Osprey_ReEu2.minicart_Checkout();
        Osprey_ReEu2.addDeliveryAddress_Guestuser("Account");
        Osprey_ReEu2.selectshippingmethod("GroundShipping method");
        Osprey_ReEu2.clickSubmitbutton_Shippingpage();
        Osprey_ReEu2.updatePaymentAndSubmitOrder("CCVisacard");

 
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
