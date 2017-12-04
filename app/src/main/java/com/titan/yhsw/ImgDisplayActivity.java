package com.titan.yhsw;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.titan.model.Pest;
import com.titan.yhsw.databinding.ActivityImgdisplayBinding;

import in.myinnos.imagesliderwithswipeslibrary.Animations.DescriptionAnimation;
import in.myinnos.imagesliderwithswipeslibrary.SliderLayout;
import in.myinnos.imagesliderwithswipeslibrary.SliderTypes.BaseSliderView;
import in.myinnos.imagesliderwithswipeslibrary.SliderTypes.TextSliderView;


/**
 * 图片展示
 */
public class ImgDisplayActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {

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
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImgDisplayActivity.this.finish();
            }
        });
        try {
            //pest=getIntent().getBundleExtra("pest")
            pest = (Pest) getIntent().getExtras().getSerializable("pest");
            binding.setPest(pest);
            binding.tvTitle.setText(pest.getCname().trim());
            if(pest.isHasimg()){
                //Uri uri=Uri.parse("file://"+pest.getImgpath().get(0).getPath());
                //binding.dv.setPhotoUri(uri);
                for (int i = 0; i <pest.getImgpath().size() ; i++) {
                    TextSliderView textSliderView = new TextSliderView(ImgDisplayActivity.this);
                    // initialize a SliderLayout
                    textSliderView
                            .description(pest.getImgpath().get(i).getName())
                            .descriptionSize(12)
                            .image("file://"+pest.getImgpath().get(i).getPath())
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(ImgDisplayActivity.this);
                    binding.slider.addSlider(textSliderView);
                }

                binding.slider.setPresetTransformer(SliderLayout.Transformer.Stack);
                binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
                binding.slider.setCustomAnimation(new DescriptionAnimation());
                binding.slider.setDuration(5000);

                //Uri uri=new Uri.parse(pest.getImgpath());
                //binding.dv.s(pest.getImgpath());
            }
            binding.tvTitle.setText(pest.getCname());
        }catch (Exception e){
            Toast.makeText(mContext,"获取数据失败"+e,Toast.LENGTH_LONG);
        }

    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.slider.stopAutoCycle();

    }
}
