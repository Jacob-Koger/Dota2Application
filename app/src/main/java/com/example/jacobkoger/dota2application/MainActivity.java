package com.example.jacobkoger.dota2application;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.jacobkoger.dota2application.RecentMatches.RecentMatchesContract;
import com.example.jacobkoger.dota2application.RecentMatches.RecentMatchesFragment;
import com.example.jacobkoger.dota2application.RecentMatches.RecentMatchesPresenter;
import static com.example.jacobkoger.dota2application.util.NetworkUtils.isConnected;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawer;
    private RecentMatchesContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.navView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setupDrawerContent(nvDrawer);

        mDrawerToggle = setupDrawerToggle(toolbar);

        final RecentMatchesFragment recentMatches = new RecentMatchesFragment();
        presenter = new RecentMatchesPresenter(this, recentMatches);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, recentMatches)
                .commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                            mDrawer.closeDrawers();
                        }
                        return true;
                    }
                }
        );
    }

    private ActionBarDrawerToggle setupDrawerToggle(Toolbar toolbar) {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onConnectivityChanged(isConnected(this));
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }


}
