package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.UpiAmazonPayRequest;
import com.pinelabs.RnD.API.PojoFiles.UpiAmazonPayResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;


public class UpiAmazonPay extends BaseUtilsAPI {
    static UpiAmazonPay upiAmazonPay;
    UpiAmazonPayRequest upiAmazonPayRequest;
    UpiAmazonPayResponse upiAmazonPayResponse;
    public static String defaultrequest = "{\"merchantID\":\"A2GI92ROAM509H\",\"orderTotalAmount\":\"1.0\",\"sellerOrderId\": \"10050\"}";
    String request;
    private UpiAmazonPay(String request) {
        this.request=request;
        upiAmazonPayRequest = stringToJavaObject(this.request, UpiAmazonPayRequest.class);
    }

    public static UpiAmazonPay getInstance(String request) {
        if (upiAmazonPay == null)
            upiAmazonPay = new UpiAmazonPay(request);
        return upiAmazonPay;
    }


    public UpiAmazonPayResponse createAndExecute() {
        request = javaObjectToString(upiAmazonPayRequest);
        setMethod(EnumsRepo.methodName.POST);

        Response response = execute(request, URI.upiAmazonPay, URI.simulatorBaseURL);
        upiAmazonPayResponse = stringToJavaObject(response.asString(), UpiAmazonPayResponse.class);
        return upiAmazonPayResponse;
    }

    public UpiAmazonPayResponse getResponse() {
        return upiAmazonPayResponse;
    }

    public UpiAmazonPayRequest getRequest() {
        return upiAmazonPayRequest;
    }
}

