package com.example.coursework.system;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connector {
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    public Connector(Socket clientSocket){
        this.clientSocket = clientSocket;
        try {
            this.inputStream=new DataInputStream(clientSocket.getInputStream());
            this.outputStream=new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void closeAll(){
        try {
            this.outputStream.close();
            this.inputStream.close();
            this.clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

