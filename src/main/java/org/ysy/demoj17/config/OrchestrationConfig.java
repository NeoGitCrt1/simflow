package org.ysy.demoj17.config;

import org.ysy.demoj17.commander.Commander;

import java.util.ArrayList;
import java.util.List;

public class OrchestrationConfig {

    public final List<NodeConfig> nodeConfigs = new ArrayList<>();

    public static class NodeConfig {

        public final   String truckId;
        public final String deviceId;
        public final Commander cmdPublisherType;

        public final boolean isManualControl;

        public final long proceedDurationMilli;

        public final String[] argVal;

        public NodeConfig(String truckId, String deviceId, Commander cmdPublisherType, boolean isManualControl, long proceedDurationMilli, String... argVal) {
            this.truckId = truckId;
            this.deviceId = deviceId;
            this.cmdPublisherType = cmdPublisherType;
            this.isManualControl = isManualControl;
            this.proceedDurationMilli = proceedDurationMilli;
            this.argVal = argVal;
        }
    }

}
