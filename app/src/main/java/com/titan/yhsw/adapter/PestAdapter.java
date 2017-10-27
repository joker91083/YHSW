package com.titan.yhsw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.titan.model.Pest;
import com.titan.yhsw.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 32 on 2017/10/20.
 * 查询结果显示Adapter
 */

public class PestAdapter extends RecyclerView.Adapter<PestAdapter.MyViewHolder> {

    List<Pest> mDates = new ArrayList<>();
    Context mContext;

    private MyItemClickListener mItemClickListener;

    public PestAdapter(List<Pest> dates, Context context) {
        mDates = dates;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, null), mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mTv_cname.setText(mDates.get(position).getCname());
        holder.mTv_ename.setText(mDates.get(position).getEname());
        holder.mTv_door.setText(mDates.get(position).getPhylum());
        holder.mTv_feature.setText(mDates.get(position).getFeature());
        if(mDates.get(position).isHasimg()){
            holder.iv_img.setImageBitmap(mDates.get(position).getBitmap());
        }
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView iv_img;
        private final TextView mTv_cname;
        private final TextView mTv_ename;
        private final TextView mTv_door;
        private final TextView mTv_more;
        private final TextView mTv_feature;

        private MyItemClickListener mListener;

        public MyViewHolder(View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);

            iv_img = (ImageView) itemView.findViewById(R.id.iv_pic);
            mTv_cname = (TextView) itemView.findViewById(R.id.tv_cname);
            mTv_ename = (TextView) itemView.findViewById(R.id.tv_ename);
            mTv_door = (TextView) itemView.findViewById(R.id.tv_door);
            mTv_more = (TextView) itemView.findViewById(R.id.tv_more);
            mTv_feature = (TextView) itemView.findViewById(R.id.tv_feature);

            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            mTv_more.setOnClickListener(this);
        }

        /**
         * 实现OnClickListener接口重写的方法
         */
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getPosition());
            }
        }
    }

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }
}
