package test.service;

import main.service.SearchAndReplace;
import main.service.impl.SearchAndReplaceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchAndReplaceFactoryTest {

    @Test
    public void testGetProcessor_returnsTextProcessor() {
        // Creating Instance
        SearchAndReplace processor = SearchAndReplaceFactory.getProcessor("txt");

        // Assert
        assertNotNull(processor);
        assertEquals("main.service.impl.TextSearchAndReplace", processor.getClass().getName());
    }

    @Test
    public void testGetProcessor_returnsXmlProcessor() {
        // Creating Instance
        SearchAndReplace processor = SearchAndReplaceFactory.getProcessor("xml");

        // Assert
        assertNotNull(processor);
        assertEquals("main.service.impl.XmlSearchAndReplace", processor.getClass().getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetProcessor_throwsExceptionForUnknownType() {
        // Act
        SearchAndReplaceFactory.getProcessor("unknown");
    }
}