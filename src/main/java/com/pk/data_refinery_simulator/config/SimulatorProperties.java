package com.pk.data_refinery_simulator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "simulator")
public class SimulatorProperties {

    private int recordCount;
    private String datatype;
    private String timestamp;
    private int timeperiod;

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

    public String getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(String timestamp){
        this.timestamp=timestamp;
    }

    public int getTimePeriod(){
        return timeperiod;
    }
    public void setTimePeriod(int timeperiod){
        this.timeperiod=timeperiod;
    }
}