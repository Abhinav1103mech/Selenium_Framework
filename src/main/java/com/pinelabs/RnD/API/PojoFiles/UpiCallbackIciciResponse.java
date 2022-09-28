package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class UpiCallbackIciciResponse {
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("erroCode")
    private String erroCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
