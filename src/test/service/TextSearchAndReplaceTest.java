package test.service;

import main.service.impl.TextSearchAndReplace;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class TextSearchAndReplaceTest {

    @Test
    public void testProcess_replacesTextCorrectly() throws IOException {
        // Our Inputs
        String input = "That's an sql test";
        String expectedOutput = "That's a java test";
        String searchString = "an sql";
        String replaceString = "a java";

        //Our Instances
        TextSearchAndReplace processor = new TextSearchAndReplace();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();

        // Method Call
        processor.process(reader, writer, searchString, replaceString);
        // Parsing Output
        String actualOutput = writer.toString();

        // Testing current and expected results
        assertEquals(expectedOutput, actualOutput.trim());
    }

    @Test
    public void testProcess_noReplacementWhenSearchStringNotFound() throws IOException {
        // our Inputs
        String input = "That's an sql test";
        String expectedOutput = "That's an sql test";
        String searchString = "Python";
        String replaceString = "Java";

        // Creating Instances
        TextSearchAndReplace processor = new TextSearchAndReplace();
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();

        //Method Call
        processor.process(reader, writer, searchString, replaceString);
        // Parsing Output
        String actualOutput = writer.toString();

        // Testing Expected and current Results
        assertEquals(expectedOutput, actualOutput.trim());
    }

    @Test(expected = NullPointerException.class)
    public void testProcess_handlesNullInput() throws IOException {
        // Our inputs
        String searchString = "Python";
        String replaceString = "Java";
        //creating TextSearchAndReplace Instance
        TextSearchAndReplace processor = new TextSearchAndReplace();

        // Method Call with null inputs
        processor.process(null, null, searchString, replaceString);
    }
}