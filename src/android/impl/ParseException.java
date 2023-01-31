package id.my.tauhidslab.mikrotik.api.impl;

import id.my.tauhidslab.mikrotik.api.MikrotikApiException;

/**
 * Exception thrown if the parser encounters an error while parsing a command line.
 * @author GideonLeGrange
 */
public class ParseException extends MikrotikApiException {

    ParseException(String msg) {
        super(msg);
    }

    ParseException(String msg, Throwable err) {
        super(msg, err);
    }
    
    
    
}
