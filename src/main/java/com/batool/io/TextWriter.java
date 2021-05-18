package com.batool.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * @author jun.wan
 * write data to file
 */
public class TextWriter implements AutoCloseable {

    private BufferedWriter bw;

    public TextWriter to(String file) throws IOException {
        return to(file, false);
    }

    public TextWriter to(String file, boolean append) throws IOException {
        if (!append && Files.exists(Paths.get(file))){
            Files.move(Paths.get(file), Paths.get(file + ".bak"), StandardCopyOption.REPLACE_EXISTING);
        }
        this.bw = new BufferedWriter(new FileWriter(file, append), 10 * 1024 * 1024);
        return this;
    }

    public TextWriter with(List<String> data) throws IOException {
        for (String v : data) {
            bw.write(v.concat("\n"));
        }
        bw.flush();
        return this;
    }

    @Override
    public void close() throws IOException {
        bw.close();
    }
}
