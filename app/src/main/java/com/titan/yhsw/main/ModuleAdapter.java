package com.titan.yhsw.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.titan.model.Module;
import com.titan.yhsw.R;

import java.util.List;

/**
 * Created by whs on 2017/11/30
 */

public class ModuleAdapter extends BaseAdapter {

    private Context mContext;
    private List<Module> moduleList;

    public ModuleAdapter(Context mContext, List<Module> moduleList) {
        this.mContext = mContext;
        this.moduleList = moduleList;
    }

    @Override
    public int getCount() {
        return moduleList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            //imageView = new ImageView(mContext);
            //binding= DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.item_module,parent,false);
            convertView=LayoutInflater.from(mContext).inflate(R.layout.item_module,null);
            viewHolder=new ViewHolder();
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            viewHolder.iv_img= (ImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_modulename);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.iv_img.setPadding(8, 8, 8, 8);
        viewHolder.iv_img.setImageResource(mThumbIds[position]);
        viewHolder.tv_name.setText(moduleList.get(position).getName());
        //convertView.setBackgroundColor(mColors[position]);

        //imageView.setImageResource(mThumbIds[position]);
        return convertView;

    }

    static class ViewHolder {
        TextView tv_name;
        ImageView iv_img;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.ic_pest, R.drawable.ic_host,
            R.drawable.ic_pest_normal, R.drawable.ic_description,
    };

    private Integer[] mColors = {
            R.color.redcolor, R.color.bluecolor,
            R.color.yellow, R.color.green,
    };
}
