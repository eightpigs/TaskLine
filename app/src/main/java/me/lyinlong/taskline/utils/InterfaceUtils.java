package me.lyinlong.taskline.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import me.lyinlong.taskline.AddItemActivity;
import me.lyinlong.taskline.MainActivity;
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
     * @param _this     要弹出的视图对象
     * @param positive  是否需要positive按钮
     * @param negative  是否需要negative按钮
     * @param type      弹出的类型以及后期处理: 1,2 添加任务时弹出任务起始日期以及任务结束日期选择; 6 首页选择查看任务的日期选择 ; 3,4 添加任务时弹出任务的起始时间以及结束事件选择
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
                        // 根据类型调用每个视图自定义的后续操作
                        if(type == 1 || type == 2){
                            ((AddItemActivity)_this).dialogContinue(datePicker.getYear()  , datePicker.getMonth() + 1 ,  datePicker.getDayOfMonth(), null , null , type );
                        }else if( type == 6){
                            ((MainActivity)_this).dialogContinue(datePicker.getYear()  , datePicker.getMonth() + 1 ,  datePicker.getDayOfMonth() );
                        }else if(type == 3 || type == 4){
                            String hour = String.valueOf(timePicker.getHour()).length() == 1 ? "0"+timePicker.getHour() : String.valueOf(timePicker.getHour());
                            String minute = String.valueOf(timePicker.getMinute()).length() == 1 ? "0"+timePicker.getMinute() : String.valueOf(timePicker.getMinute());
                            ((AddItemActivity)_this).dialogContinue(null  , null , null, hour , minute , type );
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
