package com.vitaliif.busroutechalange.exception;

public class IllegalBusStationFileException extends RuntimeException {

    public IllegalBusStationFileException(String message) {
        super(message);
    }

    public IllegalBusStationFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
