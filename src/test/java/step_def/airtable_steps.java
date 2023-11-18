package step_def;

import Util.APIUtil;
import Util.Config;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class airtable_steps {
    String path = "";
    String tableID = "";

    @When("a user calls a GET endpoint")
    public void a_user_calls_a_get_endpoint() {
        path = "/Table%201";
        tableID = Config.getProperty("tableID");
        APIUtil.callGET(path,tableID);

    }
    @Then("user will receive status {string}")
    public void user_will_receive_status(String actualStatus) {
        Assert.assertEquals(actualStatus, "200");
    }

}
