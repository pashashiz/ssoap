package com.ps.ssoap;

import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.custommonkey.xmlunit.XMLUnit;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class XPathTest implements Runnable {

    private File file;

    public XPathTest(File file) {
        this.file = file;
    }

    public static String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            System.out.println("nodeToString Transformer Exception");
        }
        return sw.toString();
    }

    public static Document createDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory fileFactory = DocumentBuilderFactory.newInstance();
        fileFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = fileFactory.newDocumentBuilder();
        return builder.parse(file);
    }

    @Override
    public void run() {
        try {
            Document doc = createDocument(file);
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            try {
                XPathExpression expr = xpath.compile("//book[author='Neal Stephenson']/title");
                NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                for (int i = 0; i < nodes.getLength(); i++) {
                    System.out.println(nodeToString(nodes.item(i)));
                }
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }



}
