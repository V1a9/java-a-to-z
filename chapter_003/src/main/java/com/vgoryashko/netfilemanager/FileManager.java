package com.vgoryashko.netfilemanager;

import com.vgoryashko.service.Settings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class that implements a network file server.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 3/01/2017
 */
public class FileManager {

    /**
     * Variable that referring to an instance of a Socket class.
     */
    private Socket socket;

    /**
     * Constructor for the class.
     *
     * @param aSocket                   Socket for the server
     */
    public FileManager(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Method that implements basic logic of the file server application.
     */
    public void managerStart() {

        String command = null;
        String responce = null;
        File dwnFile = null;
        File uplFile = null;
        boolean uplReady = false;
        boolean dwnReady = false;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readLine());

            do {

                System.out.println(in.readLine());
                System.out.println(in.readLine());
                command = scanner.nextLine();

                if ("exit".equals(command)) {

                    out.println(command);
                    responce = in.readLine();
                    System.out.println(responce);

                } else if (command.startsWith("upl ")) {

                    String filePath = command.substring("upl ".length(), command.length());
                    uplFile = new File(filePath);

                    String fileName = uplFile.getName();
                    out.println(String.format("upl %s", fileName));

                    /**
                     * Check why -1 isn't returned.
                     */
                    try (RandomAccessFile upload = new RandomAccessFile(uplFile, "rw");
                         PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true)) {
                        int data;
                        do {
                            data = upload.read();
                            if (data != -1) {
                                output.write(data);
                            }
                        } while (data != -1);
                    }

                } else {

                    out.println(command);

                    do {
                        responce = in.readLine();
                        if (!responce.isEmpty()) {
                            System.out.println(responce);
                        }
                    } while (!responce.isEmpty());
                }

//                if (uplReady) {
//                    try (RandomAccessFile upload = new RandomAccessFile(uplFile, "rw")) {
//                        int data;
//                        do {
//                            data = upload.read();
//                            out.write(data);
//                        } while (data != -1);
//                        uplReady = false;
//                    }
//                }

            } while (!"exit".equals(command));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {
        String port = null;
        String serverIp = null;

        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();

        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
            port = settings.getValues("server.port");
            serverIp = settings.getValues("server.ip");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try (Socket socket = new Socket(InetAddress.getByName(serverIp), Integer.parseInt(port))) {
            new FileManager(socket).managerStart();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
