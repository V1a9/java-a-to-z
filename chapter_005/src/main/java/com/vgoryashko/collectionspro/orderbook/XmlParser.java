package com.vgoryashko.collectionspro.orderbook;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that parses a given xml file.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 7/20/17
 */
public class XmlParser {

    /**
     * Method that parses an order file.
     */
    public void parseFile(Path pathToFile) throws Exception {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();

        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new Handler());
        xmlReader.parse(new InputSource(Files.newInputStream(pathToFile)));

    }


}
