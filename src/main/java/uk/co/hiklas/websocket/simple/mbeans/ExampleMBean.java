package uk.co.hiklas.websocket.simple.mbeans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ManagedResource
public class ExampleMBean {

    private Integer totalCount = 0;
    private String message = "Default message";

    @ManagedAttribute
    public Integer getTotalCount() {
        return this.totalCount;
    }


    @ManagedOperation
    public void logMessage() {
        totalCount++;
        log.debug("MBean log message: {}", message);
    }
}
