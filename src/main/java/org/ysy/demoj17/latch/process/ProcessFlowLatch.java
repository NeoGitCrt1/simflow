package org.ysy.demoj17.latch.process;

import org.ysy.demoj17.latch.FlowLatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ProcessFlowLatch implements FlowLatch {

    final BufferedReader br;
    public ProcessFlowLatch(String serverIp, int serverport) throws IOException {
        Socket socket = new Socket(serverIp,serverport);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void hold() throws InterruptedException {

        try {
            String mess = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void release() {
        throw new UnsupportedOperationException();
    }
}
