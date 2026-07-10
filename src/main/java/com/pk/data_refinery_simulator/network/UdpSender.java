package com.pk.data_refinery_simulator.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class UdpSender implements Sender{
    private final String host;
    private final int port;
    
    public UdpSender(String host, int port){
        this.host=host;
        this.port=port;
    }

    public boolean send(String message){

        try(
            DatagramSocket socket = new DatagramSocket();
            // Creates a UDP socket for sending packets (no connection required).
        ){
            byte[] data = message.getBytes(); 
            //we send bytes because diff computers do not share java's string object, bytes are universal
            
            DatagramPacket packet = new DatagramPacket(
                data, //actual bytes to send
                data.length, //how many bytes should be sent
                InetAddress.getByName(host), //this converts 'host' to something Java Networks would understand
                //i.e. resolves host name to IP address
                port); //exactly like TCP, which application should receive this packet?
                
                socket.send(packet);

        } catch(IOException e){
            return false;
        }
        return true;
    }
}