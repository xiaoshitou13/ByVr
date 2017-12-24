package com.example.asus.byvr.adater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.byvr.R;
import com.example.asus.byvr.bean.Datas;

import java.util.List;

/**
 * Created by ASUS on 2017/12/22.
 */

public class VideoAdataer extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Datas> datas;
    Context context;

    public VideoAdataer(List<Datas> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    public interface OnclickLinsters{
        void OnItemClickLinster(View v,int pos);
    }
    private OnclickLinsters onclickLinsters;

    public void setOnclickLinster(OnclickLinsters onclickLinster) {
        this.onclickLinsters = onclickLinster;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderV(LayoutInflater.from(parent.getContext()).inflate(R.layout.videoitem,parent,false));
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(onclickLinsters!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onclickLinsters.OnItemClickLinster(holder.itemView,pos);
                }
            });
        }
          if(holder instanceof ViewHolderV){
              ((ViewHolderV) holder).vt1.setText(datas.get(0).getContent().get(position).getTitle());
              Glide.with(context).load(datas.get(0).getContent().get(position).getImg()).into(((ViewHolderV) holder).vi1);

          }
    }

    @Override
    public int getItemCount() {
        return datas.get(0).getContent().size();
    }

    class ViewHolderV extends RecyclerView.ViewHolder{
        ImageView vi1;
        TextView vt1;
        public ViewHolderV(View itemView) {
            super(itemView);

            vi1 = itemView.findViewById(R.id.videoim);
            vt1 = itemView.findViewById(R.id.videoname);
        }
    }
}
