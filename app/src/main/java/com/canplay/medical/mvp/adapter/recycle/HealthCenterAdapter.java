package com.canplay.medical.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.canplay.medical.R;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.bean.ORDER;
import com.canplay.medical.mvp.adapter.viewholder.HealtCenterHolder;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.CircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mykar on 17/4/12.
 */
public class HealthCenterAdapter extends BaseRecycleViewAdapter {

    private Context context;
    private int type;
    public HealthCenterAdapter(Context context,int type) {
        this.context = context;
        this.type=type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if(type==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, null);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_health, null);
        }


        return new HealtCenterHolder(view,type);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HealtCenterHolder holders = (HealtCenterHolder) holder;

        holders.ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickListener(0,"");
            }
        });
        if(type==0){
            final Friend data= (Friend) datas.get(position);
            if(TextUtil.isNotEmpty(data.name)){
                holders.name.setText(data.name);
            }  if(TextUtil.isNotEmpty(data.familyAndFriendsUserName)){
                holders.phone.setText(data.familyAndFriendsUserName);
            }
            Glide.with(context).load(data.avatar).asBitmap()
                    .placeholder(R.drawable.moren).transform(new CircleTransform(context)).into(holders.img);
        }else {

        }
     }

    @Override
    public int getItemCount() {
        int count = 0;

        if (datas != null && datas.size() > 0) {
            count = datas.size();
        }

        return count;
    }

    public void setClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public OnItemClickListener listener;
    public interface  OnItemClickListener{
        void clickListener(int poiston, String id);
    }
}
