package com.bychis.auth_tool.dao;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class dbXML extends Thread{

    static synchronized void writeInXMLFILE(Document doc){
        XMLOutputter outter=new XMLOutputter();
        outter.setFormat(Format.getPrettyFormat());
        try {
            outter.output(doc, new FileWriter(new File("policy.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static synchronized Document useSAXBuilder() {
        File inputFile = new File("policy.xml");
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
        try {
            document = saxBuilder.build(inputFile);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
