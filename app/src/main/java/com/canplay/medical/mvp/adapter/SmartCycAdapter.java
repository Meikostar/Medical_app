package com.canplay.medical.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.bean.Euip;

import java.util.List;

import retrofit2.http.Headers;


public class SmartCycAdapter extends BaseAdapter {
    private Context mContext;
    private List<Euip> list;
    private int type=0;//1是用药记录item
    public SmartCycAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks{
        void getItem(Euip menu, int type);//type 1表示点击事件2 表示长按事件
    }
    private ItemCliks listener;
    public void setClickListener(ItemCliks listener){
        this.listener=listener;
    }
    public void setData(List<Euip> list){
        this.list=list;
        notifyDataSetChanged();
    }


    public void setType(int type){
        this.type=type;

    }
    @Override
    public int getCount() {
        return list!=null?list.size():6;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ResultViewHolder holder;
        if (view == null){
            holder = new ResultViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_cyc, parent, false);
            holder.tvTime= (TextView) view.findViewById(R.id.tv_time);
            holder.tvTimes= (TextView) view.findViewById(R.id.tv_times);
            holder.ll_item= (LinearLayout) view.findViewById(R.id.ll_bg);


            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        if(position==0){
            holder.ll_item.setBackground(mContext.getResources().getDrawable(R.drawable.blue_cycle));
            holder.tvTime.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvTimes.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvTimes.setVisibility(View.VISIBLE);
        }else if(position==2){
            holder.ll_item.setBackground(mContext.getResources().getDrawable(R.drawable.line_cyc_red));
            holder.tvTimes.setTextColor(mContext.getResources().getColor(R.color.red_cyc));
            holder.tvTime.setVisibility(View.GONE);
            holder.tvTimes.setVisibility(View.VISIBLE);
        }else {
            holder.ll_item.setBackground(mContext.getResources().getDrawable(R.drawable.line_cyc_blue));
            holder.tvTime.setTextColor(mContext.getResources().getColor(R.color.blue));
            holder.tvTimes.setTextColor(mContext.getResources().getColor(R.color.txt_blue));
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvTimes.setVisibility(View.VISIBLE);
        }
        return view;


    }

    public  class ResultViewHolder{

        TextView tvTime;
        TextView tvTimes;

        LinearLayout ll_item;

    }
}
