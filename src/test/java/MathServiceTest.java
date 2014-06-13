import javax.xml.transform.Source;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.xml.transform.StringSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.ws.test.server.MockWebServiceClient;
import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-ws-servlet.xml")
public class MathServiceTest {

    @Autowired
    private ApplicationContext applicationContext;
    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @After
    public void tearDown() {
        mockClient = null;
    }

    @Test
    public void testService() {
        Source requestPayload = new StringSource(
                "<MathServiceRequest xmlns='http://ssoap.ps.com/MathService'>" +
                        "<Number>2</Number>" +
                        "</MathServiceRequest>");
        Source responsePayload = new StringSource(
                "<MathServiceResponse xmlns='http://ssoap.ps.com/MathService'>" +
                        "<Number>2</Number>" +
                        "<isEven>true</isEven>" +
                        "</MathServiceResponse>");
        mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
    }

}
