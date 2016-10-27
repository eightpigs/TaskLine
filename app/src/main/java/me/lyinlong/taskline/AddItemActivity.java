package me.lyinlong.taskline;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.lyinlong.taskline.utils.TimeUtils;

public class AddItemActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
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

    public static Toast toast;
    public static AddItemActivity _this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        _this = this;

        gestureDetector = new GestureDetector(this.getApplicationContext(), new DoubleClickView());

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

        tvAddTaskStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cusAlertDialog(R.layout.layout_date_choose);
            }
        });

        tvAddTaskStartHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cusAlertDialog(R.layout.layout_time_choose);
            }
        });

    }

    private void show_cusAlertDialog(int res){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);  //先得到构造器
//        builder.setTitle("自定义dialog");             //设置标题
//        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可

        //  载入布局
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(res,null);
        builder.setView(layout);
        //  显示
        builder.create().show();
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