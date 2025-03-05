package TestExecute.API.Osprey_EMEA;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test_DGLD_API_001_Guestuser_checkout_with_One_Line_Item_QTY_Two_with_CC {
    private String apiKey;
    private String orderNumber;
    public Integer itemId;
    public String sku;
    public Integer QTYOrder;
    public String customerEmail;
    public String increment_id;
    public String MagentoOrder_ID;
    public String deliveryNumber;
  
    @Test(priority = 1)
    public void generateApiKey() {
        RestAssured.baseURI = "https://emea-preprod.hele.digital/rest/V1/integration/admin/token";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String requestBody = "{\"username\":\"qa-api\", \"password\":\"R2K261GbPSaI\"}";
        request.body(requestBody);

        Response response = request.post();

        Assert.assertEquals(response.getStatusCode(), 200, "API Key generation failed");

        apiKey = response.getBody().asString().replaceAll("^\"|\"$", "");
        System.out.println("API Key: " + apiKey);
    }

    @Test(priority = 2, dependsOnMethods = "generateApiKey")
    public void getOrderCopy() {
        
    	MagentoOrder_ID="901535";
    	RestAssured.baseURI = "https://emea-preprod.hele.digital/rest/ospreyuken/V1/orders/"+MagentoOrder_ID+"/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);

        Response response = request.get();

        Assert.assertEquals(response.getStatusCode(), 200, "Get Order Copy failed");
        String jsonResponse = response.getBody().asString();
        String formattedJson = JsonFormatter.formatJson(jsonResponse);

//      System.out.println("Get Order Copy Response: " + formattedJson);
        orderNumber = response.path("increment_id");
        System.out.println("OderNumber: " + orderNumber);
        customerEmail = response.path("customer_email");
        System.out.println("Customer Email: " + customerEmail);
        List<Map<String, Object>> items = response.jsonPath().getList("items");

        if (items != null && !items.isEmpty()) {
            Map<String, Object> firstItem = items.get(0); // Get the first item
            itemId = (Integer) firstItem.get("item_id");
            System.out.println("item_id: " + itemId);
             QTYOrder = (Integer) firstItem.get("qty_ordered");
            System.out.println("QTY_Ordered: " + QTYOrder);
            sku = (String) firstItem.get("sku");
            System.out.println("SKU: " + sku);
        } else {
            System.out.println("No items found in the response.");
            Assert.fail("No items found in the order copy");
        }
    }


    @Test(priority = 3, dependsOnMethods = {"generateApiKey", "getOrderCopy"})
    public void shipOrder() {
        RestAssured.baseURI = "https://emea-preprod.hele.digital/rest/all/V1/order/"+MagentoOrder_ID+"/ship";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);

        // Assuming itemId, deliveryNumber, and trackingNumber are available
        String deliveryNumberBase = "02102"; 
        String deliveryNumberSuffix = generateRandomNumber(4); 
        deliveryNumber = deliveryNumberBase + deliveryNumberSuffix;
        String trackingNumberBase = "1025433";
        String trackingNumberSuffix = generateRandomNumber(6); 
        String trackingNumber = trackingNumberBase + trackingNumberSuffix;

        String requestBody = "{\n" +
                "    \"notify\": \"false\",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"order_item_id\": " + itemId + ",\n" +
                "            \"qty\": 1.0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"tracks\": [\n" +
                "        {\n" +
                "            \"track_number\": \"" + trackingNumber + "\",\n" +
                "            \"title\": \"FedEx\",\n" +
                "            \"carrier_code\": \"fedex\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"arguments\": {\n" +
                "        \"extension_attributes\": {\n" +
                "            \"delivery_number\": \"" + deliveryNumber + "\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        request.body(requestBody);

        Response response = request.post();

        Assert.assertEquals(response.getStatusCode(), 200, "Ship order failed");
        System.out.println("Ship Order Response: " + response.getBody().asString());
        System.out.println("Request Body: " + requestBody); // print request body for debugging.
    }
    
    @Test(priority = 4, dependsOnMethods = {"generateApiKey", "getOrderCopy", "shipOrder"})
    public void invoice() {
        RestAssured.baseURI = "https://emea-preprod.hele.digital/rest/all/V1/order/"+MagentoOrder_ID+"/invoice";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);
        
        String requestBody = "{\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"order_item_id\": " + itemId + ",\n" +
                "            \"qty\": "+QTYOrder+".0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"notify\": false,\n" +
                "    \"appendComment\": false,\n" +
                "    \"capture\": true,\n" +
                "    \"arguments\": {\n" +
                "        \"extension_attributes\": {\n" +
                "            \"delivery_number\": \"" + deliveryNumber + "\",\n" +
                "            \"oracle_customer_number\": \"\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        request.body(requestBody);

        Response response = request.post();

        Assert.assertEquals(response.getStatusCode(), 200, "Ship order failed");
        System.out.println("Ship Order Response: " + response.getBody().asString());
        System.out.println("Request Body: " + requestBody); // print request body for debugging.
    }
    
    
  /*  @Test(priority = 3, dependsOnMethods = {"generateApiKey", "getOrderCopy"})
    public void shipOrder_And_InvoiceOrder() {
        RestAssured.baseURI = "https://webhooks.eu.workato.com/webhooks/rest/22a30675-6e14-4d17-b1ff-50a3d6535479/new_shipment";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);

        String trackingNumberBase = "279492"; 
        String trackingNumberSuffix = generateRandomNumber(6); 
        String trackingNumber = trackingNumberBase + trackingNumberSuffix;

        String requestBody = "{\n" +
                "    \"attribute1\": \"\",\n" +
                "    \"attribute2\": \"\",\n" +
                "    \"attribute3\": \"\",\n" +
                "    \"attribute4\": \"\",\n" +
                "    \"attribute5\": \"\",\n" +
                "    \"delivery_number\": \"\",\n" +
                "    \"magento_customer_number\": \"\",\n" +
                "    \"magento_order_number\": \""+orderNumber+"  \",\n" +
                "    \"oracle_customer_number\": \"\",\n" +
                "    \"oracle_order_number\": \"\",\n" +
                "    \"shipments\": [\n" +
                "        {\n" +
                "            \"carrier\": \"GLS\",\n" +
                "            \"items\": [\n" +
                "                {\n" +
                "                    \"attribute1\": \"\",\n" +
                "                    \"attribute2\": \"\",\n" +
                "                    \"attribute3\": \"\",\n" +
                "                    \"attribute4\": \"\",\n" +
                "                    \"attribute5\": \"\",\n" +
                "                    \"magento_order_item_id\":"+itemId+",\n" +
                "                    \"quantity\": "+QTYOrder+",\n" +
                "                    \"quantity_cancelled\": \"\",\n" +
                "                    \"recipe_id\": \"\",\n" +
                "                    \"sku_ordered\": \"" +sku+"\",\n" +
                "                    \"sku_shipped\": \"" +sku+"\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"tracking_number\": \""+trackingNumber +"\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"website_id\": \"OSPREYUK\"\n" +
                "}";
        request.body(requestBody);

        Response response = request.post();

        Assert.assertEquals(response.getStatusCode(), 200, "Ship order failed");
        System.out.println("Ship Order Response: " + response.getBody().asString());
        System.out.println("Generated Tracking Number: " + trackingNumber);
    
    }
*/
	
	
	///***Create RMA***///
    	@Test(priority = 5, dependsOnMethods = {"generateApiKey", "getOrderCopy", "shipOrder","invoice"})
 public void createRma() throws InterruptedException {
    		Thread.sleep(30000);
	System.out.println(apiKey);
     RestAssured.baseURI = "https://emea-preprod.hele.digital/rest/all/V1/returns/create-rma";

     RequestSpecification request = RestAssured.given();
     request.header("Content-Type", "application/json"); 
     request.header("Authorization", "Bearer " + apiKey);
     
     String Increment_Base = "17118qa";
     String incrementSuffix = generateRandomNumber(5); 
     increment_id = Increment_Base + incrementSuffix;
     
     String requestBody = "{\n" +
    	        "    \"increment_id\": \""+increment_id+"\",\n" +
    	        "    \"order_increment_id\": \""+orderNumber+"\",\n" +
    	        "    \"external_rma_id\": \""+increment_id+"\",\n" +
    	        "    \"customer_custom_email\": \"" + customerEmail + "\",\n" + // Use customerEmail variable
    	        "    \"items\": [\n" +
    	        "        {\n" +
    	        "            \"order_item_id\": "+itemId+",\n" +
    	        "            \"quantity_to_return\": "+QTYOrder+",\n" +
    	        "            \"entered_custom_attributes\": [\n" +
    	        "                {\n" +
    	        "                    \"attribute_code\": \"reason\",\n" +
    	        "                    \"value\": \"2\"\n" +
    	        "                },\n" +
    	        "                {\n" +
    	        "                    \"attribute_code\": \"compensationMethod\",\n" +
    	        "                    \"value\": \"return-for-refund\"\n" +
    	        "                }\n" +
    	        "            ],\n" +
    	        "            \"selected_custom_attributes\": []\n" +
    	        "        }\n" +
    	        "    ]\n" +
    	        "}";
    

     request.body(requestBody);
     Response response = request.post();
     String jsonResponse = response.getBody().asString();

     JSONObject jsonObject = new JSONObject(jsonResponse);
     //System.out.println(jsonObject);
     Assert.assertEquals(response.getStatusCode(), 200, "Create RMA failed");

     // Validations
     Assert.assertEquals(jsonObject.getString("customer_custom_email"), customerEmail, "Customer email mismatch");
     Assert.assertEquals(jsonObject.getString("increment_id"), increment_id, "Increment ID mismatch");
     Assert.assertEquals(jsonObject.getString("order_increment_id"), orderNumber, "Order increment ID mismatch");
     Assert.assertEquals(jsonObject.getString("status"), "authorized", "Status mismatch");
//     Assert.assertEquals(jsonObject.getJSONArray("items").getJSONObject(0).getInt("order_item_id"), itemId, "Order item ID mismatch");
     System.out.println("Create RMA Response: " + jsonObject.toString(4));
 }
     

	
	
	///****Post Credit Memo****///
	@Test(priority = 6, dependsOnMethods = {"generateApiKey", "getOrderCopy", "shipOrder","invoice","createRma"})
 public void postCreditMemo() {
     RestAssured.baseURI = "https://emea-preprod.hele.digital/rest/V1/returns/"+increment_id+"/refund";

     RequestSpecification request = RestAssured.given();
     request.header("Content-Type", "application/json"); // Or "text/plain" as in your Postman
     request.header("Authorization", "Bearer " + apiKey);// Use the provided token

     String requestBody = "{\n" +
             "    \"type\": \"approved_return\",\n" +
             "    \"order_item_ids\": [\n" +
             "        {\n" +
             "            \"qty\": \""+QTYOrder+".0\",\n" +
             "            \"order_item_id\": \""+itemId+"\"\n" +
             "        }\n" +
             "    ],\n" +
             "    \"refund_shipping\": false\n" +
             "}";

     request.body(requestBody);
     Response response = request.post();
     String jsonResponse = response.getBody().asString();

     JSONObject jsonObject = new JSONObject(jsonResponse);
     Assert.assertEquals(response.getStatusCode(), 200, "Post Credit Memo failed");

     // Validate credit_memo_id
     JSONArray successArray = jsonObject.getJSONArray("success");
     if (successArray.length() > 0) {
         JSONObject successObject = successArray.getJSONObject(0);
         if (successObject.has("credit_memo_id")) {
             int creditMemoId = successObject.getInt("credit_memo_id");
             System.out.println("Credit Memo ID: " + creditMemoId);
            
         } else {
             Assert.fail("credit_memo_id is missing in the response");
         }
     } else {
         Assert.fail("success array is empty");
     }

     System.out.println("Post Credit Memo Response: " + jsonObject.toString(4));
 }
     
	//***Update RMA***///
//	@Test(priority = 6, dependsOnMethods = "generateApiKey")
// public void updateRma() {
//     RestAssured.baseURI = "https://na-preprod.hele.digital/rest/all/V1/returns/update-rma/15110ea75021";
//
//     RequestSpecification request = RestAssured.given();
//     request.header("Content-Type", "application/json"); // Or "text/plain" as in Postman
//     request.header("Authorization", "Bearer" + apiKey); // Use the provided token
//
//     String requestBody = "{\r\n" + // ... (your request body) ...
//             "}";
//
//     request.body(requestBody);
//
//     Response response = request.put();
//
//     Assert.assertEquals(response.getStatusCode(), 200, "Update RMA failed");
//     System.out.println("Update RMA Response: " + response.getBody().asString());
// }
	
private String generateRandomNumber(int length) {
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
        sb.append(random.nextInt(10)); 
    }
    return sb.toString();
}

public static class JsonFormatter {
    public static String formatJson(String jsonString) {
        try {
            JSONTokener tokener = new JSONTokener(new StringReader(jsonString));
            JSONObject jsonObject = new JSONObject(tokener);
            return jsonObject.toString(4);
        } catch (org.json.JSONException e) {
            return "Error parsing JSON: " + jsonString;
        }
    }
}
	}
