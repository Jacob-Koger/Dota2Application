package com.jacob.dota2.dota2Application.RecentMatches;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jacobkoger.dota2Application.R;

import static android.view.LayoutInflater.from;

/**
 * A {@link GridLayout} used to display the radiant and dire hero images for each match
 */

public class HeroIconContainer extends GridLayout {
    /**
     * Constructor for {@code HeroIconContainer}
     *
     * @param context The {@link Context} to use
     * @param attrs   The attributes of the XML tag that is inflating the view
     */
    public HeroIconContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        for (int i = 0, size = getRowCount() * getColumnCount(); i < size; i++) {
            addView(from(context).inflate(R.layout.heroicon, this, false));
        }
    }
    public void bindIcons(int index, int resId) {
        Glide.with(getContext())
                .load(resId)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.blankhero)
                .placeholder(R.drawable.blankhero)
                .into((ImageView) getChildAt(index));
    }
}