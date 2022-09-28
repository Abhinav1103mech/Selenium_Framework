package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class IrisUpdateLubeStockDetailsRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("LubeCode")
    private String lubeCode;
    @JsonProperty("Quantity")
    private Integer quantity;
    @JsonProperty("TotalQuantity")
    private Integer totalQuantity;
    @JsonProperty("StockUpdationDateTime")
    private String stockUpdationDateTime;
    @JsonProperty("ROid")
    private String rOid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
