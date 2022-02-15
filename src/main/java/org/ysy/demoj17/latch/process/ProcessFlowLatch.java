package org.ysy.demoj17.latch.process;

import org.ysy.demoj17.latch.FlowLatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ProcessFlowLatch implements FlowLatch {

    private final DatagramSocket socket;
    public ProcessFlowLatch(int serverport) throws IOException {
        socket = new DatagramSocket(serverport);
    }

    public ProcessFlowLatch(String serverIp, int serverport) throws IOException {
        try {
            socket = new DatagramSocket();
            socket.connect(InetAddress.getByName(serverIp), serverport);
        } catch (SocketException | UnknownHostException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void hold() {
        DatagramPacket packet = new DatagramPacket(new byte[1], 1);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void release() {
        byte[] data = new byte[1];
        data[0] = 1;
        DatagramPacket packet = new DatagramPacket(data, 1);
        try {
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
