package org.ysy.demoj17.commander;

import org.ysy.demoj17.CmdPublisher;

import java.io.BufferedWriter;
import java.io.IOException;

public class ManualConfirmBtnCmd implements CmdPublisher {
    private String truckId;
    private String deviceId;
    private String btn;
    @Override
    public void init(String truckId, String deviceId, String[] argVal) {
        this.truckId = truckId;
        this.deviceId = deviceId;
        if (argVal.length < 1) {
            throw new IllegalArgumentException("argVal.length < 1");
        }
        this.btn = argVal[0];
    }

    @Override
    public void send(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(truckId);
        bufferedWriter.write("&");
        bufferedWriter.write(deviceId);
        bufferedWriter.write("&");
        bufferedWriter.write("press p " + btn);
        bufferedWriter.newLine();
    }
}
