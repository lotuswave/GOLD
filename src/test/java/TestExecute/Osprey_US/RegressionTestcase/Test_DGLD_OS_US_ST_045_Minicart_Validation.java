package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_ST_045_Minicart_Validation {

		String datafile = "Osprey_US//GoldOspreyus.xlsx";
		GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Minicart");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Minicart_Validation () throws Exception {

			try {
				Osprey_ReEu.verifingHomePage();
				Osprey_ReEu.search_product("Product");     
		        Osprey_ReEu.addtocart("Product");
			 // Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
			    Osprey_ReEu.search_product("Simple product");                   
			    Osprey_ReEu.simple_addtocart("Simple product");            
			    Osprey_ReEu.click_minicart();
			    Osprey_ReEu.clickontheproduct_and_image("Product");
			    Osprey_ReEu.minicart_freeshipping();
			    Osprey_ReEu.minicart_delete("Product");
	        
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