package com.jacob.dota2.dota2Application.shopkeepersquiz;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jacobkoger.dota2Application.R;
import com.google.gson.Gson;
import com.jacob.dota2.dota2Application.data.items.Build;
import com.jacob.dota2.dota2Application.data.items.Items;
import com.jacob.dota2.dota2Application.data.items.ItemsList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopkeepersQuizFragment extends Fragment {
    private static Context mContext;

    private final List<Items> itemsList = new ArrayList<>(0);
    private final List<Build> buildList = new ArrayList<>(0);

    private ItemAnswerContainer itemAnswerContainer;
    private ItemChoiceContainer itemChoiceContainer;

    private Resources mRes;
    private String mPkgName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        try (InputStream in = mContext.getAssets().open("Items.json")) {
            final ItemsList Il = new Gson().fromJson(new InputStreamReader(in), ItemsList.class);
            itemsList.addAll(Il.getItems());
            for (Items items : itemsList) {
                buildList.addAll(items.getBuild());
            }
        } catch (IOException ignored) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (inflater.inflate(R.layout.fragment_shopkeepers_quiz, container, false));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPkgName = mContext.getPackageName();
        mRes = view.getResources();
        super.onViewCreated(view, savedInstanceState);

        itemAnswerContainer = (ItemAnswerContainer) view.findViewById(R.id.item_answer_container);
        itemAnswerContainer.setColumnCount(6);
        itemAnswerContainer.setRowCount(1);
        itemChoiceContainer = (ItemChoiceContainer) view.findViewById(R.id.item_choice_container);
        randomizeImages();
    }

    private void randomizeImages() {
        Build build = buildList.get(0);
        Random random = new Random();
        int randomInt = random.nextInt(3) + 1;
        switch (randomInt) {
            case 1:
                setImages(build.getItem1(), build.getItem2(), build.getItem3(),
                        build.getItem4(), build.getItem5(), build.getItem6());
                break;
            case 2:
                setImages(build.getItem1(), build.getItem3(), build.getItem4(),
                        build.getItem5(), build.getItem6(), build.getItem2());
                break;
            case 3:
                setImages(build.getItem1(), build.getItem2(), build.getItem4(),
                        build.getItem5(), build.getItem6(), build.getItem3());
                break;
        }
    }

    private int getRandomInt() {
        Random random = new Random();
        int o = random.nextInt(242) + 1;
        return o;
    }

    private void getRandomItem(int index) {
        int id = getRandomInt();
        for (int i = 0, list = 242; i < list; i++) {
            Items items = itemsList.get(i);
            if (id == items.getId()) {
                final int resRandomID = mRes.getIdentifier(items.getName(), "drawable", mPkgName);
                itemChoiceContainer.BindRandom(resRandomID, index);
            }
        }
    }

    private void setImages(String item1, String item2, String item3,
                           String item4, String item5, String item6) {
        for (int i = 0, col = itemAnswerContainer.getColumnCount() *
                itemAnswerContainer.getRowCount(); i < col; i++) {
            itemAnswerContainer.BindIcons(R.drawable.emptyitembg, i);
        }

        if (item1 != null) {
            final int resId1 = mRes.getIdentifier(item1, "drawable", mPkgName);
            itemChoiceContainer.Bind1(resId1);
            itemChoiceContainer.getChildAt(0).setOnClickListener(new OnChoiceClickListener(item1,
                    getContext(), (ImageView) itemChoiceContainer.getChildAt(0), itemAnswerContainer));

        } else {
            getRandomItem(0);
        }

        if (item2 != null) {
            final int resId2 = mRes.getIdentifier(item2, "drawable", mPkgName);
            itemChoiceContainer.Bind2(resId2);
            itemChoiceContainer.getChildAt(1).setOnClickListener(new OnChoiceClickListener(item2,
                    getContext(), (ImageView) itemChoiceContainer.getChildAt(1), itemAnswerContainer));

        } else {
            getRandomItem(1);
        }

        if (item3 != null) {
            final int resId3 = mRes.getIdentifier(item3, "drawable", mPkgName);
            itemChoiceContainer.Bind3(resId3);
            itemChoiceContainer.getChildAt(2).setOnClickListener(new OnChoiceClickListener(item3,
                    getContext(), (ImageView) itemChoiceContainer.getChildAt(2), itemAnswerContainer));

        } else {
            getRandomItem(2);
        }

        if (item4 != null) {
            final int resId4 = mRes.getIdentifier(item4, "drawable", mPkgName);
            itemChoiceContainer.Bind4(resId4);
            itemChoiceContainer.getChildAt(3).setOnClickListener(new OnChoiceClickListener(item4,
                    getContext(), (ImageView) itemChoiceContainer.getChildAt(3), itemAnswerContainer));

        } else {
            getRandomItem(3);
        }

        if (item5 != null) {
            final int resId5 = mRes.getIdentifier(item5, "drawable", mPkgName);
            itemChoiceContainer.Bind5(resId5);
            itemChoiceContainer.getChildAt(4).setOnClickListener(new OnChoiceClickListener(item5,
                    getContext(), (ImageView) itemChoiceContainer.getChildAt(4), itemAnswerContainer));


        } else {
            getRandomItem(4);
        }

        if (item6 != null) {
            final int resId6 = mRes.getIdentifier(item6, "drawable", mPkgName);
            itemChoiceContainer.Bind6(resId6);
            itemChoiceContainer.getChildAt(5).setOnClickListener(new OnChoiceClickListener(item6,
                    getContext(), (ImageView) itemChoiceContainer.getChildAt(5), itemAnswerContainer));

        } else {
            getRandomItem(5);
        }
    }
}