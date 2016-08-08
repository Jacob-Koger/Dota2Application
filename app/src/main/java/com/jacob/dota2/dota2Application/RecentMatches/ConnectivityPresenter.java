package com.jacob.dota2.dota2Application.RecentMatches;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

import com.jacob.dota2.dota2Application.util.NetworkUtils;

import java.lang.ref.WeakReference;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;
import static com.jacob.dota2.dota2Application.util.NetworkUtils.isConnected;
import static com.jacob.dota2.dota2Application.util.Utils.requireNonNull;

/**
 * A {@link RecentMatchesContract.Presenter} that has connectivity awareness
 */
public abstract class ConnectivityPresenter implements RecentMatchesContract.Presenter {

    private final Context mContext;

    private final ConnectivityReceiver mConnectivityReceiver;

    private boolean mIsRegistered;

    protected ConnectivityPresenter(@NonNull Context context) {
        mContext = requireNonNull(context.getApplicationContext(), "Presenter must not be null");
        mConnectivityReceiver = new ConnectivityReceiver(this);
    }

    @Override
    public void onStart() {
        if (!mIsRegistered) {
            mContext.registerReceiver(mConnectivityReceiver, new IntentFilter(CONNECTIVITY_ACTION));
            mIsRegistered = true;
        }
    }

    @Override
    public void onStop() {
        if (mIsRegistered) {
            mContext.unregisterReceiver(mConnectivityReceiver);
            mIsRegistered = false;
        }
    }

    @Override
    public void onConnectivityChanged(boolean isConnected) {
        throw new UnsupportedOperationException("no-op");
    }

    static final class ConnectivityReceiver extends BroadcastReceiver {

        private final WeakReference<RecentMatchesContract.Presenter> mPresenter;

        ConnectivityReceiver(@NonNull RecentMatchesContract.Presenter presenter) {
            requireNonNull(presenter, "Presenter must not be null");
            mPresenter = new WeakReference<>(presenter);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            final RecentMatchesContract.Presenter presenter = mPresenter.get();
            if (presenter != null) {
                presenter.onConnectivityChanged(NetworkUtils.isConnected(context));
            }
        }

    }

}
