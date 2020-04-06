package model;

import component.EventChannel;

import java.util.concurrent.ExecutorService;

public class EventChannelMock extends EventChannel {
    public EventChannelMock(ExecutorService executorService) {
        super(executorService);
    }
}
