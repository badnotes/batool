package com.batool.io;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author jun.wan
 *
 */
public class CommonReader implements Runnable {

    private TextReader reader;
    private BlockingQueue<List<String>> queue;
    private int size = 0;
    private Consumer<Integer> callback;

    public CommonReader(String input, BlockingQueue<List<String>> queue, int batch, Consumer<Integer> callbackLineSize) throws FileNotFoundException {
        this.queue = queue;
        this.callback = callbackLineSize;
        this.reader = TextIO.read().withBatch(batch).from(input);
    }

    @Override
    public void run() {
        while (reader.hasNext()){
            try {
                List<String> data = reader.next();
                queue.offer(data, 10, TimeUnit.MINUTES);
                this.size += data.size();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.finish();
    }

    private void finish() {
        callback.accept(this.size);
    }
}
