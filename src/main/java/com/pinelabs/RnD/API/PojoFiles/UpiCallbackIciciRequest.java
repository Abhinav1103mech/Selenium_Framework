package com.pinelabs.RnD.API.PojoFiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class UpiCallbackIciciRequest {

    @JsonProperty("merchantId")
    private String merchantId;
    @JsonProperty("subMerchantId")
    private String subMerchantId;
    @JsonProperty("terminalId")
    private String terminalId;
    @JsonProperty("BankRRN")
    private String bankRRN;
    @JsonProperty("merchantTranId")
    private String merchantTranId;
    @JsonProperty("PayerName")
    private String payerName;
    @JsonProperty("PayerMobile")
    private String payerMobile;
    @JsonProperty("PayerVA")
    private String payerVA;
    @JsonProperty("PayerAmount")
    private String payerAmount;
    @JsonProperty("TxnStatus")
    private String txnStatus;
    @JsonProperty("TxnInitDate")
    private String txnInitDate;
    @JsonProperty("TxnCompletionDate")
    private String txnCompletionDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
