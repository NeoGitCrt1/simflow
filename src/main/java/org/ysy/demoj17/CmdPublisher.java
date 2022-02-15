package org.ysy.demoj17;

import java.io.BufferedWriter;
import java.io.IOException;

public interface CmdPublisher {

    void init(String truckId, String deviceId, String[] argVal);

    void send(BufferedWriter bufferedWriter) throws IOException;
}
