package me.lyinlong.taskline.widget.mdtabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.lyinlong.taskline.AddItemActivity;
import me.lyinlong.taskline.R;
import me.lyinlong.taskline.model.TaskItem;
import me.lyinlong.taskline.model.node.HourNode;
import me.lyinlong.taskline.widget.list.TaskItemAdapter;

import static me.lyinlong.taskline.R.id.view_clickAddTask;

public class MyFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;

    /**
     * 当天的任务列表
     */
    private List<TaskItem> todayTaskItems;

    public MyFragment() {  }

    public List<TaskItem> getTodayTaskItems() {
        return todayTaskItems;
    }

    public void setTodayTaskItems(List<TaskItem> todayTaskItems) {
        this.todayTaskItems = todayTaskItems;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_one, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_taskList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<HourNode> taskLists = getTaskListByTime(2016,10,10);

        List<List<TaskItem>> allDayTasks = new ArrayList<>();
        List<TaskItem> taskItems = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            allDayTasks.add(
                    new ArrayList<TaskItem>(){{
                        add(new TaskItem(
                                UUID.randomUUID().toString() ,
                                "测试任务内容" ,
                                "任务的详细说明",
                                "2016-10-01",
                                "2016-10-10",
                                "2016-11-11",
                                true ,
                                10
                        ));
                    }}
            );
        }


        TaskItemAdapter adapter = new TaskItemAdapter(taskLists , allDayTasks);

        // 所有的添加按钮
        final List<TextView> view_clickAddTaskBtns = new ArrayList<>();
            adapter.setOnItemClickListener(new TaskItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                // TextView和RelativeLayout 都使用了本自定义事件, 如果view强转为TextView失败,则view是RelativeLayout
                try{
                    // 点击添加按钮 , 添加任务
                    TextView tv_addTask = (TextView)view;
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), AddItemActivity.class);
                    startActivity(intent);

                }catch (Exception e){
                    // 显示添加按钮
                    // 获取添加按钮控件
                    TextView tempTextView = (TextView)view.findViewById(view_clickAddTask);

                    // 如果该控件没有在所有的控件列表中(控件列表用于快速做单选显示)
                    if(!view_clickAddTaskBtns.contains(tempTextView))
                        view_clickAddTaskBtns.add(tempTextView);
                    // 把以前选中的都隐藏
                    for (TextView btn : view_clickAddTaskBtns) {
                        btn.setVisibility(View.GONE);
                    }

                    // 显示当前时间的添加按钮
                    tempTextView.setVisibility(View.VISIBLE);

                    // 隐藏当前时间的所有任务
                }
            }
        });

        mRecyclerView.setAdapter(adapter);

       mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
               // 滚动时隐藏按钮
               if(view_clickAddTaskBtns.size() > 0){
                   // 遍历隐藏所有
                   for (TextView btn : view_clickAddTaskBtns) {
                       btn.setVisibility(View.GONE);
                   }
               }
           }
       });

        return view;
    }
    public List<HourNode> getTaskListByTime(int year , int month , int day){
        List<HourNode> list = new ArrayList<>();

        List<TaskItem> items = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            HourNode node = new HourNode();
            node.setName( (i < 10 ? "0"+i : i)+":00" );
            node.setTaskItems(items);
            list.add(node);
        }
        return list;
    }


}