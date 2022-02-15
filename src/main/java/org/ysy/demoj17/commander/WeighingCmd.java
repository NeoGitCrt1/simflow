package org.ysy.demoj17.commander;

import org.ysy.demoj17.CmdPublisher;

import java.io.BufferedWriter;
import java.io.IOException;

public class WeighingCmd implements CmdPublisher {
    private  String[] d;
    private  String truckId;
    private  String deviceId;

    @Override
    public void init(String truckId, String deviceId, String[] d) {
        this.truckId = truckId;
        this.deviceId = deviceId;
        if (d.length < 4) {
            throw new IllegalArgumentException("d.length < 4");
        }
        this.d = d;
    }

    @Override
    public void send(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(truckId);
        bufferedWriter.write("&");
        bufferedWriter.write(deviceId);
        bufferedWriter.write("&");
        bufferedWriter.write("prepare -d0 "+ d[0] +" -d1 " + d[1] + " -d2 " + d[2] + " -d3 "+ d[3]);
    }
}
