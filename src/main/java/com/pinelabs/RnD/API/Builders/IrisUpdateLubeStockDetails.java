package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.IrisUpdateLubeStockDetailsRequest;
import com.pinelabs.RnD.API.PojoFiles.IrisUpdateLubeStockDetailsResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;

public class IrisUpdateLubeStockDetails extends BaseUtilsAPI {
    static IrisUpdateLubeStockDetails irisUpdateLubeStockDetails;
    IrisUpdateLubeStockDetailsRequest irisUpdateLubeStockDetailsRequest;
    IrisUpdateLubeStockDetailsResponse irisUpdateLubeStockDetailsResponse;
    public static String defaultRequest = "{\"HardwareId\":\"0821397407\",\"LubeCode\":\"OIL1234567\",\"Quantity\":100,\"TotalQuantity\":200,\"StockUpdationDateTime\":\"2021-04-22 20:35:41\",\"ROid\":\"180664\"}";
    String request;

    private IrisUpdateLubeStockDetails(String request) {
        this.request = request;
        irisUpdateLubeStockDetailsRequest = stringToJavaObject(this.request, IrisUpdateLubeStockDetailsRequest.class);
    }

    public static IrisUpdateLubeStockDetails getInstance(String request) {
        if (irisUpdateLubeStockDetails == null)
            irisUpdateLubeStockDetails = new IrisUpdateLubeStockDetails(request);
        return irisUpdateLubeStockDetails;
    }


    public IrisUpdateLubeStockDetailsResponse createAndExecute(String token) {
        request = javaObjectToString(irisUpdateLubeStockDetailsRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(request, URI.irisUpdateLubeStockDetails, URI.baseURL,token);
        irisUpdateLubeStockDetailsResponse = stringToJavaObject(response.asString(), IrisUpdateLubeStockDetailsResponse.class);
        return irisUpdateLubeStockDetailsResponse;
    }

    public IrisUpdateLubeStockDetailsResponse getResponse() {
        return irisUpdateLubeStockDetailsResponse;
    }

    public IrisUpdateLubeStockDetailsRequest getRequest() {
        return irisUpdateLubeStockDetailsRequest;
    }
}
