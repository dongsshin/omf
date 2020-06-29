package com.rap.omc.framework.exception;



public class WSClientException extends BaseException {

    private static final long serialVersionUID = -7515042547275141976L;

    public WSClientException(String message) {
        super(message);
    }

    public WSClientException() {
       super();
    }
}