package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/13/2017
 */
public class Client {
    Socket socket = new Socket(InetAddress.getByName(ip), port);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    Scanner console = new Scanner(System.in);
    do {
        out.println("Hello oracle");
        String str;
        while (!(str = in.readLine()).isEmpty()) {
            System.out.println(str);
        }
    } while ()
}
