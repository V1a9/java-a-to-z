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
 * @version 0.5
 * @since 8/27/17
 */
public class XmlParser {

    /**
     * Method that parses an order file.
     *
     * @param handler handler used for a file parsing
     * @param pathToFile path to a file to be parsed
     * @throws Exception Exception
     */
    public void parseFile(Path pathToFile, Handler handler) throws Exception {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();

        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(Files.newInputStream(pathToFile)));

    }
}