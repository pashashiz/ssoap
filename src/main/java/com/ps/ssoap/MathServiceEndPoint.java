package com.ps.ssoap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MathServiceEndPoint {

    @Autowired
    MathService mathService;

    @PayloadRoot(localPart="MathServiceRequest", namespace = "http://ssoap.ps.com/MathService")
    public @ResponsePayload MathServiceResponse handleMathServiceRequest(@RequestPayload MathServiceRequest mathServiceRequest) {
        boolean isEven = getMathService().isEven(mathServiceRequest.getNumber());
        MathServiceResponse r = new MathServiceResponse();
        r.setIsEven(isEven);
        r.setNumber(mathServiceRequest.getNumber());
        return r;
    }

    public MathService getMathService() {
        return mathService;
    }

}
