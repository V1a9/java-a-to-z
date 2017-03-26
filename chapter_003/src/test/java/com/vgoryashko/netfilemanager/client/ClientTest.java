package com.vgoryashko.netfilemanager.client;

import com.google.common.base.Joiner;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/22/2017
 */
public class ClientTest {

    private String NL = System.getProperty("line.separator");
    private String FS = File.separator;

    /**
     * Tests have to be reworked.
     */

//    @Test
//    public void clientTest() throws IOException {
//
//        Socket socket = mock(Socket.class);
//
////        ByteArrayInputStream userInput = new ByteArrayInputStream(
////                Joiner.on(NL)
////                        .join(
////                                "ls",
////                                "pwd",
////                                "cd usr",
////                                "..",
////                                "cd tmp",
////                                String.format("upl .%stest.txt", FS),
////                                "exit")
////                        .getBytes()
////        );
//
//        ByteArrayInputStream userInput = new ByteArrayInputStream("exit".getBytes());
//
//        DataInputStream clientIn = new DataInputStream(new ByteArrayInputStream(
//                Joiner.on(NL)
//                        .join(
//                                "You're connected to the file server.",
//                                "Enter command: ",
////                                String.format(".%sroot%s", FS, FS),
////                                String.format(".%sroot%stmp", FS, FS),
////                                String.format(".%sroot%susr", FS, FS),
////                                "",
////                                "Enter command: ",
//                                "Server is shutting down...",
//                                ""
//                        )
//                        .getBytes()
//        ));
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setIn(userInput);
//
//        when(socket.getInputStream()).thenReturn(clientIn);
//        when(socket.getOutputStream()).thenReturn(out);
//
//        Client client = new Client(socket);
//        client.start();
//
//        assertThat(out.toString(), is(
//                Joiner.on(NL)
//                        .join(
//                                "You're connected to the file server.",
//                                "Enter command: ",
////                                String.format(".%sroot%s", FS, FS),
////                                String.format(".%sroot%stmp", FS, FS),
////                                String.format(".%sroot%susr", FS, FS),
////                                "",
////                                "Enter command: ",
//                                "Server is shutting down...",
//                                ""
//                        )
//        ));
//
//    }

//    @Test
//    public void dataInputTest() throws IOException {
//
//        String[] userInput = {"exit", "ls", "pwd"};
//
//        DataOutputStream fileOut = new DataOutputStream(
//                new BufferedOutputStream(
//                        new FileOutputStream("userInput")));
//
//        for (int i = 0; i < userInput.length; i++) {
//            fileOut.writeUTF(userInput[i]);
//        }
//
//        DataInput in = new DataInputStream(
//                new BufferedInputStream(
//                        new FileInputStream("userInput")));
//
//        DataOutputStream out = new DataOutputStream(System.out);
//
//        System.out.println(in.readUTF());
//
//        }

    }
