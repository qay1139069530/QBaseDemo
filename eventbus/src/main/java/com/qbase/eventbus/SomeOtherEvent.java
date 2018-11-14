package com.qbase.eventbus;

public class SomeOtherEvent {

    private String message;

    public SomeOtherEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
