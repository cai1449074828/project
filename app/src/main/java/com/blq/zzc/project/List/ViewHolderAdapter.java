package com.blq.zzc.project.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blq.zzc.project.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 */

public class ViewHolderAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mLayoutInflater;
    public ViewHolderAdapter(List<String> list,Context context) {
        this.mContext=context;
        this.mList=list;
        mLayoutInflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View converView, ViewGroup group) {
        ViewHolder viewHolder=null;
        if (converView==null){
            viewHolder=new ViewHolder();
            converView=mLayoutInflater.inflate(R.layout.item_list,null);
            viewHolder.mImageView= (ImageView) converView.findViewById(R.id.imageView);
            viewHolder.mTextView= (TextView) converView.findViewById(R.id.textView);
            converView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) converView.getTag();
        }
        viewHolder.mTextView.setText("这是第"+i+"行");
        return converView;
    }

    public final class ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
    }
}
