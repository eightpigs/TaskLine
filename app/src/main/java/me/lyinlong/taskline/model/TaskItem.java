package me.lyinlong.taskline.model;

/**
 * 任务项
 * Created by ownde on 2016/11/7.
 */

public class TaskItem {

    /**
     * 任务唯一标识
     */
    private Integer token;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务说明
     */
    private String intro;
    /**
     * 任务创建时间
     */
    private String createTime;
    /**
     * 任务开始时间
     */
    private String startTime;
    /**
     * 任务结束时间
     */
    private String endTime;
    /**
     * 是否是全天
     */
    private Boolean isAddDay;
    /**
     * 提前提醒时间(单位:分)
     */
    private Integer beforehandWarnTime;

    public TaskItem() {  }

    public TaskItem(Integer token, String name, String intro, String createTime, String startTime, String endTime, Boolean isAddDay, Integer beforehandWarnTime) {
        this.token = token;
        this.name = name;
        this.intro = intro;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAddDay = isAddDay;
        this.beforehandWarnTime = beforehandWarnTime;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getAddDay() {
        return isAddDay;
    }

    public void setAddDay(Boolean addDay) {
        isAddDay = addDay;
    }

    public Integer getBeforehandWarnTime() {
        return beforehandWarnTime;
    }

    public void setBeforehandWarnTime(Integer beforehandWarnTime) {
        this.beforehandWarnTime = beforehandWarnTime;
    }
}
