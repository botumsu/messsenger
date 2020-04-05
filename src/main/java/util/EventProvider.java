package util;

import component.EventChannel;

import java.util.concurrent.Executors;

public class EventProvider {

    public static EventChannel staticEvent = new EventChannel(Executors.newFixedThreadPool(10));
}
