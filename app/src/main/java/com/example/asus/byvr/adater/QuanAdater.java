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
import com.example.asus.byvr.bean.ImageItem;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ASUS on 2017/12/21.
 */

public class QuanAdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ImageItem> imageItems;
    Context context;

    public QuanAdater(List<ImageItem> imageItems, Context context) {
        this.imageItems = imageItems;
        this.context = context;
    }

    public interface OnclickLinster{
        void OnItemClickLinster(View v,int pos);
    }
    private OnclickLinster onclickLinster;

    public void setOnclickLinster(OnclickLinster onclickLinster) {
        this.onclickLinster = onclickLinster;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderQ(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemquan,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if(onclickLinster!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onclickLinster.OnItemClickLinster(holder.itemView,pos);
                }
            });
        }
        if(holder instanceof ViewHolderQ){
            ((ViewHolderQ) holder).qt1.setText(imageItems.get(position).getTitle());
             Glide.with(context).load(imageItems.get(position).getUrl()).into(((ViewHolderQ) holder).qi1);
        }
    }

    @Override
    public int getItemCount() {
        return imageItems!=null?imageItems.size():0;
    }

    class ViewHolderQ extends RecyclerView.ViewHolder{
        ImageView qi1;
        TextView qt1;
        public ViewHolderQ(View itemView) {
            super(itemView);

            qi1 = itemView.findViewById(R.id.qvrimage);
            qt1 = itemView.findViewById(R.id.qtv);
        }
    }
}
