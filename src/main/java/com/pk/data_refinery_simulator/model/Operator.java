/* The purpose of this Operator class is to set variables and getters for the Operator data */
package com.pk.data_refinery_simulator.model;

public class Operator {

    private String operatorName;
    private String country;
    private String countryCode;
    private String mcc;
    private String mnc;
    private String ndc;

    public Operator(
            String operatorName,
            String country,
            String countryCode,
            String mcc,
            String mnc,
            String ndc){
        this.operatorName = operatorName;
        this.country = country;
        this.countryCode = countryCode;
        this.mcc = mcc;
        this.mnc = mnc;
        this.ndc = ndc;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public String getNdc() {
        return ndc;
    }
    
    @Override
    public String toString(){
        return "Operator{" +
        "operatorName='" + operatorName + '\'' +
        ", country='" + country + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", mcc='" + mcc + '\'' +
        ", mnc='" + mnc + '\'' +
        ", ndc='" + ndc + '\'' +
        '}';
    }
}
