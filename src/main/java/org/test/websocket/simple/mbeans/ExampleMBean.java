package org.test.websocket.simple.mbeans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@Slf4j
@ManagedResource
public class ExampleMBean {

    private Integer totalCount;
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
