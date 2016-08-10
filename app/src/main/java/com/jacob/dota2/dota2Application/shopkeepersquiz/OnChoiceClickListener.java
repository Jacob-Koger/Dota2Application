package com.jacob.dota2.dota2Application.shopkeepersquiz;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jacobkoger.dota2Application.R;

public class OnChoiceClickListener implements View.OnClickListener {
    CharSequence item;
    Context context;
    ImageView iv;
    boolean is1filled;
    boolean is2filled;
    boolean is3filled;
    boolean is4filled;
    boolean is5filled;
    boolean is6filled;
    boolean isVisible;

    private ItemAnswerContainer itemAnswerContainer;

    public OnChoiceClickListener(CharSequence item, Context context, ImageView iv, ItemAnswerContainer itemAnswerContainer) {
        this.item = item;
        this.context = context;
        this.iv = iv;
        this.itemAnswerContainer = itemAnswerContainer;

    }

    @Override
    public void onClick(View view) {
        final int resID = context.getResources().getIdentifier(String.valueOf(item), "drawable", context.getPackageName());
        Toast.makeText(context, item, Toast.LENGTH_SHORT).show();

        if (!is1filled) {
            if (!isVisible) {
                iv.setImageResource(resID);
                itemAnswerContainer.Bind1(R.drawable.emptyitembg);
                isVisible = true;
                is1filled = true;
            } else if (isVisible) {
                iv.setImageResource(R.drawable.emptyitembg);
                itemAnswerContainer.Bind1(resID);
                isVisible = false;
                is1filled = false;
            }
        } else if (is1filled) {
            if (!is2filled) {
                if (!isVisible) {
                    iv.setImageResource(resID);
                    itemAnswerContainer.Bind2(R.drawable.emptyitembg);
                    isVisible = true;
                    is2filled = true;
                } else if (isVisible) {
                    iv.setImageResource(R.drawable.emptyitembg);
                    itemAnswerContainer.Bind2(resID);
                    isVisible = false;
                    is2filled = false;
                }
            } else if (is2filled) {
                if (!is3filled) {
                    if (!isVisible) {
                        iv.setImageResource(resID);
                        itemAnswerContainer.Bind3(R.drawable.emptyitembg);
                        isVisible = true;
                        is3filled = true;
                    } else if (isVisible) {
                        iv.setImageResource(R.drawable.emptyitembg);
                        itemAnswerContainer.Bind3(resID);
                        isVisible = false;
                        is3filled = false;
                    }
                } else if (is3filled) {
                    if (!is4filled) {
                        if (!isVisible) {
                            iv.setImageResource(resID);
                            itemAnswerContainer.Bind4(R.drawable.emptyitembg);
                            isVisible = true;
                            is4filled = true;
                        } else if (isVisible) {
                            iv.setImageResource(R.drawable.emptyitembg);
                            itemAnswerContainer.Bind4(resID);
                            isVisible = false;
                            is4filled = false;
                        }
                    } else if (is4filled) {
                        if (!is5filled) {
                            if (!isVisible) {
                                iv.setImageResource(resID);
                                itemAnswerContainer.Bind5(R.drawable.emptyitembg);
                                isVisible = true;
                                is5filled = true;
                            } else if (isVisible) {
                                iv.setImageResource(R.drawable.emptyitembg);
                                itemAnswerContainer.Bind5(resID);
                                isVisible = false;
                                is5filled = false;
                            }
                        } else if (!is5filled) {
                            if (!is6filled) {
                                if (!isVisible) {
                                    iv.setImageResource(resID);
                                    itemAnswerContainer.Bind6(R.drawable.emptyitembg);
                                    isVisible = true;
                                    is6filled = true;
                                } else if (isVisible) {
                                    iv.setImageResource(R.drawable.emptyitembg);
                                    itemAnswerContainer.Bind6(resID);
                                    isVisible = false;
                                    is6filled = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}