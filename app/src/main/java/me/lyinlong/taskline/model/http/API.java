package me.lyinlong.taskline.model.http;

/**
 * API对象
 * Created by ep on 2017/2/11.
 */

public class API {

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求的类型
     */
    private String method;
    /**
     * 是否需要返回的结果
     */
    public Boolean needResult;

    public API() {}

    public API(String url, String method, Boolean needResult) {
        this.url = url;
        this.method = method;
        this.needResult = needResult;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getNeedResult() {
        return needResult;
    }

    public void setNeedResult(Boolean needResult) {
        this.needResult = needResult;
    }
}
