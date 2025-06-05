package TestExecute.Hydroflask_EMEA.Regression_Testcases;
 
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;
 
public class Test_DGLD_HF_EMEA_RT_033_Validate_CountrySelector {
 
	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"Country");
 
@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
public void Verifying_Validate_Country_Selector () throws Exception {
 
	try {
		Hydro.verifingHomePage();
		Hydro.country_selctor("Countryselector");
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