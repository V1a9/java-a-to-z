package com.vgoryashko.bot;

import com.google.common.base.Joiner;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class that tests Client application.
 *
 * @author Vlad Goryashko
 * @version 0.9
 * @since 2/26/2017
 */
public class ClientTest {

    /**
     * Variable that stores line separator value.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Method that tests.
     *
     * @throws IOException                          IOException
     */
    @Test
    public void whenClientSendsHelloThenItReceivesRespectiveResponseFromServer() throws IOException {

        Socket socket = mock(Socket.class);

        ByteArrayInputStream in = new ByteArrayInputStream(Joiner.on(NL)
                .join(
                        "Oracle: The connection was established successfully!",
                        "Oracle: Hello, dear friend, I'm an oracle!",
                        "",
                        "Oracle: Good by my friend.",
                        "Server is shutting down....",
                        "",
                        ""
                ).getBytes());

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(Joiner.on(NL)
                .join(
                        "hello",
                        "bye"
                ).getBytes());

        System.setIn(inputStream);

        Client client = new Client(socket);
        client.clientStart();

        assertThat(out.toString(), is(
                Joiner.on(NL).
                        join(
                                "hello",
                                "bye",
                                ""
                        )
                )
        );
    }
}
