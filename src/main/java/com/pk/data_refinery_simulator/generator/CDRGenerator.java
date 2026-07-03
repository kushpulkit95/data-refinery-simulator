package com.pk.data_refinery_simulator.generator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pk.data_refinery_simulator.data.OperatorData;
import com.pk.data_refinery_simulator.model.CDRRecord;
import com.pk.data_refinery_simulator.model.Operator;
import com.pk.data_refinery_simulator.data.IMEIData;
import com.pk.data_refinery_simulator.util.RandomUtils;

public class CDRGenerator {
    // ---> This was test by passing fixed values. 28/6/2026
    // public CDRRecord generateCDRRecord(){
    // return new CDRRecord("404450123456789",
    // "919876543210",
    // "35674010123456",
    // "internet.mnc045.mcc404.gprs",
    // "4G",
    // "START",
    // LocalDateTime.now());
    // }
    private final Random random = new Random();

    public CDRRecord generateRecord(){
        Operator operator = pickRandomOperator();
        String tac = pickRandomTAC();
        // NEVER do :
        // Operator operator = pickRandomOperator(); 
        // in every generate function as they will not be same
        // here, as the operator may vary in imsi and msisdn

        return new CDRRecord(
            generateIMSI(operator), 
            generateMSISDN(operator), 
            generateIMEI(tac), 
            generateAPN(operator), 
            generateRatType(),
            generateAction(),
            generateTimestamp());
    }

    public List<CDRRecord> generateRecords(int recordCount){
        List<CDRRecord> records = new ArrayList<>();

        for(int i=0;i<recordCount;i++){
            records.add(generateRecord());
        }
        return records;
    }
    
    String generateIMSI(Operator operator){
        String mcc = operator.getMcc();
        String mnc = operator.getMnc();
        int msinLength= 15 - (mcc.length()+mnc.length());
        // this is important imsi cannot be more than 15 digits
        String msin = RandomUtils.randomNumericString(msinLength);
        return mcc + mnc + msin;
    }

    String generateMSISDN(Operator operator){
        String countryCode = operator.getCountryCode();
        String ndc = operator.getNdc();
        int subscriberLength = 15 - (countryCode.length() + ndc.length());
        // this is important msisdn cannot be more than 15 digits
        String subscriberNumber = RandomUtils.randomNumericString(subscriberLength);
        return countryCode + ndc + subscriberNumber;
    }

    String generateIMEI(String tac){
        String serialNumber = RandomUtils.randomNumericString(7);
        return tac + serialNumber;
    }

    String generateAPN(Operator operator){

        String mcc = operator.getMcc();

        String mnc = String.format(
                "%03d",
                Integer.parseInt(operator.getMnc())
        );
    // converting to appropriate mnc format, because for APN if mnc is 2-digits long, ex:45, it needs to be written 045.
    // if mnc is 7, it must be written 007
        return "internet.mnc"
                + mnc
                + ".mcc"
                + mcc
                + ".gprs";
    }

    private String generateRatType(){
        final List<String> ratTypes = List.of(
        "2G",
        "3G",
        "4G",
        "5G");

        int index = random.nextInt(ratTypes.size());
        return ratTypes.get(index);
    }

    private String generateAction(){
        final List<String> actionTypes = List.of(
        "START",
        "STOP",
        "UPDATE"
        );
        int index = random.nextInt(actionTypes.size());
        return actionTypes.get(index);
    }

    private LocalDateTime generateTimestamp(){
        return LocalDateTime.now();
    }

    private Operator pickRandomOperator(){
        int index = random.nextInt(OperatorData.OPERATORS.size());
        
        return OperatorData.OPERATORS.get(index);
        
        // OperatorData contains all operators in OPERATORS list
        // ↓
        // Choose random index using random object
        // ↓
        // Return that operator

    }

    private String pickRandomTAC(){
        int index = random.nextInt(IMEIData.TAC_LIST.size());

        return IMEIData.TAC_LIST.get(index);
    }
    //Exact same working pattern as operator picker
}