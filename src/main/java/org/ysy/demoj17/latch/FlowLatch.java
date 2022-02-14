package org.ysy.demoj17.latch;

public interface FlowLatch {

    void hold() throws InterruptedException;

    void release();
}
