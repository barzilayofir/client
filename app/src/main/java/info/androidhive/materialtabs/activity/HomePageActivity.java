package info.androidhive.materialtabs.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.fragments.AppsFragment;
import info.androidhive.materialtabs.fragments.DeviceFragment;
import info.androidhive.materialtabs.fragments.NetworkFragment;

public class HomePageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView mTabDevice;
    private TextView mTabApps;
    private TextView mTabNetwork;
    private List<TextView> mTabTextViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        mTabDevice = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        mTabDevice.setText(R.string.device);
        mTabDevice.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
        tabLayout.getTabAt(0).setCustomView(mTabDevice);
        mTabDevice.setTextColor(getResources().getColor(R.color.pageBackground));

        mTabApps = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        mTabApps.setText(R.string.apps);
        mTabApps.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_call, 0, 0);
        tabLayout.getTabAt(1).setCustomView(mTabApps);

        mTabNetwork = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        mTabNetwork.setText(R.string.network);
        mTabNetwork.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
        tabLayout.getTabAt(2).setCustomView(mTabNetwork);

        mTabTextViews.add(mTabDevice);
        mTabTextViews.add(mTabApps);
        mTabTextViews.add(mTabNetwork);
    }

    /**
     * Adding fragments to ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(final ViewPager viewPager) {
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(24);
        viewPager.setPadding(48, 8, 48, 8);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DeviceFragment(), "ONE");
        adapter.addFrag(new AppsFragment(), "TWO");
        adapter.addFrag(new NetworkFragment(), "THREE");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment selected = adapter.getItem(position);
                int color = 0;
                for (int i = 0; i < mTabTextViews.size(); i++) {
                    TextView textView = mTabTextViews.get(i);
                    if (i == position) {
                        color = getResources().getColor(R.color.pageBackground);
                    } else {
                        color = getResources().getColor(R.color.textUnselected);
                    }
                    textView.setTextColor(color);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
