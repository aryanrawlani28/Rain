package com.aryan.rain.events;

public class Event {

    // Add more here once you have new type of events.
    public enum Type {
        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_MOVED
    }

    private Type type;
    boolean handled;

    protected Event(Type type) {
        this.type = type;
    }


    public Type getType() {
        return type;
    }


}
