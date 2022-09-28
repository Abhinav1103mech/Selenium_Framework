package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class IrisGetParametersFromPineCloudResponse {
    @JsonProperty("BpclCloudUrl")
    private String bpclCloudUrl;
    @JsonProperty("PineCloudUrl")
    private String pineCloudUrl;
    @JsonProperty("AlpCloudUrl")
    private String alpCloudUrl;
    @JsonProperty("FastTagUrl")
    private Object fastTagUrl;
    @JsonProperty("ROid")
    private String rOid;
    @JsonProperty("CommMode")
    private Integer commMode;
    @JsonProperty("PrintWidth")
    private Integer printWidth;
    @JsonProperty("ErrCode")
    private String errCode;
    @JsonProperty("ResMsg")
    private String resMsg;
    @JsonProperty("VmsAuthToken")
    private Object vmsAuthToken;
    @JsonProperty("VmsCloudUrl")
    private String vmsCloudUrl;
    @JsonProperty("QBusting")
    private Boolean qBusting;
    @JsonProperty("VmsPrePay")
    private Boolean vmsPrePay;
    @JsonProperty("PriceChange")
    private Boolean priceChange;
    @JsonProperty("LocalAccount")
    private Boolean localAccount;
    @JsonProperty("FccShiftEnd")
    private Boolean fccShiftEnd;
    @JsonProperty("GeoLocation")
    private Boolean geoLocation;
    @JsonProperty("AlpFeature")
    private Boolean alpFeature;
    @JsonProperty("FasTagFeature")
    private Boolean fasTagFeature;
    @JsonProperty("Lube")
    private Boolean lube;
    @JsonProperty("latitute")
    private Object latitute;
    @JsonProperty("longitute")
    private Object longitute;
    @JsonProperty("GeoLocationRadius")
    private String geoLocationRadius;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
