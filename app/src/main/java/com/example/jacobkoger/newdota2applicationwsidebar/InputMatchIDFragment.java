package com.example.jacobkoger.newdota2applicationwsidebar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class InputMatchIDFragment extends android.support.v4.app.Fragment {

    private OnMatchSelectionListener mListener;


    public InputMatchIDFragment() {
    }

    public static InputMatchIDFragment newInstance(String param1, String param2) {
        return new InputMatchIDFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.input_match_id_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View goToMatchDetails = view.findViewById(R.id.goToMatchDetails);
        final EditText matchInput = (EditText) view.findViewById(R.id.editText);
        goToMatchDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(String.valueOf(matchInput.getText()));
            }
        });
    }

    public void onButtonPressed(String matchId) {
        if (mListener != null) {
            mListener.onMatchSelected(matchId);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMatchSelectionListener) {
            mListener = (OnMatchSelectionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMatchSelectionListener {
        void onMatchSelected(String matchId);
    }


}
