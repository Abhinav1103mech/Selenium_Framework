package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class IrisUploadTransactionDetailsRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("NozzleNum")
    private Integer nozzleNum;
    @JsonProperty("PumpNum")
    private Integer pumpNum;
    @JsonProperty("ROid")
    private Integer rOid;
    @JsonProperty("lAmount")
    private Integer lAmount;
    @JsonProperty("lCreditLimit")
    private Integer lCreditLimit;
    @JsonProperty("lDiscount")
    private Integer lDiscount;
    @JsonProperty("lNetAmount")
    private Integer lNetAmount;
    @JsonProperty("lOutstandingAmount")
    private Integer lOutstandingAmount;
    @JsonProperty("lPresetValue")
    private Integer lPresetValue;
    @JsonProperty("lProductPrice")
    private Integer lProductPrice;
    @JsonProperty("lShiftNum")
    private Integer lShiftNum;
    @JsonProperty("lTxnStatus")
    private Integer lTxnStatus;
    @JsonProperty("lVolume")
    private Integer lVolume;
    @JsonProperty("strAttendant")
    private String strAttendant;
    @JsonProperty("strCashMemoNum")
    private String strCashMemoNum;
    @JsonProperty("strCommMode")
    private String strCommMode;
    @JsonProperty("strLubeCode")
    private String strLubeCode;
    @JsonProperty("strMobileNumber")
    private String strMobileNumber;
    @JsonProperty("strPLTrnxId")
    private String strPLTrnxId;
    @JsonProperty("strPaymode")
    private String strPaymode;
    @JsonProperty("strPresetType")
    private String strPresetType;
    @JsonProperty("strProductName")
    private String strProductName;
    @JsonProperty("strResponseCode")
    private String strResponseCode;
    @JsonProperty("strTransactionEndDateTime")
    private String strTransactionEndDateTime;
    @JsonProperty("strTransactionStartDateTime")
    private String strTransactionStartDateTime;
    @JsonProperty("strUniqueId")
    private String strUniqueId;
    @JsonProperty("strVechSeg")
    private String strVechSeg;
    @JsonProperty("strVechicleNumber")
    private String strVechicleNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
