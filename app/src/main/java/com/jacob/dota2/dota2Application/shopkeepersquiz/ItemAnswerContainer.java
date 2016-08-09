package com.jacob.dota2.dota2Application.shopkeepersquiz;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.jacobkoger.dota2Application.R;

import static android.view.LayoutInflater.from;


public class ItemAnswerContainer extends GridLayout {

 public ItemAnswerContainer(Context context, AttributeSet attrs){
        super(context, attrs);
        for (int i = 0, size = 4; i < size; i++) {
            addView(from(context).inflate(R.layout.itembutton, this, false));
        }
    }

    public void BindIcons(int index) {

        Glide.with(getContext())
                .load(R.drawable.emptyitembg)
                .crossFade()
                .fitCenter()
                .placeholder(R.drawable.emptyitembg)
                .error(R.drawable.emptyitembg)
                .into((ImageButton) getChildAt(index));
    }
}
