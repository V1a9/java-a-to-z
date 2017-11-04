package com.vgoryashko.sql.xmloptimizer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that transforms 1.xml file to 2.xml with 1.xst stylesheet.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 10/30/17
 */
public class XsltTransformer {

    private final Logger logger = LoggerFactory.getLogger(XsltTransformer.class);

    /**
     * Constant that refers to the 2.xsl file that is used for modification of the given 1.xml file.
     */
    private File fileStyle;

    /**
     * todo
     */
    private File fileOutput;


    public XsltTransformer() {
    }

    /**
     *todo
     */
    public void transform(File fileSource) {

        ReadConfig readConfig = new ReadConfig();
        ClassLoader loader = ReadConfig.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            readConfig.load(in);
            this.fileStyle = new File(readConfig.getProperty("style"));
            this.fileOutput = new File(readConfig.getProperty("dest"));
        } catch (IOException io) {
            io.printStackTrace();
        }

        if (fileOutput.exists()) {
            logger.trace(String.format("File %s exists.", this.fileOutput));
            fileOutput.delete();
            logger.trace(String.format("File %s has been removed.", this.fileOutput));
        }

        StreamSource source = new StreamSource(fileSource);
        StreamSource style = new StreamSource(this.fileStyle);
        StreamResult out = new StreamResult(this.fileOutput);
        TransformerFactory factory = TransformerFactory.newInstance();

        try {

            Transformer t = factory.newTransformer(style);
            t.transform(source, out);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
