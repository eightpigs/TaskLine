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
    private String name;
    /**
     * 该时间的任务
     */
    private List<TaskItem> taskItems;

    public HourNode() {  }

    public HourNode(String name, List<TaskItem> taskItems) {
        this.name = name;
        this.taskItems = taskItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }
}
