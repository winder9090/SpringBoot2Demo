package jingweng.demo.springboot2.exception;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.exception
 * @className: UnauthorizedException
 * @author:
 * @description: TODO
 * @date: 2023/3/15 13:29
 * @version: 1.0
 */
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public UnauthorizedException(int code) {
        this.code = code;
        this.msg = "error";
    }

    public UnauthorizedException(int code, String... params) {
        this.code = code;
        this.msg = "error";
    }

    public UnauthorizedException(int code, Throwable e) {
        super(e);
        this.code = code;
        this.msg = "error";
    }

    public UnauthorizedException(int code, Throwable e, String... params) {
        super(e);
        this.code = code;
        this.msg = "error";
    }

    public UnauthorizedException(String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
    }

    public UnauthorizedException(String msg, Throwable e) {
        super(msg, e);
        this.code = 500;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
