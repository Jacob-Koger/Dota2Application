package com.jacob.dota2.dota2Application.shopkeepersquiz;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class ShopkeepersQuizFragment extends Fragment {
    private static Context mContext;
    private final List<Items> itemsList = new ArrayList<>(0);
    private final List<Build> buildList = new ArrayList<>(0);
    private ItemAnswerContainer itemAnswerContainer;
    private ItemChoiceContainer itemChoiceContainer;
    private Resources mRes;
    private String mPkgName;

    public static View.OnClickListener onClickChoice(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (index) {
                    case 0: {
                        Toast.makeText(mContext, "1", Toast.LENGTH_SHORT).show();
                    }
                    case 1: {
                        Toast.makeText(mContext, "2", Toast.LENGTH_SHORT).show();
                    }
                    case 2: {
                        Toast.makeText(mContext, "3", Toast.LENGTH_SHORT).show();
                    }
                    case 3: {
                        Toast.makeText(mContext, "4", Toast.LENGTH_SHORT).show();
                    }
                    case 4: {
                        Toast.makeText(mContext, "5", Toast.LENGTH_SHORT).show();
                    }
                    case 5: {
                        Toast.makeText(mContext, "6", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };
    }

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
            Log.d("build", String.valueOf(buildList.size()));
            Log.d("items", String.valueOf(itemsList.size()));
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
        itemAnswerContainer.setColumnCount(4);
        itemAnswerContainer.setRowCount(1);
        itemChoiceContainer = (ItemChoiceContainer) view.findViewById(R.id.item_choice_container);
        setImages();
    }

    private void setImages() {
        for (int i = 0, col = itemAnswerContainer.getColumnCount() *
                itemAnswerContainer.getRowCount(); i < col; i++) {
            itemAnswerContainer.BindIcons(i);
        }
        for (Items items : itemsList) {
                Build build = buildList.get(0);
                final int resId1 = mRes.getIdentifier(build.getItem1(), "drawable", mPkgName);
                for (int i = 0, col = itemChoiceContainer.getColumnCount() *
                        itemChoiceContainer.getRowCount(); i < col; i++) {
                    itemChoiceContainer.BindIcons(resId1, i);
                }
            }
        }
    }




