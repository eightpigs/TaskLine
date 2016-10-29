package me.lyinlong.taskline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import me.lyinlong.taskline.utils.InterfaceUtils;
import me.lyinlong.taskline.utils.TimeUtils;

public class AddItemActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;

    // 任务的开始日期
    public static TextView tvAddTaskStartTime;
    // 任务的结束日期
    public static TextView tvAddTaskEndTime;
    // 任务开始时间
    public static TextView tvAddTaskStartHour;
    // 任务结束时间
    public static TextView tvTaskEndHour;
    // 日期选择
    private DatePicker mDatePicker;
    // 时间选择
    private TimePicker mTimePicker;

    public static Toast toast;
    public static AddItemActivity _this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // 该页面的对象
        _this = this;

        gestureDetector = new GestureDetector(this.getApplicationContext(), new DoubleClickView());

        tvAddTaskStartTime = (TextView)findViewById(R.id.tv_addTask_startTime);
        tvAddTaskEndTime = (TextView)findViewById(R.id.tv_addTask_endTime);
        tvAddTaskStartHour = (TextView)findViewById(R.id.tv_addTask_startHour);
        tvTaskEndHour = (TextView)findViewById(R.id.tv_addTask_endHour);
        mDatePicker = (DatePicker)findViewById(R.id.dpChooseDate);
        mTimePicker = (TimePicker)findViewById(R.id.tpChooseTime);

        // 设置默认选择的日期时间
        setNormalDateTime();

//        mDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toast.makeText(AddItemActivity._this, mDatePicker.getDayOfMonth() ,Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    /**
     * 进入添加页面设置默认选择的日期时间
     */
    private void setNormalDateTime(){
        // 获取时间
        String nowTime = TimeUtils.getNowTime(Constants.DATE_FORMAT_yyyy年MM月dd日HH时mm分);
        String endTime = (String)TimeUtils.getTimeByCons(10,1,nowTime,false,Constants.DATE_FORMAT_yyyy年MM月dd日HH时mm分);

        String nowTImeSplit[] = nowTime.split(" ");
        String endTimeSplit[] = endTime.split(" ");
        tvAddTaskStartTime.setText(nowTImeSplit[0]);
        tvAddTaskStartHour.setText(nowTImeSplit[1]);
        tvAddTaskEndTime.setText(endTimeSplit[0]);
        tvTaskEndHour.setText(endTimeSplit[1]);

        setDateTimeOnClick(tvAddTaskStartTime,1,null);
        setDateTimeOnClick(tvAddTaskEndTime,2,null);
        setDateTimeOnClick(tvAddTaskStartHour,3,2);
        setDateTimeOnClick(tvTaskEndHour,4,2);

    }

    /**
     * 日期选择弹出框方法
     * @param view      具体需要选择日期的对象
     * @param type      相应的type
     */
    private void setDateTimeOnClick(TextView view , final Integer type , final Integer chooseType){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 默认日期选择控件
                int res = R.layout.layout_date_choose;

                if(chooseType != null && chooseType == 2)
                    res = R.layout.layout_time_choose;

                InterfaceUtils.show_cusAlertDialog(AddItemActivity.this,res,null ,null,true , true , type);
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //Single Tap
        return gestureDetector.onTouchEvent(e);
    }

}

/**
 * 双击实现
 */
class DoubleClickView extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
    // event when double tap occurs
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        AddItemActivity.toast.makeText(AddItemActivity._this,"已保存",Toast.LENGTH_SHORT).show();
        return true;
    }
}