package com.titan.yhsw;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.titan.model.Pest;
import com.titan.yhsw.databinding.ActivityImgdisplayBinding;



/**
 * 图片展示
 */
public class ImgDisplayActivity extends AppCompatActivity {

    private ActivityImgdisplayBinding binding;
    //
    private  Pest pest;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=this;
        binding=DataBindingUtil.setContentView(this,R.layout.activity_imgdisplay);
        //setContentView(R.layout.activity_show);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImgDisplayActivity.this.finish();
            }
        });
        try {
            //pest=getIntent().getBundleExtra("pest")
            pest = (Pest) getIntent().getExtras().getSerializable("pest");
            binding.setPest(pest);
            binding.tvTitle.setText(pest.getCname());
            if(pest.isHasimg()){
                Uri uri=Uri.parse("file://"+pest.getImgpath());
                binding.dv.setPhotoUri(uri);
                //Uri uri=new Uri.parse(pest.getImgpath());
                //binding.dv.s(pest.getImgpath());
            }
        }catch (Exception e){
            Toast.makeText(mContext,"获取图片信息失败"+e,Toast.LENGTH_LONG);
        }

    }


}
