package main.service.impl;

import main.service.SearchAndReplace;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlSearchAndReplace implements SearchAndReplace {
    @Override
    public void process(Reader reader, Writer writer, String searchString, String replaceString) throws IOException {
        try {
            // Loading and parsing the Xml file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(reader));
            // Normalizing the XML structure
            doc.getDocumentElement().normalize();
            // Replacing values
            replaceAttributeInNodes(doc.getDocumentElement(), searchString, replaceString);
            // Writing the updated XML to the writer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            // Creating a DOMSource and StreamResult
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(writer);
            // Transforming the DOM object into the output stream
            transformer.transform(source, result);
            writer.flush();
        } catch (Exception e) {
            throw new IOException("Error processing XML", e);
        }
    }

    // Helper method to replace text in nodes
    private void replaceAttributeInNodes(Node node, String searchString, String replaceString) {
        if (node.hasAttributes()) {
            NamedNodeMap attributes = node.getAttributes();
            // Looping through all attributes of the node
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                String attrValue = attribute.getNodeValue();
                if (attrValue.contains(searchString)) {
                    // Replacing the matching text in the attribute's value
                    String newValue = attrValue.replace(searchString, replaceString);
                    attribute.setNodeValue(newValue);
                }
            }
        }
        // Recursively checking child nodes
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            replaceAttributeInNodes(childNodes.item(i), searchString, replaceString);
        }
    }

}