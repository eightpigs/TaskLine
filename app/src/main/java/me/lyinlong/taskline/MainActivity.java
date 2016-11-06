package me.lyinlong.taskline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.lyinlong.taskline.utils.InterfaceUtils;
import me.lyinlong.taskline.utils.TimeUtils;
import me.lyinlong.taskline.widget.calendarview.DateUtils;
import me.lyinlong.taskline.widget.mdtabs.MyFragment;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    // tabs对应的内容
    private ViewPager viewPager;

    // 当前日期
    public TextView mtvNowDateTime;
    private FloatingActionButton mbtnAddTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            mtvNowDateTime = (TextView)findViewById(R.id.tvNowDateTime);
            mbtnAddTask = (FloatingActionButton)findViewById(R.id.btnAddTask);

            String time = TimeUtils.getNowTime("yyyy年MM月");
            // 设置当前时间
            mtvNowDateTime.setText(time);

            // 初始化布局
            initView();

            //事件
            mtvNowDateTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InterfaceUtils.show_cusAlertDialog(MainActivity.this, R.layout.layout_date_choose ,null ,null,true , true , 6);
                }
            });
            mbtnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, AddItemActivity.class);
                    startActivity(intent);
                }
            });


        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     * 调用日期控件之后的后续操作
     * @param year      日期控件返回的年份
     * @param month     返回的月份
     * @param day       返回的具体天
     */
    public void dialogContinue(Integer year , Integer month , Integer day){
        mtvNowDateTime.setText(year+"年"+month+"月");
        tabLayout.getTabAt(day-1).select();
    }

    /**
     * 初始化布局
     */
    private void initView(){
        // 设置工具类
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // 禁用返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setScrollbarFadingEnabled(true);
        viewPager.setHorizontalScrollBarEnabled(true);
        viewPager.setVerticalScrollBarEnabled(true);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // 默认选中指定日期
        tabLayout.getTabAt(TimeUtils.getNowDay()-1).select();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        String [] nowDate = TimeUtils.getNowTime("yyyy-MM").split("-");
        // 默认获取本月最大的一天
        int maxDay = DateUtils.getMonthDays(Integer.valueOf(nowDate[0]),Integer.valueOf(nowDate[1])-1);
        // 生成天数
        for (int i = 1; i <= maxDay; i++) {
            adapter.addFragment(new MyFragment(), String.valueOf(i));
        }
        // 设置
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}