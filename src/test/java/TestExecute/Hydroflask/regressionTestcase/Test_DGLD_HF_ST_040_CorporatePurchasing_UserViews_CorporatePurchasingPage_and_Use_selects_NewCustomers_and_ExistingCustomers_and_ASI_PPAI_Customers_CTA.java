package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_040_CorporatePurchasing_UserViews_CorporatePurchasingPage_and_Use_selects_NewCustomers_and_ExistingCustomers_and_ASI_PPAI_Customers_CTA {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"Forms");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_CorporatePurchasing_UserViews () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.corporate_purchasing();
			Hydro.new_Account_Inquiry_corporate("DealercentralNewInquiry");
			
			
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
