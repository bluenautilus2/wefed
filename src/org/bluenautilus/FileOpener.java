package org.bluenautilus;

import java.io.File;

public class FileOpener {

    public File getFile(String filename){
        return new File(
                getClass().getClassLoader().getResource("resources/" + filename).getFile()
        );
    }
}
