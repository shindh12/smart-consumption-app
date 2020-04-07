package com.ysj.sc.layout.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ysj.MainActivity;
import com.ysj.sc.layout.listener.OnBottomButtonClickListener;

public class BaseFragment extends Fragment implements OnBottomButtonClickListener {
    private static String TAG = BaseFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onBottomButtonClick() {
        Log.d(TAG, "onBottomButtonClick.");
    }
}
