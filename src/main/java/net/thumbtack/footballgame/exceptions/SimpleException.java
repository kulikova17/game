package net.thumbtack.footballgame.exceptions;

import java.util.Objects;

public class SimpleException extends Exception {
    private ErrorCode code;


    public SimpleException(ErrorCode code) {
        super();
        this.code = code;
    }

    public SimpleException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public SimpleException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public SimpleException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleException)) return false;
        SimpleException that = (SimpleException) o;
        return getCode() == that.getCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    @Override
    public String toString() {
        return "SimpleException{" +
                "code=" + code +
                '}';
    }
}
