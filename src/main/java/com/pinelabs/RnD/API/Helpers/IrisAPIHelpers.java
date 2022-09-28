package com.pinelabs.RnD.API.Helpers;

import com.pinelabs.RnD.API.Builders.GetJWTToken;
import com.pinelabs.RnD.API.Builders.IrisUploadBookFuelDetails;
import com.pinelabs.RnD.API.PojoFiles.GetJWTResponse;
import com.pinelabs.RnD.API.PojoFiles.IrisUploadBookFuelDetailsResponse;

/*public class IrisAPIHelpers {

    public static GetJWTResponse getTokenFromGetJWTToken(String hardwareID, String request) {
        GetJWTToken getJWTToken = GetJWTToken.getInstance(request);
        getJWTToken.getRequest().setHardwareid(hardwareID);
        getJWTToken.getRequest().setDevicesubtype("82");
        getJWTToken.createAndExecute();
        return getJWTToken.getResponse();
    }

    public static IrisUploadBookFuelDetailsResponse uploadBookFuelDetails(String hardwareID, String accessToken){
        IrisUploadBookFuelDetails irisUploadBookFuelDetails = IrisUploadBookFuelDetails.getInstance(IrisUploadBookFuelDetails.defaultRequest);
        irisUploadBookFuelDetails.getRequestPojo().setHardwareId(hardwareID);
        irisUploadBookFuelDetails.getRequestPojo().setRoid(180664);
        irisUploadBookFuelDetails.createAndExecute(accessToken);
        return irisUploadBookFuelDetails.getResponsePojo();
    }
}
*/