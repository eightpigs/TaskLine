package me.lyinlong.taskline.config;

import me.lyinlong.taskline.model.http.API;

/**
 * 服务器相关的API
 * Created by ep on 2017/2/11.
 */

public class ServerAPI {

    /**
     * 添加单条任务的API
     * key：API url地址
     * val: 该请求的请求类型
     */
    public static API API_ADD_TASK_ITEM = new API("http://10.16.231.44:3000/task/add", "get", true);

}
