package me.lyinlong.taskline.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;

import me.lyinlong.taskline.config.Constants;
import me.lyinlong.taskline.model.http.ResponseBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHttp的封装使用
 * Created by ep on 2017/2/11.
 */

public class OkHttpUtils {

    /**
     * 同步发送请求
     * @param url   URL，参数也在这
     * @return
     * @throws IOException
     */
    public static ResponseBody asyncGet(String url, Map<String, String> params) {

        OkHttpClient client = Constants.OK_HTTP_CLIENT;
        Request request = new Request.Builder()
                .url(assemblyParams(url, params))
                .build();
        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()){
                return JSONObject.parseObject(response.body().string(), ResponseBody.class);
            }

        }catch (Exception e){
            return new ResponseBody("错误", 0l);
        }
        return new ResponseBody("错误", 0l);

    }

    /**
     * 组装参数
     * @param params    参数Map
     * @return
     */
    private static String assemblyParams(String url, Map<String, String> params){
        StringBuffer buffer = new StringBuffer(url);
        if(params != null && params.keySet().size() > 0){
            buffer.append("?");
            for (String name : params.keySet()) {
                buffer.append(name);
                buffer.append("=");
                buffer.append(params.get(name));
            }
        }
        return buffer.toString();
    }

}
