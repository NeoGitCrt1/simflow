package org.ysy.demoj17;

import java.util.Iterator;
import java.util.concurrent.ThreadPoolExecutor;

public class FlowExecutor {

    public static void start(Timeline timeline, ThreadPoolExecutor executor) {
        executor.submit(() -> {
            Iterator<Timeline.Node> iterator = timeline.iterator();
            while (iterator.hasNext()) {
                iterator.next().proceed();
            }
        });
    }

}
