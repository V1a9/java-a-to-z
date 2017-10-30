package com.vgoryashko.sql.xmloptimizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Class that transforms 1.xml file to 2.xml with 1.xst stylesheet.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 10/30/17
 */
public class XsltTransformer {

    private final Logger logger = LogManager.getLogger(XsltTransformer.class);

    /**
     * Constant that defines file separator string value.
     */
    private static final String FS = System.getProperty("file.separator");

    /**
     * Constant that refers to the 2.xsl file that is used for modification of the given 1.xml file.
     */
    private final File fileStyle = new File(String.format(".%schapter_008%ssrc%smain%sresources%s2.xsl", FS, FS, FS, FS, FS));

    /**
     * todo
     */
    private final File fileOutput = new File(String.format(".%schapter_008%ssrc%smain%sresources%s2.xml", FS, FS, FS, FS, FS));

    /**
     *todo
     */
    public void transform(File fileSource) {

        if (fileOutput.exists()) {
            logger.trace(String.format("File %s exists.", this.fileOutput));
            fileOutput.delete();
            logger.trace(String.format("File %s has been removed.", this.fileOutput));
        }

        StreamSource source = new StreamSource(fileSource);
        StreamSource style = new StreamSource(fileStyle);
        StreamResult out = new StreamResult(fileOutput);
        TransformerFactory factory = TransformerFactory.newInstance();

        try {

            Transformer t = factory.newTransformer(style);
            t.transform(source, out);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
