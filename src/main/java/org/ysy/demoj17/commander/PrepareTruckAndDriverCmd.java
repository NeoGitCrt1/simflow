package org.ysy.demoj17.commander;

import org.ysy.demoj17.CmdPublisher;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PrepareTruckAndDriverCmd implements CmdPublisher {
    private  String truckId;
    private final Map<String, String> properties = new HashMap<>();

    @Override
    public void init(String truckId, String deviceId, String[] cmdArgs) {
        this.truckId = truckId;
        if ((cmdArgs.length & 1) == 0) {
            throw new IllegalArgumentException("argVal is not key>val pair");
        }
        for (int i = 0; i < cmdArgs.length; i += 2) {
            if (i + 1 >= cmdArgs.length) {
                break;
            }
            properties.put(cmdArgs[i], cmdArgs[i + 1]);
        }

    }

    @Override
    public void send(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(truckId);
        bufferedWriter.write("&");
        bufferedWriter.write("&");
        StringBuilder stringBuilder = new StringBuilder("prepare ");
        for (Map.Entry<String, String> entry: properties.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" ").append(entry.getValue()).append(" ");
        }
        bufferedWriter.write(stringBuilder.toString());
    }
}
