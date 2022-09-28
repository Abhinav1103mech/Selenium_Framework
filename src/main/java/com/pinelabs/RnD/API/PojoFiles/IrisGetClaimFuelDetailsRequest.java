package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class IrisGetClaimFuelDetailsRequest {

    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("PumpNum")
    private Integer pumpNum;
    @JsonProperty("NozNum")
    private Integer nozNum;
    @JsonProperty("QrStr")
    private String qrStr;
    @JsonProperty("ClmTranDt")
    private String clmTranDt;
    @JsonProperty("ROid")
    private Integer rOid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
