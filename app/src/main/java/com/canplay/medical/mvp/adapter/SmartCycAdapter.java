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
import com.canplay.medical.bean.Box;
import com.canplay.medical.bean.Euip;
import com.canplay.medical.util.TimeUtil;

import java.util.List;

import retrofit2.http.Headers;


public class SmartCycAdapter extends BaseAdapter {
    private Context mContext;
    private List<Box> list;
    private int type=0;//1是用药记录item
    public SmartCycAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public interface ItemCliks{
        void getItem(Box menu, int type);//type 1表示点击事件2 表示长按事件
    }
    private ItemCliks listener;
    public void setClickListener(ItemCliks listener){
        this.listener=listener;
    }
    public void setData(List<Box> list){
        this.list=list;
        notifyDataSetChanged();
    }


    public void setType(int type){
        this.type=type;

    }
    @Override
    public int getCount() {
        return list!=null?list.size():0;
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
        final Box box=list.get(position);
        if(box!=null&&box.dateTime!=0){
            String time = TimeUtil.formatTimes(box.dateTime);
            String[] split = time.split("-");
            holder.tvTime.setText(split[0]);
            holder.tvTimes.setText(split[1]);
        }else {
            holder.ll_item.setBackground(mContext.getResources().getDrawable(R.drawable.line_cyc_red));
            holder.tvTimes.setTextColor(mContext.getResources().getColor(R.color.red_cyc));
            holder.tvTime.setVisibility(View.GONE);
            holder.tvTimes.setVisibility(View.VISIBLE);
            holder.tvTimes.setText("空");
        }


        if(box.status==3){
            holder.ll_item.setBackground(mContext.getResources().getDrawable(R.drawable.blue_cycle));
            holder.tvTime.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvTimes.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvTimes.setVisibility(View.VISIBLE);
        }else if(box.status==0){
            holder.ll_item.setBackground(mContext.getResources().getDrawable(R.drawable.line_cyc_red));
            holder.tvTimes.setTextColor(mContext.getResources().getColor(R.color.red_cyc));
            holder.tvTime.setVisibility(View.GONE);
            holder.tvTimes.setText("空");
            holder.tvTimes.setVisibility(View.VISIBLE);
        }else {
            holder.ll_item.setBackground(mContext.getResources().getDrawable(R.drawable.line_cyc_blue));
            holder.tvTime.setTextColor(mContext.getResources().getColor(R.color.blue));
            holder.tvTimes.setTextColor(mContext.getResources().getColor(R.color.txt_blue));
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvTimes.setVisibility(View.VISIBLE);
        }
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).status==0){
                    return;
                }
                for(int i=0;i<list.size();i++){
                    if(i==position){
                        list.get(i).status=3;
                    }else {
                        if(  list.get(i).status==3){
                            list.get(i).status=1;
                        }
                    }
                }

                listener.getItem(box,position);
                notifyDataSetChanged();
            }
        });
        return view;


    }

    public  class ResultViewHolder{

        TextView tvTime;
        TextView tvTimes;

        LinearLayout ll_item;

    }
}
