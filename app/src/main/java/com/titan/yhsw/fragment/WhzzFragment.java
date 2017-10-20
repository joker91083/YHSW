package com.titan.yhsw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.titan.yhsw.R;

/**
 * Created by 32 on 2017/10/19.
 * 症状或危害状识别
 */

public class WhzzFragment extends Fragment {

    private View mInflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.fragment_whzz, null);
        return mInflate;
    }
}
