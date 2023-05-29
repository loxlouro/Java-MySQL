package com.example.fxmultithreadserver.system;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {
    private static Socket clientDialog;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {
        try {
            this.outputStream = new DataOutputStream(clientDialog.getOutputStream());
            this.inputStream = new DataInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created with " + clientDialog.getInetAddress());
            System.out.println("DataOutputStream created with " + clientDialog.getInetAddress());

            //main working part
           // while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");
                //String entry = "";
//                try {
//                    entry = inputStream.readUTF();
//                } catch (EOFException e) {
//                    e.printStackTrace();
//                } catch (SocketException e) {
//                    e.printStackTrace();
//                    break;
//                }

//                System.out.println("Client message: " + entry);
//                if (entry.equalsIgnoreCase("quit")) {
//                    System.out.println("Client initialize connection suicide");
//                    outputStream.writeUTF(temp);
//                    Thread.sleep(3000);
//                    break;
//                }


                System.out.println("Server try writing to channel");
               // outputStream.writeUTF(temp);
                System.out.println("Server wrote message to clientDialog");


                outputStream.flush();
           // }

            //main working part


        } catch (IOException e) {
            e.printStackTrace();
        }// catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    public void closeChannel(){
        try {
            System.out.println("Client disconnected");
            System.out.println("Closing connections and channels");
            inputStream.close();
            outputStream.close();

            clientDialog.close();
            System.out.println("Closing connections & channels - done");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
