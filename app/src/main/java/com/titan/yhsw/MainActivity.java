package com.titan.yhsw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.titan.model.Pest;
import com.titan.yhsw.adapter.MyFragmentPagerAdapter;
import com.titan.yhsw.fragment.JzswFragment;
import com.titan.yhsw.fragment.WhzzFragment;
import com.titan.yhsw.fragment.YhswFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.tv_title)
    TextView mTv_title;
    @BindView(R.id.rg_identify)
    RadioGroup mRg_identify;
    @BindView(R.id.rb_yhsw)
    RadioButton mRb_yhsw;
    @BindView(R.id.rb_whzz)
    RadioButton mRb_whzz;
    @BindView(R.id.rb_jzsw)
    RadioButton mRb_jzsw;
    @BindView(R.id.vp_identify)
    ViewPager mVp_identify;


    public List<Pest> getQueryPests() {
        return queryPests;
    }

    //查看结果
    private  List<Pest> queryPests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        List<Fragment> fmList = new ArrayList<>();
        fmList.add(new YhswFragment()); // 有害生物识别
        fmList.add(new WhzzFragment()); // 症状或危害状识别
        fmList.add(new JzswFragment()); // 寄主生物识别

        mVp_identify.addOnPageChangeListener(this);
        mVp_identify.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fmList));
        mRg_identify.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mTv_title.setText(R.string.yhsw);
                mRb_yhsw.setChecked(true);
                break;
            case 1:
                mTv_title.setText(R.string.whzz);
                mRb_whzz.setChecked(true);
                break;
            case 2:
                mTv_title.setText(R.string.jzsw);
                mRb_jzsw.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_yhsw:
                mTv_title.setText(R.string.yhsw);
                mVp_identify.setCurrentItem(0, false);
                break;
            case R.id.rb_whzz:
                mTv_title.setText(R.string.whzz);
                mVp_identify.setCurrentItem(1, false);
                break;
            case R.id.rb_jzsw:
                mTv_title.setText(R.string.jzsw);
                mVp_identify.setCurrentItem(2, false);
                break;
        }
    }

    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    /**
     * 保存MyTouchListener接口的列表
     */
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
}
