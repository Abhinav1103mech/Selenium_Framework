package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class IrisUploadLubeSaleDetailsRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("LubeCode")
    private String lubeCode;
    @JsonProperty("Quantity")
    private Integer quantity;
    @JsonProperty("VoucherId")
    private String voucherId;
    @JsonProperty("TotalAmount")
    private Integer totalAmount;
    @JsonProperty("Paymode")
    private String paymode;
    @JsonProperty("LubeSaleDateTime")
    private String lubeSaleDateTime;
    @JsonProperty("ROid")
    private String rOid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
