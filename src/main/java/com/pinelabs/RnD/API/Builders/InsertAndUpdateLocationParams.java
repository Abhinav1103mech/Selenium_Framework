package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.InsertAndUpdateLocationParamsRequest;
import com.pinelabs.RnD.API.PojoFiles.InsertAndUpdateLocationParamsResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;

public class InsertAndUpdateLocationParams extends BaseUtilsAPI {
    static InsertAndUpdateLocationParams insertAndUpdateLocationParams;
    InsertAndUpdateLocationParamsRequest insertAndUpdateLocationParamsRequest;
    InsertAndUpdateLocationParamsResponse insertAndUpdateLocationParamsResponse;
    public static String defaultRequest = "{\"hardwareid\":\"0821397407\",\"latitude\":\"26.630298\",\"longitude\":\"35.630278\"}";

    String request;

    private InsertAndUpdateLocationParams(String request) {
        this.request = request;
        insertAndUpdateLocationParamsRequest = stringToJavaObject(this.request, InsertAndUpdateLocationParamsRequest.class);
    }

    public static InsertAndUpdateLocationParams getInstance(String request) {
        if (insertAndUpdateLocationParams == null)
            insertAndUpdateLocationParams = new InsertAndUpdateLocationParams(request);
        return insertAndUpdateLocationParams;
    }


    public void createAndExecute(String token) {
        request = javaObjectToString(insertAndUpdateLocationParamsRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(request, URI.insertAndUpdateLocationParams, URI.baseURL, token);
        insertAndUpdateLocationParamsResponse = stringToJavaObject(response.asString(), InsertAndUpdateLocationParamsResponse.class);
    }

    public InsertAndUpdateLocationParamsResponse getResponsePojo() {
        return insertAndUpdateLocationParamsResponse;
    }

    public InsertAndUpdateLocationParamsRequest getRequestPojo() {
        return insertAndUpdateLocationParamsRequest;
    }
}
