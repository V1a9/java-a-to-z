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
 * Class that tests the Server class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/19/17
 */
public class ServerTest {

    /**
     * Variable that stores line separator value.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Method that unifies testing of the Server application.
     *
     * @param inputMessage                      messages to be sent to the server
     * @param outputMessage                     expected answers to be sent by the server
     * @throws IOException                      IOException
     */
    private void testServer(String inputMessage, String outputMessage) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(inputMessage.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.serverLauncher();
        assertThat(out.toString(), is(outputMessage));
    }

    /**
     * Method that tests exit in the Server application.
     *
     * @throws IOException                          IOException
     */
    @Test
    public void whenServerReceivesExitThenServerShutsDown() throws IOException {
        testServer("bye",
                Joiner.on(NL)
                        .join(
                                "Oracle: The connection was established successfully!",
                                "Oracle: Good by my friend.",
                                "Server is shutting down....",
                                "",
                                "")
        );

        testServer(
                Joiner.on(NL)
                        .join(
                                "hello",
                                "How r u?",
                                "How is your life?",
                                "",
                                "cool",
                                "bye"
                                ),
                Joiner.on(NL)
                        .join(
                                "Oracle: The connection was established successfully!",
                                "Oracle: Hello, dear friend, I'm an oracle!",
                                "",
                                "Oracle: Thanks, I'm fine!",
                                "",
                                "Oracle: The life is great!!!!",
                                "",
                                "Oracle: you sent nothing. Please try again.",
                                "",
                                "Oracle: you sent following \"cool\".",
                                "",
                                "Oracle: Good by my friend.",
                                "Server is shutting down....",
                                "",
                                "")
        );
    }
}
