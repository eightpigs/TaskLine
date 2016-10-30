package me.lyinlong.taskline.widget.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.lyinlong.taskline.R;

import java.util.List;
import java.util.Map;

/**
 * 自定义RecyclerView的数据适配器
 * Created by ownde on 2016/10/30.
 */

public class TaskItemAdapter extends RecyclerView.Adapter {

    private List<Map<String, Object>> mItems;

    public TaskItemAdapter(List<Map<String, Object>> items){
        this.mItems = items;

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        /**
         * 时间
         */
        private TextView time;

        ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tvTaskItem_time);
        }

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_tasklist_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;
        String time = String.valueOf(mItems.get(position).get("time"));
        vh.getTime().setText(time);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}