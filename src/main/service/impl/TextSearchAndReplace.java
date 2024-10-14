package main.service.impl;

import main.service.SearchAndReplace;

import java.io.*;

public class TextSearchAndReplace implements SearchAndReplace {
    @Override
    public void process(Reader reader, Writer writer, String searchString, String replaceString) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.replace(searchString, replaceString);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
    }
}
