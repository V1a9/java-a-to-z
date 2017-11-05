package com.vgoryashko.sql.xmloptimizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
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
 * @version 0.4
 * @since 11/05/17
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
     * Constructor for the class.
     */
    public XsltTransformer() {
    }

    /**
     * Method that performs transformation of the 1.xml to 2.xml.
     */
    public void transform(File fileSource) {

        ReadConfig readConfig = new ReadConfig();
        ClassLoader loader = ReadConfig.class.getClassLoader();
        ClassLoader classLoader = this.getClass().getClassLoader();

//        try (InputStream readProperties = loader.getResourceAsStream("app.properties");
//             InputStream reader = classLoader.getResourceAsStream("2.xsl");
//             FileWriter writer = new FileWriter("2.xsl.tmp")) {
//
//            int c;
//
//            while ((c = reader.read()) != -1) {
//
//                writer.write(c);
//
//            }
//
//            readConfig.load(readProperties);
//
//            this.fileStyle = new File("2.xsl.tmp");
//            this.fileOutput = new File(readConfig.getProperty("dest"));
//
//        } catch (IOException io) {
//            io.printStackTrace();
//        }

        InputStream readProperties = null;
        InputStream reader = null;
        FileWriter writer = null;
        try {
            readProperties = loader.getResourceAsStream("app.properties");
            reader = classLoader.getResourceAsStream("2.xsl");
            writer = new FileWriter("2.xsl.tmp");

            int c;

            while ((c = reader.read()) != -1) {

                writer.write(c);

            }

            readConfig.load(readProperties);

            this.fileStyle = new File("2.xsl.tmp");
            this.fileOutput = new File(readConfig.getProperty("dest"));

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (reader != null && readProperties != null && writer != null) {
                try {
                    reader.close();
                    readProperties.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
            this.fileStyle.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
