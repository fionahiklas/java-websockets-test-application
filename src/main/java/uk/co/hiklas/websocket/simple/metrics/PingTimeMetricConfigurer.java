package uk.co.hiklas.websocket.simple.metrics;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PingTimeMetricConfigurer {

    @Bean
    @Qualifier("pingTime")
    public DistributionSummary configureMetric(MeterRegistry registry) {
        return registry.summary("PingTime");
    }
}
