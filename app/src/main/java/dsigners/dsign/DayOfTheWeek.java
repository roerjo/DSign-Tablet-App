package dsigners.dsign;

import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class DayOfTheWeek extends AppCompatActivity {
    Button exit;
    Boolean sundayBool = SyncSettings.getInstance().getWeekendMode();


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_of_the_week);
        exit = (Button)findViewById(R.id.exit2);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DayOfTheWeek.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_of_the_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            if (sundayBool == false) {
                switch (position) {
                    case 0:
                        TabMonday mon = new TabMonday();
                        return mon;
                    case 1:
                        TabTuesday tues = new TabTuesday();
                        return tues;
                    case 2:
                        TabWednesday wed = new TabWednesday();
                        return wed;
                    case 3:
                        TabThursday thurs = new TabThursday();
                        return thurs;
                    case 4:
                        TabFriday fri = new TabFriday();
                        return fri;
                    default:
                        return null;
                }
            }
            else if (sundayBool == true) {
                switch (position) {
                    case 0:
                        TabSunday sun = new TabSunday();
                        return sun;
                    case 1:
                        TabMonday mon = new TabMonday();
                        return mon;
                    case 2:
                        TabTuesday tues = new TabTuesday();
                        return tues;
                    case 3:
                        TabWednesday wed = new TabWednesday();
                        return wed;
                    case 4:
                        TabThursday thurs = new TabThursday();
                        return thurs;
                    case 5:
                        TabFriday fri = new TabFriday();
                        return fri;
                    default:
                        return null;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            if (sundayBool == true) {
                return 6;
            }
            else
                return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (sundayBool == true){
                switch (position) {
                    case 0:
                        return "Sunday";
                    case 1:
                        return "Monday";
                    case 2:
                        return "Tuesday";
                    case 3:
                        return "Wednesday";
                    case 4:
                        return "Thursday";
                    case 5:
                        return "Friday";

                }
            }
            else {
                switch (position) {
                    case 0:
                        return "Monday";
                    case 1:
                        return "Tuesday";
                    case 2:
                        return "Wednesday";
                    case 3:
                        return "Thursday";
                    case 4:
                        return "Friday";

                }
            }
            return null;
        }
    }

}
