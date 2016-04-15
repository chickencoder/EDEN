package com.edenlang.reader;

import java.io.*;
import java.util.Scanner;

public class FileReader {
    private String path = "";
    private File file;

    public FileReader(String path) throws IOException {
        File f = new File(path);

        if (f.exists() && !f.isDirectory()) {
            this.path = path;
            this.file = f;
        } else {
            throw new IOException("File not found: " + path);
        }
    }

    public String readFully() throws IOException {
        InputStream stream = new FileInputStream(this.file);
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
