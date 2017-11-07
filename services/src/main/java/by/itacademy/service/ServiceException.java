package by.itacademy.service;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
