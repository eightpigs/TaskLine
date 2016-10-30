package me.lyinlong.taskline.widget.mdtabs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.lyinlong.taskline.R;
import me.lyinlong.taskline.widget.list.TaskItemAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;

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
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_taskList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Map<String, Object>> taskLists = getTaskListByTime(2016,10,10);

        TaskItemAdapter adapter_1 = new TaskItemAdapter(taskLists);

        mRecyclerView.setAdapter(adapter_1);

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