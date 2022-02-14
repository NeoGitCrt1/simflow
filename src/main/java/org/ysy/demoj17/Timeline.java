package org.ysy.demoj17;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Timeline {

    private final List<Node> nodes = new ArrayList<>();

    private int idx = -1;
    public Node next() {
        idx++;
        return nodes.get(idx);
    }

    public void register(Node node) {
        nodes.add(node);
    }

    static class Node{

        final Duration duration;

        final CmdPublisher commander;

        Node(Duration duration, CmdPublisher commander) {
            this.duration = duration;
            this.commander = commander;
        }

        void proceed() throws InterruptedException {
            commander.send();
            TimeUnit.MILLISECONDS.sleep(duration.toMillis());
        }

    }
}
