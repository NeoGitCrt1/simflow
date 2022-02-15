package org.ysy.demoj17;

import org.junit.jupiter.api.Test;
import org.ysy.demoj17.commander.PrepareTruckAndDriverCmd;
import org.ysy.demoj17.config.OrchestrationConfig;
import org.ysy.demoj17.latch.FlowLatch;
import org.ysy.demoj17.latch.thread.ThreadFlowLatch;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FlowExecutorTest {

    ExecutorService poolExecutor = Executors.newFixedThreadPool(1);

    @Test
    void p() throws IOException {


        Timeline timeline = new Timeline("127.0.0.1", 19960, new ThreadFlowLatch());

        timeline.register(new OrchestrationConfig.NodeConfig(
                "t1",
                "",
                "pre",
                false,
                0,
                "gross","30.4",
                "idcard","1401111233333",
                "plate","晋Axxaaa"
        ));

        timeline.register(new OrchestrationConfig.NodeConfig(
                "t1",
                "g0idreader",
                "idreader",
                false,
                3000));

        timeline.register(new OrchestrationConfig.NodeConfig(
                "t1",
                "g0w",
                "w",
                false,
                8000,
                "0", "4000", "16000", "100000"));

        timeline.register(new OrchestrationConfig.NodeConfig(
                "t1",
                "g0btn",
                "btn",
                false,
                4000,
                "giveup"));

        timeline.register(new OrchestrationConfig.NodeConfig(
                "",
                "",
                "",
                false,
                4000));

        FlowExecutor.start(timeline, (ThreadPoolExecutor) poolExecutor);
    }

}
