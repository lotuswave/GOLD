package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestComponent.OXO.GoldOxoHyvaHelper;
import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_023_Promotion_Cart_Rule_Prices {
	String datafile = "Admin\\GoldAdminTestData.xlsx";
	String datafileOxo = "OXO//GoldOxoTestData.xlsx";
	String datafileHydro = "Hydroflask//GoldHydroTestData.xlsx";
	String datafileDrybar = "Drybar_US//GoldDrybarTestData.xlsx";
	String datafileOsspreyEU = "Osprey_US//GoldOspreyus.xlsx";
	
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "CartRulePrice");
	OspreyEMEA_HYVA Website_Emea = new OspreyEMEA_HYVA(datafile,"Checkout payments");
	GoldOxoHyvaHelper Website_Oxo = new GoldOxoHyvaHelper(datafileOxo,"DataSet");
	GoldHydroHyvaHelper WebsiteHydro = new GoldHydroHyvaHelper(datafileHydro,"DataSet");
	GoldDrybarusHelper2 WebsiteDrybar = new GoldDrybarusHelper2(datafileDrybar,"DataSet");
	GoldOspreyUSHyvaHelper WebsiteOsprey_ReEu = new GoldOspreyUSHyvaHelper(datafileOsspreyEU,"Checkout payments");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Catalog_product_update() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.Click_Marketing();
			Admin.select_cart_price_rule();
			Admin.Click_AddNewRule("AccountDetails");
			Admin.Rule_information("AccountDetails");
			Admin.open_website("AccountDetails");  // Change the websiye URL Based on site.
			
			
		// Uncomment if Execute on EMEA Site	
			
			Website_Emea.search_product("Product");
	        Website_Emea.addtocart("Product");
	        Website_Emea.minicart_Checkout();
	        Website_Emea.addDeliveryAddress_Guestuser("Account");
	        Website_Emea.selectshippingmethod("GroundShipping method");
	        Website_Emea.clickSubmitbutton_Shippingpage();
	        Website_Emea.discountCode("Discount");
	        Website_Emea.updatePaymentAndSubmitOrder("CCMastercard");

			// Uncomment if Execute on OXO Site
			
	/*		Website_Oxo.coffee_headerlinks("Coffee & Beverage");
			Website_Oxo.addtocart("addproduct");
			Website_Oxo.minicart_Checkout();
			Website_Oxo.addDeliveryAddress_Guest("AccountDetails");
			Website_Oxo.select_Shipping_Method("GroundShipping method");
			Website_Oxo.discountCode("Discount");
			Website_Oxo.clickSubmitbutton_Shippingpage();
			Website_Oxo.updatePaymentAndSubmitOrder("CCDiscovercard");*/
			
			// Uncomment if Execute on Hydro Site	
	/*		WebsiteHydro.search_product("Product");      
			WebsiteHydro.addtocart("Product");   
			WebsiteHydro.minicart_Checkout();
			WebsiteHydro.addDeliveryAddress_Guestuser("AccountDetails");
			WebsiteHydro.selectshippingaddress("GroundShipping method");
			WebsiteHydro.clickSubmitbutton_Shippingpage();
			WebsiteHydro.discountCode("Discount");
			WebsiteHydro.updatePaymentAndSubmitOrder("PaymentDetails");*/
			
			
	/*		WebsiteDrybar.Verify_Homepage();
			WebsiteDrybar.HairTools_headerlinks("Hair Tools"); 
			WebsiteDrybar.addtocart("PLP Product");
			WebsiteDrybar.minicart_Checkout();
			WebsiteDrybar.addDeliveryAddress_Guestuser("Address");
			WebsiteDrybar.selectshippingmethod("GroundShipping method");
			WebsiteDrybar.discountCode("Discount");
			WebsiteDrybar.updatePaymentAndSubmitOrder("PaymentDetails");*/
			
			
/*			WebsiteOsprey_ReEu.search_product("Product");
			WebsiteOsprey_ReEu.addtocart("Product");
			WebsiteOsprey_ReEu.minicart_Checkout();
			WebsiteOsprey_ReEu.addDeliveryAddress_Guestuser("Account");
			WebsiteOsprey_ReEu.selectshippingmethod("GroundShipping method");
			WebsiteOsprey_ReEu.clickSubmitbutton_Shippingpage();
			WebsiteOsprey_ReEu.discountCode("Discount");
	        WebsiteOsprey_ReEu.updatePaymentAndSubmitOrder("CCMastercard");*/
	        
	        
			Admin.Backto_magento_admin();
			Admin.delet_existing_Coupon("AccountDetails");

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
		System.setProperty("configFile", "Admin\\config.properties");
		Login.signIn();

	}

}
