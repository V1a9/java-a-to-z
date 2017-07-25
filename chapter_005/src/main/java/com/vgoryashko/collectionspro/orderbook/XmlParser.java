package com.vgoryashko.collectionspro.orderbook;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

/**
 * Class that parses a given xml file.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 7/25/17
 */
public class XmlParser {

    /**
     * Method that parses an order file.
     */
    public Collection parseFile(Path pathToFile) throws Exception {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();

        Handler handler = new Handler();

        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(Files.newInputStream(pathToFile)));

        return handler.getCollection();

    }


}
