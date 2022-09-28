package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class IrisUploadProductPriceDetailsRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("ProductNumber")
    private Integer productNumber;
    @JsonProperty("ProductPrice")
    private Integer productPrice;
    @JsonProperty("ROid")
    private Integer rOid;
    @JsonProperty("PriceUpdateDateTime")
    private String priceUpdateDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
