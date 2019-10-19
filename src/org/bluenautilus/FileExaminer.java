package org.bluenautilus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class FileExaminer {
    private final File theFile;
    private final int bigEnough = 80;

    public FileExaminer(File f) {
        theFile = f;
    }

    public void readFileChunks(Consumer<String> consumer) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(theFile))) {
            String line;
            StringBuilder collectedLines = new StringBuilder();

            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()){
                    collectedLines.append(line + '\n');
                }

                if (collectedLines.length() >= bigEnough) {
                    consumer.accept(removeStupidDots(collectedLines.toString()));
                    collectedLines.setLength(0);
                }
            }
        }
    }

    private String removeStupidDots(String line) {
        line = line.replace(". . . .", "...");
        line = line.replace(". . .", "...");
        return line;
    }
}
       
       
       
      
       
           
          
