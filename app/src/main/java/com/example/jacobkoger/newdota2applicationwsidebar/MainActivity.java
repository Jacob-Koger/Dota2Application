package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.example.jacobkoger.newdota2applicationwsidebar.POJO_AccountInfo.AccountInfo;
import com.example.jacobkoger.newdota2applicationwsidebar.POJO_AccountInfo.Player;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements InputMatchIDFragment.OnMatchSelectionListener, DisplayMatchDetailsFragment.OnFragmentInteractionListener, AccountDetailsFragment.OnFragmentInteractionListener {

    String url = "https://api.steampowered.com";
    String accountid = null;
    String Name, AccountName, UserID, Avatar;
    TextView RealName, Username, ProfileURL;
    ImageView ProfilePicture;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("player_id", Context.MODE_PRIVATE);
        accountid = sharedPreferences.getString("player_id", null);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = setupDrawerToggle();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, new InputMatchIDFragment())
                .commit();
        if (accountid == null) {
            View header = nvDrawer.inflateHeaderView(R.layout.navbar_header);
            View headerView = nvDrawer.inflateHeaderView(R.layout.navbar_headerwaccount);

            nvDrawer.removeHeaderView(header);
            nvDrawer.removeHeaderView(headerView);
            nvDrawer.inflateMenu(R.menu.drawer_view_nonlogged);
            nvDrawer.addHeaderView(header);
            Log.d("header1", String.valueOf(nvDrawer.getHeaderView(0)));


        } else {
            View header = nvDrawer.inflateHeaderView(R.layout.navbar_header);
            View headerView = nvDrawer.inflateHeaderView(R.layout.navbar_headerwaccount);

            nvDrawer.removeHeaderView(header);
            nvDrawer.removeHeaderView(headerView);
            nvDrawer.addHeaderView(headerView);
            nvDrawer.inflateMenu(R.menu.drawer_view);
            RealName = (TextView) headerView.findViewById(R.id.accountname);
            Username = (TextView) headerView.findViewById(R.id.username);
            ProfilePicture = (ImageView) headerView.findViewById(R.id.profilepic);
            ProfileURL = (TextView) headerView.findViewById(R.id.profileURL);
            Log.d("header2", String.valueOf(nvDrawer.getHeaderView(0)));
            getResult();
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }

                }
        );

    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        if (accountid != null) {
            switch (menuItem.getItemId()) {
                case R.id.nav_InputMatchID_fragment:
                    fragmentClass = InputMatchIDFragment.class;
                    break;
                case R.id.nav_RecentMatches_fragment:
                    fragmentClass = RecentMatchesFragment.class;
                    break;
                case R.id.nav_AccountDetails_fragment:
                    fragmentClass = AccountDetailsFragment.class;
                    break;
                default:
                    fragmentClass = InputMatchIDFragment.class;
            }

            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            switch (menuItem.getItemId()) {
                case R.id.nav_InputMatchID_fragment:
                    fragmentClass = InputMatchIDFragment.class;
                    break;
                case R.id.nav_RecentMatches_fragment:
                    fragmentClass = RecentMatchesFragment.class;
                    break;
                case R.id.nav_Login_fragment:
                    fragmentClass = LoginFragment.class;
                    break;
                default:
                    fragmentClass = InputMatchIDFragment.class;
            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fragmentClass == InputMatchIDFragment.class || fragmentClass == LoginFragment.class) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
            mDrawer.closeDrawers();

        } else if (fragmentClass == AccountDetailsFragment.class) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("name", Name);
            bundle.putString("accountname", AccountName);
            bundle.putString("userid", UserID);
            bundle.putString("avatar", Avatar);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            setTitle(menuItem.getTitle());
            menuItem.setChecked(true);
            mDrawer.closeDrawers();
        } else if (fragmentClass == RecentMatchesFragment.class) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("userid", UserID);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            setTitle(menuItem.getTitle());
            menuItem.setChecked(true);
            mDrawer.closeDrawers();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onMatchSelected(String matchId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, DisplayMatchDetailsFragment.newInstance(matchId))
                .addToBackStack(null)
                .commit();
    }

    public void getResult() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                final Request orig = chain.request();
                HttpUrl origUrl = orig.url();
                return chain.proceed(orig.newBuilder()
                        .url(origUrl.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).addQueryParameter("steamids", accountid)
                                .build())
                        .build());
            }
        });

        final Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AccountDetailsInterface service = retrofit.create(AccountDetailsInterface.class);
        Call<AccountInfo> callMH = service.getResponse();
        callMH.enqueue(new Callback<AccountInfo>() {

            @Override
            public void onResponse(Call<AccountInfo> call, Response<AccountInfo> response) {
                AccountInfo accountInfo = response.body();
                Player player = accountInfo.getResponse().getPlayers().get(0);
                if (player.getProfilestate() == 1) {
                    if (player.getCommunityvisibilitystate() == 3) {
                        SharedPreferences sharedPreferences = getSharedPreferences("player_id", Context.MODE_PRIVATE);
                        Name = player.getRealname();
                        AccountName = player.getPersonaname();
                        UserID = player.getSteamid();
                        Avatar = player.getAvatarfull();
                        sharedPreferences.edit().putString("username", player.getPersonaname()).apply();
                        RealName.setText(player.getRealname());
                        Username.setText(player.getPersonaname() + " - ");
                        ProfileURL.setText(player.getProfileurl());
                        Picasso.with(MainActivity.this).load(player.getAvatarfull()).into(ProfilePicture);
                    } else {
                        RealName.setText("");
                        Username.setText("Community Profile is not setup");
                        ProfileURL.setText("");
                    }
                } else {
                    RealName.setText("");
                    Username.setText("Account is Private");
                    ProfileURL.setText("");
                }
            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}