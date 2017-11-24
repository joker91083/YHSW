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
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.titan.TitanApplication;
import com.titan.data.source.Injection;
import com.titan.data.source.local.LDataSource;
import com.titan.model.Pest;
import com.titan.util.TitanFileFilter;
import com.titan.yhsw.Biology;
import com.titan.yhsw.ImgDisplayActivity;
import com.titan.yhsw.MainActivity;
import com.titan.yhsw.R;
import com.titan.yhsw.ShowActivity;
import com.titan.yhsw.SpaceItemDecoration;
import com.titan.yhsw.adapter.PestAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 32 on 2017/10/19.
 * 有害生物查询
 */

public class WhzzFragment extends Fragment {

    @BindView(R.id.ll_whzz)
    LinearLayout mLl_whzz;
    @BindView(R.id.et_keyword)
    EditText mEt_keyword;
    @BindView(R.id.tv_select)
    TextView mTv_select;
    @BindView(R.id.ll_num)
    LinearLayout mLl_num;
    @BindView(R.id.tv_num)
    TextView mTv_num;
    @BindView(R.id.rv_whzz)
    RecyclerView mRv_whzz;
    @BindView(R.id.ctv_gs)
    CheckedTextView ctvGs;
    @BindView(R.id.ctv_yb)
    CheckedTextView ctvYb;
    @BindView(R.id.ctv_jb)
    CheckedTextView ctvJb;
    @BindView(R.id.ctv_gb)
    CheckedTextView ctvGb;

    private View mInflate;

    Context mContext;

    List<Biology> allDatas = new ArrayList<>();
    List<Biology> showDatas = new ArrayList<>();

    //查询结果
    private List<Pest> queryPests = new ArrayList<>();
    //危害部位
    private Set<String> whbwset = new HashSet<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.fragment_whzz, null);

        ButterKnife.bind(this, mInflate);

        mContext = this.getContext();

        initView();

        return mInflate;
    }

    private void initView() {
        // 设置列表条目间距
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_space);
        mRv_whzz.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        setTouch();
        ctvYb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView)v;
                checkedTextView.toggle();
                if(checkedTextView.isChecked()){
                    whbwset.add("叶部");
                }
                else {
                    whbwset.remove("叶部");
                }
            }
        });

        ctvGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView)v;
                checkedTextView.toggle();
                if(checkedTextView.isChecked()){
                    whbwset.add("根部");
                }
                else {
                    whbwset.remove("根部");
                }
            }
        });

        ctvJb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView)v;
                checkedTextView.toggle();
                if(checkedTextView.isChecked()){
                    whbwset.add("茎部");
                }
                else {
                    whbwset.remove("茎部");
                }
            }
        });
        ctvGs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView)v;
                checkedTextView.toggle();
                if(checkedTextView.isChecked()){
                    whbwset.add("果实");
                }
                else {
                    whbwset.remove("果实");
                }
            }
        });


        queryPest("");

        mTv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = mEt_keyword.getText().toString();
                if (keyword.equals("")&& whbwset.isEmpty()) {
                    queryPest("");
                    //Toast.makeText(mContext, "请输入查询条件", Toast.LENGTH_SHORT).show();
                } else {
                    queryPest(keyword);
                   /* showDatas.clear();
                    checkData(keyword);
                    setData();*/
                }
            }
        });
    }


    /**
     * 查询病虫害
     *
     * @param keyword
     */
    private void queryPest(final String keyword) {
        queryPests.clear();

        Injection.provideDataRepository(getActivity()).queryPest(1, keyword,whbwset, new LDataSource.qureyCalllback() {
            @Override
            public void onFailure(String info) {
                Toast.makeText(mContext, "有害生物查询失败" + info, Toast.LENGTH_LONG).show();
                showPest();
            }

            @Override
            public void onSuccess(List<Pest> data) {
                if (data == null || data.isEmpty()) {
                    Toast.makeText(mContext, "未找到所需信息", Toast.LENGTH_LONG).show();
                    showPest();
                } else {
                    Toast.makeText(mContext, "查询到" + data.size() + "条数据", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < data.size(); i++) {
                        String imgforder = mContext.getDatabasePath(TitanApplication.IMGS).getAbsolutePath();
                        File file = new File(imgforder);
                        File[] files = file.listFiles(new TitanFileFilter.ImgFileFilter());
                        for (File f : files) {
                            if (f.getName().contains(data.get(i).getCname())) {
                                data.get(i).setHasimg(true);
                                data.get(i).setImgpath(f.getAbsolutePath());
                            }
                        }
                    }
                    queryPests.addAll(data);
                    showPest();


                }


            }
        });
    }

    /**
     * 更新界面
     */
    private void showPest() {

        //创建默认的线性LayoutManager, 竖直排布
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRv_whzz.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRv_whzz.setHasFixedSize(true);
        //创建并设置Adapter
        PestAdapter adapter = new PestAdapter(queryPests, mContext);
        mRv_whzz.setAdapter(adapter);

        mLl_num.setVisibility(View.VISIBLE);
        mTv_num.setText(String.valueOf(queryPests.size()));

        adapter.setItemClickListener(new PestAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pest", queryPests.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onImgClick(View view, int position) {
                Intent intent = new Intent(mContext, ImgDisplayActivity.class);
                //Biology biology = showDatas.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pest", queryPests.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
                mLl_whzz.setFocusable(true);
                mLl_whzz.setFocusableInTouchMode(true);
                mLl_whzz.requestFocus();
            }
        };

        // 将myTouchListener注册到分发列表
        ((MainActivity) this.getActivity()).registerMyTouchListener(myTouchListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
