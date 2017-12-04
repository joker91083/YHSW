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
import android.widget.AutoCompleteTextView;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.titan.TitanApplication;
import com.titan.data.source.Injection;
import com.titan.data.source.local.LDataSource;
import com.titan.model.Img;
import com.titan.model.Pest;
import com.titan.util.TitanFileFilter;
import com.titan.yhsw.HostAdapter;
import com.titan.yhsw.ImgDisplayActivity;
import com.titan.yhsw.MainActivity;
import com.titan.yhsw.R;
import com.titan.yhsw.ShowActivity;
import com.titan.yhsw.SpaceItemDecoration;
import com.titan.yhsw.adapter.PestAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 32 on 2017/10/19.
 * 寄主植物查询
 */

public class JzswFragment extends Fragment {

    @BindView(R.id.ll_jzsw)
    LinearLayout mLl_jzsw;
    @BindView(R.id.et_keyword)
    AutoCompleteTextView mEt_keyword;
    @BindView(R.id.tv_select)
    TextView mTv_select;
    @BindView(R.id.ll_num)
    LinearLayout mLl_num;
    @BindView(R.id.tv_num)
    TextView mTv_num;
    @BindView(R.id.rv_jzsw)
    RecyclerView mRv_jzsw;
    @BindView(R.id.ctv_ss)
    CheckedTextView ctvSs;
    @BindView(R.id.ctv_bs)
    CheckedTextView ctvBs;
    @BindView(R.id.ctv_shanshu)
    CheckedTextView ctvShanshu;
    @BindView(R.id.ctv_yc)
    CheckedTextView ctvYc;


    private View mInflate;

    Context mContext;

    //查询结果
    private List<Pest> queryPests = new ArrayList<>();

    //常用寄主集合
    private Set<String> hostset = new HashSet<>();

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
        ctvSs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView) v;
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    hostset.add("松");
                } else {
                    hostset.remove("松");
                }
            }
        });
        ctvBs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView) v;
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    hostset.add("柏");
                } else {
                    hostset.remove("柏");
                }
            }
        });
        ctvShanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView) v;
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    hostset.add("杉");
                } else {
                    hostset.remove("杉");
                }
            }
        });

        ctvYc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView checkedTextView = (CheckedTextView) v;
                checkedTextView.toggle();
                if (checkedTextView.isChecked()) {
                    hostset.add("油茶");
                } else {
                    hostset.remove("油茶");
                }
            }
        });
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext,R.layout.item_host,mContext.getResources().getStringArray(R.array.host));
        List<String> listhost= Arrays.asList(mContext.getResources().getStringArray(R.array.host));
        HostAdapter<String> adapter=new HostAdapter<>(mContext,listhost);
        mEt_keyword.setAdapter(adapter);
        mEt_keyword.setThreshold(1);
        /*mEt_keyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });*/

        //queryPest("");
        mTv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = mEt_keyword.getText().toString();
                if (keyword.equals("")&& hostset.isEmpty()) {
                    Toast.makeText(mContext, "请输入查询条件", Toast.LENGTH_SHORT).show();
                    //queryPest("");
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

        Injection.provideDataRepository(getActivity()).queryPest(3, keyword, hostset, new LDataSource.qureyCalllback() {
            @Override
            public void onFailure(String info) {
                Toast.makeText(mContext, "寄主生物查询失败" + info, Toast.LENGTH_LONG).show();
                showPest();
            }

            @Override
            public void onSuccess(List<Pest> data) {
                if (data == null || data.isEmpty()) {
                    Toast.makeText(mContext, "未找到所需信息", Toast.LENGTH_LONG).show();
                    showPest();

                } else {
                    //Toast.makeText(mContext, "查询到" + data.size() + "条数据", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < data.size(); i++) {
                        String imgforder = mContext.getDatabasePath(TitanApplication.IMGS).getAbsolutePath();
                        File file = new File(imgforder);
                        File[] files = file.listFiles(new TitanFileFilter.ImgFileFilter());
                        List<Img> imgList=new ArrayList<>();
                        for (File f : files) {
                           if(f.getName().startsWith(data.get(i).getCname().trim())){
                                data.get(i).setHasimg(true);
                                Img img=new Img();
                                img.setPath(f.getAbsolutePath());
                                img.setName(f.getName());
                                imgList.add(img);
                                data.get(i).setImgpath(imgList);
                            }
                            /*if (f.getName().split("-")[0].equals(data.get(i).getCname())) {

                                //data.get(i).setImgpath(f.getAbsolutePath());
                            }*/
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
        mRv_jzsw.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRv_jzsw.setHasFixedSize(true);
        //创建并设置Adapter
        PestAdapter adapter = new PestAdapter(queryPests, mContext);
        mRv_jzsw.setAdapter(adapter);

        mLl_num.setVisibility(View.VISIBLE);
        mTv_num.setText(String.valueOf(queryPests.size()));

        adapter.setItemClickListener(new PestAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ShowActivity.class);
                Bundle bundle = new Bundle();
                //bundle.putParcelable("pest", queryPests.get(position));
                bundle.putSerializable("pest",queryPests.get(position));
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
                mLl_jzsw.setFocusable(true);
                mLl_jzsw.setFocusableInTouchMode(true);
                mLl_jzsw.requestFocus();
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
