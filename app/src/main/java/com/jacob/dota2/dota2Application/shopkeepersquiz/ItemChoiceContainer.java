package com.jacob.dota2.dota2Application.shopkeepersquiz;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.jacobkoger.dota2Application.R;

import static android.view.LayoutInflater.from;

public class ItemChoiceContainer extends GridLayout{

    public ItemChoiceContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        for(int i = 0, size = 6; i < size; i++) {
            addView(from(context).inflate(R.layout.itembutton, this, false));
        }
    }
    public void BindIcons(int ResID, int index){
        Glide.with(getContext())
                .load(ResID)
                .fitCenter()
                .crossFade()
                .placeholder(R.drawable.emptyitembg)
                .error(R.drawable.emptyitembg)
                .into((ImageButton) getChildAt(index));
        getChildAt(index).setOnClickListener(ShopkeepersQuizFragment.onClickChoice(index));

    }
}
