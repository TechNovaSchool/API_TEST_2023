package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:target/cucumber-report.html"},
        features = "src/test/resources",
        glue = "step_def",
        tags = "@api",
        dryRun = false
)

public class CukesRunner {

}

