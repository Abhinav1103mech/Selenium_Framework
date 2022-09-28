package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class IrisUploadShiftDetailsRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("ShiftStatus")
    private String shiftStatus;
    @JsonProperty("ShiftNumber")
    private Integer shiftNumber;
    @JsonProperty("ROid")
    private Integer rOid;
    @JsonProperty("ShiftStartDateTime")
    private String shiftStartDateTime;
    @JsonProperty("ShiftEndDateTime")
    private String shiftEndDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
