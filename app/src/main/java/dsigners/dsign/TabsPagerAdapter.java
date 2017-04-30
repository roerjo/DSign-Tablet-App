package dsigners.dsign;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabsPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;
    public TabsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabGeneralFragment tab1 = new TabGeneralFragment();
                return tab1;
            case 1:
                TabMessagesFragment tab2 = new TabMessagesFragment();
                return tab2;
            case 2:
                TabCalendarFragment tab3 = new TabCalendarFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}

