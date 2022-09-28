package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class IrisUploadBookFuelDetailsRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("MobNum")
    private String mobNum;
    @JsonProperty("VehNum")
    private String vehNum;
    @JsonProperty("VehSeg")
    private String vehSeg;
    @JsonProperty("ProdType")
    private String prodType;
    @JsonProperty("PayMode")
    private String payMode;
    @JsonProperty("TransAmt")
    private Integer transAmt;
    @JsonProperty("BkTranDt")
    private String bkTranDt;
    @JsonProperty("Roid")
    private Integer roid;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
