package com.pk.data_refinery_simulator.config;

import java.time.LocalDate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "simulator")
public class SimulatorProperties {

    private int recordCount;
    private String datatype;
    private LocalDate timestamp;
    private int timeperiod;
    private String cdrhost;
    private int cdrport;
    private String nathost;
    private int natport;

    public int getRecordCount(){
        String value = getOverride("recordcount", "RECORDCOUNT");

        return value != null ? Integer.parseInt(value) : recordCount;

    }
    public void setRecordCount(int recordCount){
        this.recordCount=recordCount;
    }

    public String getDataType(){
        String value = getOverride("datatype", "DATATYPE");

        return value != null ? value : datatype;
    }
    public void setDataType(String datatype){
        this.datatype=datatype;
    }

    public LocalDate getTimestamp(){
        String value = getOverride("timestamp", "TIMESTAMP");

        return value != null ? LocalDate.parse(value) : timestamp;
    }
    public void setTimestamp(LocalDate timestamp){
        this.timestamp=timestamp;
    }

    public int getTimePeriod(){
        String value = getOverride("timeperiod", "TIMEPERIOD");

        return value != null ? Integer.parseInt(value) : timeperiod;
    }
    public void setTimePeriod(int timeperiod){
        this.timeperiod=timeperiod;
    }

    public String getCdrHost(){
        String value = getOverride("cdrhost", "CDRHOST");

        return value != null ? value : cdrhost;
    }
    public void setCdrHost(String cdrhost){
        this.cdrhost=cdrhost;
    }

    public int getCdrPort(){
        String value = getOverride("cdrport", "CDRPORT");

        return value != null ? Integer.parseInt(value) : cdrport; 
    }
    public void setCdrPort(int cdrport){
        this.cdrport=cdrport;
    }

    public String getNatHost(){
        String value = getOverride("nathost", "NATHOST");

        return value != null ? value : nathost;
    }
    public void setNatHost(String nathost){
        this.nathost=nathost;
    }

    public int getNatPort(){
        String value = getOverride("natport", "NATPORT");

        return value != null ? Integer.parseInt(value) : natport;
    }
    public void setNatPort(int natport){
        this.natport=natport;
    }

    /*  This getOverride is a function used to reduce repeated code, this is to decide if override comes from 
        command line -d properties or docker compose, if neither then it will simply return application.yml config value  */
    
    private String getOverride(String propertyName, String envName) {
    String property = System.getProperty(propertyName);
    if (property != null) {
        return property;
    }

    return System.getenv(envName);
}
}