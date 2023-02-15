import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
        glue={"stepDefination"},
        plugin = {"pretty", "html:target/HTMLReport.html"})
public class testRunner {
}
