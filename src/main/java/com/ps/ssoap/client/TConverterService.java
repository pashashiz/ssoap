package com.ps.ssoap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class TConverterService implements Runnable {

    private static final String TEST_MESSAGE =
            "<CelsiusToFahrenheit xmlns='http://www.w3schools.com/webservices/'>" +
                "<Celsius>22</Celsius>" +
            "</CelsiusToFahrenheit>";

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public void setDefaultUri(String defaultUri) {
        webServiceTemplate.setDefaultUri(defaultUri);
    }

    public void simpleSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(TEST_MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
    }

    public void marshalSendAndReceive() {
        CelsiusToFahrenheit request = new CelsiusToFahrenheit();
        request.setCelsius("22");
        webServiceTemplate.marshalSendAndReceive(request);
    }

    @Override
    public void run() {
        // simpleSendAndReceive();
        marshalSendAndReceive();
    }
}
