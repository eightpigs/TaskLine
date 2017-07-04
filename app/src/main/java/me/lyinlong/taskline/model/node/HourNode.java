package me.lyinlong.taskline.model.node;

import java.util.List;

import me.lyinlong.taskline.model.TaskItem;

/**
 * 小时节点(每小时的对象)
 * Created by ownde on 2016/11/7.
 */

public class HourNode {

    /**
     * 时间值
     */
    private String hour;
    /**
     * 该时间的任务
     */
    private List<TaskItem> taskItems;

    public HourNode() {  }

    public HourNode(String hour, List<TaskItem> taskItems) {
        this.hour = hour;
        this.taskItems = taskItems;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }
}
