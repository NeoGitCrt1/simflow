package org.ysy.demoj17.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ysy.demoj17.latch.FlowLatch;
import org.ysy.demoj17.latch.process.ProcessFlowLatch;
import org.ysy.demoj17.latch.thread.ThreadFlowLatch;

import java.io.IOException;

@Configuration
@ConditionalOnProperty(value = "run.mode", havingValue = "standalone")
public class InProcessConfig {
    @Bean
    public FlowLatch flowLatch() throws IOException {
        return new ProcessFlowLatch("127.0.0.1", 8008);
    }
}
