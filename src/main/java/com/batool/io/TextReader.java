package com.batool.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jun.wan
 * read data from file in batch
 */
public class TextReader implements Iterator<List<String>> {

    private int batchSize = -1;

    private BufferedReader br;

    private boolean hasNext = true;

    public TextReader withBatch(int batchSize){
        this.batchSize = batchSize;
        return this;
    }

    public TextReader from(String file) throws FileNotFoundException {
        this.br = new BufferedReader(new FileReader(file), 10 * 1024 * 1024);
        return this;
    }

    @Override
    public List<String> next(){

        List<String> lines = new ArrayList<>(batchSize > 0 ? batchSize : 100);
        String line = null;
        try {
            int size = batchSize;
            while ((batchSize < 0 || (batchSize > 0 && size -- > 0)) && (line = br.readLine()) != null) {
                lines.add(line);
            }
            if (line == null){
                this.hasNext = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (line == null){
                try {
                    this.br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    @Override
    public boolean hasNext(){
        return this.hasNext;
    }

}
