package me.lyinlong.taskline.widget.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.lyinlong.taskline.R;
import me.lyinlong.taskline.model.TaskItem;
import me.lyinlong.taskline.model.node.HourNode;

/**
 * 自定义RecyclerView的数据适配器
 * Created by ownde on 2016/10/30.
 */

public class TaskItemAdapter extends RecyclerView.Adapter {

    //define interface
    public static interface OnItemClickListener  {
        void onClick(View view , int position);
    }
    // 接口变量(click)
    private OnItemClickListener  onItemClickListener = null;

    /**
     * 每个小时的内容
     */
    private List<HourNode> mItems;

    /**
     * 整体的任务
     * 24小时(必须24条记录)
     */
    private List<List<TaskItem>> allDayTasks ;

    public TaskItemAdapter(List<HourNode> items , List<List<TaskItem>> allDayTasks){
        this.mItems = items;
        this.allDayTasks = allDayTasks;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private int ll_taskItems_height = 0;
    private int day_length = 0 ;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_tasklist_item,viewGroup,false);

        // 整体的布局
        RelativeLayout parent = (RelativeLayout) view;

        // 如果是23 ,重置为0
        day_length = day_length == 23 ? 0 : day_length;

        // 任务列表 & 小时时间的布局
        RelativeLayout content = (RelativeLayout)parent.getChildAt(1);

        // 任务列表的布局
        LinearLayout ll_taskItems = (LinearLayout) content.getChildAt(0);

        // 每个时间都重置任务列表的高度
        ll_taskItems_height = 0;

        // 依次添加数据
        for (TaskItem taskItem : allDayTasks.get(day_length)) {
            ll_taskItems.addView(
                    generateTaskItemView(ll_taskItems , taskItem.getName() , taskItem.getToken()) , 0
            );
            ll_taskItems_height+= 200;
        }

        // 设置点击按钮的高度 = 任务列表的高度
        TextView tv_addbtn = ((TextView)content.getChildAt(1));
        tv_addbtn.setHeight(ll_taskItems_height - 35);

        // 设置分割线的margin-top = 任务列表的高度 - 35
        View v_dividingline = content.getChildAt(2);
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(v_dividingline.getLayoutParams());
        ((RelativeLayout.LayoutParams) v_dividingline.getLayoutParams()).setMargins(margin.leftMargin , ll_taskItems_height - 35 , margin.rightMargin ,margin.bottomMargin);

        // 设置小时时间的padding-top = 任务列表高度 -50
        TextView tv_time = ((TextView)parent.getChildAt(0));
        tv_time.setPadding( tv_time.getPaddingLeft() , ll_taskItems_height - 50 , tv_time.getPaddingRight() , tv_time.getPaddingBottom()  );

        // 由于在布局文件中,将整体布局margin-top:-10dp ,所以每个日期下面的第一个时间(00:00)会有部分没显示
        // 通过判断是否是第一个时间(00:00) , 设置padding-top:10;

        return new ViewHolder(view);
    }

    /**
     * 生成当个任务视图
     * @param ll_taskItems
     * @return
     */
    private TextView generateTaskItemView(LinearLayout ll_taskItems, String content , String token ){
        TextView tv_Item = new TextView(ll_taskItems.getContext());
        tv_Item.setBackground(ll_taskItems.getResources().getDrawable(R.drawable.corners_border_by_taskitem));
        tv_Item.setTextColor(ll_taskItems.getResources().getColor(R.color.colorWhite));
        tv_Item.setPadding(10,10,10,10);
        tv_Item.setText(content);
        tv_Item.setTag(token);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , 150);
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(layoutParams);
        layoutParams.setMargins(margin.leftMargin , margin.topMargin , margin.rightMargin , 40);
        tv_Item.setLayoutParams(layoutParams);
        return tv_Item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;
        HourNode node = mItems.get(position);
        vh.getTime().setText(node.getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         * 时间
         */
        private TextView time;
        private RelativeLayout mViewClickAddTask;

        ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tvTaskItem_time);
            mViewClickAddTask = (RelativeLayout)itemView.findViewById(R.id.view_clickShowAdd);
            mViewClickAddTask.setOnClickListener(this);
        }

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(v, getAdapterPosition());
            }
        }
    }
}