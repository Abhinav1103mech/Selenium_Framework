package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class IrisUploadBookFuelDetailsResponse {
    @JsonProperty("ErrCode")
    private Integer errCode;
    @JsonProperty("ResMsg")
    private String resMsg;
    @JsonProperty("QrStr")
    private String qrStr;
    @JsonProperty("QrValidityTp")
    private Integer qrValidityTp;
    @JsonProperty("QRGeneratedTimeStamp")
    private String qRGeneratedTimeStamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
