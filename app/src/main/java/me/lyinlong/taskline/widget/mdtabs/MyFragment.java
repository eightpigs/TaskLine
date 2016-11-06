package me.lyinlong.taskline.widget.mdtabs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.lyinlong.taskline.R;
import me.lyinlong.taskline.widget.list.TaskItemAdapter;

import static me.lyinlong.taskline.R.id.view_clickAddTask;

public class MyFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;

    public static TextView mTv_addTaskBtn;

    public MyFragment() {
        // Required empty public constructor
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

        List<Map<String, Object>> taskLists = getTaskListByTime(2016,10,10);

        TaskItemAdapter adapter = new TaskItemAdapter(taskLists);
        // 所有的添加按钮
        final List<TextView> view_clickAddTaskBtns = new ArrayList<>();
            adapter.setOnItemClickListener(new TaskItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView tempTextView = (TextView)view.findViewById(view_clickAddTask);
                if(!view_clickAddTaskBtns.contains(tempTextView))
                    view_clickAddTaskBtns.add(tempTextView);
                for (TextView btn : view_clickAddTaskBtns) {
                    btn.setVisibility(View.INVISIBLE);
                }
                tempTextView.setVisibility(View.VISIBLE);

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
                       btn.setVisibility(View.INVISIBLE);
                   }
               }
           }
       });

        return view;
    }
    public List<Map<String, Object>> getTaskListByTime(int year , int month , int day){
        List<Map<String, Object>> list=new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            Map<String, Object> map=new HashMap<>();
            map.put("time", (i < 10 ? "0"+i : i)+":00");
            list.add(map);
        }
        return list;
    }


}