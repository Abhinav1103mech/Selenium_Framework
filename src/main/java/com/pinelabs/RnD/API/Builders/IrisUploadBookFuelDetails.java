package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.IrisUploadBookFuelDetailsRequest;
import com.pinelabs.RnD.API.PojoFiles.IrisUploadBookFuelDetailsResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;

public class IrisUploadBookFuelDetails extends BaseUtilsAPI {
    static IrisUploadBookFuelDetails irisUploadBookFuelDetails;
    IrisUploadBookFuelDetailsRequest irisUploadBookFuelDetailsRequest;
    IrisUploadBookFuelDetailsResponse irisUploadBookFuelDetailsResponse;
    public static String defaultRequest = "{\"HardwareId\":\"0821397407\",\"PumpNum\":1,\"NozNum\":1,\"QrStr\":\"IRIS://Amt=15090&TxnId=242&VehNo=AB12DE45677&DT=23042021124649&RoId=333000&ProdType=Petrol\",\"ClmTranDt\":\"23042021124700\",\"ROid\":333000}";
    String request;

    private IrisUploadBookFuelDetails(String request) {
        this.request = request;
        irisUploadBookFuelDetailsRequest = stringToJavaObject(this.request, IrisUploadBookFuelDetailsRequest.class);
    }

    public static IrisUploadBookFuelDetails getInstance(String request) {
        if (irisUploadBookFuelDetails == null)
            irisUploadBookFuelDetails = new IrisUploadBookFuelDetails(request);
        return irisUploadBookFuelDetails;
    }

    public void createAndExecute(String token) {
        request = javaObjectToString(irisUploadBookFuelDetailsRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(request, URI.irisUploadBookFuelDetails, URI.baseURL, token);
        irisUploadBookFuelDetailsResponse = stringToJavaObject(response.asString(), IrisUploadBookFuelDetailsResponse.class);

    }

    public IrisUploadBookFuelDetailsResponse getResponsePojo() {
        return irisUploadBookFuelDetailsResponse;
    }

    public IrisUploadBookFuelDetailsRequest getRequestPojo() {
        return irisUploadBookFuelDetailsRequest;
    }
}
