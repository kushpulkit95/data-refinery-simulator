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
        return recordCount;
    }
    public void setRecordCount(int recordCount){
        this.recordCount=recordCount;
    }

    public String getDataType(){
        return datatype;
    }
    public void setDataType(String datatype){
        this.datatype=datatype;
    }

    public LocalDate getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(LocalDate timestamp){
        this.timestamp=timestamp;
    }

    public int getTimePeriod(){
        return timeperiod;
    }
    public void setTimePeriod(int timeperiod){
        this.timeperiod=timeperiod;
    }

    public String getCdrHost(){
        return cdrhost;
    }
    public void setCdrHost(String cdrhost){
        this.cdrhost=cdrhost;
    }

    public int getCdrPort(){
        return cdrport;
    }
    public void setCdrPort(int cdrport){
        this.cdrport=cdrport;
    }

    public String getNatHost(){
        return nathost;
    }
    public void setNatHost(String nathost){
        this.nathost=nathost;
    }

    public int getNatPort(){
        return natport;
    }
    public void setNatPort(int natport){
        this.natport=natport;
    }
}