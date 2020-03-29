package org.pietrzakp.proxyservice.infrastructure.dto;

public class MessageDTO {

    private String serviceName;
    private String result;
    private String message;

    public String getServiceName() {
        return serviceName;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}
