package org.test.websocket.simple.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Slf4j
@Configuration
public class PingerThreadPoolExecutorConfigurer {

    @Bean(name="PingerThreadPool")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        log.debug("Configuring Pinger thread pool");
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("PingerThreadPool");
        return threadPoolTaskScheduler;
    }
}
