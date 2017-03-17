package com.vgoryashko.netfilemanager.server;

import java.io.*;
import java.net.Socket;

/**
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/15/2017
 */
public class ServerUpload {

    private Socket socket;

    public ServerUpload(Socket aSocket) {
        this.socket = aSocket;
    }

    public void upload() {

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(
                     new FileReader(
                             String.format(".%spom.xml", File.separator)))) {

            out.write("Transfer has been started...");

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                out.write(inputLine, 0, inputLine.length());
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
