package bestbuy.serinity.wk18.crudtest;

import bestbuy.serinity.wk18.steps.ProductSteps;
import bestbuy.serinity.wk18.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {
    static int id;
    String name = "Ice factory";
    String type = "ICE CUBES";
    int price = 10;
    int shipping = 2;
    String upc = "abc";
    String description = "ICE CUBES";
    String manufacturer = "HV";
    String model = "SQUARE";
    String url = "www.hvice.com";
    String image = "hvice.jpg";
    @Steps
    ProductSteps productsSteps;

    @Title("Creating a new product.")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Getting single product.")
    @Test
    public void test002() {
        ValidatableResponse response = productsSteps.getProduct(id);
        response.log().all().statusCode(200);
    }

    @Title("Updating the product.")
    @Test
    public void test003() {
        name = "Bowe Dry Cleaning Machine-Updated";
        ValidatableResponse response = productsSteps.updateProduct(id, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the product and verifying the deletion.")
    @Test
    public void test004() {
        productsSteps.deleteProduct(id).statusCode(200);
        productsSteps.getProduct(id).statusCode(404);
    }

}
