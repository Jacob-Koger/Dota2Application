package com.jacob.dota2.dota2Application.shopkeepersquiz;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.jacobkoger.dota2Application.R;

import static android.view.LayoutInflater.from;

public class ItemChoiceContainer extends GridLayout{

    public ItemChoiceContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        for(int i = 0, size = 6; i < size; i++) {
            addView(from(context).inflate(R.layout.itembutton, this, false));
        }
    }
    public void Bind1(int ResID){
        BindIcons(ResID, 0);
    }
    public void Bind2(int ResID){
        BindIcons(ResID, 1);
    }
    public void Bind3(int ResID){
        BindIcons(ResID, 2);
    }
    public void Bind4(int ResID){
        BindIcons(ResID, 3);
    }
    public void Bind5(int ResID){
        BindIcons(ResID, 4);
    }
    public void Bind6(int ResID){
        BindIcons(ResID, 5);
    }
    public void BindRandom(int ResID, int index){
        BindIcons(ResID, index);
    }

    public void BindIcons(int ResID, int index){
        ImageView imageView = ((ImageView) getChildAt(index));
        imageView.setImageResource(ResID);
        Log.d("ran", String.valueOf(index));
    }
}
