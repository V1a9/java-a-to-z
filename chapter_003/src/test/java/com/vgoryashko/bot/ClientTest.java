package com.vgoryashko.bot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class that tests Client application.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/20/2017
 */
public class ClientTest {

    /**
     * Method that tests.
     *
     * @throws IOException                          IOException
     */
    @Test
    public void whenClientSendsHelloThenItReceivedRespectiveResponseFromServer() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("hello\n".getBytes());
        System.setIn(input);
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream commandToServer = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(commandToServer);
        Socket serverSocket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(commandToServer.toByteArray());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream messageFromServer = new ByteArrayInputStream(out.toString().getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(serverSocket);
        Client client = new Client(socket);
        server.serverLauncher();
        client.clientStart();
        assertThat(messageFromServer.toString(), is(
                Joiner.on(System.getProperty("line.separator")
                        .join("Oracle: The connection was established successfully!",
                                "Oracle: Hello, dear friend, I'm an oracle!",
                                ""))));
    }

}
