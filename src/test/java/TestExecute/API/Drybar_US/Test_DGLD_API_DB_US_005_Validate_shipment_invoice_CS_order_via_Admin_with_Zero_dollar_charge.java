package TestExecute.API.Drybar_US;

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

public class Test_DGLD_API_DB_US_005_CS_order_via_Admin_with_Zero_dollar_charge {
    private String apiKey;
    private String orderNumber;
    public Integer itemId;
    public String sku;
    public Integer QTYOrder;
    public String customerEmail;
    public String increment_id;
    public String deliveryNumber;
    public String MagentoOrder_ID;
 
    @Test(priority = 1)
    public void generateApiKey() {
        RestAssured.baseURI = "https://na-preprod.hele.digital/rest/V1/integration/admin/token";

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
        
    	MagentoOrder_ID="12821694";
    	RestAssured.baseURI = "https://na-preprod.hele.digital/rest/all/V1/orders/"+MagentoOrder_ID+"/";
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
        RestAssured.baseURI = "https://na-preprod.hele.digital/rest/drybar/V1/order/" + MagentoOrder_ID + "/ship";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);

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
                "            \"qty\":"+QTYOrder+".0\n" +
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
        System.out.println("Request Body: " + requestBody);
    }

    @Test(priority = 4, dependsOnMethods = {"generateApiKey", "getOrderCopy", "shipOrder"})
    public void invoice() {
        RestAssured.baseURI = "https://na-preprod.hele.digital/rest/drybar/V1/order/" + MagentoOrder_ID + "/invoice";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);

        String requestBody = "{\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"order_item_id\": " + itemId + ",\n" +
                "            \"qty\": " + QTYOrder + ".0\n" +
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

        Assert.assertEquals(response.getStatusCode(), 200, "Invoice creation failed");
        System.out.println("Invoice Response: " + response.getBody().asString());
        System.out.println("Request Body: " + requestBody);
    }

	

	
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
