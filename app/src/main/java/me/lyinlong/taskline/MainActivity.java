package me.lyinlong.taskline;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import me.lyinlong.taskline.widget.mdtabs.MyFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyFragment(), "ONE");
        adapter.addFragment(new MyFragment(), "TWO");
        adapter.addFragment(new MyFragment(), "1");
        adapter.addFragment(new MyFragment(), "2");
        adapter.addFragment(new MyFragment(), "3");
        adapter.addFragment(new MyFragment(), "4");
        adapter.addFragment(new MyFragment(), "5");
        adapter.addFragment(new MyFragment(), "6");
        adapter.addFragment(new MyFragment(), "7");
        adapter.addFragment(new MyFragment(), "8");
        adapter.addFragment(new MyFragment(), "9");
        adapter.addFragment(new MyFragment(), "0");
        adapter.addFragment(new MyFragment(), "11");
        adapter.addFragment(new MyFragment(), "12");
        adapter.addFragment(new MyFragment(), "13");
        adapter.addFragment(new MyFragment(), "14");
        adapter.addFragment(new MyFragment(), "15");
        adapter.addFragment(new MyFragment(), "16");
        adapter.addFragment(new MyFragment(), "17");
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