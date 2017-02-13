package me.lyinlong.taskline.model;

/**
 * 任务项
 * Created by ownde on 2016/11/7.
 */

public class TaskItem {

    /**
     * 任务唯一标识
     */
    private String token;
    /**
     * 任务名称
     */
    private String content;
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
    private Boolean allDay;
    /**
     * 提前提醒时间(单位:分)
     */
    private Integer advance;
    /**
     * 执行地址
     */
    private String address;

    public TaskItem() {  }

    public TaskItem(String token, String content, String intro, String createTime, String startTime, String endTime, Boolean allDay, Integer advance, String address) {
        this.token = token;
        this.content = content;
        this.intro = intro;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.allDay = allDay;
        this.advance = advance;
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAdvance() {
        return advance;
    }

    public void setAdvance(Integer advance) {
        this.advance = advance;
    }
}
