package com.pk.data_refinery_simulator.generator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.pk.data_refinery_simulator.model.NATRecord;
import com.pk.data_refinery_simulator.util.RandomUtils;

@Component //Asking spring to create and manage an object of this class
public class NATGenerator {
    // ---> This was test by passing fixed values 28/06/2026
    // public NATRecord generateNATRecord(){
    //     return new NATRecord("10.0.5.21",
    //     54321,
    //     "43.21.56.201",
    //     60001, 
    //     "8.8.8.8", 
    //     443, 
    //     "TCP", 
    //     LocalDateTime.now());
    // }
    private static final List<Integer> DESTINATION_PORTS = List.of(
        80, //HTTP
        443,//HTTPS 
        53, //DNS
        22, //SSH
        25, //SMTP
        110,//POP3
        143 //IMAP
    );
    private final Random random = new Random();

    public NATRecord generateRecord(LocalDate simulationDate){
        return new NATRecord(
            generatePrivateIP(), 
            generatePrivatePort(), 
            generatePublicIP(), 
            generatePublicPort(), 
            generateDestinationIP(), 
            generateDestinationPort(), 
            generateProtocol(), 
            generateTimestamp(simulationDate));
    }

    public List<NATRecord> generateRecords(int recordCount, LocalDate simulationDate){
        List<NATRecord> records = new ArrayList<>();
        for(int i=0;i<recordCount;i++){
            records.add(generateRecord(simulationDate));
        }
        return records;
    }

    private String generatePrivateIP() {

    int choice = random.nextInt(3);

    switch(choice){
        case 0:
            return "10."
                    + RandomUtils.randomInteger(0,255)
                    + "."
                    + RandomUtils.randomInteger(0,255)
                    + "."
                    + RandomUtils.randomInteger(0,255);

        case 1:
            return "172."
                    + RandomUtils.randomInteger(16,31)
                    + "."
                    + RandomUtils.randomInteger(0,255)
                    + "."
                    + RandomUtils.randomInteger(0,255);

        case 2:
            return "192.168."
                    + RandomUtils.randomInteger(0,255)
                    + "."
                    + RandomUtils.randomInteger(0,255);

        default:
            throw new IllegalStateException(
            "Unexpected random choice."
            );
        }
    }

    private int generatePrivatePort(){
        return RandomUtils.randomInteger(1024, 65535);
    }

    private String generatePublicIP(){
        int firstOctet,secondOctet,thirdOctet,fourthOctet;
        while(true){
            firstOctet = RandomUtils.randomInteger(1, 223);
            secondOctet = RandomUtils.randomInteger(0, 255);
            thirdOctet = RandomUtils.randomInteger(0, 255);
            fourthOctet = RandomUtils.randomInteger(0, 255);
            if(isPrivateIP(firstOctet, secondOctet))
                continue;
            else
            break;
        }
        return firstOctet + "." 
               + secondOctet + "." 
               + thirdOctet + "." 
               + fourthOctet;
    }

    private int generatePublicPort(){
        return RandomUtils.randomInteger(1024, 65535);
    }
    
    private String generateDestinationIP(){
        return generatePublicIP();
    }
    
    private int generateDestinationPort(){
        int index = random.nextInt(DESTINATION_PORTS.size());
        return DESTINATION_PORTS.get(index);
    }
    
    private String generateProtocol(){
        final List<String> protocol = List.of("TCP","UDP");
        
        int index = random.nextInt(protocol.size());
        return protocol.get(index);
    }
    
    private LocalDateTime generateTimestamp(LocalDate simulationDate){
        int hour = RandomUtils.randomInteger(0,23);
        int minute = RandomUtils.randomInteger(0,59);
        int second = RandomUtils.randomInteger(0,59);
        //getting random time 

        return simulationDate.atTime(hour, minute, second);
    }

    private boolean isPrivateIP(int first, int second){
        if(first==10)
            return true;
        else if(first==172 && (second >= 16 && second <=31))
            return true;
        else if(first==192 && second==168)
            return true;

    return false;
    }
}