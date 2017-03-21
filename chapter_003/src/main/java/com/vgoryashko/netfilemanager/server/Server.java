package com.vgoryashko.netfilemanager.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that implements main logic of the server application for the network file manager.
 *
 * @author Vlad Goryashko
 * @version 0.11
 * @since 3/17/17
 */
public class Server {

    public Socket getSocket() {
        return this.socket;
    }

    private Socket socket;

    public Server(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Variable that stores the file separator value.
     */
    final String FS = File.separator;

    /**
     * Variable that stores root folder path of the server.
     */
    private final File rootFolder = new File(String.format(".%sroot", FS));

    /**
     * Variable that stores user folder path of the server.
     */
    private final File usrFolder = new File(String.format(".%sroot%susr", FS, FS));

    /**
     * Variable that stores tmp folder path of the server.
     */
    private final File tmpFolder = new File(String.format(".%sroot%stmp", FS, FS));

    File dwnFile = null;
    File uplFile = null;

    /**
     * Variable that stores a path of a currentFolder folder.
     */
    private File currentFolder = rootFolder;

    /**
     * Getter for the variable currentFolderFolder.
     *
     * @return                              <code>File</code>
     */
    public File getCurrentFolder() {
        return currentFolder;
    }

    /**
     * Method that setups the server environments.
     */
    public void initServer() {
        rootFolder.mkdir();
        usrFolder.mkdir();
        tmpFolder.mkdir();
    }

    public void download(DataInputStream aIn) {

        try (RandomAccessFile download = new RandomAccessFile(dwnFile, "rw")) {

            int data;
            do {
                data = aIn.read();
                if (data != 255) {
                    download.write(data);
                }
            } while (data != 255);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void upload(DataOutputStream aOut) {

        try (RandomAccessFile upload = new RandomAccessFile(uplFile, "rw")) {
            int data;
            do {
                data = upload.read();
                aOut.write(data);
            } while (data != -1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void start() {

        try (DataInputStream in = new DataInputStream(this.socket.getInputStream());
             DataOutputStream out = new DataOutputStream(this.socket.getOutputStream())) {

            out.writeUTF("You're connected to the file server.");
            String clientCommand;

            do {

                out.writeUTF("Enter command: ");
                clientCommand = in.readUTF();

                if ("exit".equals(clientCommand)) {

                    out.writeUTF("Server is shutting down...");
                    out.writeUTF("");

                } else if ("ls".equals(clientCommand)) {

                    File[] files = currentFolder.listFiles();

                    if (files.length != 0) {

                        out.writeUTF(String.format("%s%s", currentFolder, FS));

                        for (File file : files) {
                            if (file.isDirectory()) {
                                out.writeUTF(String.format("%s%s", file.toString(), FS));
                            } else {
                                out.writeUTF(String.format("%s", file.toString()));
                            }
                        }

                        out.writeUTF("");

                    } else {
                        out.writeUTF(String.format("%s", currentFolder));
                        out.writeUTF("");
                    }

                } else if ("pwd".equals(clientCommand)) {

                    out.writeUTF(String.format("%s%s", currentFolder, FS));
                    out.writeUTF("");

                } else if (clientCommand.startsWith("cd ")) {

                    String dir = clientCommand.substring("cd ".length(), clientCommand.length());
                    String[] files = currentFolder.list();

                    if (files.length > 0) {
                        for (int index = 0; index < files.length; index++) {
                            if (dir.equals(files[index])) {
                                currentFolder = new File(String.format("%s%s%s", rootFolder, FS, dir));
                                out.writeUTF(String.format("%s%s", currentFolder, FS));
                                out.writeUTF("");
                            } else if (index == files.length) {
                                out.writeUTF("There is no such directory.");
                                out.writeUTF("");
                            }
                        }
                    } else {
                        out.writeUTF("There is no such directory.");
                        out.writeUTF("");
                    }

                } else if ("..".equals(clientCommand)) {

                    currentFolder = new File(currentFolder.toString().substring(0, rootFolder.toString().length()));
                    out.writeUTF(String.format("%s%s", currentFolder, FS));
                    out.writeUTF("");

                } else if (clientCommand.startsWith("upl ")) {

                    String fileName = clientCommand.substring("upl ".length(), clientCommand.length());
                    String[] files = currentFolder.list();

                    if (files.length > 0) {
                        for (int index = 0; index < files.length; index++) {
                            if (fileName.equals(files[index])) {
                                out.writeUTF("A file with such name already exists.");
                                out.writeUTF("");
                            } else if (!fileName.equals(files[index]) && index == files.length - 1) {
                                dwnFile = new File(String.format("%s%s%s", currentFolder.toString(), FS, fileName));
                                download(in);
                                out.writeUTF("File transfer complete.");
                            }
                        }
                    } else {
                        dwnFile = new File(String.format("%s%s%s", currentFolder.toString(), FS, fileName));
                        download(in);
                        out.writeUTF("File transfer complete.");
                    }
                } else if (clientCommand.startsWith("dwn ")) {
                    String fileName = clientCommand.substring("dwn ".length(), clientCommand.length());
                    String[] files = currentFolder.list();

                    if (files.length > 0) {
                        for (int index = 0; index < files.length; index++) {
                            if (fileName.equals(files[index])) {
                                uplFile = new File(String.format("%s%s%s", currentFolder.toString(), FS, fileName));
                                upload(out);
                                System.out.println(in.readUTF());
                            } else if (!fileName.equals(files[index]) && index == files.length - 1) {
                                out.writeUTF("There is no a file with such name.");
                                out.writeUTF("");
                            }
                        }
                    }
                }

            } while (!("exit".equals(clientCommand)));


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) {

        ServerSettings serverSettings = new ServerSettings("app.properties");
        serverSettings.readProperties();

        System.out.println("Server is up and waiting for a connection.");

        try (ServerSocket serverSocket = new ServerSocket(serverSettings.getPort());
             Socket socket = serverSocket.accept()) {
            System.out.println("A client has been connected.");
            Server server = new Server(socket);
            server.initServer();
            server.start();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + serverSettings.getPort() + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
