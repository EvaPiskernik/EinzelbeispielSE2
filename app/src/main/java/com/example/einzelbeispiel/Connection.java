package com.example.einzelbeispiel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection extends Thread implements Runnable
{
    String serverMsg;
    String response;
    public Connection(String message) {
        this.serverMsg = message;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("se2-submission.aau.at", 20080);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.writeBytes(serverMsg + '\n');
            response = in.readLine();
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServerMsg()
    {
        return this.response;
    }


}
