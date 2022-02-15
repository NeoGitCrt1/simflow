package org.ysy.demoj17.commander;

import org.ysy.demoj17.CmdPublisher;

import java.io.BufferedWriter;
import java.io.IOException;

public class IdReaderCmd implements CmdPublisher {

    private  String truckId;
    private  String deviceId;

    @Override
    public void init(String truckId, String deviceId, String[] argVal) {
        this.truckId = truckId;
        this.deviceId = deviceId;
    }

    @Override
    public void send(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(truckId);
        bufferedWriter.write("&");
        bufferedWriter.write(deviceId);
        bufferedWriter.write("&");
        bufferedWriter.write("swipe");
    }
}
