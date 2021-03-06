package org.ysy.demoj17;

import org.ysy.demoj17.config.OrchestrationConfig;
import org.ysy.demoj17.latch.FlowLatch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Timeline extends ArrayList<Timeline.Node> {

    private final BufferedWriter bufferedWriter;
    private final FlowLatch flowLatch;
    public Timeline(String host, int port, FlowLatch flowLatch) throws IOException {
        this.flowLatch = flowLatch;
        Socket s = new Socket(host,port);
        OutputStream os = s.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        this.bufferedWriter = bw;
    }

    public void register(OrchestrationConfig.NodeConfig nodeConfig) {
        CmdPublisher cmdPublisher = null;
        if (nodeConfig.cmdPublisherType == null) {
            cmdPublisher = new CmdPublisher() {
                @Override
                public void init(String truckId, String deviceId, String[] argVal) {

                }

                @Override
                public void send(BufferedWriter bufferedWriter) throws IOException {

                }
            };
        } else {
            try {
                cmdPublisher = (CmdPublisher) nodeConfig.cmdPublisherType.clz.getConstructors()[0].newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }


        cmdPublisher.init(nodeConfig.truckId, nodeConfig.deviceId, nodeConfig.argVal);
        this.add(new Node(Duration.ofMillis(nodeConfig.proceedDurationMilli),
                cmdPublisher,
                nodeConfig.isManualControl
                ));
    }

    class Node{
        private final Duration duration;
        private final CmdPublisher commander;
        private final boolean isManualControl;


        Node(Duration duration, CmdPublisher commander, boolean isManualControl) {
            this.duration = duration;
            this.commander = commander;
            this.isManualControl = isManualControl;
        }

        void proceed(){
            try {
                if (isManualControl) {
                    flowLatch.hold();
                }
                commander.send(bufferedWriter);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                TimeUnit.MILLISECONDS.sleep(duration.toMillis());

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
