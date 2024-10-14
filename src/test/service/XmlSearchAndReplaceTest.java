package test.service;

import main.service.impl.XmlSearchAndReplace;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class XmlSearchAndReplaceTest {

    @Test
    public void testProcess_replacesTextCorrectlyInXml() throws IOException {
        // Our inputs
        String input = "<note><to>Tove</to><from>abdallah</from><body>python</body></note>";
        String expectedOutput = "<note><to>Tove</to><from>abdallah</from><body>java</body></note>";
        String searchString = "python";
        String replaceString = "java";

        // Creating instances
        XmlSearchAndReplace processor = new XmlSearchAndReplace();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();

        // Method Call
        processor.process(reader, writer, searchString, replaceString);
        // parsing the output to String
        String actualOutput = writer.toString();

        // Assert
        assertEquals(expectedOutput, actualOutput.trim());
    }
}