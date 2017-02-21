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
 * @version 0.2
 * @since 2/21/2017
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

        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format(
                        "Oracle: The connection was established successfully!%sOracle: Hello, dear friend, I'm an oracle!%s%s",
                        NL,
                        NL,
                        NL
                ).getBytes()
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("hello".getBytes());
        System.setIn(inputStream);

        Client client = new Client(socket);
        client.clientStart();

        assertThat(out.toString(), is("hello\n"));

        assertThat(in.toString(), is(
                Joiner.on(NL).
                        join(
                                "Oracle: Hello, dear friend, I'm an oracle!",
                                ""
                        )
                )
        );
    }
}
