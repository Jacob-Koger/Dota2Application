package com.jacob.dota2.dota2Application.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jacob.dota2.dota2Application.CacheStrategy;
import com.example.jacobkoger.dota2Application.R;
import com.jacob.dota2.dota2Application.RecentMatches.RecentMatchesContract;
import com.jacob.dota2.dota2Application.RecentMatches.RecentMatchesFragment;
import com.jacob.dota2.dota2Application.RecentMatches.RecentMatchesPresenter;
import com.jacob.dota2.dota2Application.ResponseCallbacks.GenericCallback;
import com.jacob.dota2.dota2Application.Wiki.WikiFragment;
import com.jacob.dota2.dota2Application.clients.LoggedInDetailsClient;
import com.jacob.dota2.dota2Application.data.accountinfo.AccountInfo;
import com.jacob.dota2.dota2Application.data.accountinfo.Player;
import com.jacob.dota2.dota2Application.data.hero.Hero;
import com.jacob.dota2.dota2Application.data.hero.HeroesList;
import com.google.gson.Gson;
import com.jacob.dota2.dota2Application.shopkeepersquiz.ShopkeepersQuizFragment;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.jacob.dota2.dota2Application.util.NetworkUtils.isConnected;

public class MainActivity extends AppCompatActivity {
    final List<Hero> mHeroes = new ArrayList<>();
    TextView RealName;
    TextView Username;
    TextView ProfileURL;
    ImageView ProfilePicture;
    NavigationView nvDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private RecentMatchesContract.Presenter presenter;
    private String accountid;
    private int number = 9;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Gson gson = new Gson();
        final AssetManager am = this.getAssets();

        InputStream in = null;
        try {
            in = am.open("Heroes.json");
            final HeroesList hl = gson.fromJson(new InputStreamReader(in), HeroesList.class);
            mHeroes.addAll(hl.getHeroes());
            Collections.sort(mHeroes, new Comparator<Hero>() {
                @Override
                public int compare(Hero h1, Hero h2) {
                    return h1.getLocalizedName().compareTo(h2.getLocalizedName());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        SharedPreferences sharedPreferences = getSharedPreferences("player_id", Context.MODE_PRIVATE);
        accountid = sharedPreferences.getString("player_id", null);

        final RecentMatchesFragment recentMatches = new RecentMatchesFragment();
        presenter = new RecentMatchesPresenter(this, recentMatches);

        final ShopkeepersQuizFragment shopkeepersQuizFragment = new ShopkeepersQuizFragment();

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.navView);

        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                            mDrawer.closeDrawers();
                        }
                        String title = item.getTitle().toString();
                        Log.d("title", title);
                        if (Objects.equals(title, "Recent Matches")) {

                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.flContent, recentMatches)
                                    .commit();
                            onStart();
                            item.setCheckable(true);
                            item.setChecked(true);
                            setTitle(item.getTitle());
                        } else if(Objects.equals(title, "Shopkeeper's Quiz")){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.flContent, shopkeepersQuizFragment)
                                    .commit();
                            item.setCheckable(true);
                            item.setChecked(true);
                            setTitle(item.getTitle());
                        } else {
                            Fragment fragment = null;
                            Class fragmentClass;
                            fragmentClass = WikiFragment.class;
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            item.setCheckable(true);
                            item.setChecked(true);
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            Bundle bundle = new Bundle();
                            bundle.putString("hero", item.getTitle().toString());
                            fragment.setArguments(bundle);
                            fragmentManager.beginTransaction().addToBackStack(null)
                                    .replace(R.id.flContent, fragment).commit();
                            setTitle(item.getTitle());
                        }
                        return false;
                    }
                }
        );
        mDrawerToggle = setupDrawerToggle();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, recentMatches)
                .commit();
        Menu m = nvDrawer.getMenu();

        for (final Hero hero : mHeroes) {
            m.add(R.id.group2, 0, 0, hero.getLocalizedName());
        }
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
            ProfilePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number--;
                    if (number == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG);

                        toast.show();
                        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.egg);
                        try {
                            mediaPlayer.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        number = 9;
                    }
                }
            });
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

    public void setActionBarTitle(String title) {
        setTitle(title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Menu menu = nvDrawer.getMenu();
        MenuItem item = menu.findItem(R.id.menu_nav_recent);
        item.setCheckable(true);
        item.setChecked(true);
    }
}
