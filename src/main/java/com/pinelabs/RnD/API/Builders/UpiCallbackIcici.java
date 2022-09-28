package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.UpiCallbackIciciRequest;
import com.pinelabs.RnD.API.PojoFiles.UpiCallbackIciciResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;


public class UpiCallbackIcici extends BaseUtilsAPI {
    static UpiCallbackIcici upiCallbackIcici;
    UpiCallbackIciciRequest upiCallbackIciciRequest;
    UpiCallbackIciciResponse upiCallbackIciciResponse;
    public static String defaultrequest = "{\"merchantId\":\"131122\",\"subMerchantId\":\"131122\",\"terminalId\":\"null\",\"merchantTranId\":\"5652000007\",\"PayerName\":\"VISHAL RATHORE\",\"PayerMobile\":\"0\",\"PayerVA\":\"vishal.rathore8@okhdfcbank\",\"PayerAmount\":\"1.0\",\"TxnStatus\":\"SUCCESS\"}";
    String request;
    private UpiCallbackIcici(String request) {
        this.request=request;
        upiCallbackIciciRequest = stringToJavaObject(this.request, UpiCallbackIciciRequest.class);


    }

    public static UpiCallbackIcici getInstance(String request) {
        if (upiCallbackIcici == null)
            upiCallbackIcici = new UpiCallbackIcici(request);
        return upiCallbackIcici;
    }


    public UpiCallbackIciciResponse createAndExecute() {
        request = javaObjectToString(upiCallbackIciciRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(request, URI.upiCallback, URI.simulatorBaseURL);
        upiCallbackIciciResponse = stringToJavaObject(response.asString(), UpiCallbackIciciResponse.class);
        return upiCallbackIciciResponse;
    }

    public UpiCallbackIciciResponse getResponse() {
        return upiCallbackIciciResponse;
    }

    public UpiCallbackIciciRequest getRequest() {
        return upiCallbackIciciRequest;
    }
}

