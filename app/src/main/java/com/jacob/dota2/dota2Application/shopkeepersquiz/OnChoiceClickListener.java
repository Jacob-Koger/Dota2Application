package com.jacob.dota2.dota2Application.shopkeepersquiz;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class OnChoiceClickListener implements View.OnClickListener {
    CharSequence item;
    Context context;

    public OnChoiceClickListener(CharSequence item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
    }
}
