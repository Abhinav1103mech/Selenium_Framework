package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class UpiAmazonPayRequest {
    @JsonProperty("merchantID")
    private String merchantID;
    @JsonProperty("orderTotalAmount")
    private String orderTotalAmount;
    @JsonProperty("sellerOrderId")
    private String sellerOrderId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
