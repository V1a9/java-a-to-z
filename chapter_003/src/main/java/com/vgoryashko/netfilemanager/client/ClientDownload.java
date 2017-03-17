package com.vgoryashko.netfilemanager.client;

import java.io.*;
import java.net.Socket;

/**
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/15/2017
 */
public class ClientDownload {

    private Socket socket;

    public ClientDownload(Socket aSocket) {
        this.socket = aSocket;
    }

    public void upload() {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
             FileOutputStream fOut = new FileOutputStream(
                     String.format(
                             ".%sroot%spom.xml", File.separator, File.separator))) {

            String userInput;
            while ((userInput = in.readLine()) != null) {
                fOut.write(userInput.getBytes());
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
