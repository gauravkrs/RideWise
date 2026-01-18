package com.airtribe.ridewise.exception;

public class NoAvailableDriverException extends Exception {

    public NoAvailableDriverException(String message) {
        super(message);
    }

    public NoAvailableDriverException() {
        super("No available drivers found for this ride request");
    }
}
