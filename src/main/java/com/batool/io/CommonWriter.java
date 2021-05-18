package com.batool.io;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author jun.wan
 *
 */
public class CommonWriter implements Runnable {

    private String output;
    private Queue<List<String>> queue;
    private boolean stop = false;
    private boolean append;

    public CommonWriter(String output, Queue<List<String>> queue) throws IOException {
        this(output, false, queue);
    }

    public CommonWriter(String output, boolean append, Queue<List<String>> queue) throws IOException {
        this.output = output;
        this.queue = queue;
        this.append = append;
    }

    @Override
    public void run() {
        try(TextWriter writer = TextIO.write().to(output, this.append)) {
            while (!stop){
                List<String> data = queue.poll();
                if (data == null){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                writer.with(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws InterruptedException {
        while (!queue.isEmpty()){
            TimeUnit.SECONDS.sleep(5);
        }
        this.stop = true;
    }
}
