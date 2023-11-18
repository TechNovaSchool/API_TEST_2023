package api.tests;

import Util.APIUtil;
import Util.Config;
import org.testng.annotations.Test;

public class AirtableUtilTest {

    @Test
    public void getMethod(){
        String path = "/Table%201";
        String tableID = Config.getProperty("tableID");
        APIUtil.callGET(path,tableID);
    }

    @Test
    public void postMethod() {

    }

}
