package org.ysy.demoj17.latch.thread;

import org.ysy.demoj17.latch.FlowLatch;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadFlowLatch implements FlowLatch {

    private final BlockingQueue<Boolean> channel = new ArrayBlockingQueue<>(1);

    @Override
    public void hold() {
        try {
            channel.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void release() {
        channel.offer(Boolean.TRUE);
    }
}
