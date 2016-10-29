package me.lyinlong.taskline.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import me.lyinlong.taskline.AddItemActivity;
import me.lyinlong.taskline.R;

/**
 * 关于界面的工具类
 * Created by ownde on 2016/10/29.
 */

public class InterfaceUtils {

    /**
     * 弹出框
     * @param res       弹出框的自定义视图
     * @param title     标题
     * @param icon      图标
     */
    public static void show_cusAlertDialog(final Activity _this, int res, String title , Integer icon , Boolean positive, Boolean negative , final Integer type){

        final AlertDialog.Builder builder = new AlertDialog.Builder(_this);  //先得到构造器

        if(title != null)
            builder.setTitle(title);
        if(icon != null)
            builder.setIcon(icon);
        //  载入布局
        LayoutInflater inflater = _this.getLayoutInflater();
        final View layout = inflater.inflate(res,null);

        final DatePicker datePicker = (DatePicker)layout.findViewById(R.id.dpChooseDate);
        final TimePicker timePicker = (TimePicker)layout.findViewById(R.id.tpChooseTime);
        // 显示 positive 按钮
        if(positive != null && positive){
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(type != null){
                        if(type == 1 || type == 2) {  // 设置日期
                            TextView tv = type == 1 ? AddItemActivity.tvAddTaskStartTime : AddItemActivity.tvAddTaskEndTime;
                            tv.setText(datePicker.getYear() + "年" + (datePicker.getMonth() + 1) + "月" + datePicker.getDayOfMonth() + "日");
                            // 将标准时间格式存入tag
                            tv.setTag(99,datePicker.getYear()+"-"+(datePicker.getMonth()+1)+ datePicker.getDayOfMonth());

                        }else if(type == 3 || type == 4){ // 设置时间
                            TextView tv = type == 3 ? AddItemActivity.tvAddTaskStartHour : AddItemActivity.tvTaskEndHour;
                            String hour = String.valueOf(timePicker.getHour()).length() == 1 ? "0"+timePicker.getHour() : String.valueOf(timePicker.getHour());
                            String minute = String.valueOf(timePicker.getMinute()).length() == 1 ? "0"+timePicker.getMinute() : String.valueOf(timePicker.getMinute());
                            tv.setText(hour+":"+minute);
                            // 将标准时间格式存入tag
                            tv.setTag(99,hour+":"+minute);
                        }

                    }
                    dialog.dismiss();
                }
            });
        }

        // 显示 negative 按钮
        if(negative != null && negative){
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        builder.setView(layout);
        //  显示
        builder.create().show();
    }

}
