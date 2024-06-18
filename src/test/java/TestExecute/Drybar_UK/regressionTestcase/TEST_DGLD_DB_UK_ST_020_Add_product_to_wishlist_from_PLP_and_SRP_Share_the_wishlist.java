package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_DGLD_DB_UK_ST_020_Add_product_to_wishlist_from_PLP_and_SRP_Share_the_wishlist {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Add_product_to_wishlist_from_PLP_and_SRP_Share_the_wishlist  () throws Exception {

		try {
			    Drybar.Verify_Homepage();
		     	Drybar.click_singinButton();
			    Drybar.login_Drybar("AccountDetails");
			    Drybar.HairTools_headerlinks("Hair Tools"); 
			    Drybar.Add_product_to_Wishlist();
				Drybar.search_product("Product");  
				Drybar.Add_product_to_Wishlist();
				Drybar.Share_WishList("WishListEmail");
				
		}   
			catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		@AfterTest
		public void clearBrowser() {
			//Common.closeAll();
			

		}


		@BeforeTest
		public void startTest() throws Exception {
			System.setProperty("configFile", "Drybar_UK\\config.properties");
	        Login.signIn();
	        Drybar.close_add();
	        
		}

	}

