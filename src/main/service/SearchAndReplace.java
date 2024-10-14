package main.service;

import java.io.*;

public interface SearchAndReplace {
    void process(Reader reader, Writer writer, String searchString, String replaceString) throws IOException;

}
