package com.datao.exception;


/**
 * Created by 海涛 on 2016/3/21.
 */
public class DataAccessException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public DataAccessException() {
    }

    public DataAccessException(String msg) {
        super(msg);
    }

    public DataAccessException(Throwable throable) {
        super(throable);
    }

    public DataAccessException(Throwable th, String str) {
        super(str, th);
    }


}
