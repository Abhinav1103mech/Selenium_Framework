package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class IrisUploadLocalAccPaymentDetailsRequest {
    @JsonProperty("HardwareId")
    private String hardwareId;
    @JsonProperty("AccountNumber")
    private String accountNumber;
    @JsonProperty("VoucherId")
    private String voucherId;
    @JsonProperty("ROid")
    private Integer rOid;
    @JsonProperty("PaymentAmount")
    private Integer paymentAmount;
    @JsonProperty("PayMode")
    private String payMode;
    @JsonProperty("PaidAmount")
    private Integer paidAmount;
    @JsonProperty("OutstandingAmount")
    private Integer outstandingAmount;
    @JsonProperty("LocalAccountPaymentDateTime")
    private String localAccountPaymentDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
