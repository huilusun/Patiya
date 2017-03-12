package com.huiju.blackbrin.exception;

/**
 * 异常处理基类
 */
public class BaseException extends RuntimeException {
    protected String info;
    protected int errorCode = 500;

    public BaseException(Throwable t, String info, int errorCode) {
        super(t.getMessage(), t);
        this.info = info;
        this.errorCode = errorCode;
    }

    public BaseException(String info, int errorCode) {
        super(info);
        this.info = info;
        this.errorCode = errorCode;
    }

    public BaseException(String info) {
        super("");
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
