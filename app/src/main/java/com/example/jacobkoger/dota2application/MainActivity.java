package com.example.jacobkoger.dota2application;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacobkoger.dota2application.RecentMatches.RecentMatchesContract;
import com.example.jacobkoger.dota2application.RecentMatches.RecentMatchesFragment;
import com.example.jacobkoger.dota2application.RecentMatches.RecentMatchesPresenter;
import com.example.jacobkoger.dota2application.ResponseCallbacks.GenericCallback;
import com.example.jacobkoger.dota2application.clients.LoggedInDetailsClient;
import com.example.jacobkoger.dota2application.data.accountinfo.AccountInfo;
import com.example.jacobkoger.dota2application.data.accountinfo.Player;
import com.squareup.picasso.Picasso;

import static com.example.jacobkoger.dota2application.util.NetworkUtils.isConnected;

public class MainActivity extends AppCompatActivity {
    TextView RealName;
    TextView Username;
    TextView ProfileURL;
    ImageView ProfilePicture;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private RecentMatchesContract.Presenter presenter;
    private String accountid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("player_id", Context.MODE_PRIVATE);
        accountid = sharedPreferences.getString("player_id", null);

        final RecentMatchesFragment recentMatches = new RecentMatchesFragment();
        presenter = new RecentMatchesPresenter(this, recentMatches);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.navView);
        setupDrawerContent(nvDrawer);

        mDrawerToggle = setupDrawerToggle();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, recentMatches)
                .commit();

        if (accountid == null) {
            View header = nvDrawer.inflateHeaderView(R.layout.navbar_header);
            View headerView = nvDrawer.inflateHeaderView(R.layout.navbar_headerwaccount);

            nvDrawer.removeHeaderView(header);
            nvDrawer.removeHeaderView(headerView);

            nvDrawer.addHeaderView(header);
            Log.d("header1", "hit1");

        } else {
            View header = nvDrawer.inflateHeaderView(R.layout.navbar_header);
            View headerView = nvDrawer.inflateHeaderView(R.layout.navbar_headerwaccount);

            nvDrawer.removeHeaderView(header);
            nvDrawer.removeHeaderView(headerView);

            nvDrawer.addHeaderView(headerView);

            RealName = (TextView) headerView.findViewById(R.id.accountname);
            Username = (TextView) headerView.findViewById(R.id.username);
            ProfilePicture = (ImageView) headerView.findViewById(R.id.profilepic);
            ProfileURL = (TextView) headerView.findViewById(R.id.profileURL);

            Log.d("header2", "hit2");

            getResult(this);
        }
    }

    private void getResult(Context context) {
        final Context mContext = context;
        LoggedInDetailsClient.with(this).cacheStrategy(CacheStrategy.NONE)
                .enqueueMatchHistory(new GenericCallback<AccountInfo>() {
                    @Override
                    public void onFailure(@Nullable Throwable t) {

                    }

                    @Override
                    public void onSuccess(AccountInfo accountInfo) {
                        SharedPreferences sharedPreferences = getSharedPreferences("player_id", Context.MODE_PRIVATE);
                        Player player = accountInfo.getResponse().getPlayers().get(0);

                        sharedPreferences.edit().putString("username", player.getPersonaname()).apply();
                        RealName.setText(player.getRealname());
                        Username.setText(player.getPersonaname() + " - ");
                        ProfileURL.setText(player.getProfileurl());
                        Picasso.with(mContext).load(player.getAvatarfull()).into(ProfilePicture);

                    }
                });
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

    private ActionBarDrawerToggle setupDrawerToggle() {
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
