package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class IrisBookClaimFuelStatusRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("ROid")
    private String rOid;
    @JsonProperty("TranId")
    private String tranId;
    @JsonProperty("RedeemAmt")
    private Integer redeemAmt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
