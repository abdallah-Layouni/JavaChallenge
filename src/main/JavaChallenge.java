package main;
import main.service.SearchAndReplace;
import main.service.impl.SearchAndReplaceFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JavaChallenge {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("4 arguments are required <file-path> <file-type> <search-string> <replace-string>");
            return;
        }
        String filePath = args[0];
        String type = args[1];
        String searchString = args[2];
        String replaceString = args[3];
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }
        SearchAndReplace processor = SearchAndReplaceFactory.getProcessor(type);
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        // Reading from the original file and write to the temporary file
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Processing the file content
            processor.process(reader, writer, searchString, replaceString);
        } catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
            return;
        }
        // Closing the reader and writer to ensure all data is written before replacing the file
        // Attempting to replace the original file with the temporary file
        try {
            // Deleting the original file and rename the temporary file to the original file
            Files.delete(file.toPath());
            Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File successfully processed and updated: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to replace the original file: " + e.getMessage());
        }
    }
}