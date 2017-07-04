package me.lyinlong.taskline;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.lyinlong.taskline.component.calendarview.CalendarView;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener  {

    /**
     * 当前年份
     */
    public static TextView tvSelectDateYear;
    /**
     * 当前月份
     */
    public static TextView tvSelectDateMouth;

    private CalendarView mCalendarView;

    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        // 添加 Todo的自定义对话框构造器
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);

        tvSelectDateYear = (TextView) findViewById(R.id.tvSelectDateYear);
        tvSelectDateMouth = (TextView) findViewById(R.id.tvSelectDateMouth);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);


        // 初始化可选日期
        initData();

        // 设置可选日期
        mCalendarView.setTaskDates(mDatas);

        // 设置点击事件
        mCalendarView.setOnClickDate(new CalendarView.OnClickListener() {
            @Override
            public void onClickDateListener(int year, int month, int day) {
//                Toast.makeText(getApplication(), year + "年" + month + "月" + day + "天", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(CalendarActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        tvSelectDateYear.setText(mCalendarView.getDate().split("-")[0]+" / ");
        tvSelectDateMouth.setText(mCalendarView.getDate().split("-")[1]);

    }


    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add("20161022");
        mDatas.add("20161021");
        mDatas.add("20161023");
        mDatas.add("20161024");
        mDatas.add("20161027");
        mDatas.add("20161028");
        mDatas.add("20161019");
        mDatas.add("20161016");
        mDatas.add("20161014");
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.img_select_last_month:
//                mCalendarView.setLastMonth();
//                tvSelectDate.setText(mCalendarView.getDate());
//                break;
//            case R.id.img_select_next_month:
//                mCalendarView.setNextMonth();
//                tvSelectDate.setText(mCalendarView.getDate());
//                break;
//        }
    }
}
