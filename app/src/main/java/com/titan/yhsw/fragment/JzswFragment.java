package com.titan.yhsw.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.titan.yhsw.Biology;
import com.titan.yhsw.MainActivity;
import com.titan.yhsw.R;
import com.titan.yhsw.ShowActivity;
import com.titan.yhsw.SpaceItemDecoration;
import com.titan.yhsw.adapter.BiologyAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 32 on 2017/10/19.
 * 寄主生物识别
 */

public class JzswFragment extends Fragment {

    @BindView(R.id.ll_jzsw)
    LinearLayout mLl_jzsw;
    @BindView(R.id.et_keyword)
    EditText mEt_keyword;
    @BindView(R.id.tv_select)
    TextView mTv_select;
    @BindView(R.id.ll_num)
    LinearLayout mLl_num;
    @BindView(R.id.tv_num)
    TextView mTv_num;
    @BindView(R.id.rv_jzsw)
    RecyclerView mRv_jzsw;

    private View mInflate;

    Context mContext;

    List<Biology> allDatas = new ArrayList<>();
    List<Biology> showDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.fragment_jzsw, null);

        ButterKnife.bind(this, mInflate);

        mContext = this.getContext();

        initView();

        return mInflate;
    }

    private void initView() {
        // 设置列表条目间距
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_space);
        mRv_jzsw.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        setTouch();
        getData();

        mTv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = mEt_keyword.getText().toString();
                if (keyword.equals("")) {
                    Toast.makeText(mContext, "关键字不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    showDatas.clear();
                    checkData(keyword);
                    setData();
                }
            }
        });
    }

    /**
     * 设置数据
     */
    private void setData() {
        if (showDatas.size() == 0) {
            Toast.makeText(mContext, "未找到所需信息", Toast.LENGTH_SHORT).show();
        } else {
            //创建默认的线性LayoutManager, 竖直排布
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            mRv_jzsw.setLayoutManager(layoutManager);
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            mRv_jzsw.setHasFixedSize(true);
            //创建并设置Adapter
            BiologyAdapter adapter = new BiologyAdapter(showDatas, mContext);
            mRv_jzsw.setAdapter(adapter);

            mLl_num.setVisibility(View.VISIBLE);
            mTv_num.setText(String.valueOf(showDatas.size()));

            adapter.setItemClickListener(new BiologyAdapter.MyItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, ShowActivity.class);
                    Biology biology = showDatas.get(position);
                    intent.putExtra("Biology", biology);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * 查找数据
     */
    private void checkData(String keyword) {
        for (int i = 0; i < allDatas.size(); i++) {
            if (allDatas.get(i).getCNAME().contains(keyword) ||
                    allDatas.get(i).getENAME().contains(keyword) ||
                    allDatas.get(i).getFEATURE().contains(keyword)) {
                showDatas.add(allDatas.get(i));
            }
        }
    }

    /**
     * 获取数据
     */
    private void getData() {
        InputStream inputStream = getResources().openRawResource(R.raw.biology);
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sData = sb.toString();
        allDatas = new Gson().fromJson(sData, new TypeToken<ArrayList<Biology>>() {}.getType());
    }

    /**
     * 接收MainActivity的Touch回调的对象
     * 重写其中的onTouchEvent函数，并进行该Fragment的逻辑处理
     */
    private void setTouch() {
        MainActivity.MyTouchListener myTouchListener = new MainActivity.MyTouchListener() {
            @Override
            public void onTouchEvent(MotionEvent event) {
                // 处理手势事件
                mLl_jzsw.setFocusable(true);
                mLl_jzsw.setFocusableInTouchMode(true);
                mLl_jzsw.requestFocus();
            }
        };

        // 将myTouchListener注册到分发列表
        ((MainActivity)this.getActivity()).registerMyTouchListener(myTouchListener);
    }
}
