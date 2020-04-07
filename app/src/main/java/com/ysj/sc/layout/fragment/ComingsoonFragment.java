package com.ysj.sc.layout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ysj.sc.R;

public class ComingsoonFragment extends Fragment {
    private static final String TAG = ComingsoonFragment.class.getSimpleName();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_comingsoon, container, false);

        return view;
    }

}
