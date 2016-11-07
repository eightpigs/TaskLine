package me.lyinlong.taskline.widget.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.lyinlong.taskline.R;
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

    private List<HourNode> mItems;

    public TaskItemAdapter(List<HourNode> items){
        this.mItems = items;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_tasklist_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;
        HourNode node = mItems.get(position);
        vh.getTime().setText(node.getName());
        // 绘制每个小时的任务内容
//        RelativeLayout parent = (RelativeLayout)vh.getTime().getParent();
//        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(parent.getChildAt(1).getLayoutParams());
//        margin.setMargins(margin.leftMargin , 10 , margin.rightMargin , margin.bottomMargin);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
//        parent.getChildAt(1).setLayoutParams(layoutParams);
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