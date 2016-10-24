package me.lyinlong.taskline.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.lyinlong.taskline.MainActivity;
import me.lyinlong.taskline.R;
import me.lyinlong.taskline.utils.BitmapUtils;

/**
 * 自定义CalendarView
 * 参考：https://github.com/Airsaid/CalendarView
 */
public class CalendarView extends View {

    /**
     * 列的数量
     */
    private static final int NUM_COLUMNS    =   7;
    /**
     * 行的数量
     */
    private static final int NUM_ROWS       =   6;

    /**
     * 拥有任务的日期
     */
    private List<String> taskDates;

    /**
     * 以选日期数据
     */
    private List<String> mSelectedDates = new ArrayList<>();


    /**
     * 日期已存在任务的背景色
     */
    private int hasTaskBGColor = Color.argb(200,99, 219, 255);
    /**
     * 日期已存在任务的日期字体颜色
     */
    private int hasTaskTextColor = Color.parseColor("#FFFFFF");
    /**
     * 默认日期字体颜色
     */
    private int normalTextColor = Color.parseColor("#555555");
    /**
     * 默认日期背景颜色
     */
    private int normalBGColor = Color.parseColor("#F7F7F7");


    /**
     * 天数字体大小
     */
    private int mDayTextSize = 14;
    /**
     * 是否可以被点击状态
     */
    private boolean mClickable = true;

    private DisplayMetrics mMetrics;
    private Paint mPaint;
    private int mCurYear;
    private int mCurMonth;
    private int mCurDate;

    private int mSelYear;
    private int mSelMonth;
    private int mSelDate;
    private int mColumnSize;
    private int mRowSize;
    private int[][] mDays;

    // 当月一共有多少天
    private int mMonthDays;
    // 当月第一天位于周几
    private int mWeekNumber;

    /**
     * 有任务的背景
     */
    private Bitmap bitmap_task;

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 获取手机屏幕参数
        mMetrics = getResources().getDisplayMetrics();
        // 创建画笔
        mPaint = new Paint();
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        mCurYear    =   calendar.get(Calendar.YEAR);
        mCurMonth   =   calendar.get(Calendar.MONTH);
        mCurDate    =   calendar.get(Calendar.DATE);
        setSelYTD(mCurYear, mCurMonth, mCurDate);

        // 有任务的背景
//        bitmap_task = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
//        bitmap_task = BitmapUtils.getResizedBitmap(bitmap_task ,80 , 80);
        // 未选中背景
//        mBgNotOptBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    public void invalidate() {
        // 避免程序过度绘制
        if(hasWindowFocus()) super.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();

        // 绘制背景
        mPaint.setColor(normalBGColor);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);

        mDays = new int[6][7];
        // 设置绘制字体大小
        mPaint.setTextSize(mDayTextSize * mMetrics.scaledDensity);
        // 设置绘制字体颜色

        String dayStr;
        // 获取当月一共有多少天
        mMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth);
        // 获取当月第一天位于周几
        mWeekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth);

        for(int day = 0; day < mMonthDays; day++){
            dayStr = String.valueOf(day + 1);
            int column  =  (day + mWeekNumber - 1) % 7;
            int row     =  (day + mWeekNumber - 1) / 7;
            mDays[row][column] = day + 1;
            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayStr)) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);

            // 判断当前是否已经有任务
            if(taskDates.contains(getSelData(mSelYear, mSelMonth, mDays[row][column]))){
                // 该日期已存在任务信息
//                if(!mSelectedDates.contains(getSelData(mSelYear, mSelMonth, mDays[row][column]))){
//                    // 绘制背景颜色
//                    mPaint.setColor(hasTaskBGColor);
//                    canvas.drawCircle(startX + 20.8f,startY - 25 ,60,mPaint);
//                    mPaint.setColor(hasTaskTextColor);
//                }else{
////                    canvas.drawBitmap(bitmap_task, startX - 22, startY - 55, mPaint);
//                    mPaint.setColor(Color.RED);
//                }
                // 绘制背景颜色
                mPaint.setColor(hasTaskBGColor);
                canvas.drawCircle(startX + 20.8f,startY - 25 ,60,mPaint);
                mPaint.setColor(hasTaskTextColor);
                // 绘制天数
                canvas.drawText(dayStr, startX, startY - 10, mPaint);
            }else{
                mPaint.setColor(normalTextColor);
                canvas.drawText(dayStr, startX, startY, mPaint);
            }
        }
    }

    private int downX = 0,downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventCode = event.getAction();
        switch(eventCode){
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if(!mClickable) return true;

                int upX = (int) event.getX();

                String []nowMouth = null;
                // 滑动
                if(upX - downX >= 100) {
                    setLastMonth();
                }else if(upX - downX <= -100){
                    setNextMonth();
                }
                nowMouth = getDate().split("-");
                MainActivity.tvSelectDateYear.setText(nowMouth[0] + " / ");
                MainActivity.tvSelectDateMouth.setText(nowMouth[1]);
                // 点击
                int upY = (int) event.getY();
                if(Math.abs(upX - downX) < 10 && Math.abs(upY - downY) < 10){
                    performClick();
                    onClick((upX + downX) / 2, (upY + downY) / 2);
                }

                break;
        }
        return true;
    }

    /**
     * 点击事件
     * @param x
     * @param y
     */
    private void onClick(int x, int y){
        int row = y / mRowSize;
        int column = x / mColumnSize;
        setSelYTD(mSelYear, mSelMonth, mDays[row][column]);

        // 判断是否点击过
        boolean isSelected = mSelectedDates.contains(getSelData(mSelYear, mSelMonth, mSelDate));
        if(isSelected){
            mSelectedDates.remove(getSelData(mSelYear, mSelMonth, mSelDate));
        }else{
            mSelectedDates.add(getSelData(mSelYear, mSelMonth, mSelDate));
        }

        invalidate();
        if(mListener != null){
            // 执行回调
            mListener.onClickDateListener(mSelYear, (mSelMonth + 1), mSelDate);
        }
    }

    /**
     * 初始化列宽和高
     */
    private void initSize() {
        // 初始化每列的大小
        mColumnSize = getWidth() / NUM_COLUMNS;
        // 初始化每行的大小
        mRowSize = getHeight() / NUM_ROWS;
    }

    public void setTaskDates(List<String> taskDates) {
        this.taskDates = taskDates;
    }

    /**
     * 设置年月日
     * @param year
     * @param month
     * @param date
     */
    public void setSelYTD(int year, int month, int date){
        this.mSelYear   =   year;
        this.mSelMonth  =   month;
        this.mSelDate   =   date;
    }

    /**
     * 设置上一个月日历
     */
    public void setLastMonth(){
        int year    =   mSelYear;
        int month   =   mSelMonth;
        int day     =   mSelDate;
        // 如果是1月份，则变成12月份
        if(month == 0){
            year = mSelYear-1;
            month = 11;
        }else if(DateUtils.getMonthDays(year, month) == day){
            //　如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month-1;
            day = DateUtils.getMonthDays(year, month);
        }else{
            month = month-1;
        }
        setSelYTD(year,month,day);
        invalidate();
    }

    /**
     * 设置下一个日历
     */
    public void setNextMonth(){
        int year    =   mSelYear;
        int month   =   mSelMonth;
        int day     =   mSelDate;
        // 如果是12月份，则变成1月份
        if(month == 11){
            year = mSelYear+1;
            month = 0;
        }else if(DateUtils.getMonthDays(year, month) == day){
            //　如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month + 1;
            day = DateUtils.getMonthDays(year, month);
        }else{
            month = month + 1;
        }
        setSelYTD(year,month,day);
        invalidate();
    }

    /**
     * 获取当前展示的年和月份
     * @return
     */
    public String getDate(){
        String data;
        if((mSelMonth + 1) < 10){
            data = mSelYear + "-0" + (mSelMonth + 1);
        }else{
            data = mSelYear + "-" + (mSelMonth + 1);
        }
        return data;
    }

    /**
     * 获取当前展示的日期
     * @param year
     * @param month
     * @param date
     * @return
     */
    private String getSelData(int year, int month, int date){
        String monty, day;
        month = (month + 1);

        // 判断月份是否有非0情况
        if((month) < 10) {
            monty = "0" + month;
        }else{
            monty = String.valueOf(month);
        }

        // 判断天数是否有非0情况
        if((date) < 10){
            day = "0" + (date);
        }else{
            day = String.valueOf(date);
        }
        return year + monty + day;
    }

    /**
     * 获取已选日期数据
     * @return
     */
    public List<String> getSelectedDates(){
        return mSelectedDates;
    }

    /**
     * 设置已选日期数据
     * @param dates
     */
    public void setSelectedDates(List<String> dates){
        this.mSelectedDates = dates;
    }

    /**
     * 设置日历是否可以点击
     * @param clickable
     */
    @Override
    public void setClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    private OnClickListener mListener;

    public interface OnClickListener{
        void onClickDateListener(int year, int month, int day);
    }

    /**
     * 设置点击回调
     * @param listener
     */
    public void setOnClickDate(OnClickListener listener){
        this.mListener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recyclerBitmap(bitmap_task);
//        recyclerBitmap(mBgNotOptBitmap);
    }

    /** * 释放Bitmap资源 */
    private void recyclerBitmap(Bitmap bitmap) {
        if(bitmap != null && !bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}