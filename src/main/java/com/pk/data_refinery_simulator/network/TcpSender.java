package com.pk.data_refinery_simulator.network;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;

public class TcpSender implements Sender{
    
    private final String host;
    private final int port;

    public TcpSender(String host,int port){
        this.host=host;
        this.port=port;
    }

    public boolean send(String message){

        // try(){} is called "try-with-resources"; automatically calls close(); on every resource
        // "These are resources that Java will automatically clean up when I'm done."
        try(
            Socket socket = new Socket(host,port); //Connection established
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true); //Ready to send data
        ){
            writer.println(message); //Data is sent through socket
            //System.out.println("Message sent Successfully!"); //Confirmation message
            return true; //Confirmation that the message was sent
        } catch (IOException e){
            return false;
        }
    }
}