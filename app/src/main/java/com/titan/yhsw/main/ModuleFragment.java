package com.titan.yhsw.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.titan.model.Module;
import com.titan.yhsw.MainActivity;
import com.titan.yhsw.R;
import com.titan.yhsw.databinding.FragMainBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hanyw on 2017/9/13/013.
 * 主界面
 */

public class ModuleFragment extends Fragment implements Main {
    private Context mContext;
    private ModuleViewModel mViewModel;
    private FragMainBinding binding;
    private Dialog dialog;
    private static ModuleFragment singleton;

    public static ModuleFragment newInstance() {
        if (singleton == null) {
            singleton = new ModuleFragment();
        }
        return singleton;
    }

    public void setViewModel(ModuleViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        binding = FragMainBinding.inflate(inflater, container, false);
        ///String appName = getString(R.string.app_version) + AppUtil.getAppVersion(mContext);
        //fragLoginBinding.tvAppversion.setText(appName);
        binding.setViewmdoel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //intiRecyclerView();

        initGridView();


    }

    /**
     *
     */
    private void initGridView() {
        List<Module> modules=new ArrayList<>();
        String[] names=mContext.getResources().getStringArray(R.array.modules);
        for (int i = 0; i <names.length ; i++) {
            modules.add(new Module(names[i]));
        }
        ModuleAdapter adapter=new ModuleAdapter(mContext,modules);
        binding.gv.setAdapter(adapter);
        binding.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                intent.putExtra("type",position);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
    }




    @Override
    public void onPest() {
        //startActivity(new Intent(getContext(),));
    }

    @Override
    public void onHost() {

    }

    @Override
    public void onCommon() {

    }

    @Override
    public void description() {

    }
}
