package me.lyinlong.taskline.component.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import me.lyinlong.taskline.R;
import me.lyinlong.taskline.model.TaskItem;
import me.lyinlong.taskline.model.node.HourNode;
import me.lyinlong.taskline.utils.TimeUtils;

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
    private Map<String, HourNode> mItems;

    /**
     * 整体的任务
     * 24小时(必须24条记录)
     */
    private List<List<TaskItem>> allDayTasks ;

    public TaskItemAdapter(Map<String, HourNode> items, List<List<TaskItem>> allDayTasks){
        this.mItems = items;
        this.allDayTasks = allDayTasks;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private int ll_taskItems_height = 0;
    private int index = 0;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_tasklist_item,viewGroup,false);
//        TextView tvItemTime = (TextView)view.findViewById(R.id.tvTaskItem_time);
//        tvItemTime.setText(TimeUtils.getTimeByPosition(index++));
//        String hour = tvItemTime.getText().toString();

        // 整体的布局
//        RelativeLayout parent = (RelativeLayout) view;

        // 任务列表 & 小时时间的布局
//        RelativeLayout content = (RelativeLayout)parent.getChildAt(1);

        // 任务列表的布局
//        LinearLayout ll_taskItems = (LinearLayout) content.getChildAt(0);

        // 每个时间都重置任务列表的高度
//        ll_taskItems_height = 0;

//        List<TaskItem> taskItems = mItems.get(hour).getTaskItems();
//        if( taskItems != null && taskItems.size() > 0){
//            for (TaskItem taskItem : taskItems) {
//                ll_taskItems.addView(
//                        generateTaskItemView(ll_taskItems , taskItem.getContent() , taskItem.getToken()) , 0
//                );
//                ll_taskItems_height+= 200;
//            }
//        }

//        ll_taskItems_height = ll_taskItems_height == 0 ? 200 : ll_taskItems_height;
//
//        // 设置点击按钮的高度 = 任务列表的高度
//        TextView tv_addbtn = ((TextView)content.getChildAt(1));
//        tv_addbtn.setHeight(ll_taskItems_height - 35);
//
//        // 设置分割线的margin-top = 任务列表的高度 - 35
//        View v_dividingline = content.getChildAt(2);
//        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(v_dividingline.getLayoutParams());
//        ((RelativeLayout.LayoutParams) v_dividingline.getLayoutParams()).setMargins(margin.leftMargin , ll_taskItems_height - 35 , margin.rightMargin ,margin.bottomMargin);
//
//        // 设置小时时间的padding-top = 任务列表高度 -50
//        TextView tv_time = ((TextView)parent.getChildAt(0));
//        tv_time.setPadding( tv_time.getPaddingLeft() , ll_taskItems_height - 50 , tv_time.getPaddingRight() , tv_time.getPaddingBottom()  );

        // 由于在布局文件中,将整体布局margin-top:-10dp ,所以每个日期下面的第一个时间(00:00)会有部分没显示
        // 通过判断是否是第一个时间(00:00) , 设置padding-top:10;



        // 添加事件


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

        // 因为一天只有24小时，通过poseion直接转换为时间格式（00:00）
        String nowItemTime = TimeUtils.getTimeByPosition(position);
        vh.getTime().setText(nowItemTime);

        // 每个时间都重置任务列表的高度
        ll_taskItems_height = 0;

        List<TaskItem> taskItems = mItems.get(nowItemTime).getTaskItems();

        vh.tasks.removeAllViews();

        if(taskItems != null && taskItems.size() > 0){
            for (int i = 0; i < taskItems.size(); i++) {
                TaskItem item = taskItems.get(i);
                vh.getTasks().addView(generateTaskItemView(vh.tasks , item.getContent() , item.getToken()) , 0);
                ll_taskItems_height+= 200;
            }
        }

        ll_taskItems_height = ll_taskItems_height == 0 ? 150 : ll_taskItems_height;

        // 设置点击按钮的高度 = 任务列表的高度
        vh.tv_addTask.setHeight(ll_taskItems_height - 50);
//
        // 设置分割线的margin-top = 任务列表的高度 - 35
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(vh.view_excisionLine.getLayoutParams());
        ((RelativeLayout.LayoutParams) vh.view_excisionLine.getLayoutParams()).setMargins(margin.leftMargin , ll_taskItems_height - 35 , margin.rightMargin ,margin.bottomMargin);

       // 设置小时时间的padding-top = 任务列表高度 - 50
        vh.getTime().setPadding( vh.getTime().getPaddingLeft() , ll_taskItems_height - 60 , vh.getTime().getPaddingRight() , vh.getTime().getPaddingBottom()  );
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
        /**
         * 添加按钮
         */
        private TextView tv_addTask;
        /**
         * 任务列表
         */
        private LinearLayout tasks;
        /**
         * 每天的分割线
         */
        private View view_excisionLine;

        ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tvTaskItem_time);
            mViewClickAddTask = (RelativeLayout)itemView.findViewById(R.id.view_clickShowAdd);
            mViewClickAddTask.setOnClickListener(this);
            // 添加按钮
            tv_addTask = (TextView) itemView.findViewById(R.id.view_clickAddTask);
            tv_addTask.setOnClickListener(this);

            tasks = (LinearLayout)itemView.findViewById(R.id.ll_dayTaskList);
            view_excisionLine = itemView.findViewById(R.id.view_excisionLine);
        }

        public LinearLayout getTasks() {
            return tasks;
        }

        public TextView getTime() {
            return time;
        }

        public View getView_excisionLine() {
            return view_excisionLine;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(v, getAdapterPosition());
            }
        }
    }
}