package com.nowwego.hakwonga.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nowwego.hakwonga.R;

/**
 * Main screen
 */
public class MainActivity extends AppCompatActivity {

    private NavigationHomeFragment homeFragment;
    // TODO: other fragments

    private TabLayout mainNavigation;


    /**
     * Creates a starting intent to start this activity
     * @param context Parent context
     * @return Starting intent
     */
    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNavigationFragments();

        customizeActionBar();
        bindViews();
        initNavigationTabLayout();
    }

    private void bindViews() {
        mainNavigation = findViewById(R.id.tabLayout_main_navigation);
    }

    private void customizeActionBar() {
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_main);
    }

    private void initNavigationFragments() {
        homeFragment = NavigationHomeFragment.newInstance("Home");
        // TODO: creation of other fragments
    }

    private void initNavigationTabLayout() {
        mainNavigation.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                navigate(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // There is nothing to do here
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // There is nothing to do here
            }
        });

        mainNavigation.getTabAt(0).select();
    }

    private void navigate(int toPosition) {
        switch (toPosition) {
            case 0:
                loadFragment(homeFragment);
                break;
            case 1:
                //loadFragment();
                break;
            case 2:
                //loadFragment();
                break;
            case 3:
                //loadFragment();
                break;
            default:
                loadFragment(homeFragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.navigation_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
