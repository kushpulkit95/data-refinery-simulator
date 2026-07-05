package com.pk.data_refinery_simulator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CDRRecord {
    private String imsi;
    private String msisdn;
    private String imei;
    private String apn;
    private String ratType;
    private String action;
    private LocalDateTime timestamp;

    public CDRRecord(String imsi,String msisdn,String imei,String apn,String ratType,String action,LocalDateTime timestamp) {
        this.imsi = imsi;
        this.msisdn = msisdn;
        this.imei = imei;
        this.apn = apn;
        this.ratType = ratType;
        this.action = action;
        this.timestamp = timestamp;
}

    @Override
    public String toString() {
        return "CDRRecord{" +
        "imsi='" + imsi + '\'' +
        ", msisdn='" + msisdn + '\'' +
        ", imei='" + imei + '\'' +
        ", apn='" + apn + '\'' +
        ", ratType='" + ratType + '\'' +
        ", action='" + action + '\'' +
        ", timestamp=" + timestamp +
        '}';
}
}
