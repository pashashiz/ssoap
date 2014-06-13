import com.eviware.soapui.tools.SoapUITestCaseRunner;
import org.junit.Test;

public class SoapUITest {

    @Test
    public void testAll() throws Exception {
        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
        runner.setProjectFile("src/test/resources/MathService-soapui-project.xml");
        runner.run();
    }

}
