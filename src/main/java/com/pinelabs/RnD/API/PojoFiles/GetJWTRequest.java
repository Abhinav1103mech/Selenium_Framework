package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GetJWTRequest {


        @JsonProperty("hardwareid")
        private String hardwareid;
        @JsonProperty("Devicesubtype")
        private String devicesubtype;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}





















