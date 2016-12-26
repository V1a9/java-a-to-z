package com.vgoryashko.checkbytestream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Class that checks byte stream.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 26/12/2016
 */
public class CheckByteStream {

    private InputStream input;

    public CheckByteStream(InputStream aInput) {
        this.input = aInput;
    }

    public boolean isNumber(InputStream aInput) {
        boolean result = false;

        return result;
    }

}
