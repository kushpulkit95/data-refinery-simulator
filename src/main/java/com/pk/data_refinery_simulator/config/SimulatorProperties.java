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
        String override = System.getProperty("recordcount");
        if(override!=null)
            return Integer.parseInt(override);
        return recordCount;
    }
    public void setRecordCount(int recordCount){
        this.recordCount=recordCount;
    }

    public String getDataType(){
        String override = System.getProperty("datatype");
        if(override!=null)
            return override;
        return datatype;
    }
    public void setDataType(String datatype){
        this.datatype=datatype;
    }

    public LocalDate getTimestamp(){
        String override = System.getProperty("timestamp");
        if(override!=null)
            return LocalDate.parse(override);
        return timestamp;
    }
    public void setTimestamp(LocalDate timestamp){
        this.timestamp=timestamp;
    }

    public int getTimePeriod(){
        String override = System.getProperty("timeperiod");
        if(override!=null)
            return Integer.parseInt(override);
        return timeperiod;
    }
    public void setTimePeriod(int timeperiod){
        this.timeperiod=timeperiod;
    }

    public String getCdrHost(){
        String override = System.getProperty("cdrhost");
        if(override!=null)
            return override;
        return cdrhost;
    }
    public void setCdrHost(String cdrhost){
        this.cdrhost=cdrhost;
    }

    public int getCdrPort(){
        String override = System.getProperty("cdrport");
        if(override!=null)
            return Integer.parseInt(override);
        return cdrport;
    }
    public void setCdrPort(int cdrport){
        this.cdrport=cdrport;
    }

    public String getNatHost(){
        String override = System.getProperty("nathost");
        if(override!=null)
            return override;
        return nathost;
    }
    public void setNatHost(String nathost){
        this.nathost=nathost;
    }

    public int getNatPort(){
        String override = System.getProperty("natport");
        if(override!=null)
            return Integer.parseInt(override);
        return natport;
    }
    public void setNatPort(int natport){
        this.natport=natport;
    }
}