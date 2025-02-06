package TestComponent.Curlsmith;

import static org.testng.Assert.fail;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import groovyjarjarantlr.CommonAST;

public class CurlsmithE2EHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public CurlsmithE2EHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("curlsmith");
			report.createTestcase("CurlsmithTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public void verify_Homepage() {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			AcceptPrivacy();
			int size = Common.findElements("xpath", "//a[@class='site-header__logo-link']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Curlsmith USA Dev"),
					"validating the Navigation to the Home page", "System directs the user to the Homepage",
					"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Home page",
					"System directs the user to the Homepage", " user unable navigates to the home page",
					"Failed to navigate to the homepage");
			Assert.fail();
		}

	}

	public void AcceptPrivacy() {
		try {

			int size = Common.findElements("id", "truste-consent-button").size();
			if (size > 0) {

				Sync.waitElementPresent("id", "truste-consent-button");
				Common.clickElement("id", "truste-consent-button");
			} else {

				System.out.println("Accpect Privacy not displayed");
			}
		} catch (Exception | Error e) {

		}
	}

	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//span[text()='Search']//parent::a");
			String open = Common.findElement("xpath", "//div[@class='site-header__search']//form")
					.getAttribute("class");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("is-active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			WebElement serachbar = Common.findElement("xpath", "//input[@class='site-header__search-input']");
			serachbar.sendKeys(product);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(8000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}
	}

	public void Bundle_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//span[text()='Search']//parent::a");
			String open = Common.findElement("xpath", "//div[@class='site-header__search']//form")
					.getAttribute("class");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("is-active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			WebElement serachbar = Common.findElement("xpath", "//input[@class='site-header__search-input']");
			serachbar.sendKeys(product);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(8000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}
	}

	public void Configurable_addtocart(String Dataset) {
		// TODO Auto-generated method stub

		String products = data.get(Dataset).get("Products");
		String Productsize = data.get(Dataset).get("size");
//		String scent=data.get(Dataset).get("scent");
		System.out.println(Productsize);
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'grid-product__image lazyautosizes')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'grid-product__image lazyautosizes')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			if (Common.getCurrentURL().contains("/gb")) {
				Sync.waitPageLoad(30);
				Thread.sleep(6000);
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
//				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + scent + "']");
//				Common.clickElement("xpath", "//div[@data-option-label='" + scent + "']");
				Sync.waitElementPresent("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
				Common.clickElement("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
				String size = Common.findElement("xpath", "(//span[contains(@class,'m-swatch-group__header s')])[2]")
						.getText().toUpperCase();
				System.out.println(size);
				String size1 = data.get(Dataset).get("size").toUpperCase();
				System.out.println(size1);
				Common.assertionCheckwithReport(size.equals(size1),
						"Verifying the the size of the product is selected in the PDP",
						"after clicking on the size product size should be selected",
						"successfully Product size has been selected on the PDP",
						"Failed to select the product price on the PDP");
				product_quantity(Dataset);
				Thread.sleep(4000);

				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Sync.waitPageLoad();
				Thread.sleep(6000);
			} else {

				Sync.waitPageLoad(30);
				Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
				Thread.sleep(3000);
//				Sync.waitElementPresent("xpath", "//label[contains(text(),'" + Productsize + "')]");
//				Common.clickElement("xpath", "//label[contains(text(),'" + Productsize + "')]");
//				Thread.sleep(8000);
//				String size = Common.findElement("xpath", "(//div[@class='variant-input']/label)[2]").getText()
//						.toUpperCase();
//				System.out.println(size);
//				String size1 = data.get(Dataset).get("size").toUpperCase();
//				System.out.println(size1);
//				Common.assertionCheckwithReport(size.equals(size1),
//						"Verifying the the size of the product is selected in the PDP",
//						"after clicking on the size product size should be selected",
//						"successfully Product size has been selected on the PDP",
//						"Failed to select the product price on the PDP");
				product_quantity(Dataset);
				Thread.sleep(4000);

				Sync.waitElementPresent("xpath", "//button[contains(@id,'AddToCart')]");
				Common.clickElement("xpath", "//button[contains(@id,'AddToCart')]");
				Sync.waitPageLoad();
				Thread.sleep(6000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}
	}

	private void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		System.out.println(Quantity);

		try {
			Common.findElement("xpath", "//div[@class='js-qty__wrapper']");
			Thread.sleep(5000);
			if (Quantity.equals("10+")) {

				Sync.waitElementPresent("xpath", "//select[@name='qty']");
//				Common.clickElement("xpath","//select[@name='qty']");
//				Common.dropdown("xpath", "//select[@name='qty']", Common.SelectBy.VALUE, Quantity);
				Common.clickElement("xpath", "//select[@name='qty']//option[@value='10']");
				Thread.sleep(3000);
				String value = Common.findElement("xpath", "//input[@name='qty']").getAttribute("value");
				Common.assertionCheckwithReport(value.contains("10") || value.contains(Quantity),
						"validating the  product the product quantity in PDP page",
						"Product quantity should be update in the PDP page",
						"Sucessfully product Qunatity has been updated ",
						"failed to Update the prodcut quantity in PDP page");

			} else {
				WebElement clear = Common.findElement("xpath", "//input[@class='js-qty__num']");
				clear.sendKeys(Keys.CONTROL + "a");
				Thread.sleep(2000);
				clear.sendKeys(Keys.DELETE);
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//input[@class='js-qty__num']", Quantity);
				Thread.sleep(4000);
				String value = Common.findElement("xpath", "//input[@class='js-qty__num']").getAttribute("value");
				System.out.println(value);
				Common.assertionCheckwithReport(value.equals(Quantity),
						"validating the  product the product quantity in PDP page",
						"Product quantity should be update in the PDP page",
						"Sucessfully product Qunatity has been updated ",
						"failed to Update the prodcut quantity in PDP page");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page", "unable to change the  product Qunatity",
					Common.getscreenShot("failed to update the product quantity"));
			Assert.fail();
		}

	}

	public void minicart_Checkout() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@class='cart__item-sub cart__item-row']//div[2]");
			String minicart = Common.findElement("xpath", "//div[@class='cart__item-sub cart__item-row']//div[2]")
					.getText();
			System.out.println(minicart);
			Sync.waitElementPresent(30, "xpath", "//button[@class='btn cart__checkout']");
			Common.clickElement("xpath", "//button[@class='btn cart__checkout']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout"),
					"validating the navigation to the shipping page when we click on the checkout",
					"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
					"Failed to navigate to the shipping page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the shipping page when we click on the checkout ",
					"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
					Common.getscreenShot("Failed to navigate to the shipping page"));

			Assert.fail();
		}

	}

	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");
		String symbol = data.get(dataSet).get("Symbol");

		try {
			Sync.waitElementPresent("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(dataSet).get("Email"));
			Sync.waitElementPresent("xpath", "//input[@name='firstName' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='firstName' and @placeholder]",
					data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("xpath", "//input[@name='lastName' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='lastName' and @placeholder]",
					data.get(dataSet).get("LastName"));
			Sync.waitElementPresent("xpath", "//input[@name='address1' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='address1' and @placeholder]", address);
			Sync.waitElementPresent("xpath", "//input[@name='city' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='city' and @placeholder]", data.get(dataSet).get("City"));
			Sync.waitElementPresent("xpath", "//select[@name='zone']");
			Common.dropdown("xpath", "//select[@name='zone']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Sync.waitElementPresent("xpath", "//input[@name='postalCode' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='postalCode' and @placeholder]",
					data.get(dataSet).get("postcode"));
			Sync.waitPageLoad();
			Thread.sleep(2000);

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}

	}

	public void select_Shipping_Method() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//button[@type='submit']//span[text()='Continue to shipping']");
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Continue to shipping']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//button[@type='submit']//span[text()='Continue to payment']");
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Continue to payment']");
			Sync.waitPageLoad();
			Thread.sleep(2000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating user navigated to the payment page",
					"After clicking on the continue to payment it should navigate to the payment page",
					"User unable to navigate to the payment page", Common.getscreenShotPathforReport(
							"Failed to Navigate to the payment page after clicking on the continue payment"));
			Assert.fail();
		}

	}

	public String CC_payment_method(String Dataset) {
		// TODO Auto-generated method stub
		String ConfirmationNumber = "";
		try {
			Thread.sleep(4000);
			Common.switchFrames("xpath",
					"//iframe[@class='card-fields-iframe' and contains(@name,'card-fields-number')]");
			Common.textBoxInput("xpath", "//input[@id='number' and @placeholder]", data.get(Dataset).get("cardNumber"));
			Common.switchToDefault();
			Thread.sleep(4000);
			Common.switchFrames("xpath",
					"//iframe[@class='card-fields-iframe' and contains(@name,'card-fields-expiry')]");
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@id='expiry' and @placeholder]",
					data.get(Dataset).get("ExpMonthYear"));
			Common.switchToDefault();
			Thread.sleep(4000);
			Common.switchFrames("xpath",
					"//iframe[@class='card-fields-iframe' and contains(@name,'card-fields-verification_value')]");
			Common.textBoxInput("xpath", "//input[@id='verification_value' and @placeholder]",
					data.get(Dataset).get("cvv"));
			Common.switchToDefault();
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Pay now']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			ConfirmationNumber = Common.findElement("xpath", "(//div[@class='_1fragem1y _1fragemlj']//p)[1]").getText()
					.replace("Confirmation ", "");
			System.out.println(ConfirmationNumber);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating user navigated to the summary page",
					"After clicking on the continue to payment it should navigate to the summary page",
					"User unable to navigate to the summary page", Common.getscreenShotPathforReport(
							"Failed to Navigate to the summary page after clicking on the continue payment"));
			Assert.fail();

		}
		return ConfirmationNumber;

	}

	public void admin_Sigin(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad(30);

//			Common.openNewTab();

			Common.oppenURL("https://www.shopify.com/logout?dest=default");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[text()='Log in']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(Dataset).get("UserName"));
			Thread.sleep(16000);
			Sync.waitElementPresent(30, "xpath", "//button[@type='submit']");
			Common.clickElement("xpath", "//button[@type='submit']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@id='account_password']", data.get(Dataset).get("Password"));
			Thread.sleep(9000);
			Sync.waitElementPresent(30, "xpath", "//button[@type='submit']");
			Common.clickElement("xpath", "//button[@type='submit']");
			Sync.waitPageLoad();
			Thread.sleep(13000);
//			Common.textBoxInput("xpath", "//input[@id='account_tfa_code']",)
			Common.clickElement("xpath", "//button[@type='submit']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation the the magneto Home page",
					"After clicking on theLogin button it should navigate to the home page",
					"User unable to navigate to the magento home page",
					Common.getscreenShotPathforReport("Failed to to navigate to the magento home page"));
			Assert.fail();
		}
	}

	public String search_order(String confirmationNumber) {
		// TODO Auto-generated method stub
		String Ordernumber = "";
		try {
			Common.switchToFirstTab();
			Sync.waitPageLoad();
			Thread.sleep(8000);
			System.out.println(Common.getCurrentURL());
			if (Common.getCurrentURL().equals("https://admin.shopify.com/store/curlsmith-usa-dev/orders")) {
				Common.clickElement("xpath", "//button[@aria-label='Search and filter orders']");
				Common.textBoxInput("xpath", "//input[@placeholder='Searching all orders']", confirmationNumber);
				Thread.sleep(6000);
				int size = Common.findElements("xpath", "//tr[contains(@class,'Polaris-IndexTable__TableRow')]").size();
				System.out.println(size);
				if (size == 1) {
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[@data-primary-link='true']");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					Common.scrollIntoView("xpath",
							"(//div[contains(@class,'_PrimaryMessage')]//p[contains(@class,'_Message')])[2]");
					String Number = Common
							.findElement("xpath",
									"(//div[contains(@class,'_PrimaryMessage')]//p[contains(@class,'_Message')])[2]")
							.getText().replace("was generated for this order.", "").replace("Confirmation ", "").trim();
					System.out.println(Number);
					Common.assertionCheckwithReport(Number.equals(confirmationNumber) || Number.contains("Order confirmation email was sent to"),
							"To validate the order number in the magneto",
							"user should able to see the order number in the admin",
							"User Successfully see the order number in the admin",
							"User Failed to see the order number in the admin");
//					Assert.assertEquals(Number, confirmationNumber);
					Common.scrollIntoView("xpath", "//h1[@class='Polaris-Header-Title']//span");
					Ordernumber = Common.findElement("xpath", "//h1[@class='Polaris-Header-Title']//span").getText();
				} else {
					Assert.fail();
				}
			} else {
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation the the order status page",
					"After clicking on theorder number it should navigate to the order status page",
					"User unable to navigate to the order status page",
					Common.getscreenShotPathforReport("Failed to to navigate to the order status page"));
			Assert.fail();
		}
		return Ordernumber;
	}

	public void prepareOrdersData(String fileName) {
		// TODO Auto-generated method stub
		try {

			File file = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/Curlsmith/" + fileName);
			XSSFWorkbook workbook;
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			if (!(file.exists())) {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("CurlsmithUS-O2C-E2E-Testing");
				CellStyle cs = workbook.createCellStyle();
				CellStyle ps = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.GOLD.getIndex());
				ps.setFillForegroundColor(IndexedColors.RED.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Curlsmith US O2C-E2E Test Execution Plan");
				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("S.No");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("Website");
				cell = row.createCell(2);
				cell.setCellStyle(cs);

				cell.setCellStyle(cs);
				cell.setCellValue("Test scenario Description");
				cell = row.createCell(3);

				cell.setCellStyle(cs);
				cell.setCellValue("SKU");
				cell = row.createCell(4);
				
				cell.setCellStyle(cs);
				cell.setCellValue("Order Confirmation Number");
				cell = row.createCell(5);

				cell.setCellStyle(cs);
				cell.setCellValue("Web Order Number");
				cell = row.createCell(6);
				
				cell.setCellStyle(cs);
				cell.setCellValue("Cutomer PO");
				cell = row.createCell(7);
				
				
				cell.setCellStyle(cs);
				cell.setCellValue("Shopify Admin Status");
				cell = row.createCell(8);

				cell.setCellStyle(cs);
				cell.setCellValue("Discount code");
				cell = row.createCell(9);

				rowcount = 2;
			}

			else {
				workbook = new XSSFWorkbook(new FileInputStream(file));
				sheet = workbook.getSheet("DrybarUS-O2C-E2E-Testing");
				rowcount = sheet.getLastRowNum() + 1;
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> orderverification(String orderNumber) {
		// TODO Auto-generated method stub
		HashMap<String, String> Orderstatus1 = new HashMap<String, String>();
		try {
			String Orderstatus = Common.findElement("xpath",
					"(//div[@class='Polaris-InlineStack']//span[@class='Polaris-Text--root Polaris-Text--bodySm'])[2]")
					.getText();
			System.out.println(Orderstatus);
			Orderstatus1.put("AdminOrderstatus", Orderstatus);
			StringBuilder concatenatedText = new StringBuilder();
			int size = Common.findElements("xpath",
					"//div[@class='_ProductDetails_pqnpf_4']//div//span[contains(@class,'Polaris-Text--root Polaris-Text--bodySm Polaris-Text--br')]")
					.size();
			for (int n = 1; n <= size; n++) {
				String sku = Common.findElement("xpath",
						"(//span[contains(@class,'Polaris-Text--root Polaris-Text--bodySm Polaris-Text--br')])[" + n
								+ "]")
						.getText().replace("SKU: ", "");
				concatenatedText.append(sku);
			}
			String finalsku = concatenatedText.toString();
			System.out.println(finalsku);
			Orderstatus1.put("Skus", finalsku);
			
			String CutomerPO= Common.findElement("xpath",
					"//h1[@class='Polaris-Header-Title']//span")
					.getText().replace("#USDEV", "");
			System.out.println(CutomerPO);
			Orderstatus1.put("CustomerPO", CutomerPO);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation the the order status page",
					"After clicking on theorder number it should navigate to the order status page",
					"User unable to navigate to the order status page",
					Common.getscreenShotPathforReport("Failed to to navigate to the order status page"));
			Assert.fail();
		}
		return Orderstatus1;
	}

	public void writeOrderNumber(String Description, String OrderIdNumber, String Skus,String ConfirmationNumber, String AdminOrderstatus,
			String CustomerPO,String Discountcode) throws FileNotFoundException, IOException {
		// String fileOut="";
		try {

			File file = new File(System.getProperty("user.dir")
					+ "/src/test/resources//TestData/Curlsmith/CurlsmithUS_E2E_orderDetails.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("CurlsmithUS-O2C-E2E-Testing");

			if ((workbook.getSheet("CurlsmithUS-O2C-E2E-Testing")) == null) {
				sheet = workbook.createSheet("CurlsmithUS-O2C-E2E-Testing");
				CellStyle cs = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("CurlsmithUS-O2C-E2E-Testing");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Web Order Number");
				rowcount = 2;

			}

			else {

				sheet = workbook.getSheet("CurlsmithUS-O2C-E2E-Testing");
				rowcount = sheet.getLastRowNum() + 1;
			}
			row = sheet.createRow(rowcount);
			cell = row.createCell(0);
			cell.setCellType(CellType.NUMERIC);
			int SNo = rowcount - 1;
			cell.setCellValue(SNo);
			cell = row.createCell(1);
			cell.setCellType(CellType.STRING);

			cell.setCellValue("Curlsmith");

			cell = row.createCell(2);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Description);

			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Skus);
			
			cell = row.createCell(4);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ConfirmationNumber);

			cell = row.createCell(5);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(OrderIdNumber);
			
			cell = row.createCell(6);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(AdminOrderstatus);
			
			cell = row.createCell(7);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(CustomerPO);

			cell = row.createCell(8);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Discountcode);

			System.out.println(OrderIdNumber);
			FileOutputStream fileOut = new FileOutputStream(file);

			workbook.write(fileOut);

			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Simple_Addtocart(String Dataset) {
		String products = data.get(Dataset).get("Products");

		try {
			Sync.waitElementPresent(30, "xpath", "//div[@data-product-handle='"+ products + "']");
			Common.clickElement("xpath", "//div[@data-product-handle='"+ products + "']");

			product_quantity(Dataset);
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//span[normalize-space()='Add to bag']//parent::button");
			Common.clickElement("xpath", "//span[normalize-space()='Add to bag']//parent::button");
			Sync.waitPageLoad();
			Thread.sleep(4000);

//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Bundle_Addtocart(String Dataset) {
		String products = data.get(Dataset).get("Products");

		try {
			Sync.waitElementPresent(30, "xpath", "//a[contains(@href,'" + products + "')]");
			Common.clickElement("xpath", "//a[contains(@href,'" + products + "')]");

			product_quantity(Dataset);
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//span[normalize-space()='Add to bag']//parent::button");
			Common.clickElement("xpath", "//span[normalize-space()='Add to bag']//parent::button");
			Sync.waitPageLoad();
			Thread.sleep(4000);

//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Register_user_Login(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[@class='site-nav__link site-nav__link--icon']//img[@loading='lazy']");
			Common.clickElement("xpath", "//a[@class='site-nav__link site-nav__link--icon']//img[@loading='lazy']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@id='email']");
			Common.textBoxInput("xpath", "//input[@id='email']", data.get(Dataset).get("UserName"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@type='submit']");
			Thread.sleep(20000);
//			 Common.textBoxInput("xpath", "//input[@placeholder='6-digit code']",
//			 data.get(Dataset).get(""));
			Common.clickElement("xpath", "//button[@type='submit']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.clickElement("xpath", "//img[@alt='Curlsmith USA Dev logo']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Home page",
					"System directs the user to the Homepage", " user unable navigates to the home page",
					"Failed to navigate to the homepage");
			Assert.fail();
		}

	}

	public void DFF_Billing_Shipping(String dataSet) {
		String address = data.get(dataSet).get("Street");

		try {

			int size = Common.findElements("xpath", "//h2[text()='Billing address']").size();
			if (size > 0) {
				Sync.waitElementPresent("xpath", "//label[text()='Use a different billing address']");
				Common.clickElement("xpath", "//label[text()='Use a different billing address']");
				Sync.waitElementPresent("xpath", "//input[@name='firstName' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='firstName' and @placeholder]",
						data.get(dataSet).get("FirstName"));
				Sync.waitElementPresent("xpath", "//input[@name='lastName' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='lastName' and @placeholder]",
						data.get(dataSet).get("LastName"));
				Sync.waitElementPresent("xpath", "//input[@name='address1' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='address1' and @placeholder]", address);
				Sync.waitElementPresent("xpath", "//input[@name='city' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='city' and @placeholder]", data.get(dataSet).get("City"));
				Sync.waitElementPresent("xpath", "//select[@id='Select3']");
				Common.dropdown("xpath", "//select[@id='Select3']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Region"));
				Sync.waitElementPresent("xpath", "//input[@name='postalCode' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='postalCode' and @placeholder]",
						data.get(dataSet).get("postcode"));
				Sync.waitPageLoad();
				Thread.sleep(2000);
				Common.clickElement("xpath", "//span[text()='Pay now']");
				String Return_Shipping = Common.getText("xpath", "//span[text()='Return to shipping']");
				System.out.println(Return_Shipping);

			} else {

				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the user enters billing Address",
					"user enters billing Address", " user unable user enters billing Address",
					"Failed to user enters billing Address");
			Assert.fail();
		}

	}

	public void FreeItem() {
		try {
			int size = Common.findElements("xpath", "//div[text()='Choose a Free Sample']").size();
			if (size > 0) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[@class='grid-item__meta']//button[@title='Add to bag']");
				Common.clickElement("xpath", "//div[@class='grid-item__meta']//button[@title='Add to bag']");
				System.out.println("Free Item Added");
			} else {
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Free Item Add to cart", "user add Free Item Add to cart",
					" user unable add Free Item Add to cart", "Failed to add Free Item Add to cart");
			Assert.fail();
		}

	}

	public void RegaddDeliveryAddress(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
		String address = data.get(dataSet).get("Street");
		String firstname = data.get(dataSet).get("FirstName");
		System.out.println(firstname);

		try {
			Sync.waitElementClickable(30, "xpath", "//select[@id='Select0']");
			Common.clickElement("xpath", "//select[@id='Select0']");

			int size = Common.findElements(By.xpath("//option[text()='Use a new address']")).size();
			if (size > 0) {

				Common.dropdown("xpath", "//select[@id='Select0']", Common.SelectBy.TEXT, "Use a new address");

				Sync.waitElementPresent("xpath", "//input[@name='firstName' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='firstName' and @placeholder]",
						data.get(dataSet).get("FirstName"));
				Sync.waitElementPresent("xpath", "//input[@name='lastName' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='lastName' and @placeholder]",
						data.get(dataSet).get("LastName"));
				Sync.waitElementPresent("xpath", "//input[@name='address1' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='address1' and @placeholder]", address);
				Sync.waitElementPresent("xpath", "//input[@name='city' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='city' and @placeholder]", data.get(dataSet).get("City"));
				Sync.waitElementPresent("xpath", "//select[@id='Select2']");
				Common.dropdown("xpath", "//select[@id='Select2']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Region"));
				Sync.waitElementPresent("xpath", "//input[@name='postalCode' and @placeholder]");
				Common.textBoxInput("xpath", "//input[@name='postalCode' and @placeholder]",
						data.get(dataSet).get("postcode"));
				Sync.waitPageLoad();
				Thread.sleep(3000);

			} else {

				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address", Common.getscreenShotPathforReport("shipping address faield"));

			Assert.fail();

		}

	}

	public void delete_FreeItem() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			for (int i = 0; i <= 10; i++) {
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//span[@class='cart__price']//span[@class='visually-hidden']");
				String price = webelementslist.get(i).getText().replace("$", "");
				if (price == "0.00") {
					Common.clickElement("xpath", "//a[@class='text-link js-remove-cart-item']");

				}

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating removing the free gift product",
					"user should delete the free gift product", " user unable to delete the free gift product",
					"Failed to delete the free gift product");
			Assert.fail();
		}

	}

	public void Validating_FreeShipping() {
		String Freeshipping = "";
		try {

			String Price = Common.findElement("xpath", "//strong[@translate='no' and contains(@class, '_19gi7yt0')]")
					.getText();

			double price = Double.parseDouble(Price.replace("$", "").trim());

			if (price > 35) {

				System.out.println("The price is greater than 35.");
				Freeshipping = Common.findElement("xpath", "//span[text()='Free']//parent::div").getText();
				System.out.println(Freeshipping + "Shipping");
			} else {

				System.out.println("The price is not greater than 35.");
				Assert.fail();
			}

			Common.assertionCheckwithReport(Freeshipping.equals("FREE"),
					"validating the Free shipping text is displayed the order total is above 35$",
					"Free shipping text Should be displayed if the order total is above 35$",
					"Successfully Free shipping text is displayed after order total is above 35$",
					"Failed to display Free shipping text if order total is above 35$");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Free shipping is selected if Product Price above 35$",
					"Free shipping is selected if Product Price above 35$",
					" Free shipping is unable to selected and display", "Failed to select Free shpping ");
			Assert.fail();
		}

	}

	public void discount_code(String discountcode) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
			String subtotal=Common.findElement("xpath", "(//div[@class='_1qy6ue6b']//span)[1]").getText().replace("$", "");
			System.out.println(subtotal);
			Float subtotalvalue = Float.parseFloat(subtotal);
			Sync.waitElementPresent("xpath", "//input[@placeholder='Discount code']");
			Common.textBoxInput("xpath", "//input[@placeholder='Discount code']", discountcode);
			Sync.waitElementPresent("xpath", "//button[@aria-label='Apply Discount Code']");
			Common.clickElement("xpath", "//button[@aria-label='Apply Discount Code']");
			Thread.sleep(4000);
			float discount =(subtotalvalue * 20 / 100);
			String discountvalue = String.valueOf(discount);
			Float value = Float.parseFloat(discountvalue);
			String s=String.valueOf(value); 
			String websitediscount=Common.findElement("xpath", "//strong[contains(@class,'_19gi7yt0 _19gi7ytw _19gi7ytv _1fragemnu _19gi7yt2 ')]").getText().replace("$", "");
			System.out.println(websitediscount);
			System.out.println(s);
			Common.assertionCheckwithReport(websitediscount.contains(s),
					"verifying the discount on the webiste",
					"user should able to see the discount on the website",
					"user successfully able to apply the discount", "Failed to apply the discount for the user");
		
		}
		catch(Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the discount on the webiste",
					"user should able to see the discount on the website",
					"unable to add the discount on the website", "Failed to apply the discount for the user");
			Assert.fail();
		}
		
	}

	public void online_Store() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Sales channels')]");
			Common.clickElement("xpath", "//span[contains(text(),'Sales channels')]");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Online Store')]");
			Common.clickElement("xpath", "//div[contains(text(),'Online Store')]");
			Thread.sleep(4000);
			Common.switchToSecondTab();
			verify_Homepage();
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Home page",
					"System directs the user to the Homepage", " user unable navigates to the home page",
					"Failed to navigate to the homepage");
			Assert.fail();
		}
		
	}

}
