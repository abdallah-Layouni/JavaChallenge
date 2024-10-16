package main.service.impl;

import main.service.SearchAndReplace;

import java.io.*;

public class TextSearchAndReplace implements SearchAndReplace {
    @Override
    public void process(Reader reader, Writer writer, String searchString, String replaceString) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String line;
        boolean firstLine = true;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.replace(searchString, replaceString);
            if (!firstLine) {
                bufferedWriter.newLine();
            } else {
                firstLine = false;
            }
            bufferedWriter.write(line);
        }

        bufferedWriter.flush();
    }

}
