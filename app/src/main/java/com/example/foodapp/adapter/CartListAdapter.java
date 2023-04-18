package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemsListener;
import com.example.foodapp.R;
import com.example.foodapp.model.PopularModel;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private ArrayList<PopularModel> popularModels;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<PopularModel> popularModels, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.popularModels = popularModels;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(popularModels.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(popularModels.get(position).getFee()));
        holder.num.setText(String.valueOf(popularModels.get(position).getNumberInCart()));
        holder.totalEachItem.setText(String.valueOf(Math.round((popularModels.get(position).getNumberInCart() * popularModels.get(position).getFee()) * 100) / 100));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularModels.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                managementCart.plusNumberFood(popularModels, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });

            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                managementCart.minusNumberFood(popularModels, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return popularModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleCartTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.num);

        }
    }
}
