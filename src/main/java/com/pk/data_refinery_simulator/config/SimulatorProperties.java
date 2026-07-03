package com.pk.data_refinery_simulator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.pk.data_refinery_simulator.enums.DataType;

@Component
@ConfigurationProperties(prefix = "simulator")
public class SimulatorProperties {

    private int recordCount;
    private DataType datatype;
    private String timestamp;
    private int timeperiod;

    public int getRecordCount(){
        return recordCount;
    }
    public void setRecordCount(int recordCount){
        this.recordCount=recordCount;
    }

    public DataType getDataType(){
        return datatype;
    }
    public void setDataType(DataType datatype){
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