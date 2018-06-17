package jSimpleDB.TestApp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    String fileName;
    File file;

    public FileEventLogger() {
    }

//    public FileEventLogger(File file) {
//        this.file = file;
//    }
//
//    public FileEventLogger(String fileName) {
//        this.fileName = fileName;
//    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\r\n", "utf-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException{
        this.file = new File(fileName);
        if (!file.canWrite()) {throw new IOException();}
    }
}
