package com.edenlang.fileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class FileWriter {
    private String path = "";
    private File file;

    public FileWriter(String path) throws IOException {
        File f = new File(path);

        if (f.exists() || f.isDirectory()) {
            throw new IOException("File already exists: " + path);
        } else {
            this.path = path;
            this.file = f;
        }
    }

    public void writeFile(String data) throws IOException {
        OutputStream stream = new FileOutputStream(this.file);
        stream.write(data.getBytes(Charset.forName("UTF-8")));
        stream.close();
    }

    public String getPath() {
        return this.path;
    }
}
