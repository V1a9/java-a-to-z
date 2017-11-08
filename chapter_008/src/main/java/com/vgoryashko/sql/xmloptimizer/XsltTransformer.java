package com.vgoryashko.sql.xmloptimizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that transforms 1.xml file to 2.xml with 1.xst stylesheet.
 *
 * @author Vlad Goryashko
 * @version 0.8
 * @since 11/06/17
 */
public class XsltTransformer {

    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(XsltTransformer.class);

    /**
     * Constant that refers to the 2.xsl file that is used for modification of the given 1.xml file.
     */
    private File fileStyle;

    /**
     * Variable that refers to the path of 2.xml.
     */
    private File fileOutput;

    /**
     * Variable that refers to the properties file.
     */
    private String properties;

    /**
     * Variable that refers to the tmp file used for creating a copy of 1.xsl.
     */
    private String tmpFile;

    /**
     * Constructor for the class.
     */
    public XsltTransformer(String properties, String tmpFile) {

        this.properties = properties;
        this.tmpFile = tmpFile;
    }

    /**
     * Method that performs transformation of the 1.xml to 2.xml.
     */
    public void transform(File fileSource) {

        ReadConfig readConfig = new ReadConfig();
        ClassLoader loader = ReadConfig.class.getClassLoader();
        ClassLoader classLoader = this.getClass().getClassLoader();

        try (InputStream readProperties = loader.getResourceAsStream(this.properties);
             InputStream reader = classLoader.getResourceAsStream("2.xsl");
             FileWriter writer = new FileWriter(this.tmpFile)) {

            int c;

            while ((c = reader.read()) != -1) {
                writer.write(c);
            }

            writer.flush();
            readConfig.load(readProperties);

            this.fileStyle = new File("2.xsl.tmp");
            this.fileOutput = new File(readConfig.getProperty("dest"));

        } catch (IOException io) {
            io.printStackTrace();
        }

        StreamSource source = new StreamSource(fileSource);
        StreamSource style = new StreamSource(this.fileStyle);
        StreamResult out = new StreamResult(this.fileOutput);
        TransformerFactory factory = TransformerFactory.newInstance();

        try {

            Transformer t = factory.newTransformer(style);
            t.transform(source, out);
            this.fileStyle.delete();
            logger.debug("Transformed 2.xml has been written.");

        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }
}
