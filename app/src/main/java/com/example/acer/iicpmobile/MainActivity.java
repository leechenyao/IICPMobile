package com.example.acer.iicpmobile;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.acer.iicpmobile.Fragment.Library;
import com.example.acer.iicpmobile.Fragment.NewsActivity;
import com.example.acer.iicpmobile.Fragment.ParkingSlots;
import com.example.acer.iicpmobile.Fragment.Profile;
import com.example.acer.iicpmobile.Fragment.Timetable;
import com.example.acer.iicpmobile.database.IICPDataBaseAdapter;

public class MainActivity extends AppCompatActivity {
    IICPDataBaseAdapter mIICPDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDummyData();

        if(SaveSharedPreference.getStudentId(MainActivity.this).isEmpty()) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_news));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_library));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_timetable));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_car));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_profile));
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.iicpRed), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().clearColorFilter();
        tabLayout.getTabAt(2).getIcon().clearColorFilter();
        tabLayout.getTabAt(3).getIcon().clearColorFilter();
        tabLayout.getTabAt(4).getIcon().clearColorFilter();
        setTitle(R.string.txtMainTab1_text); //Default first page
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.beginFakeDrag(); //Disable view pager swiping
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: return new NewsActivity();
                    case 1: return new Library();
                    case 2: return new Timetable();
                    case 3: return new ParkingSlots();
                    case 4: return new Profile();
                    default: return null;
                }
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);

                if(tab.getPosition() == 0) setTitle(R.string.txtMainTab1_text);
                else if(tab.getPosition() == 1) setTitle(R.string.txtMainTab2_text);
                else if(tab.getPosition() == 2) setTitle(R.string.txtMainTab3_text);
                else if(tab.getPosition() == 3) setTitle(R.string.txtMainTab4_text);
                else if(tab.getPosition() == 4) setTitle(R.string.txtMainTab5_text);
                else setTitle("");

                tab.getIcon().setColorFilter(getResources().getColor(R.color.iicpRed), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().clearColorFilter();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void createDummyData() {
        mIICPDataBaseAdapter = new IICPDataBaseAdapter(this);
        mIICPDataBaseAdapter = mIICPDataBaseAdapter.Open();
        ContentValues values;

        values = new ContentValues();
        values.put("student_id", "p14004433");
        values.put("password", "123456789");
        values.put("name", "Lee Chen Yao");
        values.put("school", "SOEAT");
        values.put("course", "BCSCU");
        values.put("semester", "6");
        values.put("phone_no", "012-3456789");
        mIICPDataBaseAdapter.Insert("student", values); // Insert into student table

        values = new ContentValues();
        values.put("news_title", "Student Evaluation Form");
        values.put("news_description", "Please kindly check Blackboard mainpage and complete all subjects evaluation that enrolled for January 2017 semester. Please complete the survey by this weekend...");
        values.put("news_date", "Feb 17, 2017");
        values.put("news_by", "IICP Staff");
        mIICPDataBaseAdapter.Insert("news", values); // Insert into news table

        values = new ContentValues();
        values.put("news_title", "Final Examination Timetable First Draft");
        values.put("news_description", "1st DRAFT of final examination timetable has been posted at notice board near Finance Office. Kindly communicate with exam unit if there is any clarification required...");
        values.put("news_date", "Feb 7, 2017");
        values.put("news_by", "IICP Staff");
        mIICPDataBaseAdapter.Insert("news", values); // Insert into news table

        values = new ContentValues();
        values.put("news_title", "Final Examination Briefing");
        values.put("news_description", "Final Examination Briefing will be conducted on Feb 21, 2017 (Tuesday) from 2:00PM to 3:00PM at Lecture Theater (level 5)...");
        values.put("news_date", "Feb 7, 2017");
        values.put("news_by", "IICP Staff");
        mIICPDataBaseAdapter.Insert("news", values); // Insert into news table

        values = new ContentValues();
        values.put("news_title", "Student Referral Programme");
        values.put("news_description", "Introduce a new student to study in IICP and you may receive reward range from RM500 to RM1500...");
        values.put("news_date", "Jan 20, 2017");
        values.put("news_by", "IICP Staff");
        mIICPDataBaseAdapter.Insert("news", values); // Insert into news table

        values = new ContentValues();
        values.put("news_title", "Get O365 Free");
        values.put("news_description", "Sign up your school email address at www.Office.com/GetOffice365. Simply INSTALL Office 365 on your device...");
        values.put("news_date", "Jan 1, 2017");
        values.put("news_by", "IICP Staff");
        mIICPDataBaseAdapter.Insert("news", values); // Insert into news table

        mIICPDataBaseAdapter.Close();
    }
}
