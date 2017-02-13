package me.lyinlong.taskline.model.http;

/**
 * 请求的响应内容
 * Created by ep on 2017/2/11.
 */

public class ResponseBody {

    /**
     * 响应的消息
     */
    private String msg;

    /**
     * 响应的代码
     */
    private Long code;

    public ResponseBody() {}

    public ResponseBody(String msg, Long code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
