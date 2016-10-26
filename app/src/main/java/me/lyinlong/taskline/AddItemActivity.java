package me.lyinlong.taskline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.lyinlong.taskline.utils.TimeUtils;

public class AddItemActivity extends AppCompatActivity {

    /**
     * 任务的开始日期
     */
    private TextView tvAddTaskStartTime;
    /**
     * 任务的结束日期
     */
    private TextView tvAddTaskEndTime;
    /**
     * 任务开始时间
     */
    private TextView tvAddTaskStartHour;
    /**
     * 任务结束时间
     */
    private TextView tvTaskEndHour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        tvAddTaskStartTime = (TextView)findViewById(R.id.tv_addTask_startTime);
        tvAddTaskEndTime = (TextView)findViewById(R.id.tv_addTask_endTime);
        tvAddTaskStartHour = (TextView)findViewById(R.id.tv_addTask_startHour);
        tvTaskEndHour = (TextView)findViewById(R.id.tv_addTask_endHour);

        // 获取时间
        String nowTime = TimeUtils.getNowTime(Constants.DATE_FORMAT_yyyy年MM月dd日HH时mm分);
        String endTime = (String)TimeUtils.getTimeByCons(10,1,nowTime,false,Constants.DATE_FORMAT_yyyy年MM月dd日HH时mm分);

        String nowTImeSplit[] = nowTime.split(" ");
        String endTimeSplit[] = endTime.split(" ");
        tvAddTaskStartTime.setText(nowTImeSplit[0]);
        tvAddTaskStartHour.setText(nowTImeSplit[1]);
        tvAddTaskEndTime.setText(endTimeSplit[0]);
        tvTaskEndHour.setText(endTimeSplit[1]);



    }
}
