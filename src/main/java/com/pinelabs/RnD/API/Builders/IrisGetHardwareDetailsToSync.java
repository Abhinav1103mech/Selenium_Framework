package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.IrisGetHardwareDetailsToSyncRequest;
import com.pinelabs.RnD.API.PojoFiles.IrisGetHardwareDetailsToSyncResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;

public class IrisGetHardwareDetailsToSync extends BaseUtilsAPI {
    static IrisGetHardwareDetailsToSync irisGetHardwareDetailsToSync;
    IrisGetHardwareDetailsToSyncRequest irisGetHardwareDetailsToSyncRequest;
    IrisGetHardwareDetailsToSyncResponse irisGetHardwareDetailsToSyncResponse;
    String defaultRequest = "{\"HardwareId\":\"0821397407\",\"PumpNum\":1,\"NozNum\":1,\"QrStr\":\"IRIS://Amt=15090&TxnId=242&VehNo=AB12DE45677&DT=23042021124649&RoId=333000&ProdType=Petrol\",\"ClmTranDt\":\"23042021124700\",\"ROid\":333000}";
    String request;

    private IrisGetHardwareDetailsToSync(String request) {
        this.request = request;
        irisGetHardwareDetailsToSyncRequest = stringToJavaObject(this.request, IrisGetHardwareDetailsToSyncRequest.class);
    }

    public static IrisGetHardwareDetailsToSync getInstance(String request) {
        if (irisGetHardwareDetailsToSync == null)
            irisGetHardwareDetailsToSync = new IrisGetHardwareDetailsToSync(request);
        return irisGetHardwareDetailsToSync;
    }


    public void createAndExecute(String token) {
        defaultRequest = javaObjectToString(irisGetHardwareDetailsToSyncRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(defaultRequest, URI.irisGetHardwareDetailsToSync, URI.baseURL, token);
        irisGetHardwareDetailsToSyncResponse = stringToJavaObject(response.asString(), IrisGetHardwareDetailsToSyncResponse.class);

    }

    public IrisGetHardwareDetailsToSyncResponse getResponsePojo() {
        return irisGetHardwareDetailsToSyncResponse;
    }

    public IrisGetHardwareDetailsToSyncRequest getRequestPojo() {
        return irisGetHardwareDetailsToSyncRequest;
    }
}
