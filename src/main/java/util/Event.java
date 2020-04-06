package util;

import java.io.Serializable;

public class Event implements Serializable {
    private static final long serialVersionUID = 4594793394556223539L;

    private String message;

    public Event(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
