package com.github.hotchpotch.iconicfontengine;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.github.hotchpotch.fontengine.IconicFontEngine;
import com.github.hotchpotch.fontengine.widget.IconicFontTextView;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // createFontAwesomeEngine();
        IconicFontEngine.addDefaultEngine(
                new FontAwesomeEngine(Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf")));
        IconicFontEngine.addDefaultEngine(
                new TypiconsEngine(Typeface.createFromAsset(getAssets(), "fonts/typicons.ttf")));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return MainFragment.newInstance();
            } else {
                return IconicFontEngineIconsFragment.newInstance(position - 1);
            }
        }

        @Override
        public int getCount() {
            return 1 + IconicFontEngine.getDefaultEngines().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
                case 2:
                    return getString(R.string.title_section3);
            }
            return null;
        }
    }

    public static class MainFragment extends Fragment {
        public static MainFragment newInstance() {
            return new MainFragment();
        }
        public MainFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }
    }

    public static class IconicFontEngineIconsFragment extends Fragment {
        private static final String ENGINE_POSITION = "engine_position";

        public static IconicFontEngineIconsFragment newInstance(int position) {
            IconicFontEngineIconsFragment fragment = new IconicFontEngineIconsFragment();
            Bundle args = new Bundle();
            args.putInt(ENGINE_POSITION, position);
            fragment.setArguments(args);
            return fragment;
        }

        public IconicFontEngineIconsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ScrollView rootView = (ScrollView) inflater.inflate(R.layout.iconic_font_engine_icons_fragment, container, false);
            LinearLayout iconicContainer = (LinearLayout) rootView.findViewById(R.id.iconic_container);
            int enginePosition = getArguments().getInt(ENGINE_POSITION);
            IconicFontEngine engine = IconicFontEngine.getDefaultEngines().get(enginePosition);
            int position = 0;
            for (String iconName: engine.getIconicFontMap().keySet()) {
                LinearLayout view = (LinearLayout) inflater.inflate(R.layout.iconic_view_layout, container, false);
                IconicFontTextView tv = (IconicFontTextView) view.findViewById(R.id.iconic_font_text_view);
                tv.setText("{" + iconName + "} " + iconName);
                view.setBackgroundResource(position % 2 == 0 ? R.color.background_even : R.color.background_odd);
                iconicContainer.addView(view);
                position++;
            }
            return rootView;
        }
    }
}
