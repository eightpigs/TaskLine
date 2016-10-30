package me.lyinlong.taskline;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import me.lyinlong.taskline.utils.InterfaceUtils;
import me.lyinlong.taskline.utils.KeyboradUtils;
import me.lyinlong.taskline.utils.TimeUtils;

import static me.lyinlong.taskline.AddItemActivity._this;

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
    // 提醒时间
    private EditText metRemind;

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
        metRemind = (EditText)findViewById(R.id.etRemind);

        // 设置默认选择的日期时间
        setNormalDateTime();
       metRemind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                   KeyboradUtils.showOrHide(AddItemActivity.this);
                   // 保存操作

                   return true;
               }
               return false;
           }
       });
    }

    /**
     * 调用日期/时间选择的后续操作
     * @param year      日期控件返回的年份
     * @param month     返回的月份
     * @param day       具体的天
     * @param hour      时间控件返回的小时
     * @param minute    时间控件返回的分钟
     * @param type      调用时传入的type
     */
    public void dialogContinue(Integer year , Integer month , Integer day , String hour , String minute , Integer type){
        if(type == 1 || type == 2){
            TextView tv_temp = type == 1 ? tvAddTaskStartTime : tvAddTaskEndTime;
            tv_temp.setText(year+"年"+month+"月"+day+"日");
        }else if(type == 3 || type == 4){
            TextView tv_temp = type == 1 ? tvAddTaskStartHour : tvTaskEndHour;
            tv_temp.setText(hour+":"+minute);
        }
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
        AddItemActivity.toast.makeText(_this,"已保存",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
//        AddItemActivity.toast.makeText(AddItemActivity._this,"长按",Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity._this);  //先得到构造器
        builder.setMessage("确定要删除该任务吗？")
                .setCancelable(false)
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        AddItemActivity.toast.makeText(AddItemActivity._this,"任务已删除",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", null);
        builder.create().show();
        super.onLongPress(e);
    }
}