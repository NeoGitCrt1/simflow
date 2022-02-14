package org.ysy.demoj17.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ysy.demoj17.latch.FlowLatch;
import org.ysy.demoj17.latch.thread.ThreadFlowLatch;

@Configuration
@ConditionalOnProperty(value = "run.mode", havingValue = "embed")
public class OnThreadConfig {

    @Bean
    public FlowLatch flowLatch(){
        return new ThreadFlowLatch();
    }

}

