package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.IrisGetClaimFuelDetailsRequest;
import com.pinelabs.RnD.API.PojoFiles.IrisGetClaimFuelDetailsResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;

public class IrisGetClaimFuelDetails extends BaseUtilsAPI {
    static IrisGetClaimFuelDetails irisGetClaimFuelDetails;
    IrisGetClaimFuelDetailsRequest irisGetClaimFuelDetailsRequest;
    IrisGetClaimFuelDetailsResponse irisGetClaimFuelDetailsResponse;
    public static String defaultRequest = "{\"HardwareId\":\"0821397407\",\"PumpNum\":1,\"NozNum\":1,\"QrStr\":\"IRIS://Amt=15090&TxnId=242&VehNo=AB12DE45677&DT=23042021124649&RoId=333000&ProdType=Petrol\",\"ClmTranDt\":\"23042021124700\",\"ROid\":333000}";
    String request;

    private IrisGetClaimFuelDetails(String request) {
        this.request = request;
        irisGetClaimFuelDetailsRequest = stringToJavaObject(this.request, IrisGetClaimFuelDetailsRequest.class);
    }

    public static IrisGetClaimFuelDetails getInstance(String request) {
        if (irisGetClaimFuelDetails == null)
            irisGetClaimFuelDetails = new IrisGetClaimFuelDetails(request);
        return irisGetClaimFuelDetails;
    }


    public void createAndExecute(String token) {
        request = javaObjectToString(irisGetClaimFuelDetailsRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(request, URI.irisGetClaimFuelDetails, URI.baseURL, token);
        irisGetClaimFuelDetailsResponse = stringToJavaObject(response.asString(), IrisGetClaimFuelDetailsResponse.class);

    }

    public IrisGetClaimFuelDetailsResponse getResponsePojo() {
        return irisGetClaimFuelDetailsResponse;
    }

    public IrisGetClaimFuelDetailsRequest getRequestPojo() {
        return irisGetClaimFuelDetailsRequest;
    }
}
