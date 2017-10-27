package com.titan.yhsw;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.titan.model.Pest;
import com.titan.yhsw.databinding.ActivityShowBinding;

import butterknife.BindView;

/**
 * 条目详情
 */
public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView mIv_back;
    @BindView(R.id.tv_title)
    TextView mTv_title;
    @BindView(R.id.iv_pic)
    ImageView mIv_pic;
    @BindView(R.id.tv_cname)
    TextView mTv_cname;
    @BindView(R.id.tv_ename)
    TextView mTv_ename;
    @BindView(R.id.tv_alias)
    TextView mTv_alias;
    @BindView(R.id.tv_door)
    TextView mTv_door;
    @BindView(R.id.tv_collect)
    TextView mTv_collect;
    @BindView(R.id.tv_distribution)
    TextView mTv_distribution;
    @BindView(R.id.tv_host)
    TextView mTv_host;
    @BindView(R.id.tv_feature)
    TextView mTv_feature;
    @BindView(R.id.tv_control)
    TextView mTv_control;

    private ActivityShowBinding binding;
    //
    private  Pest pest;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        binding=DataBindingUtil.setContentView(this,R.layout.activity_show);
        //setContentView(R.layout.activity_show);
        try {
            pest = (Pest) getIntent().getSerializableExtra("pest");
            binding.setPest(pest);

        }catch (Exception e){
            Toast.makeText(mContext,"初始化数据异常"+e,Toast.LENGTH_LONG);
        }
        //ButterKnife.bind(this);

        //initView();
    }

    private void initView() {

        Biology biology = (Biology)getIntent().getSerializableExtra("Biology");
        mTv_title.setText(biology.getCNAME());

        mTv_cname.setVisibility(View.VISIBLE);
        mTv_cname.setText(biology.getCNAME());

        mTv_ename.setVisibility(View.VISIBLE);
        mTv_ename.setText(biology.getENAME());

        mTv_door.setVisibility(View.VISIBLE);
        mTv_door.setText(biology.getDOOR());

        mTv_feature.setVisibility(View.VISIBLE);
        mTv_feature.setText(biology.getFEATURE());

        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
