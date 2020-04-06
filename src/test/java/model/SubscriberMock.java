package model;

import component.EventChannel;
import component.Subscriber;
import util.EventUpdater;

public class SubscriberMock extends Subscriber {
    public SubscriberMock(EventUpdater eventUpdater, EventChannel eventChannel) {
        super(eventUpdater, eventChannel);
    }
}
