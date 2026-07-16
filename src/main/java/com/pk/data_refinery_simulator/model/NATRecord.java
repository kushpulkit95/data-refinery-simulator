package com.pk.data_refinery_simulator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NATRecord {
    private String privateIp;
    private int privatePort;
    private String publicIp;
    private int publicPort;
    private String destinationIp;
    private int destinationPort;
    private String protocol;
    private LocalDateTime timestamp;

    public NATRecord(
    String privateIp,
    int privatePort,
    String publicIp,
    int publicPort,
    String destinationIp,
    int destinationPort,
    String protocol,
    LocalDateTime timestamp){

        this.privateIp=privateIp;
        this.privatePort=privatePort;
        this.publicIp=publicIp;
        this.publicPort=publicPort;
        this.destinationIp=destinationIp;
        this.destinationPort=destinationPort;
        this.protocol=protocol;
        this.timestamp=timestamp;

    }
//     @Override
//     public String toString() {
//         return "NATRecord{" +
//         "Private IP='" + privateIp + '\'' +
//         ", Private Port='" + privatePort + '\'' +
//         ", Public IP='" + publicIp + '\'' +
//         ", Public Port='" + publicPort + '\'' +
//         ", Destination IP='" + destinationIp + '\'' +
//         ", Destination Port='" + destinationPort + '\'' +
//         ", Protocol='" + protocol + '\'' +
//         ", Timestamp='" + timestamp + 
//         '}';
// }

    public String toCsv(){
        return String.join(",",
            privateIp,
            Integer.toString(privatePort),
            publicIp,
            Integer.toString(publicPort),
            destinationIp,
            Integer.toString(destinationPort),
            protocol,
            timestamp.toString()
         );
    }
}